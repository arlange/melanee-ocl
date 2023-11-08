/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Ralph Gerbig - initial API and implementation and initial documentation Arne Lange
 * - ocl2 implementation
 *******************************************************************************/

package org.melanee.ocl2.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.management.RuntimeErrorException;
import javax.naming.OperationNotSupportedException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ErrorNodeImpl;
import org.antlr.v4.runtime.tree.ParseTree;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.AndOrXorContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.ArrowContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.BodyCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.BooleanContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.ClassifierContextCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.CollectionArgumentsContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.CollectionLiteralExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.CollectionLiteralPartCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.CollectionTypeCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.CollectionTypeIDentifierContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.ContextDeclCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.DefCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.DerCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.DotContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.EqualOperationsContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.ExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.IfExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.ImpliesContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.IndexExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.InitCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.InvCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.InvalidContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.IteratorBarContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.IteratorBarExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.LetExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.LetVariableCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.LevelSpecificationCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.LinguisticalNameContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.MessageContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NameContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NavigatingArgCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NavigatingArgExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NavigatingBarAgrsCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NavigatingCommaArgCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NavigatingExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NavigatingSemiAgrsCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NestedExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NullContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.NumberContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.OnespaceContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.OntologicalNameContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.OperationContextCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.ParameterCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.PlusMinusContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.PostCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.PreCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.PrefixedExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.PrefixedExpContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.PrimaryExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.PrimitiveTypeCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.PropertyContextDeclCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.SelfExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.SpecificationCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.StringContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TimesDivideContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TupleLiteralExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TupleLiteralPartCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TuplePartCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TupleTypeCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TypeExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TypeLiteralCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TypeLiteralExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.TypeNameExpCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclVisitor;
import org.melanee.ocl2.service.exception.InterpreterException;
import org.melanee.ocl2.service.exception.NavigationException;
import org.melanee.ocl2.service.util.LetVariable;
import org.melanee.ocl2.service.util.OclInvalid;
import org.melanee.ocl2.service.util.Tuple;

/**
 * This class extends a class from the antlr4 generated parseTreeVisitor.
 * 
 * @author Arne Lange
 *
 */
public class DeepOclRuleVisitor extends AbstractParseTreeVisitor<Object>
    implements DeepOclVisitor<Object> {
  private boolean collectionOperation;
  private Element context;
  private DeepOCLClabjectWrapperImpl wrapper;
  private boolean isInv;
  private boolean isBody;
  private boolean isPost;
  private boolean isPre;
  private boolean isDerive;
  private boolean isDef;
  private boolean isInit;
  private Collection<?> tempCollection;
  private String tempString;
  private Collection<?> parameters;
  private Integer startLevel;
  private Integer endLevel;
  private Boolean assign;

  /**
   * Deep OCL Rule visitor is the main class for interpreting the parsed ocl statement.
   * 
   * @param context - is a Clabject
   */
  public DeepOclRuleVisitor(Clabject context) {
    super();
    this.context = context;
    this.wrapper = new DeepOCLClabjectWrapperImpl(this.context);
    this.collectionOperation = false;
    this.isBody = false;
    this.isDef = false;
    this.isDerive = false;
    this.isInit = false;
    this.isInv = false;
    this.isPost = false;
    this.isPre = false;
    this.tempCollection = null;
    this.tempString = null;
    this.parameters = null;
    this.startLevel = ((Clabject) this.context).getLevelIndex();
    this.endLevel = null;
    this.assign = false;
  }

  /**
   * Different constructor.
   * 
   * @param context is a Clabject (context of the OCL statement)
   */
  public DeepOclRuleVisitor(Element context) {
    super();
    this.context = context;
    this.wrapper = new DeepOCLClabjectWrapperImpl(this.context);
    this.collectionOperation = false;
    this.isBody = false;
    this.isDef = false;
    this.isDerive = false;
    this.isInit = false;
    this.isInv = false;
    this.isPost = false;
    this.isPre = false;
    this.tempCollection = null;
    this.tempString = null;
    this.parameters = null;
    this.startLevel = 0;
    this.endLevel = null;
    this.assign = false;
  }

  /**
   * Different constructor.
   * 
   * @param context is a Clabject
   * @param parameters is a Collection of Elements from PLM
   */
  public DeepOclRuleVisitor(Clabject context, Collection<Element> parameters) {
    super();
    this.context = context;
    this.wrapper = new DeepOCLClabjectWrapperImpl(this.context, parameters);
    this.collectionOperation = false;
    this.isBody = false;
    this.isDef = false;
    this.isDerive = false;
    this.isInit = false;
    this.isInv = false;
    this.isPost = false;
    this.isPre = false;
    this.tempCollection = null;
    this.tempString = null;
  }

  @Override
  public Object visitContextDeclCS(ContextDeclCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitOperationContextCS(OperationContextCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitBodyCS(BodyCSContext ctx) {
    resetContraintTypes();
    this.isBody = true;
    this.wrapper.setConstraintType("body");
    return visitChildren(ctx);
  }

  @Override
  public Object visitPostCS(PostCSContext ctx) {
    resetContraintTypes();
    this.isPost = true;
    this.wrapper.setConstraintType("post");
    return visitChildren(ctx);
  }

  @Override
  public Object visitPreCS(PreCSContext ctx) {
    resetContraintTypes();
    this.isPre = true;
    this.wrapper.setConstraintType("pre");
    return visitChildren(ctx);
  }

  @Override
  public Object visitDefCS(DefCSContext ctx) {
    resetContraintTypes();
    this.isDef = true;
    this.wrapper.setConstraintType("def");
    return visitChildren(ctx);
  }

  @Override
  public Object visitTypeExpCS(TypeExpCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitTypeLiteralCS(TypeLiteralCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitTupleTypeCS(TupleTypeCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitTuplePartCS(TuplePartCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitCollectionTypeCS(CollectionTypeCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitCollectionTypeIDentifier(CollectionTypeIDentifierContext ctx) {
    return ctx.getText();
  }

  @Override
  public Object visitPrimitiveTypeCS(PrimitiveTypeCSContext ctx) {
    return ctx.getText();
    // return visitChildren(ctx);
  }

  @Override
  public Object visitTypeNameExpCS(TypeNameExpCSContext ctx) {
    return ctx.getText();
  }

  @Override
  public Object visitSpecificationCS(SpecificationCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitExpCS(ExpCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitIteratorBarExpCS(IteratorBarExpCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitDot(DotContext ctx) {
    collectionOperation = false;
    return visitChildren(ctx);
  }

  @Override
  public Object visitArrow(ArrowContext ctx) {
    collectionOperation = true;
    return visitChildren(ctx);
  }

  @Override
  public Object visitPrefixedExpCS(PrefixedExpCSContext ctx) {
    if (ctx.not != null) {
      try {
        Object result = visit(ctx.exp);
        return !Boolean.parseBoolean(result.toString());
      } catch (Exception e) {
        return null;
      }
    } else {
      return visitChildren(ctx);
    }
  }

  @Override
  public Object visitNestedExpCS(NestedExpCSContext ctx) {
    return visit(ctx.exp);

  }

  @Override
  public Object visitIfExpCS(IfExpCSContext ctx) {
    this.assign = false;
    Object result = visit(ctx.ifexp);
    try {
      Boolean bool = Boolean.parseBoolean(result.toString());
      //why this assign = true
      //this.assign = true;
      if (bool) {
        return visit(ctx.thenexp);
      } else {
        return visit(ctx.elseexp);
      }
    } catch (Exception e) {
      return new OclInvalid();
    }
  }

  @Override
  public Object visitLetExpCS(LetExpCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitLetVariableCS(LetVariableCSContext ctx) {
    try {
      Object type = visit(ctx.type);
      Object exp = visit(ctx.exp);
      Object value = null;
      if (exp instanceof Collection<?>) {
        for (Object o : (Collection<?>) exp) {
          if (o instanceof Attribute) {
            value = ((Attribute) o).getValue();
          }
        }
      }
      if (value == null) {
        value = exp;
      }
      this.wrapper.createLetVariable(ctx.name.getText(), type.toString(), value.toString());
    } catch (InterpreterException e) {
      System.out.println("Could not create let variable");
      return new OclInvalid();
    }
    return visitChildren(ctx);
  }

  @Override
  public Object visitTypeLiteralExpCS(TypeLiteralExpCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitCollectionLiteralExpCS(CollectionLiteralExpCSContext ctx) {
    String args = ctx.argument.getText();
    if (ctx.type.getText().equals("Set") || ctx.type.getText().equals("OrderedSet")) {
      HashSet<String> set = new HashSet<>();
      for (String arg : args.split(",")) {
        set.add(arg);
      }
      this.tempCollection = set;
      return set;
    } else if (ctx.type.getText().equals("Bag") || ctx.type.getText().equals("Sequence")) {
      List<String> bag = new ArrayList<>();
      for (String arg : args.split(",")) {
        bag.add(arg);
      }
      this.tempCollection = bag;
      return bag;
    }
    return visitChildren(ctx);
  }

  @Override
  public Object visitCollectionLiteralPartCS(CollectionLiteralPartCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitTupleLiteralExpCS(TupleLiteralExpCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitTupleLiteralPartCS(TupleLiteralPartCSContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * so here it gets messy. probably a spot to begin refactoring. every time it is almost the same
   * procedure. check if the tempCollection is not null and gather the parameters from the tree.
   * iteration operation that return another selection rather than Integer or Boolean, get the
   * current iterator object from the wrapper that represent the actual navigation in the deep
   * model. As long as the iterator has next a new Wrapper object is created and as the new context
   * the current navigation is set initially. Every operation that returns a new collection as a
   * result is using the tempCollection. As mentioned, pretty messy!
   */
  @SuppressWarnings("unchecked")
  @Override
  public Object visitNavigatingExpCS(NavigatingExpCSContext ctx) {
    if (collectionOperation == true) {
      collectionOperation = false;
      if (this.wrapper.getOperationList().contains(ctx.opName.getText())) {
        // size operation
        // we have to create an attribute an put it in
        // the navigation stack. That is because the left side of an
        // logic statement has to reside in the navigation stack as an
        // attribute; it has to change later maybe.
        if (ctx.opName.getText().equals("size")) {
          Attribute attr = PLMFactory.eINSTANCE.createAttribute();
          attr.setDatatype("Integer");
          if (this.tempCollection != null) {
            Integer size = tempCollection.size();
            tempCollection = null;
            attr.setValue(size.toString());
            this.wrapper.getNavigationStack()
                .push(new Tuple<String, Collection<Element>>("size", Arrays.asList(attr)));
            return size;
          } else {
            if (this.wrapper.getNavigationStack().peek().getSecond() != null) {
              Integer size = this.wrapper.getNavigationStack().peek().getSecond().size();
              attr.setValue(size.toString());
              this.wrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("size", Arrays.asList(attr)));
              return size;
            } else {
              return new OclInvalid();
            }
          }
        }
        // first
        else if (ctx.opName.getText().equals("first")) {
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            Element e = (Element) tempCollection.toArray()[0];
            tempCollection = null;
            return e;
          } else {
            return this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
          }
        }


        // at
        else if (ctx.opName.getText().equals("at")) {
          Integer index;
          try {
            Double d = Double.parseDouble(visit(ctx.arg).toString());
            index = d.intValue();
          } catch (Exception e) {
            System.out.println("Could not find index in the parameter");
            return new OclInvalid();
          }
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            Element e = (Element) tempCollection.toArray()[index];
            tempCollection = Arrays.asList(e);
            this.wrapper.getNavigationStack()
                .push(new Tuple<String, Collection<Element>>("at", Arrays.asList(e)));
            return e;
          } else {
            Element e =
                (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[index];
            this.wrapper.getNavigationStack()
                .push(new Tuple<String, Collection<Element>>("at", Arrays.asList(e)));
            return e;
          }
        }
        // iterate
        else if (ctx.opName.getText().equals("iterate")) {
          String type = ctx.arg.getText().substring(ctx.arg.getText().indexOf(":") + 1,
              ctx.arg.getText().length());
          // defining a collection beforehand, like Set{1,2,3}
          if (type.equals("Integer") || type.equals("Real") || type.equals("Boolean")
              || type.equals("String")) {
            if (this.tempCollection != null && this.tempCollection.size() > 0) {
              String iterationExpression = ctx.getText().substring(ctx.getText().indexOf("|") + 1,
                  ctx.getText().indexOf(")"));
              Object[] semiArgs = (Object[]) visit(ctx.semiArg);
              String var = ctx.arg.getText().substring(0, ctx.arg.getText().indexOf(":"));

              Object accu = semiArgs[2].toString();
              Iterator it = this.tempCollection.iterator();
              while (it.hasNext()) {
                Object itElement = it.next();
                DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(iterationExpression));
                DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
                ParseTree tree = parser.specificationCS();
                DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.context);
                visitor.getWrapper().addIterationMap(var, itElement);
                visitor.getWrapper().addIterationMap(semiArgs[0].toString(), accu);
                visitor.getWrapper().setIteratorName(var);
                accu = visitor.visit(tree);
              }
              if (semiArgs[1].equals("Integer")) {
                Double d = Double.valueOf(accu.toString());
                Integer acc = d.intValue();
                accu = acc;
              }
              Attribute returnAttribute = PLMFactory.eINSTANCE.createAttribute();
              returnAttribute.setValue(accu.toString());
              this.wrapper.getNavigationStack().push(new Tuple<String, Collection<Element>>(
                  "iterate", Arrays.asList(returnAttribute)));
              return accu;
            } else {
              String iterationExpression = ctx.getText().substring(ctx.getText().indexOf("|") + 1,
                  ctx.getText().indexOf(")"));
              Object[] semiArgs = (Object[]) visit(ctx.semiArg);
              String var = ctx.arg.getText().substring(0, ctx.arg.getText().indexOf(":"));
              Object accu = semiArgs[2].toString();
              Iterator it = this.wrapper.getCurrentCollectionIterator();
              while (it.hasNext()) {
                Object itElement = it.next();
                DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(iterationExpression));
                DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
                ParseTree tree = parser.specificationCS();
                DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.context);
                visitor.getWrapper().addIterationMap(var, itElement);
                visitor.getWrapper().addIterationMap(semiArgs[0].toString(), accu);
                accu = visitor.visit(tree);
              }
              if (semiArgs[1].equals("Integer")) {
                Double d = Double.valueOf(accu.toString());
                Integer acc = d.intValue();
                accu = acc;
              }
              Attribute returnAttribute = PLMFactory.eINSTANCE.createAttribute();
              returnAttribute.setValue(accu.toString());
              this.wrapper.getNavigationStack().push(new Tuple<String, Collection<Element>>(
                  "iterate", Arrays.asList(returnAttribute)));
              return accu;
            }

          }
          // this is the section for real model navigation
          else {
            String iterationExpression =
                ctx.getText().substring(ctx.getText().indexOf("|") + 1, ctx.getText().indexOf(")"));
            Object[] semiArgs = (Object[]) visit(ctx.semiArg);
            String var = ctx.arg.getText().substring(0, ctx.arg.getText().indexOf(":"));
            Object accu = semiArgs[2].toString();
            Iterator<Element> it = this.wrapper.getCurrentCollectionIterator();
            while (it.hasNext()) {
              Element itElement = it.next();
              DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(iterationExpression));
              DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
              ParseTree tree = parser.specificationCS();
              DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(itElement);
              visitor.getWrapper().addIterationMap(semiArgs[0].toString(), accu);
              visitor.wrapper.setIteratorName(var);
              accu = visitor.visit(tree);
            }
            Attribute returnAttribute = PLMFactory.eINSTANCE.createAttribute();
            returnAttribute.setValue(accu.toString());
            this.wrapper.getNavigationStack().push(
                new Tuple<String, Collection<Element>>("iterate", Arrays.asList(returnAttribute)));
            return accu;
          }

        }
        // last
        else if (ctx.opName.getText().equals("last")) {
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            Element e = (Element) tempCollection.toArray()[tempCollection.size() - 1];
            tempCollection = null;
            this.wrapper.getNavigationStack()
                .add(new Tuple<String, Collection<Element>>("last", Arrays.asList(e)));
            return e;
          } else {
            int size = this.wrapper.getNavigationStack().peek().getSecond().size();
            if (size > 0) {
              Element e = (Element) this.wrapper.getNavigationStack().peek().getSecond()
                  .toArray()[size - 1];
              this.wrapper.getNavigationStack()
                  .add(new Tuple<String, Collection<Element>>("last", Arrays.asList(e)));
              return e;
            } else {
              return new OclInvalid();
            }
          }
        }
        // is Empty
        else if (ctx.opName.getText().equals("isEmpty")) {
          if (this.tempCollection != null) {
            Collection<Element> list = (Collection<Element>) this.tempCollection;
            this.tempCollection = null;
            return list.isEmpty();
          } else if (this.wrapper.getNavigationStack().peek().getSecond() != null) {
            return this.wrapper.getNavigationStack().peek().getSecond().isEmpty();
          } else {
            return new OclInvalid();
          }
        }
        // notEmpty
        else if (ctx.opName.getText().equals("notEmpty")) {
          if (this.tempCollection != null) {
            Collection<Element> list = (Collection<Element>) this.tempCollection;
            this.tempCollection = null;
            return !list.isEmpty();
          } else if (this.wrapper.getNavigationStack().peek().getSecond() != null) {
            return !this.wrapper.getNavigationStack().peek().getSecond().isEmpty();
          } else {
            return new OclInvalid();
          }
        }
        // collect
        else if (ctx.opName.getText().equals("collect")) {
          Collection<?> collection = this.wrapper.getNavigationStack().peek().getSecond();
          String expression = ctx.arg.getText();
          Collection<?> returnCollection = new ArrayList<>();
          if (!collection.isEmpty()) {
            Clabject clabject = (Clabject) collection.toArray()[0];
            Attribute a = (Attribute) clabject.getFeatureForName(expression);
            if (a.getDatatype().equals("Integer") || a.getDatatype().equals("Natural")) {
              List<Integer> integerCollection = new ArrayList<>();
              Iterator<?> it = collection.iterator();
              while (it.hasNext()) {
                Clabject c = (Clabject) it.next();
                integerCollection.add(
                    Integer.parseInt(((Attribute) c.getFeatureForName(expression)).getValue()));
              }
              this.tempCollection = integerCollection;
              return this.tempCollection;
            } else if (a.getDatatype().equals("Real")) {
              List<Double> doubleCollection = new ArrayList<>();
              Iterator<?> it = collection.iterator();
              while (it.hasNext()) {
                Clabject c = (Clabject) it.next();
                doubleCollection.add(
                    Double.parseDouble(((Attribute) c.getFeatureForName(expression)).getValue()));
              }
              this.tempCollection = doubleCollection;
              return this.tempCollection;
            } else if (a.getDatatype().equals("String")) {
              List<String> stringCollection = new ArrayList<>();
              Iterator<?> it = collection.iterator();
              while (it.hasNext()) {
                Clabject c = (Clabject) it.next();
                stringCollection.add(((Attribute) c.getFeatureForName(expression)).getValue());
              }
              return stringCollection;
            } else if (a.getDatatype().equals("Boolean")) {
              List<Boolean> booleanCollection = new ArrayList<>();
              Iterator<?> it = collection.iterator();
              while (it.hasNext()) {
                Clabject c = (Clabject) it.next();
                booleanCollection.add(
                    Boolean.parseBoolean(((Attribute) c.getFeatureForName(expression)).getValue()));
              }
              this.tempCollection = booleanCollection;
              return this.tempCollection;
            }
          }
        }
        // collectNested
        else if (ctx.opName.getText().equals("collectNested")) {
          this.tempCollection = null;
          Collection<?> collection = this.wrapper.getNavigationStack().peek().getSecond();
          String expression = ctx.arg.getText();
          Collection<Element> returnCollection = new ArrayList<>();
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          if (!collection.isEmpty()) {
            for (Object clab : collection) {
              if (clab instanceof Clabject) {
                Clabject clabject = (Clabject) clab;
                DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(clabject);
                this.wrapper = newWrapper;
                Object result = visit(ctx.arg);
                if (result instanceof Number) {
                  Attribute attr = PLMFactory.eINSTANCE.createAttribute();
                  attr.setDatatype("Real");
                  attr.setValue(result.toString());
                  returnCollection.add(attr);
                } else if (result instanceof Attribute) {
                  Attribute attr = (Attribute) result;
                  returnCollection.add(attr);
                } else if (result instanceof Collection) {
                  if (((Collection) result).toArray()[0] instanceof Attribute) {
                    Attribute attr = (Attribute) ((Collection) result).toArray()[0];
                    returnCollection.add(attr);
                  }
                } else if (result instanceof String) {
                  Attribute attr = PLMFactory.eINSTANCE.createAttribute();
                  attr.setDatatype("String");
                  attr.setValue(result.toString());
                  returnCollection.add(attr);
                }
              }
            }
          }
          this.wrapper = oldWrapper;
          this.wrapper.getNavigationStack()
              .push(new Tuple<String, Collection<Element>>("collectNested", returnCollection));
          return returnCollection;
        }

        // sum
        else if (ctx.opName.getText().equals("sum")) {
          Object[] arg = new Object[1];
          Attribute attr = PLMFactory.eINSTANCE.createAttribute();
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            Collection<Element> list = (Collection<Element>) tempCollection;
            tempCollection = null;
            arg[0] = list;
          } else if (this.wrapper.getNavigationStack().peek().getSecond() != null) {
            Collection<Element> list = this.wrapper.getNavigationStack().peek().getSecond();
            arg[0] = list;
          }
          try {
            Number result = (Number) this.wrapper.invoke("sum", arg);
            if (result instanceof Integer) {
              attr.setValue(result.toString());
              attr.setDatatype("Integer");
              this.wrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("sum", Arrays.asList(attr)));
            } else if (result instanceof Double || result instanceof Float) {
              attr.setValue(result.toString());
              attr.setDatatype("Real");
              this.wrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("sum", Arrays.asList(attr)));
            }
          } catch (Exception e) {
            return new OclInvalid();
          }
          return Arrays.asList(attr);
        }
        // one operation
        else if (ctx.opName.getText().equals("one")) {
          Collection<Element> list = new ArrayList<>();
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          Iterator<Element> it = wrapper.getCurrentCollectionIterator();
          while (it.hasNext()) {
            Clabject clab = (Clabject) it.next();
            DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(clab);
            this.wrapper = newWrapper;
            Object result = visit(ctx.arg);
            if (result != null) {
              try {
                boolean r = Boolean.parseBoolean(result.toString());
                if (r == true) {
                  list.add(clab);
                }
              } catch (Exception e) {
                oldWrapper.getNavigationStack()
                    .push(new Tuple<String, Collection<Element>>("one", null));
                return new OclInvalid();
              }
            } else {
              oldWrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("one", null));
            }
          }
          this.wrapper = oldWrapper;
          // return true if only one element was found for the
          // statement
          if (list.size() == 1) {
            return true;
          } else {
            // return false if the list is smaller or bigger than 1
            return false;
          }
        }
        // exists operation
        else if (ctx.opName.getText().equals("exists")) {
          Collection<Element> list = new ArrayList<>();
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          Iterator<Element> it = wrapper.getCurrentCollectionIterator();
          while (it.hasNext()) {
            Clabject clab = (Clabject) it.next();
            DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(clab);
            this.wrapper = newWrapper;
            Object result = visit(ctx.arg);
            if (result != null) {
              try {
                boolean r = Boolean.parseBoolean(result.toString());
                if (r == true) {
                  return true;
                }
              } catch (Exception e) {
                oldWrapper.getNavigationStack()
                    .push(new Tuple<String, Collection<Element>>("exists", null));
                return new OclInvalid();
              }
            } else {
              oldWrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("exists", null));
            }
          }
          this.wrapper = oldWrapper;
          // return true if only one element was found for the
          // statement
          if (list.size() == 1) {
            return true;
          } else {
            // return false if the list is smaller or bigger than 1
            return false;
          }
        }
        // any
        else if (ctx.opName.getText().equals("any")) {
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            Element e = (Element) tempCollection.toArray()[0];
            tempCollection = null;
            return e;
          } else {
            Collection<Element> list = this.wrapper.getNavigationStack().peek().getSecond();
            Element e = (Element) list.toArray()[0];
            return e;
          }
        }
        // includesAll: this method is passed through to the wrapper.
        else if (ctx.opName.getText().equals("includesAll")) {
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            Collection<Element> list = (Collection<Element>) tempCollection;
            Object arg = visit(ctx.arg);
            tempCollection = null;
            Object[] args = new Object[2];
            args[0] = list;
            args[1] = arg;
            try {
              return Boolean.parseBoolean(this.wrapper.invoke("includesAll", args).toString());
            } catch (Exception e) {
              e.printStackTrace();
              return new OclInvalid();
            }
          } else {
            Collection<Element> list = this.wrapper.getNavigationStack().peek().getSecond();
            Object[] args = new Object[2];
            args[0] = list;
            args[1] = this.wrapper.getNavigationStack().peek().getSecond();
            try {
              return Boolean.parseBoolean(this.wrapper.invoke("includesAll", args).toString());
            } catch (Exception e) {
              e.printStackTrace();
              return new OclInvalid();
            }
          }
        }
        // excludesAll: this method is passed through to the wrapper.
        else if (ctx.opName.getText().equals("excludesAll")) {
          visit(ctx.arg);
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            Collection<Element> list = (Collection<Element>) tempCollection;
            tempCollection = null;
            Object[] args = new Object[2];
            args[0] = list;
            args[1] = this.wrapper.getNavigationStack().peek().getSecond();
            try {
              return Boolean.parseBoolean(this.wrapper.invoke("excludesAll", args).toString());
            } catch (Exception e) {
              return new OclInvalid();
            }
          } else {
            Collection<Element> list = this.wrapper.getNavigationStack().peek().getSecond();
            Object[] args = new Object[2];
            args[0] = list;
            args[1] = this.wrapper.getNavigationStack().peek().getSecond();
            try {
              return Boolean.parseBoolean(this.wrapper.invoke("excludesAll", args).toString());
            } catch (Exception e) {
              return new OclInvalid();
            }
          }
        }
        // excludes: this method is passed through to the wrapper.
        else if (ctx.opName.getText().equals("excludes")) {
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            visit(ctx.arg);
            Element element;
            if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1) {
              element = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
            } else {
              element = null;
            }
            Collection<Element> list = (Collection<Element>) tempCollection;
            tempCollection = null;
            Object[] args = new Object[2];
            args[0] = list;
            args[1] = element;
            try {
              return Boolean.parseBoolean(this.wrapper.invoke("excludes", args).toString());
            } catch (Exception e) {
              this.wrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("excludes", null));
              return new OclInvalid();
            }
          } else {
            Collection<Element> list = this.wrapper.getNavigationStack().peek().getSecond();
            visit(ctx.arg);
            Element element;
            if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1) {
              element = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
            } else {
              element = null;
            }
            Object[] args = new Object[2];
            args[0] = list;
            args[1] = element;
            try {
              return Boolean.parseBoolean(this.wrapper.invoke("excludes", args).toString());
            } catch (Exception e) {
              this.wrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("excludes", null));
              return new OclInvalid();
            }
          }
        }
        // excluding
        else if (ctx.opName.getText().equals("excluding")) {
          this.tempCollection = null;
          Object[] arg = {this.wrapper.getNavigationStack().peek().getSecond(), ctx.arg.getText()};
          try {
            return this.wrapper.invoke(ctx.opName.getText(), arg);
          } catch (Exception e) {
            return new OclInvalid();
          }
        }
        // includes: this method is passed through to the wrapper.
        else if (ctx.opName.getText().equals("includes")) {
          if (this.tempCollection != null && this.tempCollection.size() > 0) {
            visit(ctx.arg);
            Element element;
            if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1) {
              element = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
            } else {
              element = null;
            }
            Collection<Element> list = (Collection<Element>) tempCollection;
            tempCollection = null;
            Object[] args = new Object[2];
            args[0] = list;
            args[1] = element;
            try {
              return Boolean.parseBoolean(this.wrapper.invoke("includes", args).toString());
            } catch (Exception e) {
              this.wrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("includes", null));
              return new OclInvalid();
            }
          } else {
            Collection<Element> list = this.wrapper.getNavigationStack().peek().getSecond();
            visit(ctx.arg);
            Element element;
            if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1) {
              element = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
            } else {
              element = null;
            }
            Object[] args = new Object[2];
            args[0] = list;
            args[1] = element;
            try {
              return Boolean.parseBoolean(this.wrapper.invoke("includes", args).toString());
            } catch (Exception e) {
              this.wrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("includes", null));
              return new OclInvalid();
            }
          }
        }
        // union
        else if (ctx.opName.getText().equals("union")) {
          Collection<Element> resultList = new ArrayList<>();
          Collection<?> leftCollection = new ArrayList<Element>();
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          DeepOCLClabjectWrapperImpl newWrapper =
              new DeepOCLClabjectWrapperImpl(oldWrapper.getContext());
          this.wrapper = newWrapper;
          if (this.tempCollection.isEmpty()) {
            leftCollection = this.wrapper.getNavigationStack().peek().getSecond();
          } else {
            leftCollection = this.tempCollection;
          }
          Object rightCollection = visit(ctx.arg);
          if (rightCollection != null) {
            Object[] args = new Object[] {leftCollection, rightCollection};
            try {
              resultList = (Collection<Element>) this.wrapper.invoke("union", args);
            } catch (Exception e) {
              oldWrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("union", null));
              String errorMessage =
                  "union operation threw an exception \n <--- ---> \n" + e.getMessage();
              return new OclInvalid(errorMessage);
            }
          } else {
            oldWrapper.getNavigationStack()
                .push(new Tuple<String, Collection<Element>>("union", null));
          }
          this.wrapper = oldWrapper;
          this.wrapper.getNavigationStack().push(
              new Tuple<String, Collection<Element>>("union", (Collection<Element>) resultList));
          this.tempCollection = resultList;
          return resultList;
        }
        // select
        else if (ctx.opName.getText().equals("select")) {
          Collection<Element> list = new ArrayList<>();
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          Iterator<Element> it = wrapper.getCurrentCollectionIterator();
          while (it.hasNext()) {
            Element element = it.next();
            DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(element);
            this.wrapper = newWrapper;
            if (ctx.arg.getText().contains("|")) {
              String iteratorName = ctx.arg.getText().substring(0, 1);
              this.wrapper.setIteratorName(iteratorName);
            }
            Object result = visit(ctx.arg);
            if (result != null) {
              try {
                boolean r = Boolean.parseBoolean(result.toString());
                if (r == true) {
                  list.add(element);
                }
              } catch (Exception e) {
                oldWrapper.getNavigationStack()
                    .push(new Tuple<String, Collection<Element>>("select", null));
                return new OclInvalid();
              }
            } else {
              oldWrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("select", null));
            }
          }
          this.wrapper = oldWrapper;
          this.wrapper.getNavigationStack()
              .push(new Tuple<String, Collection<Element>>("select", list));
          if (list.size() > 0) {
            this.tempCollection = list;
            return list;
          } else {
            return null;
          }
        } // nonReflexiveClosure operation
        else if (ctx.opName.getText().equals("nonReflexiveClosure")) {
          // limit the navigation to 300 steps to make sure it isn't in an endless loop
          // (cycle).
          // maybe a problem in very big model, where the transitive navigation could be
          // more than 300 steps...
          int maxIterations = 300;
          int counter = 0;
          boolean removed = false;
          Collection<Element> list = new HashSet<Element>();
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          // list.addAll(oldWrapper.getNavigationStack().peek().getSecond());
          Collection<Element> originalList = wrapper.getNavigationStack().peek().getSecond();
          Iterator<Element> it = wrapper.getCurrentCollectionIterator();
          Queue<Element> closureQueue = new LinkedList<Element>();
          while (it.hasNext()) {
            Clabject clab = (Clabject) it.next();
            closureQueue.add(clab);
          }
          while (!closureQueue.isEmpty() && counter <= maxIterations) {
            counter++;
            Clabject clab = (Clabject) closureQueue.poll();
            if (!originalList.contains(clab) || removed) {
              list.add(clab);
            }
            removed = true;
            DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(clab);
            this.wrapper = newWrapper;
            Object result = visit(ctx.arg);
            if (result != null) {
              if (result instanceof Collection) {
                closureQueue.addAll((Collection<Element>) result);
              }
            }
          }
          this.wrapper = oldWrapper;
          this.wrapper.getNavigationStack().push(new Tuple<String, Collection<Element>>(
              "nonReflexiveClosure", (Collection<Element>) list));
          this.tempCollection = list;

        }
        // closure operation
        else if (ctx.opName.getText().equals("closure")) {
          // limit the navigation to 300 steps to make sure it isn't in an endless loop
          // (cycle).
          // maybe a problem in very big model, where the transitive navigation could be
          // more than 300 steps...
          int maxIterations = 300;
          int counter = 0;
          Collection<Element> list = new HashSet<Element>();
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          // list.addAll(oldWrapper.getNavigationStack().peek().getSecond());
          Iterator<Element> it = wrapper.getCurrentCollectionIterator();
          Queue<Element> closureQueue = new LinkedList<Element>();
          while (it.hasNext()) {
            Clabject clab = (Clabject) it.next();
            closureQueue.add(clab);
          }
          while (!closureQueue.isEmpty() && counter <= maxIterations) {
            counter++;
            Clabject clab = (Clabject) closureQueue.poll();
            list.add(clab);
            DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(clab);
            this.wrapper = newWrapper;
            Object result = visit(ctx.arg);
            if (result != null) {
              if (result instanceof Collection) {
                closureQueue.addAll((Collection<Element>) result);
              }
            }
          }
          this.wrapper = oldWrapper;
          this.wrapper.getNavigationStack()
              .push(new Tuple<String, Collection<Element>>("closure", (Collection<Element>) list));
          this.tempCollection = list;
          return list;
        }
        // reverse logic as select operation
        else if (ctx.opName.getText().equals("reject")) {
          Collection<Element> list = new ArrayList<>();
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          Iterator<Element> it = wrapper.getCurrentCollectionIterator();
          while (it.hasNext()) {
            Element element = it.next();
            if (ctx.arg.getText().equals("self")) {
              if (!element.equals(wrapper.getContext())) {
                list.add(element);
              }
              continue;
            }
            DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(element);
            this.wrapper = newWrapper;
            Object result = visit(ctx.arg);
            if (result != null) {
              try {
                boolean r = Boolean.parseBoolean(result.toString());
                if (r == false) {
                  list.add(element);
                }
              } catch (Exception e) {
                System.out.println("was not boolean");
                e.printStackTrace();
                return new OclInvalid();
              }
            } else {
              oldWrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("reject", null));
              return new OclInvalid();
            }
          }
          this.wrapper = oldWrapper;
          this.wrapper.getNavigationStack()
              .push(new Tuple<String, Collection<Element>>("reject", list));
          if (list.size() > 0) {
            this.tempCollection = list;
            return this.tempCollection;
          } else {
            return null;
          }
        } // forAll operation: similar to select operation)
        else if (ctx.opName.getText().equals("forAll")) {
          this.collectionOperation = true;
          DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
          Iterator<Element> it = wrapper.getCurrentCollectionIterator();
          while (it.hasNext()) {
            Element element = it.next();
            DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(element);
            this.wrapper = newWrapper;
            Object result = visit(ctx.arg);
            if (result != null) {
              try {
                boolean r = Boolean.parseBoolean(result.toString());
                if (r == false) {
                  // if any element is evaluated as false we
                  // can immediately return false
                  this.collectionOperation = false;
                  return false;
                }
              } catch (Exception e) {
                oldWrapper.getNavigationStack()
                    .push(new Tuple<String, Collection<Element>>("forAll", null));
                this.collectionOperation = false;
                return new OclInvalid();
              }
            } else {
              oldWrapper.getNavigationStack()
                  .push(new Tuple<String, Collection<Element>>("forAll", null));
            }
          }
          this.wrapper = oldWrapper;
          // if nothing returned previously we can assume that
          // everything was evaluated as true, hence we can return
          // true
          this.collectionOperation = false;
          return true;
        }
        // asSet
        else if (ctx.opName.getText().equals("asSet")) {
          Object[] arg = new Object[1];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
            tempCollection = null;
          } else {
            arg[0] = this.wrapper.getNavigationStack().pop().getSecond();
          }
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("asSet", arg);
          } catch (Exception e) {
            this.wrapper.getNavigationStack()
                .push(new Tuple<String, Collection<Element>>("asSet", null));
          }
          // return value for the asSet() operation
          return this.tempCollection;
        }
        // asBag
        else if (ctx.opName.getText().equals("asBag")) {
          Object[] arg = new Object[1];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
          } else {
            arg[0] = this.wrapper.getNavigationStack().pop();
          }
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("asBag", arg);
          } catch (Exception e) {
            return new OclInvalid();
          }
          // return value for the asBag() operation
          return this.tempCollection;
        }
        // asOderedSet
        else if (ctx.opName.getText().equals("asOderedSet")) {
          Object[] arg = new Object[1];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
          } else {
            arg[0] = this.wrapper.getNavigationStack().pop();
          }
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("asOderedSet", arg);
          } catch (Exception e) {
            return new OclInvalid();
          }
          // return value for the asOderedSet() operation
          return this.tempCollection;
        }
        // asSequence
        else if (ctx.opName.getText().equals("asSequence")) {
          Object[] arg = new Object[1];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
          } else {
            arg[0] = this.wrapper.getNavigationStack().pop();
          }
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("asSequence", arg);
          } catch (Exception e) {
            return new OclInvalid();
          }
          // return value for the asSequence() operation
          return this.tempCollection;
        }
        // append
        else if (ctx.opName.getText().equals("append")) {
          Object[] arg = new Object[2];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
          } else {
            arg[0] = this.wrapper.getNavigationStack().pop();
          }
          visit(ctx.arg);
          if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1) {
            arg[1] = this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
          } else {
            System.out.println("could not find anything to append");
            return new OclInvalid();
          }
          tempCollection = null;
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("append", arg);
          } catch (Exception e) {
            e.printStackTrace();
            return new OclInvalid();
          }
          // return value for the append operation
          return this.tempCollection;
        }
        // prepend
        else if (ctx.opName.getText().equals("prepend")) {
          Object[] arg = new Object[2];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
          } else {
            arg[0] = this.wrapper.getNavigationStack().pop();
          }
          visit(ctx.arg);
          if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1) {
            arg[1] = this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
          } else {
            System.out.println("could not find anything to prepend");
            return new OclInvalid();
          }
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("prepend", arg);
          } catch (Exception e) {
            return new OclInvalid();
          }
          // return value for the append operation
          return this.tempCollection;
        }
        // intersection
        else if (ctx.opName.getText().equals("intersection")) {
          Object[] arg = new Object[2];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
          } else {
            arg[0] = this.wrapper.getNavigationStack().pop();
          }
          visit(ctx.arg);
          arg[1] = this.wrapper.getNavigationStack().peek().getSecond();
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("intersection", arg);
          } catch (Exception e) {
            return new OclInvalid();
          }
          // return value for the intersection operation
          return this.tempCollection;
        }
        // sortedBy
        else if (ctx.opName.getText().equals("sortedBy")) {
          Object[] arg = new Object[2];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
          } else {
            arg[0] = this.wrapper.getNavigationStack().peek().getSecond();
          }
          arg[1] = ctx.arg.getText();
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("sortedBy", arg);
          } catch (Exception e) {
            e.printStackTrace();
            return new OclInvalid();
          }
          return this.tempCollection;
        }
        // insertAt
        else if (ctx.opName.getText().equals("insertAt")) {
          Object[] arg = new Object[3];
          if (tempCollection != null && tempCollection.size() > 0) {
            arg[0] = tempCollection;
          } else {
            arg[0] = this.wrapper.getNavigationStack().pop();
          }
          arg[1] = visit(ctx.arg);
          visit(ctx.commaArg);
          arg[2] = this.wrapper.getNavigationStack().peek().getSecond();
          try {
            this.tempCollection = (Collection<Element>) this.wrapper.invoke("insertAt", arg);
          } catch (Exception e) {
            return new OclInvalid();
          }
          // return value for the insertAt operation
          return this.tempCollection;
        }

      } else {
        return visitChildren(ctx);
      }
    } // concat // TODO not finished yet
    else if (ctx.opName.getText().equals("concat")) {
      if (this.tempString != null) {
        this.tempString = this.tempString + visitChildren(ctx);
        return this.tempString;
      }
      if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1) {
        try {
          Attribute attr =
              (Attribute) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
          if (attr.getDatatype().equals("String")) {
            this.tempString = attr.getValue() + visitNavigatingArgCS(ctx.arg);
            return this.tempString;
          } else {
            System.out.println("Attribute was not from type String");
            return null;
          }
        } catch (ClassCastException e) {
          System.out.println(
              "Could not cast NavigationElement to Attribute, i.e. could concat anything!");
          return null;
        }

      }
    } else if (ctx.opName.getText().equals("substring")) {
      if (this.tempString != null) {
        this.tempString = this.tempString + visitChildren(ctx);
        return this.tempString;
      } else if (ctx.getText().contains("(")) {
        int arg1 = Integer.parseInt(
            ctx.getText().substring(ctx.getText().indexOf("(") + 1, ctx.getText().indexOf(",")));
        int arg2 = Integer.parseInt(
            ctx.getText().substring(ctx.getText().indexOf(",") + 1, ctx.getText().indexOf(")")));
        if (this.wrapper.getNavigationStack().peek().getSecond() instanceof Collection) {
          Attribute attr =
              (Attribute) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
          if (attr.getDatatype().equals("String")) {
            String result = attr.getValue().substring(arg1 - 1, arg2);
            Attribute resultAttribute = PLMFactory.eINSTANCE.createAttribute();
            resultAttribute.setDatatype("String");
            resultAttribute.setValue(result);
            this.wrapper.getNavigationStack().push(new Tuple<String, Collection<Element>>(
                "substring", Arrays.asList(resultAttribute)));
          }
        }
      }
    }
    // to Upper Case
    else if (ctx.opName.getText().equals("toUpperCase")) {
      if (this.wrapper.getNavigationStack().peek().getSecond() instanceof Attribute) {
        Attribute attribute = (Attribute) this.wrapper.getNavigationStack().peek().getSecond();
        if (attribute.getDatatype().equals("String")) {
          Attribute toUpper = PLMFactory.eINSTANCE.createAttribute();
          toUpper.setDatatype("String");
          toUpper.setValue(attribute.getValue().toUpperCase());
          this.wrapper.getNavigationStack()
              .push(new Tuple<String, Collection<Element>>("toUpperCase", Arrays.asList(toUpper)));
        } else {
          return null;
        }
      }
    }
    // to Lower Case
    else if (ctx.opName.getText().equals("toLowerCase")) {
      if (this.wrapper.getNavigationStack().peek().getSecond() instanceof Attribute) {
        Attribute attribute = (Attribute) this.wrapper.getNavigationStack().peek().getSecond();
        if (attribute.getDatatype().equals("String")) {
          Attribute toLower = PLMFactory.eINSTANCE.createAttribute();
          toLower.setDatatype("String");
          toLower.setValue(attribute.getValue().toLowerCase());
          this.wrapper.getNavigationStack()
              .push(new Tuple<String, Collection<Element>>("toLowerCase", Arrays.asList(toLower)));
        } else {
          return null;
        }
      }
    }
    // oclIsTypeOf
    else if (ctx.opName.getText().equals("oclIsTypeOf")) {
      return this.wrapper.oclIsTypeOf(ctx.arg.getText());
    } // oclIsKindOf
    else if (ctx.opName.getText().equals("oclIsKindOf")) {
      return this.wrapper.oclIsKindOf(ctx.arg.getText());
    } // oclAsType
    else if (ctx.opName.getText().equals("oclAsType")) {
      return this.wrapper.oclAsType(ctx.arg.getText());
    } // allInstances
    else if (ctx.opName.getText().equals("allInstances")) {
      Object[] args = {};
      try {
        return this.wrapper.invoke("allInstances", args);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } // allDeepInstances
    else if (ctx.opName.getText().equals("allDeepInstances")) {
      Object[] args = {};
      try {
        return this.wrapper.invoke("allDeepInstances", args);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else if (ctx.opName.getText().equals("isInstanceOf")) {
      return this.wrapper.isInstanceOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("isDeepInstanceOf")) {
      return this.wrapper.isDeepInstanceOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("isDirectInstanceOf")) {
      return this.wrapper.isDirectInstanceOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("isDeepDirectInstanceOf")) {
      return this.wrapper.isDeepDirectInstanceOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("isDeepKindOf")) {
      return this.wrapper.isDeepInstanceOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("doclIsDeepKindOf")) {
      return this.wrapper.doclIsDeepKindOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("doclIsDeepTypeOf")) {
      return this.wrapper.doclIsDeepTypeOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("doclIsIsonymOf")) {
      return this.wrapper.doclIsIsonymOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("doclIsHyponymOf")) {
      return this.wrapper.doclIsHyponymOf(ctx.arg.getText());
    } else if (ctx.opName.getText().equals("oclIsUndefined")) {
      return this.wrapper.oclIsUndefined();
    }
    if (this.wrapper.operationExist(ctx.opName.getText())) {
      if (ctx.arg != null && ctx.arg.getText() != "") {
        Object[] arg = {this.wrapper.getNavigationStack().peek().getSecond(), ctx.arg.getText()};
        try {
          return this.wrapper.invoke(ctx.opName.getText(), arg);
        } catch (Exception e) {
          return new OclInvalid("operation name was found but could not execute operation.");
        }
      } else {
        try {
          return this.wrapper.invoke(ctx.opName.getText(), null);
        } catch (Exception e) {
          return new OclInvalid();
        }
      }
    }
    if (this.wrapper.operationOnClabject(ctx.opName.getText())) {
      Object[] args = {};
      return this.wrapper.executeClabjectOperation(ctx.opName.getText(), args);
    }
    return visitChildren(ctx);
  }

  @Override
  public Object visitNavigatingSemiAgrsCS(NavigatingSemiAgrsCSContext ctx) {
    this.collectionOperation = true;
    Object[] result = new Object[3];
    if (ctx.var != null && ctx.exp != null && ctx.typeName != null) {
      result[0] = ctx.var.getText();
      result[1] = visit(ctx.typeName);
      result[2] = visit(ctx.exp);
      this.collectionOperation = false;
      return result;
    } else {
      this.collectionOperation = false;
      return visitChildren(ctx);
    }
  }

  @Override
  public Object visitNavigatingCommaArgCS(NavigatingCommaArgCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitNavigatingArgExpCS(NavigatingArgExpCSContext ctx) {
    if (ctx.body != null) {
      return visit(ctx.body);
    } else if (ctx.name != null) {
      return visit(ctx.name);
    } else if (ctx.iteratorVariable != null) {
      return ctx.getText();
    } else {
      return visitChildren(ctx);
    }
  }

  @Override
  public Object visitNavigatingBarAgrsCS(NavigatingBarAgrsCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitNavigatingArgCS(NavigatingArgCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitIndexExpCS(IndexExpCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitName(NameContext ctx) {
    if (!collectionOperation) {
      try {
        return wrapper.navigate(ctx.getText());
      } catch (NavigationException e) {
        e.printStackTrace();
        return new OclInvalid();
      }
    } else {
      return ctx.getText();
    }
  }

  @Override
  public Object visitParameterCS(ParameterCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitInvCS(InvCSContext ctx) {
    resetContraintTypes();
    this.isInv = true;
    this.wrapper.setConstraintType("inv");
    return visitChildren(ctx);
  }

  @Override
  public Object visitClassifierContextCS(ClassifierContextCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitPropertyContextDeclCS(PropertyContextDeclCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitDerCS(DerCSContext ctx) {
    resetContraintTypes();
    this.isDerive = true;
    this.wrapper.setConstraintType("der");
    return visitChildren(ctx);
  }

  @Override
  public Object visitInitCS(InitCSContext ctx) {
    resetContraintTypes();
    this.isInit = true;
    this.wrapper.setConstraintType("init");
    return visitChildren(ctx);
  }

  @Override
  public Object visitTimesDivide(TimesDivideContext ctx) {
    Object left = visit(ctx.left);
    if (left instanceof Collection) {
      left = ((Collection) left).toArray()[0];
      if (left instanceof Attribute) {
        left = ((Attribute) left).getValue();
      }
    }
    Double dLeft = Double.parseDouble(left.toString());
    Object right = visit(ctx.right);
    if (right instanceof Collection) {
      right = ((Collection) right).toArray()[0];
      if (right instanceof Attribute) {
        right = ((Attribute) right).getValue();
      }
    }
    Double dRight = Double.parseDouble(right.toString());
    switch (ctx.op.getText()) {
      case "*":
        return (dLeft * dRight);
      case "/":
        return (dLeft / dRight);
      default:
        break;
    }
    return visitChildren(ctx);
  }

  @Override
  public Object visitPlusMinus(PlusMinusContext ctx) {
    Object left = visit(ctx.left);
    if (left instanceof Collection) {
      left = ((Collection) left).toArray()[0];
      if (left instanceof Attribute) {
        left = ((Attribute) left).getValue();
      }
    }
    Double dLeft = Double.parseDouble(left.toString());
    Object right = visit(ctx.right);
    if (right instanceof Collection) {
      right = ((Collection) right).toArray()[0];
      if (right instanceof Attribute) {
        right = ((Attribute) right).getValue();
      }
    }
    Double dRight = Double.parseDouble(right.toString());
    switch (ctx.op.getText()) {
      case "+":
        return (dLeft + dRight);
      case "-":
        return (dLeft - dRight);
      default:
        break;
    }
    return visitChildren(ctx);
  }

  @Override
  public Object visitAndOrXor(AndOrXorContext ctx) {
    Object left = visit(ctx.left);
    Boolean bLeft = Boolean.parseBoolean(left.toString());
    Object right = visit(ctx.right);
    Boolean bRight = Boolean.parseBoolean(right.toString());
    switch (ctx.op.getText()) {
      case "and":
        return bLeft && bRight;
      case "or":
        return bLeft || bRight;
      case "xor":
        return (bLeft || bRight) && !(bLeft && bRight);
      default:
        break;
    }
    return visitChildren(ctx);
  }

  @Override
  public Object visitMessage(MessageContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitEqualOperations(EqualOperationsContext ctx) {
    Object result = null;
    if (!this.assign) {
      try {
        Object left = visit(ctx.left);
        return this.wrapper.eval(left, visit(ctx.right), ctx.op.getText());
      } catch (InterpreterException e) {
        this.wrapper.getNavigationStack().clear();
        return new OclInvalid();
      }
    } else {
      // System.out.println("left: " + ctx.left.getText());
      Object left = visit(ctx.left);
      Object right = visit(ctx.right);
      if (right instanceof List && left instanceof List) {
        List<?> rightList = (List<?>) right;
        List<?> leftList = (List<?>) left;
        if (rightList.get(0) instanceof Attribute && leftList.get(0) instanceof Attribute) {
          Attribute rightAttribute = (Attribute) rightList.get(0);
          Attribute leftAttribute = (Attribute) leftList.get(0);
          result = this.wrapper.assign(leftAttribute, rightAttribute);
        }

      } else {
        this.assign = false;
        result = this.wrapper.assign(left, right.toString());
      }
      // System.out.println("let result: " + result.toString());
      return result;
    }
  }

  @Override
  public Object visitPrefixedExp(PrefixedExpContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitIteratorBar(IteratorBarContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitImplies(ImpliesContext ctx) {
    if (ctx.op.getText().equals("implies")) {
      Object left = visit(ctx.left);
      Boolean bLeft = Boolean.parseBoolean(left.toString());
      Object right = visit(ctx.right);
      Boolean bRight = Boolean.parseBoolean(right.toString());
      return wrapper.implies(bLeft, bRight);
    }
    return null;
  }

  @Override
  public Object visitNumber(NumberContext ctx) {
    return Double.parseDouble(ctx.getText());
  }

  @Override
  public Object visitString(StringContext ctx) {
    return ctx.getText();
  }

  @Override
  public Object visitBoolean(BooleanContext ctx) {
    return Boolean.valueOf(ctx.getText());
  }

  @Override
  public Object visitInvalid(InvalidContext ctx) {
    return new OclInvalid().getValue();
  }

  @Override
  public Object visitNull(NullContext ctx) {
    return "null";
  }

  public DeepOCLClabjectWrapperImpl getWrapper() {
    return this.wrapper;
  }

  /**
   * for navigation a level higher in the model, e.g. from O2 to O1 models and using their
   * navigation
   */
  @Override
  public Object visitOntologicalName(OntologicalNameContext ctx) {
    try {
      wrapper.navigateOntoUp(ctx.clab.getText());
    } catch (NavigationException e) {
      return new OclInvalid();
    }
    return ctx.getText();
  }

  /**
   * visit a linguistic aspect, like deep model, level or clabject
   */
  @Override
  public Object visitLinguisticalName(LinguisticalNameContext ctx) {
    if (ctx.getText().contains("(")) {
      // System.out.println(ctx.aspect.getText());
      String param =
          ctx.getText().substring(ctx.getText().indexOf('(') + 1, ctx.getText().length() - 2);
      if (param.contains(",")) {
        String[] params = param.split(",");
        return this.wrapper.getLinguisticAspect(ctx.aspect.getText(), params);
      } else {
        String[] params = {param};
        return this.wrapper.getLinguisticAspect(ctx.aspect.getText(), params);
      }
    }
    return this.wrapper.getLinguisticAspect(ctx.aspect.getText(), null);
  }

  /**
   * reset all constraint types (false)
   */
  private void resetContraintTypes() {
    this.isBody = false;
    this.isDef = false;
    this.isDerive = false;
    this.isInit = false;
    this.isInv = false;
    this.isPost = false;
    this.isPre = false;
  }

  @Override
  public Object visitPrimaryExpCS(PrimaryExpCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitSelfExpCS(SelfExpCSContext ctx) {
    /*
     * if (collectionOperation == true && this.context instanceof Clabject) {
     * this.wrapper.self((Clabject) this.context); } this.wrapper.self(); return this.context;
     */
    if (this.context instanceof Clabject) {
      this.wrapper.self((Clabject) this.context);
      return this.context;
    } else {
      this.wrapper.self();
      return this.wrapper.getContext();
    }
  }

  /**
   * return a space.
   */
  @Override
  public Object visitOnespace(OnespaceContext ctx) {
    return " ";
  }

  @Override
  public Object visitLevelSpecificationCS(LevelSpecificationCSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Object visitCollectionArguments(CollectionArgumentsContext ctx) {
    // TODO Auto-generated method stub
    return visitChildren(ctx);
  }
}
