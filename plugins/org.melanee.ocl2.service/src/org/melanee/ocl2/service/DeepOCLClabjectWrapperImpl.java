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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.ocl.util.CollectionUtil;
import org.melanee.core.models.plm.PLM.AbstractConstraint;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Entity;
import org.melanee.core.models.plm.PLM.Feature;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.models.definition.constraint.BodyConstraint;
import org.melanee.ocl2.models.definition.constraint.DefinitionConstraint;
import org.melanee.ocl2.models.definition.constraint.PreConstraint;
import org.melanee.ocl2.service.exception.InterpreterException;
import org.melanee.ocl2.service.exception.NavigationException;
import org.melanee.ocl2.service.util.DeepOCL2Util;
import org.melanee.ocl2.service.util.LetVariable;
import org.melanee.ocl2.service.util.OclInvalid;
import org.melanee.ocl2.service.util.Tuple;

public class DeepOCLClabjectWrapperImpl implements DeepOCLClabjectWrapper {
  private Element context;
  private Collection<Element> newContext;
  private Collection<Element> tempCollection;
  private List<String> operationList;
  private String right;
  private String operator;
  private Stack<Tuple<String, Collection<Element>>> navigationStack;
  private List<LetVariable> letVariables;
  private String constraintType;
  private Collection<Element> parameters;
  private Integer startLevel;
  private Integer endLevel;
  private boolean ontologicalNavigation;

  public DeepOCLClabjectWrapperImpl(Element context) {
    this.context = context;
    this.tempCollection = new ArrayList<>();
    this.operationList = new ArrayList<>();
    this.loadOperations(operationList);
    this.newContext = null;
    this.navigationStack = new Stack<>();
    this.navigationStack.push(new Tuple<String, Collection<Element>>(this.context.getName(),
        Arrays.asList(this.context)));
    this.letVariables = new ArrayList<>();
    this.constraintType = null;
    this.startLevel = null;
    this.endLevel = null;
    this.ontologicalNavigation = false;
  }

  public DeepOCLClabjectWrapperImpl(Element context, Collection<Element> parameters) {
    this.context = context;
    this.tempCollection = new ArrayList<>();
    this.operationList = new ArrayList<>();
    this.loadOperations(operationList);
    this.newContext = null;
    this.navigationStack = new Stack<>();
    this.navigationStack.push(new Tuple<String, Collection<Element>>(this.context.getName(),
        Arrays.asList(this.context)));
    this.letVariables = new ArrayList<>();
    this.constraintType = null;
    this.parameters = parameters;
    this.startLevel = null;
    this.endLevel = null;
    this.ontologicalNavigation = false;
  }

  private void loadOperations(List<String> operationList) {
    operationList.add("forAll");
    operationList.add("exists");
    operationList.add("isUnique");
    operationList.add("one");
    operationList.add("insertAt");
    operationList.add("any");
    operationList.add("collect");
    operationList.add("collectNested");
    operationList.add("iterate");
    operationList.add("reject");
    operationList.add("select");
    operationList.add("sortedBy");
    operationList.add("excluding");
    operationList.add("flatten");
    operationList.add("prepend");
    operationList.add("append");
    operationList.add("symmetricDifference");
    operationList.add("union");
    operationList.add("intersection");
    operationList.add("asSet");
    operationList.add("asSequence");
    operationList.add("asOrderedSet");
    operationList.add("first");
    operationList.add("last");
    operationList.add("includes");
    operationList.add("excludes");
    operationList.add("isEmpty");
    operationList.add("notEmpty");
    operationList.add("excludesAll");
    operationList.add("includesAll");
    operationList.add("size");
    operationList.add("indexOf");
    operationList.add("at");
    operationList.add("sum");
    operationList.add("including");
    operationList.add("closure");
    // deepOCL operations
    operationList.add("allInstances");
    operationList.add("indirectInstances");
    operationList.add("directInstances");
    operationList.add("deepDirectInstances");
    operationList.add("deepIndirectInstances");
    operationList.add("deepInstances");
    operationList.add("instanceOf");
    operationList.add("isDeepInstanceOf");
    operationList.add("isDirectInstanceOf");
    operationList.add("isDeepDirectInstanceOf");
    operationList.add("isIndirectInstanceOf");
    operationList.add("isDeepIndirectInstanceOf");
    operationList.add("isDeepKindOf");
  }

  public List<String> getOperationList() {
    return this.operationList;
  }

  public void addOperation(String operation) {
    this.operationList.add(operation);
  }

  public Element getContext() {
    return this.context;
  }

  public Collection<Element> getNewContext() {
    return this.newContext;
  }

  public void setContext(String context) {
    if (context.equals("self")) {
      this.newContext = new ArrayList<>(Arrays.asList(this.context));
    }
  }

  /**
   * Please look at the operation you want to invoke in order to create the exact
   * Object array.
   * 
   * @param operation
   * @param arg
   * @return Object
   */
  public Object invoke(String operation, Object[] arg) throws Exception {
    if (!operationList.contains(operation)) {
      throw new Exception("Method not found in ocl operation" + operation);
    } else if (operation.equals("forAll")) {
      return forAll(arg);
    } else if (operation.equals("exists")) {
      return exists(arg);
    } else if (operation.equals("isUnique")) {
      return isUnique(arg);
    } else if (operation.equals("one")) {
      return one(arg);
    } else if (operation.equals("insertAt")) {
      return insertAt(arg);
    } else if (operation.equals("any")) {
      return any(arg);
    } else if (operation.equals("collect")) {
      return collect(arg);
    } else if (operation.equals("collectNested")) {
      return collectNested(arg);
    } else if (operation.equals("reject")) {
      return reject(arg);
    } else if (operation.equals("select")) {
      return select(arg);
    } else if (operation.equals("sortedBy")) {
      return sortedBy(arg);
    } else if (operation.equals("excluding")) {
      return excluding(arg);
    } else if (operation.equals("flatten")) {
      return flatten(arg);
    } else if (operation.equals("prepend")) {
      return prepend(arg);
    } else if (operation.equals("append")) {
      return append(arg);
    } else if (operation.equals("symmetricDifference")) {
      return symmetricDifference(arg);
    } else if (operation.equals("union")) {
      return union(arg);
    } else if (operation.equals("intersection")) {
      return intersection(arg);
    } else if (operation.equals("asSet")) {
      return asSet(arg);
    } else if (operation.equals("asBag")) {
      return asBag(arg);
    } else if (operation.equals("asSequence")) {
      return asSequence(arg);
    } else if (operation.equals("asOrderedSet")) {
      return asOrderedSet(arg);
    } else if (operation.equals("first")) {
      return first(arg);
    } else if (operation.equals("last")) {
      return last(arg);
    } else if (operation.equals("includes")) {
      return includes(arg);
    } else if (operation.equals("excludes")) {
      return excludes(arg);
    } else if (operation.equals("isEmpty")) {
      return isEmpty(arg);
    } else if (operation.equals("notEmpty")) {
      return notEmpty(arg);
    } else if (operation.equals("excludesAll")) {
      return excludesAll(arg);
    } else if (operation.equals("includesAll")) {
      return includesAll(arg);
    } else if (operation.equals("size")) {
      return size(arg);
    } else if (operation.equals("indexOf")) {
      return indexOf(arg);
    } else if (operation.equals("sum")) {
      return sum(arg);
    } else if (operation.equals("at")) {
      return at(arg);
    } else if (operation.equals("instances")) {
      return instances(arg);
    } else if (operation.equals("allInstances")) {
      return allInstances(arg);
    } else if (operation.equals("instanceOf")) {
      return instanceOf(arg);
    } else if (operation.equals("including")) { return including(arg); }
    return null;
  }
  /*
   * public void addOperation(String operation){
   * this.operationList.add(operation); }
   */

  private Object collectNested(Object[] arg) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * navigation Operation. navigation has to be on the same level as the current
   * context navigation, if not this operation won't find any stuff.
   */
  @Override
  public Object navigate(String target) throws NavigationException {
    List<Element> returnList = new ArrayList<>();
    Collection<Element> currentNavigation = this.navigationStack.peek().getSecond();
    for (Element element : currentNavigation) {
      if (element instanceof Attribute
          && navigationStack.size() == 1) { throw new NavigationException("something wrong here.",
              new Throwable(target)); }
      if (element instanceof Connection) {
        Connection connection = (Connection) element;
        for (ConnectionEnd connectionEnd : connection.getConnectionEnd()) {
          if (connectionEnd.getMoniker().equals(target)) {
            returnList.add(connectionEnd.getDestination());
          } else if (connectionEnd.getDestination().getName() != null
              && connectionEnd.getDestination().getName().equals(target)) {
            returnList.add(connectionEnd.getDestination());
          }
        }
        this.navigationStack.push(new Tuple<String, Collection<Element>>(target, returnList));
        return returnList;
      } else if (element instanceof Clabject) {
        for (Feature feature : ((Clabject) element).getFeature()) {
          if (feature instanceof Attribute) {
            Attribute attr = (Attribute) feature;
            if (attr.getName().equals(target)) {
              this.navigationStack
                  .push(new Tuple<String, Collection<Element>>(target, Arrays.asList(attr)));
              returnList.add(attr);
              return returnList;
            }
          }
        }
        for (Connection connection : ((Clabject) element).getConnections()) {
          if (connection.getMoniker().contains(target)) {
            EList<ConnectionEnd> ends = connection.getAllConnectionEnd();
            for (ConnectionEnd end : ends) {
              if (!end.getDestination().equals(element) && end.getMoniker().equals(target)) {
                if (ontologicalNavigation == true) {
                  // if ontological navigation we have to
                  // perform a lot of comparison on multiple
                  // levels.
                  Clabject ontoNav = (Clabject) this.navigationStack.peek().getSecond()
                      .toArray()[0];
                  int levelIndex = ((Clabject) this.context).getLevelIndex();
                  DeepModel dm = ((Clabject) this.context).getDeepModel();
                  Level level = dm.getLevelAtIndex(levelIndex + 1);
                  Clabject instance = null;
                  for (Element x : level.getContent()) {
                    if (x instanceof Clabject) {
                      if (((Clabject) x).getClassificationTreeAsInstance().contains(ontoNav)) {
                        instance = (Clabject) x;
                      }
                    }
                  }
                  for (Element e : level.getContent()) {
                    if (e instanceof Clabject && instance != null) {
                      if (((Clabject) e).getClassificationTreeAsInstance()
                          .contains(end.getDestination())) {
                        for (Connection c3 : ((Clabject) e).getConnections()) {
                          for (ConnectionEnd ende : c3.getConnectionEnd()) {
                            if (ende.getDestination().equals(e)
                                && c3.getParticipants().contains(instance) && ende.getLower() > 1) {
                              for (int i = 1; i <= ende.getLower(); i++) {
                                returnList.add(ende.getDestination());
                              }
                            } else if (ende.getDestination().equals(e)
                                && c3.getParticipants().contains(instance)) {
                              returnList.add(ende.getDestination());
                            }
                          }
                        }
                      }
                    }
                  }
                  this.navigationStack
                      .push(new Tuple<String, Collection<Element>>(target, returnList));
                  return returnList;
                } else if (end.getLower() == 1) {
                  ontologicalNavigation = false;
                  returnList.add(end.getDestination());
                  this.navigationStack.push(new Tuple<String, Collection<Element>>(
                      end.getDestination().getName(), returnList));
                  return end.getDestination();
                } else {
                  ontologicalNavigation = false;
                  returnList.add(end.getDestination());
                }
              }
            }
          } else if (connection.getName() != null && connection.getName().equals(target)) {
            returnList.add(connection);
            this.navigationStack.push(new Tuple<String, Collection<Element>>(target, returnList));
            return returnList;
          }
        }
        // if the list is empty, let's try to find a navigation on the
        // upper levels
        if (returnList.isEmpty()) {
          List<Connection> connectionList = new ArrayList<>();
          for (Clabject c : ((Clabject) element).getClassificationTreeAsInstance()) {
            for (Connection co : c.getConnections()) {
              for (ConnectionEnd end : co.getConnectionEnd()) {
                if (end.getMoniker() != null && end.getMoniker().equals(target)) {
                  connectionList.add(co);
                }
              }
            }
          }
          // go through all connections that were found from the
          // directTypes of the context clabject.
          for (Connection co1 : ((Clabject) element).getConnections()) {
            for (Connection co : connectionList) {
              if (co1.isInstanceOf(co)) {
                EList<ConnectionEnd> ends = co1.getAllConnectionEnd();
                for (ConnectionEnd end : ends) {
                  if (!end.getDestination().equals(element) && end.getMoniker().equals(target)) {
                    returnList.add(end.getDestination());
                  }
                }
              }
            }
          }
        }
        this.navigationStack.push(new Tuple<String, Collection<Element>>(target, returnList));
      } else if (!this.letVariables.isEmpty() && returnList.isEmpty()) {
        for (LetVariable letVariable : this.letVariables) {
          if (letVariable.getName().equals(target)) {
            // necessary because second item of the tuple has to
            // be a collection of elements
            Attribute attr = PLMFactory.eINSTANCE.createAttribute();
            attr.setDatatype(letVariable.getDataType());
            attr.setValue(letVariable.getValue().toString());
            attr.setName(letVariable.getName());
            returnList.add(attr);
            this.navigationStack.push(new Tuple<String, Collection<Element>>(target, returnList));
          }
        }
      } else if (element instanceof DeepModel) {
        if (target.equals("Clabject")) {
          DeepModel deepModel = (DeepModel) element;
          List<Element> clabList = new ArrayList<Element>();
          for (Level level : deepModel.getContent()) {
            clabList.addAll(level.getClabjects());
            Iterator<Element> iter = clabList.iterator();
            while (iter.hasNext()) {
              Element clab = iter.next();
              if (clab instanceof Connection || clab instanceof ConnectionEnd) iter.remove();
            }
            this.navigationStack
                .push(new Tuple<String, Collection<Element>>("Clabjects", clabList));
          }
        } else if (target.equals("Level")) {
          List<Element> levelList = new ArrayList<Element>();
          levelList.addAll(((DeepModel) element).getContent());
          this.navigationStack.push(new Tuple<String, Collection<Element>>("Levels", levelList));
          return ((DeepModel) element).getContent();
        } else if (target.equals("Connection")) {
          List<Element> connectionList = new ArrayList<Element>();
          for (Level level : ((DeepModel) element).getContent()) {
            connectionList.addAll(level.getConnections());
          }
          this.navigationStack
              .push(new Tuple<String, Collection<Element>>("Connections", connectionList));
          return connectionList;
        }
      }
    }
    return returnList.size() == 0 ? null : returnList;
  }

  /**
   * if you want navigate up to a higher level, ie to the directType of the
   * context your in use this method (level cast)
   * 
   * @param target
   * @return
   * @throws NavigationException
   */
  public Collection<Element> navigateOntoUp(String target) throws NavigationException {
    ontologicalNavigation = true;
    Collection<Element> returnCollection = new ArrayList<>();
    Collection<Element> nav = this.navigationStack.peek().getSecond();
    if (nav.size() == 1) {
      Clabject clab = (Clabject) nav.toArray()[0];
      for (Clabject cl : clab.getClassificationTreeAsInstance()) {
        if (cl.getName().equals(target)) {
          returnCollection.add(cl);
          navigationStack.push(new Tuple<String, Collection<Element>>(target, returnCollection));
        }
      }
    } else {
      throw new NavigationException("upcast is only possible from one element");
    }
    return returnCollection.size() == 0 ? null : returnCollection;
  }

  public boolean implies(boolean a, boolean b) {
    if (a) {
      if (b) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  @Override
  public Boolean oclIsTypeOf(String target) {
    Boolean isTypeOf = true;
    Class<?> clazz;
    try {
      if (target.equals("Clabject")) {
        clazz = Class.forName("org.melanee.core.models.plm.PLM.impl.EntityImpl");
      } else {
        clazz = Class.forName("org.melanee.core.models.plm.PLM.impl." + target + "Impl");
      }
      for (Element e : this.navigationStack.peek().getSecond()) {
        if (!e.getClass().getSimpleName().equals(clazz.getSimpleName())) {
          isTypeOf = false;
        }
      }
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }
    return isTypeOf;
  }

  /**
   * only types of targets are "DeepModel","Level","Clabject"
   */
  @Override
  public Boolean oclIsKindOf(String target) {
    Boolean isTypeOf = true;
    try {
      Class<?> clazz;
      if (target.equals("Clabject")) {
        clazz = Class.forName("org.melanee.core.models.plm.PLM.Entity");
      } else {
        clazz = Class.forName("org.melanee.core.models.plm.PLM." + target);
      }
      // I think the string comparison is more exact in this cas than the
      // regular JAVA tool to compare instances of classes
      for (Element e : this.navigationStack.peek().getSecond()) {
        if (!e.getClass().getInterfaces()[0].equals(clazz)) {
          isTypeOf = false;
        }
      }
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
      return false;
    }
    return isTypeOf;
  }

  public Object oclAsType(String target) {
    try {
      Class<?> clazz;
      if (target.equals("Clabject")) {
        clazz = Class.forName("org.melanee.core.models.plm.PLM.Entity");
      } else {
        clazz = Class.forName("org.melanee.core.models.plm.PLM." + target);
      }
      for (Element e : this.navigationStack.peek().getSecond()) {
        if (e.getClass().isInstance(clazz)) { return e.getClass().cast(clazz); }
      }
    } catch (Exception e1) {
      e1.printStackTrace();
      return new OclInvalid();
    }
    return true;
  }

  @Override
  public Integer oclIntegerMax(int i1, int i2) {
    if (i1 < i2) {
      return i2;
    } else {
      return i1;
    }
  }

  @Override
  public Integer oclIntegerMin(int i1, int i2) {
    if (i1 < i2) {
      return i1;
    } else {
      return i2;
    }
  }

  /**
   * "Deep" methods block
   */

  public Boolean isDeepDirectInstanceOf(String text) {
    Clabject context = (Clabject) this.context;
    List<Clabject> resultList = new ArrayList<>();
    resultList = DeepOCL2Util.traverseClassifications(context, resultList);
    for (Clabject clab : resultList) {
      if (clab.getName().equals(text)) { return true; }
    }
    return false;
  }

  public Boolean isDirectInstanceOf(String text) {
    Clabject context = (Clabject) this.context;
    for (Element type : context.getDirectTypes()) {
      if (type.getName().equals(text)) { return true; }
    }
    return false;
  }

  public Boolean isDeepInstanceOf(String text) {
    Clabject context = (Clabject) this.context;
    for (Element type : context.getClassificationTreeAsInstance()) {
      if (type.getName().equals(text)) { return true; }
    }
    return false;
  }

  public Object isInstanceOf(String text) {
    Boolean result = false;
    try {
      Clabject context = (Clabject) this.context;
      for (Element type : context.getDirectTypes()) {
        Clabject clab = (Clabject) type;
        if (clab.getName().equals(text)) {
          result = true;
        }
      }
    } catch (ClassCastException e) {
      // not castable to Clabject, which is necessary
      return null;
    }
    return result;
  }

  /**
   * 
   * @param text
   *          Clabject
   * @return
   */
  public Object isDeepKindOf(String text) {
    Boolean result = false;
    try {
      Clabject context = (Clabject) this.context;
      for (Element type : context.getDirectTypes()) {
        Clabject clab = (Clabject) type;
        // is there is no name of the clabject a NullPointer will be thrown. Like
        // connection without a name
        try {
          if (clab.getName().equals(text)) {
            result = true;
          }
        } catch (Exception e) {
          // TODO: handle exception
        }
      }
      if (!result) {
        for (Element type : context.getDirectTypes()) {
          Clabject clab = (Clabject) type;
          for (Clabject supertype : clab.getSupertypes()) {
            if (supertype.getName().equals(text)) {
              result = true;
            }
          }
        }
      }
    } catch (ClassCastException e) {
      // not castable to Clabject, which is necessary
      return null;
    }
    return result;
  }

  private Boolean instanceOf(Object[] arg) {
    return ((Clabject) this.context).isInstanceOf((Clabject) arg[0]);
  }

  /**
   * arg[0] has to the collection to insert at arg[1] has to the object to insert
   * into the collection arg[2] has to the index.
   * 
   * @param arg
   *          Object[]
   * @return
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Collection<?> insertAt(Object[] arg) {
    Collection collection = (Collection<?>) arg[0];
    Object object = arg[1];
    int index = (Integer) arg[2];
    return CollectionUtil.insertAt(collection, index, object);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Collection<?> symmetricDifference(Object[] arg) {
    return CollectionUtil.symmetricDifference((Set) arg[0], (Set) arg[1]);
  }

  private Collection<?> union(Object[] arg) {
    return CollectionUtil.union((Collection<?>) arg[0], (Collection<?>) arg[1]);
  }

  private Boolean includesAll(Object[] arg) {
    return CollectionUtil.includesAll((Collection<?>) arg[0], (Collection<?>) arg[1]);
  }

  private Boolean excludesAll(Object[] arg) {
    return CollectionUtil.excludesAll((Collection<?>) arg[0], (Collection<?>) arg[1]);
  }

  private Boolean notEmpty(Object[] arg) {
    return !((Collection<?>) arg[0]).isEmpty();
  }

  private Boolean isEmpty(Object[] arg) {
    return ((Collection<?>) arg[0]).isEmpty();
  }

  private Collection<?> last(Object[] arg) {
    Collection<Object> returnCollection = new ArrayList<>();
    Collection<?> collection = (Collection<?>) arg[0];
    int index = collection.size() - 1;
    returnCollection.add(collection.toArray()[index]);
    return returnCollection;
  }

  private Collection<?> first(Object[] arg) {
    Collection<Object> returnCollection = new ArrayList<>();
    returnCollection.add(((Collection<?>) arg[0]).toArray()[0]);
    return returnCollection;
  }

  private Collection<?> asOrderedSet(Object[] arg) {
    return CollectionUtil.asOrderedSet((Collection<?>) arg[0]);
  }

  private Collection<?> asSequence(Object[] arg) {
    return CollectionUtil.asSequence((Collection<?>) arg[0]);
  }

  private Collection<?> asBag(Object[] arg) {
    return CollectionUtil.asBag((Collection<?>) arg[0]);
  }

  /**
   * 
   * @param arg
   * @return collection as Set
   */
  private Collection<?> asSet(Object[] arg) {
    return CollectionUtil.asSet((Collection<?>) arg[0]);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Collection<?> append(Object[] arg) {
    return CollectionUtil.append((Collection) arg[0], arg[1]);

  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Collection<?> prepend(Object[] arg) {
    return CollectionUtil.prepend((Collection) arg[0], arg[1]);
  }

  /**
   * 
   * @param collection
   * @param collection1
   * @return
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Collection<?> intersection(Object[] arg) {
    return CollectionUtil.intersection((Collection) arg[0], (Collection) arg[1]);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Collection<?> including(Object[] arg) {
    return CollectionUtil.including((Collection) arg[0], arg[1]);
  }

  private Boolean includes(Object[] arg) {
    return CollectionUtil.includes((Collection<?>) arg[0], arg[1]);
  }

  /**
   * arg[0] = collection
   * 
   * @param arg
   * @return
   */
  private Collection<?> flatten(Object[] arg) {
    return CollectionUtil.flatten((Collection<?>) arg[0]);
  }

  /**
   * arg[0] collection, arg[1] object
   * 
   * @param arg
   * @return
   */
  private Collection<?> excluding(Object[] arg) {
    return CollectionUtil.excluding((Collection<?>) arg[0], arg[1]);
  }

  private Boolean excludes(Object[] arg) {
    return CollectionUtil.excludes((Collection<?>) arg[0], arg[1]);
  }

  private Integer size(Object[] arg) {
    return ((Collection<?>) arg[0]).size();
  }

  private Number sum(Object[] arg) {
    return (Number) CollectionUtil.sum((Collection<?>) arg[0]);
  }

  /**
   * DeepOCLClabjectWrapperImpl returns the index of the object of the collection
   * 
   * @param collection
   * @param object
   * @return
   */
  private Integer indexOf(Object[] arg) {
    return CollectionUtil.indexOf((Collection<?>) arg[0], arg[1]);
  }

  /**
   * 
   * @param collection
   * @param expression
   * @return
   */
  private Collection<?> sortedBy(Object[] arg) {
    // TODO sortedBy
    return null;
  }

  /**
   * return the inverse collection compared to the select operation returns all
   * element for which the expression is false as a collection
   * 
   * @param collection
   * @param expression
   * @return
   */
  private Collection<?> reject(Object[] arg) {
    Collection<Clabject> returnCollection = new HashSet<>();
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject clabject = (Clabject) it.next();
      if (!compare(clabject.getFeatureForName(expressionMap.get("left")),
          expressionMap.get("right"), expressionMap.get("operator"))) {
        returnCollection.add(clabject);
      }
    }
    return returnCollection;
  }

  /**
   * arg[0] has to a collection and arg[1] has to be an expression
   * 
   * @param collection
   * @param expression
   * @return
   */
  private Boolean one(Object[] arg) {
    Set<Clabject> returnCollection = new HashSet<>();
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject c = (Clabject) it.next();
      Attribute attr = (Attribute) c.getFeatureForName(expressionMap.get("left"));
      if (compare(attr.getValue(), expressionMap.get("right"), expressionMap.get("operator"))) {
        returnCollection.add(c);
        if (returnCollection.size() > 1) { return false; }
      }
    }
    return true;
  }

  private Object instances(Object[] arg) {
    if (this.navigationStack.peek().getSecond().size() == 1) {
      List<Element> returnCollection = new ArrayList<>();
      if (this.navigationStack.peek().getSecond().toArray()[0] instanceof Clabject) {
        Collection<Clabject> tempCollection = ((Clabject) this.navigationStack.peek().getSecond()
            .toArray()[0]).getDefinedInstances();
        for (Clabject c : tempCollection) {
          returnCollection.add(c);
        }
        this.navigationStack.push(new Tuple<String, Collection<Element>>("allInstances",
            (Collection<Element>) returnCollection));
        return returnCollection;
      } else {
        return new OclInvalid();
      }
    } else {
      return new OclInvalid();
    }
  }

  private Object allInstances(Object[] arg) {
    if (this.navigationStack.peek().getSecond().size() == 1) {
      List<Element> returnCollection = new ArrayList<>();
      if (this.navigationStack.peek().getSecond().toArray()[0] instanceof Clabject) {
        Collection<Clabject> tempCollection = ((Clabject) this.navigationStack.peek().getSecond()
            .toArray()[0]).getDefinedInstances();
        for (Clabject c : tempCollection) {
          returnCollection.add(c);
        }
        this.navigationStack.push(new Tuple<String, Collection<Element>>("allInstances",
            (Collection<Element>) returnCollection));
        return returnCollection;
      } else {
        return new OclInvalid();
      }
    } else {
      return new OclInvalid();
    }
  }

  public Boolean eval(String right, String operator) throws InterpreterException {
    if (this.navigationStack.peek().getSecond() != null
        && this.navigationStack.peek().getSecond().size() == 1) {
      Map<String, String> expressionMap = new HashMap<>();
      expressionMap.put("right", right);
      expressionMap.put("operator", operator);
      return castAndCompare((Attribute) this.navigationStack.peek().getSecond().toArray()[0],
          expressionMap);
    } else {
      throw new InterpreterException("tried to access an attribute from a collection?");
    }
  }

  public Boolean eval(Object right, String operator) throws InterpreterException {
    if (right instanceof Collection) {
      right = ((Collection) right).toArray()[0];
    }
    if (right instanceof Attribute) {
      Attribute attribute = (Attribute) right;
      if (this.navigationStack.peek().getSecond() != null
          && this.navigationStack.peek().getSecond().size() == 1) {
        Map<String, String> expressionMap = new HashMap<>();
        expressionMap.put("right", attribute.getValue());
        expressionMap.put("operator", operator);
        return castAndCompare((Attribute) this.navigationStack.peek().getSecond().toArray()[0],
            expressionMap);
      } else {
        throw new InterpreterException("tried to access an attribute from a collection?");
      }
    } else if (right instanceof String) {
      return eval((String) right, operator);
    } else if (right instanceof Number) {
      return eval(right.toString(), operator);
    } else {
      throw new InterpreterException("did not work");
    }
  }

  public Boolean eval(Object left, Object right, String operator) throws InterpreterException {
    if (right instanceof Collection) {
      right = ((Collection) right).toArray()[0];
    }
    if (left instanceof Collection && !((Collection) left).isEmpty()) {
      left = ((Collection) left).toArray()[0];
    }
    if (right instanceof Attribute && left instanceof Attribute) {
      Attribute attributeRight = (Attribute) right;
      Attribute attributeLeft = (Attribute) left;
      Map<String, String> expressionMap = new HashMap<>();
      expressionMap.put("right", attributeRight.getValue());
      expressionMap.put("operator", operator);
      return castAndCompare(attributeLeft, expressionMap);
    } else if (right instanceof Boolean && left instanceof Attribute) {
      Attribute attributeLeft = (Attribute) left;
      Map<String, String> expressionMap = new HashMap<>();
      expressionMap.put("right", right.toString());
      expressionMap.put("operator", operator);
      return castAndCompare(attributeLeft, expressionMap);
    } else if (right instanceof String) {
      return eval((String) right, operator);
    } else if (right instanceof Number) {
      return eval(right.toString(), operator);
    } else {
      throw new InterpreterException("did not work");
    }
  }

  public Boolean eval(String left, String right, String operator) throws InterpreterException {
    if (operator.contains("<") || operator.contains(">") || operator.contains(">=")
        || operator.contains("<=") || operator.contains("=")) {
      try {
        Double dR = Double.parseDouble(right);
        Double dL = Double.parseDouble(left);
        return compare(dL, dR, operator);
      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }

  /**
   * arg[0] has to be a collection and arg[1] has to be the expression
   * 
   * @param collection
   * @param expression
   * @return
   */
  private Boolean isUnique(Object[] arg) {
    Set set = new HashSet<>();
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject clabject = (Clabject) it.next();
      if (!set.add(((Attribute) clabject.getFeatureForName(expressionMap.get("attribute")))
          .getValue())) { return false; }
    }
    return true;
  }

  /**
   * arg[0] has to be a collection and arg[1] has to be an expression.
   * 
   * @param collection
   *          collection to check
   * @param expression
   *          expression to check the collection for
   * @return
   */
  private Boolean exists(Object[] arg) {
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject c = (Clabject) it.next();
      Attribute a = (Attribute) c.getFeatureForName(expressionMap.get("left"));
      if (castAndCompare(a, expressionMap)) { return true; }
    }
    return false;
  }

  /**
   * collects the value of the attribute (expression), and puts it in a collection
   * of that type. method is pretty long and similar to the cast and compare
   * method a little further down due the casting of the proper data type for the
   * lists that are returned.
   * 
   * @param arg
   *          : arg[0] collection; arg[1] expression
   * @return
   */
  private Collection<?> collect(Object[] arg) {
    return null;
  }

  /**
   * arg[0] = collection<?> ; arg[1] = expression : String
   * 
   * @return
   */
  private Collection<?> any(Object[] arg) {
    List<Clabject> returnCollection = new ArrayList<>();
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject c = (Clabject) it.next();
      Attribute a = (Attribute) c.getFeatureForName(expressionMap.get("left"));
      if (castAndCompare(a, expressionMap)) {
        returnCollection.add(c);
        return returnCollection;
      }
    }
    return null;
  }

  /**
   * in arg, there has to be a collection at index=0 and an expression at index=1
   * 
   * @param arg
   * @return
   */
  private Boolean forAll(Object[] arg) {
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject clabject = (Clabject) it.next();
      Attribute a = (Attribute) clabject.getFeatureForName(expressionMap.get("left"));
      if (castAndCompare(a, expressionMap)) {
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * select from a collection and return a new collection
   * 
   * @param arg
   * @return
   * @throws NavigationException
   * @throws InterpreterException
   */
  private Collection<?> select(Object[] arg) throws NavigationException, InterpreterException {
    Collection<Clabject> returnCollection = new HashSet<>();
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject clabject = (Clabject) it.next();
      Collection<Element> c = (Collection<Element>) navigate(expressionMap.get("left"));
      // Attribute a = (Attribute)
      // clabject.getFeatureForName(expressionMap.get("left"));
      for (Element element : c) {
        try {
          Attribute a = (Attribute) element;
          if (castAndCompare(a, expressionMap)) {
            returnCollection.add(clabject);
          }
        } catch (ClassCastException e) {
          throw new InterpreterException(e.getMessage(), e.getCause());
        }
      }
    }
    return returnCollection;
  }

  /**
   * arg[0] = collection; arg[1] = index : Integer
   * 
   * @param arg
   * @return
   */
  private Object at(Object[] arg) {
    return CollectionUtil.at((Collection<?>) arg[0], (Integer) arg[1]);
  }

  /**
   * returns the linguistic aspect of the meta model (plm)
   * 
   * @param text
   * @param params
   * @return
   */
  @SuppressWarnings("unchecked")
  public Object getLinguisticAspect(String text, String[] params) {
    EList<Object> argList = new BasicEList<Object>();
    Object result = null;
    for (Element e : this.navigationStack.peek().getSecond()) {
      if (e instanceof Clabject) {
        Clabject clab = (Clabject) e;
        Method method;
        primitiveType: try {
          method = clab.getClass().getMethod(text, null);
          Attribute attr = PLMFactory.eINSTANCE.createAttribute();
          if (method.getReturnType().toString().equals("int")) {
            attr.setDatatype("Integer");
          } else if (method.getReturnType().toString().equals("long")) {
            attr.setDatatype("Real");
          } else if (method.getReturnType().toString().equals("class java.lang.String")) {
            attr.setDatatype("String");
          } else if (method.getReturnType().toString().equals("double")) {
            attr.setDatatype("Real");
          } else if (method.getReturnType().toString().equals("boolean")) {
            attr.setDatatype("Boolean");
          } else {
            break primitiveType;
          }
          attr.setValue(method.invoke(clab, null).toString());
          this.navigationStack
              .push(new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) attr)));
          return method.invoke(clab, null);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        oldLoop: for (EOperation operation : clab.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) { return new OclInvalid(); }
              index++;
            }
            try {
              result = ((Clabject) e).eInvoke(operation, argList);
              if (result instanceof Collection<?>) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                return result;
              } else if (result instanceof Element) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) result)));
                return Arrays.asList(result);
              }
            } catch (Exception exception) {
              return new OclInvalid();
            }
          }
        }
      } else if (e instanceof Connection) {
        Connection connect = (Connection) e;
        for (EOperation operation : connect.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) { return new OclInvalid(); }
              index++;
            }
            try {
              result = ((Connection) e).eInvoke(operation, argList);
              if (result instanceof Collection<?>) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                return result;
              } else if (result instanceof Element) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) result)));
                return Arrays.asList(result);
              }
            } catch (Exception exception) {
              return new OclInvalid();
            }
          }
        }
      } else if (e instanceof Attribute) {
        for (EOperation operation : e.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) { return new OclInvalid(); }
              index++;
            }
            try {
              result = ((Attribute) e).eInvoke(operation, argList);
              if (result instanceof Collection<?>) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                return result;
              } else if (result instanceof Element) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) result)));
                return Arrays.asList(result);
              }
            } catch (Exception exception) {
              return new OclInvalid();
            }
          }
        }
      }
      // if the context is the deepModel then it is assumed that the constraint is to
      // be reflective
      else if (e instanceof DeepModel) {
        DeepModel deepModel = (DeepModel) e;
        for (EOperation operation : deepModel.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) { return new OclInvalid(); }
              index++;
            }
            try {
              result = ((DeepModel) e).eInvoke(operation, argList);
              if (result instanceof Collection<?>) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                return result;
              } else if (result instanceof Element) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) result)));
                return Arrays.asList(result);
              }
            } catch (Exception exception) {
              return new OclInvalid();
            }
          }
        }
      } else if (e instanceof Level) {
        Level level = (Level) e;
        for (EOperation operation : level.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) { return new OclInvalid(); }
              index++;
            }
            try {
              result = ((Level) e).eInvoke(operation, argList);
              if (result instanceof Collection<?>) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
              } else if (result instanceof Element) {
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) result)));
              }
            } catch (Exception exception) {
              return new OclInvalid();
            }
          }
        }
      }
      if (result == null) {
        // last resort; when result == null this method searches for the
        // navigation in the all features of the meta model
        if (e instanceof Clabject) {
          e = (Clabject) e;
        }
        if (e instanceof Level) {
          e = (Level) e;
        }
        if (e instanceof DeepModel) {
          e = (DeepModel) e;
        }
        if (e instanceof Connection) {
          e = (Connection) e;
        }
        for (EStructuralFeature feature : e.eClass().getEAllStructuralFeatures()) {
          if (feature.getName().equals(text)) {
            try {
              result = e.eGet(feature);
              if (result instanceof Collection<?>) {
                result = (Collection<Element>) result;
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
              } else if (result instanceof Element) {
                result = Arrays.asList((Element) result);
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) result)));
              } else if (result instanceof Integer) {
                Attribute attr = PLMFactory.eINSTANCE.createAttribute();
                attr.setDatatype("Integer");
                attr.setValue(result.toString());
                result = Arrays.asList(attr);
                this.navigationStack.push(
                    new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
              }
            } catch (Exception exception) {
              return new OclInvalid();
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * |=====================================================================|
   * |helper operations section |
   * |=====================================================================|
   */

  /**
   * maybe more types are needed, these are enough for the moment. Input values
   * are the clabject's attribute and the expression which the value of the
   * attribute is compared with. The first value of the returnCollection is the
   * casted attribute value and the second value is the properly casted expression
   * which the attribute is compared with
   * 
   * @param a
   * @param expressionMap
   * @return Boolean
   */
  private Boolean castAndCompare(Attribute a, Map<String, String> expressionMap) {
    if (expressionMap == null) {
      expressionMap = new HashMap<>();
      expressionMap.put("right", this.right);
      expressionMap.put("operator", this.operator);
    }
    try {
      if (a.getDatatype().equals("Integer")) {
        int first = Integer.parseInt(a.getValue());
        Double d = Double.parseDouble(expressionMap.get("right"));
        int second = d.intValue();
        return compare(first, second, expressionMap.get("operator"));
      } else if (a.getDatatype().equals("Real")) {
        double first = Double.parseDouble(a.getValue());
        double second = Double.parseDouble(expressionMap.get("right"));
        return compare(first, second, expressionMap.get("operator"));
      } else if (a.getDatatype().equals("String")) {
        String first = a.getValue().replaceAll("\"", "");
        String second = expressionMap.get("right").replaceAll("\"", "");
        return compare(first, second, expressionMap.get("operator"));
      } else if (a.getDatatype().equals("Boolean")) {
        Boolean first = Boolean.parseBoolean(a.getValue());
        Boolean second = Boolean.parseBoolean(expressionMap.get("right"));
        return compare(first, second, expressionMap.get("operator"));
      }
    } catch (NullPointerException e) {
      System.out
          .println("Could not access Attribute " + a + " or get Value from this Attribute. \n");
      // if the attribute has no value as a String or otherwise then this check will
      // be made for safety.
      String second = expressionMap.get("right").replaceAll("\"", "");
      if (expressionMap.get("operator").equals("<>") && second.isEmpty()) {
        return true;
      } else {
        e.printStackTrace();
        return null;
      }
    }
    return null;
  }

  /**
   * splits the expression if the string contains a special character, like = >=
   * <= and so on.
   * 
   * @param expression
   * @return
   */
  private Map<String, String> splitExpression(String expression) {
    Map<String, String> map = new HashMap<String, String>();
    Pattern pattern = Pattern.compile("[^A-Za-z0-9\\.\\s*]");
    if (expression.contains("|")) {
      String[] splitted = expression.split("\\|");
      if (splitted.length == 2 && splitted[1].contains(".")) {
        Matcher match = pattern.matcher(splitted[1].split("\\.")[1]);
        if (match.find()) {
          System.out.println(splitted[1].split("\\.")[1]);
          return splitExpression(splitted[1].split("\\.")[1]);
        } else {
          map.put("attribute", splitted[1].split("\\.")[1]);
        }
      }
      return map;
    } else {
      String operator = "";
      Matcher matcher = pattern.matcher(expression);
      while (matcher.find()) {
        String match = matcher.group();
        operator += match;
      }
      String subLeft = expression.substring(0, expression.indexOf(operator));
      String subRight = expression.substring(expression.indexOf(operator) + operator.length());
      map.put("left", subLeft.trim());
      map.put("right", subRight.trim());
      map.put("operator", operator);
      return map;
    }
  }

  /**
   * compares integer values and return true or false depending on the operator
   * 
   * @param first
   * @param second
   * @param operator
   * @return
   */
  private Boolean compare(int first, int second, String operator) {
    if ("=".equals(operator)) {
      return first == second;
    } else if ("<=".equals(operator)) {
      return first <= second;
    } else if (">=".equals(operator)) {
      return first >= second;
    } else if ("<".equals(operator)) {
      return first < second;
    } else if (">".equals(operator)) {
      return first > second;
    } else {
      return null;
    }
  }

  /**
   * compares double values and returns true or false depending on the operator
   * 
   * @param first
   * @param second
   * @param operator
   * @return
   */
  private Boolean compare(double first, double second, String operator) {
    if ("=".equals(operator)) {
      return first == second;
    } else if ("<=".equals(operator)) {
      return first <= second;
    } else if (">=".equals(operator)) {
      return first >= second;
    } else if ("<".equals(operator)) {
      return first < second;
    } else if (">".equals(operator)) {
      return first > second;
    } else {
      return null;
    }
  }

  /**
   * compares string values and returns true or false depending on the operator
   * 
   * @param first
   * @param second
   * @param operation
   * @return
   */
  private Boolean compare(String first, String second, String operation) {
    switch (operation) {
      case "=":
        return first.equals(second);
      case "<>":
        return !first.equals(second);
      default:
        return null;
    }
  }

  /**
   * compares Objects and returns true or false depending on the operator
   * 
   * @param object
   * @param object1
   * @param operator
   * @return
   */
  private Boolean compare(Object object, Object object1, String operator) {
    switch (operator) {
      case "=":
        return object == object1;
      case "<>":
        return object != object1;
      default:
        return null;
    }
  }

  public void self() {
    this.navigationStack.clear();
    this.navigationStack.push(new Tuple<String, Collection<Element>>(this.context.getName(),
        Arrays.asList(this.context)));
  }

  public void setRight(String right) {
    this.right = right;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public void setConstraintType(String type) {
    this.constraintType = type;
  }

  public Iterator<Element> getCurrentCollectionIterator() {
    return this.navigationStack.peek().getSecond().iterator();
  }

  public Stack<Tuple<String, Collection<Element>>> getNavigationStack() {
    return this.navigationStack;
  }

  /**
   * adds a Tuple to let Variable list. First element is the name of the variable
   * and the second element in the tuple is the actual variable
   * 
   * @param letName
   * @param letType
   * @param value
   * @throws InterpreterException
   */
  public void createLetVariable(String letName, String letType, String value)
      throws InterpreterException {
    LetVariable letVariable = new LetVariable(letName, letType, value, (Clabject) this.context);
    this.letVariables.add(letVariable);
  }

  /**
   * assign a value to a let variable
   * 
   * @param value
   * @return
   */
  public Object assign(String value) {
    if (this.navigationStack.peek().getSecond().size() == 1) {
      try {
        Attribute attr = (Attribute) this.navigationStack.pop().getSecond().toArray()[0];
        for (LetVariable letVariable : this.letVariables) {
          if (letVariable.getName().equals(attr.getName())) {
            letVariable.setValue(value);
            return letVariable.getValue();
          } else {
            return null;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    } else {
      return null;
    }
    return null;
  }

  // when doing loop collection operation a new instance of this class, with a
  // context parameter is created for each iterator variable and we need a
  // method to navigate to the old context. that is why we need this method
  public void self(Clabject context2) {
    this.navigationStack.push(
        new Tuple<String, Collection<Element>>(this.context.getName(), Arrays.asList(context2)));
  }

  public boolean operationExist(String text) {
    if (this.operationList.contains(text)) { return true; }
    return false;
  }

  public boolean operationOnClabject(String operation) {
    boolean result = false;
    for (Element element : this.navigationStack.peek().getSecond()) {
      if (element instanceof Clabject) {
        for (Feature method : ((Clabject) element).getDefinedMethods()) {
          if (!method.getName().substring(0, method.getName().indexOf("(")).equals(operation)) {
            return false;
          } else {
            result = true;
          }
        }
      }
    }
    return result;
  }

  // execute methods that are defined by def or body constraints
  public Object executeClabjectOperation(String text, Object[] args) {
    Object result = null;
    boolean preConstraint = true;
    for (Element element : this.navigationStack.peek().getSecond()) {
      if (element instanceof Clabject) {
        for (Feature feature : ((Clabject) element).getDefinedMethods()) {
          if (feature.getName().substring(0, feature.getName().indexOf("(")).equals(text)) {
            for (AbstractConstraint constraint : feature.getConstraint()) {
              if (constraint instanceof BodyConstraint && preConstraint) {
                if (DeepOCL2Util.checkPreConstraints(feature, (Clabject) element)) {
                  String oclExpression = "body:" + ((BodyConstraint) constraint).getText();
                  DeepOclLexer ocl2Lexer = new DeepOclLexer(new ANTLRInputStream(oclExpression));
                  DeepOclParser parser = new DeepOclParser(new CommonTokenStream(ocl2Lexer));
                  ParseTree tree = parser.bodyCS();
                  DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(element);
                  result = visitor.visit(tree);
                } else {
                  return new OclInvalid();
                }
              }
            }
          }
        }
        // if no body constraint was found on the method with that name
        // and if the method does not exist on the clabject, we have to
        // search for it in the definition constraint on the clabject
        if (result == null) {
          for (AbstractConstraint constraint : element.getConstraint()) {
            if (constraint instanceof DefinitionConstraint && text
                .equals(constraint.getName().substring(0, constraint.getName().indexOf("(")))) {
              String oclExpression = "def:" + ((BodyConstraint) constraint).getText();
              DeepOclLexer ocl2Lexer = new DeepOclLexer(new ANTLRInputStream(oclExpression));
              DeepOclParser parser = new DeepOclParser(new CommonTokenStream(ocl2Lexer));
              ParseTree tree = parser.defCS();
              DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(element);
              result = visitor.visit(tree).toString();
            }
          }
        }
      }
    }
    return result;
  }
}