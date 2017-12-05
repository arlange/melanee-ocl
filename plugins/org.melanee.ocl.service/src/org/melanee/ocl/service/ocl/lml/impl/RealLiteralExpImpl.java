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
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.ocl.service.ocl.lml.RealLiteralExp;

/**
 * The RealLiteralExpImpl Class
 * @author Dominik Kantner
 *
 */
public class RealLiteralExpImpl extends PrimitiveLiteralExpImpl implements
		RealLiteralExp,InternalEObject {

	
	/**
	 * stores the default real symbol
	 */
	protected static final Double REAL_SYMBOL_EDEFAULT = null;

	
	/**
	 * sores the real symbol
	 */
	protected Double realSymbol = REAL_SYMBOL_EDEFAULT;
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.RealLiteralExp#getRealSymbol()
	 */
	@Override
	public Double getRealSymbol() {
		// TODO Auto-generated method stub
		return realSymbol;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.RealLiteralExp#setRealSymbol(java.lang.Double)
	 */
	@Override
	public void setRealSymbol(Double value) {
		// TODO Auto-generated method stub
		realSymbol=value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.RealLiteralExp#checkRealType(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean checkRealType(DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO Auto-generated method stub
		return false;
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
	
	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.LiteralExpImpl#accept(org.eclipse.ocl.utilities.Visitor)
	 */
	@SuppressWarnings("unchecked")
	public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
		return ((Visitor<T, EObject, ?, ?, ?, ?, ?, ?, ?, ?>) v)
			.visitRealLiteralExp(this);
	}

}
