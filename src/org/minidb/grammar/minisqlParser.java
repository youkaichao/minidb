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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		K_AND=18, K_AS=19, K_CREATE=20, K_DATABASE=21, K_DATABASES=22, K_DELETE=23, 
		K_DISTINCT=24, K_DROP=25, K_EXCEPT=26, K_EXISTS=27, K_FROM=28, K_IF=29, 
		K_IN=30, K_INSERT=31, K_INTERSECT=32, K_INTO=33, K_IS=34, K_ISNULL=35, 
		K_JOIN=36, K_KEY=37, K_NATURAL=38, K_NO=39, K_NOT=40, K_NOTNULL=41, K_NULL=42, 
		K_ON=43, K_OR=44, K_PRIMARY=45, K_RECURSIVE=46, K_SELECT=47, K_SET=48, 
		K_TABLE=49, K_UNION=50, K_UNIQUE=51, K_UPDATE=52, K_USING=53, K_VALUES=54, 
		K_WHERE=55, K_WITH=56, K_INT=57, K_LONG=58, K_FLOAT=59, K_DOUBLE=60, K_VARCHAR=61, 
		K_USE=62, K_SHOW=63, K_EXIT=64, IDENTIFIER=65, NUMERIC_LITERAL=66, STRING_LITERAL=67, 
		SPACES=68;
	public static final int
		RULE_sql_stmt = 0, RULE_insert_stmt = 1, RULE_select_stmt = 2, RULE_select_core = 3, 
		RULE_column_def = 4, RULE_type_name = 5, RULE_table_constraint = 6, RULE_expr = 7, 
		RULE_result_column = 8, RULE_table = 9, RULE_join_clause = 10, RULE_join_operator = 11, 
		RULE_join_constraint = 12, RULE_compound_operator = 13, RULE_literal_value = 14, 
		RULE_unary_operator = 15, RULE_table_name = 16, RULE_column_name = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"sql_stmt", "insert_stmt", "select_stmt", "select_core", "column_def", 
			"type_name", "table_constraint", "expr", "result_column", "table", "join_clause", 
			"join_operator", "join_constraint", "compound_operator", "literal_value", 
			"unary_operator", "table_name", "column_name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'='", "'.'", "'*'", "'/'", "'%'", "'+'", 
			"'-'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'<>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "K_AND", "K_AS", "K_CREATE", "K_DATABASE", 
			"K_DATABASES", "K_DELETE", "K_DISTINCT", "K_DROP", "K_EXCEPT", "K_EXISTS", 
			"K_FROM", "K_IF", "K_IN", "K_INSERT", "K_INTERSECT", "K_INTO", "K_IS", 
			"K_ISNULL", "K_JOIN", "K_KEY", "K_NATURAL", "K_NO", "K_NOT", "K_NOTNULL", 
			"K_NULL", "K_ON", "K_OR", "K_PRIMARY", "K_RECURSIVE", "K_SELECT", "K_SET", 
			"K_TABLE", "K_UNION", "K_UNIQUE", "K_UPDATE", "K_USING", "K_VALUES", 
			"K_WHERE", "K_WITH", "K_INT", "K_LONG", "K_FLOAT", "K_DOUBLE", "K_VARCHAR", 
			"K_USE", "K_SHOW", "K_EXIT", "IDENTIFIER", "NUMERIC_LITERAL", "STRING_LITERAL", 
			"SPACES"
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
		public Create_dbContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitCreate_db(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Insert_tableContext extends Sql_stmtContext {
		public Insert_stmtContext insert_stmt() {
			return getRuleContext(Insert_stmtContext.class,0);
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
		public Drop_tableContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitDrop_table(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExitContext extends Sql_stmtContext {
		public TerminalNode K_EXIT() { return getToken(minisqlParser.K_EXIT, 0); }
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
		public Show_dbContext(Sql_stmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitShow_db(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Select_tableContext extends Sql_stmtContext {
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
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
		public TerminalNode K_WHERE() { return getToken(minisqlParser.K_WHERE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_WHERE() { return getToken(minisqlParser.K_WHERE, 0); }
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
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new Create_tableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				match(K_CREATE);
				setState(37);
				match(K_TABLE);
				setState(38);
				table_name();
				setState(39);
				match(T__0);
				setState(40);
				column_def();
				setState(45);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(41);
						match(T__1);
						setState(42);
						column_def();
						}
						} 
					}
					setState(47);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(48);
					match(T__1);
					setState(49);
					table_constraint();
					}
					}
					setState(54);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(55);
				match(T__2);
				}
				break;
			case 2:
				_localctx = new Insert_tableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				insert_stmt();
				}
				break;
			case 3:
				_localctx = new Delete_tableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				match(K_DELETE);
				setState(59);
				match(K_FROM);
				setState(60);
				table_name();
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(61);
					match(K_WHERE);
					setState(62);
					expr(0);
					}
				}

				}
				break;
			case 4:
				_localctx = new Drop_tableContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				match(K_DROP);
				setState(66);
				match(K_TABLE);
				setState(67);
				table_name();
				}
				break;
			case 5:
				_localctx = new Update_tableContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				match(K_UPDATE);
				setState(69);
				table_name();
				setState(70);
				match(K_SET);
				setState(71);
				column_name();
				setState(72);
				match(T__3);
				setState(73);
				expr(0);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(74);
					match(T__1);
					setState(75);
					column_name();
					setState(76);
					match(T__3);
					setState(77);
					expr(0);
					}
					}
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_WHERE) {
					{
					setState(84);
					match(K_WHERE);
					setState(85);
					expr(0);
					}
				}

				}
				break;
			case 6:
				_localctx = new Select_tableContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(88);
				select_stmt();
				}
				break;
			case 7:
				_localctx = new Show_tableContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(89);
				match(K_SHOW);
				setState(90);
				match(K_TABLE);
				setState(91);
				match(IDENTIFIER);
				}
				break;
			case 8:
				_localctx = new Create_dbContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(92);
				match(K_CREATE);
				setState(93);
				match(K_DATABASE);
				setState(94);
				match(IDENTIFIER);
				}
				break;
			case 9:
				_localctx = new Drop_dbContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(95);
				match(K_DROP);
				setState(96);
				match(K_DATABASE);
				setState(97);
				match(IDENTIFIER);
				}
				break;
			case 10:
				_localctx = new Use_dbContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(98);
				match(K_USE);
				setState(99);
				match(K_DATABASE);
				setState(100);
				match(IDENTIFIER);
				}
				break;
			case 11:
				_localctx = new Show_dbsContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(101);
				match(K_SHOW);
				setState(102);
				match(K_DATABASES);
				}
				break;
			case 12:
				_localctx = new Show_dbContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(103);
				match(K_SHOW);
				setState(104);
				match(K_DATABASE);
				setState(105);
				match(IDENTIFIER);
				}
				break;
			case 13:
				_localctx = new ExitContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(106);
				match(K_EXIT);
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

	public static class Insert_stmtContext extends ParserRuleContext {
		public TerminalNode K_INSERT() { return getToken(minisqlParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(minisqlParser.K_INTO, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_VALUES() { return getToken(minisqlParser.K_VALUES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Insert_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitInsert_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_stmtContext insert_stmt() throws RecognitionException {
		Insert_stmtContext _localctx = new Insert_stmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(K_INSERT);
			setState(110);
			match(K_INTO);
			setState(111);
			table_name();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(112);
				match(T__0);
				setState(113);
				column_name();
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(114);
					match(T__1);
					setState(115);
					column_name();
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(121);
				match(T__2);
				}
			}

			{
			setState(125);
			match(K_VALUES);
			setState(126);
			match(T__0);
			setState(127);
			expr(0);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(128);
				match(T__1);
				setState(129);
				expr(0);
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(135);
			match(T__2);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(136);
				match(T__1);
				setState(137);
				match(T__0);
				setState(138);
				expr(0);
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(139);
					match(T__1);
					setState(140);
					expr(0);
					}
					}
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(146);
				match(T__2);
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Select_stmtContext extends ParserRuleContext {
		public List<Select_coreContext> select_core() {
			return getRuleContexts(Select_coreContext.class);
		}
		public Select_coreContext select_core(int i) {
			return getRuleContext(Select_coreContext.class,i);
		}
		public List<Compound_operatorContext> compound_operator() {
			return getRuleContexts(Compound_operatorContext.class);
		}
		public Compound_operatorContext compound_operator(int i) {
			return getRuleContext(Compound_operatorContext.class,i);
		}
		public Select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitSelect_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_stmtContext select_stmt() throws RecognitionException {
		Select_stmtContext _localctx = new Select_stmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			select_core();
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_EXCEPT) | (1L << K_INTERSECT) | (1L << K_UNION))) != 0)) {
				{
				{
				setState(154);
				compound_operator();
				setState(155);
				select_core();
				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Select_coreContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(minisqlParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public TerminalNode K_DISTINCT() { return getToken(minisqlParser.K_DISTINCT, 0); }
		public TerminalNode K_FROM() { return getToken(minisqlParser.K_FROM, 0); }
		public TerminalNode K_WHERE() { return getToken(minisqlParser.K_WHERE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TableContext> table() {
			return getRuleContexts(TableContext.class);
		}
		public TableContext table(int i) {
			return getRuleContext(TableContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public Select_coreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_core; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitSelect_core(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_coreContext select_core() throws RecognitionException {
		Select_coreContext _localctx = new Select_coreContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_select_core);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(K_SELECT);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_DISTINCT) {
				{
				setState(163);
				match(K_DISTINCT);
				}
			}

			setState(166);
			result_column();
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(167);
				match(T__1);
				setState(168);
				result_column();
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_FROM) {
				{
				setState(174);
				match(K_FROM);
				setState(184);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(175);
					table();
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(176);
						match(T__1);
						setState(177);
						table();
						}
						}
						setState(182);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(183);
					join_clause();
					}
					break;
				}
				}
			}

			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(188);
				match(K_WHERE);
				setState(189);
				expr(0);
				}
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
		enterRule(_localctx, 8, RULE_column_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			column_name();
			setState(193);
			type_name();
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(194);
				match(K_PRIMARY);
				setState(195);
				match(K_KEY);
				}
				break;
			case K_NOT:
				{
				setState(196);
				match(K_NOT);
				setState(197);
				match(K_NULL);
				}
				break;
			case K_UNIQUE:
				{
				setState(198);
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
		enterRule(_localctx, 10, RULE_type_name);
		try {
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				match(K_INT);
				}
				break;
			case K_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				match(K_LONG);
				}
				break;
			case K_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(203);
				match(K_FLOAT);
				}
				break;
			case K_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				match(K_DOUBLE);
				}
				break;
			case K_VARCHAR:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(205);
				match(K_VARCHAR);
				setState(206);
				match(T__0);
				setState(207);
				match(NUMERIC_LITERAL);
				setState(208);
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
		enterRule(_localctx, 12, RULE_table_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(211);
				match(K_PRIMARY);
				setState(212);
				match(K_KEY);
				}
				break;
			case K_UNIQUE:
				{
				setState(213);
				match(K_UNIQUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(216);
			match(T__0);
			setState(217);
			column_name();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(218);
				match(T__1);
				setState(219);
				column_name();
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(225);
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

	public static class ExprContext extends ParserRuleContext {
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Unary_operatorContext unary_operator() {
			return getRuleContext(Unary_operatorContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_AND() { return getToken(minisqlParser.K_AND, 0); }
		public TerminalNode K_OR() { return getToken(minisqlParser.K_OR, 0); }
		public TerminalNode K_ISNULL() { return getToken(minisqlParser.K_ISNULL, 0); }
		public TerminalNode K_NOTNULL() { return getToken(minisqlParser.K_NOTNULL, 0); }
		public TerminalNode K_NOT() { return getToken(minisqlParser.K_NOT, 0); }
		public TerminalNode K_NULL() { return getToken(minisqlParser.K_NULL, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(228);
				literal_value();
				}
				break;
			case 2:
				{
				setState(232);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(229);
					table_name();
					setState(230);
					match(T__4);
					}
					break;
				}
				setState(234);
				column_name();
				}
				break;
			case 3:
				{
				setState(235);
				unary_operator();
				setState(236);
				expr(8);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(265);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(240);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(241);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(242);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(243);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(244);
						_la = _input.LA(1);
						if ( !(_la==T__8 || _la==T__9) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(245);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(247);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(248);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(249);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(250);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(251);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(252);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(253);
						match(K_AND);
						setState(254);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(255);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(256);
						match(K_OR);
						setState(257);
						expr(3);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(258);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(263);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case K_ISNULL:
							{
							setState(259);
							match(K_ISNULL);
							}
							break;
						case K_NOTNULL:
							{
							setState(260);
							match(K_NOTNULL);
							}
							break;
						case K_NOT:
							{
							setState(261);
							match(K_NOT);
							setState(262);
							match(K_NULL);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					}
					} 
				}
				setState(269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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

	public static class Result_columnContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(minisqlParser.K_AS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
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
		enterRule(_localctx, 16, RULE_result_column);
		int _la;
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				table_name();
				setState(272);
				match(T__4);
				setState(273);
				match(T__5);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(275);
				expr(0);
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_AS) {
					{
					setState(276);
					match(K_AS);
					setState(277);
					match(IDENTIFIER);
					}
				}

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

	public static class TableContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(minisqlParser.K_AS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(minisqlParser.IDENTIFIER, 0); }
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			table_name();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_AS) {
				{
				setState(283);
				match(K_AS);
				setState(284);
				match(IDENTIFIER);
				}
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

	public static class Join_clauseContext extends ParserRuleContext {
		public List<TableContext> table() {
			return getRuleContexts(TableContext.class);
		}
		public TableContext table(int i) {
			return getRuleContext(TableContext.class,i);
		}
		public List<Join_operatorContext> join_operator() {
			return getRuleContexts(Join_operatorContext.class);
		}
		public Join_operatorContext join_operator(int i) {
			return getRuleContext(Join_operatorContext.class,i);
		}
		public List<Join_constraintContext> join_constraint() {
			return getRuleContexts(Join_constraintContext.class);
		}
		public Join_constraintContext join_constraint(int i) {
			return getRuleContext(Join_constraintContext.class,i);
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
		enterRule(_localctx, 20, RULE_join_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			table();
			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << K_JOIN) | (1L << K_NATURAL))) != 0)) {
				{
				{
				setState(288);
				join_operator();
				setState(289);
				table();
				setState(290);
				join_constraint();
				}
				}
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
		enterRule(_localctx, 22, RULE_join_operator);
		int _la;
		try {
			setState(302);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(297);
				match(T__1);
				}
				break;
			case K_JOIN:
			case K_NATURAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(298);
					match(K_NATURAL);
					}
				}

				setState(301);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		enterRule(_localctx, 24, RULE_join_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_ON:
				{
				setState(304);
				match(K_ON);
				setState(305);
				expr(0);
				}
				break;
			case K_USING:
				{
				setState(306);
				match(K_USING);
				setState(307);
				match(T__0);
				setState(308);
				column_name();
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(309);
					match(T__1);
					setState(310);
					column_name();
					}
					}
					setState(315);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(316);
				match(T__2);
				}
				break;
			case EOF:
			case T__1:
			case K_EXCEPT:
			case K_INTERSECT:
			case K_JOIN:
			case K_NATURAL:
			case K_UNION:
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

	public static class Compound_operatorContext extends ParserRuleContext {
		public TerminalNode K_UNION() { return getToken(minisqlParser.K_UNION, 0); }
		public TerminalNode K_INTERSECT() { return getToken(minisqlParser.K_INTERSECT, 0); }
		public TerminalNode K_EXCEPT() { return getToken(minisqlParser.K_EXCEPT, 0); }
		public Compound_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitCompound_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_operatorContext compound_operator() throws RecognitionException {
		Compound_operatorContext _localctx = new Compound_operatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_compound_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_EXCEPT) | (1L << K_INTERSECT) | (1L << K_UNION))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		enterRule(_localctx, 28, RULE_literal_value);
		int _la;
		try {
			setState(328);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
			case T__9:
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8 || _la==T__9) {
					{
					setState(322);
					_la = _input.LA(1);
					if ( !(_la==T__8 || _la==T__9) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(325);
				match(NUMERIC_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(326);
				match(STRING_LITERAL);
				}
				break;
			case K_NULL:
				enterOuterAlt(_localctx, 3);
				{
				setState(327);
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

	public static class Unary_operatorContext extends ParserRuleContext {
		public TerminalNode K_NOT() { return getToken(minisqlParser.K_NOT, 0); }
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof minisqlVisitor ) return ((minisqlVisitor<? extends T>)visitor).visitUnary_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << K_NOT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		enterRule(_localctx, 32, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
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
		enterRule(_localctx, 34, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
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
		case 7:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3F\u0153\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2.\n\2\f\2\16\2\61\13\2\3\2\3"+
		"\2\7\2\65\n\2\f\2\16\28\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2B\n\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2R\n\2\f\2"+
		"\16\2U\13\2\3\2\3\2\5\2Y\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2n\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\7\3w\n\3\f\3\16\3z\13\3\3\3\3\3\5\3~\n\3\3\3\3\3\3\3\3\3\3\3\7\3\u0085"+
		"\n\3\f\3\16\3\u0088\13\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\u0090\n\3\f\3\16"+
		"\3\u0093\13\3\3\3\3\3\7\3\u0097\n\3\f\3\16\3\u009a\13\3\3\4\3\4\3\4\3"+
		"\4\7\4\u00a0\n\4\f\4\16\4\u00a3\13\4\3\5\3\5\5\5\u00a7\n\5\3\5\3\5\3\5"+
		"\7\5\u00ac\n\5\f\5\16\5\u00af\13\5\3\5\3\5\3\5\3\5\7\5\u00b5\n\5\f\5\16"+
		"\5\u00b8\13\5\3\5\5\5\u00bb\n\5\5\5\u00bd\n\5\3\5\3\5\5\5\u00c1\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00ca\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\5\7\u00d4\n\7\3\b\3\b\3\b\5\b\u00d9\n\b\3\b\3\b\3\b\3\b\7\b\u00df\n"+
		"\b\f\b\16\b\u00e2\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t\u00eb\n\t\3\t\3"+
		"\t\3\t\3\t\5\t\u00f1\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u010a\n\t\7\t\u010c"+
		"\n\t\f\t\16\t\u010f\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0119\n\n"+
		"\5\n\u011b\n\n\3\13\3\13\3\13\5\13\u0120\n\13\3\f\3\f\3\f\3\f\3\f\7\f"+
		"\u0127\n\f\f\f\16\f\u012a\13\f\3\r\3\r\5\r\u012e\n\r\3\r\5\r\u0131\n\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u013a\n\16\f\16\16\16\u013d\13"+
		"\16\3\16\3\16\5\16\u0141\n\16\3\17\3\17\3\20\5\20\u0146\n\20\3\20\3\20"+
		"\3\20\5\20\u014b\n\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\2\3\20\24\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\b\3\2\b\n\3\2\13\f\3\2\r\20"+
		"\4\2\6\6\21\23\5\2\34\34\"\"\64\64\4\2\13\f**\2\u017f\2m\3\2\2\2\4o\3"+
		"\2\2\2\6\u009b\3\2\2\2\b\u00a4\3\2\2\2\n\u00c2\3\2\2\2\f\u00d3\3\2\2\2"+
		"\16\u00d8\3\2\2\2\20\u00f0\3\2\2\2\22\u011a\3\2\2\2\24\u011c\3\2\2\2\26"+
		"\u0121\3\2\2\2\30\u0130\3\2\2\2\32\u0140\3\2\2\2\34\u0142\3\2\2\2\36\u014a"+
		"\3\2\2\2 \u014c\3\2\2\2\"\u014e\3\2\2\2$\u0150\3\2\2\2&\'\7\26\2\2\'("+
		"\7\63\2\2()\5\"\22\2)*\7\3\2\2*/\5\n\6\2+,\7\4\2\2,.\5\n\6\2-+\3\2\2\2"+
		".\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\66\3\2\2\2\61/\3\2\2\2\62\63\7\4"+
		"\2\2\63\65\5\16\b\2\64\62\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2"+
		"\2\2\679\3\2\2\28\66\3\2\2\29:\7\5\2\2:n\3\2\2\2;n\5\4\3\2<=\7\31\2\2"+
		"=>\7\36\2\2>A\5\"\22\2?@\79\2\2@B\5\20\t\2A?\3\2\2\2AB\3\2\2\2Bn\3\2\2"+
		"\2CD\7\33\2\2DE\7\63\2\2En\5\"\22\2FG\7\66\2\2GH\5\"\22\2HI\7\62\2\2I"+
		"J\5$\23\2JK\7\6\2\2KS\5\20\t\2LM\7\4\2\2MN\5$\23\2NO\7\6\2\2OP\5\20\t"+
		"\2PR\3\2\2\2QL\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TX\3\2\2\2US\3\2\2"+
		"\2VW\79\2\2WY\5\20\t\2XV\3\2\2\2XY\3\2\2\2Yn\3\2\2\2Zn\5\6\4\2[\\\7A\2"+
		"\2\\]\7\63\2\2]n\7C\2\2^_\7\26\2\2_`\7\27\2\2`n\7C\2\2ab\7\33\2\2bc\7"+
		"\27\2\2cn\7C\2\2de\7@\2\2ef\7\27\2\2fn\7C\2\2gh\7A\2\2hn\7\30\2\2ij\7"+
		"A\2\2jk\7\27\2\2kn\7C\2\2ln\7B\2\2m&\3\2\2\2m;\3\2\2\2m<\3\2\2\2mC\3\2"+
		"\2\2mF\3\2\2\2mZ\3\2\2\2m[\3\2\2\2m^\3\2\2\2ma\3\2\2\2md\3\2\2\2mg\3\2"+
		"\2\2mi\3\2\2\2ml\3\2\2\2n\3\3\2\2\2op\7!\2\2pq\7#\2\2q}\5\"\22\2rs\7\3"+
		"\2\2sx\5$\23\2tu\7\4\2\2uw\5$\23\2vt\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2"+
		"\2\2y{\3\2\2\2zx\3\2\2\2{|\7\5\2\2|~\3\2\2\2}r\3\2\2\2}~\3\2\2\2~\177"+
		"\3\2\2\2\177\u0080\78\2\2\u0080\u0081\7\3\2\2\u0081\u0086\5\20\t\2\u0082"+
		"\u0083\7\4\2\2\u0083\u0085\5\20\t\2\u0084\u0082\3\2\2\2\u0085\u0088\3"+
		"\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0089\u0098\7\5\2\2\u008a\u008b\7\4\2\2\u008b\u008c\7\3"+
		"\2\2\u008c\u0091\5\20\t\2\u008d\u008e\7\4\2\2\u008e\u0090\5\20\t\2\u008f"+
		"\u008d\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\7\5\2\2\u0095"+
		"\u0097\3\2\2\2\u0096\u008a\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2"+
		"\2\2\u0098\u0099\3\2\2\2\u0099\5\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u00a1"+
		"\5\b\5\2\u009c\u009d\5\34\17\2\u009d\u009e\5\b\5\2\u009e\u00a0\3\2\2\2"+
		"\u009f\u009c\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\7\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a6\7\61\2\2\u00a5"+
		"\u00a7\7\32\2\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3"+
		"\2\2\2\u00a8\u00ad\5\22\n\2\u00a9\u00aa\7\4\2\2\u00aa\u00ac\5\22\n\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00bc\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00ba\7\36\2\2\u00b1"+
		"\u00b6\5\24\13\2\u00b2\u00b3\7\4\2\2\u00b3\u00b5\5\24\13\2\u00b4\u00b2"+
		"\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00bb\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00bb\5\26\f\2\u00ba\u00b1\3"+
		"\2\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00b0\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bf\79\2\2\u00bf\u00c1\5\20"+
		"\t\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\t\3\2\2\2\u00c2\u00c3"+
		"\5$\23\2\u00c3\u00c9\5\f\7\2\u00c4\u00c5\7/\2\2\u00c5\u00ca\7\'\2\2\u00c6"+
		"\u00c7\7*\2\2\u00c7\u00ca\7,\2\2\u00c8\u00ca\7\65\2\2\u00c9\u00c4\3\2"+
		"\2\2\u00c9\u00c6\3\2\2\2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\13\3\2\2\2\u00cb\u00d4\7;\2\2\u00cc\u00d4\7<\2\2\u00cd\u00d4\7=\2\2\u00ce"+
		"\u00d4\7>\2\2\u00cf\u00d0\7?\2\2\u00d0\u00d1\7\3\2\2\u00d1\u00d2\7D\2"+
		"\2\u00d2\u00d4\7\5\2\2\u00d3\u00cb\3\2\2\2\u00d3\u00cc\3\2\2\2\u00d3\u00cd"+
		"\3\2\2\2\u00d3\u00ce\3\2\2\2\u00d3\u00cf\3\2\2\2\u00d4\r\3\2\2\2\u00d5"+
		"\u00d6\7/\2\2\u00d6\u00d9\7\'\2\2\u00d7\u00d9\7\65\2\2\u00d8\u00d5\3\2"+
		"\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\7\3\2\2\u00db"+
		"\u00e0\5$\23\2\u00dc\u00dd\7\4\2\2\u00dd\u00df\5$\23\2\u00de\u00dc\3\2"+
		"\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4\7\5\2\2\u00e4\17\3\2\2"+
		"\2\u00e5\u00e6\b\t\1\2\u00e6\u00f1\5\36\20\2\u00e7\u00e8\5\"\22\2\u00e8"+
		"\u00e9\7\7\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00e7\3\2\2\2\u00ea\u00eb\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00f1\5$\23\2\u00ed\u00ee\5 \21\2\u00ee"+
		"\u00ef\5\20\t\n\u00ef\u00f1\3\2\2\2\u00f0\u00e5\3\2\2\2\u00f0\u00ea\3"+
		"\2\2\2\u00f0\u00ed\3\2\2\2\u00f1\u010d\3\2\2\2\u00f2\u00f3\f\t\2\2\u00f3"+
		"\u00f4\t\2\2\2\u00f4\u010c\5\20\t\n\u00f5\u00f6\f\b\2\2\u00f6\u00f7\t"+
		"\3\2\2\u00f7\u010c\5\20\t\t\u00f8\u00f9\f\7\2\2\u00f9\u00fa\t\4\2\2\u00fa"+
		"\u010c\5\20\t\b\u00fb\u00fc\f\6\2\2\u00fc\u00fd\t\5\2\2\u00fd\u010c\5"+
		"\20\t\7\u00fe\u00ff\f\5\2\2\u00ff\u0100\7\24\2\2\u0100\u010c\5\20\t\6"+
		"\u0101\u0102\f\4\2\2\u0102\u0103\7.\2\2\u0103\u010c\5\20\t\5\u0104\u0109"+
		"\f\3\2\2\u0105\u010a\7%\2\2\u0106\u010a\7+\2\2\u0107\u0108\7*\2\2\u0108"+
		"\u010a\7,\2\2\u0109\u0105\3\2\2\2\u0109\u0106\3\2\2\2\u0109\u0107\3\2"+
		"\2\2\u010a\u010c\3\2\2\2\u010b\u00f2\3\2\2\2\u010b\u00f5\3\2\2\2\u010b"+
		"\u00f8\3\2\2\2\u010b\u00fb\3\2\2\2\u010b\u00fe\3\2\2\2\u010b\u0101\3\2"+
		"\2\2\u010b\u0104\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\21\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u011b\7\b\2"+
		"\2\u0111\u0112\5\"\22\2\u0112\u0113\7\7\2\2\u0113\u0114\7\b\2\2\u0114"+
		"\u011b\3\2\2\2\u0115\u0118\5\20\t\2\u0116\u0117\7\25\2\2\u0117\u0119\7"+
		"C\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2\2\2\u011a"+
		"\u0110\3\2\2\2\u011a\u0111\3\2\2\2\u011a\u0115\3\2\2\2\u011b\23\3\2\2"+
		"\2\u011c\u011f\5\"\22\2\u011d\u011e\7\25\2\2\u011e\u0120\7C\2\2\u011f"+
		"\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\25\3\2\2\2\u0121\u0128\5\24\13"+
		"\2\u0122\u0123\5\30\r\2\u0123\u0124\5\24\13\2\u0124\u0125\5\32\16\2\u0125"+
		"\u0127\3\2\2\2\u0126\u0122\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2"+
		"\2\2\u0128\u0129\3\2\2\2\u0129\27\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u0131"+
		"\7\4\2\2\u012c\u012e\7(\2\2\u012d\u012c\3\2\2\2\u012d\u012e\3\2\2\2\u012e"+
		"\u012f\3\2\2\2\u012f\u0131\7&\2\2\u0130\u012b\3\2\2\2\u0130\u012d\3\2"+
		"\2\2\u0131\31\3\2\2\2\u0132\u0133\7-\2\2\u0133\u0141\5\20\t\2\u0134\u0135"+
		"\7\67\2\2\u0135\u0136\7\3\2\2\u0136\u013b\5$\23\2\u0137\u0138\7\4\2\2"+
		"\u0138\u013a\5$\23\2\u0139\u0137\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139"+
		"\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2\2\2\u013d\u013b\3\2\2\2\u013e"+
		"\u013f\7\5\2\2\u013f\u0141\3\2\2\2\u0140\u0132\3\2\2\2\u0140\u0134\3\2"+
		"\2\2\u0140\u0141\3\2\2\2\u0141\33\3\2\2\2\u0142\u0143\t\6\2\2\u0143\35"+
		"\3\2\2\2\u0144\u0146\t\3\2\2\u0145\u0144\3\2\2\2\u0145\u0146\3\2\2\2\u0146"+
		"\u0147\3\2\2\2\u0147\u014b\7D\2\2\u0148\u014b\7E\2\2\u0149\u014b\7,\2"+
		"\2\u014a\u0145\3\2\2\2\u014a\u0148\3\2\2\2\u014a\u0149\3\2\2\2\u014b\37"+
		"\3\2\2\2\u014c\u014d\t\7\2\2\u014d!\3\2\2\2\u014e\u014f\7C\2\2\u014f#"+
		"\3\2\2\2\u0150\u0151\7C\2\2\u0151%\3\2\2\2\'/\66ASXmx}\u0086\u0091\u0098"+
		"\u00a1\u00a6\u00ad\u00b6\u00ba\u00bc\u00c0\u00c9\u00d3\u00d8\u00e0\u00ea"+
		"\u00f0\u0109\u010b\u010d\u0118\u011a\u011f\u0128\u012d\u0130\u013b\u0140"+
		"\u0145\u014a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}