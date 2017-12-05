// Generated from grammar/DeepOcl.g4 by ANTLR 4.5.3
package org.melanee.ocl2.grammar.definition.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DeepOclParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DeepOclVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#contextDeclCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextDeclCS(DeepOclParser.ContextDeclCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#operationContextCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationContextCS(DeepOclParser.OperationContextCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#levelSpecificationCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevelSpecificationCS(DeepOclParser.LevelSpecificationCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#bodyCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBodyCS(DeepOclParser.BodyCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#postCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostCS(DeepOclParser.PostCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#preCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreCS(DeepOclParser.PreCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#defCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefCS(DeepOclParser.DefCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#typeExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeExpCS(DeepOclParser.TypeExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#typeLiteralCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLiteralCS(DeepOclParser.TypeLiteralCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#tupleTypeCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTupleTypeCS(DeepOclParser.TupleTypeCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#tuplePartCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuplePartCS(DeepOclParser.TuplePartCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#collectionTypeCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionTypeCS(DeepOclParser.CollectionTypeCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#collectionTypeIDentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionTypeIDentifier(DeepOclParser.CollectionTypeIDentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#primitiveTypeCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveTypeCS(DeepOclParser.PrimitiveTypeCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#typeNameExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeNameExpCS(DeepOclParser.TypeNameExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#specificationCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecificationCS(DeepOclParser.SpecificationCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#expCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpCS(DeepOclParser.ExpCSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timesDivide}
	 * labeled alternative in {@link DeepOclParser#infixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimesDivide(DeepOclParser.TimesDivideContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link DeepOclParser#infixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinus(DeepOclParser.PlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andOrXor}
	 * labeled alternative in {@link DeepOclParser#infixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrXor(DeepOclParser.AndOrXorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Message}
	 * labeled alternative in {@link DeepOclParser#infixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(DeepOclParser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalOperations}
	 * labeled alternative in {@link DeepOclParser#infixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualOperations(DeepOclParser.EqualOperationsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prefixedExp}
	 * labeled alternative in {@link DeepOclParser#infixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixedExp(DeepOclParser.PrefixedExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code iteratorBar}
	 * labeled alternative in {@link DeepOclParser#infixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIteratorBar(DeepOclParser.IteratorBarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code implies}
	 * labeled alternative in {@link DeepOclParser#infixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplies(DeepOclParser.ImpliesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#iteratorBarExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIteratorBarExpCS(DeepOclParser.IteratorBarExpCSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dot}
	 * labeled alternative in {@link DeepOclParser#navigationOperatorCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDot(DeepOclParser.DotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrow}
	 * labeled alternative in {@link DeepOclParser#navigationOperatorCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrow(DeepOclParser.ArrowContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#prefixedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixedExpCS(DeepOclParser.PrefixedExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#primaryExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpCS(DeepOclParser.PrimaryExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#nestedExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpCS(DeepOclParser.NestedExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#ifExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpCS(DeepOclParser.IfExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#letExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExpCS(DeepOclParser.LetExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#letVariableCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetVariableCS(DeepOclParser.LetVariableCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#typeLiteralExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeLiteralExpCS(DeepOclParser.TypeLiteralExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#collectionLiteralExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionLiteralExpCS(DeepOclParser.CollectionLiteralExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#collectionLiteralPartCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionLiteralPartCS(DeepOclParser.CollectionLiteralPartCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#tupleLiteralExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTupleLiteralExpCS(DeepOclParser.TupleLiteralExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#tupleLiteralPartCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTupleLiteralPartCS(DeepOclParser.TupleLiteralPartCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#selfExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfExpCS(DeepOclParser.SelfExpCSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code number}
	 * labeled alternative in {@link DeepOclParser#primitiveLiteralExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(DeepOclParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link DeepOclParser#primitiveLiteralExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(DeepOclParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link DeepOclParser#primitiveLiteralExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(DeepOclParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code invalid}
	 * labeled alternative in {@link DeepOclParser#primitiveLiteralExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvalid(DeepOclParser.InvalidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code null}
	 * labeled alternative in {@link DeepOclParser#primitiveLiteralExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(DeepOclParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#navigatingExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigatingExpCS(DeepOclParser.NavigatingExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#navigatingSemiAgrsCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigatingSemiAgrsCS(DeepOclParser.NavigatingSemiAgrsCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#navigatingCommaArgCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigatingCommaArgCS(DeepOclParser.NavigatingCommaArgCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#navigatingArgExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigatingArgExpCS(DeepOclParser.NavigatingArgExpCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#navigatingBarAgrsCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigatingBarAgrsCS(DeepOclParser.NavigatingBarAgrsCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#navigatingArgCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigatingArgCS(DeepOclParser.NavigatingArgCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#indexExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexExpCS(DeepOclParser.IndexExpCSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code name}
	 * labeled alternative in {@link DeepOclParser#nameExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(DeepOclParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ontologicalName}
	 * labeled alternative in {@link DeepOclParser#nameExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOntologicalName(DeepOclParser.OntologicalNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code linguisticalName}
	 * labeled alternative in {@link DeepOclParser#nameExpCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinguisticalName(DeepOclParser.LinguisticalNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#parameterCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterCS(DeepOclParser.ParameterCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#invCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvCS(DeepOclParser.InvCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#classifierContextCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassifierContextCS(DeepOclParser.ClassifierContextCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#propertyContextDeclCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyContextDeclCS(DeepOclParser.PropertyContextDeclCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#derCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDerCS(DeepOclParser.DerCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#initCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitCS(DeepOclParser.InitCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link DeepOclParser#onespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnespace(DeepOclParser.OnespaceContext ctx);
}