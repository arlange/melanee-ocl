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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.util.OCLStandardLibraryUtil;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.ocl.service.ocl.lml.Constraint;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironment;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironmentFactory;
import org.melanee.ocl.service.ocl.lml.TypeType;
import org.melanee.ocl.service.ocl.lml.util.DeepOCLStandardLibraryUtil;



/**
 * The TypeTypeImpl
 * @author Dominik Kantner
 *
 */
public class TypeTypeImpl extends EObjectImpl implements TypeType {

	
	/**
	 * stores the referred type
	 */
	protected EObject referredType;
	
	/**
	 * stores the operations
	 */
	protected EList<EObject> operations;
	
	/**
	 * Constructor 
	 */
	protected TypeTypeImpl() {
		super();
	}


	/**
	 * Constructor
	 * @param referredType
	 */
	protected TypeTypeImpl(EObject referredType) {
		this();

		this.referredType = referredType;
	}

	/**
	 * @param referredType
	 * @return A TypeType
	 */
	public static TypeType createTypeType(EObject referredType) {
		return new TypeTypeImpl(referredType);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.TypeType#getReferredType()
	 */
	@Override
	public EObject getReferredType() {
		// TODO Auto-generated method stub
		return this.referredType;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.PredefinedType#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SINGLETON_NAME;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.PredefinedType#oclOperations()
	 */
	@Override
	public EList<EObject> oclOperations() {
		// TODO Auto-generated method stub
		if(operations==null || operations.isEmpty()){
			operations=	ECollections.asEList(OCLStandardLibraryUtil.createTypeTypeOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
			  // create a new factory with new environment , may be later use already existing env
			
			Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> lmlEnvironment=new DeepOCLEnvironmentFactory().createEnvironment();
			List<EObject> standardTypeOperations=OCLStandardLibraryUtil.createTypeTypeOperations(lmlEnvironment);
			List<EObject> deepTypeOperations=DeepOCLStandardLibraryUtil.createTypeTypeOperations((DeepOCLEnvironment) lmlEnvironment);
			List<EObject> allTypeOperations=new ArrayList<EObject>();
			allTypeOperations.addAll(standardTypeOperations);
			allTypeOperations.addAll(deepTypeOperations);
			operations=ECollections.asEList(allTypeOperations);
		}
		
		return operations;
	}


}
