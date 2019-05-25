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
import org.minidb.utils.Misc;
import org.minidb.relation.*;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
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
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitDelete_table(minisqlParser.Delete_tableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitShow_table(minisqlParser.Show_tableContext ctx) {
        try {
            String table_name = ctx.IDENTIFIER().getText();
            Path dir = Paths.get(currentDB.getDirectory(), table_name);
            if(!Files.isDirectory(dir)){
                return  ResultTable.getSimpleMessageTable(String.format("The table named %s does not exist!", table_name));
            }
            else {
                Relation rel = currentDB.getRelation(table_name);
                StringBuilder output_ctx = new StringBuilder();
                int column_number = rel.meta.ncols;
                String table_columns = "column nummber:" + column_number + "\n";
                output_ctx.append(table_columns);
                for(int i = 0;i < column_number;i++){
                    output_ctx.append((i+1) + ". ");
                    String column_name = "column name:" + rel.meta.colnames.get(i) + ", ";
                    output_ctx.append(column_name);
                    String column_type = "column type:" + rel.meta.coltypes.get(i).toString() + ", ";
                    output_ctx.append(column_type);
                    String column_nullable = "";
                    if(rel.meta.nullableColIds.contains(i)){
                        column_nullable = "nullable";
                    }
                    else{
                        column_nullable = "not nullable";
                    }
                    output_ctx.append(column_nullable + ";\n");
                }
                int size = rel.meta.superKeys.size();
                for(int i = 0;i <size;i++){
                    output_ctx.append("unique columns " + (i+1) + ": ");
                    ArrayList<Integer> superkey = rel.meta.superKeys.get(i);
                    int sizesuper = superkey.size();
                    for(int j = 0;j < sizesuper;j++){
                        output_ctx.append(rel.meta.colnames.get(superkey.get(j)) + " ");
                    }
                    output_ctx.append(";\n");
                }
                return ResultTable.getSimpleMessageTable(output_ctx.toString());
            }
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
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
    public ResultTable visitInsert_table(minisqlParser.Insert_tableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitSelect_table(minisqlParser.Select_tableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitLogical_expr(minisqlParser.Logical_exprContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitValue_expr(minisqlParser.Value_exprContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitUpdate_table(minisqlParser.Update_tableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }

    @Override
    public ResultTable visitRow(minisqlParser.RowContext ctx) {
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
    public ResultTable visitResult_column(minisqlParser.Result_columnContext ctx) {
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
