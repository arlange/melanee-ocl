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
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.ocl.service.ocl.lml.Variable;


/**
 * The VariableImpl Class
 * @author Dominik Kantner
 *
 */
public class VariableImpl
implements Variable, InternalEObject{

	/**
	 * stores the name
	 */
	protected String name;
	/**
	 * stores the name
	 */
	protected EObject type;
	/**
	 * stores the initExpression
	 */
	protected OCLExpression<EObject> initExpression;
	
	/**
	 * constructor
	 */
	public VariableImpl() {
		super();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.TypedElement#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.TypedElement#getType()
	 */
	@Override
	public EObject getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.TypedElement#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name=name;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.TypedElement#setType(java.lang.Object)
	 */
	@Override
	public void setType(EObject type) {
		this.type=type;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eClass()
	 */
	@Override
	public EClass eClass() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eResource()
	 */
	@Override
	public Resource eResource() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eContainer()
	 */
	@Override
	public EObject eContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eContainingFeature()
	 */
	@Override
	public EStructuralFeature eContainingFeature() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eContainmentFeature()
	 */
	@Override
	public EReference eContainmentFeature() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eContents()
	 */
	@Override
	public EList<EObject> eContents() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eAllContents()
	 */
	@Override
	public TreeIterator<EObject> eAllContents() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eIsProxy()
	 */
	@Override
	public boolean eIsProxy() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eCrossReferences()
	 */
	@Override
	public EList<EObject> eCrossReferences() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public Object eGet(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature, boolean)
	 */
	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eSet(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eUnset(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public void eUnset(EStructuralFeature feature) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eInvoke(org.eclipse.emf.ecore.EOperation, org.eclipse.emf.common.util.EList)
	 */
	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments)
			throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Notifier#eAdapters()
	 */
	@Override
	public EList<Adapter> eAdapters() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Notifier#eDeliver()
	 */
	@Override
	public boolean eDeliver() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Notifier#eSetDeliver(boolean)
	 */
	@Override
	public void eSetDeliver(boolean deliver) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Notifier#eNotify(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void eNotify(Notification notification) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.Variable#getInitExpression()
	 */
	@Override
	public OCLExpression<EObject> getInitExpression() {
		// TODO Auto-generated method stub
		return initExpression;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.Variable#setInitExpression(org.eclipse.ocl.expressions.OCLExpression)
	 */
	@Override
	public void setInitExpression(OCLExpression<EObject> value) {
		// TODO Auto-generated method stub
		initExpression=value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.Variable#getRepresentedParameter()
	 */
	@Override
	public EObject getRepresentedParameter() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.Variable#setRepresentedParameter(java.lang.Object)
	 */
	@Override
	public void setRepresentedParameter(EObject value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.Variable#checkInitType(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.Variable#checkInitType(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean checkInitType(DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.Visitable#accept(U)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
		// TODO Auto-generated method stub
		return ((Visitor<T, EObject, ?, ?, ?, EObject, ?, ?, ?, ?>) v)
				.visitVariable(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.TypedASTNode#getTypeStartPosition()
	 */
	@Override
	public int getTypeStartPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.TypedASTNode#setTypeStartPosition(int)
	 */
	@Override
	public void setTypeStartPosition(int value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.TypedASTNode#getTypeEndPosition()
	 */
	@Override
	public int getTypeEndPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.TypedASTNode#setTypeEndPosition(int)
	 */
	@Override
	public void setTypeEndPosition(int value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ASTNode#getStartPosition()
	 */
	@Override
	public int getStartPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ASTNode#setStartPosition(int)
	 */
	@Override
	public void setStartPosition(int value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ASTNode#getEndPosition()
	 */
	@Override
	public int getEndPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ASTNode#setEndPosition(int)
	 */
	@Override
	public void setEndPosition(int value) {
		// TODO Auto-generated method stub
		
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
