/*******************************************************************************
 * Copyright (c) 2012, 2013 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.models.definition.constraint.impl;

import org.eclipse.emf.ecore.EClass;

import org.melanee.ocl2.models.definition.constraint.ConstraintPackage;
import org.melanee.ocl2.models.definition.constraint.PostConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Post Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class PostConstraintImpl extends ConstraintImpl implements PostConstraint {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PostConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintPackage.Literals.POST_CONSTRAINT;
	}

} //PostConstraintImpl
