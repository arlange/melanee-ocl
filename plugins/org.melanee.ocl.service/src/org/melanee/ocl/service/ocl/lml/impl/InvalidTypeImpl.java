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


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.melanee.ocl.service.ocl.lml.InvalidType;


/**
 * The InvalidTypeImpl class
 * @author Dominik Kantner
 *
 */
public class InvalidTypeImpl extends EObjectImpl implements InvalidType {

	/**
	 * stores the name
	 */
	protected String name;
	
	/**
	 * stores a list of operations
	 */
	protected EList<EObject> operations;
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.PredefinedType#getName()
	 */
	@Override
	public String getName() {
		if (name == null) {
			name = SINGLETON_NAME;
		}

		return name;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.PredefinedType#oclOperations()
	 */
	@Override
	public EList<EObject> oclOperations() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
