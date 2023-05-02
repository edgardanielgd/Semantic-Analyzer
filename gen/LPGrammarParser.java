// Generated from D:/UNALArchivo/Lenguajes de Programación/SemanticAnalyzer/grammar\LPGrammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LPGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, ENDIF=3, END=4, SUB=5, TEXTWINDOW=6, THEN=7, ENDSUB=8, ARRAY=9, 
		ELSEIF=10, FOR=11, WHILE=12, ENDWHILE=13, TO=14, ENDFOR=15, GOTO=16, PROGRAM=17, 
		STACK=18, STEP=19, CLOCK=20, AND=21, OR=22, PLUS=23, MINUS=24, MULTIPLY=25, 
		DIVIDE=26, LEQ=27, GEQ=28, LOWER=29, GREATER=30, EQUAL=31, DIFF=32, COMMA=33, 
		SEMICOLON=34, COLON=35, LPAREN=36, RPAREN=37, LBRACKET=38, RBRACKET=39, 
		PERIOD=40, IDENTIFIER=41, NUMBER=42, TEXT=43, COMMENT=44, TRUE=45, FALSE=46, 
		SPACE=47;
	public static final int
		RULE_s = 0, RULE_functiondeclaration = 1, RULE_ifdeclaration = 2, RULE_ifcontinuation = 3, 
		RULE_whiledeclaration = 4, RULE_fordeclaration = 5, RULE_fordeclarationcomp = 6, 
		RULE_labelcall = 7, RULE_statement = 8, RULE_statementcomp = 9, RULE_arrayaccessor = 10, 
		RULE_mainstatement = 11, RULE_mainstatementscomp = 12, RULE_expression = 13, 
		RULE_actualexpression = 14, RULE_expressionhelper = 15, RULE_notcomparatorexp = 16, 
		RULE_notcomparatorexphelper = 17, RULE_subexpression = 18, RULE_operator = 19, 
		RULE_andoroperator = 20, RULE_comparator = 21, RULE_specialcall = 22, 
		RULE_specialcallkeyword = 23, RULE_arguments = 24, RULE_optionalarg = 25, 
		RULE_furtherarguments = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"s", "functiondeclaration", "ifdeclaration", "ifcontinuation", "whiledeclaration", 
			"fordeclaration", "fordeclarationcomp", "labelcall", "statement", "statementcomp", 
			"arrayaccessor", "mainstatement", "mainstatementscomp", "expression", 
			"actualexpression", "expressionhelper", "notcomparatorexp", "notcomparatorexphelper", 
			"subexpression", "operator", "andoroperator", "comparator", "specialcall", 
			"specialcallkeyword", "arguments", "optionalarg", "furtherarguments"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'If'", "'Else'", "'EndIf'", "'End'", "'Sub'", "'TextWindow'", 
			"'Then'", "'EndSub'", "'Array'", "'ElseIf'", "'For'", "'While'", "'EndWhile'", 
			"'To'", "'EndFor'", "'Goto'", "'Program'", "'Stack'", "'Step'", "'Clock'", 
			"'And'", "'Or'", "'+'", "'-'", "'*'", "'/'", "'<='", "'>='", "'<'", "'>'", 
			"'='", "'<>'", "','", "';'", "':'", "'('", "')'", "'['", "']'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "ENDIF", "END", "SUB", "TEXTWINDOW", "THEN", "ENDSUB", 
			"ARRAY", "ELSEIF", "FOR", "WHILE", "ENDWHILE", "TO", "ENDFOR", "GOTO", 
			"PROGRAM", "STACK", "STEP", "CLOCK", "AND", "OR", "PLUS", "MINUS", "MULTIPLY", 
			"DIVIDE", "LEQ", "GEQ", "LOWER", "GREATER", "EQUAL", "DIFF", "COMMA", 
			"SEMICOLON", "COLON", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "PERIOD", 
			"IDENTIFIER", "NUMBER", "TEXT", "COMMENT", "TRUE", "FALSE", "SPACE"
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
	public String getGrammarFileName() { return "LPGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LPGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SContext extends ParserRuleContext {
		public MainstatementContext mainstatement() {
			return getRuleContext(MainstatementContext.class,0);
		}
		public SContext s() {
			return getRuleContext(SContext.class,0);
		}
		public TerminalNode EOF() { return getToken(LPGrammarParser.EOF, 0); }
		public SContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_s);
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case SUB:
			case TEXTWINDOW:
			case ARRAY:
			case FOR:
			case WHILE:
			case GOTO:
			case PROGRAM:
			case STACK:
			case CLOCK:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				mainstatement();
				setState(55);
				s();
				setState(56);
				match(EOF);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctiondeclarationContext extends ParserRuleContext {
		public TerminalNode SUB() { return getToken(LPGrammarParser.SUB, 0); }
		public TerminalNode IDENTIFIER() { return getToken(LPGrammarParser.IDENTIFIER, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode ENDSUB() { return getToken(LPGrammarParser.ENDSUB, 0); }
		public FunctiondeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functiondeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterFunctiondeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitFunctiondeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitFunctiondeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctiondeclarationContext functiondeclaration() throws RecognitionException {
		FunctiondeclarationContext _localctx = new FunctiondeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_functiondeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(SUB);
			setState(62);
			match(IDENTIFIER);
			setState(63);
			statement();
			setState(64);
			match(ENDSUB);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IfdeclarationContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(LPGrammarParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(LPGrammarParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LPGrammarParser.RPAREN, 0); }
		public TerminalNode THEN() { return getToken(LPGrammarParser.THEN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IfcontinuationContext ifcontinuation() {
			return getRuleContext(IfcontinuationContext.class,0);
		}
		public IfdeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifdeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterIfdeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitIfdeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitIfdeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfdeclarationContext ifdeclaration() throws RecognitionException {
		IfdeclarationContext _localctx = new IfdeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ifdeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(IF);
			setState(67);
			match(LPAREN);
			setState(68);
			expression();
			setState(69);
			match(RPAREN);
			setState(70);
			match(THEN);
			setState(71);
			statement();
			setState(72);
			ifcontinuation();
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

	@SuppressWarnings("CheckReturnValue")
	public static class IfcontinuationContext extends ParserRuleContext {
		public TerminalNode ENDIF() { return getToken(LPGrammarParser.ENDIF, 0); }
		public TerminalNode ELSE() { return getToken(LPGrammarParser.ELSE, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode ELSEIF() { return getToken(LPGrammarParser.ELSEIF, 0); }
		public TerminalNode LPAREN() { return getToken(LPGrammarParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LPGrammarParser.RPAREN, 0); }
		public TerminalNode THEN() { return getToken(LPGrammarParser.THEN, 0); }
		public IfcontinuationContext ifcontinuation() {
			return getRuleContext(IfcontinuationContext.class,0);
		}
		public IfcontinuationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifcontinuation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterIfcontinuation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitIfcontinuation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitIfcontinuation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfcontinuationContext ifcontinuation() throws RecognitionException {
		IfcontinuationContext _localctx = new IfcontinuationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifcontinuation);
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ENDIF:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(ENDIF);
				}
				break;
			case ELSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				match(ELSE);
				setState(76);
				statement();
				setState(77);
				match(ENDIF);
				}
				break;
			case ELSEIF:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				match(ELSEIF);
				setState(80);
				match(LPAREN);
				setState(81);
				expression();
				setState(82);
				match(RPAREN);
				setState(83);
				match(THEN);
				setState(84);
				statement();
				setState(85);
				ifcontinuation();
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

	@SuppressWarnings("CheckReturnValue")
	public static class WhiledeclarationContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(LPGrammarParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(LPGrammarParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LPGrammarParser.RPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode ENDWHILE() { return getToken(LPGrammarParser.ENDWHILE, 0); }
		public WhiledeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whiledeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterWhiledeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitWhiledeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitWhiledeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhiledeclarationContext whiledeclaration() throws RecognitionException {
		WhiledeclarationContext _localctx = new WhiledeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_whiledeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(WHILE);
			setState(90);
			match(LPAREN);
			setState(91);
			expression();
			setState(92);
			match(RPAREN);
			setState(93);
			statement();
			setState(94);
			match(ENDWHILE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FordeclarationContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(LPGrammarParser.FOR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(LPGrammarParser.IDENTIFIER, 0); }
		public TerminalNode EQUAL() { return getToken(LPGrammarParser.EQUAL, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode TO() { return getToken(LPGrammarParser.TO, 0); }
		public FordeclarationcompContext fordeclarationcomp() {
			return getRuleContext(FordeclarationcompContext.class,0);
		}
		public FordeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fordeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterFordeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitFordeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitFordeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FordeclarationContext fordeclaration() throws RecognitionException {
		FordeclarationContext _localctx = new FordeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fordeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(FOR);
			setState(97);
			match(IDENTIFIER);
			setState(98);
			match(EQUAL);
			setState(99);
			expression();
			setState(100);
			match(TO);
			setState(101);
			expression();
			setState(102);
			fordeclarationcomp();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FordeclarationcompContext extends ParserRuleContext {
		public TerminalNode STEP() { return getToken(LPGrammarParser.STEP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode ENDFOR() { return getToken(LPGrammarParser.ENDFOR, 0); }
		public FordeclarationcompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fordeclarationcomp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterFordeclarationcomp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitFordeclarationcomp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitFordeclarationcomp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FordeclarationcompContext fordeclarationcomp() throws RecognitionException {
		FordeclarationcompContext _localctx = new FordeclarationcompContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fordeclarationcomp);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STEP:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(STEP);
				setState(105);
				expression();
				setState(106);
				statement();
				setState(107);
				match(ENDFOR);
				}
				break;
			case IF:
			case TEXTWINDOW:
			case ARRAY:
			case FOR:
			case WHILE:
			case GOTO:
			case PROGRAM:
			case STACK:
			case CLOCK:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				statement();
				setState(110);
				match(ENDFOR);
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

	@SuppressWarnings("CheckReturnValue")
	public static class LabelcallContext extends ParserRuleContext {
		public TerminalNode GOTO() { return getToken(LPGrammarParser.GOTO, 0); }
		public TerminalNode IDENTIFIER() { return getToken(LPGrammarParser.IDENTIFIER, 0); }
		public LabelcallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelcall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterLabelcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitLabelcall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitLabelcall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelcallContext labelcall() throws RecognitionException {
		LabelcallContext _localctx = new LabelcallContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_labelcall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(GOTO);
			setState(115);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LPGrammarParser.IDENTIFIER, 0); }
		public StatementcompContext statementcomp() {
			return getRuleContext(StatementcompContext.class,0);
		}
		public IfdeclarationContext ifdeclaration() {
			return getRuleContext(IfdeclarationContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhiledeclarationContext whiledeclaration() {
			return getRuleContext(WhiledeclarationContext.class,0);
		}
		public FordeclarationContext fordeclaration() {
			return getRuleContext(FordeclarationContext.class,0);
		}
		public LabelcallContext labelcall() {
			return getRuleContext(LabelcallContext.class,0);
		}
		public SpecialcallContext specialcall() {
			return getRuleContext(SpecialcallContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				match(IDENTIFIER);
				setState(118);
				statementcomp();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				ifdeclaration();
				setState(120);
				statement();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				whiledeclaration();
				setState(123);
				statement();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				fordeclaration();
				setState(126);
				statement();
				}
				break;
			case GOTO:
				enterOuterAlt(_localctx, 5);
				{
				setState(128);
				labelcall();
				setState(129);
				statement();
				}
				break;
			case TEXTWINDOW:
			case ARRAY:
			case PROGRAM:
			case STACK:
			case CLOCK:
				enterOuterAlt(_localctx, 6);
				{
				setState(131);
				specialcall();
				setState(132);
				statement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementcompContext extends ParserRuleContext {
		public ArrayaccessorContext arrayaccessor() {
			return getRuleContext(ArrayaccessorContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(LPGrammarParser.EQUAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(LPGrammarParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LPGrammarParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(LPGrammarParser.COLON, 0); }
		public StatementcompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementcomp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterStatementcomp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitStatementcomp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitStatementcomp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementcompContext statementcomp() throws RecognitionException {
		StatementcompContext _localctx = new StatementcompContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statementcomp);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUAL:
			case LBRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				arrayaccessor();
				setState(137);
				match(EQUAL);
				setState(138);
				expression();
				setState(139);
				statement();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				match(LPAREN);
				setState(142);
				expression();
				setState(143);
				match(RPAREN);
				setState(144);
				statement();
				}
				break;
			case COLON:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				match(COLON);
				setState(147);
				statement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayaccessorContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(LPGrammarParser.LBRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(LPGrammarParser.RBRACKET, 0); }
		public ArrayaccessorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayaccessor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterArrayaccessor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitArrayaccessor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitArrayaccessor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayaccessorContext arrayaccessor() throws RecognitionException {
		ArrayaccessorContext _localctx = new ArrayaccessorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arrayaccessor);
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(LBRACKET);
				setState(151);
				expression();
				setState(152);
				match(RBRACKET);
				}
				break;
			case EOF:
			case IF:
			case SUB:
			case TEXTWINDOW:
			case ARRAY:
			case FOR:
			case WHILE:
			case TO:
			case GOTO:
			case PROGRAM:
			case STACK:
			case STEP:
			case CLOCK:
			case AND:
			case OR:
			case PLUS:
			case MINUS:
			case MULTIPLY:
			case DIVIDE:
			case LEQ:
			case GEQ:
			case LOWER:
			case GREATER:
			case EQUAL:
			case DIFF:
			case COMMA:
			case RPAREN:
			case RBRACKET:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class MainstatementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LPGrammarParser.IDENTIFIER, 0); }
		public MainstatementscompContext mainstatementscomp() {
			return getRuleContext(MainstatementscompContext.class,0);
		}
		public LabelcallContext labelcall() {
			return getRuleContext(LabelcallContext.class,0);
		}
		public SpecialcallContext specialcall() {
			return getRuleContext(SpecialcallContext.class,0);
		}
		public IfdeclarationContext ifdeclaration() {
			return getRuleContext(IfdeclarationContext.class,0);
		}
		public WhiledeclarationContext whiledeclaration() {
			return getRuleContext(WhiledeclarationContext.class,0);
		}
		public FordeclarationContext fordeclaration() {
			return getRuleContext(FordeclarationContext.class,0);
		}
		public FunctiondeclarationContext functiondeclaration() {
			return getRuleContext(FunctiondeclarationContext.class,0);
		}
		public MainstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterMainstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitMainstatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitMainstatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainstatementContext mainstatement() throws RecognitionException {
		MainstatementContext _localctx = new MainstatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_mainstatement);
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(IDENTIFIER);
				setState(158);
				mainstatementscomp();
				}
				break;
			case GOTO:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				labelcall();
				}
				break;
			case TEXTWINDOW:
			case ARRAY:
			case PROGRAM:
			case STACK:
			case CLOCK:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				specialcall();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(161);
				ifdeclaration();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(162);
				whiledeclaration();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 6);
				{
				setState(163);
				fordeclaration();
				}
				break;
			case SUB:
				enterOuterAlt(_localctx, 7);
				{
				setState(164);
				functiondeclaration();
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

	@SuppressWarnings("CheckReturnValue")
	public static class MainstatementscompContext extends ParserRuleContext {
		public ArrayaccessorContext arrayaccessor() {
			return getRuleContext(ArrayaccessorContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(LPGrammarParser.EQUAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(LPGrammarParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LPGrammarParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(LPGrammarParser.COLON, 0); }
		public MainstatementscompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainstatementscomp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterMainstatementscomp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitMainstatementscomp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitMainstatementscomp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainstatementscompContext mainstatementscomp() throws RecognitionException {
		MainstatementscompContext _localctx = new MainstatementscompContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_mainstatementscomp);
		try {
			setState(176);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUAL:
			case LBRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				arrayaccessor();
				setState(168);
				match(EQUAL);
				setState(169);
				expression();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				match(LPAREN);
				setState(172);
				expression();
				setState(173);
				match(RPAREN);
				}
				break;
			case COLON:
				enterOuterAlt(_localctx, 3);
				{
				setState(175);
				match(COLON);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ActualexpressionContext actualexpression() {
			return getRuleContext(ActualexpressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(LPGrammarParser.MINUS, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expression);
		try {
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTWINDOW:
			case ARRAY:
			case PROGRAM:
			case STACK:
			case CLOCK:
			case LPAREN:
			case IDENTIFIER:
			case NUMBER:
			case TEXT:
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				actualexpression();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				match(MINUS);
				setState(180);
				actualexpression();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ActualexpressionContext extends ParserRuleContext {
		public SubexpressionContext subexpression() {
			return getRuleContext(SubexpressionContext.class,0);
		}
		public ExpressionhelperContext expressionhelper() {
			return getRuleContext(ExpressionhelperContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(LPGrammarParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LPGrammarParser.RPAREN, 0); }
		public ActualexpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualexpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterActualexpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitActualexpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitActualexpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActualexpressionContext actualexpression() throws RecognitionException {
		ActualexpressionContext _localctx = new ActualexpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_actualexpression);
		try {
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTWINDOW:
			case ARRAY:
			case PROGRAM:
			case STACK:
			case CLOCK:
			case IDENTIFIER:
			case NUMBER:
			case TEXT:
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				subexpression();
				setState(184);
				expressionhelper();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				match(LPAREN);
				setState(187);
				expression();
				setState(188);
				match(RPAREN);
				setState(189);
				expressionhelper();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionhelperContext extends ParserRuleContext {
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AndoroperatorContext andoroperator() {
			return getRuleContext(AndoroperatorContext.class,0);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public NotcomparatorexpContext notcomparatorexp() {
			return getRuleContext(NotcomparatorexpContext.class,0);
		}
		public ExpressionhelperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionhelper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterExpressionhelper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitExpressionhelper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitExpressionhelper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionhelperContext expressionhelper() throws RecognitionException {
		ExpressionhelperContext _localctx = new ExpressionhelperContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expressionhelper);
		try {
			setState(203);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case MULTIPLY:
			case DIVIDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				operator();
				setState(194);
				expression();
				}
				break;
			case AND:
			case OR:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				andoroperator();
				setState(197);
				expression();
				}
				break;
			case LEQ:
			case GEQ:
			case LOWER:
			case GREATER:
			case EQUAL:
			case DIFF:
				enterOuterAlt(_localctx, 3);
				{
				setState(199);
				comparator();
				setState(200);
				notcomparatorexp();
				}
				break;
			case EOF:
			case IF:
			case SUB:
			case TEXTWINDOW:
			case ARRAY:
			case FOR:
			case WHILE:
			case TO:
			case GOTO:
			case PROGRAM:
			case STACK:
			case STEP:
			case CLOCK:
			case COMMA:
			case RPAREN:
			case RBRACKET:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class NotcomparatorexpContext extends ParserRuleContext {
		public SubexpressionContext subexpression() {
			return getRuleContext(SubexpressionContext.class,0);
		}
		public NotcomparatorexphelperContext notcomparatorexphelper() {
			return getRuleContext(NotcomparatorexphelperContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(LPGrammarParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LPGrammarParser.RPAREN, 0); }
		public TerminalNode MINUS() { return getToken(LPGrammarParser.MINUS, 0); }
		public NotcomparatorexpContext notcomparatorexp() {
			return getRuleContext(NotcomparatorexpContext.class,0);
		}
		public NotcomparatorexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notcomparatorexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterNotcomparatorexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitNotcomparatorexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitNotcomparatorexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotcomparatorexpContext notcomparatorexp() throws RecognitionException {
		NotcomparatorexpContext _localctx = new NotcomparatorexpContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_notcomparatorexp);
		try {
			setState(215);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTWINDOW:
			case ARRAY:
			case PROGRAM:
			case STACK:
			case CLOCK:
			case IDENTIFIER:
			case NUMBER:
			case TEXT:
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				subexpression();
				setState(206);
				notcomparatorexphelper();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				match(LPAREN);
				setState(209);
				expression();
				setState(210);
				match(RPAREN);
				setState(211);
				notcomparatorexphelper();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(213);
				match(MINUS);
				setState(214);
				notcomparatorexp();
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

	@SuppressWarnings("CheckReturnValue")
	public static class NotcomparatorexphelperContext extends ParserRuleContext {
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public NotcomparatorexpContext notcomparatorexp() {
			return getRuleContext(NotcomparatorexpContext.class,0);
		}
		public AndoroperatorContext andoroperator() {
			return getRuleContext(AndoroperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotcomparatorexphelperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notcomparatorexphelper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterNotcomparatorexphelper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitNotcomparatorexphelper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitNotcomparatorexphelper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotcomparatorexphelperContext notcomparatorexphelper() throws RecognitionException {
		NotcomparatorexphelperContext _localctx = new NotcomparatorexphelperContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_notcomparatorexphelper);
		try {
			setState(224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case MULTIPLY:
			case DIVIDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				operator();
				setState(218);
				notcomparatorexp();
				}
				break;
			case AND:
			case OR:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				andoroperator();
				setState(221);
				expression();
				}
				break;
			case EOF:
			case IF:
			case SUB:
			case TEXTWINDOW:
			case ARRAY:
			case FOR:
			case WHILE:
			case TO:
			case GOTO:
			case PROGRAM:
			case STACK:
			case STEP:
			case CLOCK:
			case COMMA:
			case RPAREN:
			case RBRACKET:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class SubexpressionContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LPGrammarParser.IDENTIFIER, 0); }
		public ArrayaccessorContext arrayaccessor() {
			return getRuleContext(ArrayaccessorContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(LPGrammarParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(LPGrammarParser.TEXT, 0); }
		public TerminalNode TRUE() { return getToken(LPGrammarParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(LPGrammarParser.FALSE, 0); }
		public SpecialcallContext specialcall() {
			return getRuleContext(SpecialcallContext.class,0);
		}
		public SubexpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subexpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterSubexpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitSubexpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitSubexpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubexpressionContext subexpression() throws RecognitionException {
		SubexpressionContext _localctx = new SubexpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_subexpression);
		try {
			setState(233);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				match(IDENTIFIER);
				setState(227);
				arrayaccessor();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				match(NUMBER);
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				match(TEXT);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(230);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(231);
				match(FALSE);
				}
				break;
			case TEXTWINDOW:
			case ARRAY:
			case PROGRAM:
			case STACK:
			case CLOCK:
				enterOuterAlt(_localctx, 6);
				{
				setState(232);
				specialcall();
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

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(LPGrammarParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LPGrammarParser.MINUS, 0); }
		public TerminalNode MULTIPLY() { return getToken(LPGrammarParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(LPGrammarParser.DIVIDE, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 125829120L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class AndoroperatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(LPGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(LPGrammarParser.OR, 0); }
		public AndoroperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andoroperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterAndoroperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitAndoroperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitAndoroperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndoroperatorContext andoroperator() throws RecognitionException {
		AndoroperatorContext _localctx = new AndoroperatorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_andoroperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class ComparatorContext extends ParserRuleContext {
		public TerminalNode LEQ() { return getToken(LPGrammarParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(LPGrammarParser.GEQ, 0); }
		public TerminalNode LOWER() { return getToken(LPGrammarParser.LOWER, 0); }
		public TerminalNode GREATER() { return getToken(LPGrammarParser.GREATER, 0); }
		public TerminalNode EQUAL() { return getToken(LPGrammarParser.EQUAL, 0); }
		public TerminalNode DIFF() { return getToken(LPGrammarParser.DIFF, 0); }
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8455716864L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class SpecialcallContext extends ParserRuleContext {
		public SpecialcallkeywordContext specialcallkeyword() {
			return getRuleContext(SpecialcallkeywordContext.class,0);
		}
		public TerminalNode PERIOD() { return getToken(LPGrammarParser.PERIOD, 0); }
		public TerminalNode IDENTIFIER() { return getToken(LPGrammarParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(LPGrammarParser.LPAREN, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LPGrammarParser.RPAREN, 0); }
		public SpecialcallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specialcall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterSpecialcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitSpecialcall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitSpecialcall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecialcallContext specialcall() throws RecognitionException {
		SpecialcallContext _localctx = new SpecialcallContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_specialcall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			specialcallkeyword();
			setState(242);
			match(PERIOD);
			setState(243);
			match(IDENTIFIER);
			setState(244);
			match(LPAREN);
			setState(245);
			arguments();
			setState(246);
			match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SpecialcallkeywordContext extends ParserRuleContext {
		public TerminalNode TEXTWINDOW() { return getToken(LPGrammarParser.TEXTWINDOW, 0); }
		public TerminalNode STACK() { return getToken(LPGrammarParser.STACK, 0); }
		public TerminalNode CLOCK() { return getToken(LPGrammarParser.CLOCK, 0); }
		public TerminalNode ARRAY() { return getToken(LPGrammarParser.ARRAY, 0); }
		public TerminalNode PROGRAM() { return getToken(LPGrammarParser.PROGRAM, 0); }
		public SpecialcallkeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specialcallkeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterSpecialcallkeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitSpecialcallkeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitSpecialcallkeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecialcallkeywordContext specialcallkeyword() throws RecognitionException {
		SpecialcallkeywordContext _localctx = new SpecialcallkeywordContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_specialcallkeyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1442368L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public OptionalargContext optionalarg() {
			return getRuleContext(OptionalargContext.class,0);
		}
		public FurtherargumentsContext furtherarguments() {
			return getRuleContext(FurtherargumentsContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_arguments);
		try {
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				optionalarg();
				setState(251);
				furtherarguments();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class OptionalargContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OptionalargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalarg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterOptionalarg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitOptionalarg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitOptionalarg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionalargContext optionalarg() throws RecognitionException {
		OptionalargContext _localctx = new OptionalargContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_optionalarg);
		try {
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTWINDOW:
			case ARRAY:
			case PROGRAM:
			case STACK:
			case CLOCK:
			case MINUS:
			case LPAREN:
			case IDENTIFIER:
			case NUMBER:
			case TEXT:
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				expression();
				}
				break;
			case COMMA:
			case RPAREN:
				enterOuterAlt(_localctx, 2);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class FurtherargumentsContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(LPGrammarParser.COMMA, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public FurtherargumentsContext furtherarguments() {
			return getRuleContext(FurtherargumentsContext.class,0);
		}
		public FurtherargumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_furtherarguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).enterFurtherarguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPGrammarListener ) ((LPGrammarListener)listener).exitFurtherarguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LPGrammarVisitor ) return ((LPGrammarVisitor<? extends T>)visitor).visitFurtherarguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FurtherargumentsContext furtherarguments() throws RecognitionException {
		FurtherargumentsContext _localctx = new FurtherargumentsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_furtherarguments);
		try {
			setState(265);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				match(COMMA);
				setState(261);
				arguments();
				setState(262);
				furtherarguments();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
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

	public static final String _serializedATN =
		"\u0004\u0001/\u010c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000<\b\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003X\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006q\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u0087\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0095\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u009c\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00a6\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0003\f\u00b1\b\f\u0001\r\u0001\r\u0001\r\u0003\r\u00b6"+
		"\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00c0\b\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00cc\b\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00d8\b\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u00e1\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00ea\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003"+
		"\u0018\u00ff\b\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u0103\b\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a"+
		"\u010a\b\u001a\u0001\u001a\u0000\u0000\u001b\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"4\u0000\u0004\u0001\u0000\u0017\u001a\u0001\u0000\u0015\u0016\u0001\u0000"+
		"\u001b \u0004\u0000\u0006\u0006\t\t\u0011\u0012\u0014\u0014\u0115\u0000"+
		";\u0001\u0000\u0000\u0000\u0002=\u0001\u0000\u0000\u0000\u0004B\u0001"+
		"\u0000\u0000\u0000\u0006W\u0001\u0000\u0000\u0000\bY\u0001\u0000\u0000"+
		"\u0000\n`\u0001\u0000\u0000\u0000\fp\u0001\u0000\u0000\u0000\u000er\u0001"+
		"\u0000\u0000\u0000\u0010\u0086\u0001\u0000\u0000\u0000\u0012\u0094\u0001"+
		"\u0000\u0000\u0000\u0014\u009b\u0001\u0000\u0000\u0000\u0016\u00a5\u0001"+
		"\u0000\u0000\u0000\u0018\u00b0\u0001\u0000\u0000\u0000\u001a\u00b5\u0001"+
		"\u0000\u0000\u0000\u001c\u00bf\u0001\u0000\u0000\u0000\u001e\u00cb\u0001"+
		"\u0000\u0000\u0000 \u00d7\u0001\u0000\u0000\u0000\"\u00e0\u0001\u0000"+
		"\u0000\u0000$\u00e9\u0001\u0000\u0000\u0000&\u00eb\u0001\u0000\u0000\u0000"+
		"(\u00ed\u0001\u0000\u0000\u0000*\u00ef\u0001\u0000\u0000\u0000,\u00f1"+
		"\u0001\u0000\u0000\u0000.\u00f8\u0001\u0000\u0000\u00000\u00fe\u0001\u0000"+
		"\u0000\u00002\u0102\u0001\u0000\u0000\u00004\u0109\u0001\u0000\u0000\u0000"+
		"67\u0003\u0016\u000b\u000078\u0003\u0000\u0000\u000089\u0005\u0000\u0000"+
		"\u00019<\u0001\u0000\u0000\u0000:<\u0001\u0000\u0000\u0000;6\u0001\u0000"+
		"\u0000\u0000;:\u0001\u0000\u0000\u0000<\u0001\u0001\u0000\u0000\u0000"+
		"=>\u0005\u0005\u0000\u0000>?\u0005)\u0000\u0000?@\u0003\u0010\b\u0000"+
		"@A\u0005\b\u0000\u0000A\u0003\u0001\u0000\u0000\u0000BC\u0005\u0001\u0000"+
		"\u0000CD\u0005$\u0000\u0000DE\u0003\u001a\r\u0000EF\u0005%\u0000\u0000"+
		"FG\u0005\u0007\u0000\u0000GH\u0003\u0010\b\u0000HI\u0003\u0006\u0003\u0000"+
		"I\u0005\u0001\u0000\u0000\u0000JX\u0005\u0003\u0000\u0000KL\u0005\u0002"+
		"\u0000\u0000LM\u0003\u0010\b\u0000MN\u0005\u0003\u0000\u0000NX\u0001\u0000"+
		"\u0000\u0000OP\u0005\n\u0000\u0000PQ\u0005$\u0000\u0000QR\u0003\u001a"+
		"\r\u0000RS\u0005%\u0000\u0000ST\u0005\u0007\u0000\u0000TU\u0003\u0010"+
		"\b\u0000UV\u0003\u0006\u0003\u0000VX\u0001\u0000\u0000\u0000WJ\u0001\u0000"+
		"\u0000\u0000WK\u0001\u0000\u0000\u0000WO\u0001\u0000\u0000\u0000X\u0007"+
		"\u0001\u0000\u0000\u0000YZ\u0005\f\u0000\u0000Z[\u0005$\u0000\u0000[\\"+
		"\u0003\u001a\r\u0000\\]\u0005%\u0000\u0000]^\u0003\u0010\b\u0000^_\u0005"+
		"\r\u0000\u0000_\t\u0001\u0000\u0000\u0000`a\u0005\u000b\u0000\u0000ab"+
		"\u0005)\u0000\u0000bc\u0005\u001f\u0000\u0000cd\u0003\u001a\r\u0000de"+
		"\u0005\u000e\u0000\u0000ef\u0003\u001a\r\u0000fg\u0003\f\u0006\u0000g"+
		"\u000b\u0001\u0000\u0000\u0000hi\u0005\u0013\u0000\u0000ij\u0003\u001a"+
		"\r\u0000jk\u0003\u0010\b\u0000kl\u0005\u000f\u0000\u0000lq\u0001\u0000"+
		"\u0000\u0000mn\u0003\u0010\b\u0000no\u0005\u000f\u0000\u0000oq\u0001\u0000"+
		"\u0000\u0000ph\u0001\u0000\u0000\u0000pm\u0001\u0000\u0000\u0000q\r\u0001"+
		"\u0000\u0000\u0000rs\u0005\u0010\u0000\u0000st\u0005)\u0000\u0000t\u000f"+
		"\u0001\u0000\u0000\u0000uv\u0005)\u0000\u0000v\u0087\u0003\u0012\t\u0000"+
		"wx\u0003\u0004\u0002\u0000xy\u0003\u0010\b\u0000y\u0087\u0001\u0000\u0000"+
		"\u0000z{\u0003\b\u0004\u0000{|\u0003\u0010\b\u0000|\u0087\u0001\u0000"+
		"\u0000\u0000}~\u0003\n\u0005\u0000~\u007f\u0003\u0010\b\u0000\u007f\u0087"+
		"\u0001\u0000\u0000\u0000\u0080\u0081\u0003\u000e\u0007\u0000\u0081\u0082"+
		"\u0003\u0010\b\u0000\u0082\u0087\u0001\u0000\u0000\u0000\u0083\u0084\u0003"+
		",\u0016\u0000\u0084\u0085\u0003\u0010\b\u0000\u0085\u0087\u0001\u0000"+
		"\u0000\u0000\u0086u\u0001\u0000\u0000\u0000\u0086w\u0001\u0000\u0000\u0000"+
		"\u0086z\u0001\u0000\u0000\u0000\u0086}\u0001\u0000\u0000\u0000\u0086\u0080"+
		"\u0001\u0000\u0000\u0000\u0086\u0083\u0001\u0000\u0000\u0000\u0087\u0011"+
		"\u0001\u0000\u0000\u0000\u0088\u0089\u0003\u0014\n\u0000\u0089\u008a\u0005"+
		"\u001f\u0000\u0000\u008a\u008b\u0003\u001a\r\u0000\u008b\u008c\u0003\u0010"+
		"\b\u0000\u008c\u0095\u0001\u0000\u0000\u0000\u008d\u008e\u0005$\u0000"+
		"\u0000\u008e\u008f\u0003\u001a\r\u0000\u008f\u0090\u0005%\u0000\u0000"+
		"\u0090\u0091\u0003\u0010\b\u0000\u0091\u0095\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0005#\u0000\u0000\u0093\u0095\u0003\u0010\b\u0000\u0094\u0088"+
		"\u0001\u0000\u0000\u0000\u0094\u008d\u0001\u0000\u0000\u0000\u0094\u0092"+
		"\u0001\u0000\u0000\u0000\u0095\u0013\u0001\u0000\u0000\u0000\u0096\u0097"+
		"\u0005&\u0000\u0000\u0097\u0098\u0003\u001a\r\u0000\u0098\u0099\u0005"+
		"\'\u0000\u0000\u0099\u009c\u0001\u0000\u0000\u0000\u009a\u009c\u0001\u0000"+
		"\u0000\u0000\u009b\u0096\u0001\u0000\u0000\u0000\u009b\u009a\u0001\u0000"+
		"\u0000\u0000\u009c\u0015\u0001\u0000\u0000\u0000\u009d\u009e\u0005)\u0000"+
		"\u0000\u009e\u00a6\u0003\u0018\f\u0000\u009f\u00a6\u0003\u000e\u0007\u0000"+
		"\u00a0\u00a6\u0003,\u0016\u0000\u00a1\u00a6\u0003\u0004\u0002\u0000\u00a2"+
		"\u00a6\u0003\b\u0004\u0000\u00a3\u00a6\u0003\n\u0005\u0000\u00a4\u00a6"+
		"\u0003\u0002\u0001\u0000\u00a5\u009d\u0001\u0000\u0000\u0000\u00a5\u009f"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a0\u0001\u0000\u0000\u0000\u00a5\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a4\u0001\u0000\u0000\u0000\u00a6\u0017"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a8\u0003\u0014\n\u0000\u00a8\u00a9\u0005"+
		"\u001f\u0000\u0000\u00a9\u00aa\u0003\u001a\r\u0000\u00aa\u00b1\u0001\u0000"+
		"\u0000\u0000\u00ab\u00ac\u0005$\u0000\u0000\u00ac\u00ad\u0003\u001a\r"+
		"\u0000\u00ad\u00ae\u0005%\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000\u0000"+
		"\u00af\u00b1\u0005#\u0000\u0000\u00b0\u00a7\u0001\u0000\u0000\u0000\u00b0"+
		"\u00ab\u0001\u0000\u0000\u0000\u00b0\u00af\u0001\u0000\u0000\u0000\u00b1"+
		"\u0019\u0001\u0000\u0000\u0000\u00b2\u00b6\u0003\u001c\u000e\u0000\u00b3"+
		"\u00b4\u0005\u0018\u0000\u0000\u00b4\u00b6\u0003\u001c\u000e\u0000\u00b5"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b6"+
		"\u001b\u0001\u0000\u0000\u0000\u00b7\u00b8\u0003$\u0012\u0000\u00b8\u00b9"+
		"\u0003\u001e\u000f\u0000\u00b9\u00c0\u0001\u0000\u0000\u0000\u00ba\u00bb"+
		"\u0005$\u0000\u0000\u00bb\u00bc\u0003\u001a\r\u0000\u00bc\u00bd\u0005"+
		"%\u0000\u0000\u00bd\u00be\u0003\u001e\u000f\u0000\u00be\u00c0\u0001\u0000"+
		"\u0000\u0000\u00bf\u00b7\u0001\u0000\u0000\u0000\u00bf\u00ba\u0001\u0000"+
		"\u0000\u0000\u00c0\u001d\u0001\u0000\u0000\u0000\u00c1\u00c2\u0003&\u0013"+
		"\u0000\u00c2\u00c3\u0003\u001a\r\u0000\u00c3\u00cc\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0003(\u0014\u0000\u00c5\u00c6\u0003\u001a\r\u0000\u00c6"+
		"\u00cc\u0001\u0000\u0000\u0000\u00c7\u00c8\u0003*\u0015\u0000\u00c8\u00c9"+
		"\u0003 \u0010\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca\u00cc\u0001"+
		"\u0000\u0000\u0000\u00cb\u00c1\u0001\u0000\u0000\u0000\u00cb\u00c4\u0001"+
		"\u0000\u0000\u0000\u00cb\u00c7\u0001\u0000\u0000\u0000\u00cb\u00ca\u0001"+
		"\u0000\u0000\u0000\u00cc\u001f\u0001\u0000\u0000\u0000\u00cd\u00ce\u0003"+
		"$\u0012\u0000\u00ce\u00cf\u0003\"\u0011\u0000\u00cf\u00d8\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0005$\u0000\u0000\u00d1\u00d2\u0003\u001a\r"+
		"\u0000\u00d2\u00d3\u0005%\u0000\u0000\u00d3\u00d4\u0003\"\u0011\u0000"+
		"\u00d4\u00d8\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005\u0018\u0000\u0000"+
		"\u00d6\u00d8\u0003 \u0010\u0000\u00d7\u00cd\u0001\u0000\u0000\u0000\u00d7"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d8"+
		"!\u0001\u0000\u0000\u0000\u00d9\u00da\u0003&\u0013\u0000\u00da\u00db\u0003"+
		" \u0010\u0000\u00db\u00e1\u0001\u0000\u0000\u0000\u00dc\u00dd\u0003(\u0014"+
		"\u0000\u00dd\u00de\u0003\u001a\r\u0000\u00de\u00e1\u0001\u0000\u0000\u0000"+
		"\u00df\u00e1\u0001\u0000\u0000\u0000\u00e0\u00d9\u0001\u0000\u0000\u0000"+
		"\u00e0\u00dc\u0001\u0000\u0000\u0000\u00e0\u00df\u0001\u0000\u0000\u0000"+
		"\u00e1#\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005)\u0000\u0000\u00e3\u00ea"+
		"\u0003\u0014\n\u0000\u00e4\u00ea\u0005*\u0000\u0000\u00e5\u00ea\u0005"+
		"+\u0000\u0000\u00e6\u00ea\u0005-\u0000\u0000\u00e7\u00ea\u0005.\u0000"+
		"\u0000\u00e8\u00ea\u0003,\u0016\u0000\u00e9\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e9\u00e4\u0001\u0000\u0000\u0000\u00e9\u00e5\u0001\u0000\u0000\u0000"+
		"\u00e9\u00e6\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e9\u00e8\u0001\u0000\u0000\u0000\u00ea%\u0001\u0000\u0000\u0000\u00eb"+
		"\u00ec\u0007\u0000\u0000\u0000\u00ec\'\u0001\u0000\u0000\u0000\u00ed\u00ee"+
		"\u0007\u0001\u0000\u0000\u00ee)\u0001\u0000\u0000\u0000\u00ef\u00f0\u0007"+
		"\u0002\u0000\u0000\u00f0+\u0001\u0000\u0000\u0000\u00f1\u00f2\u0003.\u0017"+
		"\u0000\u00f2\u00f3\u0005(\u0000\u0000\u00f3\u00f4\u0005)\u0000\u0000\u00f4"+
		"\u00f5\u0005$\u0000\u0000\u00f5\u00f6\u00030\u0018\u0000\u00f6\u00f7\u0005"+
		"%\u0000\u0000\u00f7-\u0001\u0000\u0000\u0000\u00f8\u00f9\u0007\u0003\u0000"+
		"\u0000\u00f9/\u0001\u0000\u0000\u0000\u00fa\u00fb\u00032\u0019\u0000\u00fb"+
		"\u00fc\u00034\u001a\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd\u00ff"+
		"\u0001\u0000\u0000\u0000\u00fe\u00fa\u0001\u0000\u0000\u0000\u00fe\u00fd"+
		"\u0001\u0000\u0000\u0000\u00ff1\u0001\u0000\u0000\u0000\u0100\u0103\u0003"+
		"\u001a\r\u0000\u0101\u0103\u0001\u0000\u0000\u0000\u0102\u0100\u0001\u0000"+
		"\u0000\u0000\u0102\u0101\u0001\u0000\u0000\u0000\u01033\u0001\u0000\u0000"+
		"\u0000\u0104\u0105\u0005!\u0000\u0000\u0105\u0106\u00030\u0018\u0000\u0106"+
		"\u0107\u00034\u001a\u0000\u0107\u010a\u0001\u0000\u0000\u0000\u0108\u010a"+
		"\u0001\u0000\u0000\u0000\u0109\u0104\u0001\u0000\u0000\u0000\u0109\u0108"+
		"\u0001\u0000\u0000\u0000\u010a5\u0001\u0000\u0000\u0000\u0011;Wp\u0086"+
		"\u0094\u009b\u00a5\u00b0\u00b5\u00bf\u00cb\u00d7\u00e0\u00e9\u00fe\u0102"+
		"\u0109";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}