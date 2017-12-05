/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.models.definition.constraint;

import org.melanee.core.models.plm.PLM.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pointer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.melanee.ocl2.models.definition.constraint.Pointer#getPointer <em>Pointer</em>}</li>
 * </ul>
 *
 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getPointer()
 * @model
 * @generated
 */
public interface Pointer extends Expression {
	/**
	 * Returns the value of the '<em><b>Pointer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pointer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pointer</em>' reference.
	 * @see #setPointer(Element)
	 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getPointer_Pointer()
	 * @model
	 * @generated
	 */
	Element getPointer();

	/**
	 * Sets the value of the '{@link org.melanee.ocl2.models.definition.constraint.Pointer#getPointer <em>Pointer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pointer</em>' reference.
	 * @see #getPointer()
	 * @generated
	 */
	void setPointer(Element value);

} // Pointer
