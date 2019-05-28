package org.minidb.server;

import com.sun.org.apache.xpath.internal.FoundIndex;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.minidb.bptree.BPlusTree;
import org.minidb.bptree.Configuration;
import org.minidb.bptree.MainDataFile;
import org.minidb.database.Database;
import org.minidb.exception.MiniDBException;
import org.minidb.grammar.*;
import org.minidb.relation.Relation;
import org.minidb.relation.RelationMeta;
import org.minidb.utils.Misc;
import sun.management.counter.Counter;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        ArrayList<Integer> int_source = source.stream().map(x -> colnames.indexOf(x.IDENTIFIER().getText().toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
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
            String table_name = ctx.table_name().IDENTIFIER().getText().toLowerCase();
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
                colnames.add(column.column_name().IDENTIFIER().getText().toLowerCase());

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
        int colID = table.meta.colnames.indexOf(ctx.value_expr(0).column_name().IDENTIFIER().getText().toLowerCase());
        if(colID == -1) return null;
        // check the value
        parseLiteral(ctx.value_expr(1).literal_value(), table.meta.coltypes.get(colID), table.meta.colsizes.get(colID));
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

    private static class TableIDAndColID
    {
        public ArrayList<Relation> tables;
        HashMap<String, Integer> counter;
        ArrayList<String> tableNames;
        HashMap<String, Pair<Integer, Integer>> uniqueColNameToID;
        HashSet<String> uniqueNames;

        public TableIDAndColID(ArrayList<Relation> tables) {
            this.tables = tables;
            // only colnames that appear only once can be directly referenced
            counter = new HashMap<>();
            tableNames = tables
                    .stream()
                    .map(x -> new File(x.directory).getName())
                    .collect(Collectors.toCollection(ArrayList::new));
            // map unique names to (tableID, colID)
            uniqueColNameToID = new HashMap<>();
            for (int i = 0; i < tables.size(); i++) {
                Relation table = tables.get(i);
                for (int j = 0; j < table.meta.colnames.size(); j++) {
                    String colName = table.meta.colnames.get(j);
                    counter.put(colName, counter.getOrDefault(colName, 0) + 1);
                    uniqueColNameToID.put(colName, new Pair<>(i, j));
                }
            }
            uniqueNames = counter
                    .entrySet()
                    .stream()
                    .filter(x -> x.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toCollection(HashSet::new));
            Set<String> names = new HashSet<>(uniqueColNameToID.keySet());
            for(String name : names)
            {
                if(!uniqueNames.contains(name))
                {
                    uniqueColNameToID.remove(name);
                }
            }
        }

        public void adjustByJoinCondition(minisqlParser.Select_tableContext ctx)
        {
            if(ctx.join_operator() == null)
                return;
            if(ctx.join_operator().K_CARTESIAN() != null)
                return;
            if(ctx.join_operator().K_NATURAL() != null)
            {
                String tableName = ctx.table_name(1).IDENTIFIER().getText().toLowerCase();
                int tableID = tableNames.indexOf(tableName);
                for(Map.Entry<String, Integer> entry : counter.entrySet())
                {
                    if(entry.getValue().equals(2))
                    {// a common name
                        int colID = tables.get(tableID).meta.colnames.indexOf(entry.getKey());
                        uniqueColNameToID.put(entry.getKey(), new Pair<>(tableID, colID));
                        uniqueNames.add(entry.getKey());
                    }
                }
                return;
            }
            if(ctx.join_operator().K_NATURAL() == null && ctx.join_constraint().K_USING() != null)
            {
                ArrayList<String> names = ctx
                                .join_constraint()
                                .column_name()
                                .stream()
                                .map(x -> x.IDENTIFIER().getText().toLowerCase())
                                .collect(Collectors.toCollection(ArrayList::new));
                String tableName = ctx.table_name(1).IDENTIFIER().getText().toLowerCase();
                int tableID = tableNames.indexOf(tableName);
                for(String colName : names)
                {
                    int colID = tables.get(tableID).meta.colnames.indexOf(colName);
                    uniqueColNameToID.put(colName, new Pair<>(tableID, colID));
                    uniqueNames.add(colName);
                }
                return;
            }
        }

        public Pair<Integer, Integer> getTableIDAndColID(String tableName, String colName)
        {
            if(tableName == null)
            {
                assert uniqueNames.contains(colName);
                return uniqueColNameToID.get(colName);
            }
            int tableID = tableNames.indexOf(tableName);
            if(tableID == -1)
                throw new ParseCancellationException(String.format("Table %s not found in this query.", tableName));
            if(!counter.containsKey(colName))
                throw new ParseCancellationException(String.format("Column %s not found in this query.", colName));
            int tableColID = tables.get(tableID).meta.colnames.indexOf(colName);
            return new Pair<>(tableID, tableColID);
        }
    }

    private static class Expression
    {
        enum ExpressionType{
            LEAF_EXPR,
            AND_EXPR,
            OR_EXPR,
            CONST_VALUE
        }
        enum Opetator{
            LT, LE, GT, GE, EQ, NEQ
        }
        ExpressionType expressionType;
        Opetator op;
        Expression left, right;
        boolean hasLiteral;
        Object literal;
        int leftTableID, leftTableColID, rightTableID, rightTableColID;
        Type leftColType, rightColType;
        boolean constant;

        public Expression(minisqlParser.Logical_exprContext ctx, ArrayList<Relation> tables) {
            this(ctx, new TableIDAndColID(tables));
        }

        public Expression(minisqlParser.Logical_exprContext ctx, TableIDAndColID tableIDAndColID)
        {
            if(ctx.value_expr().size() != 0)
            {
                // leaf expression, a = b ; a.a = b.b; literal values are adjusted to the right side
                expressionType = ExpressionType.LEAF_EXPR;
                minisqlParser.Value_exprContext first = ctx.value_expr(0), second = ctx.value_expr(1), tmp = null;
                if(ctx.K_LT() != null) op = Opetator.LT;
                if(ctx.K_LE() != null) op = Opetator.LE;
                if(ctx.K_GT() != null) op = Opetator.GT;
                if(ctx.K_GE() != null) op = Opetator.GE;
                if(ctx.K_EQ() != null) op = Opetator.EQ;
                if(ctx.K_NEQ() != null) op = Opetator.NEQ;
                if(first.literal_value() != null && second.literal_value() != null)
                {
                    throw new ParseCancellationException("Not allowed for comparison with two literal values!");
                }
                if(first.literal_value() != null && second.literal_value() == null)
                {// switch 1=a to a=1
                    tmp = first; first = second; second = tmp;
                    switch (op) {
                        case LT:
                            op = Opetator.GT;
                            break;
                        case LE:
                            op = Opetator.GE;
                            break;
                        case GT:
                            op = Opetator.LT;
                            break;
                        case GE:
                            op = Opetator.LE;
                            break;
                        case EQ:
                            break;
                        case NEQ:
                            break;
                    }
                }
                Pair<Integer, Integer> tmpPair = tableIDAndColID.getTableIDAndColID(
                        first.table_name() != null ? first.table_name().IDENTIFIER().getText().toLowerCase() : null,
                        first.column_name().IDENTIFIER().getText().toLowerCase()
                );
                leftTableID = tmpPair.a;
                leftTableColID = tmpPair.b;
                leftColType = tableIDAndColID.tables.get(leftTableID).meta.coltypes.get(leftTableColID);

                if(second.literal_value() != null)
                {
                    hasLiteral = true;
                    literal = parseLiteral(second.literal_value(), leftColType,
                            tableIDAndColID.tables.get(leftTableID).meta.colsizes.get(leftTableColID));
                    if(literal == null)
                        throw new ParseCancellationException("Not supported for comparison with null!");
                }else{
                    tmpPair = tableIDAndColID.getTableIDAndColID(
                            second.table_name() != null ? second.table_name().IDENTIFIER().getText().toLowerCase() : null,
                            second.column_name().IDENTIFIER().getText().toLowerCase()
                    );
                    rightTableID = tmpPair.a;
                    rightTableColID = tmpPair.b;
                    rightColType = tableIDAndColID.tables.get(rightTableID).meta.coltypes.get(rightTableColID);
                    if((leftColType == String.class && rightColType == String.class) || (leftColType != String.class && rightColType != String.class))
                    {
                    }else {
                        throw new ParseCancellationException("Cannot compare string with non-string!");
                    }
                }
            }else {
                left = new Expression(ctx.logical_expr(0), tableIDAndColID);
                right = new Expression(ctx.logical_expr(1), tableIDAndColID);
                if(ctx.K_AND() != null)
                    expressionType = ExpressionType.AND_EXPR;
                if(ctx.K_OR() != null)
                    expressionType = ExpressionType.OR_EXPR;
            }
        }

        public Expression() {
        }

        public Expression(boolean constant) {
            expressionType = ExpressionType.CONST_VALUE;
            this.constant = constant;
        }

        public static Expression And(Expression a, Expression b)
        {
            Expression ans = new Expression();
            ans.expressionType = ExpressionType.AND_EXPR;
            ans.left = a;
            ans.right = b;
            return ans;
        }

        public static Expression getEqualityExpression(Collection<String> names, ArrayList<Relation> tables)
        {// return expression with `a.a = b.a && a.c = b.c` used in natural join
            return names.stream().map(x -> {
                Expression ans = new Expression();
                ans.expressionType = ExpressionType.LEAF_EXPR;
                ans.op = Opetator.EQ;
                ans.hasLiteral = false;
                ans.leftTableID = 0;
                ans.rightTableID = 1;
                ans.leftTableColID = tables.get(0).meta.colnames.indexOf(x);
                ans.rightTableColID = tables.get(1).meta.colnames.indexOf(x);
                ans.leftColType = tables.get(ans.leftTableID).meta.coltypes.get(ans.leftTableColID);
                ans.rightColType = tables.get(ans.rightTableID).meta.coltypes.get(ans.rightTableColID);
                if((ans.leftColType == String.class && ans.rightColType == String.class)
                        || (ans.leftColType != String.class && ans.rightColType != String.class))
                {
                }else {
                    throw new ParseCancellationException("Cannot compare string with non-string!");
                }
                return ans;
            }).reduce(new Expression(true), Expression::And);
        }

        public boolean apply(ArrayList<MainDataFile.SearchResult> results)
        {
            switch (expressionType) {
                case LEAF_EXPR:
                    int ans = 0;
                    if(hasLiteral)
                    {
                        Object left = results.get(leftTableID).key.get(leftTableColID);
                        if(left == null || literal == null)
                        {
                            return false;
                        }
                        if(leftColType == Integer.class)
                        {
                            ans = Integer.compare((Integer)left, (Integer)literal);
                        }else if(leftColType == Long.class)
                        {
                            ans = Long.compare((Long)left, (Long)literal);
                        }else if(leftColType == Float.class)
                        {
                            ans = Float.compare((Float)left, (Float)literal);
                        }else if(leftColType == Double.class)
                        {
                            ans = Double.compare((Double)left, (Double)literal);
                        }else if(leftColType == String.class)
                        {
                            ans = ((String)left).compareTo((String)literal);
                        }else {ans = 0;}
                    }else {
                        Object left = results.get(leftTableID).key.get(leftTableColID),
                                right = results.get(rightTableID).key.get(rightTableColID);
                        if(left == null || right == null)
                            return false;
                        if(leftColType == String.class && rightColType == String.class)
                        {
                            ans = ((String)left).compareTo((String)right);
                        }else if(leftColType != String.class && rightColType != String.class)
                        {// number compare
                            BigDecimal firstVal = null, secondVal = null;
                            if(leftColType == Integer.class)
                            {
                                firstVal = new BigDecimal((Integer)left);
                            }else if(leftColType == Long.class)
                            {
                                firstVal = new BigDecimal((Long)left);
                            }else if(leftColType == Float.class)
                            {
                                firstVal = new BigDecimal((Float)left);
                            }else if(leftColType == Double.class)
                            {
                                firstVal = new BigDecimal((Double) left);
                            }

                            if(rightColType == Integer.class)
                            {
                                secondVal = new BigDecimal((Integer)right);
                            }else if(rightColType == Long.class)
                            {
                                secondVal = new BigDecimal((Long)right);
                            }else if(rightColType == Float.class)
                            {
                                secondVal = new BigDecimal((Float)right);
                            }else if(rightColType == Double.class)
                            {
                                secondVal = new BigDecimal((Double) right);
                            }else {ans = 0;}
                            ans = firstVal.compareTo(secondVal);
                        }
                    }
                    switch (op) {
                        case LT:
                            return ans < 0;
                        case LE:
                            return ans <= 0;
                        case GT:
                            return ans > 0;
                        case GE:
                            return ans >= 0;
                        case EQ:
                            return ans == 0;
                        case NEQ:
                            return ans != 0;
                    }
                case AND_EXPR:
                    return left.apply(results) && right.apply(results);
                case OR_EXPR:
                    return left.apply(results) || right.apply(results);
                case CONST_VALUE:
                    return constant;
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
                expression = new Expression(expr.logical_expr(1), new ArrayList<>(Arrays.asList(table)));
            }else{
                expression = new Expression(expr, new ArrayList<>(Arrays.asList(table)));
            }
        }
        // `indexableExpr`, `tree`, `expression`
        // if `indexableExpr == null`, linear scan and evaluate `expression`
        // else, use `tree` to search for `indexableExpr` and then evaluate the `expression`
        LinkedList<MainDataFile.SearchResult> ans = null;
        if(tree != null)
        {// use index
            String colName = indexableExpr.value_expr(0).column_name().IDENTIFIER().getText().toLowerCase();
            Integer colID = table.meta.colnames.indexOf(colName);
            Type colType = table.meta.coltypes.get(colID);
            Object value = parseLiteral(indexableExpr.value_expr(1).literal_value(), colType, table.meta.colsizes.get(colID));
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
            ans = table.searchRows(x -> expression.apply(new ArrayList<>(Arrays.asList(x))));
        }else {
            ans = ans.stream().filter(x -> expression.apply(new ArrayList<>(Arrays.asList(x))))
                    .collect(Collectors.toCollection(LinkedList::new));
        }
        return ans;
    }

    @Override
    public ResultTable visitDelete_table(minisqlParser.Delete_tableContext ctx) {
        try{
            String table_name = ctx.table_name().IDENTIFIER().getText().toLowerCase();
            if(currentDB.getRelation(table_name) == null)
                return ResultTable.getSimpleMessageTable(String.format("Delete table failed: table %s does not exists.", table_name));
            Relation table = currentDB.getRelation(table_name);
            minisqlParser.Logical_exprContext expr = ctx.logical_expr();
            if(expr == null)
            {
                long nrows = table.data.getElementCount();
                table.deleteAllData();
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
            String table_name = ctx.IDENTIFIER().getText().toLowerCase();
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
            String table_name = ctx.table_name().IDENTIFIER().getText().toLowerCase();
            currentDB.dropRelation(table_name);
            return ResultTable.getSimpleMessageTable(String.format("Table (%s) dropped.", table_name));
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitCreate_db(minisqlParser.Create_dbContext ctx) {
        try {
            Path dir = Paths.get(dataDir.toString(), ctx.IDENTIFIER().getText().toLowerCase());
            if(Files.exists(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The database named %s already exists!", ctx.IDENTIFIER().getText().toLowerCase()));
            }
            Files.createDirectory(dir);
            return ResultTable.getSimpleMessageTable(String.format("The database named %s is created successfully!", ctx.IDENTIFIER().getText().toLowerCase()));
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitDrop_db(minisqlParser.Drop_dbContext ctx) {
        try {
            Path dir = Paths.get(dataDir.toString(), ctx.IDENTIFIER().getText().toLowerCase());
            if(dir.toString().equals(currentDB.getDirectory()))
            {
                return ResultTable.getSimpleMessageTable("Cannot delete the database in usage currently");
            }
            if(!Files.isDirectory(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The database named %s does not exist!", ctx.IDENTIFIER().getText().toLowerCase()));
            }else{
                Misc.rmDir(dir);
                return ResultTable.getSimpleMessageTable(String.format("Database (%s) dropped.", ctx.IDENTIFIER().getText().toLowerCase()));
            }
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitUse_db(minisqlParser.Use_dbContext ctx) {
        try {
            Path dir = Paths.get(dataDir.toString(), ctx.IDENTIFIER().getText().toLowerCase());
            if(dir.toString().equals(currentDB.getDirectory()))
            {
                return ResultTable.getSimpleMessageTable(String.format("Already in database: %s!", ctx.IDENTIFIER().getText().toLowerCase()));
            }
            if(!Files.isDirectory(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The database named %s does not exist!", ctx.IDENTIFIER().getText().toLowerCase()));
            }else{
                currentDB.close();
                currentDB = new Database(dir.toString());
                currentDB.resume();
                return ResultTable.getSimpleMessageTable(String.format("Switched to database: %s", ctx.IDENTIFIER().getText().toLowerCase()));
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
            Path dir = Paths.get(dataDir.toString(), ctx.IDENTIFIER().getText().toLowerCase());
            if(!Files.isDirectory(dir))
            {
                return ResultTable.getSimpleMessageTable(String.format("The database named %s does not exist!", ctx.IDENTIFIER().getText().toLowerCase()));
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

    private static Object parseLiteral(minisqlParser.Literal_valueContext element, Type colType, int colSize)
    {
        if(element.K_NULL() != null)
        {
            return null;
        }
        String text = element.getText();
        if(element.STRING_LITERAL() != null)
        {
            text = text.substring(1, text.length() - 1);
            if(colType == String.class)
            {
                text = StringEscapeUtils.unescapeJava(text);
                return Configuration.padString(text, colSize);
            }
        }
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
            return Configuration.padString(text, colSize);
        }
        return null;
    }

    @Override
    public ResultTable visitInsert_table(minisqlParser.Insert_tableContext ctx) {
        try {
            String table_name = ctx.table_name().IDENTIFIER().getText().toLowerCase();
            Relation table = currentDB.getRelation(table_name);
            if(table == null) return ResultTable.getSimpleMessageTable(String.format("The table named %s does not exist!", table_name));
            Set<String> colNames = new HashSet<String>(ctx.column_name().stream().map(x -> x.IDENTIFIER().getText().toLowerCase()).collect(Collectors.toCollection(HashSet::new)));
            boolean isInOrder = colNames.size() == 0;
            if(colNames.size() != 0)
            {// insert with custom col name order
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
                    permute.add(table.meta.colnames.indexOf(col.IDENTIFIER().getText().toLowerCase()));
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
                    literal_row[i] = parseLiteral(row.get(i), table.meta.coltypes.get(i), table.meta.colsizes.get(i));
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
            if(ctx.join_operator() == null)
            {// simple selection for just one table
                String table_name = ctx.table_name(0).IDENTIFIER().getText().toLowerCase();
                if(currentDB.getRelation(table_name) == null)
                    return ResultTable.getSimpleMessageTable(String.format("Select table failed: table %s does not exists.", table_name));
                Relation table = currentDB.getRelation(table_name);
                minisqlParser.Logical_exprContext expr = ctx.logical_expr();
                LinkedList<MainDataFile.SearchResult> ans = query(expr, table);

                // select columns
                ArrayList<String> colNames;
                if(ctx.result_column().size() == 0)
                {// select *
                    colNames = table.meta.colnames;
                }else{
                    colNames = ctx.result_column()
                            .stream()
                            .map(x -> x.column_name().IDENTIFIER().getText().toLowerCase()).collect(Collectors.toCollection(ArrayList::new));
                }
                ArrayList<Integer> colIDs = colNames.stream().map(x -> table.meta.colnames.indexOf(x)).collect(Collectors.toCollection(ArrayList::new));
                if(colIDs.contains(-1))
                    return ResultTable.getSimpleMessageTable("Unknown column name(s) for selection!");
                ArrayList<ArrayList<Object>> data = ans.stream()
                        .map(x -> colIDs.stream().map(y -> x.key.get(y)).collect(Collectors.toCollection(ArrayList::new)))
                        .collect(Collectors.toCollection(ArrayList::new));
                return new ResultTable(colNames, data);
            }else {
                String table1_name = ctx.table_name(0).IDENTIFIER().getText().toLowerCase();
                Relation table1 = currentDB.getRelation(table1_name);
                if(table1 == null)
                    return ResultTable.getSimpleMessageTable(String.format("Select table failed: table %s does not exists.", table1_name));
                String table2_name = ctx.table_name(1).IDENTIFIER().getText().toLowerCase();
                Relation table2 = currentDB.getRelation(table2_name);
                if(table2 == null)
                    return ResultTable.getSimpleMessageTable(String.format("Select table failed: table %s does not exists.", table2_name));

                ArrayList<Relation> tables = new ArrayList<>(Arrays.asList(table1, table2));
                TableIDAndColID tableIDAndColID = new TableIDAndColID(tables);
                tableIDAndColID.adjustByJoinCondition(ctx);
                ArrayList<Pair<MainDataFile.SearchResult, MainDataFile.SearchResult>> joined_results = new ArrayList<>();
                Expression expression;

                if(ctx.join_operator().K_CARTESIAN() != null)
                {// cartesian product
                    assert ctx.join_constraint() == null : "Select table failed: Cannot use join constraint in cartesian product";
                    expression = new Expression(true);
                }else if(ctx.join_operator().K_NATURAL() != null ||
                        (ctx.join_operator().K_NATURAL() == null && ctx.join_constraint() != null && ctx.join_constraint().K_USING() != null))
                {// natural join or join using xxx
                    assert ctx.join_constraint() == null : "Select table failed: Cannot use join constraint in natural join";
                    HashSet<String> commonNames = new HashSet<>(table1.meta.colnames);
                    commonNames.retainAll(new HashSet<>(table2.meta.colnames));
                    assert commonNames.size() > 0 : String.format("No common column names for table %s and %s.", table1_name, table2_name);
                    HashSet<String> namesToUse;
                    if(ctx.join_operator().K_NATURAL() != null)
                    {//natural join
                        namesToUse = commonNames;
                    }else{
                        ArrayList<String> usingNames = ctx
                                .join_constraint()
                                .column_name()
                                .stream()
                                .map(x -> x.IDENTIFIER().getText().toLowerCase())
                                .collect(Collectors.toCollection(ArrayList::new));
                        for(String name : usingNames)
                        {
                            assert commonNames.contains(name) : String.format("Column (%s) is not a common name.", name);
                        }
                        namesToUse = new HashSet<>(usingNames);
                    }
                    // parse equality expressions
                    expression = Expression.getEqualityExpression(namesToUse, tables);
                }else{
                    // parse logical expression
                    assert ctx.join_operator().K_NATURAL() == null && ctx.join_constraint() != null && ctx.join_constraint().K_ON() != null
                            : "Illegal join constraint.";
                    expression = new Expression(ctx.join_constraint().logical_expr(), tableIDAndColID);
                }

                // expression && where logical expression
                Expression where_expression =
                        ctx.K_WHERE() != null ? new Expression(ctx.logical_expr(), tableIDAndColID) : new Expression(true);
                final Expression final_expression = Expression.And(expression, where_expression);

                table1.searchRows(result1 -> {
                    table2.searchRows(result2 -> {
                        if(final_expression.apply(new ArrayList<>(Arrays.asList(result1, result2))))
                        {
                            joined_results.add(new Pair<>(result1, result2));
                        }
                        return false;
                    });
                    return false;
                });

                // col select from joined_results
                ArrayList<Pair<Integer, Integer>> tabIDAndColIDPairs;
                ArrayList<String> colnames = new ArrayList<>();
                if(ctx.result_column().size() == 0)
                {// select *
                    ArrayList<Pair<Integer, Integer>> tmp = new ArrayList<>(); // variable in lambda should be final
                    for (int i = 0; i < table1.meta.ncols; i++) {
                        tmp.add(new Pair<>(0, i));
                        colnames.add(table1_name + "." + table1.meta.colnames.get(i));
                    }
                    for (int i = 0; i < table2.meta.ncols; i++) {
                        tmp.add(new Pair<>(1, i));
                        colnames.add(table2_name + "." + table2.meta.colnames.get(i));
                    }
                    tabIDAndColIDPairs = tmp;
                }else{
                    tabIDAndColIDPairs = ctx.result_column().stream().map(x -> {
                        return tableIDAndColID.getTableIDAndColID(
                                x.table_name() != null ? x.table_name().IDENTIFIER().getText().toLowerCase() : null,
                                x.column_name().IDENTIFIER().getText().toLowerCase());
                    }).collect(Collectors.toCollection(ArrayList::new));
                    colnames = ctx
                            .result_column()
                            .stream()
                            .map(
                                    x ->
                                            (x.table_name() != null ? x.table_name().IDENTIFIER().getText().toLowerCase() + "." : "")
                                                    + x.column_name().IDENTIFIER().getText().toLowerCase())
                            .collect(Collectors.toCollection(ArrayList::new));
                }
                ArrayList<ArrayList<Object>> values =
                    joined_results
                        .stream()
                        .map(
                            rowPair -> tabIDAndColIDPairs.stream().map(
                                    id_pair -> {
                                        Integer tabID = id_pair.a, colID = id_pair.b;
                                        return (tabID.equals(0)? rowPair.a : rowPair.b).key.get(colID);
                                    }
                            ).collect(Collectors.toCollection(ArrayList::new))
                        ).collect(Collectors.toCollection(ArrayList::new));
                return new ResultTable(colnames, values);
            }
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitUpdate_table(minisqlParser.Update_tableContext ctx) {
        try{
            String table_name = ctx.table_name().IDENTIFIER().getText().toLowerCase();
            if(currentDB.getRelation(table_name) == null)
                return ResultTable.getSimpleMessageTable(String.format("Update table failed: table %s does not exists.", table_name));
            Relation table = currentDB.getRelation(table_name);

            //对指定的列名查重, 如果非重复列名和语句中的列名个数不等, 则说明语句中有重复列名
            Set<String> colNames = new HashSet<String>(ctx.column_name().stream().map(x -> x.IDENTIFIER().getText().toLowerCase()).collect(Collectors.toCollection(HashSet::new)));
            if(colNames.size() != ctx.column_name().size())
            {
                return ResultTable.getSimpleMessageTable("Update failed: cannot update rows with duplicate names!");
            }

            ArrayList<Pair<Integer, Object>> colAndValues = new ArrayList<>();
            //解析&处理set子句
            for(int i = 0; i < ctx.column_name().size(); i++)
            {
                //对一行column_name - literal_value组合:
                minisqlParser.Column_nameContext column_name = ctx.column_name(i);
                minisqlParser.Literal_valueContext literal_value = ctx.literal_value(i);

                //根据column_name, 获取column的列序号
                int colID = table.meta.colnames.indexOf(column_name.IDENTIFIER().getText().toLowerCase());
                if(colID == -1)
                    return ResultTable.getSimpleMessageTable(String.format("Update failed: row %s not exist!", column_name.IDENTIFIER().getText().toLowerCase()));

                //根据literal_value, 获取要修改的内容
                Object new_value = parseLiteral(literal_value, table.meta.coltypes.get(colID), table.meta.colsizes.get(colID));
                colAndValues.add(new Pair<>(colID, new_value));
            }

            //1: 根据where子句, 从待更新的table中取出所有原数据列
            //2: 根据set子句, 把原数据列该改的部分改掉, 成为新数据列
            //3: 把原数据列从表中删掉,把新数据列插进去.
            //4: rollback, 如果第3步成功,那么update成功; 否则把新数据列删掉, 把原数据列插回去.
            //当前进展: 基本功能实现, 可以考虑做: rollback

            //解析where子句
            minisqlParser.Logical_exprContext expr = ctx.logical_expr();
            //没有where子句的话, expr == null, query将返回所有值.
            LinkedList<MainDataFile.SearchResult> ans = query(expr, table);
            if(ans.size() == 0)
            {
                //查询结果为空
                return ResultTable.getSimpleMessageTable("Update succeed: no row changed!");
            }
            ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
            for(MainDataFile.SearchResult ans_item : ans)
            {
                data.add(ans_item.key);
            }

            //删除表中ans对应的列
            table.delete(ans.stream().map(x -> x.rowID).collect(Collectors.toCollection(ArrayList::new)));

            // set new data
            for(Pair<Integer, Object> pair : colAndValues)
            {
                Integer colID = pair.a;
                Object new_value = pair.b;
                for(ArrayList<Object> data_item : data)
                {
                    data_item.set(colID, new_value);
                }
            }

            //把新data插回去
            for(ArrayList<Object> data_item : data)
            {
                table.insert(data_item);
            }

            return ResultTable.getSimpleMessageTable(String.format("%d row(s) updated.", ans.size()));
        }catch (Exception e){
            throw new ParseCancellationException(e);
        }
    }

    @Override
    public ResultTable visitComment(minisqlParser.CommentContext ctx) {
        return ResultTable.getSimpleMessageTable("");
    }
}
