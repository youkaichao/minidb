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
		IDENTIFIER=56, NUMERIC_LITERAL=57, STRING_LITERAL=58, SPACES=59;
	public static final int
		RULE_sql_stmt = 0, RULE_row = 1, RULE_column_def = 2, RULE_type_name = 3, 
		RULE_table_constraint = 4, RULE_logical_expr = 5, RULE_value_expr = 6, 
		RULE_result_column = 7, RULE_join_clause = 8, RULE_join_operator = 9, 
		RULE_join_constraint = 10, RULE_literal_value = 11, RULE_table_name = 12, 
		RULE_column_name = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"sql_stmt", "row", "column_def", "type_name", "table_constraint", "logical_expr", 
			"value_expr", "result_column", "join_clause", "join_operator", "join_constraint", 
			"literal_value", "table_name", "column_name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'.'", "'+'", "'-'", null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "'<'", "'<='", "'>'", "'>='", "'='", "'<>'"
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
			"K_LE", "K_GT", "K_GE", "K_EQ", "K_NEQ", "IDENTIFIER", "NUMERIC_LITERAL", 
			"STRING_LITERAL", "SPACES"
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
	public static class Select_tableContext extends Sql_stmtContext {
		public TerminalNode K_SELECT() { return getToken(minisqlParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public TerminalNode K_FROM() { return getToken(minisqlParser.K_FROM, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode EOF() { return getToken(minisqlParser.EOF, 0); }
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(minisqlParser.K_WHERE, 0); }
		public Logical_exprContext logical_expr() {
			return getRuleContext(Logical_exprContext.class,0);
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
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new Create_tableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				match(K_CREATE);
				setState(29);
				match(K_TABLE);
				setState(30);
				table_name();
				setState(31);
				match(T__0);
				setState(32);
				column_def();
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(33);
						match(T__1);
						setState(34);
						column_def();
						}
						} 
					}
					setState(39);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(40);
					match(T__1);
					setState(41);
					table_constraint();
					}
					}
					setState(46);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(47);
				match(T__2);
				setState(48);
				match(EOF);
				}
				break;
			case 2:
				_localctx = new Insert_tableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				match(K_INSERT);
				setState(51);
				match(K_INTO);
				setState(52);
				table_name();
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(53);
					match(T__0);
					setState(54);
					column_name();
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(55);
						match(T__1);
						setState(56);
						column_name();
						}
						}
						setState(61);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(62);
					match(T__2);
					}
				}

				{
				setState(66);
				match(K_VALUES);
				setState(67);
				row();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(68);
					match(T__1);
					setState(69);
					row();
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				setState(75);
				match(EOF);
				}
				break;
			case 3:
				_localctx = new Delete_tableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				match(K_DELETE);
				setState(78);
				match(K_FROM);
				setState(79);
				table_name();
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(80);
					match(K_WHERE);
					setState(81);
					logical_expr(0);
					}
				}

				setState(84);
				match(EOF);
				}
				break;
			case 4:
				_localctx = new Drop_tableContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(86);
				match(K_DROP);
				setState(87);
				match(K_TABLE);
				setState(88);
				table_name();
				setState(89);
				match(EOF);
				}
				break;
			case 5:
				_localctx = new Update_tableContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(91);
				match(K_UPDATE);
				setState(92);
				table_name();
				setState(93);
				match(K_SET);
				setState(94);
				column_name();
				setState(95);
				match(K_EQ);
				setState(96);
				literal_value();
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(97);
					match(T__1);
					setState(98);
					column_name();
					setState(99);
					match(K_EQ);
					setState(100);
					literal_value();
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(107);
					match(K_WHERE);
					setState(108);
					logical_expr(0);
					}
				}

				setState(111);
				match(EOF);
				}
				break;
			case 6:
				_localctx = new Select_tableContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(113);
				match(K_SELECT);
				setState(114);
				result_column();
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(115);
					match(T__1);
					setState(116);
					result_column();
					}
					}
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(122);
				match(K_FROM);
				setState(123);
				table_name();
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << K_JOIN) | (1L << K_NATURAL))) != 0)) {
					{
					setState(124);
					join_clause();
					}
				}

				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(127);
					match(K_WHERE);
					setState(128);
					logical_expr(0);
					}
				}

				setState(131);
				match(EOF);
				}
				break;
			case 7:
				_localctx = new Show_tableContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(133);
				match(K_SHOW);
				setState(134);
				match(K_TABLE);
				setState(135);
				match(IDENTIFIER);
				setState(136);
				match(EOF);
				}
				break;
			case 8:
				_localctx = new Create_dbContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(137);
				match(K_CREATE);
				setState(138);
				match(K_DATABASE);
				setState(139);
				match(IDENTIFIER);
				setState(140);
				match(EOF);
				}
				break;
			case 9:
				_localctx = new Drop_dbContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(141);
				match(K_DROP);
				setState(142);
				match(K_DATABASE);
				setState(143);
				match(IDENTIFIER);
				setState(144);
				match(EOF);
				}
				break;
			case 10:
				_localctx = new Use_dbContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(145);
				match(K_USE);
				setState(146);
				match(K_DATABASE);
				setState(147);
				match(IDENTIFIER);
				setState(148);
				match(EOF);
				}
				break;
			case 11:
				_localctx = new Show_dbsContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(149);
				match(K_SHOW);
				setState(150);
				match(K_DATABASES);
				setState(151);
				match(EOF);
				}
				break;
			case 12:
				_localctx = new Show_dbContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(152);
				match(K_SHOW);
				setState(153);
				match(K_DATABASE);
				setState(154);
				match(IDENTIFIER);
				setState(155);
				match(EOF);
				}
				break;
			case 13:
				_localctx = new ExitContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(156);
				match(K_EXIT);
				setState(157);
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
			setState(160);
			match(T__0);
			setState(161);
			literal_value();
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(162);
				match(T__1);
				setState(163);
				literal_value();
				}
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(169);
			match(T__2);
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
			setState(171);
			column_name();
			setState(172);
			type_name();
			setState(178);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(173);
				match(K_PRIMARY);
				setState(174);
				match(K_KEY);
				}
				break;
			case K_NOT:
				{
				setState(175);
				match(K_NOT);
				setState(176);
				match(K_NULL);
				}
				break;
			case K_UNIQUE:
				{
				setState(177);
				match(K_UNIQUE);
				}
				break;
			case T__1:
			case T__2:
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
			setState(188);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				match(K_INT);
				}
				break;
			case K_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				match(K_LONG);
				}
				break;
			case K_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(182);
				match(K_FLOAT);
				}
				break;
			case K_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(183);
				match(K_DOUBLE);
				}
				break;
			case K_VARCHAR:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(184);
				match(K_VARCHAR);
				setState(185);
				match(T__0);
				setState(186);
				match(NUMERIC_LITERAL);
				setState(187);
				match(T__2);
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
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(190);
				match(K_PRIMARY);
				setState(191);
				match(K_KEY);
				}
				break;
			case K_UNIQUE:
				{
				setState(192);
				match(K_UNIQUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(195);
			match(T__0);
			setState(196);
			column_name();
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(197);
				match(T__1);
				setState(198);
				column_name();
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			match(T__2);
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
		public List<Logical_exprContext> logical_expr() {
			return getRuleContexts(Logical_exprContext.class);
		}
		public Logical_exprContext logical_expr(int i) {
			return getRuleContext(Logical_exprContext.class,i);
		}
		public TerminalNode K_AND() { return getToken(minisqlParser.K_AND, 0); }
		public TerminalNode K_OR() { return getToken(minisqlParser.K_OR, 0); }
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
			{
			setState(207);
			value_expr();
			setState(208);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_LT) | (1L << K_LE) | (1L << K_GT) | (1L << K_GE) | (1L << K_EQ) | (1L << K_NEQ))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(209);
			value_expr();
			}
			_ctx.stop = _input.LT(-1);
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(217);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new Logical_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expr);
						setState(211);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(212);
						match(K_AND);
						setState(213);
						logical_expr(3);
						}
						break;
					case 2:
						{
						_localctx = new Logical_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expr);
						setState(214);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(215);
						match(K_OR);
						setState(216);
						logical_expr(2);
						}
						break;
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case K_NULL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				literal_value();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(223);
					table_name();
					setState(224);
					match(T__3);
					}
					break;
				}
				setState(228);
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
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				column_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				table_name();
				setState(233);
				match(T__3);
				setState(234);
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

	public static class Join_clauseContext extends ParserRuleContext {
		public Join_operatorContext join_operator() {
			return getRuleContext(Join_operatorContext.class,0);
		}
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Join_constraintContext join_constraint() {
			return getRuleContext(Join_constraintContext.class,0);
		}
		public Join_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_clause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitJoin_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_clauseContext join_clause() throws RecognitionException {
		Join_clauseContext _localctx = new Join_clauseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_join_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			join_operator();
			setState(239);
			table_name();
			setState(240);
			join_constraint();
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
		enterRule(_localctx, 18, RULE_join_operator);
		int _la;
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				match(T__1);
				}
				break;
			case K_JOIN:
			case K_NATURAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(243);
					match(K_NATURAL);
					}
				}

				setState(246);
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
		enterRule(_localctx, 20, RULE_join_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_ON:
				{
				setState(249);
				match(K_ON);
				setState(250);
				logical_expr(0);
				}
				break;
			case K_USING:
				{
				setState(251);
				match(K_USING);
				setState(252);
				match(T__0);
				setState(253);
				column_name();
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(254);
					match(T__1);
					setState(255);
					column_name();
					}
					}
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(261);
				match(T__2);
				}
				break;
			case EOF:
			case K_WHERE:
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
		enterRule(_localctx, 22, RULE_literal_value);
		int _la;
		try {
			setState(271);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4 || _la==T__5) {
					{
					setState(265);
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

				setState(268);
				match(NUMERIC_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				match(STRING_LITERAL);
				}
				break;
			case K_NULL:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
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
		enterRule(_localctx, 24, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
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
		enterRule(_localctx, 26, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
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
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0118\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7"+
		"\2&\n\2\f\2\16\2)\13\2\3\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2<\n\2\f\2\16\2?\13\2\3\2\3\2\5\2C\n\2\3"+
		"\2\3\2\3\2\3\2\7\2I\n\2\f\2\16\2L\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2"+
		"U\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\7\2i\n\2\f\2\16\2l\13\2\3\2\3\2\5\2p\n\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\7\2x\n\2\f\2\16\2{\13\2\3\2\3\2\3\2\5\2\u0080\n\2\3\2\3\2\5\2\u0084"+
		"\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u00a1\n\2\3\3\3\3\3\3\3"+
		"\3\7\3\u00a7\n\3\f\3\16\3\u00aa\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4\u00b5\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00bf\n\5\3\6\3\6"+
		"\3\6\5\6\u00c4\n\6\3\6\3\6\3\6\3\6\7\6\u00ca\n\6\f\6\16\6\u00cd\13\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00dc\n\7\f\7\16"+
		"\7\u00df\13\7\3\b\3\b\3\b\3\b\5\b\u00e5\n\b\3\b\5\b\u00e8\n\b\3\t\3\t"+
		"\3\t\3\t\3\t\5\t\u00ef\n\t\3\n\3\n\3\n\3\n\3\13\3\13\5\13\u00f7\n\13\3"+
		"\13\5\13\u00fa\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0103\n\f\f\f\16\f"+
		"\u0106\13\f\3\f\3\f\5\f\u010a\n\f\3\r\5\r\u010d\n\r\3\r\3\r\3\r\5\r\u0112"+
		"\n\r\3\16\3\16\3\17\3\17\3\17\2\3\f\20\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\2\4\3\2\649\3\2\7\b\2\u0137\2\u00a0\3\2\2\2\4\u00a2\3\2\2\2\6\u00ad"+
		"\3\2\2\2\b\u00be\3\2\2\2\n\u00c3\3\2\2\2\f\u00d0\3\2\2\2\16\u00e7\3\2"+
		"\2\2\20\u00ee\3\2\2\2\22\u00f0\3\2\2\2\24\u00f9\3\2\2\2\26\u0109\3\2\2"+
		"\2\30\u0111\3\2\2\2\32\u0113\3\2\2\2\34\u0115\3\2\2\2\36\37\7\n\2\2\37"+
		" \7%\2\2 !\5\32\16\2!\"\7\3\2\2\"\'\5\6\4\2#$\7\4\2\2$&\5\6\4\2%#\3\2"+
		"\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(.\3\2\2\2)\'\3\2\2\2*+\7\4\2\2+-"+
		"\5\n\6\2,*\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2"+
		"\2\2\61\62\7\5\2\2\62\63\7\2\2\3\63\u00a1\3\2\2\2\64\65\7\24\2\2\65\66"+
		"\7\25\2\2\66B\5\32\16\2\678\7\3\2\28=\5\34\17\29:\7\4\2\2:<\5\34\17\2"+
		";9\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7\5\2\2"+
		"AC\3\2\2\2B\67\3\2\2\2BC\3\2\2\2CD\3\2\2\2DE\7)\2\2EJ\5\4\3\2FG\7\4\2"+
		"\2GI\5\4\3\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2"+
		"\2MN\7\2\2\3N\u00a1\3\2\2\2OP\7\r\2\2PQ\7\21\2\2QT\5\32\16\2RS\7*\2\2"+
		"SU\5\f\7\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7\2\2\3W\u00a1\3\2\2\2XY\7"+
		"\17\2\2YZ\7%\2\2Z[\5\32\16\2[\\\7\2\2\3\\\u00a1\3\2\2\2]^\7\'\2\2^_\5"+
		"\32\16\2_`\7$\2\2`a\5\34\17\2ab\78\2\2bj\5\30\r\2cd\7\4\2\2de\5\34\17"+
		"\2ef\78\2\2fg\5\30\r\2gi\3\2\2\2hc\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2"+
		"\2ko\3\2\2\2lj\3\2\2\2mn\7*\2\2np\5\f\7\2om\3\2\2\2op\3\2\2\2pq\3\2\2"+
		"\2qr\7\2\2\3r\u00a1\3\2\2\2st\7#\2\2ty\5\20\t\2uv\7\4\2\2vx\5\20\t\2w"+
		"u\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7\21\2\2"+
		"}\177\5\32\16\2~\u0080\5\22\n\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080\u0083"+
		"\3\2\2\2\u0081\u0082\7*\2\2\u0082\u0084\5\f\7\2\u0083\u0081\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7\2\2\3\u0086\u00a1\3\2"+
		"\2\2\u0087\u0088\7\62\2\2\u0088\u0089\7%\2\2\u0089\u008a\7:\2\2\u008a"+
		"\u00a1\7\2\2\3\u008b\u008c\7\n\2\2\u008c\u008d\7\13\2\2\u008d\u008e\7"+
		":\2\2\u008e\u00a1\7\2\2\3\u008f\u0090\7\17\2\2\u0090\u0091\7\13\2\2\u0091"+
		"\u0092\7:\2\2\u0092\u00a1\7\2\2\3\u0093\u0094\7\61\2\2\u0094\u0095\7\13"+
		"\2\2\u0095\u0096\7:\2\2\u0096\u00a1\7\2\2\3\u0097\u0098\7\62\2\2\u0098"+
		"\u0099\7\f\2\2\u0099\u00a1\7\2\2\3\u009a\u009b\7\62\2\2\u009b\u009c\7"+
		"\13\2\2\u009c\u009d\7:\2\2\u009d\u00a1\7\2\2\3\u009e\u009f\7\63\2\2\u009f"+
		"\u00a1\7\2\2\3\u00a0\36\3\2\2\2\u00a0\64\3\2\2\2\u00a0O\3\2\2\2\u00a0"+
		"X\3\2\2\2\u00a0]\3\2\2\2\u00a0s\3\2\2\2\u00a0\u0087\3\2\2\2\u00a0\u008b"+
		"\3\2\2\2\u00a0\u008f\3\2\2\2\u00a0\u0093\3\2\2\2\u00a0\u0097\3\2\2\2\u00a0"+
		"\u009a\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\3\3\2\2\2\u00a2\u00a3\7\3\2\2"+
		"\u00a3\u00a8\5\30\r\2\u00a4\u00a5\7\4\2\2\u00a5\u00a7\5\30\r\2\u00a6\u00a4"+
		"\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\7\5\2\2\u00ac\5\3\2\2\2"+
		"\u00ad\u00ae\5\34\17\2\u00ae\u00b4\5\b\5\2\u00af\u00b0\7!\2\2\u00b0\u00b5"+
		"\7\31\2\2\u00b1\u00b2\7\34\2\2\u00b2\u00b5\7\36\2\2\u00b3\u00b5\7&\2\2"+
		"\u00b4\u00af\3\2\2\2\u00b4\u00b1\3\2\2\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\7\3\2\2\2\u00b6\u00bf\7,\2\2\u00b7\u00bf\7-\2\2\u00b8\u00bf"+
		"\7.\2\2\u00b9\u00bf\7/\2\2\u00ba\u00bb\7\60\2\2\u00bb\u00bc\7\3\2\2\u00bc"+
		"\u00bd\7;\2\2\u00bd\u00bf\7\5\2\2\u00be\u00b6\3\2\2\2\u00be\u00b7\3\2"+
		"\2\2\u00be\u00b8\3\2\2\2\u00be\u00b9\3\2\2\2\u00be\u00ba\3\2\2\2\u00bf"+
		"\t\3\2\2\2\u00c0\u00c1\7!\2\2\u00c1\u00c4\7\31\2\2\u00c2\u00c4\7&\2\2"+
		"\u00c3\u00c0\3\2\2\2\u00c3\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6"+
		"\7\3\2\2\u00c6\u00cb\5\34\17\2\u00c7\u00c8\7\4\2\2\u00c8\u00ca\5\34\17"+
		"\2\u00c9\u00c7\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc"+
		"\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\5\2\2\u00cf"+
		"\13\3\2\2\2\u00d0\u00d1\b\7\1\2\u00d1\u00d2\5\16\b\2\u00d2\u00d3\t\2\2"+
		"\2\u00d3\u00d4\5\16\b\2\u00d4\u00dd\3\2\2\2\u00d5\u00d6\f\4\2\2\u00d6"+
		"\u00d7\7\t\2\2\u00d7\u00dc\5\f\7\5\u00d8\u00d9\f\3\2\2\u00d9\u00da\7 "+
		"\2\2\u00da\u00dc\5\f\7\4\u00db\u00d5\3\2\2\2\u00db\u00d8\3\2\2\2\u00dc"+
		"\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\r\3\2\2\2"+
		"\u00df\u00dd\3\2\2\2\u00e0\u00e8\5\30\r\2\u00e1\u00e2\5\32\16\2\u00e2"+
		"\u00e3\7\6\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e4\u00e5\3\2"+
		"\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e8\5\34\17\2\u00e7\u00e0\3\2\2\2\u00e7"+
		"\u00e4\3\2\2\2\u00e8\17\3\2\2\2\u00e9\u00ef\5\34\17\2\u00ea\u00eb\5\32"+
		"\16\2\u00eb\u00ec\7\6\2\2\u00ec\u00ed\5\34\17\2\u00ed\u00ef\3\2\2\2\u00ee"+
		"\u00e9\3\2\2\2\u00ee\u00ea\3\2\2\2\u00ef\21\3\2\2\2\u00f0\u00f1\5\24\13"+
		"\2\u00f1\u00f2\5\32\16\2\u00f2\u00f3\5\26\f\2\u00f3\23\3\2\2\2\u00f4\u00fa"+
		"\7\4\2\2\u00f5\u00f7\7\32\2\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2\2"+
		"\u00f7\u00f8\3\2\2\2\u00f8\u00fa\7\30\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00f6"+
		"\3\2\2\2\u00fa\25\3\2\2\2\u00fb\u00fc\7\37\2\2\u00fc\u010a\5\f\7\2\u00fd"+
		"\u00fe\7(\2\2\u00fe\u00ff\7\3\2\2\u00ff\u0104\5\34\17\2\u0100\u0101\7"+
		"\4\2\2\u0101\u0103\5\34\17\2\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104"+
		"\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u0104\3\2"+
		"\2\2\u0107\u0108\7\5\2\2\u0108\u010a\3\2\2\2\u0109\u00fb\3\2\2\2\u0109"+
		"\u00fd\3\2\2\2\u0109\u010a\3\2\2\2\u010a\27\3\2\2\2\u010b\u010d\t\3\2"+
		"\2\u010c\u010b\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u0112"+
		"\7;\2\2\u010f\u0112\7<\2\2\u0110\u0112\7\36\2\2\u0111\u010c\3\2\2\2\u0111"+
		"\u010f\3\2\2\2\u0111\u0110\3\2\2\2\u0112\31\3\2\2\2\u0113\u0114\7:\2\2"+
		"\u0114\33\3\2\2\2\u0115\u0116\7:\2\2\u0116\35\3\2\2\2\36\'.=BJTjoy\177"+
		"\u0083\u00a0\u00a8\u00b4\u00be\u00c3\u00cb\u00db\u00dd\u00e4\u00e7\u00ee"+
		"\u00f6\u00f9\u0104\u0109\u010c\u0111";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}