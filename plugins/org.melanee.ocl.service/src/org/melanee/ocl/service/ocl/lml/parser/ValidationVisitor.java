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
package org.melanee.ocl.service.ocl.lml.parser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.OppositePropertyCallExp;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.ecore.utilities.VisitorExtension;
import org.eclipse.ocl.expressions.OperationCallExp;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.ocl.service.ocl.lml.Constraint;
import org.melanee.ocl.service.ocl.lml.util.DeepOCLStandardLibraryUtil;

/**
 * Validation Visitor adapted to deep model
 * 
 * @author Dominik
 *
 */
public class ValidationVisitor extends
		org.eclipse.ocl.parser.ValidationVisitor<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject>
		implements VisitorExtension<Boolean> {

	/**
	 * Constructor
	 * 
	 * @param environment
	 */
	public ValidationVisitor(
			Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> environment) {
		super(environment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ocl.ecore.utilities.VisitorExtension#
	 * visitOppositePropertyCallExp(org.eclipse.ocl.ecore.
	 * OppositePropertyCallExp)
	 */
	@Override
	public Boolean visitOppositePropertyCallExp(OppositePropertyCallExp callExp) {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ocl.parser.ValidationVisitor#visitOperationCallExp(org.
	 * eclipse.ocl.expressions.OperationCallExp) need to be overridden so that
	 * oclAsDeepType works
	 */
	public Boolean visitOperationCallExp(OperationCallExp<EObject, EObject> oc) {
		if (oc.getReferredOperation() instanceof EOperation && ((EOperation) oc.getReferredOperation())
				.getName() == DeepOCLStandardLibraryUtil.OCL_AS_DEEP_TYPE_NAME) {
			return true;
		}
		return super.visitOperationCallExp(oc);
	}

}
