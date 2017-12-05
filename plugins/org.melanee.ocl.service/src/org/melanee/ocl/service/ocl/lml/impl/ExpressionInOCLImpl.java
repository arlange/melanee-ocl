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
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.ocl.service.ocl.lml.ExpressionInOCL;


/**
 * The ExpressionInOCLImpl class
 * @author Dominik Kantner
 *
 */
public class ExpressionInOCLImpl implements ExpressionInOCL{

	/**
	 * stores the body expression
	 */
	protected OCLExpression<EObject> bodyExpression;
	
	/**
	 * stores the context variable
	 */
	protected Variable<EObject,EObject> contextVariable;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ExpressionInOCL#getBodyExpression()
	 */
	@Override
	public OCLExpression<EObject> getBodyExpression() {
		// TODO Auto-generated method stub
		return this.bodyExpression;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ExpressionInOCL#setBodyExpression(org.eclipse.ocl.expressions.OCLExpression)
	 */
	@Override
	public void setBodyExpression(OCLExpression<EObject> value) {
		this.bodyExpression=value;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ExpressionInOCL#getContextVariable()
	 */
	@Override
	public Variable<EObject, EObject> getContextVariable() {
		// TODO Auto-generated method stub
		return this.contextVariable;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ExpressionInOCL#setContextVariable(org.eclipse.ocl.expressions.Variable)
	 */
	@Override
	public void setContextVariable(Variable<EObject, EObject> value) {
		this.contextVariable=value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ExpressionInOCL#getResultVariable()
	 */
	@Override
	public Variable<EObject, EObject> getResultVariable() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ExpressionInOCL#setResultVariable(org.eclipse.ocl.expressions.Variable)
	 */
	@Override
	public void setResultVariable(Variable<EObject, EObject> value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ExpressionInOCL#getParameterVariable()
	 */
	@Override
	public EList<Variable<EObject, EObject>> getParameterVariable() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.ExpressionInOCL#getGeneratedType()
	 */
	@Override
	public EList<EObject> getGeneratedType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.Visitable#accept(U)
	 */
	@Override
	public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
		// TODO Auto-generated method stub
		return null;
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
		return new BasicInternalEList<EObject>(null);
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
		return ECollections.newBasicEList();
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

}
