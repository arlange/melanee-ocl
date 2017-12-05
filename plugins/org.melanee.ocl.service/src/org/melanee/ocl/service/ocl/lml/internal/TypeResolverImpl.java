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
package org.melanee.ocl.service.ocl.lml.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource; //vllt eigene ressource
import org.eclipse.ocl.AbstractTypeResolver;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironment;

/**
 * The TypeResolverImpl class
 * @author Dominik Kantner
 *
 */
public class TypeResolverImpl extends AbstractTypeResolver<EPackage, EObject, EObject, EObject, EObject>{

	/**
	 * Constructor
	 * @param env
	 */
	public TypeResolverImpl(DeepOCLEnvironment env){
		super(env);
	}
	
	/**
	 * Constructor
	 * @param env
	 * @param resource
	 */
	public TypeResolverImpl(DeepOCLEnvironment env, Resource resource){
		super(env,resource);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractTypeResolver#createShadowClass(java.lang.Object)
	 */
	@Override
	protected EObject createShadowClass(EObject type) {
		// TODO Auto-generated EObject stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractTypeResolver#addProperty(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void addProperty(EObject owner, EObject property) {
		// TODO Auto-generated EObject stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractTypeResolver#addOperation(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void addOperation(EObject owner, EObject operation) {
		// TODO Auto-generated EObject stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractTypeResolver#getShadowedClassifier(java.lang.Object)
	 */
	@Override
	protected EObject getShadowedClassifier(EObject shadow) {
		// TODO Auto-generated EObject stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractTypeResolver#createPackage(java.lang.String)
	 */
	@Override
	protected EPackage createPackage(String name) {
		// TODO Auto-generated EObject stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractTypeResolver#findPackage(java.lang.String)
	 */
	@Override
	protected EPackage findPackage(String name) {
		// TODO Auto-generated EObject stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractTypeResolver#addClassifier(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void addClassifier(EPackage pkg, EObject classifier) {
		// TODO Auto-generated EObject stub
		
	}
}
