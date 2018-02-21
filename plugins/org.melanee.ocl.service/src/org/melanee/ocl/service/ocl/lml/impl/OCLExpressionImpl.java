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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.ocl.service.ocl.lml.OCLExpression;

/**
 * The OCLExpressionImpl class
 * 
 * @author Dominik Kantner
 *
 */
public class OCLExpressionImpl implements OCLExpression {

  /**
   * stores the name
   */
  protected String name;

  /**
   * stores the type
   */
  protected EObject type;

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedElement#getName()
   */
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return this.name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedElement#getType()
   */
  @Override
  public EObject getType() {
    // TODO Auto-generated method stub
    return this.type;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedElement#setName(java.lang.String)
   */
  @Override
  public void setName(String name) {
    // TODO Auto-generated method stub
    this.name = name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedElement#setType(java.lang.Object)
   */
  @Override
  public void setType(EObject type) {
    // TODO Auto-generated method stub
    this.type = type;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eClass()
   */
  @Override
  public EClass eClass() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eResource()
   */
  @Override
  public Resource eResource() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eContainer()
   */
  @Override
  public EObject eContainer() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eContainingFeature()
   */
  @Override
  public EStructuralFeature eContainingFeature() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eContainmentFeature()
   */
  @Override
  public EReference eContainmentFeature() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eContents()
   */
  @Override
  public EList<EObject> eContents() {
    // TODO Auto-generated method stub
    return new BasicInternalEList<EObject>(null);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eAllContents()
   */
  @Override
  public TreeIterator<EObject> eAllContents() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eIsProxy()
   */
  @Override
  public boolean eIsProxy() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eCrossReferences()
   */
  @Override
  public EList<EObject> eCrossReferences() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public Object eGet(EStructuralFeature feature) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature,
   * boolean)
   */
  @Override
  public Object eGet(EStructuralFeature feature, boolean resolve) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.EObject#eSet(org.eclipse.emf.ecore.EStructuralFeature,
   * java.lang.Object)
   */
  @Override
  public void eSet(EStructuralFeature feature, Object newValue) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.
   * EStructuralFeature)
   */
  @Override
  public boolean eIsSet(EStructuralFeature feature) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eUnset(org.eclipse.emf.ecore.
   * EStructuralFeature)
   */
  @Override
  public void eUnset(EStructuralFeature feature) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.EObject#eInvoke(org.eclipse.emf.ecore.EOperation,
   * org.eclipse.emf.common.util.EList)
   */
  @Override
  public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.common.notify.Notifier#eAdapters()
   */
  @Override
  public EList<Adapter> eAdapters() {
    // TODO Auto-generated method stub
    return ECollections.newBasicEList();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.common.notify.Notifier#eDeliver()
   */
  @Override
  public boolean eDeliver() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.common.notify.Notifier#eSetDeliver(boolean)
   */
  @Override
  public void eSetDeliver(boolean deliver) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.common.notify.Notifier#eNotify(org.eclipse.emf.common.notify.
   * Notification)
   */
  @Override
  public void eNotify(Notification notification) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.Visitable#accept(U)
   */
  @Override
  public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.ASTNode#getStartPosition()
   */
  @Override
  public int getStartPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.ASTNode#setStartPosition(int)
   */
  @Override
  public void setStartPosition(int value) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.ASTNode#getEndPosition()
   */
  @Override
  public int getEndPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.ASTNode#setEndPosition(int)
   */
  @Override
  public void setEndPosition(int value) {
    // TODO Auto-generated method stub

  }

}
