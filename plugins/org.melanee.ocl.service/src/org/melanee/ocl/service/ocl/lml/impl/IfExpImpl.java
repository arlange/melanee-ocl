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
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.operations.IfExpOperations;
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.ocl.service.ocl.lml.IfExp;

/**
 * The IfExpImpl class
 * @author Dominik Kantner
 *
 */
public class IfExpImpl extends OCLExpressionImpl implements IfExp,InternalEObject {
	
	/**
	 * stores the condition
	 */
	protected OCLExpression<EObject> condition;

	/**
	 * The cached value of the '{@link #getThenExpression() <em>Then Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenExpression()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression<EObject> thenExpression;

	/**
	 * The cached value of the '{@link #getElseExpression() <em>Else Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseExpression()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression<EObject> elseExpression;

	/**
	 * Constructor with behavior of super class constructor
	 * 
	 * @generated
	 */
	public IfExpImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IfExp#getCondition()
	 */
	@Override
	public OCLExpression<EObject> getCondition() {
		// TODO Auto-generated method stub
		return condition;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IfExp#setCondition(org.eclipse.ocl.expressions.OCLExpression)
	 */
	@Override
	public void setCondition(OCLExpression<EObject> value) {
		// TODO Auto-generated method stub
		condition=value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IfExp#getThenExpression()
	 */
	@Override
	public OCLExpression<EObject> getThenExpression() {
		// TODO Auto-generated method stub
		return thenExpression;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IfExp#setThenExpression(org.eclipse.ocl.expressions.OCLExpression)
	 */
	@Override
	public void setThenExpression(OCLExpression<EObject> value) {
		// TODO Auto-generated method stub
		thenExpression=value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IfExp#getElseExpression()
	 */
	@Override
	public OCLExpression<EObject> getElseExpression() {
		// TODO Auto-generated method stub
		return elseExpression;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IfExp#setElseExpression(org.eclipse.ocl.expressions.OCLExpression)
	 */
	@Override
	public void setElseExpression(OCLExpression<EObject> value) {
		// TODO Auto-generated method stub
		elseExpression=value;

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IfExp#checkBooleanCondition(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean checkBooleanCondition(DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO Auto-generated method stub
		return IfExpOperations
				.checkBooleanCondition(this, diagnostics, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IfExp#checkIfType(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean checkIfType(DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO Auto-generated method stub
		return IfExpOperations.checkIfType(this, diagnostics, context);
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.OCLExpressionImpl#accept(org.eclipse.ocl.utilities.Visitor)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
		return ((Visitor<T, EObject, ?, ?, ?, ?, ?, ?, ?, ?>) v)
			.visitIfExp(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eNotificationRequired()
	 */
	@Override
	public boolean eNotificationRequired() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eURIFragmentSegment(org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public String eURIFragmentSegment(EStructuralFeature eFeature,
			EObject eObject) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eObjectForURIFragmentSegment(java.lang.String)
	 */
	@Override
	public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eSetClass(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public void eSetClass(EClass eClass) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eSetting(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public Setting eSetting(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eBaseStructuralFeatureID(int, java.lang.Class)
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eContainerFeatureID()
	 */
	@Override
	public int eContainerFeatureID() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eDerivedStructuralFeatureID(int, java.lang.Class)
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eDerivedOperationID(int, java.lang.Class)
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eSetResource(org.eclipse.emf.ecore.resource.Resource.Internal, org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eSetResource(Internal resource,
			NotificationChain notifications) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eInverseAdd(org.eclipse.emf.ecore.InternalEObject, int, java.lang.Class, org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, Class<?> baseClass, NotificationChain notifications) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eInverseRemove(org.eclipse.emf.ecore.InternalEObject, int, java.lang.Class, org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, Class<?> baseClass, NotificationChain notifications) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eBasicSetContainer(org.eclipse.emf.ecore.InternalEObject, int, org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eBasicSetContainer(InternalEObject newContainer,
			int newContainerFeatureID, NotificationChain notifications) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eBasicRemoveFromContainer(org.eclipse.emf.common.notify.NotificationChain)
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainer(
			NotificationChain notifications) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eProxyURI()
	 */
	@Override
	public URI eProxyURI() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eSetProxyURI(org.eclipse.emf.common.util.URI)
	 */
	@Override
	public void eSetProxyURI(URI uri) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eResolveProxy(org.eclipse.emf.ecore.InternalEObject)
	 */
	@Override
	public EObject eResolveProxy(InternalEObject proxy) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eInternalContainer()
	 */
	@Override
	public InternalEObject eInternalContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eInternalResource()
	 */
	@Override
	public Internal eInternalResource() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eDirectResource()
	 */
	@Override
	public Internal eDirectResource() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eStore()
	 */
	@Override
	public EStore eStore() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eSetStore(org.eclipse.emf.ecore.InternalEObject.EStore)
	 */
	@Override
	public void eSetStore(EStore store) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eGet(org.eclipse.emf.ecore.EStructuralFeature, boolean, boolean)
	 */
	@Override
	public Object eGet(EStructuralFeature eFeature, boolean resolve,
			boolean coreType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eGet(int, boolean, boolean)
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eSet(int, java.lang.Object)
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eUnset(int)
	 */
	@Override
	public void eUnset(int featureID) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eIsSet(int)
	 */
	@Override
	public boolean eIsSet(int featureID) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.InternalEObject#eInvoke(int, org.eclipse.emf.common.util.EList)
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

}
