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
package org.melanee.ocl.service.ocl.lml.impl;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.ocl.service.ocl.lml.IteratorExp;

/**
 * The IteratorExpImpl class
 * 
 * @author Dominik Kantner
 *
 */
public class IteratorExpImpl extends LoopExpImpl implements IteratorExp {

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.IteratorExp#checkBooleanType(org.eclipse.emf.
   * common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkBooleanType(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.IteratorExp#checkCollectType(org.eclipse.emf.
   * common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkCollectType(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.IteratorExp#checkSelectRejectType(org.eclipse.emf
   * .common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkSelectRejectType(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.IteratorExp#checkBooleanBodyType(org.eclipse.emf.
   * common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkBooleanBodyType(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.melanee.ocl.service.OCLExpressionImpl#accept(org.eclipse.ocl.utilities.
   * Visitor)
   */
  @SuppressWarnings("unchecked")
  public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
    return ((Visitor<T, EObject, ?, ?, ?, EObject, ?, ?, ?, ?>) v).visitIteratorExp(this);
  }

}
