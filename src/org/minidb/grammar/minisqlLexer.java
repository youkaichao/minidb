// Generated from /Users/youkaichao/Courses/database/minidb/src/org/minidb/grammar/minisql.g4 by ANTLR 4.7.2
package org.minidb.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class minisqlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		K_AND=18, K_AS=19, K_CREATE=20, K_DATABASE=21, K_DATABASES=22, K_DELETE=23, 
		K_DISTINCT=24, K_DROP=25, K_EXISTS=26, K_FROM=27, K_IF=28, K_IN=29, K_INSERT=30, 
		K_INTO=31, K_IS=32, K_ISNULL=33, K_JOIN=34, K_KEY=35, K_NATURAL=36, K_NO=37, 
		K_NOT=38, K_NOTNULL=39, K_NULL=40, K_ON=41, K_OR=42, K_PRIMARY=43, K_RECURSIVE=44, 
		K_SELECT=45, K_SET=46, K_TABLE=47, K_UNIQUE=48, K_UPDATE=49, K_USING=50, 
		K_VALUES=51, K_WHERE=52, K_WITH=53, K_INT=54, K_LONG=55, K_FLOAT=56, K_DOUBLE=57, 
		K_VARCHAR=58, K_USE=59, K_SHOW=60, K_EXIT=61, IDENTIFIER=62, NUMERIC_LITERAL=63, 
		STRING_LITERAL=64, SPACES=65;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"K_AND", "K_AS", "K_CREATE", "K_DATABASE", "K_DATABASES", "K_DELETE", 
			"K_DISTINCT", "K_DROP", "K_EXISTS", "K_FROM", "K_IF", "K_IN", "K_INSERT", 
			"K_INTO", "K_IS", "K_ISNULL", "K_JOIN", "K_KEY", "K_NATURAL", "K_NO", 
			"K_NOT", "K_NOTNULL", "K_NULL", "K_ON", "K_OR", "K_PRIMARY", "K_RECURSIVE", 
			"K_SELECT", "K_SET", "K_TABLE", "K_UNIQUE", "K_UPDATE", "K_USING", "K_VALUES", 
			"K_WHERE", "K_WITH", "K_INT", "K_LONG", "K_FLOAT", "K_DOUBLE", "K_VARCHAR", 
			"K_USE", "K_SHOW", "K_EXIT", "IDENTIFIER", "NUMERIC_LITERAL", "STRING_LITERAL", 
			"SPACES", "DIGIT", "NZDIGIT", "A", "B", "C", "D", "E", "F", "G", "H", 
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", 
			"W", "X", "Y", "Z"
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
			"K_DATABASES", "K_DELETE", "K_DISTINCT", "K_DROP", "K_EXISTS", "K_FROM", 
			"K_IF", "K_IN", "K_INSERT", "K_INTO", "K_IS", "K_ISNULL", "K_JOIN", "K_KEY", 
			"K_NATURAL", "K_NO", "K_NOT", "K_NOTNULL", "K_NULL", "K_ON", "K_OR", 
			"K_PRIMARY", "K_RECURSIVE", "K_SELECT", "K_SET", "K_TABLE", "K_UNIQUE", 
			"K_UPDATE", "K_USING", "K_VALUES", "K_WHERE", "K_WITH", "K_INT", "K_LONG", 
			"K_FLOAT", "K_DOUBLE", "K_VARCHAR", "K_USE", "K_SHOW", "K_EXIT", "IDENTIFIER", 
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


	public minisqlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "minisql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2C\u0246\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3"+
		"$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3"+
		"(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3"+
		",\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66"+
		"\3\66\3\67\3\67\3\67\3\67\38\38\38\38\38\39\39\39\39\39\39\3:\3:\3:\3"+
		":\3:\3:\3:\3;\3;\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3=\3=\3=\3=\3=\3>\3>\3"+
		">\3>\3>\3?\3?\7?\u01e5\n?\f?\16?\u01e8\13?\3@\6@\u01eb\n@\r@\16@\u01ec"+
		"\3@\3@\7@\u01f1\n@\f@\16@\u01f4\13@\5@\u01f6\n@\3@\3@\6@\u01fa\n@\r@\16"+
		"@\u01fb\5@\u01fe\n@\3A\3A\3A\3A\7A\u0204\nA\fA\16A\u0207\13A\3A\3A\3B"+
		"\3B\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L"+
		"\3M\3M\3N\3N\3O\3O\3P\3P\3Q\3Q\3R\3R\3S\3S\3T\3T\3U\3U\3V\3V\3W\3W\3X"+
		"\3X\3Y\3Y\3Z\3Z\3[\3[\3\\\3\\\3]\3]\3^\3^\2\2_\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081"+
		"B\u0083C\u0085\2\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2\u0091\2\u0093"+
		"\2\u0095\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f\2\u00a1\2\u00a3\2\u00a5"+
		"\2\u00a7\2\u00a9\2\u00ab\2\u00ad\2\u00af\2\u00b1\2\u00b3\2\u00b5\2\u00b7"+
		"\2\u00b9\2\u00bb\2\3\2\"\5\2C\\aac|\6\2\62;C\\aac|\3\2))\5\2\13\f\17\17"+
		"\"\"\3\2\62;\3\2\63;\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4"+
		"\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQq"+
		"q\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2"+
		"ZZzz\4\2[[{{\4\2\\\\||\2\u0231\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2"+
		"i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3"+
		"\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081"+
		"\3\2\2\2\2\u0083\3\2\2\2\3\u00bd\3\2\2\2\5\u00bf\3\2\2\2\7\u00c1\3\2\2"+
		"\2\t\u00c3\3\2\2\2\13\u00c5\3\2\2\2\r\u00c7\3\2\2\2\17\u00c9\3\2\2\2\21"+
		"\u00cb\3\2\2\2\23\u00cd\3\2\2\2\25\u00cf\3\2\2\2\27\u00d1\3\2\2\2\31\u00d3"+
		"\3\2\2\2\33\u00d6\3\2\2\2\35\u00d8\3\2\2\2\37\u00db\3\2\2\2!\u00de\3\2"+
		"\2\2#\u00e1\3\2\2\2%\u00e4\3\2\2\2\'\u00e8\3\2\2\2)\u00eb\3\2\2\2+\u00f2"+
		"\3\2\2\2-\u00fb\3\2\2\2/\u0105\3\2\2\2\61\u010c\3\2\2\2\63\u0115\3\2\2"+
		"\2\65\u011a\3\2\2\2\67\u0121\3\2\2\29\u0126\3\2\2\2;\u0129\3\2\2\2=\u012c"+
		"\3\2\2\2?\u0133\3\2\2\2A\u0138\3\2\2\2C\u013b\3\2\2\2E\u0142\3\2\2\2G"+
		"\u0147\3\2\2\2I\u014b\3\2\2\2K\u0153\3\2\2\2M\u0156\3\2\2\2O\u015a\3\2"+
		"\2\2Q\u0162\3\2\2\2S\u0167\3\2\2\2U\u016a\3\2\2\2W\u016d\3\2\2\2Y\u0175"+
		"\3\2\2\2[\u017f\3\2\2\2]\u0186\3\2\2\2_\u018a\3\2\2\2a\u0190\3\2\2\2c"+
		"\u0197\3\2\2\2e\u019e\3\2\2\2g\u01a4\3\2\2\2i\u01ab\3\2\2\2k\u01b1\3\2"+
		"\2\2m\u01b6\3\2\2\2o\u01ba\3\2\2\2q\u01bf\3\2\2\2s\u01c5\3\2\2\2u\u01cc"+
		"\3\2\2\2w\u01d4\3\2\2\2y\u01d8\3\2\2\2{\u01dd\3\2\2\2}\u01e2\3\2\2\2\177"+
		"\u01fd\3\2\2\2\u0081\u01ff\3\2\2\2\u0083\u020a\3\2\2\2\u0085\u020e\3\2"+
		"\2\2\u0087\u0210\3\2\2\2\u0089\u0212\3\2\2\2\u008b\u0214\3\2\2\2\u008d"+
		"\u0216\3\2\2\2\u008f\u0218\3\2\2\2\u0091\u021a\3\2\2\2\u0093\u021c\3\2"+
		"\2\2\u0095\u021e\3\2\2\2\u0097\u0220\3\2\2\2\u0099\u0222\3\2\2\2\u009b"+
		"\u0224\3\2\2\2\u009d\u0226\3\2\2\2\u009f\u0228\3\2\2\2\u00a1\u022a\3\2"+
		"\2\2\u00a3\u022c\3\2\2\2\u00a5\u022e\3\2\2\2\u00a7\u0230\3\2\2\2\u00a9"+
		"\u0232\3\2\2\2\u00ab\u0234\3\2\2\2\u00ad\u0236\3\2\2\2\u00af\u0238\3\2"+
		"\2\2\u00b1\u023a\3\2\2\2\u00b3\u023c\3\2\2\2\u00b5\u023e\3\2\2\2\u00b7"+
		"\u0240\3\2\2\2\u00b9\u0242\3\2\2\2\u00bb\u0244\3\2\2\2\u00bd\u00be\7*"+
		"\2\2\u00be\4\3\2\2\2\u00bf\u00c0\7.\2\2\u00c0\6\3\2\2\2\u00c1\u00c2\7"+
		"+\2\2\u00c2\b\3\2\2\2\u00c3\u00c4\7?\2\2\u00c4\n\3\2\2\2\u00c5\u00c6\7"+
		"\60\2\2\u00c6\f\3\2\2\2\u00c7\u00c8\7,\2\2\u00c8\16\3\2\2\2\u00c9\u00ca"+
		"\7\61\2\2\u00ca\20\3\2\2\2\u00cb\u00cc\7\'\2\2\u00cc\22\3\2\2\2\u00cd"+
		"\u00ce\7-\2\2\u00ce\24\3\2\2\2\u00cf\u00d0\7/\2\2\u00d0\26\3\2\2\2\u00d1"+
		"\u00d2\7>\2\2\u00d2\30\3\2\2\2\u00d3\u00d4\7>\2\2\u00d4\u00d5\7?\2\2\u00d5"+
		"\32\3\2\2\2\u00d6\u00d7\7@\2\2\u00d7\34\3\2\2\2\u00d8\u00d9\7@\2\2\u00d9"+
		"\u00da\7?\2\2\u00da\36\3\2\2\2\u00db\u00dc\7?\2\2\u00dc\u00dd\7?\2\2\u00dd"+
		" \3\2\2\2\u00de\u00df\7#\2\2\u00df\u00e0\7?\2\2\u00e0\"\3\2\2\2\u00e1"+
		"\u00e2\7>\2\2\u00e2\u00e3\7@\2\2\u00e3$\3\2\2\2\u00e4\u00e5\5\u0089E\2"+
		"\u00e5\u00e6\5\u00a3R\2\u00e6\u00e7\5\u008fH\2\u00e7&\3\2\2\2\u00e8\u00e9"+
		"\5\u0089E\2\u00e9\u00ea\5\u00adW\2\u00ea(\3\2\2\2\u00eb\u00ec\5\u008d"+
		"G\2\u00ec\u00ed\5\u00abV\2\u00ed\u00ee\5\u0091I\2\u00ee\u00ef\5\u0089"+
		"E\2\u00ef\u00f0\5\u00afX\2\u00f0\u00f1\5\u0091I\2\u00f1*\3\2\2\2\u00f2"+
		"\u00f3\5\u008fH\2\u00f3\u00f4\5\u0089E\2\u00f4\u00f5\5\u00afX\2\u00f5"+
		"\u00f6\5\u0089E\2\u00f6\u00f7\5\u008bF\2\u00f7\u00f8\5\u0089E\2\u00f8"+
		"\u00f9\5\u00adW\2\u00f9\u00fa\5\u0091I\2\u00fa,\3\2\2\2\u00fb\u00fc\5"+
		"\u008fH\2\u00fc\u00fd\5\u0089E\2\u00fd\u00fe\5\u00afX\2\u00fe\u00ff\5"+
		"\u0089E\2\u00ff\u0100\5\u008bF\2\u0100\u0101\5\u0089E\2\u0101\u0102\5"+
		"\u00adW\2\u0102\u0103\5\u0091I\2\u0103\u0104\5\u00adW\2\u0104.\3\2\2\2"+
		"\u0105\u0106\5\u008fH\2\u0106\u0107\5\u0091I\2\u0107\u0108\5\u009fP\2"+
		"\u0108\u0109\5\u0091I\2\u0109\u010a\5\u00afX\2\u010a\u010b\5\u0091I\2"+
		"\u010b\60\3\2\2\2\u010c\u010d\5\u008fH\2\u010d\u010e\5\u0099M\2\u010e"+
		"\u010f\5\u00adW\2\u010f\u0110\5\u00afX\2\u0110\u0111\5\u0099M\2\u0111"+
		"\u0112\5\u00a3R\2\u0112\u0113\5\u008dG\2\u0113\u0114\5\u00afX\2\u0114"+
		"\62\3\2\2\2\u0115\u0116\5\u008fH\2\u0116\u0117\5\u00abV\2\u0117\u0118"+
		"\5\u00a5S\2\u0118\u0119\5\u00a7T\2\u0119\64\3\2\2\2\u011a\u011b\5\u0091"+
		"I\2\u011b\u011c\5\u00b7\\\2\u011c\u011d\5\u0099M\2\u011d\u011e\5\u00ad"+
		"W\2\u011e\u011f\5\u00afX\2\u011f\u0120\5\u00adW\2\u0120\66\3\2\2\2\u0121"+
		"\u0122\5\u0093J\2\u0122\u0123\5\u00abV\2\u0123\u0124\5\u00a5S\2\u0124"+
		"\u0125\5\u00a1Q\2\u01258\3\2\2\2\u0126\u0127\5\u0099M\2\u0127\u0128\5"+
		"\u0093J\2\u0128:\3\2\2\2\u0129\u012a\5\u0099M\2\u012a\u012b\5\u00a3R\2"+
		"\u012b<\3\2\2\2\u012c\u012d\5\u0099M\2\u012d\u012e\5\u00a3R\2\u012e\u012f"+
		"\5\u00adW\2\u012f\u0130\5\u0091I\2\u0130\u0131\5\u00abV\2\u0131\u0132"+
		"\5\u00afX\2\u0132>\3\2\2\2\u0133\u0134\5\u0099M\2\u0134\u0135\5\u00a3"+
		"R\2\u0135\u0136\5\u00afX\2\u0136\u0137\5\u00a5S\2\u0137@\3\2\2\2\u0138"+
		"\u0139\5\u0099M\2\u0139\u013a\5\u00adW\2\u013aB\3\2\2\2\u013b\u013c\5"+
		"\u0099M\2\u013c\u013d\5\u00adW\2\u013d\u013e\5\u00a3R\2\u013e\u013f\5"+
		"\u00b1Y\2\u013f\u0140\5\u009fP\2\u0140\u0141\5\u009fP\2\u0141D\3\2\2\2"+
		"\u0142\u0143\5\u009bN\2\u0143\u0144\5\u00a5S\2\u0144\u0145\5\u0099M\2"+
		"\u0145\u0146\5\u00a3R\2\u0146F\3\2\2\2\u0147\u0148\5\u009dO\2\u0148\u0149"+
		"\5\u0091I\2\u0149\u014a\5\u00b9]\2\u014aH\3\2\2\2\u014b\u014c\5\u00a3"+
		"R\2\u014c\u014d\5\u0089E\2\u014d\u014e\5\u00afX\2\u014e\u014f\5\u00b1"+
		"Y\2\u014f\u0150\5\u00abV\2\u0150\u0151\5\u0089E\2\u0151\u0152\5\u009f"+
		"P\2\u0152J\3\2\2\2\u0153\u0154\5\u00a3R\2\u0154\u0155\5\u00a5S\2\u0155"+
		"L\3\2\2\2\u0156\u0157\5\u00a3R\2\u0157\u0158\5\u00a5S\2\u0158\u0159\5"+
		"\u00afX\2\u0159N\3\2\2\2\u015a\u015b\5\u00a3R\2\u015b\u015c\5\u00a5S\2"+
		"\u015c\u015d\5\u00afX\2\u015d\u015e\5\u00a3R\2\u015e\u015f\5\u00b1Y\2"+
		"\u015f\u0160\5\u009fP\2\u0160\u0161\5\u009fP\2\u0161P\3\2\2\2\u0162\u0163"+
		"\5\u00a3R\2\u0163\u0164\5\u00b1Y\2\u0164\u0165\5\u009fP\2\u0165\u0166"+
		"\5\u009fP\2\u0166R\3\2\2\2\u0167\u0168\5\u00a5S\2\u0168\u0169\5\u00a3"+
		"R\2\u0169T\3\2\2\2\u016a\u016b\5\u00a5S\2\u016b\u016c\5\u00abV\2\u016c"+
		"V\3\2\2\2\u016d\u016e\5\u00a7T\2\u016e\u016f\5\u00abV\2\u016f\u0170\5"+
		"\u0099M\2\u0170\u0171\5\u00a1Q\2\u0171\u0172\5\u0089E\2\u0172\u0173\5"+
		"\u00abV\2\u0173\u0174\5\u00b9]\2\u0174X\3\2\2\2\u0175\u0176\5\u00abV\2"+
		"\u0176\u0177\5\u0091I\2\u0177\u0178\5\u008dG\2\u0178\u0179\5\u00b1Y\2"+
		"\u0179\u017a\5\u00abV\2\u017a\u017b\5\u00adW\2\u017b\u017c\5\u0099M\2"+
		"\u017c\u017d\5\u00b3Z\2\u017d\u017e\5\u0091I\2\u017eZ\3\2\2\2\u017f\u0180"+
		"\5\u00adW\2\u0180\u0181\5\u0091I\2\u0181\u0182\5\u009fP\2\u0182\u0183"+
		"\5\u0091I\2\u0183\u0184\5\u008dG\2\u0184\u0185\5\u00afX\2\u0185\\\3\2"+
		"\2\2\u0186\u0187\5\u00adW\2\u0187\u0188\5\u0091I\2\u0188\u0189\5\u00af"+
		"X\2\u0189^\3\2\2\2\u018a\u018b\5\u00afX\2\u018b\u018c\5\u0089E\2\u018c"+
		"\u018d\5\u008bF\2\u018d\u018e\5\u009fP\2\u018e\u018f\5\u0091I\2\u018f"+
		"`\3\2\2\2\u0190\u0191\5\u00b1Y\2\u0191\u0192\5\u00a3R\2\u0192\u0193\5"+
		"\u0099M\2\u0193\u0194\5\u00a9U\2\u0194\u0195\5\u00b1Y\2\u0195\u0196\5"+
		"\u0091I\2\u0196b\3\2\2\2\u0197\u0198\5\u00b1Y\2\u0198\u0199\5\u00a7T\2"+
		"\u0199\u019a\5\u008fH\2\u019a\u019b\5\u0089E\2\u019b\u019c\5\u00afX\2"+
		"\u019c\u019d\5\u0091I\2\u019dd\3\2\2\2\u019e\u019f\5\u00b1Y\2\u019f\u01a0"+
		"\5\u00adW\2\u01a0\u01a1\5\u0099M\2\u01a1\u01a2\5\u00a3R\2\u01a2\u01a3"+
		"\5\u0095K\2\u01a3f\3\2\2\2\u01a4\u01a5\5\u00b3Z\2\u01a5\u01a6\5\u0089"+
		"E\2\u01a6\u01a7\5\u009fP\2\u01a7\u01a8\5\u00b1Y\2\u01a8\u01a9\5\u0091"+
		"I\2\u01a9\u01aa\5\u00adW\2\u01aah\3\2\2\2\u01ab\u01ac\5\u00b5[\2\u01ac"+
		"\u01ad\5\u0097L\2\u01ad\u01ae\5\u0091I\2\u01ae\u01af\5\u00abV\2\u01af"+
		"\u01b0\5\u0091I\2\u01b0j\3\2\2\2\u01b1\u01b2\5\u00b5[\2\u01b2\u01b3\5"+
		"\u0099M\2\u01b3\u01b4\5\u00afX\2\u01b4\u01b5\5\u0097L\2\u01b5l\3\2\2\2"+
		"\u01b6\u01b7\5\u0099M\2\u01b7\u01b8\5\u00a3R\2\u01b8\u01b9\5\u00afX\2"+
		"\u01b9n\3\2\2\2\u01ba\u01bb\5\u009fP\2\u01bb\u01bc\5\u00a5S\2\u01bc\u01bd"+
		"\5\u00a3R\2\u01bd\u01be\5\u0095K\2\u01bep\3\2\2\2\u01bf\u01c0\5\u0093"+
		"J\2\u01c0\u01c1\5\u009fP\2\u01c1\u01c2\5\u00a5S\2\u01c2\u01c3\5\u0089"+
		"E\2\u01c3\u01c4\5\u00afX\2\u01c4r\3\2\2\2\u01c5\u01c6\5\u008fH\2\u01c6"+
		"\u01c7\5\u00a5S\2\u01c7\u01c8\5\u00b1Y\2\u01c8\u01c9\5\u008bF\2\u01c9"+
		"\u01ca\5\u009fP\2\u01ca\u01cb\5\u0091I\2\u01cbt\3\2\2\2\u01cc\u01cd\5"+
		"\u00b3Z\2\u01cd\u01ce\5\u0089E\2\u01ce\u01cf\5\u00abV\2\u01cf\u01d0\5"+
		"\u008dG\2\u01d0\u01d1\5\u0097L\2\u01d1\u01d2\5\u0089E\2\u01d2\u01d3\5"+
		"\u00abV\2\u01d3v\3\2\2\2\u01d4\u01d5\5\u00b1Y\2\u01d5\u01d6\5\u00adW\2"+
		"\u01d6\u01d7\5\u0091I\2\u01d7x\3\2\2\2\u01d8\u01d9\5\u00adW\2\u01d9\u01da"+
		"\5\u0097L\2\u01da\u01db\5\u00a5S\2\u01db\u01dc\5\u00b5[\2\u01dcz\3\2\2"+
		"\2\u01dd\u01de\5\u0091I\2\u01de\u01df\5\u00b7\\\2\u01df\u01e0\5\u0099"+
		"M\2\u01e0\u01e1\5\u00afX\2\u01e1|\3\2\2\2\u01e2\u01e6\t\2\2\2\u01e3\u01e5"+
		"\t\3\2\2\u01e4\u01e3\3\2\2\2\u01e5\u01e8\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e6"+
		"\u01e7\3\2\2\2\u01e7~\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e9\u01eb\5\u0085"+
		"C\2\u01ea\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec"+
		"\u01ed\3\2\2\2\u01ed\u01f5\3\2\2\2\u01ee\u01f2\7\60\2\2\u01ef\u01f1\5"+
		"\u0085C\2\u01f0\u01ef\3\2\2\2\u01f1\u01f4\3\2\2\2\u01f2\u01f0\3\2\2\2"+
		"\u01f2\u01f3\3\2\2\2\u01f3\u01f6\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f5\u01ee"+
		"\3\2\2\2\u01f5\u01f6\3\2\2\2\u01f6\u01fe\3\2\2\2\u01f7\u01f9\7\60\2\2"+
		"\u01f8\u01fa\5\u0085C\2\u01f9\u01f8\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb"+
		"\u01f9\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc\u01fe\3\2\2\2\u01fd\u01ea\3\2"+
		"\2\2\u01fd\u01f7\3\2\2\2\u01fe\u0080\3\2\2\2\u01ff\u0205\7)\2\2\u0200"+
		"\u0204\n\4\2\2\u0201\u0202\7)\2\2\u0202\u0204\7)\2\2\u0203\u0200\3\2\2"+
		"\2\u0203\u0201\3\2\2\2\u0204\u0207\3\2\2\2\u0205\u0203\3\2\2\2\u0205\u0206"+
		"\3\2\2\2\u0206\u0208\3\2\2\2\u0207\u0205\3\2\2\2\u0208\u0209\7)\2\2\u0209"+
		"\u0082\3\2\2\2\u020a\u020b\t\5\2\2\u020b\u020c\3\2\2\2\u020c\u020d\bB"+
		"\2\2\u020d\u0084\3\2\2\2\u020e\u020f\t\6\2\2\u020f\u0086\3\2\2\2\u0210"+
		"\u0211\t\7\2\2\u0211\u0088\3\2\2\2\u0212\u0213\t\b\2\2\u0213\u008a\3\2"+
		"\2\2\u0214\u0215\t\t\2\2\u0215\u008c\3\2\2\2\u0216\u0217\t\n\2\2\u0217"+
		"\u008e\3\2\2\2\u0218\u0219\t\13\2\2\u0219\u0090\3\2\2\2\u021a\u021b\t"+
		"\f\2\2\u021b\u0092\3\2\2\2\u021c\u021d\t\r\2\2\u021d\u0094\3\2\2\2\u021e"+
		"\u021f\t\16\2\2\u021f\u0096\3\2\2\2\u0220\u0221\t\17\2\2\u0221\u0098\3"+
		"\2\2\2\u0222\u0223\t\20\2\2\u0223\u009a\3\2\2\2\u0224\u0225\t\21\2\2\u0225"+
		"\u009c\3\2\2\2\u0226\u0227\t\22\2\2\u0227\u009e\3\2\2\2\u0228\u0229\t"+
		"\23\2\2\u0229\u00a0\3\2\2\2\u022a\u022b\t\24\2\2\u022b\u00a2\3\2\2\2\u022c"+
		"\u022d\t\25\2\2\u022d\u00a4\3\2\2\2\u022e\u022f\t\26\2\2\u022f\u00a6\3"+
		"\2\2\2\u0230\u0231\t\27\2\2\u0231\u00a8\3\2\2\2\u0232\u0233\t\30\2\2\u0233"+
		"\u00aa\3\2\2\2\u0234\u0235\t\31\2\2\u0235\u00ac\3\2\2\2\u0236\u0237\t"+
		"\32\2\2\u0237\u00ae\3\2\2\2\u0238\u0239\t\33\2\2\u0239\u00b0\3\2\2\2\u023a"+
		"\u023b\t\34\2\2\u023b\u00b2\3\2\2\2\u023c\u023d\t\35\2\2\u023d\u00b4\3"+
		"\2\2\2\u023e\u023f\t\36\2\2\u023f\u00b6\3\2\2\2\u0240\u0241\t\37\2\2\u0241"+
		"\u00b8\3\2\2\2\u0242\u0243\t \2\2\u0243\u00ba\3\2\2\2\u0244\u0245\t!\2"+
		"\2\u0245\u00bc\3\2\2\2\13\2\u01e6\u01ec\u01f2\u01f5\u01fb\u01fd\u0203"+
		"\u0205\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}