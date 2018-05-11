// Generated from grammar/DeepOcl.g4 by ANTLR 4.5.3
package org.melanee.ocl2.grammar.definition.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DeepOclParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, CONTEXT=63, UnaryOperatorCS=64, InvalIDLiteralExpCS=65, 
		NumberLiteralExpCS=66, INT=67, BooleanLiteralExpCS=68, NullLiteralExpCS=69, 
		ID=70, WS=71, ONESPACE=72, STRING=73, UnterminatedStringLiteral=74, COMMENT=75;
	public static final int
		RULE_contextDeclCS = 0, RULE_operationContextCS = 1, RULE_levelSpecificationCS = 2, 
		RULE_bodyCS = 3, RULE_postCS = 4, RULE_preCS = 5, RULE_defCS = 6, RULE_typeExpCS = 7, 
		RULE_typeLiteralCS = 8, RULE_tupleTypeCS = 9, RULE_tuplePartCS = 10, RULE_collectionTypeCS = 11, 
		RULE_collectionTypeIDentifier = 12, RULE_primitiveTypeCS = 13, RULE_typeNameExpCS = 14, 
		RULE_specificationCS = 15, RULE_expCS = 16, RULE_infixedExpCS = 17, RULE_iteratorBarExpCS = 18, 
		RULE_navigationOperatorCS = 19, RULE_prefixedExpCS = 20, RULE_primaryExpCS = 21, 
		RULE_nestedExpCS = 22, RULE_ifExpCS = 23, RULE_letExpCS = 24, RULE_letVariableCS = 25, 
		RULE_typeLiteralExpCS = 26, RULE_collectionLiteralExpCS = 27, RULE_collectionLiteralPartCS = 28, 
		RULE_tupleLiteralExpCS = 29, RULE_tupleLiteralPartCS = 30, RULE_selfExpCS = 31, 
		RULE_primitiveLiteralExpCS = 32, RULE_navigatingExpCS = 33, RULE_navigatingSemiAgrsCS = 34, 
		RULE_navigatingCommaArgCS = 35, RULE_navigatingArgExpCS = 36, RULE_navigatingBarAgrsCS = 37, 
		RULE_navigatingArgCS = 38, RULE_indexExpCS = 39, RULE_nameExpCS = 40, 
		RULE_parameterCS = 41, RULE_invCS = 42, RULE_classifierContextCS = 43, 
		RULE_propertyContextDeclCS = 44, RULE_derCS = 45, RULE_initCS = 46, RULE_onespace = 47;
	public static final String[] ruleNames = {
		"contextDeclCS", "operationContextCS", "levelSpecificationCS", "bodyCS", 
		"postCS", "preCS", "defCS", "typeExpCS", "typeLiteralCS", "tupleTypeCS", 
		"tuplePartCS", "collectionTypeCS", "collectionTypeIDentifier", "primitiveTypeCS", 
		"typeNameExpCS", "specificationCS", "expCS", "infixedExpCS", "iteratorBarExpCS", 
		"navigationOperatorCS", "prefixedExpCS", "primaryExpCS", "nestedExpCS", 
		"ifExpCS", "letExpCS", "letVariableCS", "typeLiteralExpCS", "collectionLiteralExpCS", 
		"collectionLiteralPartCS", "tupleLiteralExpCS", "tupleLiteralPartCS", 
		"selfExpCS", "primitiveLiteralExpCS", "navigatingExpCS", "navigatingSemiAgrsCS", 
		"navigatingCommaArgCS", "navigatingArgExpCS", "navigatingBarAgrsCS", "navigatingArgCS", 
		"indexExpCS", "nameExpCS", "parameterCS", "invCS", "classifierContextCS", 
		"propertyContextDeclCS", "derCS", "initCS", "onespace"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "'::'", "'('", "','", "')'", "'_'", "'body'", "'post'", "'pre'", 
		"'def'", "'='", "'Tuple'", "'<'", "'>'", "'Collection'", "'Bag'", "'OrderedSet'", 
		"'Sequence'", "'Set'", "'Boolean'", "'Integer'", "'Real'", "'ID'", "'UnlimitedNatural'", 
		"'OclAny'", "'OclInvalID'", "'OclVoID'", "'/'", "'*'", "'+'", "'-'", "'<='", 
		"'>='", "'<>'", "'^'", "'and'", "'or'", "'xor'", "'implies'", "'|'", "'.'", 
		"'->'", "'if'", "'then'", "'else'", "'endif'", "'let'", "'in'", "'{'", 
		"'}'", "'..'", "'self'", "'@'", "'\"'", "';'", "'['", "']'", "'$'", "'#'", 
		"'inv'", "'derive'", "'init'", "'context'", null, "'invalid'", null, null, 
		null, "'null'", null, null, "' '"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "CONTEXT", "UnaryOperatorCS", "InvalIDLiteralExpCS", 
		"NumberLiteralExpCS", "INT", "BooleanLiteralExpCS", "NullLiteralExpCS", 
		"ID", "WS", "ONESPACE", "STRING", "UnterminatedStringLiteral", "COMMENT"
	};
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
	public String getGrammarFileName() { return "DeepOcl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DeepOclParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ContextDeclCSContext extends ParserRuleContext {
		public List<PropertyContextDeclCSContext> propertyContextDeclCS() {
			return getRuleContexts(PropertyContextDeclCSContext.class);
		}
		public PropertyContextDeclCSContext propertyContextDeclCS(int i) {
			return getRuleContext(PropertyContextDeclCSContext.class,i);
		}
		public List<ClassifierContextCSContext> classifierContextCS() {
			return getRuleContexts(ClassifierContextCSContext.class);
		}
		public ClassifierContextCSContext classifierContextCS(int i) {
			return getRuleContext(ClassifierContextCSContext.class,i);
		}
		public List<OperationContextCSContext> operationContextCS() {
			return getRuleContexts(OperationContextCSContext.class);
		}
		public OperationContextCSContext operationContextCS(int i) {
			return getRuleContext(OperationContextCSContext.class,i);
		}
		public ContextDeclCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextDeclCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterContextDeclCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitContextDeclCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitContextDeclCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContextDeclCSContext contextDeclCS() throws RecognitionException {
		ContextDeclCSContext _localctx = new ContextDeclCSContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_contextDeclCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(99);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(96);
					propertyContextDeclCS();
					}
					break;
				case 2:
					{
					setState(97);
					classifierContextCS();
					}
					break;
				case 3:
					{
					setState(98);
					operationContextCS();
					}
					break;
				}
				}
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CONTEXT );
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

	public static class OperationContextCSContext extends ParserRuleContext {
		public TerminalNode CONTEXT() { return getToken(DeepOclParser.CONTEXT, 0); }
		public List<TerminalNode> ID() { return getTokens(DeepOclParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DeepOclParser.ID, i);
		}
		public LevelSpecificationCSContext levelSpecificationCS() {
			return getRuleContext(LevelSpecificationCSContext.class,0);
		}
		public List<ParameterCSContext> parameterCS() {
			return getRuleContexts(ParameterCSContext.class);
		}
		public ParameterCSContext parameterCS(int i) {
			return getRuleContext(ParameterCSContext.class,i);
		}
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public List<PreCSContext> preCS() {
			return getRuleContexts(PreCSContext.class);
		}
		public PreCSContext preCS(int i) {
			return getRuleContext(PreCSContext.class,i);
		}
		public List<PostCSContext> postCS() {
			return getRuleContexts(PostCSContext.class);
		}
		public PostCSContext postCS(int i) {
			return getRuleContext(PostCSContext.class,i);
		}
		public List<BodyCSContext> bodyCS() {
			return getRuleContexts(BodyCSContext.class);
		}
		public BodyCSContext bodyCS(int i) {
			return getRuleContext(BodyCSContext.class,i);
		}
		public OperationContextCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationContextCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterOperationContextCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitOperationContextCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitOperationContextCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContextCSContext operationContextCS() throws RecognitionException {
		OperationContextCSContext _localctx = new OperationContextCSContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_operationContextCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(CONTEXT);
			setState(105);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(104);
				levelSpecificationCS();
				}
			}

			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(107);
				match(ID);
				setState(108);
				match(T__0);
				}
				break;
			}
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(111);
				match(ID);
				setState(112);
				match(T__1);
				setState(117);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(113);
						match(ID);
						setState(114);
						match(T__1);
						}
						} 
					}
					setState(119);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				setState(120);
				match(ID);
				}
				break;
			case 2:
				{
				setState(121);
				match(ID);
				}
				break;
			}
			setState(124);
			match(T__2);
			setState(133);
			_la = _input.LA(1);
			if (((((_la - 12)) & ~0x3f) == 0 && ((1L << (_la - 12)) & ((1L << (T__11 - 12)) | (1L << (T__14 - 12)) | (1L << (T__15 - 12)) | (1L << (T__16 - 12)) | (1L << (T__17 - 12)) | (1L << (T__18 - 12)) | (1L << (T__19 - 12)) | (1L << (T__20 - 12)) | (1L << (T__21 - 12)) | (1L << (T__22 - 12)) | (1L << (T__23 - 12)) | (1L << (T__24 - 12)) | (1L << (T__25 - 12)) | (1L << (T__26 - 12)) | (1L << (ID - 12)))) != 0)) {
				{
				setState(125);
				parameterCS();
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(126);
					match(T__3);
					setState(127);
					parameterCS();
					}
					}
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(135);
			match(T__4);
			setState(138);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(136);
				match(T__0);
				setState(137);
				typeExpCS();
				}
			}

			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8))) != 0)) {
				{
				setState(143);
				switch (_input.LA(1)) {
				case T__8:
					{
					setState(140);
					preCS();
					}
					break;
				case T__7:
					{
					setState(141);
					postCS();
					}
					break;
				case T__6:
					{
					setState(142);
					bodyCS();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(147);
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

	public static class LevelSpecificationCSContext extends ParserRuleContext {
		public List<TerminalNode> NumberLiteralExpCS() { return getTokens(DeepOclParser.NumberLiteralExpCS); }
		public TerminalNode NumberLiteralExpCS(int i) {
			return getToken(DeepOclParser.NumberLiteralExpCS, i);
		}
		public LevelSpecificationCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_levelSpecificationCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterLevelSpecificationCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitLevelSpecificationCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitLevelSpecificationCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LevelSpecificationCSContext levelSpecificationCS() throws RecognitionException {
		LevelSpecificationCSContext _localctx = new LevelSpecificationCSContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_levelSpecificationCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(T__2);
			setState(149);
			match(NumberLiteralExpCS);
			setState(152);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(150);
				match(T__3);
				setState(151);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==NumberLiteralExpCS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(154);
			match(T__4);
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

	public static class BodyCSContext extends ParserRuleContext {
		public SpecificationCSContext specificationCS() {
			return getRuleContext(SpecificationCSContext.class,0);
		}
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public BodyCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bodyCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterBodyCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitBodyCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitBodyCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyCSContext bodyCS() throws RecognitionException {
		BodyCSContext _localctx = new BodyCSContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bodyCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(T__6);
			setState(158);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(157);
				match(ID);
				}
			}

			setState(160);
			match(T__0);
			setState(161);
			specificationCS();
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

	public static class PostCSContext extends ParserRuleContext {
		public SpecificationCSContext specificationCS() {
			return getRuleContext(SpecificationCSContext.class,0);
		}
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public PostCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterPostCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitPostCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitPostCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostCSContext postCS() throws RecognitionException {
		PostCSContext _localctx = new PostCSContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_postCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__7);
			setState(165);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(164);
				match(ID);
				}
			}

			setState(167);
			match(T__0);
			setState(168);
			specificationCS();
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

	public static class PreCSContext extends ParserRuleContext {
		public SpecificationCSContext specificationCS() {
			return getRuleContext(SpecificationCSContext.class,0);
		}
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public PreCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterPreCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitPreCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitPreCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreCSContext preCS() throws RecognitionException {
		PreCSContext _localctx = new PreCSContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_preCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(T__8);
			setState(172);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(171);
				match(ID);
				}
			}

			setState(174);
			match(T__0);
			setState(175);
			specificationCS();
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

	public static class DefCSContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(DeepOclParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DeepOclParser.ID, i);
		}
		public SpecificationCSContext specificationCS() {
			return getRuleContext(SpecificationCSContext.class,0);
		}
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public List<ParameterCSContext> parameterCS() {
			return getRuleContexts(ParameterCSContext.class);
		}
		public ParameterCSContext parameterCS(int i) {
			return getRuleContext(ParameterCSContext.class,i);
		}
		public DefCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterDefCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitDefCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitDefCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefCSContext defCS() throws RecognitionException {
		DefCSContext _localctx = new DefCSContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_defCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__9);
			setState(179);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(178);
				match(ID);
				}
			}

			setState(181);
			match(T__0);
			setState(182);
			match(ID);
			{
			setState(195);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(183);
				match(T__2);
				setState(185);
				_la = _input.LA(1);
				if (((((_la - 12)) & ~0x3f) == 0 && ((1L << (_la - 12)) & ((1L << (T__11 - 12)) | (1L << (T__14 - 12)) | (1L << (T__15 - 12)) | (1L << (T__16 - 12)) | (1L << (T__17 - 12)) | (1L << (T__18 - 12)) | (1L << (T__19 - 12)) | (1L << (T__20 - 12)) | (1L << (T__21 - 12)) | (1L << (T__22 - 12)) | (1L << (T__23 - 12)) | (1L << (T__24 - 12)) | (1L << (T__25 - 12)) | (1L << (T__26 - 12)) | (1L << (ID - 12)))) != 0)) {
					{
					setState(184);
					parameterCS();
					}
				}

				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(187);
					match(T__3);
					setState(188);
					parameterCS();
					}
					}
					setState(193);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(194);
				match(T__4);
				}
			}

			setState(197);
			match(T__0);
			setState(199);
			_la = _input.LA(1);
			if (((((_la - 12)) & ~0x3f) == 0 && ((1L << (_la - 12)) & ((1L << (T__11 - 12)) | (1L << (T__14 - 12)) | (1L << (T__15 - 12)) | (1L << (T__16 - 12)) | (1L << (T__17 - 12)) | (1L << (T__18 - 12)) | (1L << (T__19 - 12)) | (1L << (T__20 - 12)) | (1L << (T__21 - 12)) | (1L << (T__22 - 12)) | (1L << (T__23 - 12)) | (1L << (T__24 - 12)) | (1L << (T__25 - 12)) | (1L << (T__26 - 12)) | (1L << (ID - 12)))) != 0)) {
				{
				setState(198);
				typeExpCS();
				}
			}

			setState(201);
			match(T__10);
			setState(202);
			specificationCS();
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

	public static class TypeExpCSContext extends ParserRuleContext {
		public TypeNameExpCSContext typeNameExpCS() {
			return getRuleContext(TypeNameExpCSContext.class,0);
		}
		public TypeLiteralCSContext typeLiteralCS() {
			return getRuleContext(TypeLiteralCSContext.class,0);
		}
		public TypeExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTypeExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTypeExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTypeExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeExpCSContext typeExpCS() throws RecognitionException {
		TypeExpCSContext _localctx = new TypeExpCSContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeExpCS);
		try {
			setState(206);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				typeNameExpCS();
				}
				break;
			case T__11:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				typeLiteralCS();
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

	public static class TypeLiteralCSContext extends ParserRuleContext {
		public PrimitiveTypeCSContext primitiveTypeCS() {
			return getRuleContext(PrimitiveTypeCSContext.class,0);
		}
		public CollectionTypeCSContext collectionTypeCS() {
			return getRuleContext(CollectionTypeCSContext.class,0);
		}
		public TupleTypeCSContext tupleTypeCS() {
			return getRuleContext(TupleTypeCSContext.class,0);
		}
		public TypeLiteralCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeLiteralCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTypeLiteralCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTypeLiteralCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTypeLiteralCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeLiteralCSContext typeLiteralCS() throws RecognitionException {
		TypeLiteralCSContext _localctx = new TypeLiteralCSContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeLiteralCS);
		try {
			setState(211);
			switch (_input.LA(1)) {
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				primitiveTypeCS();
				}
				break;
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				collectionTypeCS();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(210);
				tupleTypeCS();
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

	public static class TupleTypeCSContext extends ParserRuleContext {
		public List<TuplePartCSContext> tuplePartCS() {
			return getRuleContexts(TuplePartCSContext.class);
		}
		public TuplePartCSContext tuplePartCS(int i) {
			return getRuleContext(TuplePartCSContext.class,i);
		}
		public TupleTypeCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleTypeCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTupleTypeCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTupleTypeCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTupleTypeCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleTypeCSContext tupleTypeCS() throws RecognitionException {
		TupleTypeCSContext _localctx = new TupleTypeCSContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tupleTypeCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(T__11);
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(214);
				match(T__2);
				setState(215);
				tuplePartCS();
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(216);
					match(T__3);
					setState(217);
					tuplePartCS();
					}
					}
					setState(222);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(223);
				match(T__4);
				}
				break;
			case 2:
				{
				setState(225);
				match(T__12);
				setState(226);
				tuplePartCS();
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(227);
					match(T__3);
					setState(228);
					tuplePartCS();
					}
					}
					setState(233);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(234);
				match(T__13);
				}
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

	public static class TuplePartCSContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public TuplePartCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuplePartCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTuplePartCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTuplePartCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTuplePartCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TuplePartCSContext tuplePartCS() throws RecognitionException {
		TuplePartCSContext _localctx = new TuplePartCSContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tuplePartCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(ID);
			setState(239);
			match(T__0);
			setState(240);
			typeExpCS();
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

	public static class CollectionTypeCSContext extends ParserRuleContext {
		public CollectionTypeIDentifierContext collectionTypeIDentifier() {
			return getRuleContext(CollectionTypeIDentifierContext.class,0);
		}
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public CollectionTypeCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collectionTypeCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterCollectionTypeCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitCollectionTypeCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitCollectionTypeCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CollectionTypeCSContext collectionTypeCS() throws RecognitionException {
		CollectionTypeCSContext _localctx = new CollectionTypeCSContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_collectionTypeCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			collectionTypeIDentifier();
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(243);
				match(T__2);
				setState(244);
				typeExpCS();
				setState(245);
				match(T__4);
				}
				break;
			case 2:
				{
				setState(247);
				match(T__12);
				setState(248);
				typeExpCS();
				setState(249);
				match(T__13);
				}
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

	public static class CollectionTypeIDentifierContext extends ParserRuleContext {
		public CollectionTypeIDentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collectionTypeIDentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterCollectionTypeIDentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitCollectionTypeIDentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitCollectionTypeIDentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CollectionTypeIDentifierContext collectionTypeIDentifier() throws RecognitionException {
		CollectionTypeIDentifierContext _localctx = new CollectionTypeIDentifierContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_collectionTypeIDentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class PrimitiveTypeCSContext extends ParserRuleContext {
		public PrimitiveTypeCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveTypeCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterPrimitiveTypeCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitPrimitiveTypeCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitPrimitiveTypeCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeCSContext primitiveTypeCS() throws RecognitionException {
		PrimitiveTypeCSContext _localctx = new PrimitiveTypeCSContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_primitiveTypeCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class TypeNameExpCSContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(DeepOclParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DeepOclParser.ID, i);
		}
		public TypeNameExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeNameExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTypeNameExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTypeNameExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTypeNameExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameExpCSContext typeNameExpCS() throws RecognitionException {
		TypeNameExpCSContext _localctx = new TypeNameExpCSContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_typeNameExpCS);
		try {
			int _alt;
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				match(ID);
				setState(258);
				match(T__1);
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(259);
						match(ID);
						setState(260);
						match(T__1);
						}
						} 
					}
					setState(265);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				setState(266);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				match(ID);
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

	public static class SpecificationCSContext extends ParserRuleContext {
		public List<InfixedExpCSContext> infixedExpCS() {
			return getRuleContexts(InfixedExpCSContext.class);
		}
		public InfixedExpCSContext infixedExpCS(int i) {
			return getRuleContext(InfixedExpCSContext.class,i);
		}
		public SpecificationCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specificationCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterSpecificationCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitSpecificationCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitSpecificationCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecificationCSContext specificationCS() throws RecognitionException {
		SpecificationCSContext _localctx = new SpecificationCSContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_specificationCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__39) | (1L << T__42) | (1L << T__46) | (1L << T__51) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UnaryOperatorCS - 64)) | (1L << (InvalIDLiteralExpCS - 64)) | (1L << (NumberLiteralExpCS - 64)) | (1L << (BooleanLiteralExpCS - 64)) | (1L << (NullLiteralExpCS - 64)) | (1L << (ID - 64)) | (1L << (STRING - 64)))) != 0)) {
				{
				{
				setState(270);
				infixedExpCS(0);
				}
				}
				setState(275);
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

	public static class ExpCSContext extends ParserRuleContext {
		public InfixedExpCSContext infixedExpCS() {
			return getRuleContext(InfixedExpCSContext.class,0);
		}
		public ExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpCSContext expCS() throws RecognitionException {
		ExpCSContext _localctx = new ExpCSContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			infixedExpCS(0);
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

	public static class InfixedExpCSContext extends ParserRuleContext {
		public InfixedExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infixedExpCS; }
	 
		public InfixedExpCSContext() { }
		public void copyFrom(InfixedExpCSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TimesDivideContext extends InfixedExpCSContext {
		public InfixedExpCSContext left;
		public Token op;
		public InfixedExpCSContext right;
		public List<InfixedExpCSContext> infixedExpCS() {
			return getRuleContexts(InfixedExpCSContext.class);
		}
		public InfixedExpCSContext infixedExpCS(int i) {
			return getRuleContext(InfixedExpCSContext.class,i);
		}
		public TimesDivideContext(InfixedExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTimesDivide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTimesDivide(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTimesDivide(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusMinusContext extends InfixedExpCSContext {
		public InfixedExpCSContext left;
		public Token op;
		public InfixedExpCSContext right;
		public List<InfixedExpCSContext> infixedExpCS() {
			return getRuleContexts(InfixedExpCSContext.class);
		}
		public InfixedExpCSContext infixedExpCS(int i) {
			return getRuleContext(InfixedExpCSContext.class,i);
		}
		public PlusMinusContext(InfixedExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterPlusMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitPlusMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitPlusMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndOrXorContext extends InfixedExpCSContext {
		public InfixedExpCSContext left;
		public Token op;
		public InfixedExpCSContext right;
		public List<InfixedExpCSContext> infixedExpCS() {
			return getRuleContexts(InfixedExpCSContext.class);
		}
		public InfixedExpCSContext infixedExpCS(int i) {
			return getRuleContext(InfixedExpCSContext.class,i);
		}
		public AndOrXorContext(InfixedExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterAndOrXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitAndOrXor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitAndOrXor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MessageContext extends InfixedExpCSContext {
		public InfixedExpCSContext left;
		public Token op;
		public InfixedExpCSContext right;
		public List<InfixedExpCSContext> infixedExpCS() {
			return getRuleContexts(InfixedExpCSContext.class);
		}
		public InfixedExpCSContext infixedExpCS(int i) {
			return getRuleContext(InfixedExpCSContext.class,i);
		}
		public MessageContext(InfixedExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualOperationsContext extends InfixedExpCSContext {
		public InfixedExpCSContext left;
		public Token op;
		public InfixedExpCSContext right;
		public List<InfixedExpCSContext> infixedExpCS() {
			return getRuleContexts(InfixedExpCSContext.class);
		}
		public InfixedExpCSContext infixedExpCS(int i) {
			return getRuleContext(InfixedExpCSContext.class,i);
		}
		public EqualOperationsContext(InfixedExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterEqualOperations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitEqualOperations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitEqualOperations(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrefixedExpContext extends InfixedExpCSContext {
		public PrefixedExpCSContext prefixedExpCS() {
			return getRuleContext(PrefixedExpCSContext.class,0);
		}
		public PrefixedExpContext(InfixedExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterPrefixedExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitPrefixedExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitPrefixedExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IteratorBarContext extends InfixedExpCSContext {
		public IteratorBarExpCSContext iteratorBarExpCS() {
			return getRuleContext(IteratorBarExpCSContext.class,0);
		}
		public IteratorBarContext(InfixedExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterIteratorBar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitIteratorBar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitIteratorBar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ImpliesContext extends InfixedExpCSContext {
		public InfixedExpCSContext left;
		public Token op;
		public InfixedExpCSContext right;
		public List<InfixedExpCSContext> infixedExpCS() {
			return getRuleContexts(InfixedExpCSContext.class);
		}
		public InfixedExpCSContext infixedExpCS(int i) {
			return getRuleContext(InfixedExpCSContext.class,i);
		}
		public ImpliesContext(InfixedExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterImplies(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitImplies(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitImplies(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfixedExpCSContext infixedExpCS() throws RecognitionException {
		return infixedExpCS(0);
	}

	private InfixedExpCSContext infixedExpCS(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InfixedExpCSContext _localctx = new InfixedExpCSContext(_ctx, _parentState);
		InfixedExpCSContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_infixedExpCS, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			switch (_input.LA(1)) {
			case T__2:
			case T__11:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__42:
			case T__46:
			case T__51:
			case T__57:
			case T__58:
			case UnaryOperatorCS:
			case InvalIDLiteralExpCS:
			case NumberLiteralExpCS:
			case BooleanLiteralExpCS:
			case NullLiteralExpCS:
			case ID:
			case STRING:
				{
				_localctx = new PrefixedExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(279);
				prefixedExpCS();
				}
				break;
			case T__39:
				{
				_localctx = new IteratorBarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(280);
				iteratorBarExpCS();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(303);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(301);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new TimesDivideContext(new InfixedExpCSContext(_parentctx, _parentState));
						((TimesDivideContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_infixedExpCS);
						setState(283);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(284);
						((TimesDivideContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__27 || _la==T__28) ) {
							((TimesDivideContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(285);
						((TimesDivideContext)_localctx).right = infixedExpCS(7);
						}
						break;
					case 2:
						{
						_localctx = new PlusMinusContext(new InfixedExpCSContext(_parentctx, _parentState));
						((PlusMinusContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_infixedExpCS);
						setState(286);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(287);
						((PlusMinusContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__29 || _la==T__30) ) {
							((PlusMinusContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(288);
						((PlusMinusContext)_localctx).right = infixedExpCS(6);
						}
						break;
					case 3:
						{
						_localctx = new EqualOperationsContext(new InfixedExpCSContext(_parentctx, _parentState));
						((EqualOperationsContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_infixedExpCS);
						setState(289);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(290);
						((EqualOperationsContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__31) | (1L << T__32) | (1L << T__33))) != 0)) ) {
							((EqualOperationsContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(291);
						((EqualOperationsContext)_localctx).right = infixedExpCS(5);
						}
						break;
					case 4:
						{
						_localctx = new MessageContext(new InfixedExpCSContext(_parentctx, _parentState));
						((MessageContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_infixedExpCS);
						setState(292);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(293);
						((MessageContext)_localctx).op = match(T__34);
						setState(294);
						((MessageContext)_localctx).right = infixedExpCS(4);
						}
						break;
					case 5:
						{
						_localctx = new AndOrXorContext(new InfixedExpCSContext(_parentctx, _parentState));
						((AndOrXorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_infixedExpCS);
						setState(295);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(296);
						((AndOrXorContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
							((AndOrXorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(297);
						((AndOrXorContext)_localctx).right = infixedExpCS(3);
						}
						break;
					case 6:
						{
						_localctx = new ImpliesContext(new InfixedExpCSContext(_parentctx, _parentState));
						((ImpliesContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_infixedExpCS);
						setState(298);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(299);
						((ImpliesContext)_localctx).op = match(T__38);
						setState(300);
						((ImpliesContext)_localctx).right = infixedExpCS(2);
						}
						break;
					}
					} 
				}
				setState(305);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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

	public static class IteratorBarExpCSContext extends ParserRuleContext {
		public IteratorBarExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iteratorBarExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterIteratorBarExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitIteratorBarExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitIteratorBarExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IteratorBarExpCSContext iteratorBarExpCS() throws RecognitionException {
		IteratorBarExpCSContext _localctx = new IteratorBarExpCSContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_iteratorBarExpCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(T__39);
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

	public static class NavigationOperatorCSContext extends ParserRuleContext {
		public NavigationOperatorCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigationOperatorCS; }
	 
		public NavigationOperatorCSContext() { }
		public void copyFrom(NavigationOperatorCSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrowContext extends NavigationOperatorCSContext {
		public ArrowContext(NavigationOperatorCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterArrow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitArrow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitArrow(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DotContext extends NavigationOperatorCSContext {
		public DotContext(NavigationOperatorCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterDot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitDot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitDot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigationOperatorCSContext navigationOperatorCS() throws RecognitionException {
		NavigationOperatorCSContext _localctx = new NavigationOperatorCSContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_navigationOperatorCS);
		try {
			setState(310);
			switch (_input.LA(1)) {
			case T__40:
				_localctx = new DotContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				match(T__40);
				}
				break;
			case T__41:
				_localctx = new ArrowContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				match(T__41);
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

	public static class PrefixedExpCSContext extends ParserRuleContext {
		public Token not;
		public PrimaryExpCSContext exp;
		public List<PrimaryExpCSContext> primaryExpCS() {
			return getRuleContexts(PrimaryExpCSContext.class);
		}
		public PrimaryExpCSContext primaryExpCS(int i) {
			return getRuleContext(PrimaryExpCSContext.class,i);
		}
		public List<TerminalNode> UnaryOperatorCS() { return getTokens(DeepOclParser.UnaryOperatorCS); }
		public TerminalNode UnaryOperatorCS(int i) {
			return getToken(DeepOclParser.UnaryOperatorCS, i);
		}
		public List<NavigationOperatorCSContext> navigationOperatorCS() {
			return getRuleContexts(NavigationOperatorCSContext.class);
		}
		public NavigationOperatorCSContext navigationOperatorCS(int i) {
			return getRuleContext(NavigationOperatorCSContext.class,i);
		}
		public PrefixedExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixedExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterPrefixedExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitPrefixedExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitPrefixedExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixedExpCSContext prefixedExpCS() throws RecognitionException {
		PrefixedExpCSContext _localctx = new PrefixedExpCSContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_prefixedExpCS);
		int _la;
		try {
			int _alt;
			setState(328);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(313); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(312);
					((PrefixedExpCSContext)_localctx).not = match(UnaryOperatorCS);
					}
					}
					setState(315); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==UnaryOperatorCS );
				setState(317);
				((PrefixedExpCSContext)_localctx).exp = primaryExpCS();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(318);
				primaryExpCS();
				setState(324);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(319);
						navigationOperatorCS();
						setState(320);
						primaryExpCS();
						}
						} 
					}
					setState(326);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(327);
				primaryExpCS();
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

	public static class PrimaryExpCSContext extends ParserRuleContext {
		public LetExpCSContext letExpCS() {
			return getRuleContext(LetExpCSContext.class,0);
		}
		public IfExpCSContext ifExpCS() {
			return getRuleContext(IfExpCSContext.class,0);
		}
		public PrimitiveLiteralExpCSContext primitiveLiteralExpCS() {
			return getRuleContext(PrimitiveLiteralExpCSContext.class,0);
		}
		public NavigatingExpCSContext navigatingExpCS() {
			return getRuleContext(NavigatingExpCSContext.class,0);
		}
		public SelfExpCSContext selfExpCS() {
			return getRuleContext(SelfExpCSContext.class,0);
		}
		public TupleLiteralExpCSContext tupleLiteralExpCS() {
			return getRuleContext(TupleLiteralExpCSContext.class,0);
		}
		public CollectionLiteralExpCSContext collectionLiteralExpCS() {
			return getRuleContext(CollectionLiteralExpCSContext.class,0);
		}
		public TypeLiteralExpCSContext typeLiteralExpCS() {
			return getRuleContext(TypeLiteralExpCSContext.class,0);
		}
		public NestedExpCSContext nestedExpCS() {
			return getRuleContext(NestedExpCSContext.class,0);
		}
		public PrimaryExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterPrimaryExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitPrimaryExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitPrimaryExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpCSContext primaryExpCS() throws RecognitionException {
		PrimaryExpCSContext _localctx = new PrimaryExpCSContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_primaryExpCS);
		try {
			setState(339);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				letExpCS();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				ifExpCS();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(332);
				primitiveLiteralExpCS();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(333);
				navigatingExpCS();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(334);
				selfExpCS();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(335);
				tupleLiteralExpCS();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(336);
				collectionLiteralExpCS();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(337);
				typeLiteralExpCS();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(338);
				nestedExpCS();
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

	public static class NestedExpCSContext extends ParserRuleContext {
		public ExpCSContext exp;
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public NestedExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNestedExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNestedExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNestedExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NestedExpCSContext nestedExpCS() throws RecognitionException {
		NestedExpCSContext _localctx = new NestedExpCSContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_nestedExpCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(349); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(341);
					match(T__2);
					setState(343); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(342);
						((NestedExpCSContext)_localctx).exp = expCS();
						}
						}
						setState(345); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__39) | (1L << T__42) | (1L << T__46) | (1L << T__51) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UnaryOperatorCS - 64)) | (1L << (InvalIDLiteralExpCS - 64)) | (1L << (NumberLiteralExpCS - 64)) | (1L << (BooleanLiteralExpCS - 64)) | (1L << (NullLiteralExpCS - 64)) | (1L << (ID - 64)) | (1L << (STRING - 64)))) != 0) );
					setState(347);
					match(T__4);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(351); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class IfExpCSContext extends ParserRuleContext {
		public ExpCSContext ifexp;
		public ExpCSContext thenexp;
		public ExpCSContext elseexp;
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public IfExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterIfExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitIfExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitIfExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfExpCSContext ifExpCS() throws RecognitionException {
		IfExpCSContext _localctx = new IfExpCSContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ifExpCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			match(T__42);
			setState(355); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(354);
				((IfExpCSContext)_localctx).ifexp = expCS();
				}
				}
				setState(357); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__39) | (1L << T__42) | (1L << T__46) | (1L << T__51) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UnaryOperatorCS - 64)) | (1L << (InvalIDLiteralExpCS - 64)) | (1L << (NumberLiteralExpCS - 64)) | (1L << (BooleanLiteralExpCS - 64)) | (1L << (NullLiteralExpCS - 64)) | (1L << (ID - 64)) | (1L << (STRING - 64)))) != 0) );
			setState(359);
			match(T__43);
			setState(361); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(360);
				((IfExpCSContext)_localctx).thenexp = expCS();
				}
				}
				setState(363); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__39) | (1L << T__42) | (1L << T__46) | (1L << T__51) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UnaryOperatorCS - 64)) | (1L << (InvalIDLiteralExpCS - 64)) | (1L << (NumberLiteralExpCS - 64)) | (1L << (BooleanLiteralExpCS - 64)) | (1L << (NullLiteralExpCS - 64)) | (1L << (ID - 64)) | (1L << (STRING - 64)))) != 0) );
			setState(365);
			match(T__44);
			setState(367); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(366);
				((IfExpCSContext)_localctx).elseexp = expCS();
				}
				}
				setState(369); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__39) | (1L << T__42) | (1L << T__46) | (1L << T__51) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UnaryOperatorCS - 64)) | (1L << (InvalIDLiteralExpCS - 64)) | (1L << (NumberLiteralExpCS - 64)) | (1L << (BooleanLiteralExpCS - 64)) | (1L << (NullLiteralExpCS - 64)) | (1L << (ID - 64)) | (1L << (STRING - 64)))) != 0) );
			setState(371);
			match(T__45);
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

	public static class LetExpCSContext extends ParserRuleContext {
		public ExpCSContext in;
		public List<LetVariableCSContext> letVariableCS() {
			return getRuleContexts(LetVariableCSContext.class);
		}
		public LetVariableCSContext letVariableCS(int i) {
			return getRuleContext(LetVariableCSContext.class,i);
		}
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public LetExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterLetExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitLetExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitLetExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetExpCSContext letExpCS() throws RecognitionException {
		LetExpCSContext _localctx = new LetExpCSContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_letExpCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(T__46);
			setState(374);
			letVariableCS();
			setState(379);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(375);
				match(T__3);
				setState(376);
				letVariableCS();
				}
				}
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(382);
			match(T__47);
			setState(384); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(383);
					((LetExpCSContext)_localctx).in = expCS();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(386); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class LetVariableCSContext extends ParserRuleContext {
		public Token name;
		public TypeExpCSContext type;
		public ExpCSContext exp;
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public LetVariableCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letVariableCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterLetVariableCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitLetVariableCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitLetVariableCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetVariableCSContext letVariableCS() throws RecognitionException {
		LetVariableCSContext _localctx = new LetVariableCSContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_letVariableCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			((LetVariableCSContext)_localctx).name = match(ID);
			setState(389);
			match(T__0);
			setState(390);
			((LetVariableCSContext)_localctx).type = typeExpCS();
			setState(391);
			match(T__10);
			setState(393); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(392);
				((LetVariableCSContext)_localctx).exp = expCS();
				}
				}
				setState(395); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__39) | (1L << T__42) | (1L << T__46) | (1L << T__51) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UnaryOperatorCS - 64)) | (1L << (InvalIDLiteralExpCS - 64)) | (1L << (NumberLiteralExpCS - 64)) | (1L << (BooleanLiteralExpCS - 64)) | (1L << (NullLiteralExpCS - 64)) | (1L << (ID - 64)) | (1L << (STRING - 64)))) != 0) );
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

	public static class TypeLiteralExpCSContext extends ParserRuleContext {
		public TypeLiteralCSContext typeLiteralCS() {
			return getRuleContext(TypeLiteralCSContext.class,0);
		}
		public TypeLiteralExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeLiteralExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTypeLiteralExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTypeLiteralExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTypeLiteralExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeLiteralExpCSContext typeLiteralExpCS() throws RecognitionException {
		TypeLiteralExpCSContext _localctx = new TypeLiteralExpCSContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_typeLiteralExpCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			typeLiteralCS();
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

	public static class CollectionLiteralExpCSContext extends ParserRuleContext {
		public CollectionTypeCSContext collectionTypeCS() {
			return getRuleContext(CollectionTypeCSContext.class,0);
		}
		public List<CollectionLiteralPartCSContext> collectionLiteralPartCS() {
			return getRuleContexts(CollectionLiteralPartCSContext.class);
		}
		public CollectionLiteralPartCSContext collectionLiteralPartCS(int i) {
			return getRuleContext(CollectionLiteralPartCSContext.class,i);
		}
		public CollectionLiteralExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collectionLiteralExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterCollectionLiteralExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitCollectionLiteralExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitCollectionLiteralExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CollectionLiteralExpCSContext collectionLiteralExpCS() throws RecognitionException {
		CollectionLiteralExpCSContext _localctx = new CollectionLiteralExpCSContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_collectionLiteralExpCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			collectionTypeCS();
			setState(400);
			match(T__48);
			setState(409);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__11) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__39) | (1L << T__42) | (1L << T__46) | (1L << T__51) | (1L << T__57) | (1L << T__58))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UnaryOperatorCS - 64)) | (1L << (InvalIDLiteralExpCS - 64)) | (1L << (NumberLiteralExpCS - 64)) | (1L << (BooleanLiteralExpCS - 64)) | (1L << (NullLiteralExpCS - 64)) | (1L << (ID - 64)) | (1L << (STRING - 64)))) != 0)) {
				{
				setState(401);
				collectionLiteralPartCS();
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(402);
					match(T__3);
					setState(403);
					collectionLiteralPartCS();
					}
					}
					setState(408);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(411);
			match(T__49);
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

	public static class CollectionLiteralPartCSContext extends ParserRuleContext {
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public CollectionLiteralPartCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collectionLiteralPartCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterCollectionLiteralPartCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitCollectionLiteralPartCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitCollectionLiteralPartCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CollectionLiteralPartCSContext collectionLiteralPartCS() throws RecognitionException {
		CollectionLiteralPartCSContext _localctx = new CollectionLiteralPartCSContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_collectionLiteralPartCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			expCS();
			setState(416);
			_la = _input.LA(1);
			if (_la==T__50) {
				{
				setState(414);
				match(T__50);
				setState(415);
				expCS();
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

	public static class TupleLiteralExpCSContext extends ParserRuleContext {
		public List<TupleLiteralPartCSContext> tupleLiteralPartCS() {
			return getRuleContexts(TupleLiteralPartCSContext.class);
		}
		public TupleLiteralPartCSContext tupleLiteralPartCS(int i) {
			return getRuleContext(TupleLiteralPartCSContext.class,i);
		}
		public TupleLiteralExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleLiteralExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTupleLiteralExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTupleLiteralExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTupleLiteralExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleLiteralExpCSContext tupleLiteralExpCS() throws RecognitionException {
		TupleLiteralExpCSContext _localctx = new TupleLiteralExpCSContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_tupleLiteralExpCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			match(T__11);
			setState(419);
			match(T__48);
			setState(420);
			tupleLiteralPartCS();
			setState(425);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(421);
				match(T__3);
				setState(422);
				tupleLiteralPartCS();
				}
				}
				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(428);
			match(T__49);
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

	public static class TupleLiteralPartCSContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public ExpCSContext expCS() {
			return getRuleContext(ExpCSContext.class,0);
		}
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public TupleLiteralPartCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupleLiteralPartCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterTupleLiteralPartCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitTupleLiteralPartCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitTupleLiteralPartCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleLiteralPartCSContext tupleLiteralPartCS() throws RecognitionException {
		TupleLiteralPartCSContext _localctx = new TupleLiteralPartCSContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_tupleLiteralPartCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(ID);
			setState(433);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(431);
				match(T__0);
				setState(432);
				typeExpCS();
				}
			}

			setState(435);
			match(T__10);
			setState(436);
			expCS();
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

	public static class SelfExpCSContext extends ParserRuleContext {
		public SelfExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selfExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterSelfExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitSelfExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitSelfExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelfExpCSContext selfExpCS() throws RecognitionException {
		SelfExpCSContext _localctx = new SelfExpCSContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_selfExpCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			match(T__51);
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

	public static class PrimitiveLiteralExpCSContext extends ParserRuleContext {
		public PrimitiveLiteralExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveLiteralExpCS; }
	 
		public PrimitiveLiteralExpCSContext() { }
		public void copyFrom(PrimitiveLiteralExpCSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NumberContext extends PrimitiveLiteralExpCSContext {
		public TerminalNode NumberLiteralExpCS() { return getToken(DeepOclParser.NumberLiteralExpCS, 0); }
		public NumberContext(PrimitiveLiteralExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanContext extends PrimitiveLiteralExpCSContext {
		public TerminalNode BooleanLiteralExpCS() { return getToken(DeepOclParser.BooleanLiteralExpCS, 0); }
		public BooleanContext(PrimitiveLiteralExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringContext extends PrimitiveLiteralExpCSContext {
		public TerminalNode STRING() { return getToken(DeepOclParser.STRING, 0); }
		public StringContext(PrimitiveLiteralExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullContext extends PrimitiveLiteralExpCSContext {
		public TerminalNode NullLiteralExpCS() { return getToken(DeepOclParser.NullLiteralExpCS, 0); }
		public NullContext(PrimitiveLiteralExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InvalidContext extends PrimitiveLiteralExpCSContext {
		public TerminalNode InvalIDLiteralExpCS() { return getToken(DeepOclParser.InvalIDLiteralExpCS, 0); }
		public InvalidContext(PrimitiveLiteralExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterInvalid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitInvalid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitInvalid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveLiteralExpCSContext primitiveLiteralExpCS() throws RecognitionException {
		PrimitiveLiteralExpCSContext _localctx = new PrimitiveLiteralExpCSContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_primitiveLiteralExpCS);
		try {
			setState(445);
			switch (_input.LA(1)) {
			case NumberLiteralExpCS:
				_localctx = new NumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(440);
				match(NumberLiteralExpCS);
				}
				break;
			case STRING:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(441);
				match(STRING);
				}
				break;
			case BooleanLiteralExpCS:
				_localctx = new BooleanContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(442);
				match(BooleanLiteralExpCS);
				}
				break;
			case InvalIDLiteralExpCS:
				_localctx = new InvalidContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(443);
				match(InvalIDLiteralExpCS);
				}
				break;
			case NullLiteralExpCS:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(444);
				match(NullLiteralExpCS);
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

	public static class NavigatingExpCSContext extends ParserRuleContext {
		public IndexExpCSContext opName;
		public NavigatingArgCSContext arg;
		public NavigatingCommaArgCSContext commaArg;
		public NavigatingSemiAgrsCSContext semiArg;
		public NavigatingBarAgrsCSContext barArg;
		public IndexExpCSContext indexExpCS() {
			return getRuleContext(IndexExpCSContext.class,0);
		}
		public List<OnespaceContext> onespace() {
			return getRuleContexts(OnespaceContext.class);
		}
		public OnespaceContext onespace(int i) {
			return getRuleContext(OnespaceContext.class,i);
		}
		public List<NavigatingArgCSContext> navigatingArgCS() {
			return getRuleContexts(NavigatingArgCSContext.class);
		}
		public NavigatingArgCSContext navigatingArgCS(int i) {
			return getRuleContext(NavigatingArgCSContext.class,i);
		}
		public List<NavigatingCommaArgCSContext> navigatingCommaArgCS() {
			return getRuleContexts(NavigatingCommaArgCSContext.class);
		}
		public NavigatingCommaArgCSContext navigatingCommaArgCS(int i) {
			return getRuleContext(NavigatingCommaArgCSContext.class,i);
		}
		public List<NavigatingSemiAgrsCSContext> navigatingSemiAgrsCS() {
			return getRuleContexts(NavigatingSemiAgrsCSContext.class);
		}
		public NavigatingSemiAgrsCSContext navigatingSemiAgrsCS(int i) {
			return getRuleContext(NavigatingSemiAgrsCSContext.class,i);
		}
		public List<NavigatingBarAgrsCSContext> navigatingBarAgrsCS() {
			return getRuleContexts(NavigatingBarAgrsCSContext.class);
		}
		public NavigatingBarAgrsCSContext navigatingBarAgrsCS(int i) {
			return getRuleContext(NavigatingBarAgrsCSContext.class,i);
		}
		public NavigatingExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigatingExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNavigatingExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNavigatingExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNavigatingExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigatingExpCSContext navigatingExpCS() throws RecognitionException {
		NavigatingExpCSContext _localctx = new NavigatingExpCSContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_navigatingExpCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			((NavigatingExpCSContext)_localctx).opName = indexExpCS();
			setState(450);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(448);
				match(T__52);
				setState(449);
				match(T__8);
				}
				break;
			}
			setState(489);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(452);
					match(T__2);
					setState(454);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
					case 1:
						{
						setState(453);
						match(T__53);
						}
						break;
					}
					setState(457);
					_la = _input.LA(1);
					if (_la==ONESPACE) {
						{
						setState(456);
						onespace();
						}
					}

					setState(462);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(459);
							((NavigatingExpCSContext)_localctx).arg = navigatingArgCS();
							}
							} 
						}
						setState(464);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
					}
					setState(468);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__3) {
						{
						{
						setState(465);
						((NavigatingExpCSContext)_localctx).commaArg = navigatingCommaArgCS();
						}
						}
						setState(470);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(474);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__54) {
						{
						{
						setState(471);
						((NavigatingExpCSContext)_localctx).semiArg = navigatingSemiAgrsCS();
						}
						}
						setState(476);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(480);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__39) {
						{
						{
						setState(477);
						((NavigatingExpCSContext)_localctx).barArg = navigatingBarAgrsCS();
						}
						}
						setState(482);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(484);
					_la = _input.LA(1);
					if (_la==T__53) {
						{
						setState(483);
						match(T__53);
						}
					}

					setState(486);
					match(T__4);
					}
					} 
				}
				setState(491);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
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

	public static class NavigatingSemiAgrsCSContext extends ParserRuleContext {
		public NavigatingArgExpCSContext var;
		public TypeExpCSContext typeName;
		public ExpCSContext exp;
		public NavigatingArgExpCSContext navigatingArgExpCS() {
			return getRuleContext(NavigatingArgExpCSContext.class,0);
		}
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public ExpCSContext expCS() {
			return getRuleContext(ExpCSContext.class,0);
		}
		public NavigatingSemiAgrsCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigatingSemiAgrsCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNavigatingSemiAgrsCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNavigatingSemiAgrsCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNavigatingSemiAgrsCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigatingSemiAgrsCSContext navigatingSemiAgrsCS() throws RecognitionException {
		NavigatingSemiAgrsCSContext _localctx = new NavigatingSemiAgrsCSContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_navigatingSemiAgrsCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			match(T__54);
			setState(493);
			((NavigatingSemiAgrsCSContext)_localctx).var = navigatingArgExpCS();
			setState(496);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(494);
				match(T__0);
				setState(495);
				((NavigatingSemiAgrsCSContext)_localctx).typeName = typeExpCS();
				}
			}

			setState(500);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(498);
				match(T__10);
				setState(499);
				((NavigatingSemiAgrsCSContext)_localctx).exp = expCS();
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

	public static class NavigatingCommaArgCSContext extends ParserRuleContext {
		public NavigatingArgExpCSContext navigatingArgExpCS() {
			return getRuleContext(NavigatingArgExpCSContext.class,0);
		}
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public NavigatingCommaArgCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigatingCommaArgCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNavigatingCommaArgCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNavigatingCommaArgCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNavigatingCommaArgCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigatingCommaArgCSContext navigatingCommaArgCS() throws RecognitionException {
		NavigatingCommaArgCSContext _localctx = new NavigatingCommaArgCSContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_navigatingCommaArgCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			match(T__3);
			setState(503);
			navigatingArgExpCS();
			setState(506);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(504);
				match(T__0);
				setState(505);
				typeExpCS();
				}
			}

			setState(514);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(508);
				match(T__10);
				setState(510); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(509);
						expCS();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(512); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class NavigatingArgExpCSContext extends ParserRuleContext {
		public InfixedExpCSContext iteratorVariable;
		public NameExpCSContext name;
		public InfixedExpCSContext body;
		public IteratorBarExpCSContext iteratorBarExpCS() {
			return getRuleContext(IteratorBarExpCSContext.class,0);
		}
		public NavigationOperatorCSContext navigationOperatorCS() {
			return getRuleContext(NavigationOperatorCSContext.class,0);
		}
		public List<InfixedExpCSContext> infixedExpCS() {
			return getRuleContexts(InfixedExpCSContext.class);
		}
		public InfixedExpCSContext infixedExpCS(int i) {
			return getRuleContext(InfixedExpCSContext.class,i);
		}
		public NameExpCSContext nameExpCS() {
			return getRuleContext(NameExpCSContext.class,0);
		}
		public NavigatingArgExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigatingArgExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNavigatingArgExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNavigatingArgExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNavigatingArgExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigatingArgExpCSContext navigatingArgExpCS() throws RecognitionException {
		NavigatingArgExpCSContext _localctx = new NavigatingArgExpCSContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_navigatingArgExpCS);
		try {
			int _alt;
			setState(531);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(516);
				((NavigatingArgExpCSContext)_localctx).iteratorVariable = infixedExpCS(0);
				setState(517);
				iteratorBarExpCS();
				setState(518);
				((NavigatingArgExpCSContext)_localctx).name = nameExpCS();
				setState(519);
				navigationOperatorCS();
				setState(523);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(520);
						((NavigatingArgExpCSContext)_localctx).body = infixedExpCS(0);
						}
						} 
					}
					setState(525);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(527); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(526);
						infixedExpCS(0);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(529); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class NavigatingBarAgrsCSContext extends ParserRuleContext {
		public NavigatingArgExpCSContext var;
		public TypeExpCSContext type;
		public NavigatingArgExpCSContext navigatingArgExpCS() {
			return getRuleContext(NavigatingArgExpCSContext.class,0);
		}
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public NavigatingBarAgrsCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigatingBarAgrsCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNavigatingBarAgrsCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNavigatingBarAgrsCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNavigatingBarAgrsCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigatingBarAgrsCSContext navigatingBarAgrsCS() throws RecognitionException {
		NavigatingBarAgrsCSContext _localctx = new NavigatingBarAgrsCSContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_navigatingBarAgrsCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(533);
			match(T__39);
			setState(534);
			((NavigatingBarAgrsCSContext)_localctx).var = navigatingArgExpCS();
			setState(537);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(535);
				match(T__0);
				setState(536);
				((NavigatingBarAgrsCSContext)_localctx).type = typeExpCS();
				}
			}

			setState(545);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(539);
				match(T__10);
				setState(541); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(540);
						expCS();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(543); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class NavigatingArgCSContext extends ParserRuleContext {
		public NavigatingArgExpCSContext navigatingArgExpCS() {
			return getRuleContext(NavigatingArgExpCSContext.class,0);
		}
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public NavigatingArgCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_navigatingArgCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterNavigatingArgCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitNavigatingArgCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitNavigatingArgCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NavigatingArgCSContext navigatingArgCS() throws RecognitionException {
		NavigatingArgCSContext _localctx = new NavigatingArgCSContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_navigatingArgCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(547);
			navigatingArgExpCS();
			setState(550);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(548);
				match(T__0);
				setState(549);
				typeExpCS();
				}
			}

			setState(558);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(552);
				match(T__10);
				setState(554); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(553);
						expCS();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(556); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class IndexExpCSContext extends ParserRuleContext {
		public NameExpCSContext nameExpCS() {
			return getRuleContext(NameExpCSContext.class,0);
		}
		public List<ExpCSContext> expCS() {
			return getRuleContexts(ExpCSContext.class);
		}
		public ExpCSContext expCS(int i) {
			return getRuleContext(ExpCSContext.class,i);
		}
		public IndexExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexExpCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterIndexExpCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitIndexExpCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitIndexExpCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexExpCSContext indexExpCS() throws RecognitionException {
		IndexExpCSContext _localctx = new IndexExpCSContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_indexExpCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			nameExpCS();
			setState(572);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				{
				setState(561);
				match(T__55);
				setState(562);
				expCS();
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(563);
					match(T__3);
					setState(564);
					expCS();
					}
					}
					setState(569);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(570);
				match(T__56);
				}
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

	public static class NameExpCSContext extends ParserRuleContext {
		public NameExpCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameExpCS; }
	 
		public NameExpCSContext() { }
		public void copyFrom(NameExpCSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LinguisticalNameContext extends NameExpCSContext {
		public Token aspect;
		public List<TerminalNode> ID() { return getTokens(DeepOclParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DeepOclParser.ID, i);
		}
		public List<TerminalNode> NumberLiteralExpCS() { return getTokens(DeepOclParser.NumberLiteralExpCS); }
		public TerminalNode NumberLiteralExpCS(int i) {
			return getToken(DeepOclParser.NumberLiteralExpCS, i);
		}
		public LinguisticalNameContext(NameExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterLinguisticalName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitLinguisticalName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitLinguisticalName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NameContext extends NameExpCSContext {
		public Token variableName;
		public TerminalNode STRING() { return getToken(DeepOclParser.STRING, 0); }
		public List<TerminalNode> ID() { return getTokens(DeepOclParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DeepOclParser.ID, i);
		}
		public NameContext(NameExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OntologicalNameContext extends NameExpCSContext {
		public Token clab;
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public OntologicalNameContext(NameExpCSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterOntologicalName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitOntologicalName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitOntologicalName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameExpCSContext nameExpCS() throws RecognitionException {
		NameExpCSContext _localctx = new NameExpCSContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_nameExpCS);
		int _la;
		try {
			int _alt;
			setState(608);
			switch (_input.LA(1)) {
			case ID:
			case STRING:
				_localctx = new NameContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(586);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					{
					{
					setState(574);
					match(ID);
					setState(575);
					match(T__1);
					setState(580);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(576);
							match(ID);
							setState(577);
							match(T__1);
							}
							} 
						}
						setState(582);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
					}
					setState(583);
					match(ID);
					}
					}
					break;
				case 2:
					{
					setState(584);
					((NameContext)_localctx).variableName = match(ID);
					}
					break;
				case 3:
					{
					setState(585);
					match(STRING);
					}
					break;
				}
				}
				break;
			case T__57:
				_localctx = new OntologicalNameContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(588);
				match(T__57);
				setState(589);
				((OntologicalNameContext)_localctx).clab = match(ID);
				setState(590);
				match(T__57);
				}
				break;
			case T__58:
				_localctx = new LinguisticalNameContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(591);
				match(T__58);
				setState(592);
				((LinguisticalNameContext)_localctx).aspect = match(ID);
				setState(605);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(593);
					match(T__2);
					setState(595);
					_la = _input.LA(1);
					if (_la==NumberLiteralExpCS || _la==ID) {
						{
						setState(594);
						_la = _input.LA(1);
						if ( !(_la==NumberLiteralExpCS || _la==ID) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
					}

					setState(601);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__3) {
						{
						{
						setState(597);
						match(T__3);
						setState(598);
						_la = _input.LA(1);
						if ( !(_la==NumberLiteralExpCS || _la==ID) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						}
						setState(603);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(604);
					match(T__4);
					}
				}

				setState(607);
				match(T__58);
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

	public static class ParameterCSContext extends ParserRuleContext {
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public ParameterCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterParameterCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitParameterCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitParameterCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterCSContext parameterCS() throws RecognitionException {
		ParameterCSContext _localctx = new ParameterCSContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_parameterCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(612);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				setState(610);
				match(ID);
				setState(611);
				match(T__0);
				}
				break;
			}
			setState(614);
			typeExpCS();
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

	public static class InvCSContext extends ParserRuleContext {
		public List<SpecificationCSContext> specificationCS() {
			return getRuleContexts(SpecificationCSContext.class);
		}
		public SpecificationCSContext specificationCS(int i) {
			return getRuleContext(SpecificationCSContext.class,i);
		}
		public TerminalNode ID() { return getToken(DeepOclParser.ID, 0); }
		public InvCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterInvCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitInvCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitInvCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvCSContext invCS() throws RecognitionException {
		InvCSContext _localctx = new InvCSContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_invCS);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616);
			match(T__59);
			setState(624);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(617);
				match(ID);
				setState(622);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(618);
					match(T__2);
					setState(619);
					specificationCS();
					setState(620);
					match(T__4);
					}
				}

				}
			}

			setState(626);
			match(T__0);
			setState(627);
			specificationCS();
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

	public static class ClassifierContextCSContext extends ParserRuleContext {
		public TerminalNode CONTEXT() { return getToken(DeepOclParser.CONTEXT, 0); }
		public List<TerminalNode> ID() { return getTokens(DeepOclParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DeepOclParser.ID, i);
		}
		public LevelSpecificationCSContext levelSpecificationCS() {
			return getRuleContext(LevelSpecificationCSContext.class,0);
		}
		public List<InvCSContext> invCS() {
			return getRuleContexts(InvCSContext.class);
		}
		public InvCSContext invCS(int i) {
			return getRuleContext(InvCSContext.class,i);
		}
		public List<DefCSContext> defCS() {
			return getRuleContexts(DefCSContext.class);
		}
		public DefCSContext defCS(int i) {
			return getRuleContext(DefCSContext.class,i);
		}
		public ClassifierContextCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classifierContextCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterClassifierContextCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitClassifierContextCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitClassifierContextCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassifierContextCSContext classifierContextCS() throws RecognitionException {
		ClassifierContextCSContext _localctx = new ClassifierContextCSContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_classifierContextCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(629);
			match(CONTEXT);
			setState(631);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(630);
				levelSpecificationCS();
				}
			}

			setState(635);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				{
				setState(633);
				match(ID);
				setState(634);
				match(T__0);
				}
				break;
			}
			setState(648);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
			case 1:
				{
				{
				setState(637);
				match(ID);
				setState(638);
				match(T__1);
				setState(643);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(639);
						match(ID);
						setState(640);
						match(T__1);
						}
						} 
					}
					setState(645);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
				}
				setState(646);
				match(ID);
				}
				}
				break;
			case 2:
				{
				setState(647);
				match(ID);
				}
				break;
			}
			setState(654);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==T__59) {
				{
				setState(652);
				switch (_input.LA(1)) {
				case T__59:
					{
					setState(650);
					invCS();
					}
					break;
				case T__9:
					{
					setState(651);
					defCS();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(656);
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

	public static class PropertyContextDeclCSContext extends ParserRuleContext {
		public TerminalNode CONTEXT() { return getToken(DeepOclParser.CONTEXT, 0); }
		public TypeExpCSContext typeExpCS() {
			return getRuleContext(TypeExpCSContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(DeepOclParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DeepOclParser.ID, i);
		}
		public DerCSContext derCS() {
			return getRuleContext(DerCSContext.class,0);
		}
		public LevelSpecificationCSContext levelSpecificationCS() {
			return getRuleContext(LevelSpecificationCSContext.class,0);
		}
		public InitCSContext initCS() {
			return getRuleContext(InitCSContext.class,0);
		}
		public PropertyContextDeclCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyContextDeclCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterPropertyContextDeclCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitPropertyContextDeclCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitPropertyContextDeclCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContextDeclCSContext propertyContextDeclCS() throws RecognitionException {
		PropertyContextDeclCSContext _localctx = new PropertyContextDeclCSContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_propertyContextDeclCS);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(657);
			match(CONTEXT);
			setState(659);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(658);
				levelSpecificationCS();
				}
			}

			setState(672);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
			case 1:
				{
				{
				setState(661);
				match(ID);
				setState(662);
				match(T__1);
				setState(667);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(663);
						match(ID);
						setState(664);
						match(T__1);
						}
						} 
					}
					setState(669);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
				}
				setState(670);
				match(ID);
				}
				}
				break;
			case 2:
				{
				setState(671);
				match(ID);
				}
				break;
			}
			setState(674);
			match(T__0);
			setState(675);
			typeExpCS();
			setState(686);
			switch (_input.LA(1)) {
			case EOF:
			case T__61:
			case CONTEXT:
				{
				setState(680);
				_la = _input.LA(1);
				if (_la==T__61) {
					{
					setState(676);
					initCS();
					setState(678);
					_la = _input.LA(1);
					if (_la==T__60) {
						{
						setState(677);
						derCS();
						}
					}

					}
				}

				}
				break;
			case T__60:
				{
				setState(682);
				derCS();
				setState(684);
				_la = _input.LA(1);
				if (_la==T__61) {
					{
					setState(683);
					initCS();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class DerCSContext extends ParserRuleContext {
		public SpecificationCSContext specificationCS() {
			return getRuleContext(SpecificationCSContext.class,0);
		}
		public DerCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterDerCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitDerCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitDerCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DerCSContext derCS() throws RecognitionException {
		DerCSContext _localctx = new DerCSContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_derCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(688);
			match(T__60);
			setState(689);
			match(T__0);
			setState(690);
			specificationCS();
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

	public static class InitCSContext extends ParserRuleContext {
		public SpecificationCSContext specificationCS() {
			return getRuleContext(SpecificationCSContext.class,0);
		}
		public InitCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterInitCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitInitCS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitInitCS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitCSContext initCS() throws RecognitionException {
		InitCSContext _localctx = new InitCSContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_initCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692);
			match(T__61);
			setState(693);
			match(T__0);
			setState(694);
			specificationCS();
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

	public static class OnespaceContext extends ParserRuleContext {
		public TerminalNode ONESPACE() { return getToken(DeepOclParser.ONESPACE, 0); }
		public OnespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).enterOnespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DeepOclListener ) ((DeepOclListener)listener).exitOnespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DeepOclVisitor ) return ((DeepOclVisitor<? extends T>)visitor).visitOnespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OnespaceContext onespace() throws RecognitionException {
		OnespaceContext _localctx = new OnespaceContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_onespace);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(696);
			match(ONESPACE);
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
		case 17:
			return infixedExpCS_sempred((InfixedExpCSContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean infixedExpCS_sempred(InfixedExpCSContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3M\u02bd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\2\6\2f\n\2\r\2\16"+
		"\2g\3\3\3\3\5\3l\n\3\3\3\3\3\5\3p\n\3\3\3\3\3\3\3\3\3\7\3v\n\3\f\3\16"+
		"\3y\13\3\3\3\3\3\5\3}\n\3\3\3\3\3\3\3\3\3\7\3\u0083\n\3\f\3\16\3\u0086"+
		"\13\3\5\3\u0088\n\3\3\3\3\3\3\3\5\3\u008d\n\3\3\3\3\3\3\3\7\3\u0092\n"+
		"\3\f\3\16\3\u0095\13\3\3\4\3\4\3\4\3\4\5\4\u009b\n\4\3\4\3\4\3\5\3\5\5"+
		"\5\u00a1\n\5\3\5\3\5\3\5\3\6\3\6\5\6\u00a8\n\6\3\6\3\6\3\6\3\7\3\7\5\7"+
		"\u00af\n\7\3\7\3\7\3\7\3\b\3\b\5\b\u00b6\n\b\3\b\3\b\3\b\3\b\5\b\u00bc"+
		"\n\b\3\b\3\b\7\b\u00c0\n\b\f\b\16\b\u00c3\13\b\3\b\5\b\u00c6\n\b\3\b\3"+
		"\b\5\b\u00ca\n\b\3\b\3\b\3\b\3\t\3\t\5\t\u00d1\n\t\3\n\3\n\3\n\5\n\u00d6"+
		"\n\n\3\13\3\13\3\13\3\13\3\13\7\13\u00dd\n\13\f\13\16\13\u00e0\13\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\7\13\u00e8\n\13\f\13\16\13\u00eb\13\13\3"+
		"\13\3\13\5\13\u00ef\n\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\5\r\u00fe\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u0108"+
		"\n\20\f\20\16\20\u010b\13\20\3\20\3\20\5\20\u010f\n\20\3\21\7\21\u0112"+
		"\n\21\f\21\16\21\u0115\13\21\3\22\3\22\3\23\3\23\3\23\5\23\u011c\n\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\7\23\u0130\n\23\f\23\16\23\u0133\13\23\3\24\3\24"+
		"\3\25\3\25\5\25\u0139\n\25\3\26\6\26\u013c\n\26\r\26\16\26\u013d\3\26"+
		"\3\26\3\26\3\26\3\26\7\26\u0145\n\26\f\26\16\26\u0148\13\26\3\26\5\26"+
		"\u014b\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0156\n"+
		"\27\3\30\3\30\6\30\u015a\n\30\r\30\16\30\u015b\3\30\3\30\6\30\u0160\n"+
		"\30\r\30\16\30\u0161\3\31\3\31\6\31\u0166\n\31\r\31\16\31\u0167\3\31\3"+
		"\31\6\31\u016c\n\31\r\31\16\31\u016d\3\31\3\31\6\31\u0172\n\31\r\31\16"+
		"\31\u0173\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u017c\n\32\f\32\16\32\u017f"+
		"\13\32\3\32\3\32\6\32\u0183\n\32\r\32\16\32\u0184\3\33\3\33\3\33\3\33"+
		"\3\33\6\33\u018c\n\33\r\33\16\33\u018d\3\34\3\34\3\35\3\35\3\35\3\35\3"+
		"\35\7\35\u0197\n\35\f\35\16\35\u019a\13\35\5\35\u019c\n\35\3\35\3\35\3"+
		"\36\3\36\3\36\5\36\u01a3\n\36\3\37\3\37\3\37\3\37\3\37\7\37\u01aa\n\37"+
		"\f\37\16\37\u01ad\13\37\3\37\3\37\3 \3 \3 \5 \u01b4\n \3 \3 \3 \3!\3!"+
		"\3\"\3\"\3\"\3\"\3\"\5\"\u01c0\n\"\3#\3#\3#\5#\u01c5\n#\3#\3#\5#\u01c9"+
		"\n#\3#\5#\u01cc\n#\3#\7#\u01cf\n#\f#\16#\u01d2\13#\3#\7#\u01d5\n#\f#\16"+
		"#\u01d8\13#\3#\7#\u01db\n#\f#\16#\u01de\13#\3#\7#\u01e1\n#\f#\16#\u01e4"+
		"\13#\3#\5#\u01e7\n#\3#\7#\u01ea\n#\f#\16#\u01ed\13#\3$\3$\3$\3$\5$\u01f3"+
		"\n$\3$\3$\5$\u01f7\n$\3%\3%\3%\3%\5%\u01fd\n%\3%\3%\6%\u0201\n%\r%\16"+
		"%\u0202\5%\u0205\n%\3&\3&\3&\3&\3&\7&\u020c\n&\f&\16&\u020f\13&\3&\6&"+
		"\u0212\n&\r&\16&\u0213\5&\u0216\n&\3\'\3\'\3\'\3\'\5\'\u021c\n\'\3\'\3"+
		"\'\6\'\u0220\n\'\r\'\16\'\u0221\5\'\u0224\n\'\3(\3(\3(\5(\u0229\n(\3("+
		"\3(\6(\u022d\n(\r(\16(\u022e\5(\u0231\n(\3)\3)\3)\3)\3)\7)\u0238\n)\f"+
		")\16)\u023b\13)\3)\3)\5)\u023f\n)\3*\3*\3*\3*\7*\u0245\n*\f*\16*\u0248"+
		"\13*\3*\3*\3*\5*\u024d\n*\3*\3*\3*\3*\3*\3*\3*\5*\u0256\n*\3*\3*\7*\u025a"+
		"\n*\f*\16*\u025d\13*\3*\5*\u0260\n*\3*\5*\u0263\n*\3+\3+\5+\u0267\n+\3"+
		"+\3+\3,\3,\3,\3,\3,\3,\5,\u0271\n,\5,\u0273\n,\3,\3,\3,\3-\3-\5-\u027a"+
		"\n-\3-\3-\5-\u027e\n-\3-\3-\3-\3-\7-\u0284\n-\f-\16-\u0287\13-\3-\3-\5"+
		"-\u028b\n-\3-\3-\7-\u028f\n-\f-\16-\u0292\13-\3.\3.\5.\u0296\n.\3.\3."+
		"\3.\3.\7.\u029c\n.\f.\16.\u029f\13.\3.\3.\5.\u02a3\n.\3.\3.\3.\3.\5.\u02a9"+
		"\n.\5.\u02ab\n.\3.\3.\5.\u02af\n.\5.\u02b1\n.\3/\3/\3/\3/\3\60\3\60\3"+
		"\60\3\60\3\61\3\61\3\61\2\3$\62\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`\2\n\4\2\b\bDD\3\2\21\25\3"+
		"\2\26\35\3\2\36\37\3\2 !\5\2\r\r\17\20\"$\3\2&(\4\2DDHH\u0304\2e\3\2\2"+
		"\2\4i\3\2\2\2\6\u0096\3\2\2\2\b\u009e\3\2\2\2\n\u00a5\3\2\2\2\f\u00ac"+
		"\3\2\2\2\16\u00b3\3\2\2\2\20\u00d0\3\2\2\2\22\u00d5\3\2\2\2\24\u00d7\3"+
		"\2\2\2\26\u00f0\3\2\2\2\30\u00f4\3\2\2\2\32\u00ff\3\2\2\2\34\u0101\3\2"+
		"\2\2\36\u010e\3\2\2\2 \u0113\3\2\2\2\"\u0116\3\2\2\2$\u011b\3\2\2\2&\u0134"+
		"\3\2\2\2(\u0138\3\2\2\2*\u014a\3\2\2\2,\u0155\3\2\2\2.\u015f\3\2\2\2\60"+
		"\u0163\3\2\2\2\62\u0177\3\2\2\2\64\u0186\3\2\2\2\66\u018f\3\2\2\28\u0191"+
		"\3\2\2\2:\u019f\3\2\2\2<\u01a4\3\2\2\2>\u01b0\3\2\2\2@\u01b8\3\2\2\2B"+
		"\u01bf\3\2\2\2D\u01c1\3\2\2\2F\u01ee\3\2\2\2H\u01f8\3\2\2\2J\u0215\3\2"+
		"\2\2L\u0217\3\2\2\2N\u0225\3\2\2\2P\u0232\3\2\2\2R\u0262\3\2\2\2T\u0266"+
		"\3\2\2\2V\u026a\3\2\2\2X\u0277\3\2\2\2Z\u0293\3\2\2\2\\\u02b2\3\2\2\2"+
		"^\u02b6\3\2\2\2`\u02ba\3\2\2\2bf\5Z.\2cf\5X-\2df\5\4\3\2eb\3\2\2\2ec\3"+
		"\2\2\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\3\3\2\2\2ik\7A\2\2jl\5"+
		"\6\4\2kj\3\2\2\2kl\3\2\2\2lo\3\2\2\2mn\7H\2\2np\7\3\2\2om\3\2\2\2op\3"+
		"\2\2\2p|\3\2\2\2qr\7H\2\2rw\7\4\2\2st\7H\2\2tv\7\4\2\2us\3\2\2\2vy\3\2"+
		"\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z}\7H\2\2{}\7H\2\2|q\3\2\2"+
		"\2|{\3\2\2\2}~\3\2\2\2~\u0087\7\5\2\2\177\u0084\5T+\2\u0080\u0081\7\6"+
		"\2\2\u0081\u0083\5T+\2\u0082\u0080\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0087"+
		"\177\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008c\7\7\2"+
		"\2\u008a\u008b\7\3\2\2\u008b\u008d\5\20\t\2\u008c\u008a\3\2\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u0093\3\2\2\2\u008e\u0092\5\f\7\2\u008f\u0092\5\n"+
		"\6\2\u0090\u0092\5\b\5\2\u0091\u008e\3\2\2\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2"+
		"\2\2\u0094\5\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7\5\2\2\u0097\u009a"+
		"\7D\2\2\u0098\u0099\7\6\2\2\u0099\u009b\t\2\2\2\u009a\u0098\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\7\7\2\2\u009d\7\3\2\2\2"+
		"\u009e\u00a0\7\t\2\2\u009f\u00a1\7H\2\2\u00a0\u009f\3\2\2\2\u00a0\u00a1"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\7\3\2\2\u00a3\u00a4\5 \21\2\u00a4"+
		"\t\3\2\2\2\u00a5\u00a7\7\n\2\2\u00a6\u00a8\7H\2\2\u00a7\u00a6\3\2\2\2"+
		"\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\7\3\2\2\u00aa\u00ab"+
		"\5 \21\2\u00ab\13\3\2\2\2\u00ac\u00ae\7\13\2\2\u00ad\u00af\7H\2\2\u00ae"+
		"\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\7\3"+
		"\2\2\u00b1\u00b2\5 \21\2\u00b2\r\3\2\2\2\u00b3\u00b5\7\f\2\2\u00b4\u00b6"+
		"\7H\2\2\u00b5\u00b4\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00b8\7\3\2\2\u00b8\u00c5\7H\2\2\u00b9\u00bb\7\5\2\2\u00ba\u00bc\5T+"+
		"\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00c1\3\2\2\2\u00bd\u00be"+
		"\7\6\2\2\u00be\u00c0\5T+\2\u00bf\u00bd\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2"+
		"\2\2\u00c4\u00c6\7\7\2\2\u00c5\u00b9\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c9\7\3\2\2\u00c8\u00ca\5\20\t\2\u00c9\u00c8\3"+
		"\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\7\r\2\2\u00cc"+
		"\u00cd\5 \21\2\u00cd\17\3\2\2\2\u00ce\u00d1\5\36\20\2\u00cf\u00d1\5\22"+
		"\n\2\u00d0\u00ce\3\2\2\2\u00d0\u00cf\3\2\2\2\u00d1\21\3\2\2\2\u00d2\u00d6"+
		"\5\34\17\2\u00d3\u00d6\5\30\r\2\u00d4\u00d6\5\24\13\2\u00d5\u00d2\3\2"+
		"\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d4\3\2\2\2\u00d6\23\3\2\2\2\u00d7\u00ee"+
		"\7\16\2\2\u00d8\u00d9\7\5\2\2\u00d9\u00de\5\26\f\2\u00da\u00db\7\6\2\2"+
		"\u00db\u00dd\5\26\f\2\u00dc\u00da\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc"+
		"\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e1\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1"+
		"\u00e2\7\7\2\2\u00e2\u00ef\3\2\2\2\u00e3\u00e4\7\17\2\2\u00e4\u00e9\5"+
		"\26\f\2\u00e5\u00e6\7\6\2\2\u00e6\u00e8\5\26\f\2\u00e7\u00e5\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2"+
		"\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\7\20\2\2\u00ed\u00ef\3\2\2\2\u00ee"+
		"\u00d8\3\2\2\2\u00ee\u00e3\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\25\3\2\2"+
		"\2\u00f0\u00f1\7H\2\2\u00f1\u00f2\7\3\2\2\u00f2\u00f3\5\20\t\2\u00f3\27"+
		"\3\2\2\2\u00f4\u00fd\5\32\16\2\u00f5\u00f6\7\5\2\2\u00f6\u00f7\5\20\t"+
		"\2\u00f7\u00f8\7\7\2\2\u00f8\u00fe\3\2\2\2\u00f9\u00fa\7\17\2\2\u00fa"+
		"\u00fb\5\20\t\2\u00fb\u00fc\7\20\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00f5\3"+
		"\2\2\2\u00fd\u00f9\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\31\3\2\2\2\u00ff"+
		"\u0100\t\3\2\2\u0100\33\3\2\2\2\u0101\u0102\t\4\2\2\u0102\35\3\2\2\2\u0103"+
		"\u0104\7H\2\2\u0104\u0109\7\4\2\2\u0105\u0106\7H\2\2\u0106\u0108\7\4\2"+
		"\2\u0107\u0105\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a"+
		"\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010f\7H\2\2\u010d"+
		"\u010f\7H\2\2\u010e\u0103\3\2\2\2\u010e\u010d\3\2\2\2\u010f\37\3\2\2\2"+
		"\u0110\u0112\5$\23\2\u0111\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111"+
		"\3\2\2\2\u0113\u0114\3\2\2\2\u0114!\3\2\2\2\u0115\u0113\3\2\2\2\u0116"+
		"\u0117\5$\23\2\u0117#\3\2\2\2\u0118\u0119\b\23\1\2\u0119\u011c\5*\26\2"+
		"\u011a\u011c\5&\24\2\u011b\u0118\3\2\2\2\u011b\u011a\3\2\2\2\u011c\u0131"+
		"\3\2\2\2\u011d\u011e\f\b\2\2\u011e\u011f\t\5\2\2\u011f\u0130\5$\23\t\u0120"+
		"\u0121\f\7\2\2\u0121\u0122\t\6\2\2\u0122\u0130\5$\23\b\u0123\u0124\f\6"+
		"\2\2\u0124\u0125\t\7\2\2\u0125\u0130\5$\23\7\u0126\u0127\f\5\2\2\u0127"+
		"\u0128\7%\2\2\u0128\u0130\5$\23\6\u0129\u012a\f\4\2\2\u012a\u012b\t\b"+
		"\2\2\u012b\u0130\5$\23\5\u012c\u012d\f\3\2\2\u012d\u012e\7)\2\2\u012e"+
		"\u0130\5$\23\4\u012f\u011d\3\2\2\2\u012f\u0120\3\2\2\2\u012f\u0123\3\2"+
		"\2\2\u012f\u0126\3\2\2\2\u012f\u0129\3\2\2\2\u012f\u012c\3\2\2\2\u0130"+
		"\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132%\3\2\2\2"+
		"\u0133\u0131\3\2\2\2\u0134\u0135\7*\2\2\u0135\'\3\2\2\2\u0136\u0139\7"+
		"+\2\2\u0137\u0139\7,\2\2\u0138\u0136\3\2\2\2\u0138\u0137\3\2\2\2\u0139"+
		")\3\2\2\2\u013a\u013c\7B\2\2\u013b\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d"+
		"\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u014b\5,"+
		"\27\2\u0140\u0146\5,\27\2\u0141\u0142\5(\25\2\u0142\u0143\5,\27\2\u0143"+
		"\u0145\3\2\2\2\u0144\u0141\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2"+
		"\2\2\u0146\u0147\3\2\2\2\u0147\u014b\3\2\2\2\u0148\u0146\3\2\2\2\u0149"+
		"\u014b\5,\27\2\u014a\u013b\3\2\2\2\u014a\u0140\3\2\2\2\u014a\u0149\3\2"+
		"\2\2\u014b+\3\2\2\2\u014c\u0156\5\62\32\2\u014d\u0156\5\60\31\2\u014e"+
		"\u0156\5B\"\2\u014f\u0156\5D#\2\u0150\u0156\5@!\2\u0151\u0156\5<\37\2"+
		"\u0152\u0156\58\35\2\u0153\u0156\5\66\34\2\u0154\u0156\5.\30\2\u0155\u014c"+
		"\3\2\2\2\u0155\u014d\3\2\2\2\u0155\u014e\3\2\2\2\u0155\u014f\3\2\2\2\u0155"+
		"\u0150\3\2\2\2\u0155\u0151\3\2\2\2\u0155\u0152\3\2\2\2\u0155\u0153\3\2"+
		"\2\2\u0155\u0154\3\2\2\2\u0156-\3\2\2\2\u0157\u0159\7\5\2\2\u0158\u015a"+
		"\5\"\22\2\u0159\u0158\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u0159\3\2\2\2"+
		"\u015b\u015c\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e\7\7\2\2\u015e\u0160"+
		"\3\2\2\2\u015f\u0157\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162/\3\2\2\2\u0163\u0165\7-\2\2\u0164\u0166\5\"\22\2"+
		"\u0165\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0168"+
		"\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016b\7.\2\2\u016a\u016c\5\"\22\2\u016b"+
		"\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2"+
		"\2\2\u016e\u016f\3\2\2\2\u016f\u0171\7/\2\2\u0170\u0172\5\"\22\2\u0171"+
		"\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2"+
		"\2\2\u0174\u0175\3\2\2\2\u0175\u0176\7\60\2\2\u0176\61\3\2\2\2\u0177\u0178"+
		"\7\61\2\2\u0178\u017d\5\64\33\2\u0179\u017a\7\6\2\2\u017a\u017c\5\64\33"+
		"\2\u017b\u0179\3\2\2\2\u017c\u017f\3\2\2\2\u017d\u017b\3\2\2\2\u017d\u017e"+
		"\3\2\2\2\u017e\u0180\3\2\2\2\u017f\u017d\3\2\2\2\u0180\u0182\7\62\2\2"+
		"\u0181\u0183\5\"\22\2\u0182\u0181\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0182"+
		"\3\2\2\2\u0184\u0185\3\2\2\2\u0185\63\3\2\2\2\u0186\u0187\7H\2\2\u0187"+
		"\u0188\7\3\2\2\u0188\u0189\5\20\t\2\u0189\u018b\7\r\2\2\u018a\u018c\5"+
		"\"\22\2\u018b\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018b\3\2\2\2\u018d"+
		"\u018e\3\2\2\2\u018e\65\3\2\2\2\u018f\u0190\5\22\n\2\u0190\67\3\2\2\2"+
		"\u0191\u0192\5\30\r\2\u0192\u019b\7\63\2\2\u0193\u0198\5:\36\2\u0194\u0195"+
		"\7\6\2\2\u0195\u0197\5:\36\2\u0196\u0194\3\2\2\2\u0197\u019a\3\2\2\2\u0198"+
		"\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019c\3\2\2\2\u019a\u0198\3\2"+
		"\2\2\u019b\u0193\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d\3\2\2\2\u019d"+
		"\u019e\7\64\2\2\u019e9\3\2\2\2\u019f\u01a2\5\"\22\2\u01a0\u01a1\7\65\2"+
		"\2\u01a1\u01a3\5\"\22\2\u01a2\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3"+
		";\3\2\2\2\u01a4\u01a5\7\16\2\2\u01a5\u01a6\7\63\2\2\u01a6\u01ab\5> \2"+
		"\u01a7\u01a8\7\6\2\2\u01a8\u01aa\5> \2\u01a9\u01a7\3\2\2\2\u01aa\u01ad"+
		"\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01ae\3\2\2\2\u01ad"+
		"\u01ab\3\2\2\2\u01ae\u01af\7\64\2\2\u01af=\3\2\2\2\u01b0\u01b3\7H\2\2"+
		"\u01b1\u01b2\7\3\2\2\u01b2\u01b4\5\20\t\2\u01b3\u01b1\3\2\2\2\u01b3\u01b4"+
		"\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\7\r\2\2\u01b6\u01b7\5\"\22\2"+
		"\u01b7?\3\2\2\2\u01b8\u01b9\7\66\2\2\u01b9A\3\2\2\2\u01ba\u01c0\7D\2\2"+
		"\u01bb\u01c0\7K\2\2\u01bc\u01c0\7F\2\2\u01bd\u01c0\7C\2\2\u01be\u01c0"+
		"\7G\2\2\u01bf\u01ba\3\2\2\2\u01bf\u01bb\3\2\2\2\u01bf\u01bc\3\2\2\2\u01bf"+
		"\u01bd\3\2\2\2\u01bf\u01be\3\2\2\2\u01c0C\3\2\2\2\u01c1\u01c4\5P)\2\u01c2"+
		"\u01c3\7\67\2\2\u01c3\u01c5\7\13\2\2\u01c4\u01c2\3\2\2\2\u01c4\u01c5\3"+
		"\2\2\2\u01c5\u01eb\3\2\2\2\u01c6\u01c8\7\5\2\2\u01c7\u01c9\78\2\2\u01c8"+
		"\u01c7\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01cb\3\2\2\2\u01ca\u01cc\5`"+
		"\61\2\u01cb\u01ca\3\2\2\2\u01cb\u01cc\3\2\2\2\u01cc\u01d0\3\2\2\2\u01cd"+
		"\u01cf\5N(\2\u01ce\u01cd\3\2\2\2\u01cf\u01d2\3\2\2\2\u01d0\u01ce\3\2\2"+
		"\2\u01d0\u01d1\3\2\2\2\u01d1\u01d6\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d3\u01d5"+
		"\5H%\2\u01d4\u01d3\3\2\2\2\u01d5\u01d8\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d6"+
		"\u01d7\3\2\2\2\u01d7\u01dc\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d9\u01db\5F"+
		"$\2\u01da\u01d9\3\2\2\2\u01db\u01de\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc"+
		"\u01dd\3\2\2\2\u01dd\u01e2\3\2\2\2\u01de\u01dc\3\2\2\2\u01df\u01e1\5L"+
		"\'\2\u01e0\u01df\3\2\2\2\u01e1\u01e4\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2"+
		"\u01e3\3\2\2\2\u01e3\u01e6\3\2\2\2\u01e4\u01e2\3\2\2\2\u01e5\u01e7\78"+
		"\2\2\u01e6\u01e5\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8"+
		"\u01ea\7\7\2\2\u01e9\u01c6\3\2\2\2\u01ea\u01ed\3\2\2\2\u01eb\u01e9\3\2"+
		"\2\2\u01eb\u01ec\3\2\2\2\u01ecE\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ee\u01ef"+
		"\79\2\2\u01ef\u01f2\5J&\2\u01f0\u01f1\7\3\2\2\u01f1\u01f3\5\20\t\2\u01f2"+
		"\u01f0\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f6\3\2\2\2\u01f4\u01f5\7\r"+
		"\2\2\u01f5\u01f7\5\"\22\2\u01f6\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7"+
		"G\3\2\2\2\u01f8\u01f9\7\6\2\2\u01f9\u01fc\5J&\2\u01fa\u01fb\7\3\2\2\u01fb"+
		"\u01fd\5\20\t\2\u01fc\u01fa\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd\u0204\3"+
		"\2\2\2\u01fe\u0200\7\r\2\2\u01ff\u0201\5\"\22\2\u0200\u01ff\3\2\2\2\u0201"+
		"\u0202\3\2\2\2\u0202\u0200\3\2\2\2\u0202\u0203\3\2\2\2\u0203\u0205\3\2"+
		"\2\2\u0204\u01fe\3\2\2\2\u0204\u0205\3\2\2\2\u0205I\3\2\2\2\u0206\u0207"+
		"\5$\23\2\u0207\u0208\5&\24\2\u0208\u0209\5R*\2\u0209\u020d\5(\25\2\u020a"+
		"\u020c\5$\23\2\u020b\u020a\3\2\2\2\u020c\u020f\3\2\2\2\u020d\u020b\3\2"+
		"\2\2\u020d\u020e\3\2\2\2\u020e\u0216\3\2\2\2\u020f\u020d\3\2\2\2\u0210"+
		"\u0212\5$\23\2\u0211\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0211\3\2"+
		"\2\2\u0213\u0214\3\2\2\2\u0214\u0216\3\2\2\2\u0215\u0206\3\2\2\2\u0215"+
		"\u0211\3\2\2\2\u0216K\3\2\2\2\u0217\u0218\7*\2\2\u0218\u021b\5J&\2\u0219"+
		"\u021a\7\3\2\2\u021a\u021c\5\20\t\2\u021b\u0219\3\2\2\2\u021b\u021c\3"+
		"\2\2\2\u021c\u0223\3\2\2\2\u021d\u021f\7\r\2\2\u021e\u0220\5\"\22\2\u021f"+
		"\u021e\3\2\2\2\u0220\u0221\3\2\2\2\u0221\u021f\3\2\2\2\u0221\u0222\3\2"+
		"\2\2\u0222\u0224\3\2\2\2\u0223\u021d\3\2\2\2\u0223\u0224\3\2\2\2\u0224"+
		"M\3\2\2\2\u0225\u0228\5J&\2\u0226\u0227\7\3\2\2\u0227\u0229\5\20\t\2\u0228"+
		"\u0226\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u0230\3\2\2\2\u022a\u022c\7\r"+
		"\2\2\u022b\u022d\5\"\22\2\u022c\u022b\3\2\2\2\u022d\u022e\3\2\2\2\u022e"+
		"\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0231\3\2\2\2\u0230\u022a\3\2"+
		"\2\2\u0230\u0231\3\2\2\2\u0231O\3\2\2\2\u0232\u023e\5R*\2\u0233\u0234"+
		"\7:\2\2\u0234\u0239\5\"\22\2\u0235\u0236\7\6\2\2\u0236\u0238\5\"\22\2"+
		"\u0237\u0235\3\2\2\2\u0238\u023b\3\2\2\2\u0239\u0237\3\2\2\2\u0239\u023a"+
		"\3\2\2\2\u023a\u023c\3\2\2\2\u023b\u0239\3\2\2\2\u023c\u023d\7;\2\2\u023d"+
		"\u023f\3\2\2\2\u023e\u0233\3\2\2\2\u023e\u023f\3\2\2\2\u023fQ\3\2\2\2"+
		"\u0240\u0241\7H\2\2\u0241\u0246\7\4\2\2\u0242\u0243\7H\2\2\u0243\u0245"+
		"\7\4\2\2\u0244\u0242\3\2\2\2\u0245\u0248\3\2\2\2\u0246\u0244\3\2\2\2\u0246"+
		"\u0247\3\2\2\2\u0247\u0249\3\2\2\2\u0248\u0246\3\2\2\2\u0249\u024d\7H"+
		"\2\2\u024a\u024d\7H\2\2\u024b\u024d\7K\2\2\u024c\u0240\3\2\2\2\u024c\u024a"+
		"\3\2\2\2\u024c\u024b\3\2\2\2\u024d\u0263\3\2\2\2\u024e\u024f\7<\2\2\u024f"+
		"\u0250\7H\2\2\u0250\u0263\7<\2\2\u0251\u0252\7=\2\2\u0252\u025f\7H\2\2"+
		"\u0253\u0255\7\5\2\2\u0254\u0256\t\t\2\2\u0255\u0254\3\2\2\2\u0255\u0256"+
		"\3\2\2\2\u0256\u025b\3\2\2\2\u0257\u0258\7\6\2\2\u0258\u025a\t\t\2\2\u0259"+
		"\u0257\3\2\2\2\u025a\u025d\3\2\2\2\u025b\u0259\3\2\2\2\u025b\u025c\3\2"+
		"\2\2\u025c\u025e\3\2\2\2\u025d\u025b\3\2\2\2\u025e\u0260\7\7\2\2\u025f"+
		"\u0253\3\2\2\2\u025f\u0260\3\2\2\2\u0260\u0261\3\2\2\2\u0261\u0263\7="+
		"\2\2\u0262\u024c\3\2\2\2\u0262\u024e\3\2\2\2\u0262\u0251\3\2\2\2\u0263"+
		"S\3\2\2\2\u0264\u0265\7H\2\2\u0265\u0267\7\3\2\2\u0266\u0264\3\2\2\2\u0266"+
		"\u0267\3\2\2\2\u0267\u0268\3\2\2\2\u0268\u0269\5\20\t\2\u0269U\3\2\2\2"+
		"\u026a\u0272\7>\2\2\u026b\u0270\7H\2\2\u026c\u026d\7\5\2\2\u026d\u026e"+
		"\5 \21\2\u026e\u026f\7\7\2\2\u026f\u0271\3\2\2\2\u0270\u026c\3\2\2\2\u0270"+
		"\u0271\3\2\2\2\u0271\u0273\3\2\2\2\u0272\u026b\3\2\2\2\u0272\u0273\3\2"+
		"\2\2\u0273\u0274\3\2\2\2\u0274\u0275\7\3\2\2\u0275\u0276\5 \21\2\u0276"+
		"W\3\2\2\2\u0277\u0279\7A\2\2\u0278\u027a\5\6\4\2\u0279\u0278\3\2\2\2\u0279"+
		"\u027a\3\2\2\2\u027a\u027d\3\2\2\2\u027b\u027c\7H\2\2\u027c\u027e\7\3"+
		"\2\2\u027d\u027b\3\2\2\2\u027d\u027e\3\2\2\2\u027e\u028a\3\2\2\2\u027f"+
		"\u0280\7H\2\2\u0280\u0285\7\4\2\2\u0281\u0282\7H\2\2\u0282\u0284\7\4\2"+
		"\2\u0283\u0281\3\2\2\2\u0284\u0287\3\2\2\2\u0285\u0283\3\2\2\2\u0285\u0286"+
		"\3\2\2\2\u0286\u0288\3\2\2\2\u0287\u0285\3\2\2\2\u0288\u028b\7H\2\2\u0289"+
		"\u028b\7H\2\2\u028a\u027f\3\2\2\2\u028a\u0289\3\2\2\2\u028b\u0290\3\2"+
		"\2\2\u028c\u028f\5V,\2\u028d\u028f\5\16\b\2\u028e\u028c\3\2\2\2\u028e"+
		"\u028d\3\2\2\2\u028f\u0292\3\2\2\2\u0290\u028e\3\2\2\2\u0290\u0291\3\2"+
		"\2\2\u0291Y\3\2\2\2\u0292\u0290\3\2\2\2\u0293\u0295\7A\2\2\u0294\u0296"+
		"\5\6\4\2\u0295\u0294\3\2\2\2\u0295\u0296\3\2\2\2\u0296\u02a2\3\2\2\2\u0297"+
		"\u0298\7H\2\2\u0298\u029d\7\4\2\2\u0299\u029a\7H\2\2\u029a\u029c\7\4\2"+
		"\2\u029b\u0299\3\2\2\2\u029c\u029f\3\2\2\2\u029d\u029b\3\2\2\2\u029d\u029e"+
		"\3\2\2\2\u029e\u02a0\3\2\2\2\u029f\u029d\3\2\2\2\u02a0\u02a3\7H\2\2\u02a1"+
		"\u02a3\7H\2\2\u02a2\u0297\3\2\2\2\u02a2\u02a1\3\2\2\2\u02a3\u02a4\3\2"+
		"\2\2\u02a4\u02a5\7\3\2\2\u02a5\u02b0\5\20\t\2\u02a6\u02a8\5^\60\2\u02a7"+
		"\u02a9\5\\/\2\u02a8\u02a7\3\2\2\2\u02a8\u02a9\3\2\2\2\u02a9\u02ab\3\2"+
		"\2\2\u02aa\u02a6\3\2\2\2\u02aa\u02ab\3\2\2\2\u02ab\u02b1\3\2\2\2\u02ac"+
		"\u02ae\5\\/\2\u02ad\u02af\5^\60\2\u02ae\u02ad\3\2\2\2\u02ae\u02af\3\2"+
		"\2\2\u02af\u02b1\3\2\2\2\u02b0\u02aa\3\2\2\2\u02b0\u02ac\3\2\2\2\u02b1"+
		"[\3\2\2\2\u02b2\u02b3\7?\2\2\u02b3\u02b4\7\3\2\2\u02b4\u02b5\5 \21\2\u02b5"+
		"]\3\2\2\2\u02b6\u02b7\7@\2\2\u02b7\u02b8\7\3\2\2\u02b8\u02b9\5 \21\2\u02b9"+
		"_\3\2\2\2\u02ba\u02bb\7J\2\2\u02bba\3\2\2\2degkow|\u0084\u0087\u008c\u0091"+
		"\u0093\u009a\u00a0\u00a7\u00ae\u00b5\u00bb\u00c1\u00c5\u00c9\u00d0\u00d5"+
		"\u00de\u00e9\u00ee\u00fd\u0109\u010e\u0113\u011b\u012f\u0131\u0138\u013d"+
		"\u0146\u014a\u0155\u015b\u0161\u0167\u016d\u0173\u017d\u0184\u018d\u0198"+
		"\u019b\u01a2\u01ab\u01b3\u01bf\u01c4\u01c8\u01cb\u01d0\u01d6\u01dc\u01e2"+
		"\u01e6\u01eb\u01f2\u01f6\u01fc\u0202\u0204\u020d\u0213\u0215\u021b\u0221"+
		"\u0223\u0228\u022e\u0230\u0239\u023e\u0246\u024c\u0255\u025b\u025f\u0262"+
		"\u0266\u0270\u0272\u0279\u027d\u0285\u028a\u028e\u0290\u0295\u029d\u02a2"+
		"\u02a8\u02aa\u02ae\u02b0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}