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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, K_AND=7, K_CREATE=8, K_DATABASE=9, 
		K_DATABASES=10, K_DELETE=11, K_DISTINCT=12, K_DROP=13, K_EXISTS=14, K_FROM=15, 
		K_IF=16, K_IN=17, K_INSERT=18, K_INTO=19, K_IS=20, K_ISNULL=21, K_JOIN=22, 
		K_KEY=23, K_NATURAL=24, K_NO=25, K_NOT=26, K_NOTNULL=27, K_NULL=28, K_ON=29, 
		K_OR=30, K_PRIMARY=31, K_RECURSIVE=32, K_SELECT=33, K_SET=34, K_TABLE=35, 
		K_UNIQUE=36, K_UPDATE=37, K_USING=38, K_VALUES=39, K_WHERE=40, K_WITH=41, 
		K_INT=42, K_LONG=43, K_FLOAT=44, K_DOUBLE=45, K_VARCHAR=46, K_USE=47, 
		K_SHOW=48, K_EXIT=49, K_LT=50, K_LE=51, K_GT=52, K_GE=53, K_EQ=54, K_NEQ=55, 
		K_CARTESIAN=56, IDENTIFIER=57, NUMERIC_LITERAL=58, STRING_LITERAL=59, 
		SPACES=60;
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
			null, "'('", "')'", "'#'", "'.'", "'+'", "'-'", null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "'<'", "'<='", "'>'", "'>='", "'='", "'<>'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "K_AND", "K_CREATE", "K_DATABASE", 
			"K_DATABASES", "K_DELETE", "K_DISTINCT", "K_DROP", "K_EXISTS", "K_FROM", 
			"K_IF", "K_IN", "K_INSERT", "K_INTO", "K_IS", "K_ISNULL", "K_JOIN", "K_KEY", 
			"K_NATURAL", "K_NO", "K_NOT", "K_NOTNULL", "K_NULL", "K_ON", "K_OR", 
			"K_PRIMARY", "K_RECURSIVE", "K_SELECT", "K_SET", "K_TABLE", "K_UNIQUE", 
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
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public TerminalNode K_FROM() { return getToken(minisqlParser.K_FROM, 0); }
		public List<Table_nameContext> table_name() {
			return getRuleContexts(Table_nameContext.class);
		}
		public Table_nameContext table_name(int i) {
			return getRuleContext(Table_nameContext.class,i);
		}
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public List<TerminalNode> K_CARTESIAN() { return getTokens(minisqlParser.K_CARTESIAN); }
		public TerminalNode K_CARTESIAN(int i) {
			return getToken(minisqlParser.K_CARTESIAN, i);
		}
		public Join_operatorContext join_operator() {
			return getRuleContext(Join_operatorContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(minisqlParser.K_WHERE, 0); }
		public Logical_exprContext logical_expr() {
			return getRuleContext(Logical_exprContext.class,0);
		}
		public Join_constraintContext join_constraint() {
			return getRuleContext(Join_constraintContext.class,0);
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
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
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
				setState(46);
				match(EOF);
				}
				break;
			case 2:
				_localctx = new Insert_tableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				match(K_INSERT);
				setState(49);
				match(K_INTO);
				setState(50);
				table_name();
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(51);
					match(T__0);
					setState(52);
					column_name();
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==K_CARTESIAN) {
						{
						{
						setState(53);
						match(K_CARTESIAN);
						setState(54);
						column_name();
						}
						}
						setState(59);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(60);
					match(T__1);
					}
				}

				{
				setState(64);
				match(K_VALUES);
				setState(65);
				row();
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(66);
					match(K_CARTESIAN);
					setState(67);
					row();
					}
					}
					setState(72);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				setState(73);
				match(EOF);
				}
				break;
			case 3:
				_localctx = new Delete_tableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				match(K_DELETE);
				setState(76);
				match(K_FROM);
				setState(77);
				table_name();
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(78);
					match(K_WHERE);
					setState(79);
					logical_expr(0);
					}
				}

				setState(82);
				match(EOF);
				}
				break;
			case 4:
				_localctx = new Drop_tableContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(84);
				match(K_DROP);
				setState(85);
				match(K_TABLE);
				setState(86);
				table_name();
				setState(87);
				match(EOF);
				}
				break;
			case 5:
				_localctx = new Update_tableContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(89);
				match(K_UPDATE);
				setState(90);
				table_name();
				setState(91);
				match(K_SET);
				setState(92);
				column_name();
				setState(93);
				match(K_EQ);
				setState(94);
				literal_value();
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(95);
					match(K_CARTESIAN);
					setState(96);
					column_name();
					setState(97);
					match(K_EQ);
					setState(98);
					literal_value();
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(105);
					match(K_WHERE);
					setState(106);
					logical_expr(0);
					}
				}

				setState(109);
				match(EOF);
				}
				break;
			case 6:
				_localctx = new Select_tableContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(111);
				match(K_SELECT);
				setState(112);
				result_column();
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(113);
					match(K_CARTESIAN);
					setState(114);
					result_column();
					}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(120);
				match(K_FROM);
				setState(121);
				table_name();
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_JOIN) | (1L << K_NATURAL) | (1L << K_CARTESIAN))) != 0)) {
					{
					setState(122);
					join_operator();
					setState(123);
					table_name();
					setState(125);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==K_ON || _la==K_USING) {
						{
						setState(124);
						join_constraint();
						}
					}

					}
				}

				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(129);
					match(K_WHERE);
					setState(130);
					logical_expr(0);
					}
				}

				setState(133);
				match(EOF);
				}
				break;
			case 7:
				_localctx = new Show_tableContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(135);
				match(K_SHOW);
				setState(136);
				match(K_TABLE);
				setState(137);
				match(IDENTIFIER);
				setState(138);
				match(EOF);
				}
				break;
			case 8:
				_localctx = new Create_dbContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(139);
				match(K_CREATE);
				setState(140);
				match(K_DATABASE);
				setState(141);
				match(IDENTIFIER);
				setState(142);
				match(EOF);
				}
				break;
			case 9:
				_localctx = new Drop_dbContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(143);
				match(K_DROP);
				setState(144);
				match(K_DATABASE);
				setState(145);
				match(IDENTIFIER);
				setState(146);
				match(EOF);
				}
				break;
			case 10:
				_localctx = new Use_dbContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(147);
				match(K_USE);
				setState(148);
				match(K_DATABASE);
				setState(149);
				match(IDENTIFIER);
				setState(150);
				match(EOF);
				}
				break;
			case 11:
				_localctx = new Show_dbsContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(151);
				match(K_SHOW);
				setState(152);
				match(K_DATABASES);
				setState(153);
				match(EOF);
				}
				break;
			case 12:
				_localctx = new Show_dbContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(154);
				match(K_SHOW);
				setState(155);
				match(K_DATABASE);
				setState(156);
				match(IDENTIFIER);
				setState(157);
				match(EOF);
				}
				break;
			case 13:
				_localctx = new ExitContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(158);
				match(K_EXIT);
				setState(159);
				match(EOF);
				}
				break;
			case 14:
				_localctx = new CommentContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(160);
					match(T__2);
					setState(164);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
					while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1+1 ) {
							{
							{
							setState(161);
							matchWildcard();
							}
							} 
						}
						setState(166);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
					}
					}
				}

				setState(169);
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
			setState(172);
			match(T__0);
			setState(173);
			literal_value();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_CARTESIAN) {
				{
				{
				setState(174);
				match(K_CARTESIAN);
				setState(175);
				literal_value();
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
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
			setState(183);
			column_name();
			setState(184);
			type_name();
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(185);
				match(K_PRIMARY);
				setState(186);
				match(K_KEY);
				}
				break;
			case K_NOT:
				{
				setState(187);
				match(K_NOT);
				setState(188);
				match(K_NULL);
				}
				break;
			case K_UNIQUE:
				{
				setState(189);
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
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				match(K_INT);
				}
				break;
			case K_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				match(K_LONG);
				}
				break;
			case K_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				match(K_FLOAT);
				}
				break;
			case K_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
				match(K_DOUBLE);
				}
				break;
			case K_VARCHAR:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(196);
				match(K_VARCHAR);
				setState(197);
				match(T__0);
				setState(198);
				match(NUMERIC_LITERAL);
				setState(199);
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
			setState(205);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(202);
				match(K_PRIMARY);
				setState(203);
				match(K_KEY);
				}
				break;
			case K_UNIQUE:
				{
				setState(204);
				match(K_UNIQUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(207);
			match(T__0);
			setState(208);
			column_name();
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_CARTESIAN) {
				{
				{
				setState(209);
				match(K_CARTESIAN);
				setState(210);
				column_name();
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(216);
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
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(219);
				match(T__0);
				setState(220);
				logical_expr(0);
				setState(221);
				match(K_AND);
				setState(222);
				logical_expr(0);
				setState(223);
				match(T__1);
				}
				break;
			case 2:
				{
				setState(225);
				match(T__0);
				setState(226);
				logical_expr(0);
				setState(227);
				match(K_OR);
				setState(228);
				logical_expr(0);
				setState(229);
				match(T__1);
				}
				break;
			case 3:
				{
				setState(231);
				value_expr();
				setState(232);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_LT) | (1L << K_LE) | (1L << K_GT) | (1L << K_GE) | (1L << K_EQ) | (1L << K_NEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(233);
				value_expr();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(245);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(243);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new Logical_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expr);
						setState(237);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(238);
						match(K_AND);
						setState(239);
						logical_expr(6);
						}
						break;
					case 2:
						{
						_localctx = new Logical_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expr);
						setState(240);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(241);
						match(K_OR);
						setState(242);
						logical_expr(5);
						}
						break;
					}
					} 
				}
				setState(247);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
			setState(255);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case K_NULL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				literal_value();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(249);
					table_name();
					setState(250);
					match(T__3);
					}
					break;
				}
				setState(254);
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
			setState(262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				column_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
				table_name();
				setState(259);
				match(T__3);
				setState(260);
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
			setState(269);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_CARTESIAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				match(K_CARTESIAN);
				}
				break;
			case K_JOIN:
			case K_NATURAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(265);
					match(K_NATURAL);
					}
				}

				setState(268);
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
			setState(285);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_ON:
				enterOuterAlt(_localctx, 1);
				{
				setState(271);
				match(K_ON);
				setState(272);
				logical_expr(0);
				}
				break;
			case K_USING:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				match(K_USING);
				setState(274);
				match(T__0);
				setState(275);
				column_name();
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(276);
					match(K_CARTESIAN);
					setState(277);
					column_name();
					}
					}
					setState(282);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(283);
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
			setState(293);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4 || _la==T__5) {
					{
					setState(287);
					_la = _input.LA(1);
					if ( !(_la==T__4 || _la==T__5) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(290);
				match(NUMERIC_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(291);
				match(STRING_LITERAL);
				}
				break;
			case K_NULL:
				enterOuterAlt(_localctx, 3);
				{
				setState(292);
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
			setState(295);
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
			setState(297);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3>\u012e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2$\n\2\f\2"+
		"\16\2\'\13\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\7\2:\n\2\f\2\16\2=\13\2\3\2\3\2\5\2A\n\2\3\2\3\2\3\2\3"+
		"\2\7\2G\n\2\f\2\16\2J\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2S\n\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2"+
		"g\n\2\f\2\16\2j\13\2\3\2\3\2\5\2n\n\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2v\n\2"+
		"\f\2\16\2y\13\2\3\2\3\2\3\2\3\2\3\2\5\2\u0080\n\2\5\2\u0082\n\2\3\2\3"+
		"\2\5\2\u0086\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\u00a5"+
		"\n\2\f\2\16\2\u00a8\13\2\5\2\u00aa\n\2\3\2\5\2\u00ad\n\2\3\3\3\3\3\3\3"+
		"\3\7\3\u00b3\n\3\f\3\16\3\u00b6\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4\u00c1\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00cb\n\5\3\6\3\6"+
		"\3\6\5\6\u00d0\n\6\3\6\3\6\3\6\3\6\7\6\u00d6\n\6\f\6\16\6\u00d9\13\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7\u00ee\n\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00f6\n\7\f\7\16\7\u00f9"+
		"\13\7\3\b\3\b\3\b\3\b\5\b\u00ff\n\b\3\b\5\b\u0102\n\b\3\t\3\t\3\t\3\t"+
		"\3\t\5\t\u0109\n\t\3\n\3\n\5\n\u010d\n\n\3\n\5\n\u0110\n\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\7\13\u0119\n\13\f\13\16\13\u011c\13\13\3\13\3"+
		"\13\5\13\u0120\n\13\3\f\5\f\u0123\n\f\3\f\3\f\3\f\5\f\u0128\n\f\3\r\3"+
		"\r\3\16\3\16\3\16\3\u00a6\3\f\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\4"+
		"\3\2\649\3\2\7\b\2\u0153\2\u00ac\3\2\2\2\4\u00ae\3\2\2\2\6\u00b9\3\2\2"+
		"\2\b\u00ca\3\2\2\2\n\u00cf\3\2\2\2\f\u00ed\3\2\2\2\16\u0101\3\2\2\2\20"+
		"\u0108\3\2\2\2\22\u010f\3\2\2\2\24\u011f\3\2\2\2\26\u0127\3\2\2\2\30\u0129"+
		"\3\2\2\2\32\u012b\3\2\2\2\34\35\7\n\2\2\35\36\7%\2\2\36\37\5\30\r\2\37"+
		" \7\3\2\2 %\5\6\4\2!\"\7:\2\2\"$\5\6\4\2#!\3\2\2\2$\'\3\2\2\2%#\3\2\2"+
		"\2%&\3\2\2\2&,\3\2\2\2\'%\3\2\2\2()\7:\2\2)+\5\n\6\2*(\3\2\2\2+.\3\2\2"+
		"\2,*\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7\4\2\2\60\61\7\2\2\3\61"+
		"\u00ad\3\2\2\2\62\63\7\24\2\2\63\64\7\25\2\2\64@\5\30\r\2\65\66\7\3\2"+
		"\2\66;\5\32\16\2\678\7:\2\28:\5\32\16\29\67\3\2\2\2:=\3\2\2\2;9\3\2\2"+
		"\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7\4\2\2?A\3\2\2\2@\65\3\2\2\2@A\3\2"+
		"\2\2AB\3\2\2\2BC\7)\2\2CH\5\4\3\2DE\7:\2\2EG\5\4\3\2FD\3\2\2\2GJ\3\2\2"+
		"\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\7\2\2\3L\u00ad\3\2\2\2MN"+
		"\7\r\2\2NO\7\21\2\2OR\5\30\r\2PQ\7*\2\2QS\5\f\7\2RP\3\2\2\2RS\3\2\2\2"+
		"ST\3\2\2\2TU\7\2\2\3U\u00ad\3\2\2\2VW\7\17\2\2WX\7%\2\2XY\5\30\r\2YZ\7"+
		"\2\2\3Z\u00ad\3\2\2\2[\\\7\'\2\2\\]\5\30\r\2]^\7$\2\2^_\5\32\16\2_`\7"+
		"8\2\2`h\5\26\f\2ab\7:\2\2bc\5\32\16\2cd\78\2\2de\5\26\f\2eg\3\2\2\2fa"+
		"\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2im\3\2\2\2jh\3\2\2\2kl\7*\2\2ln"+
		"\5\f\7\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7\2\2\3p\u00ad\3\2\2\2qr\7#\2"+
		"\2rw\5\20\t\2st\7:\2\2tv\5\20\t\2us\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2"+
		"\2\2xz\3\2\2\2yw\3\2\2\2z{\7\21\2\2{\u0081\5\30\r\2|}\5\22\n\2}\177\5"+
		"\30\r\2~\u0080\5\24\13\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3"+
		"\2\2\2\u0081|\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0084"+
		"\7*\2\2\u0084\u0086\5\f\7\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0088\7\2\2\3\u0088\u00ad\3\2\2\2\u0089\u008a\7\62"+
		"\2\2\u008a\u008b\7%\2\2\u008b\u008c\7;\2\2\u008c\u00ad\7\2\2\3\u008d\u008e"+
		"\7\n\2\2\u008e\u008f\7\13\2\2\u008f\u0090\7;\2\2\u0090\u00ad\7\2\2\3\u0091"+
		"\u0092\7\17\2\2\u0092\u0093\7\13\2\2\u0093\u0094\7;\2\2\u0094\u00ad\7"+
		"\2\2\3\u0095\u0096\7\61\2\2\u0096\u0097\7\13\2\2\u0097\u0098\7;\2\2\u0098"+
		"\u00ad\7\2\2\3\u0099\u009a\7\62\2\2\u009a\u009b\7\f\2\2\u009b\u00ad\7"+
		"\2\2\3\u009c\u009d\7\62\2\2\u009d\u009e\7\13\2\2\u009e\u009f\7;\2\2\u009f"+
		"\u00ad\7\2\2\3\u00a0\u00a1\7\63\2\2\u00a1\u00ad\7\2\2\3\u00a2\u00a6\7"+
		"\5\2\2\u00a3\u00a5\13\2\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2"+
		"\2\2\u00a9\u00a2\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"+
		"\u00ad\7\2\2\3\u00ac\34\3\2\2\2\u00ac\62\3\2\2\2\u00acM\3\2\2\2\u00ac"+
		"V\3\2\2\2\u00ac[\3\2\2\2\u00acq\3\2\2\2\u00ac\u0089\3\2\2\2\u00ac\u008d"+
		"\3\2\2\2\u00ac\u0091\3\2\2\2\u00ac\u0095\3\2\2\2\u00ac\u0099\3\2\2\2\u00ac"+
		"\u009c\3\2\2\2\u00ac\u00a0\3\2\2\2\u00ac\u00a9\3\2\2\2\u00ad\3\3\2\2\2"+
		"\u00ae\u00af\7\3\2\2\u00af\u00b4\5\26\f\2\u00b0\u00b1\7:\2\2\u00b1\u00b3"+
		"\5\26\f\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2"+
		"\u00b4\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8"+
		"\7\4\2\2\u00b8\5\3\2\2\2\u00b9\u00ba\5\32\16\2\u00ba\u00c0\5\b\5\2\u00bb"+
		"\u00bc\7!\2\2\u00bc\u00c1\7\31\2\2\u00bd\u00be\7\34\2\2\u00be\u00c1\7"+
		"\36\2\2\u00bf\u00c1\7&\2\2\u00c0\u00bb\3\2\2\2\u00c0\u00bd\3\2\2\2\u00c0"+
		"\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\7\3\2\2\2\u00c2\u00cb\7,\2\2"+
		"\u00c3\u00cb\7-\2\2\u00c4\u00cb\7.\2\2\u00c5\u00cb\7/\2\2\u00c6\u00c7"+
		"\7\60\2\2\u00c7\u00c8\7\3\2\2\u00c8\u00c9\7<\2\2\u00c9\u00cb\7\4\2\2\u00ca"+
		"\u00c2\3\2\2\2\u00ca\u00c3\3\2\2\2\u00ca\u00c4\3\2\2\2\u00ca\u00c5\3\2"+
		"\2\2\u00ca\u00c6\3\2\2\2\u00cb\t\3\2\2\2\u00cc\u00cd\7!\2\2\u00cd\u00d0"+
		"\7\31\2\2\u00ce\u00d0\7&\2\2\u00cf\u00cc\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00d2\7\3\2\2\u00d2\u00d7\5\32\16\2\u00d3\u00d4\7"+
		":\2\2\u00d4\u00d6\5\32\16\2\u00d5\u00d3\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9\u00d7\3\2"+
		"\2\2\u00da\u00db\7\4\2\2\u00db\13\3\2\2\2\u00dc\u00dd\b\7\1\2\u00dd\u00de"+
		"\7\3\2\2\u00de\u00df\5\f\7\2\u00df\u00e0\7\t\2\2\u00e0\u00e1\5\f\7\2\u00e1"+
		"\u00e2\7\4\2\2\u00e2\u00ee\3\2\2\2\u00e3\u00e4\7\3\2\2\u00e4\u00e5\5\f"+
		"\7\2\u00e5\u00e6\7 \2\2\u00e6\u00e7\5\f\7\2\u00e7\u00e8\7\4\2\2\u00e8"+
		"\u00ee\3\2\2\2\u00e9\u00ea\5\16\b\2\u00ea\u00eb\t\2\2\2\u00eb\u00ec\5"+
		"\16\b\2\u00ec\u00ee\3\2\2\2\u00ed\u00dc\3\2\2\2\u00ed\u00e3\3\2\2\2\u00ed"+
		"\u00e9\3\2\2\2\u00ee\u00f7\3\2\2\2\u00ef\u00f0\f\7\2\2\u00f0\u00f1\7\t"+
		"\2\2\u00f1\u00f6\5\f\7\b\u00f2\u00f3\f\6\2\2\u00f3\u00f4\7 \2\2\u00f4"+
		"\u00f6\5\f\7\7\u00f5\u00ef\3\2\2\2\u00f5\u00f2\3\2\2\2\u00f6\u00f9\3\2"+
		"\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\r\3\2\2\2\u00f9\u00f7"+
		"\3\2\2\2\u00fa\u0102\5\26\f\2\u00fb\u00fc\5\30\r\2\u00fc\u00fd\7\6\2\2"+
		"\u00fd\u00ff\3\2\2\2\u00fe\u00fb\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100"+
		"\3\2\2\2\u0100\u0102\5\32\16\2\u0101\u00fa\3\2\2\2\u0101\u00fe\3\2\2\2"+
		"\u0102\17\3\2\2\2\u0103\u0109\5\32\16\2\u0104\u0105\5\30\r\2\u0105\u0106"+
		"\7\6\2\2\u0106\u0107\5\32\16\2\u0107\u0109\3\2\2\2\u0108\u0103\3\2\2\2"+
		"\u0108\u0104\3\2\2\2\u0109\21\3\2\2\2\u010a\u0110\7:\2\2\u010b\u010d\7"+
		"\32\2\2\u010c\u010b\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		"\u0110\7\30\2\2\u010f\u010a\3\2\2\2\u010f\u010c\3\2\2\2\u0110\23\3\2\2"+
		"\2\u0111\u0112\7\37\2\2\u0112\u0120\5\f\7\2\u0113\u0114\7(\2\2\u0114\u0115"+
		"\7\3\2\2\u0115\u011a\5\32\16\2\u0116\u0117\7:\2\2\u0117\u0119\5\32\16"+
		"\2\u0118\u0116\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b"+
		"\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u011e\7\4\2\2\u011e"+
		"\u0120\3\2\2\2\u011f\u0111\3\2\2\2\u011f\u0113\3\2\2\2\u0120\25\3\2\2"+
		"\2\u0121\u0123\t\3\2\2\u0122\u0121\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124"+
		"\3\2\2\2\u0124\u0128\7<\2\2\u0125\u0128\7=\2\2\u0126\u0128\7\36\2\2\u0127"+
		"\u0122\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0126\3\2\2\2\u0128\27\3\2\2"+
		"\2\u0129\u012a\7;\2\2\u012a\31\3\2\2\2\u012b\u012c\7;\2\2\u012c\33\3\2"+
		"\2\2\"%,;@HRhmw\177\u0081\u0085\u00a6\u00a9\u00ac\u00b4\u00c0\u00ca\u00cf"+
		"\u00d7\u00ed\u00f5\u00f7\u00fe\u0101\u0108\u010c\u010f\u011a\u011f\u0122"+
		"\u0127";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}