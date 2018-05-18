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
package org.melanee.ocl.service;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.swt.widgets.Composite;
import org.melanee.core.models.plm.PLM.AbstractConstraint;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.core.workbench.interfaces.IConstraintLanguageService;
import org.melanee.ocl.service.ocl.lml.Constraint;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironmentFactory;
import org.melanee.ocl.service.ocl.lml.DeepOCLEvaluationEnvironment;

/**
 * The OCL Service which allows to evaluate expression in a deep OCL dialect on
 * deep models built with LML
 * 
 * @author Dominik Kantner
 *
 */
public class OCLService implements IConstraintLanguageService {

  /**
   * stores the OCL
   */
  private OCL<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> ocl;

  /**
   * stores the OCLHelper
   */
  OCLHelper<EObject, EObject, EObject, Constraint> helper;

  /**
   * Constructor
   */
  public OCLService() {
    ocl = OCL.newInstance(new DeepOCLEnvironmentFactory());
    helper = ocl.createOCLHelper();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.melanee.core.workbench.interfaces.IConstraintLanguageService#evaluate
   * (java.lang.Object, java.lang.String)
   */
  public Object evaluate(Object context, String expression) throws Exception {
    return evaluate(null, context, expression);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.melanee.core.workbench.interfaces.IConstraintLanguageService#evaluate
   * (java.lang.Object, java.lang.Object, java.lang.String)
   */
  public Object evaluate(Object definitionContext, Object context, String expression)
      throws Exception {

    if (definitionContext == null || definitionContext == context
        || (definitionContext instanceof Clabject
            && ((Clabject) definitionContext).getSubtypes().contains(context))) {
      helper.setContext((EObject) context);
      OCLExpression<EObject> parsed = helper.createQuery(expression);

      Query<EObject, EObject, EObject> query = ocl.createQuery(parsed);
      ((DeepOCLEvaluationEnvironment) query.getEvaluationEnvironment()).clearHelpData();
      return query.evaluate(context);
    } else {
      helper.setContext((EObject) context);
      if (definitionContext instanceof Clabject) {
        if (((Clabject) definitionContext).getClassificationTreeAsType().contains(context)) {
          if (!expression.startsWith("self")
              // Self shall not be added to keywords in the
              // beginning
              && !expression.startsWith("if ") && !expression.startsWith("'")
              && !expression.startsWith("let") && !expression.startsWith("true")
              && !expression.startsWith("false"))
            expression = "self." + expression;

          // If the expression started with an navigation it should
          // now be starting
          // with self and casted to the definition context.
          if (expression.startsWith("self"))
            expression = "self._" + ((Clabject) definitionContext).getName() + "_."
                + expression.substring(5);// begin
                                          // after
                                          // self.

          OCLExpression<EObject> parsed = helper.createQuery(expression);
          Query<EObject, EObject, EObject> query = ocl.createQuery(parsed);
          return query.evaluate(context);
        } else {
          throw new Exception("Bad Evaluation Context");
        }
      }
      throw new Exception("Definition Context no Clabject");

    }
  }

  @Override
  public String[] getDefinedConstraintsFor(Element definitionContext) {
    return null;
  }

  @Override
  public String[] getPossibleConstraintKindsFor(Element definitionContext) {
    return null;
  }

  @Override
  public AbstractConstraint addConstraint(Element definitionContext, String constraintKind) {
    return null;
  }

  @Override
  public AbstractConstraint getConstraint(Element definitionContext, String constraintName) {
    return null;
  }

  @Override
  public void deleteConstraint(Element definitionContext, String constraintName)
      throws UnsupportedOperationException {
    return;
  }

  @Override
  public ConstraintPropertySheetComposite getPropertySheetCompositeFor(
      AbstractConstraint constraint, Object widgetFactory, Composite editingArea,
      Element definitionContext) {
    return null;
  }

  @Override
  public Set<IValidationResult> validate(Element element, boolean createResourceMarkers) {
    return null;
  }

  @Override
  public Command initAttribute(Attribute attribute) {
    return null;
  }

  @Override
  public Command recalculateDerivedAttributes(Element element, int changeType) {
    return null;
  }

}
