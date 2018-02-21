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
package org.melanee.ocl.service.ocl.lml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.ecore.delegate.InvocationBehavior;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.OperationCallExp;
import org.eclipse.ocl.expressions.IteratorExp;
import org.eclipse.ocl.expressions.PropertyCallExp;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.utilities.PredefinedType;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.ocl.service.ocl.lml.utilities.DeepOCLUtilities;

/**
 * An adapted EvaluationVisitorImpl to the needs of a deep OCL dialect
 * 
 * @author Dominik Kantner
 *
 */
public class DeepOCLEvaluationVisitorImpl extends
    org.eclipse.ocl.EvaluationVisitorImpl<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> {

  /**
   * stores the last levelcast
   */
  protected EObject lastCast;

  /**
   * @param env
   *          A Environment
   * @param evalEnv
   *          An EvaluationEnvironment
   * @param extentMap
   *          An ExtentMap
   */
  public DeepOCLEvaluationVisitorImpl(
      Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> env,
      EvaluationEnvironment<EObject, EObject, EObject, EObject, EObject> evalEnv,
      Map<? extends EObject, ? extends Set<? extends EObject>> extentMap) {
    super(env, evalEnv, extentMap);
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.AbstractEvaluationVisitor#getOperationBody(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  protected OCLExpression<EObject> getOperationBody(EObject operation) {
    if (operation instanceof EOperation) {
      @SuppressWarnings("rawtypes")
      OCLExpression result = InvocationBehavior.INSTANCE
          .getCachedOCLExpression((EOperation) operation);
      if (result == InvocationBehavior.NO_OCL_DEFINITION) {
        return null;
      } else if (result == null) {
        String expr = OCLCommon.getDelegateAnnotation((EModelElement) operation, "body");
        if (expr != null) {
          EClass context = ((EOperation) operation).getEContainingClass();
          OCL<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> ocl = OCL
              .newInstance(getEnvironment().getFactory());
          OCLHelper<EObject, EObject, EObject, Constraint> helper = ocl.createOCLHelper();
          helper.setOperationContext(context, operation);
          Constraint constraint;
          try {
            constraint = (Constraint) helper.createBodyCondition(expr);
          } catch (ParserException e) {
            throw new org.eclipse.ocl.ecore.delegate.OCLDelegateException(e.getLocalizedMessage(),
                e);
          }
          if (constraint == null) {
            return null;
          }
          ExpressionInOCL specification = (ExpressionInOCL) constraint.getSpecification();
          if (specification == null) {
            return null;
          }

          OCLExpression<EObject> body = (OCLExpression<EObject>) specification.getBodyExpression();

          result = body;
        }
        if (result == null) {

          result = super.getOperationBody(operation);
        }

      }

      return result;
    } else {
      return super.getOperationBody(operation);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.EvaluationVisitorImpl#visitIteratorExp(org.eclipse.ocl.
   * expressions.IteratorExp)
   */
  public Object visitIteratorExp(IteratorExp<EObject, EObject> ie) {

    Object object = super.visitIteratorExp(ie);
    if (ie.getName() == "collect") {
      if (ie.getBody() instanceof PropertyCallExp) { // cast destination
        @SuppressWarnings("rawtypes")
        PropertyCallExp propertyCallExp = (PropertyCallExp) (ie.getBody());
        if (propertyCallExp.getReferredProperty() instanceof NavigationAttribute) {
          NavigationAttribute navAttribute = ((NavigationAttribute) propertyCallExp
              .getReferredProperty());
          CastData newCast = new CastData();
          newCast.setDestination(navAttribute.getNavigationValue());
          newCast.setSource(null);
          ((DeepOCLEvaluationEnvironment) getEvaluationEnvironment()).setLastCast(newCast);
        } else if (propertyCallExp.getReferredProperty() instanceof LevelCastAttribute) {
          LevelCastAttribute levelCastAttribute = (LevelCastAttribute) (propertyCallExp
              .getReferredProperty());
          CastData newCast = new CastData();
          newCast.setDestination(levelCastAttribute.getCast());
          newCast.setSource(null);
          ((DeepOCLEvaluationEnvironment) getEvaluationEnvironment()).setLastCast(newCast);
        }

      }
    }
    return object;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.EvaluationVisitorImpl#visitPropertyCallExp(org.eclipse.ocl.
   * expressions.PropertyCallExp)
   */
  public Object visitPropertyCallExp(PropertyCallExp<EObject, EObject> pc) {
    Object object = super.visitPropertyCallExp(pc);
    if (pc.getReferredProperty() instanceof NavigationAttribute
        && !(pc.getSource() instanceof VariableExp)) { // only
                                                       // cast
                                                       // if
      // source is not
      // a
      // variable, in
      // normal case
      // indicator for
      // iterator exp,
      // so that no
      // casst is done
      // until
      // iteration has
      // finished
      NavigationAttribute navAttribute = ((NavigationAttribute) pc.getReferredProperty());
      CastData newCast = new CastData();
      newCast.setDestination(navAttribute.getNavigationValue());
      newCast.setSource(null);
      ((DeepOCLEvaluationEnvironment) getEvaluationEnvironment()).setLastCast(newCast);
    }

    if (pc.getReferredProperty() instanceof LevelCastAttribute) {
      OCLExpression<EObject> source = pc.getSource();
      Object sourceVal = safeVisitExpression(source);
      LevelCastAttribute levelCastAttribute = (LevelCastAttribute) pc.getReferredProperty();
      if ((sourceVal != levelCastAttribute.getCast()) && !(DeepOCLUtilities
          .getAllTypes((Clabject) sourceVal).contains(levelCastAttribute.getCast()))) {
        return getInvalid();
      }

      CastData newCast = new CastData();
      newCast.setDestination(levelCastAttribute.getCast());
      newCast.setSource(null);
      ((DeepOCLEvaluationEnvironment) getEvaluationEnvironment()).setLastCast(newCast);

    }
    return object;

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.EvaluationVisitorImpl#visitOperationCallExp(org.eclipse.ocl.
   * expressions.OperationCallExp)
   */
  public Object visitOperationCallExp(OperationCallExp<EObject, EObject> oc) {
    Object resultObject = super.visitOperationCallExp(oc);

    OCLExpression<EObject> source = oc.getSource();
    Object sourceVal = safeVisitExpression(source);

    if (sourceVal instanceof Clabject) {
      int opCode = oc.getOperationCode();
      List<OCLExpression<EObject>> args = oc.getArgument();

      if (opCode == PredefinedType.OCL_AS_TYPE) {

        OCLExpression<EObject> arg = args.get(0);

        EObject type = (EObject) arg.accept(getVisitor());
        if (type instanceof Clabject) {
          if (Boolean.TRUE.equals(oclIsKindOf(sourceVal, type))) {

            return sourceVal;

          } else {
            return getInvalid();
          }
        }
      }

      if (opCode == PredefinedType.ALL_INSTANCES && args.size() == 2) {

        Clabject sourceClabject = (Clabject) sourceVal;
        if (args.get(0) instanceof IntegerLiteralExp && args.get(1) instanceof IntegerLiteralExp) {
          int distance1 = ((IntegerLiteralExp) args.get(0)).getIntegerSymbol();
          int distance2 = ((IntegerLiteralExp) args.get(1)).getIntegerSymbol();
          if (distance1 >= 1 && distance2 > distance1) {
            int tempDist = distance1;
            ArrayList<Clabject> allInstances = new ArrayList<Clabject>();
            while (tempDist <= distance2) {
              allInstances
                  .addAll(DeepOCLUtilities.getInstancesWithDistance(sourceClabject, tempDist));
              tempDist++;
            }
            return allInstances;

          }
        }

        return null;

      }

      if (opCode == PredefinedType.ALL_INSTANCES && args.size() == 1) {

        Clabject sourceClabject = (Clabject) sourceVal;
        if (args.get(0) instanceof IntegerLiteralExp) {
          int distance = ((IntegerLiteralExp) args.get(0)).getIntegerSymbol();
          if (distance >= 1) {
            return DeepOCLUtilities.getInstancesWithDistance(sourceClabject, distance);
          }
        }

        return null;

      }

      if (opCode == PredefinedType.ALL_INSTANCES && args.size() == 0) {

        Clabject sourceClabject = (Clabject) sourceVal;
        return sourceClabject.getInstances();

      }

      return resultObject;

    }
    return resultObject;
  }

}
