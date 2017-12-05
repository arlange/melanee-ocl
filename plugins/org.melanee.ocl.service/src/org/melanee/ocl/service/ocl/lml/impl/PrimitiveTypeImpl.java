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

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EDataTypeImpl;
import org.eclipse.ocl.util.OCLStandardLibraryUtil;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironmentFactory;
import org.melanee.ocl.service.ocl.lml.PrimitiveType;

/**
 * The PrimitiveTypeImpl Class
 * @author Dominik Kantner
 *
 */
public class PrimitiveTypeImpl extends EDataTypeImpl implements PrimitiveType {

	/**
	 * stores operations
	 */
	protected EList<EObject> operations;
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.PredefinedType#oclOperations()
	 */
	@Override
	public EList<EObject> oclOperations() {
		// TODO Auto-generated method stub
		if(operations==null){
			String myName=getName();
			if(INTEGER_NAME.equals(myName)){
				operations=ECollections.asEList(OCLStandardLibraryUtil.createIntegerOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
			}
			if(REAL_NAME.equals(myName)){
				operations=ECollections.asEList(OCLStandardLibraryUtil.createRealOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
			}
			
			if(BOOLEAN_NAME.equals(myName)){
				operations=ECollections.asEList(OCLStandardLibraryUtil.createBooleanOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
			}
			if(STRING_NAME.equals(myName)){
				operations=ECollections.asEList(OCLStandardLibraryUtil.createStringOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
			}
		}
		return operations;
	}

}
