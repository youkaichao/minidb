package org.minidb.server;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringEscapeUtils;
import org.minidb.bptree.BPlusTree;
import org.minidb.bptree.MainDataFile;
import org.minidb.database.Database;
import org.minidb.exception.MiniDBException;
import org.minidb.grammar.*;
import org.minidb.relation.Relation;
import org.minidb.relation.RelationMeta;
import org.minidb.utils.Misc;

import java.io.*;
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

    private BPlusTree selectIndex(minisqlParser.Logical_exprContext ctx, Relation table)
    {// index optimization can only be used for literal equality with index, such as col = 1
        boolean literalEq = ctx != null
                && ctx.value_expr() != null
                && ctx.K_EQ() != null
                && ctx.value_expr(1).literal_value() != null
                && ctx.value_expr(0).column_name() != null;
        if(!literalEq) return null;
        int colID = table.meta.colnames.indexOf(ctx.value_expr(0).column_name().IDENTIFIER().getText());
        if(colID == -1) return null;
        // check the value
        parseLiteral(ctx.value_expr(1).literal_value(), table.meta.coltypes.get(colID));
        // find the index to be used
        for(BPlusTree tree : table.superKeyTrees)
        {
            if(tree.conf.colIDs.get(0) == colID)
            {
                return tree;
            }
        }
        for(BPlusTree tree : table.indexTrees)
        {
            if(tree.conf.colIDs.get(0) == colID)
            {
                return tree;
            }
        }
        return null;
    }

    private class Expression
    {
        boolean isLeaf;
        boolean isAnd;
        boolean isOr;
        Expression left, right;
        int colID;
        Type colType;
        Object comparedValue;
        boolean lt, le, gt, ge, eq, neq;
        boolean isConstant, constant;
        HashSet<Integer> queriedCols;

        public Expression(minisqlParser.Logical_exprContext ctx, Relation table) {
            if(ctx.value_expr() != null)
            {
                isLeaf = true; isAnd = isOr = false; left = right = null;
                minisqlParser.Value_exprContext first = ctx.value_expr(0), second = ctx.value_expr(1), tmp = null;
                if(first.literal_value() != null && second.literal_value() != null)
                {
                    throw new ParseCancellationException("Not supported for comparison with two literal values!");
                }
                if(first.column_name() != null && second.column_name() != null)
                {
                    throw new ParseCancellationException("Not supported for comparison with two columns!");
                }
                if(first.table_name() != null || second.table_name() != null)
                {
                    throw new ParseCancellationException("Table name Not supported here!");
                }
                if(first.literal_value() != null && second.column_name() != null)
                {
                    tmp = first; first = second; second = tmp;
                }
                // now it is in the type of `col = literal`
                String colname = first.column_name().IDENTIFIER().getText();
                colID = table.meta.colnames.indexOf(colname);
                if(colID == -1)
                {
                    throw new ParseCancellationException(String.format("Column name (%s) not found!", colname));
                }
                colType = table.meta.coltypes.get(colID);
                comparedValue = parseLiteral(second.literal_value(), colType);
                if(comparedValue == null)
                    throw new ParseCancellationException("Not supported for comparison with null!");
                lt = le = gt = ge = eq = neq = false;
                if(ctx.K_LT() != null) lt = true;
                if(ctx.K_LE() != null) le = true;
                if(ctx.K_GT() != null) gt = true;
                if(ctx.K_GE() != null) ge = true;
                if(ctx.K_EQ() != null) eq = true;
                if(ctx.K_NEQ() != null) neq = true;
                queriedCols = new HashSet<>(Arrays.asList(colID));
            }else {
                left = new Expression(ctx.logical_expr(0), table);
                right = new Expression(ctx.logical_expr(1), table);
                isAnd = ctx.K_AND() != null;
                isOr = ctx.K_OR() != null;
                queriedCols = new HashSet<>(left.queriedCols);
                queriedCols.addAll(right.queriedCols);
            }
        }

        public Expression(boolean constant) {
            this.isConstant = true;
            this.constant = constant;
            isAnd = isOr = isLeaf = false;
            queriedCols = new HashSet<>();
        }

        public boolean apply(MainDataFile.SearchResult result)
        {
            if(isConstant)
                return constant;
            if(isAnd)
            {
                return left.apply(result) && right.apply(result);
            }
            if(isOr)
            {
                return left.apply(result) || right.apply(result);
            }
            if(isLeaf)
            {
                int ans;
                if(colType == Integer.class)
                {
                    ans = Integer.compare((Integer)result.key.get(colID), (Integer)comparedValue);
                }else if(colType == Long.class)
                {
                    ans = Long.compare((Long)result.key.get(colID), (Long)comparedValue);
                }else if(colType == Float.class)
                {
                    ans = Float.compare((Float)result.key.get(colID), (Float)comparedValue);
                }else if(colType == Double.class)
                {
                    ans = Double.compare((Double)result.key.get(colID), (Double)comparedValue);
                }else if(colType == String.class)
                {
                    ans = ((String)result.key.get(colID)).compareTo((String)comparedValue);
                }else {ans = 0;}
                if(lt) return ans < 0;
                if(le) return ans <= 0;
                if(gt) return ans > 0;
                if(ge) return ans >= 0;
                if(eq) return ans == 0;
                if(neq) return ans != 0;
                return false;
            }
            return false;
        }
    }

    // execute the query on the table
    private LinkedList<MainDataFile.SearchResult> query(minisqlParser.Logical_exprContext expr, Relation table) throws IOException {
        minisqlParser.Logical_exprContext indexableExpr = null;
        BPlusTree tree = null;
        Expression expression;
        if(expr == null)
        {// no where clause, select all data
            expression = new Expression(true);
        }else {
            tree = selectIndex(expr, table);
            if(tree !=null)
            {// only a `col = 1`
                indexableExpr = expr;
                expression = new Expression(true);
            }else if(expr.K_AND() != null && selectIndex(expr.logical_expr(0), table) != null)
            {// col=1 and blabla
                indexableExpr = expr.logical_expr(0);
                tree = selectIndex(expr.logical_expr(0), table);
                expression = new Expression(expr.logical_expr(1), table);
            }else{
                expression = new Expression(expr, table);
            }
        }
        HashSet<Integer> queriedNullableCols = (HashSet<Integer>)expression.queriedCols.clone();
        queriedNullableCols.retainAll(table.meta.nullableColIds);
        ArrayList<BPlusTree> queriedNullableColTree = table.nullTrees
                .stream()
                .filter(x -> queriedNullableCols.contains(x.conf.colIDs.get(0)))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<BPlusTree> notQueriedNullableColTree = table.nullTrees
                .stream()
                .filter(x -> !queriedNullableCols.contains(x.conf.colIDs.get(0)))
                .collect(Collectors.toCollection(ArrayList::new));
        // `indexableExpr`, `tree`, `expression`
        // if `indexableExpr == null`, linear scan and evaluate `expression`
        // else, use `tree` to search for `indexableExpr` and then evaluate the `expression`
        LinkedList<MainDataFile.SearchResult> ans = null;
        if(tree != null)
        {// use index
            String colName = indexableExpr.value_expr(0).column_name().IDENTIFIER().getText();
            Integer colID = table.meta.colnames.indexOf(colName);
            Type colType = table.meta.coltypes.get(colID);
            Object value = parseLiteral(indexableExpr.value_expr(1).literal_value(), colType);
            if(tree.conf.colIDs.size() == 1)
            {// exact equality comparison
                LinkedList<Long> findRowIDs = tree.search(new ArrayList<Object>(Arrays.asList(value)));
                ans = table.readRows(findRowIDs);
            }else{// partial search
                ArrayList<Object> minKey = new ArrayList<>(), maxKey = new ArrayList<>();
                minKey.add(value); maxKey.add(value);
                for (int i = 1; i < tree.conf.colIDs.size(); ++i)
                {
                    colType = tree.conf.types.get(i);
                    if(colType == Integer.class)
                    {
                        minKey.add(Integer.MIN_VALUE);
                        maxKey.add(Integer.MAX_VALUE);
                    }else if(colType == Long.class)
                    {
                        minKey.add(Long.MIN_VALUE);
                        maxKey.add(Long.MAX_VALUE);
                    }else if(colType == Float.class)
                    {
                        minKey.add(Float.MIN_VALUE);
                        maxKey.add(Float.MAX_VALUE);
                    }else if(colType == Double.class)
                    {
                        minKey.add(Double.MIN_VALUE);
                        maxKey.add(Double.MAX_VALUE);
                    }else if(colType == String.class)
                    {
                        // char is unicode, while byte is only a byte.
                        // empty string is the smallest
                        char[] tmp = new char[tree.conf.sizes.get(i) + 1];
                        Arrays.fill(tmp, Character.MAX_VALUE);
                        minKey.add("");
                        maxKey.add(new String(tmp));
                    }
                }
                LinkedList<Long> findRowIDs = tree.rangeSearch(minKey, maxKey, true, true);
                ans = table.readRows(findRowIDs);
            }
        }
        if(ans == null){
            ans = table.data.searchRows(x -> expression.apply(x));
        }else {
            ans = ans.stream().filter(expression::apply).collect(Collectors.toCollection(LinkedList::new));
        }
        // remove null rows in comparison
        for(BPlusTree nullTree : queriedNullableColTree)
        {
            LinkedList<MainDataFile.SearchResult> tmp = new LinkedList<>();
            for(MainDataFile.SearchResult each : ans)
            {
                if(nullTree.search(new ArrayList<Object>(Arrays.asList(each.rowID))).size() == 0)
                {
                    tmp.add(each);
                }
            }
            ans = tmp;
        }
        for(BPlusTree nullTree : notQueriedNullableColTree)
        {
            for(MainDataFile.SearchResult each : ans)
            {
                if(nullTree.search(new ArrayList<Object>(Arrays.asList(each.rowID))).size() != 0)
                {
                    each.key.set(nullTree.conf.colIDs.get(0), null);
                }
            }
        }
        return ans;
    }

    @Override
    public ResultTable visitDelete_table(minisqlParser.Delete_tableContext ctx) {
        try{
            String table_name = ctx.table_name().IDENTIFIER().getText();
            if(currentDB.getRelation(table_name) == null)
                return ResultTable.getSimpleMessageTable(String.format("Delete table failed: table %s does not exists.", table_name));
            Relation table = currentDB.getRelation(table_name);
            minisqlParser.Logical_exprContext expr = ctx.logical_expr();
            if(expr == null)
            {
                long nrows = table.data.getElementCount();
                currentDB.dropRelation(table_name);
                currentDB.addRelation(table_name, table);
                return ResultTable.getSimpleMessageTable(String.format("%d row(s) deleted.", nrows));
            }
            LinkedList<MainDataFile.SearchResult> ans = query(expr, table);
            table.delete(ans.stream().map(x -> x.rowID).collect(Collectors.toCollection(ArrayList::new)));
            return ResultTable.getSimpleMessageTable(String.format("%d row(s) deleted.", ans.size()));
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitShow_table(minisqlParser.Show_tableContext ctx) {
        try {
            String table_name = ctx.IDENTIFIER().getText();
            Relation rel = currentDB.getRelation(table_name);
            if(rel == null) return  ResultTable.getSimpleMessageTable(String.format("The table named %s does not exist!", table_name));
            StringBuilder output_ctx = new StringBuilder();
            int column_number = rel.meta.ncols;
            String table_columns = "column nummber:" + column_number + "\n";
            output_ctx.append(table_columns);
            for(int i = 0;i < column_number;i++){
                output_ctx.append((i+1) + ". ");
                String column_name = "column name:" + rel.meta.colnames.get(i) + ", ";
                output_ctx.append(column_name);
                String typeName = ""; Type type = rel.meta.coltypes.get(i);
                if(type == Integer.class)
                {
                    typeName = "INT";
                }else if(type == Long.class)
                {
                    typeName = "LONG";
                }else if(type == Float.class)
                {
                    typeName = "FLOAT";
                }else if(type == Double.class)
                {
                    typeName = "DOUBLE";
                }else if(type == String.class)
                {
                    typeName = String.format("STRING(%d)", rel.meta.colsizes.get(i));
                }
                String column_type = "column type:" + typeName + ", ";
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
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitDrop_table(minisqlParser.Drop_tableContext ctx) {
        try {
            String table_name = ctx.table_name().IDENTIFIER().getText();
            currentDB.dropRelation(table_name);
            return ResultTable.getSimpleMessageTable(String.format("Table (%s) dropped.", table_name));
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

    private Object parseLiteral(minisqlParser.Literal_valueContext element, Type colType)
    {
        if(element.K_NULL() != null)
        {
            return null;
        }
        String text = element.getText();
        if(colType == Integer.class)
        {
            return Integer.valueOf(text);
        }else if(colType == Long.class)
        {
            return Long.valueOf(text);
        }else if(colType == Float.class)
        {
            return Float.valueOf(text);
        }else if(colType == Double.class)
        {
            return Double.valueOf(text);
        }else if(colType == String.class)
        {
            assert text.length() >= 2 && text.startsWith("'") && text.endsWith("'") : String.format("Illegal string literal %s!", text);
            text = text.substring(1, text.length() - 1);
            text = StringEscapeUtils.unescapeJava(text);
            return text;
        }
        return null;
    }

    @Override
    public ResultTable visitInsert_table(minisqlParser.Insert_tableContext ctx) {
        try {
            String table_name = ctx.table_name().IDENTIFIER().getText();
            Relation table = currentDB.getRelation(table_name);
            if(table == null) return ResultTable.getSimpleMessageTable(String.format("The table named %s does not exist!", table_name));
            Set<String> colNames = new HashSet<String>(ctx.column_name().stream().map(x -> x.IDENTIFIER().getText()).collect(Collectors.toCollection(HashSet::new)));
            boolean isInOrder = colNames.size() == 0;
            if(colNames.size() != 0)
            {// insert with custon col name order
                if(colNames.size() != ctx.column_name().size())
                {
                    return ResultTable.getSimpleMessageTable("Duplicate column names for insertion!");
                }
                Set<String> missingNames = new HashSet<>(table.meta.colnames);
                missingNames.removeAll(colNames);
                if(missingNames.size() != 0)
                {
                    return ResultTable.getSimpleMessageTable(String.format("Missing column names (%s) for insertion!", missingNames.toString()));
                }
            }
            // colnames for insertion is legal
            // the ith element in values should be put in permute[i]
            ArrayList<Integer> permute = new ArrayList<>();
            if(!isInOrder)
            {
                for(minisqlParser.Column_nameContext col : ctx.column_name())
                {
                    permute.add(table.meta.colnames.indexOf(col.IDENTIFIER().getText()));
                }
            }
            for(minisqlParser.RowContext row : ctx.row())
            {
                if(row.literal_value().size() != table.meta.ncols)
                {
                    return ResultTable.getSimpleMessageTable(String.format("The row (%s) size mismatches!", row.getText()));
                }
            }
            ArrayList<ArrayList<minisqlParser.Literal_valueContext>> values = new ArrayList<>();
            for(minisqlParser.RowContext row : ctx.row())
            {
                values.add(new ArrayList<minisqlParser.Literal_valueContext>(row.literal_value()));
            }
            // permute
            if(!isInOrder)
            {
                for(int i = 0; i < values.size(); ++i)
                {
                    ArrayList<minisqlParser.Literal_valueContext> row = values.get(i);
                    minisqlParser.Literal_valueContext[] tmp = new minisqlParser.Literal_valueContext[row.size()];
                    for(int j = 0; j < row.size(); ++j)
                    {
                        tmp[permute.get(j)] = row.get(j);
                    }
                    values.set(i, new ArrayList<minisqlParser.Literal_valueContext>(Arrays.asList(tmp)));
                }
            }
            // parse value
            ArrayList<ArrayList<Object>> literal_values = new ArrayList<>();
            for(ArrayList<minisqlParser.Literal_valueContext> row : values)
            {
                Object[] literal_row = new Object[row.size()];
                for (int i = 0; i < row.size(); ++i)
                {
                    literal_row[i] = parseLiteral(row.get(i), table.meta.coltypes.get(i));
                }
                literal_values.add(new ArrayList<Object>(Arrays.asList(literal_row)));
            }
            for(ArrayList<Object> row : literal_values)
            {
                table.insert(row);
            }
            return ResultTable.getSimpleMessageTable(String.format("%d rows inserted!", literal_values.size()));
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitSelect_table(minisqlParser.Select_tableContext ctx) {
        try{
            if(ctx.join_clause() == null)
            {// simple selection for just one table
                String table_name = ctx.table_name().IDENTIFIER().getText();
                if(currentDB.getRelation(table_name) == null)
                    return ResultTable.getSimpleMessageTable(String.format("Select table failed: table %s does not exists.", table_name));
                Relation table = currentDB.getRelation(table_name);
                minisqlParser.Logical_exprContext expr = ctx.logical_expr();
                LinkedList<MainDataFile.SearchResult> ans = query(expr, table);
                // select columns
                ArrayList<String> colNames = ctx.result_column()
                        .stream()
                        .map(x -> x.column_name().IDENTIFIER().getText()).collect(Collectors.toCollection(ArrayList::new));
                ArrayList<Integer> colIDs = colNames.stream().map(x -> table.meta.colnames.indexOf(x)).collect(Collectors.toCollection(ArrayList::new));
                if(colIDs.contains(-1))
                    return ResultTable.getSimpleMessageTable("Unknown column name(s) for selection!");
                ArrayList<ArrayList<Object>> data = ans.stream()
                        .map(x -> colIDs.stream().map(y -> x.key.get(y)).collect(Collectors.toCollection(ArrayList::new)))
                        .collect(Collectors.toCollection(ArrayList::new));
                return new ResultTable(colNames, data);
            }else {
                // TODO JOIN SELECT
                return ResultTable.getSimpleMessageTable("Unsupported");
            }
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitUpdate_table(minisqlParser.Update_tableContext ctx) {
        // TODO
        return ResultTable.getSimpleMessageTable("Unsupported");
    }
}
