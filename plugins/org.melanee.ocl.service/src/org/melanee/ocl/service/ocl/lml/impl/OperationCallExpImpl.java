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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.ocl.ecore.EcorePackage;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.util.OCLStandardLibraryUtil;
import org.eclipse.ocl.utilities.PredefinedType;
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.ocl.service.ocl.lml.OperationCallExp;

/**
 * The OperationCallExpImpl class
 * 
 * @author Dominik Kantner
 *
 */
public class OperationCallExpImpl implements OperationCallExp, InternalEObject {

  /**
   * stores the source
   */
  protected OCLExpression<EObject> source;

  /**
   * stores the argument
   */
  protected EList<OCLExpression<EObject>> argument;

  /**
   * stores the type
   */
  protected EObject type;

  /**
   * stores the operation code
   */
  protected int operationCode = -1;

  /**
   * stores the referred Operation
   */
  protected EObject referredOperation;

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.OperationCallExp#getArgument()
   */
  @Override
  public EList<OCLExpression<EObject>> getArgument() {
    // TODO Auto-generated method stub
    if (argument == null) {
      argument = new EObjectContainmentEList<OCLExpression<EObject>>(OCLExpression.class, this,
          EcorePackage.OPERATION_CALL_EXP__ARGUMENT);
    }
    return argument;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.OperationCallExp#getReferredOperation()
   */
  @Override
  public EObject getReferredOperation() {
    // TODO Auto-generated method stub
    return this.referredOperation;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.OperationCallExp#setReferredOperation(java.lang.
   * Object)
   */
  @Override
  public void setReferredOperation(EObject value) {
    this.referredOperation = value;

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.OperationCallExp#getOperationCode()
   */
  @Override
  public int getOperationCode() {
    // TODO Auto-generated method stub

    // later maybe differentiation between any and typetype //like
    // OperatioCallExpImpl of ecore
    EObject type = getSource().getType();

    if (getReferredOperation() instanceof EOperation) {
      String operName = ((EOperation) getReferredOperation()).getName();
      if (type instanceof PredefinedType<?>) {
        operationCode = OCLStandardLibraryUtil.getOperationCode(operName);
      } else {
        if (operName == PredefinedType.OCL_IS_TYPE_OF_NAME) {
          operationCode = OCLStandardLibraryUtil.getOclAnyOperationCode(operName);
        } else if (operName == PredefinedType.OCL_IS_KIND_OF_NAME) {
          operationCode = OCLStandardLibraryUtil.getOclAnyOperationCode(operName);
        } else if (operName == PredefinedType.OCL_IS_UNDEFINED_NAME) {
          operationCode = OCLStandardLibraryUtil.getOclAnyOperationCode(operName);
        } else if (operName == PredefinedType.OCL_IS_INVALID_NAME) {
          operationCode = OCLStandardLibraryUtil.getOclAnyOperationCode(operName);
        }

      }

    }

    return operationCode;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.OperationCallExp#setOperationCode(int)
   */
  @Override
  public void setOperationCode(int value) {

    this.operationCode = value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.OperationCallExp#checkArgumentsConform(org.
   * eclipse.emf.common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkArgumentsConform(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.OperationCallExp#checkArgumentCount(org.eclipse.
   * emf.common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkArgumentCount(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.FeatureCallExp#isMarkedPre()
   */
  @Override
  public boolean isMarkedPre() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setMarkedPre(boolean value) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.expressions.CallExp#getSource()
   */
  @Override
  public OCLExpression<EObject> getSource() {
    // TODO Auto-generated method stub
    return this.source;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.expressions.CallExp#setSource(org.eclipse.ocl.expressions.
   * OCLExpression)
   */
  @Override
  public void setSource(OCLExpression<EObject> value) {
    this.source = value;

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedElement#getName()
   */
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
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

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedElement#setType(java.lang.Object)
   */
  @Override
  public void setType(EObject type) {
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
  @SuppressWarnings("unchecked")
  @Override
  public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
    // TODO Auto-generated method stub
    return ((Visitor<T, EObject, EObject, ?, ?, ?, ?, ?, ?, ?>) v).visitOperationCallExp(this);
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

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.CallingASTNode#getPropertyStartPosition()
   */
  @Override
  public int getPropertyStartPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.CallingASTNode#setPropertyStartPosition(int)
   */
  @Override
  public void setPropertyStartPosition(int value) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.CallingASTNode#getPropertyEndPosition()
   */
  @Override
  public int getPropertyEndPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.CallingASTNode#setPropertyEndPosition(int)
   */
  @Override
  public void setPropertyEndPosition(int value) {
    // TODO Auto-generated method stub

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
