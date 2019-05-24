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
	 * Visit a parse tree produced by {@link minisqlParser#insert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_stmt(minisqlParser.Insert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#select_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_stmt(minisqlParser.Select_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#select_core}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_core(minisqlParser.Select_coreContext ctx);
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
	 * Visit a parse tree produced by {@link minisqlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(minisqlParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#result_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResult_column(minisqlParser.Result_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(minisqlParser.TableContext ctx);
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
	 * Visit a parse tree produced by {@link minisqlParser#compound_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_operator(minisqlParser.Compound_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#literal_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral_value(minisqlParser.Literal_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisqlParser#unary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator(minisqlParser.Unary_operatorContext ctx);
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