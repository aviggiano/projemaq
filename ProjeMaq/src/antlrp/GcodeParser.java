package antlrp;// Generated from Gcode.4g by ANTLR 4.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GcodeParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__12=1, T__11=2, T__10=3, T__9=4, T__8=5, T__7=6, T__6=7, T__5=8, T__4=9, 
		T__3=10, T__2=11, T__1=12, T__0=13, MFIM=14, SIGN=15, INT=16, ID=17, WS=18, 
		STRING=19;
	public static final String[] tokenNames = {
		"<INVALID>", "'G00'", "'P'", "'G96'", "'.'", "'G01'", "'G76'", "'Q'", 
		"'F'", "'\r'", "'X'", "'S'", "'N'", "'Z'", "MFIM", "SIGN", "INT", "ID", 
		"WS", "STRING"
	};
	public static final int
		RULE_def = 0, RULE_fimprograma = 1, RULE_statement = 2, RULE_decimal3p2 = 3, 
		RULE_decimal2p2 = 4, RULE_decimal1p1 = 5, RULE_numerolinha = 6, RULE_coord = 7, 
		RULE_coordx = 8, RULE_coordz = 9, RULE_avanco = 10, RULE_fimdelinha = 11, 
		RULE_g00 = 12, RULE_g01 = 13, RULE_g76 = 14, RULE_g96 = 15;
	public static final String[] ruleNames = {
		"def", "fimprograma", "statement", "decimal3p2", "decimal2p2", "decimal1p1", 
		"numerolinha", "coord", "coordx", "coordz", "avanco", "fimdelinha", "g00", 
		"g01", "g76", "g96"
	};

	@Override
	public String getGrammarFileName() { return "Gcode.4g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public GcodeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DefContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FimprogramaContext fimprograma() {
			return getRuleContext(FimprogramaContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_def);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(32); statement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(35); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(37); fimprograma();
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

	public static class FimprogramaContext extends ParserRuleContext {
		public TerminalNode MFIM() { return getToken(GcodeParser.MFIM, 0); }
		public NumerolinhaContext numerolinha() {
			return getRuleContext(NumerolinhaContext.class,0);
		}
		public FimprogramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fimprograma; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterFimprograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitFimprograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitFimprograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FimprogramaContext fimprograma() throws RecognitionException {
		FimprogramaContext _localctx = new FimprogramaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_fimprograma);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); match(12);
			setState(40); numerolinha();
			setState(41); match(MFIM);
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

	public static class StatementContext extends ParserRuleContext {
		public FimdelinhaContext fimdelinha() {
			return getRuleContext(FimdelinhaContext.class,0);
		}
		public G96Context g96() {
			return getRuleContext(G96Context.class,0);
		}
		public G76Context g76() {
			return getRuleContext(G76Context.class,0);
		}
		public G00Context g00() {
			return getRuleContext(G00Context.class,0);
		}
		public G01Context g01() {
			return getRuleContext(G01Context.class,0);
		}
		public NumerolinhaContext numerolinha() {
			return getRuleContext(NumerolinhaContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(64);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43); match(12);
				setState(44); numerolinha();
				setState(45); g00();
				setState(46); fimdelinha();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48); match(12);
				setState(49); numerolinha();
				setState(50); g01();
				setState(51); fimdelinha();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(53); match(12);
				setState(54); numerolinha();
				setState(55); g76();
				setState(56); fimdelinha();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(58); match(12);
				setState(59); numerolinha();
				setState(60); g96();
				setState(61); fimdelinha();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(63); fimdelinha();
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

	public static class Decimal3p2Context extends ParserRuleContext {
		public TerminalNode SIGN() { return getToken(GcodeParser.SIGN, 0); }
		public List<TerminalNode> INT() { return getTokens(GcodeParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(GcodeParser.INT, i);
		}
		public Decimal3p2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal3p2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterDecimal3p2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitDecimal3p2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitDecimal3p2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal3p2Context decimal3p2() throws RecognitionException {
		Decimal3p2Context _localctx = new Decimal3p2Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_decimal3p2);
		int _la;
		try {
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				_la = _input.LA(1);
				if (_la==SIGN) {
					{
					setState(66); match(SIGN);
					}
				}

				setState(69); match(INT);
				setState(71);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(70); match(INT);
					}
					break;
				}
				setState(74);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(73); match(INT);
					}
				}

				setState(76); match(4);
				setState(78);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(77); match(INT);
					}
					break;
				}
				setState(81);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(80); match(INT);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				_la = _input.LA(1);
				if (_la==SIGN) {
					{
					setState(83); match(SIGN);
					}
				}

				setState(86); match(4);
				setState(87); match(INT);
				setState(89);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(88); match(INT);
					}
				}

				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				_la = _input.LA(1);
				if (_la==SIGN) {
					{
					setState(91); match(SIGN);
					}
				}

				setState(94); match(INT);
				setState(96);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(95); match(INT);
					}
					break;
				}
				setState(99);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(98); match(INT);
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

	public static class Decimal2p2Context extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(GcodeParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(GcodeParser.INT, i);
		}
		public Decimal2p2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal2p2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterDecimal2p2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitDecimal2p2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitDecimal2p2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal2p2Context decimal2p2() throws RecognitionException {
		Decimal2p2Context _localctx = new Decimal2p2Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_decimal2p2);
		int _la;
		try {
			setState(123);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(103); match(INT);
				setState(105);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(104); match(INT);
					}
				}

				setState(107); match(4);
				setState(109);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(108); match(INT);
					}
					break;
				}
				setState(112);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(111); match(INT);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(114); match(4);
				setState(115); match(INT);
				setState(117);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(116); match(INT);
					}
				}

				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119); match(INT);
				setState(121);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(120); match(INT);
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

	public static class Decimal1p1Context extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(GcodeParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(GcodeParser.INT, i);
		}
		public Decimal1p1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal1p1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterDecimal1p1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitDecimal1p1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitDecimal1p1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal1p1Context decimal1p1() throws RecognitionException {
		Decimal1p1Context _localctx = new Decimal1p1Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_decimal1p1);
		int _la;
		try {
			setState(132);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(125); match(INT);
				setState(126); match(4);
				setState(128);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(127); match(INT);
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 2);
				{
				setState(130); match(4);
				setState(131); match(INT);
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

	public static class NumerolinhaContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(GcodeParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(GcodeParser.INT, i);
		}
		public NumerolinhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerolinha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterNumerolinha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitNumerolinha(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitNumerolinha(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumerolinhaContext numerolinha() throws RecognitionException {
		NumerolinhaContext _localctx = new NumerolinhaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_numerolinha);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); match(INT);
			setState(135); match(INT);
			setState(136); match(INT);
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

	public static class CoordContext extends ParserRuleContext {
		public Decimal3p2Context decimal3p2() {
			return getRuleContext(Decimal3p2Context.class,0);
		}
		public CoordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterCoord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitCoord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitCoord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CoordContext coord() throws RecognitionException {
		CoordContext _localctx = new CoordContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_coord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); decimal3p2();
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

	public static class CoordxContext extends ParserRuleContext {
		public CoordContext coord() {
			return getRuleContext(CoordContext.class,0);
		}
		public CoordxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordx; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterCoordx(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitCoordx(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitCoordx(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CoordxContext coordx() throws RecognitionException {
		CoordxContext _localctx = new CoordxContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_coordx);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); match(10);
			setState(141); coord();
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

	public static class CoordzContext extends ParserRuleContext {
		public CoordContext coord() {
			return getRuleContext(CoordContext.class,0);
		}
		public CoordzContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordz; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterCoordz(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitCoordz(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitCoordz(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CoordzContext coordz() throws RecognitionException {
		CoordzContext _localctx = new CoordzContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_coordz);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143); match(13);
			setState(144); coord();
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

	public static class AvancoContext extends ParserRuleContext {
		public Decimal2p2Context decimal2p2() {
			return getRuleContext(Decimal2p2Context.class,0);
		}
		public AvancoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_avanco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterAvanco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitAvanco(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitAvanco(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AvancoContext avanco() throws RecognitionException {
		AvancoContext _localctx = new AvancoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_avanco);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146); match(8);
			setState(147); decimal2p2();
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

	public static class FimdelinhaContext extends ParserRuleContext {
		public FimdelinhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fimdelinha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterFimdelinha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitFimdelinha(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitFimdelinha(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FimdelinhaContext fimdelinha() throws RecognitionException {
		FimdelinhaContext _localctx = new FimdelinhaContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fimdelinha);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(9);
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

	public static class G00Context extends ParserRuleContext {
		public CoordzContext coordz() {
			return getRuleContext(CoordzContext.class,0);
		}
		public CoordxContext coordx() {
			return getRuleContext(CoordxContext.class,0);
		}
		public G00Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g00; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterG00(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitG00(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitG00(this);
			else return visitor.visitChildren(this);
		}
	}

	public final G00Context g00() throws RecognitionException {
		G00Context _localctx = new G00Context(_ctx, getState());
		enterRule(_localctx, 24, RULE_g00);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); match(1);
			setState(152); coordx();
			setState(153); coordz();
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

	public static class G01Context extends ParserRuleContext {
		public CoordzContext coordz() {
			return getRuleContext(CoordzContext.class,0);
		}
		public AvancoContext avanco() {
			return getRuleContext(AvancoContext.class,0);
		}
		public CoordxContext coordx() {
			return getRuleContext(CoordxContext.class,0);
		}
		public G01Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g01; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterG01(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitG01(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitG01(this);
			else return visitor.visitChildren(this);
		}
	}

	public final G01Context g01() throws RecognitionException {
		G01Context _localctx = new G01Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_g01);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155); match(5);
			setState(156); coordx();
			setState(157); coordz();
			setState(158); avanco();
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

	public static class G76Context extends ParserRuleContext {
		public Decimal1p1Context decimal1p1() {
			return getRuleContext(Decimal1p1Context.class,0);
		}
		public CoordzContext coordz() {
			return getRuleContext(CoordzContext.class,0);
		}
		public AvancoContext avanco() {
			return getRuleContext(AvancoContext.class,0);
		}
		public CoordxContext coordx() {
			return getRuleContext(CoordxContext.class,0);
		}
		public Decimal2p2Context decimal2p2() {
			return getRuleContext(Decimal2p2Context.class,0);
		}
		public G76Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g76; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterG76(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitG76(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitG76(this);
			else return visitor.visitChildren(this);
		}
	}

	public final G76Context g76() throws RecognitionException {
		G76Context _localctx = new G76Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_g76);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); match(6);
			setState(161); coordx();
			setState(162); coordz();
			setState(163); match(2);
			setState(164); decimal2p2();
			setState(165); match(7);
			setState(166); decimal1p1();
			setState(167); avanco();
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

	public static class G96Context extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(GcodeParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(GcodeParser.INT, i);
		}
		public G96Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g96; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).enterG96(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GcodeListener ) ((GcodeListener)listener).exitG96(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GcodeVisitor ) return ((GcodeVisitor<? extends T>)visitor).visitG96(this);
			else return visitor.visitChildren(this);
		}
	}

	public final G96Context g96() throws RecognitionException {
		G96Context _localctx = new G96Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_g96);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); match(3);
			setState(170); match(11);
			setState(172);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(171); match(INT);
				}
				break;
			}
			setState(174); match(INT);
			setState(175); match(INT);
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

	public static final String _serializedATN =
		"\2\3\25\u00b4\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\3\2\6\2$\n\2\r\2\16\2%\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\5\4C\n\4\3\5\5\5F\n\5\3\5\3\5\5\5J\n\5\3\5\5\5M\n\5\3\5\3\5\5\5Q"+
		"\n\5\3\5\5\5T\n\5\3\5\5\5W\n\5\3\5\3\5\3\5\5\5\\\n\5\3\5\5\5_\n\5\3\5"+
		"\3\5\5\5c\n\5\3\5\5\5f\n\5\5\5h\n\5\3\6\3\6\5\6l\n\6\3\6\3\6\5\6p\n\6"+
		"\3\6\5\6s\n\6\3\6\3\6\3\6\5\6x\n\6\3\6\3\6\5\6|\n\6\5\6~\n\6\3\7\3\7\3"+
		"\7\5\7\u0083\n\7\3\7\3\7\5\7\u0087\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\5\21\u00af\n\21\3\21\3\21\3\21\3\21\2\22\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \2\2\u00be\2#\3\2\2\2\4)\3\2\2\2\6B\3\2\2\2\bg\3\2\2\2"+
		"\n}\3\2\2\2\f\u0086\3\2\2\2\16\u0088\3\2\2\2\20\u008c\3\2\2\2\22\u008e"+
		"\3\2\2\2\24\u0091\3\2\2\2\26\u0094\3\2\2\2\30\u0097\3\2\2\2\32\u0099\3"+
		"\2\2\2\34\u009d\3\2\2\2\36\u00a2\3\2\2\2 \u00ab\3\2\2\2\"$\5\6\4\2#\""+
		"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\5\4\3\2(\3\3\2\2"+
		"\2)*\7\16\2\2*+\5\16\b\2+,\7\20\2\2,\5\3\2\2\2-.\7\16\2\2./\5\16\b\2/"+
		"\60\5\32\16\2\60\61\5\30\r\2\61C\3\2\2\2\62\63\7\16\2\2\63\64\5\16\b\2"+
		"\64\65\5\34\17\2\65\66\5\30\r\2\66C\3\2\2\2\678\7\16\2\289\5\16\b\29:"+
		"\5\36\20\2:;\5\30\r\2;C\3\2\2\2<=\7\16\2\2=>\5\16\b\2>?\5 \21\2?@\5\30"+
		"\r\2@C\3\2\2\2AC\5\30\r\2B-\3\2\2\2B\62\3\2\2\2B\67\3\2\2\2B<\3\2\2\2"+
		"BA\3\2\2\2C\7\3\2\2\2DF\7\21\2\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2\2GI\7\22"+
		"\2\2HJ\7\22\2\2IH\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KM\7\22\2\2LK\3\2\2\2LM\3"+
		"\2\2\2MN\3\2\2\2NP\7\6\2\2OQ\7\22\2\2PO\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RT"+
		"\7\22\2\2SR\3\2\2\2ST\3\2\2\2Th\3\2\2\2UW\7\21\2\2VU\3\2\2\2VW\3\2\2\2"+
		"WX\3\2\2\2XY\7\6\2\2Y[\7\22\2\2Z\\\7\22\2\2[Z\3\2\2\2[\\\3\2\2\2\\h\3"+
		"\2\2\2]_\7\21\2\2^]\3\2\2\2^_\3\2\2\2_`\3\2\2\2`b\7\22\2\2ac\7\22\2\2"+
		"ba\3\2\2\2bc\3\2\2\2ce\3\2\2\2df\7\22\2\2ed\3\2\2\2ef\3\2\2\2fh\3\2\2"+
		"\2gE\3\2\2\2gV\3\2\2\2g^\3\2\2\2h\t\3\2\2\2ik\7\22\2\2jl\7\22\2\2kj\3"+
		"\2\2\2kl\3\2\2\2lm\3\2\2\2mo\7\6\2\2np\7\22\2\2on\3\2\2\2op\3\2\2\2pr"+
		"\3\2\2\2qs\7\22\2\2rq\3\2\2\2rs\3\2\2\2s~\3\2\2\2tu\7\6\2\2uw\7\22\2\2"+
		"vx\7\22\2\2wv\3\2\2\2wx\3\2\2\2x~\3\2\2\2y{\7\22\2\2z|\7\22\2\2{z\3\2"+
		"\2\2{|\3\2\2\2|~\3\2\2\2}i\3\2\2\2}t\3\2\2\2}y\3\2\2\2~\13\3\2\2\2\177"+
		"\u0080\7\22\2\2\u0080\u0082\7\6\2\2\u0081\u0083\7\22\2\2\u0082\u0081\3"+
		"\2\2\2\u0082\u0083\3\2\2\2\u0083\u0087\3\2\2\2\u0084\u0085\7\6\2\2\u0085"+
		"\u0087\7\22\2\2\u0086\177\3\2\2\2\u0086\u0084\3\2\2\2\u0087\r\3\2\2\2"+
		"\u0088\u0089\7\22\2\2\u0089\u008a\7\22\2\2\u008a\u008b\7\22\2\2\u008b"+
		"\17\3\2\2\2\u008c\u008d\5\b\5\2\u008d\21\3\2\2\2\u008e\u008f\7\f\2\2\u008f"+
		"\u0090\5\20\t\2\u0090\23\3\2\2\2\u0091\u0092\7\17\2\2\u0092\u0093\5\20"+
		"\t\2\u0093\25\3\2\2\2\u0094\u0095\7\n\2\2\u0095\u0096\5\n\6\2\u0096\27"+
		"\3\2\2\2\u0097\u0098\7\13\2\2\u0098\31\3\2\2\2\u0099\u009a\7\3\2\2\u009a"+
		"\u009b\5\22\n\2\u009b\u009c\5\24\13\2\u009c\33\3\2\2\2\u009d\u009e\7\7"+
		"\2\2\u009e\u009f\5\22\n\2\u009f\u00a0\5\24\13\2\u00a0\u00a1\5\26\f\2\u00a1"+
		"\35\3\2\2\2\u00a2\u00a3\7\b\2\2\u00a3\u00a4\5\22\n\2\u00a4\u00a5\5\24"+
		"\13\2\u00a5\u00a6\7\4\2\2\u00a6\u00a7\5\n\6\2\u00a7\u00a8\7\t\2\2\u00a8"+
		"\u00a9\5\f\7\2\u00a9\u00aa\5\26\f\2\u00aa\37\3\2\2\2\u00ab\u00ac\7\5\2"+
		"\2\u00ac\u00ae\7\r\2\2\u00ad\u00af\7\22\2\2\u00ae\u00ad\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\7\22\2\2\u00b1\u00b2\7"+
		"\22\2\2\u00b2!\3\2\2\2\30%BEILPSV[^begkorw{}\u0082\u0086\u00ae";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}