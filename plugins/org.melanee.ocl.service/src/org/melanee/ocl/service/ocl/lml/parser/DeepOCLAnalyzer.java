/*******************************************************************************
 * Copyright (c) 2014 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dominik Kantner - initial API and implementation and initial documentation
 *******************************************************************************/
package org.melanee.ocl.service.ocl.lml.parser;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.cst.IfExpCS;
import org.eclipse.ocl.cst.OperationCallExpCS;
import org.eclipse.ocl.cst.SimpleNameCS;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.PrimitiveType;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.expressions.IfExp;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.OperationCallExp;
import org.eclipse.ocl.internal.l10n.OCLMessages;
import org.eclipse.ocl.parser.AbstractOCLParser;
import org.eclipse.ocl.utilities.PredefinedType;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.ocl.service.ocl.lml.CollectionType;
import org.melanee.ocl.service.ocl.lml.Constraint;
import org.melanee.ocl.service.ocl.lml.LevelCastAttribute;
import org.melanee.ocl.service.ocl.lml.NavigationAttribute;
import org.melanee.ocl.service.ocl.lml.PropertyCallExp;
import org.melanee.ocl.service.ocl.lml.SetType;
import org.melanee.ocl.service.ocl.lml.TypeExp;
import org.melanee.ocl.service.ocl.lml.internal.DeepOCLFactoryImpl;
import org.melanee.ocl.service.ocl.lml.util.DeepOCLStandardLibraryUtil;

/**
 * OCL Analyzer adapted to deep model
 * 
 * @author Dominik Kantner
 *
 */
@SuppressWarnings("restriction")
public class DeepOCLAnalyzer extends
    org.eclipse.ocl.parser.OCLAnalyzer<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> {
  public DeepOCLAnalyzer(AbstractOCLParser parser) {
    super(parser);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.parser.AbstractOCLAnalyzer#simplePropertyName(org.eclipse
   * .ocl.cst.SimpleNameCS, org.eclipse.ocl.Environment,
   * org.eclipse.ocl.expressions.OCLExpression, java.lang.Object,
   * java.lang.String)
   */
  @Override
  protected org.eclipse.ocl.expressions.PropertyCallExp<EObject, EObject> simplePropertyName(
      SimpleNameCS simpleNameCS,
      Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> env,
      OCLExpression<EObject> source, EObject owner, String simpleName) {
    // TODO Auto-generated method stub
    // workaround: add dollar at beginning of simpleName if it end with $,
    // $dollar at beginning was removed by lexer/parser of OCLAnalyzer
    if (simpleName.length() > 0 && simpleName.charAt(simpleName.length() - 1) == '$') {
      simpleName = "$" + simpleName;
    }
    PropertyCallExp propCallExp = (PropertyCallExp) super.simplePropertyName(simpleNameCS, env,
        source, owner, simpleName);
    if (propCallExp != null) {
      if (propCallExp.getReferredProperty() instanceof NavigationAttribute) {

        NavigationAttribute navAttribute = (NavigationAttribute) propCallExp.getReferredProperty();
        Clabject collType = navAttribute.getNavigationValue();
        OCLExpression<EObject> expr = propCallExp.getSource();
        if (expr instanceof PropertyCallExp) {
          PropertyCallExp pc = (PropertyCallExp) expr;
          // it's a bag if the previous property was of type
          // collection, except the refered property is a
          // NavigationAttribute with navigation Value to Connection
          // ->then its a set
          if (pc.getType() instanceof CollectionType) {
            if (pc.getReferredProperty() instanceof NavigationAttribute
                && (((NavigationAttribute) pc.getReferredProperty())
                    .getNavigationValue()) instanceof Connection) {
              org.eclipse.ocl.types.SetType<Clabject, Object> set = DeepOCLFactoryImpl.INSTANCE
                  .createSetType(collType);
              propCallExp.setType(set);
            } else {
              org.eclipse.ocl.types.BagType<Clabject, Object> bag = DeepOCLFactoryImpl.INSTANCE
                  .createBagType(collType);
              propCallExp.setType(bag);
            }
          } else if (pc.getReferredProperty() instanceof LevelCastAttribute) {
            // if there was a level cast , it is a single element if
            // issingle is true, otherwise a bag
            if (navAttribute.isSingle()) {
              propCallExp.setType(collType);
            } else {
              org.eclipse.ocl.types.BagType<Clabject, Object> bag = DeepOCLFactoryImpl.INSTANCE
                  .createBagType(collType);
              propCallExp.setType(bag);
            }
          } else {
            // if there was no level cast , it is a single
            // element if issingle is true, otherwise a set
            if (navAttribute.isSingle()) {
              propCallExp.setType(collType);
            } else {
              org.eclipse.ocl.types.SetType<Clabject, Object> set = DeepOCLFactoryImpl.INSTANCE
                  .createSetType(collType);
              propCallExp.setType(set);
            }
          }
        } else {
          if (expr.getType() instanceof CollectionType) {
            // it's a bag if the previous expr was of type
            // collection
            org.eclipse.ocl.types.BagType<Clabject, Object> bag = DeepOCLFactoryImpl.INSTANCE
                .createBagType(collType);
            propCallExp.setType(bag);
          } else {
            if (navAttribute.isSingle()) {
              propCallExp.setType(collType);
            } else {
              org.eclipse.ocl.types.SetType<Clabject, Object> set = DeepOCLFactoryImpl.INSTANCE
                  .createSetType(collType);
              propCallExp.setType(set);
            }
          }
        }
      }
    }
    return propCallExp;
  }

  /**
   * @param rootEnvironment
   * @param input
   */
  public DeepOCLAnalyzer(
      Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> rootEnvironment,
      String input) {
    super(rootEnvironment, input);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.parser.AbstractOCLAnalyzer#genOperationCallExp(org.
   * eclipse.ocl.Environment, org.eclipse.ocl.cst.OperationCallExpCS,
   * java.lang.String, java.lang.String,
   * org.eclipse.ocl.expressions.OCLExpression, java.lang.Object, java.util.List)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected OperationCallExp genOperationCallExp(
      Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> env,
      OperationCallExpCS operationCallExpCS, String rule, String operName,
      OCLExpression<EObject> source, EObject ownerType, List<OCLExpression<EObject>> args) {
    for (int i = 0; i < args.size(); i++) {
      if ((ownerType instanceof EClass) && (args.get(i) instanceof Clabject)) {
        ERROR(operationCallExpCS, rule, OCLMessages.BadArg_ERROR_);
      }

      if ((ownerType instanceof Clabject) && (args.get(i) instanceof EClass)) {
        ERROR(operationCallExpCS, rule, OCLMessages.BadArg_ERROR_);
      }

    }

    OperationCallExp operationCallExp = super.genOperationCallExp(env, operationCallExpCS, rule,
        operName, source, ownerType, args);

    if (operationCallExp.getReferredOperation() instanceof EOperation) {
      if (((EOperation) (operationCallExp.getReferredOperation()))
          .getName() == PredefinedType.ALL_INSTANCES_NAME) {
        if (operationCallExp.getType() instanceof SetType) {
          if (operationCallExp.getSource() instanceof TypeExp) {
            ((SetType) operationCallExp.getType())
                .setElementType(((TypeExp) operationCallExp.getSource()).getReferredType());
            return operationCallExp;
          }
        }
      }
      if (((EOperation) (operationCallExp.getReferredOperation()))
          .getName() == DeepOCLStandardLibraryUtil.OCL_AS_DEEP_TYPE_NAME) {
        if (args.size() == 1 && args.get(0) instanceof TypeExp
            && ((TypeExp) args.get(0)).getReferredType() instanceof Clabject) {
          operationCallExp.setType(((TypeExp) args.get(0)).getReferredType());
        }
      }
    }

    if (operationCallExp.getType() instanceof CollectionType
        && operationCallExp.getSource().getType() instanceof CollectionType) {
      ((CollectionType) operationCallExp.getType()).setElementType(
          ((CollectionType) operationCallExp.getSource().getType()).getElementType());
      return operationCallExp;
    }

    return operationCallExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.parser.AbstractOCLAnalyzer#ifExpCS(org.eclipse.ocl.cst.
   * IfExpCS, org.eclipse.ocl.Environment)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected IfExp ifExpCS(IfExpCS ifExpCS,
      Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> env) {

    OCLExpression condition = oclExpressionCS(ifExpCS.getCondition(), env);

    OCLExpression thenExpression = oclExpressionCS(ifExpCS.getThenExpression(), env);
    OCLExpression elseExpression = oclExpressionCS(ifExpCS.getElseExpression(), env);

    TRACE("ifExpCS", " "); //$NON-NLS-2$//$NON-NLS-1$

    IfExp astNode = oclFactory.createIfExp();

    if (isErrorNode(condition)) {
      // don't validate the condition type
    } else if (condition.getType() != getBoolean() && !(condition.getType() instanceof PrimitiveType
        && ((PrimitiveType) condition.getType()).getName() == "Boolean")) {
      ERROR(ifExpCS.getCondition(), "ifExpCS", OCLMessages.bind( //$NON-NLS-1$
          OCLMessages.BooleanForIf_ERROR_, computeInputString(ifExpCS.getCondition())));
    }

    initASTMapping(env, astNode, ifExpCS);
    astNode.setCondition(condition);
    astNode.setThenExpression(thenExpression);
    astNode.setElseExpression(elseExpression);

    if ((thenExpression != null) && (elseExpression != null)) {

      astNode.setType(thenExpression.getType()); // other than in
      // superclass

      if (isErrorNode(thenExpression)) {
        // propagate error stigma to the if expression
        markAsErrorNode(astNode);
      }
      if (isErrorNode(elseExpression)) {
        // propagate error stigma to the if expression
        markAsErrorNode(astNode);
      }
    } else {
      astNode.setType(getOclVoid());
    }

    initStartEndPositions(astNode, ifExpCS);

    return astNode;
  }

}
