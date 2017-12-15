/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
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

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ErrorNodeImpl;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.AndOrXorContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.ArrowContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.BodyCSContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.BooleanContext;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser.ClassifierContextCSContext;
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
import org.melanee.ocl2.grammar.definition.grammar.DeepOclVisitor;
import org.melanee.ocl2.service.exception.InterpreterException;
import org.melanee.ocl2.service.exception.NavigationException;
import org.melanee.ocl2.service.util.LetVariable;
import org.melanee.ocl2.service.util.OclInvalid;
import org.melanee.ocl2.service.util.Tuple;

public class DeepOclRuleVisitor extends AbstractParseTreeVisitor<Object> implements DeepOclVisitor<Object> {
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
	private Collection<Element> tempCollection;
	private String tempString;
	private Collection<?> parameters;
	private Integer startLevel;
	private Integer endLevel;
	private Boolean assign;

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
		return visitChildren(ctx);
	}

	@Override
	public Object visitPrimitiveTypeCS(PrimitiveTypeCSContext ctx) {
		return ctx.getText();
		// return visitChildren(ctx);
	}

	@Override
	public Object visitTypeNameExpCS(TypeNameExpCSContext ctx) {
		return visitChildren(ctx);
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
		return visitChildren(ctx);
	}

	@Override
	public Object visitNestedExpCS(NestedExpCSContext ctx) {
		return visitChildren(ctx);

	}

	@Override
	public Object visitIfExpCS(IfExpCSContext ctx) {
		Object result = visit(ctx.ifexp);
		try {
			Boolean bool = Boolean.parseBoolean(result.toString());
			System.out.println(bool);
			this.assign = true;
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
			System.out.println(ctx.name.getText());
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
	 * so here it gets messy. probably a spot to begin refactoring. every time it is
	 * almost the same procedure. check if the tempCollection is not null and gather
	 * the parameters from the tree. iteration operation that return another
	 * selection rather than Integer or Boolean, get the current iterator object
	 * from the wrapper that represent the actual navigation in the deep model. As
	 * long as the iterator has next a new Wrapper object is created and as the new
	 * context the current navigation is set initially. Every operation that returns
	 * a new collection as a result is using the tempCollection. As mentioned,
	 * pretty messy!
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
						index = (Integer) visit(ctx.arg);
						System.out.println("atIndex: " + index);
					} catch (Exception e) {
						System.out.println("Could not find index in the parameter");
						return new OclInvalid();
					}
					if (this.tempCollection != null && this.tempCollection.size() > 0) {
						Element e = (Element) tempCollection.toArray()[index];
						tempCollection = null;
						return e;
					} else {
						return this.wrapper.getNavigationStack().peek().getSecond().toArray()[index];
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
						Element e = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[size - 1];
						this.wrapper.getNavigationStack()
								.add(new Tuple<String, Collection<Element>>("last", Arrays.asList(e)));
						return e;
					}
				}
				// is Empty
				else if (ctx.opName.getText().equals("isEmpty")) {
					if (this.tempCollection != null) {
						Collection<Element> list = this.tempCollection;
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
						Collection<Element> list = this.tempCollection;
						this.tempCollection = null;
						return !list.isEmpty();
					} else if (this.wrapper.getNavigationStack().peek().getSecond() != null) {
						return !this.wrapper.getNavigationStack().peek().getSecond().isEmpty();
					} else {
						return new OclInvalid();
					}
				}
				// sum
				else if (ctx.opName.getText().equals("sum")) {
					Object[] arg = new Object[1];
					Attribute attr = PLMFactory.eINSTANCE.createAttribute();
					if (this.tempCollection != null && this.tempCollection.size() > 0) {
						Collection<Element> list = tempCollection;
						tempCollection = null;
						arg[0] = list;
					} else if (this.wrapper.getNavigationStack().peek().getSecond() != null) {
						Collection<Element> list = this.wrapper.getNavigationStack().peek().getSecond();
						arg[0] = list;
					}
					try {
						Integer result = Integer.parseInt(this.wrapper.invoke("sum", arg).toString());
						attr.setValue(result.toString());
						attr.setDatatype("Integer");
						this.wrapper.getNavigationStack()
								.push(new Tuple<String, Collection<Element>>("sum", Arrays.asList(attr)));
					} catch (Exception e) {
						return new OclInvalid();
					}
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
							oldWrapper.getNavigationStack().push(new Tuple<String, Collection<Element>>("one", null));
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
				} else if (ctx.opName.getText().equals("any")) {
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
					visit(ctx.arg);
					if (this.tempCollection != null && this.tempCollection.size() > 0) {
						Collection<Element> list = tempCollection;
						tempCollection = null;
						Object[] args = new Object[2];
						args[0] = list;
						args[1] = this.wrapper.getNavigationStack().peek().getSecond();
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
				} // excludesAll: this method is passed through to the wrapper.
				else if (ctx.opName.getText().equals("excludesAll")) {
					visit(ctx.arg);
					if (this.tempCollection != null && this.tempCollection.size() > 0) {
						Collection<Element> list = tempCollection;
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
				} // excludes: this method is passed through to the wrapper.
				else if (ctx.opName.getText().equals("excludes")) {
					if (this.tempCollection != null && this.tempCollection.size() > 0) {
						visit(ctx.arg);
						Element element;
						if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1)
							element = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
						// else
						element = null;
						Collection<Element> list = tempCollection;
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
						if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1)
							element = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
						else
							element = null;
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
				} // includes: this method is passed through to the wrapper.
				else if (ctx.opName.getText().equals("includes")) {
					if (this.tempCollection != null && this.tempCollection.size() > 0) {
						visit(ctx.arg);
						Element element;
						if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1)
							element = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
						else
							element = null;
						Collection<Element> list = tempCollection;
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
						if (this.wrapper.getNavigationStack().peek().getSecond().size() == 1)
							element = (Element) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
						else
							element = null;
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
				// select
				else if (ctx.opName.getText().equals("select")) {
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
										.push(new Tuple<String, Collection<Element>>("select", null));
								return new OclInvalid();
							}
						} else {
							oldWrapper.getNavigationStack()
									.push(new Tuple<String, Collection<Element>>("select", null));
						}
					}
					this.wrapper = oldWrapper;
					if (list.size() > 0) {
						this.tempCollection = list;
						return null;
					} else {
						return null;
					}
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
					list.addAll(oldWrapper.getNavigationStack().peek().getSecond());
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
					if (list.size() > 0) {
						this.tempCollection = list;
						return this.tempCollection;
					} else {
						return null;
					}
				}
				// reverse logic as select operation
				else if (ctx.opName.getText().equals("reject")) {
					Collection<Element> list = new ArrayList<>();
					DeepOCLClabjectWrapperImpl oldWrapper = this.wrapper;
					Iterator<Element> it = wrapper.getCurrentCollectionIterator();
					while (it.hasNext()) {
						Clabject clab = (Clabject) it.next();
						if (ctx.arg.getText().equals("self")) {
							if (!clab.equals(wrapper.getContext())) {
								list.add(clab);
							}
							continue;
						}
						DeepOCLClabjectWrapperImpl newWrapper = new DeepOCLClabjectWrapperImpl(clab);
						this.wrapper = newWrapper;
						Object result = visit(ctx.arg);
						if (result != null) {
							try {
								boolean r = Boolean.parseBoolean(result.toString());
								if (r == false) {
									list.add(clab);
								}
							} catch (Exception e) {
								System.out.println("was not boolean nature");
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
					if (list.size() > 0) {
						this.tempCollection = list;
						return this.tempCollection;
					} else {
						return null;
					}
				} // forAll operation: similar to select operation)
				else if (ctx.opName.getText().equals("forAll")) {
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
								if (r == false) {
									// if any element is evaluated as false we
									// can immediately return false
									return false;
								}
							} catch (Exception e) {
								oldWrapper.getNavigationStack()
										.push(new Tuple<String, Collection<Element>>("forAll", null));
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
						this.wrapper.getNavigationStack().push(new Tuple<String, Collection<Element>>("asSet", null));
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
					Attribute attr = (Attribute) this.wrapper.getNavigationStack().peek().getSecond().toArray()[0];
					if (attr.getDatatype().equals("String")) {
						this.tempString = attr.getValue() + visitNavigatingArgCS(ctx.arg);
						return this.tempString;
					} else {
						System.out.println("Attribute was not from type String");
						return null;
					}
				} catch (ClassCastException e) {
					System.out.println("Could not cast NavigationElement to Attribute, i.e. could concat anything!");
					return null;
				}

			}
		} // oclIsTypeOf
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
		}
		if (this.wrapper.operationExist(ctx.opName.getText())) {
			if (ctx.arg.getText() != null && ctx.arg.getText() != "") {
				Object[] arg = {};
				try {
					this.wrapper.invoke(ctx.opName.getText(), arg);
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
		return visitChildren(ctx);
	}

	@Override
	public Object visitNavigatingCommaArgCS(NavigatingCommaArgCSContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Object visitNavigatingArgExpCS(NavigatingArgExpCSContext ctx) {
		if (ctx.body != null) {
			return visit(ctx.body);
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
		}
		return ctx.getText();
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
		Double dLeft = Double.parseDouble(left.toString());
		Object right = visit(ctx.left);
		Double dRight = Double.parseDouble(right.toString());
		switch (ctx.op.getText()) {
		case "*":
			return (dLeft * dRight);
		case "/":
			return (dLeft / dRight);
		}
		return visitChildren(ctx);
	}

	@Override
	public Object visitPlusMinus(PlusMinusContext ctx) {
		Object left = visit(ctx.left);
		Double dLeft = Double.parseDouble(left.toString());
		Object right = visit(ctx.left);
		Double dRight = Double.parseDouble(right.toString());
		switch (ctx.op.getText()) {
		case "+":
			return (dLeft + dRight);
		case "-":
			return (dLeft - dRight);
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
		}
		return visitChildren(ctx);
	}

	@Override
	public Object visitMessage(MessageContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Object visitEqualOperations(EqualOperationsContext ctx) {
		if (!this.assign) {
			try {
				visit(ctx.left);
				return this.wrapper.eval(visit(ctx.right).toString(), ctx.op.getText());
			} catch (InterpreterException e) {
				this.wrapper.getNavigationStack().clear();
				return new OclInvalid();
			}
		} else {
			// System.out.println("left: " + ctx.left.getText());
			visit(ctx.left);
			this.assign = false;
			Object result = this.wrapper.assign(visit(ctx.right).toString());
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
	 * for navigation a level higher in the model, e.g. from O2 to O1 models and
	 * using their navigation
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
			String param = ctx.getText().substring(ctx.getText().indexOf('(') + 1, ctx.getText().length() - 2);
			if (param.contains(",")) {
				String[] params = param.split(",");
				return this.wrapper.getLinguisticAspect(ctx.aspect.getText(), params);
			} else {
				String[] params = { param };
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
		if (collectionOperation == true && this.context instanceof Clabject) {
			this.wrapper.self((Clabject) this.context);
		}
		this.wrapper.self();
		return visitChildren(ctx);
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
}
