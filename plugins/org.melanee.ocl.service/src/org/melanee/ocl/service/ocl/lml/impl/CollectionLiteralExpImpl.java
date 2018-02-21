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
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.ocl.ecore.EcorePackage;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.expressions.CollectionLiteralPart;
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.ocl.service.ocl.lml.CollectionLiteralExp;

/**
 * The CollectionLiteralExpImpl class
 * 
 * @author Dominik Kantner
 *
 */
public class CollectionLiteralExpImpl extends LiteralExpImpl
    implements CollectionLiteralExp, InternalEObject {
  /**
   * The collection default kind
   */
  protected static final CollectionKind KIND_EDEFAULT = CollectionKind.SET_LITERAL;

  /**
   * stores the collection kind
   */
  protected CollectionKind kind = KIND_EDEFAULT;

  /**
   * stores the part in a list
   */
  protected EList<CollectionLiteralPart<EObject>> part;

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.CollectionLiteralExp#getKind()
   */
  @Override
  public CollectionKind getKind() {
    // TODO Auto-generated method stub
    return this.kind;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.CollectionLiteralExp#setKind(org.eclipse.ocl.
   * expressions.CollectionKind)
   */
  @Override
  public void setKind(CollectionKind value) {
    // TODO Auto-generated method stub
    this.kind = value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.CollectionLiteralExp#getPart()
   */
  @Override
  public EList<CollectionLiteralPart<EObject>> getPart() {
    // TODO Auto-generated method stub
    if (part == null) {
      part = new EObjectContainmentEList<CollectionLiteralPart<EObject>>(
          CollectionLiteralPart.class, this, EcorePackage.COLLECTION_LITERAL_EXP__PART);
    }
    return part;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.CollectionLiteralExp#isSimpleRange()
   */
  @Override
  public boolean isSimpleRange() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.CollectionLiteralExp#checkNoCollectionInstances(
   * org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkNoCollectionInstances(DiagnosticChain diagnostics,
      Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.CollectionLiteralExp#checkSetKind(org.eclipse.emf
   * .common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkSetKind(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.CollectionLiteralExp#checkSequenceKind(org.
   * eclipse.emf.common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkSequenceKind(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.CollectionLiteralExp#checkBagKind(org.eclipse.emf
   * .common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkBagKind(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.CollectionLiteralExp#checkElementType(org.eclipse
   * .emf.common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkElementType(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.ocl.service.LiteralExpImpl#accept(org.eclipse.ocl.utilities.
   * Visitor)
   */
  @SuppressWarnings("unchecked")
  public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
    return ((Visitor<T, EObject, ?, ?, ?, ?, ?, ?, ?, ?>) v).visitCollectionLiteralExp(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eNotificationRequired()
   */
  @Override
  public boolean eNotificationRequired() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eURIFragmentSegment(org.eclipse.emf.
   * ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public String eURIFragmentSegment(EStructuralFeature eFeature, EObject eObject) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eObjectForURIFragmentSegment(java.lang.
   * String)
   */
  @Override
  public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eSetClass(org.eclipse.emf.ecore.EClass)
   */
  @Override
  public void eSetClass(EClass eClass) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eSetting(org.eclipse.emf.ecore.
   * EStructuralFeature)
   */
  @Override
  public Setting eSetting(EStructuralFeature feature) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eBaseStructuralFeatureID(int,
   * java.lang.Class)
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eContainerFeatureID()
   */
  @Override
  public int eContainerFeatureID() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eDerivedStructuralFeatureID(int,
   * java.lang.Class)
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eDerivedOperationID(int,
   * java.lang.Class)
   */
  @Override
  public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eSetResource(org.eclipse.emf.ecore.
   * resource.Resource.Internal, org.eclipse.emf.common.notify.NotificationChain)
   */
  @Override
  public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eInverseAdd(org.eclipse.emf.ecore.
   * InternalEObject, int, java.lang.Class,
   * org.eclipse.emf.common.notify.NotificationChain)
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class<?> baseClass,
      NotificationChain notifications) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eInverseRemove(org.eclipse.emf.ecore.
   * InternalEObject, int, java.lang.Class,
   * org.eclipse.emf.common.notify.NotificationChain)
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      Class<?> baseClass, NotificationChain notifications) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eBasicSetContainer(org.eclipse.emf.
   * ecore.InternalEObject, int, org.eclipse.emf.common.notify.NotificationChain)
   */
  @Override
  public NotificationChain eBasicSetContainer(InternalEObject newContainer,
      int newContainerFeatureID, NotificationChain notifications) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eBasicRemoveFromContainer(org.eclipse.
   * emf.common.notify.NotificationChain)
   */
  @Override
  public NotificationChain eBasicRemoveFromContainer(NotificationChain notifications) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eProxyURI()
   */
  @Override
  public URI eProxyURI() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eSetProxyURI(org.eclipse.emf.common.
   * util.URI)
   */
  @Override
  public void eSetProxyURI(URI uri) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.emf.ecore.InternalEObject#eResolveProxy(org.eclipse.emf.ecore.
   * InternalEObject)
   */
  @Override
  public EObject eResolveProxy(InternalEObject proxy) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eInternalContainer()
   */
  @Override
  public InternalEObject eInternalContainer() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eInternalResource()
   */
  @Override
  public Internal eInternalResource() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eDirectResource()
   */
  @Override
  public Internal eDirectResource() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eStore()
   */
  @Override
  public EStore eStore() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eSetStore(org.eclipse.emf.ecore.
   * InternalEObject.EStore)
   */
  @Override
  public void eSetStore(EStore store) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eGet(org.eclipse.emf.ecore.
   * EStructuralFeature, boolean, boolean)
   */
  @Override
  public Object eGet(EStructuralFeature eFeature, boolean resolve, boolean coreType) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eGet(int, boolean, boolean)
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eSet(int, java.lang.Object)
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eUnset(int)
   */
  @Override
  public void eUnset(int featureID) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eIsSet(int)
   */
  @Override
  public boolean eIsSet(int featureID) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.InternalEObject#eInvoke(int,
   * org.eclipse.emf.common.util.EList)
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
    // TODO Auto-generated method stub
    return null;
  }

}
