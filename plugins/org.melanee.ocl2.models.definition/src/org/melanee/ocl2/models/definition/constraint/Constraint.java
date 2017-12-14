/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.models.definition.constraint;

import org.eclipse.emf.common.util.EList;

import org.melanee.core.models.plm.PLM.AbstractConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.melanee.ocl2.models.definition.constraint.Constraint#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.melanee.ocl2.models.definition.constraint.Constraint#getMessage <em>Message</em>}</li>
 *   <li>{@link org.melanee.ocl2.models.definition.constraint.Constraint#getSeverity <em>Severity</em>}</li>
 *   <li>{@link org.melanee.ocl2.models.definition.constraint.Constraint#getLevel <em>Level</em>}</li>
 * </ul>
 *
 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends AbstractConstraint {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference list.
	 * The list contents are of type {@link org.melanee.ocl2.models.definition.constraint.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference list.
	 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getConstraint_Expression()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getExpression();

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getConstraint_Message()
	 * @model
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link org.melanee.ocl2.models.definition.constraint.Constraint#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Severity</b></em>' attribute.
	 * The literals are from the enumeration {@link org.melanee.ocl2.models.definition.constraint.Severity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Severity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Severity</em>' attribute.
	 * @see org.melanee.ocl2.models.definition.constraint.Severity
	 * @see #setSeverity(Severity)
	 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getConstraint_Severity()
	 * @model
	 * @generated
	 */
	Severity getSeverity();

	/**
	 * Sets the value of the '{@link org.melanee.ocl2.models.definition.constraint.Constraint#getSeverity <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Severity</em>' attribute.
	 * @see org.melanee.ocl2.models.definition.constraint.Severity
	 * @see #getSeverity()
	 * @generated
	 */
	void setSeverity(Severity value);

	/**
	 * Returns the value of the '<em><b>Level</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Level</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level</em>' containment reference.
	 * @see #setLevel(Level)
	 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getConstraint_Level()
	 * @model containment="true"
	 * @generated
	 */
	Level getLevel();

	/**
	 * Sets the value of the '{@link org.melanee.ocl2.models.definition.constraint.Constraint#getLevel <em>Level</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level</em>' containment reference.
	 * @see #getLevel()
	 * @generated
	 */
	void setLevel(Level value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='String result = \"\";\n\t\tfor (Expression e : this.getExpression()) {\n\t\t\tif (e instanceof org.melanee.ocl2.models.definition.constraint.Pointer) {\n\t\t\t\tresult += ((org.melanee.ocl2.models.definition.constraint.Pointer) e).getPointer().getName();\n\t\t\t}\n\t\t\tif (e instanceof org.melanee.ocl2.models.definition.constraint.Text) {\n\t\t\t\tresult += ((org.melanee.ocl2.models.definition.constraint.Text) e).getText();\n\t\t\t}\n\t\t}\n\t\treturn result;'"
	 * @generated
	 */
	String getText();

} // Constraint
