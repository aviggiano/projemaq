package antlrp;// Generated from Gcode.4g by ANTLR 4.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GcodeLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__12=1, T__11=2, T__10=3, T__9=4, T__8=5, T__7=6, T__6=7, T__5=8, T__4=9, 
		T__3=10, T__2=11, T__1=12, T__0=13, MFIM=14, SIGN=15, INT=16, ID=17, WS=18, 
		STRING=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'G00'", "'P'", "'G96'", "'.'", "'G01'", "'G76'", "'Q'", "'F'", "'\r'", 
		"'X'", "'S'", "'N'", "'Z'", "MFIM", "SIGN", "INT", "ID", "WS", "STRING"
	};
	public static final String[] ruleNames = {
		"T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", 
		"T__3", "T__2", "T__1", "T__0", "MFIM", "SIGN", "INT", "ID", "WS", "STRING", 
		"HEX_DIGIT", "ESC_SEQ", "OCTAL_ESC", "UNICODE_ESC"
	};


	public GcodeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Gcode.4g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 17: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\25\u0089\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17Z\n\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\7\24i\n\24\f\24"+
		"\16\24l\13\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\5\26v\n\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0081\n\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\2\31\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1"+
		"\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23"+
		"\1%\24\2\'\25\1)\2\1+\2\1-\2\1/\2\1\3\2\7\4--//\5\f\f\"\"))\4$$^^\5\62"+
		";CHch\n$$))^^ddhhppttvv\u008b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3\61\3\2\2\2"+
		"\5\65\3\2\2\2\7\67\3\2\2\2\t;\3\2\2\2\13=\3\2\2\2\rA\3\2\2\2\17E\3\2\2"+
		"\2\21G\3\2\2\2\23I\3\2\2\2\25K\3\2\2\2\27M\3\2\2\2\31O\3\2\2\2\33Q\3\2"+
		"\2\2\35Y\3\2\2\2\37[\3\2\2\2!]\3\2\2\2#_\3\2\2\2%a\3\2\2\2\'e\3\2\2\2"+
		")o\3\2\2\2+u\3\2\2\2-\u0080\3\2\2\2/\u0082\3\2\2\2\61\62\7I\2\2\62\63"+
		"\7\62\2\2\63\64\7\62\2\2\64\4\3\2\2\2\65\66\7R\2\2\66\6\3\2\2\2\678\7"+
		"I\2\289\7;\2\29:\78\2\2:\b\3\2\2\2;<\7\60\2\2<\n\3\2\2\2=>\7I\2\2>?\7"+
		"\62\2\2?@\7\63\2\2@\f\3\2\2\2AB\7I\2\2BC\79\2\2CD\78\2\2D\16\3\2\2\2E"+
		"F\7S\2\2F\20\3\2\2\2GH\7H\2\2H\22\3\2\2\2IJ\7\17\2\2J\24\3\2\2\2KL\7Z"+
		"\2\2L\26\3\2\2\2MN\7U\2\2N\30\3\2\2\2OP\7P\2\2P\32\3\2\2\2QR\7\\\2\2R"+
		"\34\3\2\2\2ST\7O\2\2TU\7\65\2\2UZ\7\62\2\2VW\7O\2\2WX\7\62\2\2XZ\7\64"+
		"\2\2YS\3\2\2\2YV\3\2\2\2Z\36\3\2\2\2[\\\t\2\2\2\\ \3\2\2\2]^\4\62;\2^"+
		"\"\3\2\2\2_`\4c|\2`$\3\2\2\2ab\t\3\2\2bc\3\2\2\2cd\b\23\2\2d&\3\2\2\2"+
		"ej\7$\2\2fi\5+\26\2gi\n\4\2\2hf\3\2\2\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2"+
		"jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7$\2\2n(\3\2\2\2op\t\5\2\2p*\3\2\2\2"+
		"qr\7^\2\2rv\t\6\2\2sv\5/\30\2tv\5-\27\2uq\3\2\2\2us\3\2\2\2ut\3\2\2\2"+
		"v,\3\2\2\2wx\7^\2\2xy\4\62\65\2yz\4\629\2z\u0081\4\629\2{|\7^\2\2|}\4"+
		"\629\2}\u0081\4\629\2~\177\7^\2\2\177\u0081\4\629\2\u0080w\3\2\2\2\u0080"+
		"{\3\2\2\2\u0080~\3\2\2\2\u0081.\3\2\2\2\u0082\u0083\7^\2\2\u0083\u0084"+
		"\7w\2\2\u0084\u0085\5)\25\2\u0085\u0086\5)\25\2\u0086\u0087\5)\25\2\u0087"+
		"\u0088\5)\25\2\u0088\60\3\2\2\2\b\2Yhju\u0080";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}