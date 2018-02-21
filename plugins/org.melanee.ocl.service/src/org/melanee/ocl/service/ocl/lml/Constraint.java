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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.utilities.ExpressionInOCL;

/**
 * The deep OCL Constraint interface
 * 
 * @author Dominik Kantner
 *
 */
public interface Constraint extends ENamedElement {

  /**
   * @return The according Expression in OCL
   */
  ExpressionInOCL<EObject, EObject> getSpecification();

  /**
   * @param value
   *          The according Expression in OCL
   */
  void setSpecification(ExpressionInOCL<EObject, EObject> value);

  /**
   * @return A list of constrained elements
   */
  EList<EModelElement> getConstrainedElements();

  /**
   * @return The stereotype
   */
  String getStereotype();

  /**
   * @param value
   *          The stereotype
   */
  void setStereotype(String value);
}
