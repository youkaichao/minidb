// Generated from /Users/youkaichao/Courses/database/minidb/src/org/minidb/grammar/minisql.g4 by ANTLR 4.7.2
package org.minidb.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link minisqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface minisqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code create_table}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_table(minisqlParser.Create_tableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code insert_table}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_table(minisqlParser.Insert_tableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code delete_table}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_table(minisqlParser.Delete_tableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code drop_table}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_table(minisqlParser.Drop_tableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code update_table}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_table(minisqlParser.Update_tableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code select_table}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_table(minisqlParser.Select_tableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code show_table}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_table(minisqlParser.Show_tableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code create_db}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_db(minisqlParser.Create_dbContext ctx);
	/**
	 * Visit a parse tree produced by the {@code drop_db}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_db(minisqlParser.Drop_dbContext ctx);
	/**
	 * Visit a parse tree produced by the {@code use_db}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse_db(minisqlParser.Use_dbContext ctx);
	/**
	 * Visit a parse tree produced by the {@code show_dbs}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_dbs(minisqlParser.Show_dbsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code show_db}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_db(minisqlParser.Show_dbContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exit}
	 * labeled alternative in {@link minisqlParser#sql_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit(minisqlParser.ExitContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRow(minisqlParser.RowContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_def(minisqlParser.Column_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_name(minisqlParser.Type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#table_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_constraint(minisqlParser.Table_constraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#logical_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_expr(minisqlParser.Logical_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#value_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue_expr(minisqlParser.Value_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#result_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResult_column(minisqlParser.Result_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#join_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_clause(minisqlParser.Join_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#join_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_operator(minisqlParser.Join_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#join_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_constraint(minisqlParser.Join_constraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#literal_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral_value(minisqlParser.Literal_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#table_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_name(minisqlParser.Table_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name(minisqlParser.Column_nameContext ctx);
}