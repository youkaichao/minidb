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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, K_AND=6, K_CREATE=7, K_DATABASE=8, 
		K_DATABASES=9, K_DELETE=10, K_DISTINCT=11, K_DROP=12, K_EXISTS=13, K_FROM=14, 
		K_IF=15, K_IN=16, K_INSERT=17, K_INTO=18, K_IS=19, K_ISNULL=20, K_JOIN=21, 
		K_KEY=22, K_NATURAL=23, K_NO=24, K_NOT=25, K_NOTNULL=26, K_NULL=27, K_ON=28, 
		K_OR=29, K_PRIMARY=30, K_RECURSIVE=31, K_SELECT=32, K_SET=33, K_TABLE=34, 
		K_UNIQUE=35, K_UPDATE=36, K_USING=37, K_VALUES=38, K_WHERE=39, K_WITH=40, 
		K_INT=41, K_LONG=42, K_FLOAT=43, K_DOUBLE=44, K_VARCHAR=45, K_USE=46, 
		K_SHOW=47, K_EXIT=48, K_LT=49, K_LE=50, K_GT=51, K_GE=52, K_EQ=53, K_NEQ=54, 
		K_CARTESIAN=55, IDENTIFIER=56, NUMERIC_LITERAL=57, STRING_LITERAL=58, 
		SPACES=59;
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
			null, "'('", "')'", "'.'", "'+'", "'-'", null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "'<'", "'<='", "'>'", "'>='", "'='", "'<>'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "K_AND", "K_CREATE", "K_DATABASE", 
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
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
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
			setState(162);
			match(T__0);
			setState(163);
			literal_value();
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_CARTESIAN) {
				{
				{
				setState(164);
				match(K_CARTESIAN);
				setState(165);
				literal_value();
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(171);
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
			setState(173);
			column_name();
			setState(174);
			type_name();
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(175);
				match(K_PRIMARY);
				setState(176);
				match(K_KEY);
				}
				break;
			case K_NOT:
				{
				setState(177);
				match(K_NOT);
				setState(178);
				match(K_NULL);
				}
				break;
			case K_UNIQUE:
				{
				setState(179);
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
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				match(K_INT);
				}
				break;
			case K_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				match(K_LONG);
				}
				break;
			case K_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
				match(K_FLOAT);
				}
				break;
			case K_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(185);
				match(K_DOUBLE);
				}
				break;
			case K_VARCHAR:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(186);
				match(K_VARCHAR);
				setState(187);
				match(T__0);
				setState(188);
				match(NUMERIC_LITERAL);
				setState(189);
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
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				{
				setState(192);
				match(K_PRIMARY);
				setState(193);
				match(K_KEY);
				}
				break;
			case K_UNIQUE:
				{
				setState(194);
				match(K_UNIQUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(197);
			match(T__0);
			setState(198);
			column_name();
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_CARTESIAN) {
				{
				{
				setState(199);
				match(K_CARTESIAN);
				setState(200);
				column_name();
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206);
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
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(209);
				match(T__0);
				setState(210);
				logical_expr(0);
				setState(211);
				match(K_AND);
				setState(212);
				logical_expr(0);
				setState(213);
				match(T__1);
				}
				break;
			case 2:
				{
				setState(215);
				match(T__0);
				setState(216);
				logical_expr(0);
				setState(217);
				match(K_OR);
				setState(218);
				logical_expr(0);
				setState(219);
				match(T__1);
				}
				break;
			case 3:
				{
				setState(221);
				value_expr();
				setState(222);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_LT) | (1L << K_LE) | (1L << K_GT) | (1L << K_GE) | (1L << K_EQ) | (1L << K_NEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(223);
				value_expr();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(233);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new Logical_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expr);
						setState(227);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(228);
						match(K_AND);
						setState(229);
						logical_expr(6);
						}
						break;
					case 2:
						{
						_localctx = new Logical_exprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_logical_expr);
						setState(230);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(231);
						match(K_OR);
						setState(232);
						logical_expr(5);
						}
						break;
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case T__4:
			case K_NULL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				literal_value();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(239);
					table_name();
					setState(240);
					match(T__2);
					}
					break;
				}
				setState(244);
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
			setState(252);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				column_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
				table_name();
				setState(249);
				match(T__2);
				setState(250);
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
			setState(259);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_CARTESIAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				match(K_CARTESIAN);
				}
				break;
			case K_JOIN:
			case K_NATURAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(255);
					match(K_NATURAL);
					}
				}

				setState(258);
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
			setState(275);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_ON:
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				match(K_ON);
				setState(262);
				logical_expr(0);
				}
				break;
			case K_USING:
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				match(K_USING);
				setState(264);
				match(T__0);
				setState(265);
				column_name();
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==K_CARTESIAN) {
					{
					{
					setState(266);
					match(K_CARTESIAN);
					setState(267);
					column_name();
					}
					}
					setState(272);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(273);
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
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case T__4:
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3 || _la==T__4) {
					{
					setState(277);
					_la = _input.LA(1);
					if ( !(_la==T__3 || _la==T__4) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(280);
				match(NUMERIC_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				match(STRING_LITERAL);
				}
				break;
			case K_NULL:
				enterOuterAlt(_localctx, 3);
				{
				setState(282);
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
			setState(285);
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
			setState(287);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0124\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2$\n\2\f\2"+
		"\16\2\'\13\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\7\2:\n\2\f\2\16\2=\13\2\3\2\3\2\5\2A\n\2\3\2\3\2\3\2\3"+
		"\2\7\2G\n\2\f\2\16\2J\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2S\n\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2"+
		"g\n\2\f\2\16\2j\13\2\3\2\3\2\5\2n\n\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2v\n\2"+
		"\f\2\16\2y\13\2\3\2\3\2\3\2\3\2\3\2\5\2\u0080\n\2\5\2\u0082\n\2\3\2\3"+
		"\2\5\2\u0086\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u00a3\n\2\3"+
		"\3\3\3\3\3\3\3\7\3\u00a9\n\3\f\3\16\3\u00ac\13\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\4\u00b7\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00c1"+
		"\n\5\3\6\3\6\3\6\5\6\u00c6\n\6\3\6\3\6\3\6\3\6\7\6\u00cc\n\6\f\6\16\6"+
		"\u00cf\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\5\7\u00e4\n\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00ec\n\7"+
		"\f\7\16\7\u00ef\13\7\3\b\3\b\3\b\3\b\5\b\u00f5\n\b\3\b\5\b\u00f8\n\b\3"+
		"\t\3\t\3\t\3\t\3\t\5\t\u00ff\n\t\3\n\3\n\5\n\u0103\n\n\3\n\5\n\u0106\n"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u010f\n\13\f\13\16\13\u0112"+
		"\13\13\3\13\3\13\5\13\u0116\n\13\3\f\5\f\u0119\n\f\3\f\3\f\3\f\5\f\u011e"+
		"\n\f\3\r\3\r\3\16\3\16\3\16\2\3\f\17\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\2\4\3\2\638\3\2\6\7\2\u0146\2\u00a2\3\2\2\2\4\u00a4\3\2\2\2\6\u00af\3"+
		"\2\2\2\b\u00c0\3\2\2\2\n\u00c5\3\2\2\2\f\u00e3\3\2\2\2\16\u00f7\3\2\2"+
		"\2\20\u00fe\3\2\2\2\22\u0105\3\2\2\2\24\u0115\3\2\2\2\26\u011d\3\2\2\2"+
		"\30\u011f\3\2\2\2\32\u0121\3\2\2\2\34\35\7\t\2\2\35\36\7$\2\2\36\37\5"+
		"\30\r\2\37 \7\3\2\2 %\5\6\4\2!\"\79\2\2\"$\5\6\4\2#!\3\2\2\2$\'\3\2\2"+
		"\2%#\3\2\2\2%&\3\2\2\2&,\3\2\2\2\'%\3\2\2\2()\79\2\2)+\5\n\6\2*(\3\2\2"+
		"\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7\4\2\2\60\61"+
		"\7\2\2\3\61\u00a3\3\2\2\2\62\63\7\23\2\2\63\64\7\24\2\2\64@\5\30\r\2\65"+
		"\66\7\3\2\2\66;\5\32\16\2\678\79\2\28:\5\32\16\29\67\3\2\2\2:=\3\2\2\2"+
		";9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7\4\2\2?A\3\2\2\2@\65\3\2\2"+
		"\2@A\3\2\2\2AB\3\2\2\2BC\7(\2\2CH\5\4\3\2DE\79\2\2EG\5\4\3\2FD\3\2\2\2"+
		"GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\7\2\2\3L\u00a3\3"+
		"\2\2\2MN\7\f\2\2NO\7\20\2\2OR\5\30\r\2PQ\7)\2\2QS\5\f\7\2RP\3\2\2\2RS"+
		"\3\2\2\2ST\3\2\2\2TU\7\2\2\3U\u00a3\3\2\2\2VW\7\16\2\2WX\7$\2\2XY\5\30"+
		"\r\2YZ\7\2\2\3Z\u00a3\3\2\2\2[\\\7&\2\2\\]\5\30\r\2]^\7#\2\2^_\5\32\16"+
		"\2_`\7\67\2\2`h\5\26\f\2ab\79\2\2bc\5\32\16\2cd\7\67\2\2de\5\26\f\2eg"+
		"\3\2\2\2fa\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2im\3\2\2\2jh\3\2\2\2k"+
		"l\7)\2\2ln\5\f\7\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7\2\2\3p\u00a3\3\2"+
		"\2\2qr\7\"\2\2rw\5\20\t\2st\79\2\2tv\5\20\t\2us\3\2\2\2vy\3\2\2\2wu\3"+
		"\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7\20\2\2{\u0081\5\30\r\2|}\5\22"+
		"\n\2}\177\5\30\r\2~\u0080\5\24\13\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080"+
		"\u0082\3\2\2\2\u0081|\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0085\3\2\2\2"+
		"\u0083\u0084\7)\2\2\u0084\u0086\5\f\7\2\u0085\u0083\3\2\2\2\u0085\u0086"+
		"\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\2\2\3\u0088\u00a3\3\2\2\2\u0089"+
		"\u008a\7\61\2\2\u008a\u008b\7$\2\2\u008b\u008c\7:\2\2\u008c\u00a3\7\2"+
		"\2\3\u008d\u008e\7\t\2\2\u008e\u008f\7\n\2\2\u008f\u0090\7:\2\2\u0090"+
		"\u00a3\7\2\2\3\u0091\u0092\7\16\2\2\u0092\u0093\7\n\2\2\u0093\u0094\7"+
		":\2\2\u0094\u00a3\7\2\2\3\u0095\u0096\7\60\2\2\u0096\u0097\7\n\2\2\u0097"+
		"\u0098\7:\2\2\u0098\u00a3\7\2\2\3\u0099\u009a\7\61\2\2\u009a\u009b\7\13"+
		"\2\2\u009b\u00a3\7\2\2\3\u009c\u009d\7\61\2\2\u009d\u009e\7\n\2\2\u009e"+
		"\u009f\7:\2\2\u009f\u00a3\7\2\2\3\u00a0\u00a1\7\62\2\2\u00a1\u00a3\7\2"+
		"\2\3\u00a2\34\3\2\2\2\u00a2\62\3\2\2\2\u00a2M\3\2\2\2\u00a2V\3\2\2\2\u00a2"+
		"[\3\2\2\2\u00a2q\3\2\2\2\u00a2\u0089\3\2\2\2\u00a2\u008d\3\2\2\2\u00a2"+
		"\u0091\3\2\2\2\u00a2\u0095\3\2\2\2\u00a2\u0099\3\2\2\2\u00a2\u009c\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a3\3\3\2\2\2\u00a4\u00a5\7\3\2\2\u00a5\u00aa"+
		"\5\26\f\2\u00a6\u00a7\79\2\2\u00a7\u00a9\5\26\f\2\u00a8\u00a6\3\2\2\2"+
		"\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad"+
		"\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00ae\7\4\2\2\u00ae\5\3\2\2\2\u00af"+
		"\u00b0\5\32\16\2\u00b0\u00b6\5\b\5\2\u00b1\u00b2\7 \2\2\u00b2\u00b7\7"+
		"\30\2\2\u00b3\u00b4\7\33\2\2\u00b4\u00b7\7\35\2\2\u00b5\u00b7\7%\2\2\u00b6"+
		"\u00b1\3\2\2\2\u00b6\u00b3\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\7\3\2\2\2\u00b8\u00c1\7+\2\2\u00b9\u00c1\7,\2\2\u00ba\u00c1"+
		"\7-\2\2\u00bb\u00c1\7.\2\2\u00bc\u00bd\7/\2\2\u00bd\u00be\7\3\2\2\u00be"+
		"\u00bf\7;\2\2\u00bf\u00c1\7\4\2\2\u00c0\u00b8\3\2\2\2\u00c0\u00b9\3\2"+
		"\2\2\u00c0\u00ba\3\2\2\2\u00c0\u00bb\3\2\2\2\u00c0\u00bc\3\2\2\2\u00c1"+
		"\t\3\2\2\2\u00c2\u00c3\7 \2\2\u00c3\u00c6\7\30\2\2\u00c4\u00c6\7%\2\2"+
		"\u00c5\u00c2\3\2\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8"+
		"\7\3\2\2\u00c8\u00cd\5\32\16\2\u00c9\u00ca\79\2\2\u00ca\u00cc\5\32\16"+
		"\2\u00cb\u00c9\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce"+
		"\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\7\4\2\2\u00d1"+
		"\13\3\2\2\2\u00d2\u00d3\b\7\1\2\u00d3\u00d4\7\3\2\2\u00d4\u00d5\5\f\7"+
		"\2\u00d5\u00d6\7\b\2\2\u00d6\u00d7\5\f\7\2\u00d7\u00d8\7\4\2\2\u00d8\u00e4"+
		"\3\2\2\2\u00d9\u00da\7\3\2\2\u00da\u00db\5\f\7\2\u00db\u00dc\7\37\2\2"+
		"\u00dc\u00dd\5\f\7\2\u00dd\u00de\7\4\2\2\u00de\u00e4\3\2\2\2\u00df\u00e0"+
		"\5\16\b\2\u00e0\u00e1\t\2\2\2\u00e1\u00e2\5\16\b\2\u00e2\u00e4\3\2\2\2"+
		"\u00e3\u00d2\3\2\2\2\u00e3\u00d9\3\2\2\2\u00e3\u00df\3\2\2\2\u00e4\u00ed"+
		"\3\2\2\2\u00e5\u00e6\f\7\2\2\u00e6\u00e7\7\b\2\2\u00e7\u00ec\5\f\7\b\u00e8"+
		"\u00e9\f\6\2\2\u00e9\u00ea\7\37\2\2\u00ea\u00ec\5\f\7\7\u00eb\u00e5\3"+
		"\2\2\2\u00eb\u00e8\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\r\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f8\5\26\f"+
		"\2\u00f1\u00f2\5\30\r\2\u00f2\u00f3\7\5\2\2\u00f3\u00f5\3\2\2\2\u00f4"+
		"\u00f1\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f8\5\32"+
		"\16\2\u00f7\u00f0\3\2\2\2\u00f7\u00f4\3\2\2\2\u00f8\17\3\2\2\2\u00f9\u00ff"+
		"\5\32\16\2\u00fa\u00fb\5\30\r\2\u00fb\u00fc\7\5\2\2\u00fc\u00fd\5\32\16"+
		"\2\u00fd\u00ff\3\2\2\2\u00fe\u00f9\3\2\2\2\u00fe\u00fa\3\2\2\2\u00ff\21"+
		"\3\2\2\2\u0100\u0106\79\2\2\u0101\u0103\7\31\2\2\u0102\u0101\3\2\2\2\u0102"+
		"\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106\7\27\2\2\u0105\u0100\3"+
		"\2\2\2\u0105\u0102\3\2\2\2\u0106\23\3\2\2\2\u0107\u0108\7\36\2\2\u0108"+
		"\u0116\5\f\7\2\u0109\u010a\7\'\2\2\u010a\u010b\7\3\2\2\u010b\u0110\5\32"+
		"\16\2\u010c\u010d\79\2\2\u010d\u010f\5\32\16\2\u010e\u010c\3\2\2\2\u010f"+
		"\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2"+
		"\2\2\u0112\u0110\3\2\2\2\u0113\u0114\7\4\2\2\u0114\u0116\3\2\2\2\u0115"+
		"\u0107\3\2\2\2\u0115\u0109\3\2\2\2\u0116\25\3\2\2\2\u0117\u0119\t\3\2"+
		"\2\u0118\u0117\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011e"+
		"\7;\2\2\u011b\u011e\7<\2\2\u011c\u011e\7\35\2\2\u011d\u0118\3\2\2\2\u011d"+
		"\u011b\3\2\2\2\u011d\u011c\3\2\2\2\u011e\27\3\2\2\2\u011f\u0120\7:\2\2"+
		"\u0120\31\3\2\2\2\u0121\u0122\7:\2\2\u0122\33\3\2\2\2 %,;@HRhmw\177\u0081"+
		"\u0085\u00a2\u00aa\u00b6\u00c0\u00c5\u00cd\u00e3\u00eb\u00ed\u00f4\u00f7"+
		"\u00fe\u0102\u0105\u0110\u0115\u0118\u011d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}