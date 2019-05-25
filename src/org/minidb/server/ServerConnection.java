package org.minidb.server;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.minidb.database.Database;
import org.minidb.exception.MiniDBException;
import org.minidb.grammar.*;
import org.minidb.relation.Relation;
import org.minidb.relation.RelationMeta;
import org.minidb.utils.Misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ServerConnection extends minisqlBaseVisitor<ResultTable> implements Runnable {
    private Socket socket;
    private Path dataDir;
    private Database currentDB;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean closed;

    ServerConnection(Socket socket, Path dataDir, Path defaultDBPath) throws IOException, MiniDBException, ClassNotFoundException {
        this.socket = socket;
        this.dataDir = dataDir;
        currentDB = new Database(defaultDBPath.toString());
        currentDB.resume();
        closed = false;
    }

    private void send(Object o) throws IOException {
        oos.writeObject(o);
    }

    private void handle() throws IOException, ClassNotFoundException {
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        while (socket != null)
        {
            try{
                String line = (String)ois.readObject();
                minisqlLexer lexer = new minisqlLexer(CharStreams.fromString(line));
                AccumulateErrorListener listener = new AccumulateErrorListener();
                lexer.removeErrorListeners();
                lexer.addErrorListener(listener);
                minisqlParser parser = new minisqlParser(new CommonTokenStream(lexer));
                parser.removeErrorListeners();
                parser.addErrorListener(listener);
                ParseTree tree = parser.sql_stmt();
                if(listener.hasError())
                {
                    send(ResultTable.getSimpleMessageTable(listener.getAllMessage()));
                }else{
                    ResultTable result = visit(tree);
                    send(result);
                }
                if(closed)
                {
                    socket.close();
                    socket = null;
                }
            }catch (Exception e)
            {
                oos.writeObject(e);
            }
        }
    }

    private void add_unique_ArrayList(ArrayList<ArrayList<Integer>> target, ArrayList<Integer> source)
    {
        for(int i = 0; i < target.size(); i++)
        {
            if(target.get(i).equals(source))
            {
                return;
            }
        }
        target.add(source);
    }

    private void add_to_ArrayList_by_name(ArrayList<ArrayList<Integer>> target, List<minisqlParser.Column_nameContext> source, ArrayList<String> colnames)
    {
        ArrayList<Integer> int_source = source.stream().map(x -> colnames.indexOf(x.IDENTIFIER().getText())).collect(Collectors.toCollection(ArrayList::new));
        if(int_source.indexOf(-1) != -1)
            throw new ParseCancellationException("Col name(s) not found!");
        add_unique_ArrayList(target, int_source);
    }

    @Override
    public void run() {
        try{
            handle();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ResultTable visitCreate_table(minisqlParser.Create_tableContext ctx) {
        try {
            //获取table_name
            String table_name = ctx.table_name().IDENTIFIER().getText();
            if(currentDB.getRelation(table_name) != null)
                return ResultTable.getSimpleMessageTable(String.format("Create table failed: table %s already exists.", table_name));

            //开始解析命令内容, 期间检测命令合法性, 不做实际建表
            //这张表是否已经设定了primary_key
            boolean setted_primary_key = false;

            //解析列数据
            //列数
            int ncols = 0;
            //列名
            ArrayList<String> colnames = new ArrayList<String>();
            //列类型
            ArrayList<Type> coltypes = new ArrayList<Type>();
            //列大小
            ArrayList<Integer> colsizes = new ArrayList<Integer>();
            //可空列的ID
            ArrayList<Integer> nullableColIds = new ArrayList<Integer>();
            //superKey列ID(对primary key的列和unique的列建立superkey)
            ArrayList<ArrayList<Integer>> superKeys = new ArrayList<ArrayList<Integer>>();
            //索引列ID(对not null的单列建立索引)
            ArrayList<ArrayList<Integer>> indices = new ArrayList<ArrayList<Integer>>();

            List<minisqlParser.Column_defContext> column_list = ctx.column_def();
            for(int i = 0; i < column_list.size(); i++)
            {
                minisqlParser.Column_defContext column = column_list.get(i);

                //维护列数
                ncols++;

                //解析列名
                colnames.add(column.column_name().IDENTIFIER().getText());

                //解析列类型
                //这一列是否已经指定了类型
                if(column.type_name().K_INT() != null)
                {
                    coltypes.add(Integer.class);
                    colsizes.add(4);
                }
                if(column.type_name().K_LONG() != null)
                {
                    coltypes.add(Long.class);
                    colsizes.add(8);
                }
                if(column.type_name().K_FLOAT() != null)
                {
                    coltypes.add(Float.class);
                    colsizes.add(4);
                }
                if(column.type_name().K_DOUBLE() != null)
                {
                    coltypes.add(Double.class);
                    colsizes.add(8);
                }
                if(column.type_name().K_VARCHAR() != null)
                {
                    coltypes.add(String.class);
                    String string_length_str = column.type_name().NUMERIC_LITERAL().getText();
                    if(string_length_str.contains("."))
                    {
                        //字符串的长度是带小数点的
                        return ResultTable.getSimpleMessageTable("Create table failed: the length of row " + colnames.get(i) + " is invalid.");
                    }
                    else
                    {
                        colsizes.add(Integer.valueOf(string_length_str));
                    }
                }

                //解析列属性
                //primary key属性
                if(column.K_PRIMARY() != null && column.K_KEY() != null)
                {
                    if(setted_primary_key)
                    {
                        return ResultTable.getSimpleMessageTable(String.format("Create table failed: table %s has multiple primary keys.", table_name));
                    }
                    else
                    {
                        add_unique_ArrayList(superKeys, new ArrayList<Integer>(Arrays.asList(i)));
                        setted_primary_key = true;
                    }
                }

                //unique属性
                if(column.K_UNIQUE() != null)
                {
                    add_unique_ArrayList(superKeys, new ArrayList<Integer>(Arrays.asList(i)));
                }

                //not null属性
                if(column.K_NOT() != null && column.K_NULL() != null)
                {
                    add_unique_ArrayList(indices, new ArrayList<Integer>(Arrays.asList(i)));
                }

                if(column.K_NOT() == null && column.K_PRIMARY() == null && column.K_UNIQUE() == null)
                {
                    nullableColIds.add(i);
                }
            }

            if(new HashSet<String>(colnames).size() != colnames.size())
                return ResultTable.getSimpleMessageTable(String.format("Create table failed: table %s has duplicate column names.", table_name));

            Integer sizeSum = colsizes.stream().reduce(0, Integer::sum);
            if(sizeSum > 512)
            {
                return ResultTable.getSimpleMessageTable(String.format("Create table failed: row size (%d) too large!", sizeSum));
            }

            //解析表属性
            List<minisqlParser.Table_constraintContext> table_constraint_list = ctx.table_constraint();
            for(minisqlParser.Table_constraintContext table_constraint : table_constraint_list) {
                //primary key属性
                if (table_constraint.K_PRIMARY() != null && table_constraint.K_KEY() != null) {
                    if (setted_primary_key) {
                        return ResultTable.getSimpleMessageTable(String.format("Create table failed: table %s has multiple primary keys.", table_name));
                    } else {
                        add_to_ArrayList_by_name(superKeys, table_constraint.column_name(), colnames);
                        setted_primary_key = true;
                    }
                }
                //unique属性
                if (table_constraint.K_UNIQUE() != null) {
                    add_to_ArrayList_by_name(superKeys, table_constraint.column_name(), colnames);
                }
            }

            //数据解析完成,数据合法,开始执行
            //新建meta
            RelationMeta meta = new RelationMeta();
            meta.ncols = ncols;
            meta.colnames = colnames;
            meta.coltypes = coltypes;
            meta.colsizes = colsizes;
            meta.nullableColIds = nullableColIds;
            meta.superKeys = superKeys;
            meta.indices = indices;

            Relation relation = new Relation();
            relation.meta = meta;
            currentDB.addRelation(table_name, relation);
            return ResultTable.getSimpleMessageTable("Create table succeed.");
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitDelete_table(minisqlParser.Delete_tableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitShow_table(minisqlParser.Show_tableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitDrop_table(minisqlParser.Drop_tableContext ctx) {
        try {
            String table_name = ctx.table_name().IDENTIFIER().getText();
            Path dir = Paths.get(currentDB.getDirectory(), table_name);
            if(!Files.isDirectory(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The table named %s does not exist!", table_name));
            }else{
                Misc.rmDir(dir);
                return ResultTable.getSimpleMessageTable(String.format("Table (%s) dropped.", table_name));
            }
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitCreate_db(minisqlParser.Create_dbContext ctx) {
        try {
            Path dir = Paths.get(dataDir.toString(), ctx.IDENTIFIER().getText());
            if(Files.exists(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The database named %s already exists!", ctx.IDENTIFIER().getText()));
            }
            Files.createDirectory(dir);
            return ResultTable.getSimpleMessageTable(String.format("The database named %s is created successfully!", ctx.IDENTIFIER().getText()));
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitDrop_db(minisqlParser.Drop_dbContext ctx) {
        try {
            Path dir = Paths.get(dataDir.toString(), ctx.IDENTIFIER().getText());
            if(dir.toString().equals(currentDB.getDirectory()))
            {
                return ResultTable.getSimpleMessageTable("Cannot delete the database in usage currently");
            }
            if(!Files.isDirectory(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The database named %s does not exist!", ctx.IDENTIFIER().getText()));
            }else{
                Misc.rmDir(dir);
                return ResultTable.getSimpleMessageTable(String.format("Database (%s) dropped.", ctx.IDENTIFIER().getText()));
            }
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitUse_db(minisqlParser.Use_dbContext ctx) {
        try {
            Path dir = Paths.get(dataDir.toString(), ctx.IDENTIFIER().getText());
            if(dir.toString().equals(currentDB.getDirectory()))
            {
                return ResultTable.getSimpleMessageTable(String.format("Already in database: %s!", ctx.IDENTIFIER().getText()));
            }
            if(!Files.isDirectory(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The database named %s does not exist!", ctx.IDENTIFIER().getText()));
            }else{
                currentDB.close();
                currentDB = new Database(dir.toString());
                currentDB.resume();
                return ResultTable.getSimpleMessageTable(String.format("Switched to database: %s", ctx.IDENTIFIER().getText()));
            }
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitShow_dbs(minisqlParser.Show_dbsContext ctx) {
        try{
            return ResultTable.getSimpleTable("dbNames",
                    Files.list(dataDir)
                    .filter(x -> Files.isDirectory(x))
                    .map(x -> x.getFileName().toString())
                    .collect(Collectors.toCollection(ArrayList::new))
            );
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitShow_db(minisqlParser.Show_dbContext ctx) {
        try{
            Path dir = Paths.get(dataDir.toString(), ctx.IDENTIFIER().getText());
            if(!Files.isDirectory(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The database named %s does not exist!", ctx.IDENTIFIER().getText()));
            }else{
                return ResultTable.getSimpleTable("tableName",
                        Files.list(dir)
                                .filter(x -> Files.isDirectory(x))
                                .map(x -> x.getFileName().toString())
                                .collect(Collectors.toCollection(ArrayList::new))
                );
            }
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitExit(minisqlParser.ExitContext ctx) {
        try{
            currentDB.close();
            closed = true;
            return ResultTable.getSimpleMessageTable("Bye Bye");
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }


    @Override
    public ResultTable visitUpdate_table(minisqlParser.Update_tableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitInsert_stmt(minisqlParser.Insert_stmtContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitRow(minisqlParser.RowContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitSelect_stmt(minisqlParser.Select_stmtContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitColumn_def(minisqlParser.Column_defContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitType_name(minisqlParser.Type_nameContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitTable_constraint(minisqlParser.Table_constraintContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitExpr(minisqlParser.ExprContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitResult_column(minisqlParser.Result_columnContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitTable(minisqlParser.TableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitJoin_clause(minisqlParser.Join_clauseContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitJoin_operator(minisqlParser.Join_operatorContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitJoin_constraint(minisqlParser.Join_constraintContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitLiteral_value(minisqlParser.Literal_valueContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitUnary_operator(minisqlParser.Unary_operatorContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitTable_name(minisqlParser.Table_nameContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitColumn_name(minisqlParser.Column_nameContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }
}
