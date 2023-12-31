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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Feature;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.models.definition.constraint.BodyConstraint;
import org.melanee.ocl2.models.definition.constraint.DefinitionConstraint;
import org.melanee.ocl2.service.exception.InterpreterException;
import org.melanee.ocl2.service.exception.NavigationException;
import org.melanee.ocl2.service.util.DeepOCL2Util;
import org.melanee.ocl2.service.util.LetVariable;
import org.melanee.ocl2.service.util.OclInvalid;
import org.melanee.ocl2.service.util.Tuple;

public class DeepOCLClabjectWrapperImpl implements DeepOCLClabjectWrapper {
  private Element context;
  private Collection<Element> newContext;
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
  private String iteratorName;
  private Map iterationMap;
  private Element self;

  public DeepOCLClabjectWrapperImpl(Element context) {
    this.context = context;
    new ArrayList<>();
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
    this.iteratorName = null;
    this.iterationMap = new HashMap<>();
    this.self = context;
  }

  public DeepOCLClabjectWrapperImpl(Element context, Collection<Element> parameters) {
    this.context = context;
    new ArrayList<>();
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
    this.iteratorName = null;
    this.iterationMap = new HashMap<>();
    this.self = context;
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
    operationList.add("nonReflexiveClosure");
    operationList.add("substring");
    operationList.add("sortedBy");
    operationList.add("reverse");
    // deepOCL operations
    operationList.add("allInstances");
    operationList.add("allDeepInstances");
    operationList.add("getindirectInstances");
    operationList.add("doclGetDirectInstances");

    operationList.add("doclGetDirectOffspring");
    operationList.add("getDeepIndirectInstances");
    operationList.add("getDeepInstances");
    operationList.add("doclGetInstances");
    operationList.add("doclIsInstanceOf");
    operationList.add("doclIsDeepInstanceOf");
    operationList.add("doclIsDirectInstanceOf");
    operationList.add("doclIsDeepDirectInstanceOf");
    operationList.add("doclIsIndirectInstanceOf");
    operationList.add("doclIsDeepIndirectInstanceOf");
    operationList.add("doclIsDeepKindOf");
    operationList.add("doclIsDeepTypeOf");
    operationList.add("doclIsIsonymOf");
    operationList.add("doclIsHyponymOf");

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

  public void addLetVariable(LetVariable letVariable) {
    this.letVariables.add(letVariable);
  }

  public void setLetVariables(List<LetVariable> letVariables) {
    this.letVariables = letVariables;
  }

  public List<LetVariable> getLetVaribles() {
    return this.letVariables;
  }

  /**
   * resets the context to self.
   * 
   * @param context "self"
   */
  public void setContext(String context) {
    if (context.equals("self")) {
      this.newContext = new ArrayList<>(Arrays.asList(this.context));
    }
  }

  /**
   * Please look at the operation you want to invoke in order to create the exact Object array.
   * 
   * @param operation to invoke
   * @param arg arguments to invoke the operation with
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
    } else if (operation.equals("doclGetInstances")) {
      return doclGetInstances(arg);
    } else if (operation.equals("doclIsInstanceOf")) {
      return doclIsInstanceOf(arg);
    } else if (operation.equals("doclIsIndirectInstanceOf")) {
      return doclIsIndirectInstanceOf(arg);
    } else if (operation.equals("allInstances")) {
      return allInstances(arg);
    } else if (operation.equals("allDeepInstances")) {
      return allDeepInstances(arg);
    } else if (operation.equals("instanceOf")) {
      return instanceOf(arg);
    } else if (operation.equals("including")) {
      return including(arg);
    } else if (operation.equals("sortedBy")) {
      return sortedBy(arg);
    } else if (operation.equals("doclGetDirectInstances")) {
      return doclGetDirectInstances();
    } else if (operation.equals("doclGetDirectOffspring")) {
      return doclGetDirectOffspring();
    } else if (operation.equals("doclIsDeepKindOf")) {
      return doclIsDeepKindOf(arg);
    }
    return null;
  }

  private Object doclIsIndirectInstanceOf(Object[] arg) {
    Boolean result = false;
    try {
      Clabject argClabject = null;
      if (iteratorName.contains((String) arg[1])) {
        argClabject = (Clabject) this.context;
      }
      for (LetVariable let : this.letVariables) {
        if (let.getName().equals(arg[1])) {
          argClabject = (Clabject) let.getValue();
        }
      }
      if (arg[0] instanceof List) {
        List<?> argList = (List<?>) arg[0];
        if (argList.size() == 1 && argList.get(0) instanceof Clabject) {
          Clabject clabject = (Clabject) argList.get(0);
          for (Clabject subtype : argClabject.getSubtypes()) {
            result = subtype.getInstances().contains(clabject);
            if (result)
              return result;
          }
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
    return result;
  }

  private Object doclIsInstanceOf(Object[] arg) {
    Boolean result = false;
    try {
      Clabject argClabject = null;
      if (iteratorName != null && iteratorName.contains((String) arg[1])) {
        argClabject = (Clabject) this.context;
      } else if ("self".equals((String) arg[1])) {
        if (this.getSelf() instanceof Clabject) {
          argClabject = (Clabject) this.getSelf();
        }
      }
      for (LetVariable let : this.letVariables) {
        if (let.getName().equals(arg[1])) {
          argClabject = (Clabject) let.getValue();
        }
      }
      if (arg[0] instanceof List) {
        List<?> argList = (List<?>) arg[0];
        for (Object clb : argList) {
          if (clb instanceof Clabject) {
            List<Clabject> subtypes = new ArrayList<>();
            for (Clabject cl : argClabject.getSubtypes()) {
              subtypes.add(cl);
            }
            subtypes.add(argClabject);
            for (Clabject clab : subtypes) {
              Clabject clabject = (Clabject) clb;
              result = clab.getInstances().contains(clabject);
              if (result)
                return result;
            }
          }
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
    return result;
  }

  private Object collectNested(Object[] arg) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * navigation Operation. navigation has to be on the same level as the current context navigation,
   * if not this operation won't find any stuff.
   */
  @Override
  public Object navigate(String target) throws NavigationException {
    if (this.iterationMap.containsKey(target)) {
      return this.iterationMap.get(target);
    }
    List<Element> returnList = new ArrayList<>();
    Collection<Element> currentNavigation = this.navigationStack.peek().getSecond();
    for (Element element : currentNavigation) {
      if (target.equals(this.iteratorName)) {
        /*
         * self(); return getSelf();
         */
        this.navigationStack
            .push(new Tuple<String, Collection<Element>>(target, Arrays.asList(this.context)));
        return this.context;
      } else if (element instanceof Attribute && navigationStack.size() == 1) {
        throw new NavigationException("something wrong here.", new Throwable(target));
      }
      if (element instanceof Connection) {
        Connection connection = (Connection) element;
        for (ConnectionEnd connectionEnd : connection.getConnectionEnd()) {
          if (connectionEnd.getMoniker() != null && connectionEnd.getMoniker().equals(target)) {
            returnList.add(connectionEnd.getDestination());
          } else if (connectionEnd.getDestination().getName() != null
              && connectionEnd.getDestination().getName().equals(target)) {
            returnList.add(connectionEnd.getDestination());
          }
        }
        if (returnList.isEmpty()) {
          for (Attribute attribute : connection.getAllAttributes()) {
            if (attribute.getName().equals(target)) {
              returnList.add(attribute);
            }
          }
        }
        this.navigationStack.push(new Tuple<String, Collection<Element>>(target, returnList));
        return returnList;
      } else if (element instanceof Clabject) {
        if (target.equals("Clabject")) {
          Clabject clabject = (Clabject) element;
          List<Element> clabList = new ArrayList<Element>();
          for (Level level : clabject.getDeepModel().getContent()) {
            for (Clabject clab : level.getEntities()) {
              clabList.add(clab);
            }
          }
          this.navigationStack.push(new Tuple<String, Collection<Element>>("Clabjects", clabList));
          return clabList;
        } else if (target.equals("Connection")) {
          List<Element> connectionList = new ArrayList<Element>();
          for (Connection connection : ((Clabject) element).getLevel().getConnections()) {
            connectionList.add(connection);
          }
          this.navigationStack
              .push(new Tuple<String, Collection<Element>>("Connections", connectionList));
          return connectionList;
        }
        for (Feature feature : ((Clabject) element).getFeature()) {
          if (feature instanceof Attribute) {
            Attribute attr = (Attribute) feature;
            if (attr.getName().equals(target)) {
              returnList.add(attr);
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
                  Clabject ontoNav =
                      (Clabject) this.navigationStack.peek().getSecond().toArray()[0];
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
        if (this.navigationStack.peek().getFirst().equals(target)) {
          this.navigationStack.pop();
        }
        this.navigationStack.push(new Tuple<String, Collection<Element>>(target, returnList));
      } else if (element instanceof DeepModel) {
        if (target.equals("Clabject")) {
          DeepModel deepModel = (DeepModel) element;
          List<Element> clabList = new ArrayList<Element>();
          for (Level level : deepModel.getContent()) {
            clabList.addAll(level.getClabjects());
            Iterator<Element> iter = clabList.iterator();
            while (iter.hasNext()) {
              Element clab = iter.next();
              if (clab instanceof Connection || clab instanceof ConnectionEnd) {
                iter.remove();
              }
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
        } else if (target.equals("Feature")) {
          List<Element> featureList = new ArrayList<Element>();
          for (Level level : ((DeepModel) context).getContent()) {
            for (Clabject clabject : level.getClabjects()) {
              for (Feature feature : clabject.getFeature()) {
                featureList.add(feature);
              }
            }
          }
          this.navigationStack.push(new Tuple<String, Collection<Element>>("Feature", featureList));
          return featureList;
        }
      } else if (element instanceof Level) {
        Level level = (Level) element;
        if (target.equals("Clabject")) {
          List<Element> clabList = new ArrayList<Element>();
          clabList.addAll(level.getClabjects());
          this.navigationStack.push(new Tuple<String, Collection<Element>>("Clabjects", clabList));
        } else if (target.equals("Connection")) {
          List<Element> connectionList = new ArrayList<Element>();
          connectionList.addAll(level.getConnections());
          this.navigationStack
              .push(new Tuple<String, Collection<Element>>("Connections", connectionList));
          return connectionList;
        } else if (target.equals("Feature")) {
          List<Element> featureList = new ArrayList<Element>();
          for (Clabject clabject : level.getClabjects()) {
            for (Feature feature : clabject.getFeature()) {
              featureList.add(feature);
            }
          }
          this.navigationStack.push(new Tuple<String, Collection<Element>>("Feature", featureList));
          return featureList;
        }
      }
    }
    if (!this.letVariables.isEmpty() && returnList.isEmpty()) {
      for (LetVariable letVariable : this.letVariables) {
        if (letVariable.getName().equals(target)) {
          // necessary because second item of the tuple has to
          // be a collection of elements
          if (letVariable.getValue() instanceof Element) {
            returnList.add((Element) letVariable.getValue());
          } else {
            Attribute attr = PLMFactory.eINSTANCE.createAttribute();
            attr.setDatatype(letVariable.getDataType());
            attr.setValue(letVariable.getValue().toString());
            attr.setName(letVariable.getName());
            returnList.add(attr);
          }
          this.navigationStack.push(new Tuple<String, Collection<Element>>(target, returnList));
        }
      }
    }
    return returnList.size() == 0 ? null : returnList;
  }

  /**
   * if you want navigate up to a higher level, i.e. to the directType of the context your in use
   * this method (level cast).
   * 
   * @param target
   * @return null or result collection
   * @throws NavigationException d
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

  /**
   * get all direct instances of the context.
   * 
   * @return the direct instances as a collection
   */
  public Collection<Element> doclGetDirectInstances() {
    Collection<Element> resultList = new ArrayList<>();
    for (Element element : this.navigationStack.peek().getSecond()) {
      if (element instanceof Clabject) {
        Clabject clabject = (Clabject) element;
        resultList.addAll(clabject.getInstances());
      }
    }
    this.navigationStack
        .push(new Tuple<String, Collection<Element>>("directInstances", resultList));
    return resultList;
    /*
     * if (this.context instanceof Clabject) { Clabject clab = (Clabject) this.context;
     * 
     * @SuppressWarnings("rawtypes") List returnList = clab.getInstances(); this.navigationStack
     * .push(new Tuple<String, Collection<Element>>("directInstances", returnList)); return
     * returnList; }
     */
  }

  public Collection<Clabject> doclGetDirectOffspring() {
    if (this.context instanceof Clabject) {
      Clabject clab = (Clabject) this.context;
      @SuppressWarnings("rawtypes")
      List returnList = clab.getClassificationTreeAsType();
      this.navigationStack
          .push(new Tuple<String, Collection<Element>>("directOffspring", returnList));
      return returnList;
    }
    return null;
  }

  /**
   * implies functionality.
   * 
   * @param a implies b
   * @param b is implied by a
   * @return boolean value
   */
  public boolean implies(boolean a, boolean b) {
    if (a) {
      if (b) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }

  @Override
  public Boolean oclIsTypeOf(String target) {
    for (LetVariable let : this.letVariables) {
      if (let.getName().equals(target)) {
        target = let.getValue().toString();
      }
    }
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
      // I think the string comparison is more exact in this case than the
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

  /**
   * try to cast a clabject into other other clabject.
   * 
   * @param target Clabject to cast to.
   * @return casted clabject
   */
  public Object oclAsType(String target) {
    try {
      Class<?> clazz;
      if (target.equals("Clabject")) {
        clazz = Class.forName("org.melanee.core.models.plm.PLM.Entity");
      } else {
        clazz = Class.forName("org.melanee.core.models.plm.PLM." + target);
      }
      for (Element e : this.navigationStack.peek().getSecond()) {
        if (e.getClass().isInstance(clazz)) {
          return e.getClass().cast(clazz);
        }
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
   * "Deep" methods block.
   */

  /**
   * 
   * @param text
   * @return
   */
  public Boolean isDeepDirectInstanceOf(String text) {
    Clabject context = (Clabject) this.context;
    List<Clabject> resultList = new ArrayList<>();
    resultList = DeepOCL2Util.traverseClassifications(context, resultList);
    for (Clabject clab : resultList) {
      if (clab.getName().equals(text)) {
        return true;
      }
    }
    return false;
  }

  public Boolean doclIsDeepTypeOf(String text) {
    Boolean result = false;
    for (LetVariable let : this.letVariables) {
      if (let.getName().equals(text)) {
        text = let.getValue().toString();
      }
    }
    if (this.context instanceof Clabject) {
      Clabject context = (Clabject) this.context;
      if (context.getDirectType().getName().equals(text)) {
        result = true;
      }
      for (Clabject type : context.getClassificationTreeAsInstance()) {
        if (type.getName().equals(text)) {
          result = true;
        }
      }
    }
    return result;
  }

  /**
   * 
   * @param text
   * @return
   */
  public Boolean isDirectInstanceOf(String text) {
    Clabject context = (Clabject) this.context;
    for (Element type : context.getDirectTypes()) {
      if (type.getName().equals(text)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * @param text
   * @return
   */
  public Boolean isDeepInstanceOf(String text) {
    Clabject context = (Clabject) this.context;
    for (Element type : context.getClassificationTreeAsInstance()) {
      if (type.getName().equals(text)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * @param text
   * @return
   */
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
   * This function returns true if the clabject is ontological or through a combination of
   * ontological and subsumption relationship connected to the clabject passed in the parameter.
   * 
   * @param text Clabject.
   * @return true or false
   */
  public Object doclIsDeepKindOf(Object text) {
    Boolean result = false;
    try {
      Clabject clabject = (Clabject) this.navigationStack.peek().getSecond().toArray()[0];
      for (Element type : clabject.getDirectTypes()) {
        Clabject clab = (Clabject) type;
        // is there is no name of the clabject a NullPointer will be thrown. Like
        // connection without a name
        try {
          if (clab.getName().equals(text.toString())) {
            result = true;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (!result) {
        for (Element type : clabject.getDirectTypes()) {
          Clabject clab = (Clabject) type;
          for (Clabject supertype : clab.getSupertypes()) {
            if (supertype.getName().equals(text.toString())) {
              result = true;
            }
          }
        }
      }
      if (!result) {
        for (Element superType : clabject.getSupertypes()) {
          if (superType.getName().equals(text.toString())) {
            result = true;
          }
        }
      }
    } catch (ArrayIndexOutOfBoundsException | ClassCastException e) {
      // not castable to Clabject, which is necessary
      e.printStackTrace();
      return null;
    }
    return result;
  }



  public Object doclIsIsonymOf(String text) {
    Boolean result = false;
    Clabject argClabject = null;

    try {
      if (text.equals(iteratorName)) {
        argClabject = (Clabject) this.context;
      }
      Clabject clabject = (Clabject) this.navigationStack.peek().getSecond().toArray()[0];
      if (clabject.getAllFeatures().size() == 0 || clabject.getDirectTypes().isEmpty()) {
        return false;
      }
      HashMap<String, Feature> featureMapType = new HashMap<>();
      for (Feature feature : clabject.getAllFeatures()) {
        if (feature.getDurability() > 0) {
          featureMapType.put(feature.getName(), feature);
        }
      }
      for (Feature feature : argClabject.getAllFeatures()) {
        if (featureMapType.containsKey(feature.getName())) {
          Feature featureType = featureMapType.get(feature.getName());
          if (feature.getDurability() == featureType.getDurability()) {
            result = true;
          }
        }
      }
    } catch (ClassCastException e) {
      return new OclInvalid();
    }
    return result;
  }

  public Object doclIsHyponymOf(String text) {
    Boolean result = false;
    Clabject argClabject = null;
    try {
      if (text.equals(iteratorName)) {
        argClabject = (Clabject) this.context;
      }
      Clabject clabject = (Clabject) this.navigationStack.peek().getSecond().toArray()[0];
      if (clabject.getAllFeatures().size() == 0 || clabject.getDirectTypes().isEmpty()) {
        return false;
      }
      HashMap<String, Feature> featureMapType = new HashMap<>();
      for (Feature feature : clabject.getAllFeatures()) {
        if (feature.getDurability() > 0) {
          featureMapType.put(feature.getName(), feature);
        }
      }
      Boolean foundAll = true;
      for (Entry<String, Feature> entry : featureMapType.entrySet()) {
        Feature value = entry.getValue();
        String key = entry.getKey();
        if (!(argClabject != null && argClabject.getFeatureForName(key) != null)) {
          foundAll = false;
        }
      }
      result = foundAll;
    } catch (ClassCastException e) {
      return new OclInvalid();
    }
    return result;
  }

  /**
   * 
   * @param arg
   * @return
   */
  private Boolean instanceOf(Object[] arg) {
    return ((Clabject) this.context).isInstanceOf((Clabject) arg[0]);
  }

  /**
   * arg[0] has to the collection to insert at arg[1] has to the object to insert into the
   * collection arg[2] has to the index.
   * 
   * @param arg Object[]
   * @return
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  private Collection<?> insertAt(Object[] arg) {
    Collection collection = (Collection<?>) arg[0];
    Object object = arg[1];
    int index = (Integer) arg[2];
    return CollectionUtil.insertAt(collection, index, object);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private Collection<?> symmetricDifference(Object[] arg) {
    return CollectionUtil.symmetricDifference((Set) arg[0], (Set) arg[1]);
  }

  // TODO make union work with resolvable collection expressions
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
   * makes a set out of a collection.
   * 
   * @param arg collection to make a set from.
   * @return collection as Set
   */
  private Collection<?> asSet(Object[] arg) {
    return CollectionUtil.asSet((Collection<?>) arg[0]);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private Collection<?> append(Object[] arg) {
    return CollectionUtil.append((Collection) arg[0], arg[1]);

  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private Collection<?> prepend(Object[] arg) {
    return CollectionUtil.prepend((Collection) arg[0], arg[1]);
  }

  /**
   * collection intersection.
   * 
   * @param collection to intersect with
   * @param collection1 to intersect with
   * @return the intersected collection.
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  private Collection<?> intersection(Object[] arg) {
    return CollectionUtil.intersection((Collection) arg[0], (Collection) arg[1]);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private Collection<?> including(Object[] arg) {
    return CollectionUtil.including((Collection) arg[0], arg[1]);
  }

  private Boolean includes(Object[] arg) {
    Collection<?> arg0 = (Collection<?>) arg[0];
    List list = new ArrayList<>();
    if (arg[1] instanceof Attribute) {
      arg[1] = ((Attribute) arg[1]).getValue();
    }
    for (Object o : arg0) {
      if (o instanceof String) {
        String entry = o.toString().replaceAll("^\"|\"$", "");
        list.add(entry);
      }
    }
    if (list.isEmpty()) {
      try {
        return CollectionUtil.includes((Collection<?>) arg[0], arg[1]);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return CollectionUtil.includes((Collection<?>) list, arg[1]);
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
    if (arg[1].equals("self")) {
      Collection<?> intermediate = CollectionUtil.excluding((Collection<?>) arg[0], this.context);
      Collection<Element> returnCollection = (Collection<Element>) intermediate;
      this.navigationStack
          .push(new Tuple<String, Collection<Element>>("excluding", returnCollection));
      return returnCollection;
    }
    return CollectionUtil.excluding((Collection<?>) arg[0], arg[1]);
  }

  private Boolean excludes(Object[] arg) {
    return CollectionUtil.excludes((Collection<?>) arg[0], arg[1]);
  }

  private Integer size(Object[] arg) {
    return ((Collection<?>) arg[0]).size();
  }

  private Number sum(Object[] arg) {
    List<Double> sumList = new ArrayList<>();
    if (arg[0] instanceof Collection) {
      for (Object object : (Collection) arg[0]) {
        if (object instanceof Attribute) {
          Double sumElement = Double.parseDouble(((Attribute) object).getValue());
          sumList.add(sumElement);
        }
      }
    }
    return sumList == null || sumList.isEmpty()
        ? (Number) CollectionUtil.sum((Collection<?>) arg[0])
        : (Number) CollectionUtil.sum(sumList);

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
  @SuppressWarnings("unchecked")
  private Collection<?> sortedBy(Object[] arg) {
    List<Element> resultCollection = new ArrayList<>();
    List<Attribute> tempCollection = new ArrayList<>();
    String dataType = null;
    if (arg[0] instanceof Collection) {
      Collection<Element> sortCollection = (Collection<Element>) arg[0];
      for (Element element : sortCollection) {
        if (element instanceof Clabject) {
          Clabject clabject = (Clabject) element;
          for (Attribute feature : clabject.getAllAttributes()) {
            if (feature.getName().equals(arg[1])) {
              tempCollection.add(feature);
              dataType = feature.getDatatype();
            }
          }
        }
      }
    } else {
      return null;
    }
    if (tempCollection.size() > 0) {
      tempCollection = sort(tempCollection, dataType);
      for (Attribute attr : tempCollection) {
        resultCollection.add(attr.getClabject());
      }
    }
    this.navigationStack.push(new Tuple<String, Collection<Element>>("sortedBy", resultCollection));
    return resultCollection;
  }

  /**
   * this sorts an array of Attributes.
   * 
   * @param collectionToSort Collection that will be sorted from lowest to highest
   * @param dataType data type of the attributes.
   * @return returns a sorted list of attributes
   */
  private List<Attribute> sort(List<Attribute> collectionToSort, String dataType) {
    try {
      if (dataType.equals("Integer") || dataType.equals("Real") || dataType.equals("Natural")) {
        Comparator<Attribute> comp = (Attribute a, Attribute b) -> {
          Double c = Double.parseDouble(b.getValue());
          Double d = Double.parseDouble(a.getValue());
          return d.compareTo(c);
        };
        Collections.sort(collectionToSort, comp);
      } else if (dataType.equals("String")) {
        Comparator<Attribute> comp = (Attribute a, Attribute b) -> {
          return a.getValue().compareTo(b.getValue());
        };
        Collections.sort(collectionToSort, comp);
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return collectionToSort;
  }

  /**
   * return the inverse collection compared to the select operation returns all element for which
   * the expression is false as a collection.
   * 
   * @param collection argument collection
   * @param expression expression to work on the collection of arguments
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
   * arg[0] has to a collection and arg[1] has to be an expression.
   * 
   * @param collection collection to chose one element from.
   * @param expression no expression needed here.
   * @return returns one element from the passed collection.
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
        if (returnCollection.size() > 1) {
          return false;
        }
      }
    }
    return true;
  }

  private Object doclGetInstances(Object[] arg) {
    List<Element> returnCollection = new ArrayList<>();
    for (Element element : this.navigationStack.peek().getSecond()) {
      if (element instanceof Clabject) {
        List<Clabject> tempCollection = new ArrayList<>();
        tempCollection.addAll(((Clabject) element).getInstances());
        for (Clabject subTypes : ((Clabject) element).getSubtypes()) {
          tempCollection.addAll(subTypes.getInstances());
        }
        for (Clabject c : tempCollection) {
          returnCollection.add(c);
        }
      } else {
        return new OclInvalid();
      }
    }
    this.navigationStack.push(new Tuple<String, Collection<Element>>("doclGetInstances",
        (Collection<Element>) returnCollection));
    return returnCollection;
  }

  private Object allInstances(Object[] arg) {
    if (this.navigationStack.peek().getSecond().size() == 1) {
      List<Element> returnCollection = new ArrayList<>();
      if (this.navigationStack.peek().getSecond().toArray()[0] instanceof Clabject) {
        List<Clabject> tempCollection =
            ((Clabject) this.navigationStack.peek().getSecond().toArray()[0]).getInstances();
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

  private Object allDeepInstances(Object[] arg) {
    if (this.navigationStack.peek().getSecond().size() == 1) {
      List<Element> returnCollection = new ArrayList<>();
      if (this.navigationStack.peek().getSecond().toArray()[0] instanceof Clabject) {
        Collection<Clabject> tempCollection =
            ((Clabject) this.navigationStack.peek().getSecond().toArray()[0])
                .getClassificationTreeAsType();
        for (Clabject c : tempCollection) {
          returnCollection.add(c);
        }
        this.navigationStack.push(new Tuple<String, Collection<Element>>("allDeepInstances",
            (Collection<Element>) returnCollection));
        return returnCollection;
      } else {
        return new OclInvalid();
      }
    } else {
      return new OclInvalid();
    }
  }

  /**
   * This function evaluates OCL statements. It puts the necessary information into a HashMap and
   * passes it on to the castAndCompare function.
   * 
   * @param right side of the equation.
   * @param operator indicates how the left and right side a related to each other.
   * @return returns a boolean value.
   * @throws InterpreterException if something went wrong this exception will be thrown.
   */
  public Boolean eval(Attribute left, String right, String operator) throws InterpreterException {
    Map<String, String> expressionMap = new HashMap<>();
    expressionMap.put("right", right);
    expressionMap.put("operator", operator);
    return castAndCompare(left, expressionMap);
  }



  /**
   * This function evaluates OCL statements. It puts the necessary information into a HashMap and
   * passes it on to the castAndCompare function.
   * 
   * @param right side of the equation.
   * @param operator indicates how the left and right side a related to each other.
   * @return returns a boolean value.
   * @throws InterpreterException if something went wrong this exception will be thrown.
   */
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

  /**
   * If the right side is not a String an instead an object it is most likely a collection that
   * wraps an attribute (PLM). This is unpacked and then passed into the other eval function.
   * 
   * @param right is here most likely a collection with an attribute.
   * @param operator to compare left and right
   * @return a boolean value
   * @throws InterpreterException is thrown when the Object is not a collection with an attribute.
   */
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
        throw new InterpreterException("Object is not a collection with an attribute");
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
    if (left instanceof Inheritance && right instanceof Inheritance) {
      if (operator.equals("=")) {
        return left.equals(right);
      } else if (operator.equals("<>")) {
        return left != right;
      }
    }
    if (left instanceof Integer && right instanceof Attribute) {
      Attribute attributeRight = (Attribute) right;
      Integer integerLeft = (Integer) left;
      Attribute attributeLeft = PLMFactory.eINSTANCE.createAttribute();
      attributeLeft.setDatatype("Integer");
      attributeLeft.setValue(integerLeft.toString());
      Map<String, String> expressionMap = new HashMap<>();
      expressionMap.put("right", attributeRight.getValue());
      expressionMap.put("operator", operator);
      return castAndCompare(attributeLeft, expressionMap);
    } else if (left instanceof Integer && right instanceof Double) {
      Double doubleRight = (Double) right;
      Double integerLeft = Double.parseDouble(left.toString());
      Attribute attributeLeft = PLMFactory.eINSTANCE.createAttribute();
      attributeLeft.setDatatype("Real");
      attributeLeft.setValue(integerLeft.toString());
      Attribute attributeRight = PLMFactory.eINSTANCE.createAttribute();
      attributeRight.setDatatype("Real");
      attributeRight.setValue(doubleRight.toString());
      Map<String, String> expressionMap = new HashMap<>();
      expressionMap.put("right", attributeRight.getValue());
      expressionMap.put("operator", operator);
      return castAndCompare(attributeLeft, expressionMap);
    } else if (left instanceof Integer && right instanceof Integer) {
      Integer integerRight = (Integer) right;
      Integer integerLeft = (Integer) left;
      Attribute attributeLeft = PLMFactory.eINSTANCE.createAttribute();
      attributeLeft.setDatatype("Integer");
      attributeLeft.setValue(integerLeft.toString());
      Attribute attributeRight = PLMFactory.eINSTANCE.createAttribute();
      attributeRight.setDatatype("Integer");
      attributeRight.setValue(integerRight.toString());
      Map<String, String> expressionMap = new HashMap<>();
      expressionMap.put("right", attributeRight.getValue());
      expressionMap.put("operator", operator);
      return castAndCompare(attributeLeft, expressionMap);
    } else if (right instanceof Integer && left instanceof Attribute) {
      Integer integerRight = (Integer) right;
      Attribute attributeLeft = (Attribute) left;
      Attribute attributeRight = PLMFactory.eINSTANCE.createAttribute();
      attributeRight.setDatatype("Integer");
      attributeRight.setValue(integerRight.toString());
      Map<String, String> expressionMap = new HashMap<>();
      expressionMap.put("right", attributeRight.getValue());
      expressionMap.put("operator", operator);
      return castAndCompare(attributeLeft, expressionMap);
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
    } else if (right instanceof Boolean && left instanceof Boolean) {
      return left.equals(right);
    } else if (right instanceof String && left instanceof Attribute) {
      this.navigationStack.push(new Tuple<String, Collection<Element>>(((Attribute) left).getName(),
          Arrays.asList(((Attribute) left))));
      return eval((String) right, operator);
    } else if (right instanceof String) {
      return eval((String) right, operator);
    } else if (right instanceof Number) {
      return eval(right.toString(), operator);
    } else if (right instanceof Clabject && left instanceof Clabject) {
      if (operator.equals("=")) {
        return left.equals(right);
      } else if (operator.equals("<>")) {
        return left != right;
      } else {
        throw new InterpreterException("which operator do you want?");
      }
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
   * arg[0] has to be a collection and arg[1] has to be the expression.
   * 
   * @param collection collection that is checked if some property is unique.
   * @param expression has to refer to some property in the elements in the collection.
   * @return a boolean value.
   */
  private Boolean isUnique(Object[] arg) {
    Set set = new HashSet<>();
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject clabject = (Clabject) it.next();
      if (!set.add(
          ((Attribute) clabject.getFeatureForName(expressionMap.get("attribute"))).getValue())) {
        return false;
      }
    }
    return true;
  }

  /**
   * arg[0] has to be a collection and arg[1] has to be an expression.
   * 
   * @param collection collection to check
   * @param expression expression to check the collection for
   * @return
   */
  private Boolean exists(Object[] arg) {
    Collection<?> collection = (Collection<?>) arg[0];
    Map<String, String> expressionMap = splitExpression((String) arg[1]);
    Iterator<?> it = collection.iterator();
    while (it.hasNext()) {
      Clabject c = (Clabject) it.next();
      Attribute a = (Attribute) c.getFeatureForName(expressionMap.get("left"));
      if (castAndCompare(a, expressionMap)) {
        return true;
      }
    }
    return false;
  }

  /**
   * arg[0] = collection<?> ; arg[1] = expression : String.
   * 
   * @return one element from the collection
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
          } else if (method.getReturnType().toString()
              .equals("interface org.eclipse.emf.common.util.EList")) {
            result = method.invoke(clab, null);
            this.navigationStack
                .push(new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
            return result;
          } else {
            break primitiveType;
          }
          attr.setValue(method.invoke(clab, null).toString());
          this.navigationStack
              .push(new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) attr)));
          return method.invoke(clab, null);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException e1) {
          e1.printStackTrace();
        }
        oldLoop: for (EOperation operation : clab.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) {
                return new OclInvalid();
              }
              index++;
            }
            try {
              if (params.length == 1) {
                for (LetVariable let : this.letVariables) {
                  if (let.getName().equals(params[0])) {
                    params[0] = let.getValue().toString();
                  }
                }
              }
              for (String parameter : params) {
                argList.add(parameter);
              }
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
              if (!parameter.getName().equals(argList.get(index))) {
                return new OclInvalid();
              }
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
      } else if (e instanceof Inheritance) {
        Inheritance inheritance = (Inheritance) e;
        Method method;
        primitiveType: try {
          method = inheritance.getClass().getMethod(text, null);
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
          } else if (method.getReturnType().toString()
              .equals("interface org.eclipse.emf.common.util.EList")) {
            result = method.invoke(inheritance, null);
            this.navigationStack
                .push(new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
            return result;
          } else {
            break primitiveType;
          }
          attr.setValue(method.invoke(inheritance, null).toString());
          this.navigationStack
              .push(new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) attr)));
          return method.invoke(inheritance, null);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException e1) {
          e1.printStackTrace();
        }
        for (EOperation operation : inheritance.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) {
                return new OclInvalid();
              }
              index++;
            }
            try {
              result = ((Inheritance) e).eInvoke(operation, argList);
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
        Attribute attribute = (Attribute) e;
        Method method;
        primitiveType: try {
          method = attribute.getClass().getMethod(text, null);
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
          attr.setValue(method.invoke(attribute).toString());
          this.navigationStack
              .push(new Tuple<String, Collection<Element>>(text, Arrays.asList((Element) attr)));
          return Arrays.asList((Element) attr);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException e1) {
          e1.printStackTrace();
        }
        oldLoop: for (EOperation operation : e.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) {
                return new OclInvalid();
              }
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
      } else if (e instanceof ConnectionEnd) {
        for (EOperation operation : e.eClass().getEAllOperations()) {
          if (operation.getName().equals(text)) {
            EList parameterTypes = operation.getETypeParameters();
            int index = 0;
            for (ETypeParameter parameter : operation.getETypeParameters()) {
              if (!parameter.getName().equals(argList.get(index))) {
                return new OclInvalid();
              }
              index++;
            }
            try {
              result = ((ConnectionEnd) e).eInvoke(operation, argList);
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
        for (EStructuralFeature feature : e.eClass().getEAllStructuralFeatures()) {
          if (feature.getName().equals(text)) {
            Object res = e.eGet(feature);
            try {
              if (result == null) {
                result = e.eGet(feature);
                if (result instanceof Collection<?>) {
                  result = (Collection<Element>) result;
                  this.navigationStack.push(
                      new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                } else if (result instanceof Element) {
                  result = Arrays.asList((Element) result);
                  this.navigationStack.push(
                      new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                } else if (result instanceof Integer) {
                  Attribute attr = PLMFactory.eINSTANCE.createAttribute();
                  attr.setDatatype("Integer");
                  attr.setValue(result.toString());
                  result = Arrays.asList(attr);
                  this.navigationStack.push(
                      new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                }
              } else {
                if (res instanceof Collection && result instanceof Collection) {
                  ((List<Element>) result).addAll((Collection<Element>) res);
                  this.navigationStack.push(
                      new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                } else if (res instanceof Element) {
                  ArrayList<Element> list = new ArrayList<>((Collection<Element>) result);
                  list.add((Element) res);
                  result = list;
                  this.navigationStack.push(
                      new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                } else if (res instanceof Integer) {
                  Attribute attr = PLMFactory.eINSTANCE.createAttribute();
                  attr.setDatatype("Integer");
                  attr.setValue(result.toString());
                  ((List<Element>) result).add(attr);
                  this.navigationStack.push(
                      new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
                }
              }

            } catch (Exception exception) {
              exception.printStackTrace();
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
              if (!parameter.getName().equals(argList.get(index))) {
                return new OclInvalid();
              }
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
              if (!parameter.getName().equals(argList.get(index))) {
                return new OclInvalid();
              }
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
              } else if (result instanceof Integer) {
                Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
                attribute.setDatatype("Integer");
                attribute.setValue(result.toString());
                this.navigationStack
                    .push(new Tuple<String, Collection<Element>>(text, Arrays.asList(attribute)));
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
                    new Tuple<String, Collection<Element>>(text, (Collection<Element>) result));
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

  // helper operation section

  /**
   * maybe more types are needed, these are enough for the moment. Input values are the clabject's
   * attribute and the expression which the value of the attribute is compared with. The first value
   * of the returnCollection is the casted attribute value and the second value is the properly
   * casted expression which the attribute is compared with
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
      if (a.getDatatype() == null) {
        if (a.getValue() == "true" || a.getValue() == "false") {
          a.setDatatype("Boolean");
        } else {
          try {
            Integer.parseInt(a.getValue());
            a.setDatatype("Ineteger");
          } catch (NumberFormatException e) {

          }
          try {
            Double.parseDouble(a.getValue());
            a.setDatatype("Real");
          } catch (NumberFormatException e) {

          }
          a.setDatatype("String");
        }
      }
      if (a.getDatatype().equals("Integer")) {
        int first = Integer.parseInt(a.getValue());
        String intermediate = expressionMap.get("right").replaceAll("\"", "");
        Double d = Double.parseDouble(intermediate);
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
    } catch (NullPointerException | NumberFormatException | ClassCastException e) {
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
   * splits the expression if the string contains a special character, like = >= <= and so on.
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
    } else if (!DeepOCL2Util.isWord(expression)) {
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
    } else {
      map.put("attribute", expression);
      return map;
    }

  }

  /**
   * compares integer values and return true or false depending on the operator
   * 
   * @param first
   * @param second
   * @param operation
   * @return
   */
  private Boolean compare(int first, int second, String operation) {
    switch (operation) {
      case "=":
        return first == second;
      case "<=":
        return first <= second;
      case ">=":
        return first >= second;
      case "<>":
        return first != second;
      case "<":
        return first < second;
      case ">":
        return first > second;
      default:
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
  private Boolean compare(double first, double second, String operation) {
    switch ((operation)) {
      case "=":
        return first == second;
      case "<=":
        return first <= second;
      case ">=":
        return first >= second;
      case "<>":
        return first != second;
      case "<":
        return first < second;
      case ">":
        return first > second;
      default:
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

  /**
   * resets the context to the original context.
   */
  public void self() {
    this.navigationStack.clear();
    /*
     * if (this.context instanceof Feature) { Feature feature = (Feature) this.context; this.context
     * = feature.getClabject(); }
     */

    this.navigationStack.push(
        new Tuple<String, Collection<Element>>(this.self.getName(), Arrays.asList(getSelf())));
  }

  /**
   * when doing loop collection operation a new instance of this class, with a context parameter is
   * created for each iterator variable and we need a method to navigate to the old context. that is
   * why we need this method.
   * 
   * @param context2 is the clabject that is passed from the loop operation
   */
  public void self(Clabject context2) {
    this.navigationStack
        .push(new Tuple<String, Collection<Element>>(context2.getName(), Arrays.asList(context2)));
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
   * adds a Tuple to let Variable list. First element is the name of the variable and the second
   * element in the tuple is the actual variable
   * 
   * @param letName
   * @param letType
   * @param value
   * @throws InterpreterException
   */
  public void createLetVariable(String letName, String letType, Object value)
      throws InterpreterException {
    LetVariable letVariable = new LetVariable(letName, letType, value, this.context);
    this.letVariables.add(letVariable);
  }

  /**
   * assign a value to a let variable.
   * 
   * @param value to assign the let variable with
   * @return
   */
  public Object assign(Object left, String value) {
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

  /**
   * checks if the operation is in the operations list.
   * 
   * @param text String with the name of the operation
   * @return returns true if the operation is in the list and false if not.
   */
  public boolean operationExist(String text) {
    if (this.operationList.contains(text)) {
      return true;
    }
    return false;
  }

  /**
   * checks if a certain method of the clabject in the current context is present.
   * 
   * @param operation name
   * @return true or false
   */
  public boolean isOperationOnClabject(String operation) {
    boolean result = false;
    try {
      for (Element element : this.navigationStack.peek().getSecond()) {
        if (element instanceof Clabject) {
          for (Feature method : ((Clabject) element).getDefinedMethods()) {
            if (method.getName().contains("(")) {
              if (!method.getName().substring(0, method.getName().indexOf("(")).equals(operation)) {
                return false;
              } else {
                result = true;
              }
            } else if (!method.getName().equals(operation)) {
              return false;
            } else {
              result = true;
            }
          }
        }
      }
    } catch (Exception e) {
      result = false;
    }

    return result;
  }

  /**
   * executes an operation/method on the clabject.
   * 
   * @param text method name
   * @param args arguments passed to the method
   * @return result of the method execution
   */
  public Object executeClabjectOperation(String text, Object[] args) {
    Object result = null;
    boolean preConstraint = true;
    for (Element element : this.navigationStack.peek().getSecond()) {
      if (element instanceof Clabject) {
        for (Feature feature : ((Clabject) element).getDefinedMethods()) {
          if (feature.getName().contains("(")) {
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
          } else if (feature.getName().equals(text)) {
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

  public void setIteratorName(String iteratorName) {
    this.iteratorName = iteratorName;
  }

  public String getIteratorName() {
    return this.iteratorName;
  }

  public void addIterationMap(String key, Object value) {
    this.iterationMap.put(key, value);
  }

  public void clearIterationMap() {
    this.iterationMap.clear();
  }

  public Object oclIsUndefined() {
    Collection<Element> elementColletion = this.navigationStack.peek().getSecond();
    if (elementColletion.size() == 1) {
      Element element = (Element) elementColletion.toArray()[0];
      if (element instanceof Attribute) {
        Attribute attribute = (Attribute) element;
        if (attribute.getValue() == "" || attribute.getValue() == null) {
          return true;
        }
      }
    }
    return false;
  }

  public Object assign(Object left, Attribute rightAttribute) {
    if (left instanceof Attribute) {
      Attribute leftAttribute = (Attribute) left;
      leftAttribute.setValue(rightAttribute.getValue());
      return rightAttribute.getValue();
    }
    return null;
  }

  public Element getSelf() {
    return this.self;
  }

  public void setSelf(Element self) {
    this.self = self;

  }


}
