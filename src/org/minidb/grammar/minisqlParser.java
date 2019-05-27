// Generated from /Users/youkaichao/Courses/database/minidb/src/org/minidb/grammar/minisql.g4 by ANTLR 4.7.2
package org.minidb.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class minisqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, K_AND=9, 
		K_CREATE=10, K_DATABASE=11, K_DATABASES=12, K_DELETE=13, K_DISTINCT=14, 
		K_DROP=15, K_EXISTS=16, K_FROM=17, K_IF=18, K_IN=19, K_INSERT=20, K_INTO=21, 
		K_IS=22, K_ISNULL=23, K_JOIN=24, K_KEY=25, K_NATURAL=26, K_NO=27, K_NOT=28, 
		K_NOTNULL=29, K_NULL=30, K_ON=31, K_OR=32, K_PRIMARY=33, K_RECURSIVE=34, 
		K_SELECT=35, K_SET=36, K_TABLE=37, K_UNIQUE=38, K_UPDATE=39, K_USING=40, 
		K_VALUES=41, K_WHERE=42, K_WITH=43, K_INT=44, K_LONG=45, K_FLOAT=46, K_DOUBLE=47, 
		K_VARCHAR=48, K_USE=49, K_SHOW=50, K_EXIT=51, K_LT=52, K_LE=53, K_GT=54, 
		K_GE=55, K_EQ=56, K_NEQ=57, K_CARTESIAN=58, IDENTIFIER=59, NUMERIC_LITERAL=60, 
		STRING_LITERAL=61, SPACES=62;
	public static final int
		RULE_sql_stmt = 0, RULE_row = 1, RULE_column_def = 2, RULE_type_name = 3, 
		RULE_table_constraint = 4, RULE_logical_expr = 5, RULE_value_expr = 6, 
		RULE_result_column = 7, RULE_join_operator = 8, RULE_join_constraint = 9, 
		RULE_literal_value = 10, RULE_table_name = 11, RULE_column_name = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"sql_stmt", "row", "column_def", "type_name", "table_constraint", "logical_expr", 
			"value_expr", "result_column", "join_operator", "join_constraint", "literal_value", 
			"table_name", "column_name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "';'", "'*'", "'#'", "'.'", "'+'", "'-'", null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "'<'", "'<='", "'>'", "'>='", "'='", "'<>'", 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "K_AND", "K_CREATE", 
			"K_DATABASE", "K_DATABASES", "K_DELETE", "K_DISTINCT", "K_DROP", "K_EXISTS", 
			"K_FROM", "K_IF", "K_IN", "K_INSERT", "K_INTO", "K_IS", "K_ISNULL", "K_JOIN", 
			"K_KEY", "K_NATURAL", "K_NO", "K_NOT", "K_NOTNULL", "K_NULL", "K_ON", 
			"K_OR", "K_PRIMARY", "K_RECURSIVE", "K_SELECT", "K_SET", "K_TABLE", "K_UNIQUE", 
			"K_UPDATE", "K_USING", "K_VALUES", "K_WHERE", "K_WITH", "K_INT", "K_LONG", 
			"K_FLOAT", "K_DOUBLE", "K_VARCHAR", "K_USE", "K_SHOW", "K_EXIT", "K_LT", 
			"K_LE", "K_GT", "K_GE", "K_EQ", "K_NEQ", "K_CARTESIAN", "IDENTIFIER", 
			"NUMERIC_LITERAL", "STRING_LITERAL", "SPACES"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "minisql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public minisqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Sql_stmtContext extends ParserRuleContext {
		public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt; }
	 
		public Sql_stmtContext() { }
		public void copyFrom(Sql_stmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Show_dbsContext extends Sql_stmtContext {
		public TerminalNode K_SHOW() { return getToken(minisqlParser.K_SHOW, 0); }
		public TerminalNode K_DATABASES() { return getToken(minisqlParser.K_DATABASES, 0); }
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Show_dbsContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitShow_dbs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Drop_dbContext extends Sql_stmtContext {
		public TerminalNode K_DROP() { return getToken(minisqlParser.K_DROP, 0); }
		public TerminalNode K_DATABASE() { return getToken(minisqlParser.K_DATABASE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Drop_dbContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitDrop_db(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Create_dbContext extends Sql_stmtContext {
		public TerminalNode K_CREATE() { return getToken(minisqlParser.K_CREATE, 0); }
		public TerminalNode K_DATABASE() { return getToken(minisqlParser.K_DATABASE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Create_dbContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitCreate_db(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Insert_tableContext extends Sql_stmtContext {
		public TerminalNode K_INSERT() { return getToken(minisqlParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(minisqlParser.K_INTO, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public TerminalNode K_VALUES() { return getToken(minisqlParser.K_VALUES, 0); }
		public List<RowContext> row() {
			return getRuleContexts(RowContext.class);
		}
		public RowContext row(int i) {
			return getRuleContext(RowContext.class,i);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<TerminalNode> K_CARTESIAN() { return getTokens(minisqlParser.K_CARTESIAN); }
		public TerminalNode K_CARTESIAN(int i) {
			return getToken(minisqlParser.K_CARTESIAN, i);
		}
		public Insert_tableContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitInsert_table(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Drop_tableContext extends Sql_stmtContext {
		public TerminalNode K_DROP() { return getToken(minisqlParser.K_DROP, 0); }
		public TerminalNode K_TABLE() { return getToken(minisqlParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Drop_tableContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitDrop_table(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExitContext extends Sql_stmtContext {
		public TerminalNode K_EXIT() { return getToken(minisqlParser.K_EXIT, 0); }
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public ExitContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitExit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Show_tableContext extends Sql_stmtContext {
		public TerminalNode K_SHOW() { return getToken(minisqlParser.K_SHOW, 0); }
		public TerminalNode K_TABLE() { return getToken(minisqlParser.K_TABLE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Show_tableContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitShow_table(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Create_tableContext extends Sql_stmtContext {
		public TerminalNode K_CREATE() { return getToken(minisqlParser.K_CREATE, 0); }
		public TerminalNode K_TABLE() { return getToken(minisqlParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public List<TerminalNode> K_CARTESIAN() { return getTokens(minisqlParser.K_CARTESIAN); }
		public TerminalNode K_CARTESIAN(int i) {
			return getToken(minisqlParser.K_CARTESIAN, i);
		}
		public List<Table_constraintContext> table_constraint() {
			return getRuleContexts(Table_constraintContext.class);
		}
		public Table_constraintContext table_constraint(int i) {
			return getRuleContext(Table_constraintContext.class,i);
		}
		public Create_tableContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitCreate_table(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Show_dbContext extends Sql_stmtContext {
		public TerminalNode K_SHOW() { return getToken(minisqlParser.K_SHOW, 0); }
		public TerminalNode K_DATABASE() { return getToken(minisqlParser.K_DATABASE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Show_dbContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitShow_db(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CommentContext extends Sql_stmtContext {
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public CommentContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Select_tableContext extends Sql_stmtContext {
		public TerminalNode K_SELECT() { return getToken(minisqlParser.K_SELECT, 0); }
		public TerminalNode K_FROM() { return getToken(minisqlParser.K_FROM, 0); }
		public List<Table_nameContext> table_name() {
			return getRuleContexts(Table_nameContext.class);
		}
		public Table_nameContext table_name(int i) {
			return getRuleContext(Table_nameContext.class,i);
		}
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Join_operatorContext join_operator() {
			return getRuleContext(Join_operatorContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(minisqlParser.K_WHERE, 0); }
		public Logical_exprContext logical_expr() {
			return getRuleContext(Logical_exprContext.class,0);
		}
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public Join_constraintContext join_constraint() {
			return getRuleContext(Join_constraintContext.class,0);
		}
		public List<TerminalNode> K_CARTESIAN() { return getTokens(minisqlParser.K_CARTESIAN); }
		public TerminalNode K_CARTESIAN(int i) {
			return getToken(minisqlParser.K_CARTESIAN, i);
		}
		public Select_tableContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitSelect_table(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Use_dbContext extends Sql_stmtContext {
		public TerminalNode K_USE() { return getToken(minisqlParser.K_USE, 0); }
		public TerminalNode K_DATABASE() { return getToken(minisqlParser.K_DATABASE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Use_dbContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitUse_db(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Delete_tableContext extends Sql_stmtContext {
		public TerminalNode K_DELETE() { return getToken(minisqlParser.K_DELETE, 0); }
		public TerminalNode K_FROM() { return getToken(minisqlParser.K_FROM, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public TerminalNode K_WHERE() { return getToken(minisqlParser.K_WHERE, 0); }
		public Logical_exprContext logical_expr() {
			return getRuleContext(Logical_exprContext.class,0);
		}
		public Delete_tableContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitDelete_table(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Update_tableContext extends Sql_stmtContext {
		public TerminalNode K_UPDATE() { return getToken(minisqlParser.K_UPDATE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_SET() { return getToken(minisqlParser.K_SET, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<TerminalNode> K_EQ() { return getTokens(minisqlParser.K_EQ); }
		public TerminalNode K_EQ(int i) {
			return getToken(minisqlParser.K_EQ, i);
		}
		public List<Literal_valueContext> literal_value() {
			return getRuleContexts(Literal_valueContext.class);
		}
		public Literal_valueContext literal_value(int i) {
			return getRuleContext(Literal_valueContext.class,i);
		}
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public List<TerminalNode> K_CARTESIAN() { return getTokens(minisqlParser.K_CARTESIAN); }
		public TerminalNode K_CARTESIAN(int i) {
			return getToken(minisqlParser.K_CARTESIAN, i);
		}
		public TerminalNode K_WHERE() { return getToken(minisqlParser.K_WHERE, 0); }
		public Logical_exprContext logical_expr() {
			return getRuleContext(Logical_exprContext.class,0);
		}
		public Update_tableContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitUpdate_table(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_stmtContext sql_stmt() throws RecognitionException {
		Sql_stmtContext _localctx = new Sql_stmtContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sql_stmt);
		int _la;
		try {
			int _alt;
			setState(215);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new Create_tableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				match(K_CREATE);
				setState(27);
				match(K_TABLE);
				setState(28);
				table_name();
				setState(29);
				match(T__0);
				setState(30);
				column_def();
				setState(35);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(31);
						match(K_CARTESIAN);
						setState(32);
						column_def();
						}
						} 
					}
					setState(37);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(38);
					match(K_CARTESIAN);
					setState(39);
					table_constraint();
					}
					}
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(45);
				match(T__1);
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(46);
					match(T__2);
					}
				}

				setState(49);
				match(EOF);
				}
				break;
			case 2:
				_localctx = new Insert_tableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				match(K_INSERT);
				setState(52);
				match(K_INTO);
				setState(53);
				table_name();
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(54);
					match(T__0);
					setState(55);
					column_name();
					setState(60);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==K_CARTESIAN) {
						{
						{
						setState(56);
						match(K_CARTESIAN);
						setState(57);
						column_name();
						}
						}
						setState(62);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(63);
					match(T__1);
					}
				}

				{
				setState(67);
				match(K_VALUES);
				setState(68);
				row();
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(69);
					match(K_CARTESIAN);
					setState(70);
					row();
					}
					}
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(76);
					match(T__2);
					}
				}

				setState(79);
				match(EOF);
				}
				break;
			case 3:
				_localctx = new Delete_tableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(K_DELETE);
				setState(82);
				match(K_FROM);
				setState(83);
				table_name();
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(84);
					match(K_WHERE);
					setState(85);
					logical_expr(0);
					}
				}

				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(88);
					match(T__2);
					}
				}

				setState(91);
				match(EOF);
				}
				break;
			case 4:
				_localctx = new Drop_tableContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				match(K_DROP);
				setState(94);
				match(K_TABLE);
				setState(95);
				table_name();
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(96);
					match(T__2);
					}
				}

				setState(99);
				match(EOF);
				}
				break;
			case 5:
				_localctx = new Update_tableContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				match(K_UPDATE);
				setState(102);
				table_name();
				setState(103);
				match(K_SET);
				setState(104);
				column_name();
				setState(105);
				match(K_EQ);
				setState(106);
				literal_value();
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(107);
					match(K_CARTESIAN);
					setState(108);
					column_name();
					setState(109);
					match(K_EQ);
					setState(110);
					literal_value();
					}
					}
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(117);
					match(K_WHERE);
					setState(118);
					logical_expr(0);
					}
				}

				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(121);
					match(T__2);
					}
				}

				setState(124);
				match(EOF);
				}
				break;
			case 6:
				_localctx = new Select_tableContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(126);
				match(K_SELECT);
				setState(136);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
					{
					setState(127);
					match(T__3);
					}
					break;
				case IDENTIFIER:
					{
					{
					setState(128);
					result_column();
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==K_CARTESIAN) {
						{
						{
						setState(129);
						match(K_CARTESIAN);
						setState(130);
						result_column();
						}
						}
						setState(135);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(138);
				match(K_FROM);
				setState(139);
				table_name();
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_JOIN) | (1L << K_NATURAL) | (1L << K_CARTESIAN))) != 0)) {
					{
					setState(140);
					join_operator();
					setState(141);
					table_name();
					setState(143);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==K_ON || _la==K_USING) {
						{
						setState(142);
						join_constraint();
						}
					}

					}
				}

				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(147);
					match(K_WHERE);
					setState(148);
					logical_expr(0);
					}
				}

				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(151);
					match(T__2);
					}
				}

				setState(154);
				match(EOF);
				}
				break;
			case 7:
				_localctx = new Show_tableContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
				match(K_SHOW);
				setState(157);
				match(K_TABLE);
				setState(158);
				match(IDENTIFIER);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(159);
					match(T__2);
					}
				}

				setState(162);
				match(EOF);
				}
				break;
			case 8:
				_localctx = new Create_dbContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(163);
				match(K_CREATE);
				setState(164);
				match(K_DATABASE);
				setState(165);
				match(IDENTIFIER);
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(166);
					match(T__2);
					}
				}

				setState(169);
				match(EOF);
				}
				break;
			case 9:
				_localctx = new Drop_dbContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(170);
				match(K_DROP);
				setState(171);
				match(K_DATABASE);
				setState(172);
				match(IDENTIFIER);
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(173);
					match(T__2);
					}
				}

				setState(176);
				match(EOF);
				}
				break;
			case 10:
				_localctx = new Use_dbContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(177);
				match(K_USE);
				setState(178);
				match(K_DATABASE);
				setState(179);
				match(IDENTIFIER);
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(180);
					match(T__2);
					}
				}

				setState(183);
				match(EOF);
				}
				break;
			case 11:
				_localctx = new Show_dbsContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(184);
				match(K_SHOW);
				setState(185);
				match(K_DATABASES);
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(186);
					match(T__2);
					}
				}

				setState(189);
				match(EOF);
				}
				break;
			case 12:
				_localctx = new Show_dbContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(190);
				match(K_SHOW);
				setState(191);
				match(K_DATABASE);
				setState(192);
				match(IDENTIFIER);
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(193);
					match(T__2);
					}
				}

				setState(196);
				match(EOF);
				}
				break;
			case 13:
				_localctx = new ExitContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(197);
				match(K_EXIT);
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(198);
					match(T__2);
					}
				}

				setState(201);
				match(EOF);
				}
				break;
			case 14:
				_localctx = new CommentContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(202);
					match(T__4);
					setState(206);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1+1 ) {
							{
							{
							setState(203);
							matchWildcard();
							}
							} 
						}
						setState(208);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					}
				}

				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(211);
					match(T__2);
					}
				}

				setState(214);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RowContext extends ParserRuleContext {
		public List<Literal_valueContext> literal_value() {
			return getRuleContexts(Literal_valueContext.class);
		}
		public Literal_valueContext literal_value(int i) {
			return getRuleContext(Literal_valueContext.class,i);
		}
		public List<TerminalNode> K_CARTESIAN() { return getTokens(minisqlParser.K_CARTESIAN); }
		public TerminalNode K_CARTESIAN(int i) {
			return getToken(minisqlParser.K_CARTESIAN, i);
		}
		public RowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_row; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitRow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowContext row() throws RecognitionException {
		RowContext _localctx = new RowContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_row);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(T__0);
			setState(218);
			literal_value();
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_CARTESIAN) {
				{
				{
				setState(219);
				match(K_CARTESIAN);
				setState(220);
				literal_value();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_defContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Type_nameContext type_name() {
			return getRuleContext(Type_nameContext.class,0);
		}
		public TerminalNode K_PRIMARY() { return getToken(minisqlParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(minisqlParser.K_KEY, 0); }
		public TerminalNode K_NOT() { return getToken(minisqlParser.K_NOT, 0); }
		public TerminalNode K_NULL() { return getToken(minisqlParser.K_NULL, 0); }
		public TerminalNode K_UNIQUE() { return getToken(minisqlParser.K_UNIQUE, 0); }
		public Column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitColumn_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_defContext column_def() throws RecognitionException {
		Column_defContext _localctx = new Column_defContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_column_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			column_name();
			setState(229);
			type_name();
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(230);
				match(K_PRIMARY);
				setState(231);
				match(K_KEY);
				}
				break;
			case K_NOT:
				{
				setState(232);
				match(K_NOT);
				setState(233);
				match(K_NULL);
				}
				break;
			case K_UNIQUE:
				{
				setState(234);
				match(K_UNIQUE);
				}
				break;
			case T__1:
			case K_CARTESIAN:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_nameContext extends ParserRuleContext {
		public TerminalNode K_INT() { return getToken(minisqlParser.K_INT, 0); }
		public TerminalNode K_LONG() { return getToken(minisqlParser.K_LONG, 0); }
		public TerminalNode K_FLOAT() { return getToken(minisqlParser.K_FLOAT, 0); }
		public TerminalNode K_DOUBLE() { return getToken(minisqlParser.K_DOUBLE, 0); }
		public TerminalNode K_VARCHAR() { return getToken(minisqlParser.K_VARCHAR, 0); }
		public TerminalNode NUMERIC_LITERAL() { return getToken(minisqlParser.NUMERIC_LITERAL, 0); }
		public Type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitType_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_nameContext type_name() throws RecognitionException {
		Type_nameContext _localctx = new Type_nameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_name);
		try {
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				match(K_INT);
				}
				break;
			case K_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(238);
				match(K_LONG);
				}
				break;
			case K_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(239);
				match(K_FLOAT);
				}
				break;
			case K_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(240);
				match(K_DOUBLE);
				}
				break;
			case K_VARCHAR:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(241);
				match(K_VARCHAR);
				setState(242);
				match(T__0);
				setState(243);
				match(NUMERIC_LITERAL);
				setState(244);
				match(T__1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_constraintContext extends ParserRuleContext {
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public TerminalNode K_PRIMARY() { return getToken(minisqlParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(minisqlParser.K_KEY, 0); }
		public TerminalNode K_UNIQUE() { return getToken(minisqlParser.K_UNIQUE, 0); }
		public List<TerminalNode> K_CARTESIAN() { return getTokens(minisqlParser.K_CARTESIAN); }
		public TerminalNode K_CARTESIAN(int i) {
			return getToken(minisqlParser.K_CARTESIAN, i);
		}
		public Table_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_constraint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitTable_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_constraintContext table_constraint() throws RecognitionException {
		Table_constraintContext _localctx = new Table_constraintContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_table_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(247);
				match(K_PRIMARY);
				setState(248);
				match(K_KEY);
				}
				break;
			case K_UNIQUE:
				{
				setState(249);
				match(K_UNIQUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(252);
			match(T__0);
			setState(253);
			column_name();
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_CARTESIAN) {
				{
				{
				setState(254);
				match(K_CARTESIAN);
				setState(255);
				column_name();
				}
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(261);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logical_exprContext extends ParserRuleContext {
		public List<Logical_exprContext> logical_expr() {
			return getRuleContexts(Logical_exprContext.class);
		}
		public Logical_exprContext logical_expr(int i) {
			return getRuleContext(Logical_exprContext.class,i);
		}
		public TerminalNode K_AND() { return getToken(minisqlParser.K_AND, 0); }
		public TerminalNode K_OR() { return getToken(minisqlParser.K_OR, 0); }
		public List<Value_exprContext> value_expr() {
			return getRuleContexts(Value_exprContext.class);
		}
		public Value_exprContext value_expr(int i) {
			return getRuleContext(Value_exprContext.class,i);
		}
		public TerminalNode K_LT() { return getToken(minisqlParser.K_LT, 0); }
		public TerminalNode K_LE() { return getToken(minisqlParser.K_LE, 0); }
		public TerminalNode K_GT() { return getToken(minisqlParser.K_GT, 0); }
		public TerminalNode K_GE() { return getToken(minisqlParser.K_GE, 0); }
		public TerminalNode K_EQ() { return getToken(minisqlParser.K_EQ, 0); }
		public TerminalNode K_NEQ() { return getToken(minisqlParser.K_NEQ, 0); }
		public Logical_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitLogical_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_exprContext logical_expr() throws RecognitionException {
		return logical_expr(0);
	}

	private Logical_exprContext logical_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Logical_exprContext _localctx = new Logical_exprContext(_ctx, _parentState);
		Logical_exprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_logical_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(264);
				match(T__0);
				setState(265);
				logical_expr(0);
				setState(266);
				match(K_AND);
				setState(267);
				logical_expr(0);
				setState(268);
				match(T__1);
				}
				break;
			case 2:
				{
				setState(270);
				match(T__0);
				setState(271);
				logical_expr(0);
				setState(272);
				match(K_OR);
				setState(273);
				logical_expr(0);
				setState(274);
				match(T__1);
				}
				break;
			case 3:
				{
				setState(276);
				value_expr();
				setState(277);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_LT) | (1L << K_LE) | (1L << K_GT) | (1L << K_GE) | (1L << K_EQ) | (1L << K_NEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(278);
				value_expr();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(290);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(288);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
					case 1:
						{
						_localctx = new Logical_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expr);
						setState(282);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(283);
						match(K_AND);
						setState(284);
						logical_expr(6);
						}
						break;
					case 2:
						{
						_localctx = new Logical_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expr);
						setState(285);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(286);
						match(K_OR);
						setState(287);
						logical_expr(5);
						}
						break;
					}
					} 
				}
				setState(292);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Value_exprContext extends ParserRuleContext {
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Value_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitValue_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Value_exprContext value_expr() throws RecognitionException {
		Value_exprContext _localctx = new Value_exprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value_expr);
		try {
			setState(300);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__7:
			case K_NULL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				literal_value();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(297);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(294);
					table_name();
					setState(295);
					match(T__5);
					}
					break;
				}
				setState(299);
				column_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Result_columnContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Result_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result_column; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitResult_column(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Result_columnContext result_column() throws RecognitionException {
		Result_columnContext _localctx = new Result_columnContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_result_column);
		try {
			setState(307);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(302);
				column_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				table_name();
				setState(304);
				match(T__5);
				setState(305);
				column_name();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_operatorContext extends ParserRuleContext {
		public TerminalNode K_CARTESIAN() { return getToken(minisqlParser.K_CARTESIAN, 0); }
		public TerminalNode K_JOIN() { return getToken(minisqlParser.K_JOIN, 0); }
		public TerminalNode K_NATURAL() { return getToken(minisqlParser.K_NATURAL, 0); }
		public Join_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitJoin_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_operatorContext join_operator() throws RecognitionException {
		Join_operatorContext _localctx = new Join_operatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_join_operator);
		int _la;
		try {
			setState(314);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_CARTESIAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				match(K_CARTESIAN);
				}
				break;
			case K_JOIN:
			case K_NATURAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(310);
					match(K_NATURAL);
					}
				}

				setState(313);
				match(K_JOIN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_constraintContext extends ParserRuleContext {
		public TerminalNode K_ON() { return getToken(minisqlParser.K_ON, 0); }
		public Logical_exprContext logical_expr() {
			return getRuleContext(Logical_exprContext.class,0);
		}
		public TerminalNode K_USING() { return getToken(minisqlParser.K_USING, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<TerminalNode> K_CARTESIAN() { return getTokens(minisqlParser.K_CARTESIAN); }
		public TerminalNode K_CARTESIAN(int i) {
			return getToken(minisqlParser.K_CARTESIAN, i);
		}
		public Join_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_constraint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitJoin_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_constraintContext join_constraint() throws RecognitionException {
		Join_constraintContext _localctx = new Join_constraintContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_join_constraint);
		int _la;
		try {
			setState(330);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_ON:
				enterOuterAlt(_localctx, 1);
				{
				setState(316);
				match(K_ON);
				setState(317);
				logical_expr(0);
				}
				break;
			case K_USING:
				enterOuterAlt(_localctx, 2);
				{
				setState(318);
				match(K_USING);
				setState(319);
				match(T__0);
				setState(320);
				column_name();
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(321);
					match(K_CARTESIAN);
					setState(322);
					column_name();
					}
					}
					setState(327);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(328);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(minisqlParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(minisqlParser.STRING_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(minisqlParser.K_NULL, 0); }
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitLiteral_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_literal_value);
		int _la;
		try {
			setState(338);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__7:
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__6 || _la==T__7) {
					{
					setState(332);
					_la = _input.LA(1);
					if ( !(_la==T__6 || _la==T__7) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(335);
				match(NUMERIC_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(336);
				match(STRING_LITERAL);
				}
				break;
			case K_NULL:
				enterOuterAlt(_localctx, 3);
				{
				setState(337);
				match(K_NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitTable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitColumn_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return logical_expr_sempred((Logical_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean logical_expr_sempred(Logical_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u015b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2$\n\2\f\2"+
		"\16\2\'\13\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\3\2\5\2\62\n\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2=\n\2\f\2\16\2@\13\2\3\2\3\2\5\2D\n"+
		"\2\3\2\3\2\3\2\3\2\7\2J\n\2\f\2\16\2M\13\2\3\2\5\2P\n\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\5\2Y\n\2\3\2\5\2\\\n\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2d\n\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2s\n\2\f\2\16\2"+
		"v\13\2\3\2\3\2\5\2z\n\2\3\2\5\2}\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\u0086"+
		"\n\2\f\2\16\2\u0089\13\2\5\2\u008b\n\2\3\2\3\2\3\2\3\2\3\2\5\2\u0092\n"+
		"\2\5\2\u0094\n\2\3\2\3\2\5\2\u0098\n\2\3\2\5\2\u009b\n\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\5\2\u00a3\n\2\3\2\3\2\3\2\3\2\3\2\5\2\u00aa\n\2\3\2\3\2\3\2"+
		"\3\2\3\2\5\2\u00b1\n\2\3\2\3\2\3\2\3\2\3\2\5\2\u00b8\n\2\3\2\3\2\3\2\3"+
		"\2\5\2\u00be\n\2\3\2\3\2\3\2\3\2\3\2\5\2\u00c5\n\2\3\2\3\2\3\2\5\2\u00ca"+
		"\n\2\3\2\3\2\3\2\7\2\u00cf\n\2\f\2\16\2\u00d2\13\2\5\2\u00d4\n\2\3\2\5"+
		"\2\u00d7\n\2\3\2\5\2\u00da\n\2\3\3\3\3\3\3\3\3\7\3\u00e0\n\3\f\3\16\3"+
		"\u00e3\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u00ee\n\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00f8\n\5\3\6\3\6\3\6\5\6\u00fd\n\6\3\6\3"+
		"\6\3\6\3\6\7\6\u0103\n\6\f\6\16\6\u0106\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u011b\n\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\7\7\u0123\n\7\f\7\16\7\u0126\13\7\3\b\3\b\3\b\3\b\5"+
		"\b\u012c\n\b\3\b\5\b\u012f\n\b\3\t\3\t\3\t\3\t\3\t\5\t\u0136\n\t\3\n\3"+
		"\n\5\n\u013a\n\n\3\n\5\n\u013d\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13\u0146\n\13\f\13\16\13\u0149\13\13\3\13\3\13\5\13\u014d\n\13\3\f"+
		"\5\f\u0150\n\f\3\f\3\f\3\f\5\f\u0155\n\f\3\r\3\r\3\16\3\16\3\16\3\u00d0"+
		"\3\f\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\4\3\2\66;\3\2\t\n\2\u018f\2"+
		"\u00d9\3\2\2\2\4\u00db\3\2\2\2\6\u00e6\3\2\2\2\b\u00f7\3\2\2\2\n\u00fc"+
		"\3\2\2\2\f\u011a\3\2\2\2\16\u012e\3\2\2\2\20\u0135\3\2\2\2\22\u013c\3"+
		"\2\2\2\24\u014c\3\2\2\2\26\u0154\3\2\2\2\30\u0156\3\2\2\2\32\u0158\3\2"+
		"\2\2\34\35\7\f\2\2\35\36\7\'\2\2\36\37\5\30\r\2\37 \7\3\2\2 %\5\6\4\2"+
		"!\"\7<\2\2\"$\5\6\4\2#!\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&,\3\2\2"+
		"\2\'%\3\2\2\2()\7<\2\2)+\5\n\6\2*(\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2"+
		"\2-/\3\2\2\2.,\3\2\2\2/\61\7\4\2\2\60\62\7\5\2\2\61\60\3\2\2\2\61\62\3"+
		"\2\2\2\62\63\3\2\2\2\63\64\7\2\2\3\64\u00da\3\2\2\2\65\66\7\26\2\2\66"+
		"\67\7\27\2\2\67C\5\30\r\289\7\3\2\29>\5\32\16\2:;\7<\2\2;=\5\32\16\2<"+
		":\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\7\4\2\2"+
		"BD\3\2\2\2C8\3\2\2\2CD\3\2\2\2DE\3\2\2\2EF\7+\2\2FK\5\4\3\2GH\7<\2\2H"+
		"J\5\4\3\2IG\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LO\3\2\2\2MK\3\2\2\2"+
		"NP\7\5\2\2ON\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\7\2\2\3R\u00da\3\2\2\2ST\7"+
		"\17\2\2TU\7\23\2\2UX\5\30\r\2VW\7,\2\2WY\5\f\7\2XV\3\2\2\2XY\3\2\2\2Y"+
		"[\3\2\2\2Z\\\7\5\2\2[Z\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]^\7\2\2\3^\u00da"+
		"\3\2\2\2_`\7\21\2\2`a\7\'\2\2ac\5\30\r\2bd\7\5\2\2cb\3\2\2\2cd\3\2\2\2"+
		"de\3\2\2\2ef\7\2\2\3f\u00da\3\2\2\2gh\7)\2\2hi\5\30\r\2ij\7&\2\2jk\5\32"+
		"\16\2kl\7:\2\2lt\5\26\f\2mn\7<\2\2no\5\32\16\2op\7:\2\2pq\5\26\f\2qs\3"+
		"\2\2\2rm\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uy\3\2\2\2vt\3\2\2\2wx\7"+
		",\2\2xz\5\f\7\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{}\7\5\2\2|{\3\2\2\2|}\3"+
		"\2\2\2}~\3\2\2\2~\177\7\2\2\3\177\u00da\3\2\2\2\u0080\u008a\7%\2\2\u0081"+
		"\u008b\7\6\2\2\u0082\u0087\5\20\t\2\u0083\u0084\7<\2\2\u0084\u0086\5\20"+
		"\t\2\u0085\u0083\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u0081\3\2"+
		"\2\2\u008a\u0082\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\7\23\2\2\u008d"+
		"\u0093\5\30\r\2\u008e\u008f\5\22\n\2\u008f\u0091\5\30\r\2\u0090\u0092"+
		"\5\24\13\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2"+
		"\u0093\u008e\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0096"+
		"\7,\2\2\u0096\u0098\5\f\7\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u009a\3\2\2\2\u0099\u009b\7\5\2\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009c\3\2\2\2\u009c\u009d\7\2\2\3\u009d\u00da\3\2\2\2\u009e"+
		"\u009f\7\64\2\2\u009f\u00a0\7\'\2\2\u00a0\u00a2\7=\2\2\u00a1\u00a3\7\5"+
		"\2\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00da\7\2\2\3\u00a5\u00a6\7\f\2\2\u00a6\u00a7\7\r\2\2\u00a7\u00a9\7="+
		"\2\2\u00a8\u00aa\7\5\2\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ab\3\2\2\2\u00ab\u00da\7\2\2\3\u00ac\u00ad\7\21\2\2\u00ad\u00ae\7"+
		"\r\2\2\u00ae\u00b0\7=\2\2\u00af\u00b1\7\5\2\2\u00b0\u00af\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00da\7\2\2\3\u00b3\u00b4\7\63"+
		"\2\2\u00b4\u00b5\7\r\2\2\u00b5\u00b7\7=\2\2\u00b6\u00b8\7\5\2\2\u00b7"+
		"\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00da\7\2"+
		"\2\3\u00ba\u00bb\7\64\2\2\u00bb\u00bd\7\16\2\2\u00bc\u00be\7\5\2\2\u00bd"+
		"\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00da\7\2"+
		"\2\3\u00c0\u00c1\7\64\2\2\u00c1\u00c2\7\r\2\2\u00c2\u00c4\7=\2\2\u00c3"+
		"\u00c5\7\5\2\2\u00c4\u00c3\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2"+
		"\2\2\u00c6\u00da\7\2\2\3\u00c7\u00c9\7\65\2\2\u00c8\u00ca\7\5\2\2\u00c9"+
		"\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00da\7\2"+
		"\2\3\u00cc\u00d0\7\7\2\2\u00cd\u00cf\13\2\2\2\u00ce\u00cd\3\2\2\2\u00cf"+
		"\u00d2\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d4\3\2"+
		"\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00cc\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d6\3\2\2\2\u00d5\u00d7\7\5\2\2\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\7\2\2\3\u00d9\34\3\2\2\2\u00d9\65"+
		"\3\2\2\2\u00d9S\3\2\2\2\u00d9_\3\2\2\2\u00d9g\3\2\2\2\u00d9\u0080\3\2"+
		"\2\2\u00d9\u009e\3\2\2\2\u00d9\u00a5\3\2\2\2\u00d9\u00ac\3\2\2\2\u00d9"+
		"\u00b3\3\2\2\2\u00d9\u00ba\3\2\2\2\u00d9\u00c0\3\2\2\2\u00d9\u00c7\3\2"+
		"\2\2\u00d9\u00d3\3\2\2\2\u00da\3\3\2\2\2\u00db\u00dc\7\3\2\2\u00dc\u00e1"+
		"\5\26\f\2\u00dd\u00de\7<\2\2\u00de\u00e0\5\26\f\2\u00df\u00dd\3\2\2\2"+
		"\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7\4\2\2\u00e5\5\3\2\2\2\u00e6"+
		"\u00e7\5\32\16\2\u00e7\u00ed\5\b\5\2\u00e8\u00e9\7#\2\2\u00e9\u00ee\7"+
		"\33\2\2\u00ea\u00eb\7\36\2\2\u00eb\u00ee\7 \2\2\u00ec\u00ee\7(\2\2\u00ed"+
		"\u00e8\3\2\2\2\u00ed\u00ea\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ed\u00ee\3\2"+
		"\2\2\u00ee\7\3\2\2\2\u00ef\u00f8\7.\2\2\u00f0\u00f8\7/\2\2\u00f1\u00f8"+
		"\7\60\2\2\u00f2\u00f8\7\61\2\2\u00f3\u00f4\7\62\2\2\u00f4\u00f5\7\3\2"+
		"\2\u00f5\u00f6\7>\2\2\u00f6\u00f8\7\4\2\2\u00f7\u00ef\3\2\2\2\u00f7\u00f0"+
		"\3\2\2\2\u00f7\u00f1\3\2\2\2\u00f7\u00f2\3\2\2\2\u00f7\u00f3\3\2\2\2\u00f8"+
		"\t\3\2\2\2\u00f9\u00fa\7#\2\2\u00fa\u00fd\7\33\2\2\u00fb\u00fd\7(\2\2"+
		"\u00fc\u00f9\3\2\2\2\u00fc\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff"+
		"\7\3\2\2\u00ff\u0104\5\32\16\2\u0100\u0101\7<\2\2\u0101\u0103\5\32\16"+
		"\2\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105"+
		"\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0108\7\4\2\2\u0108"+
		"\13\3\2\2\2\u0109\u010a\b\7\1\2\u010a\u010b\7\3\2\2\u010b\u010c\5\f\7"+
		"\2\u010c\u010d\7\13\2\2\u010d\u010e\5\f\7\2\u010e\u010f\7\4\2\2\u010f"+
		"\u011b\3\2\2\2\u0110\u0111\7\3\2\2\u0111\u0112\5\f\7\2\u0112\u0113\7\""+
		"\2\2\u0113\u0114\5\f\7\2\u0114\u0115\7\4\2\2\u0115\u011b\3\2\2\2\u0116"+
		"\u0117\5\16\b\2\u0117\u0118\t\2\2\2\u0118\u0119\5\16\b\2\u0119\u011b\3"+
		"\2\2\2\u011a\u0109\3\2\2\2\u011a\u0110\3\2\2\2\u011a\u0116\3\2\2\2\u011b"+
		"\u0124\3\2\2\2\u011c\u011d\f\7\2\2\u011d\u011e\7\13\2\2\u011e\u0123\5"+
		"\f\7\b\u011f\u0120\f\6\2\2\u0120\u0121\7\"\2\2\u0121\u0123\5\f\7\7\u0122"+
		"\u011c\3\2\2\2\u0122\u011f\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2"+
		"\2\2\u0124\u0125\3\2\2\2\u0125\r\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u012f"+
		"\5\26\f\2\u0128\u0129\5\30\r\2\u0129\u012a\7\b\2\2\u012a\u012c\3\2\2\2"+
		"\u012b\u0128\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012f"+
		"\5\32\16\2\u012e\u0127\3\2\2\2\u012e\u012b\3\2\2\2\u012f\17\3\2\2\2\u0130"+
		"\u0136\5\32\16\2\u0131\u0132\5\30\r\2\u0132\u0133\7\b\2\2\u0133\u0134"+
		"\5\32\16\2\u0134\u0136\3\2\2\2\u0135\u0130\3\2\2\2\u0135\u0131\3\2\2\2"+
		"\u0136\21\3\2\2\2\u0137\u013d\7<\2\2\u0138\u013a\7\34\2\2\u0139\u0138"+
		"\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d\7\32\2\2"+
		"\u013c\u0137\3\2\2\2\u013c\u0139\3\2\2\2\u013d\23\3\2\2\2\u013e\u013f"+
		"\7!\2\2\u013f\u014d\5\f\7\2\u0140\u0141\7*\2\2\u0141\u0142\7\3\2\2\u0142"+
		"\u0147\5\32\16\2\u0143\u0144\7<\2\2\u0144\u0146\5\32\16\2\u0145\u0143"+
		"\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148"+
		"\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u014b\7\4\2\2\u014b\u014d\3\2"+
		"\2\2\u014c\u013e\3\2\2\2\u014c\u0140\3\2\2\2\u014d\25\3\2\2\2\u014e\u0150"+
		"\t\3\2\2\u014f\u014e\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\3\2\2\2\u0151"+
		"\u0155\7>\2\2\u0152\u0155\7?\2\2\u0153\u0155\7 \2\2\u0154\u014f\3\2\2"+
		"\2\u0154\u0152\3\2\2\2\u0154\u0153\3\2\2\2\u0155\27\3\2\2\2\u0156\u0157"+
		"\7=\2\2\u0157\31\3\2\2\2\u0158\u0159\7=\2\2\u0159\33\3\2\2\2\61%,\61>"+
		"CKOX[cty|\u0087\u008a\u0091\u0093\u0097\u009a\u00a2\u00a9\u00b0\u00b7"+
		"\u00bd\u00c4\u00c9\u00d0\u00d3\u00d6\u00d9\u00e1\u00ed\u00f7\u00fc\u0104"+
		"\u011a\u0122\u0124\u012b\u012e\u0135\u0139\u013c\u0147\u014c\u014f\u0154";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}