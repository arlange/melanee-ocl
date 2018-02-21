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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Level</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.melanee.ocl2.models.definition.constraint.Level#getStartLevel
 * <em>Start Level</em>}</li>
 * <li>{@link org.melanee.ocl2.models.definition.constraint.Level#getEndLevel
 * <em>End Level</em>}</li>
 * </ul>
 *
 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getLevel()
 * @model
 * @generated
 */
public interface Level extends EObject {
  /**
   * Returns the value of the '<em><b>Start Level</b></em>' attribute. <!--
   * begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start Level</em>' attribute isn't clear, there
   * really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Start Level</em>' attribute.
   * @see #setStartLevel(int)
   * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getLevel_StartLevel()
   * @model
   * @generated
   */
  int getStartLevel();

  /**
   * Sets the value of the
   * '{@link org.melanee.ocl2.models.definition.constraint.Level#getStartLevel
   * <em>Start Level</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @param value
   *          the new value of the '<em>Start Level</em>' attribute.
   * @see #getStartLevel()
   * @generated
   */
  void setStartLevel(int value);

  /**
   * Returns the value of the '<em><b>End Level</b></em>' attribute. <!--
   * begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End Level</em>' attribute isn't clear, there
   * really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>End Level</em>' attribute.
   * @see #setEndLevel(int)
   * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage#getLevel_EndLevel()
   * @model
   * @generated
   */
  int getEndLevel();

  /**
   * Sets the value of the
   * '{@link org.melanee.ocl2.models.definition.constraint.Level#getEndLevel
   * <em>End Level</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>End Level</em>' attribute.
   * @see #getEndLevel()
   * @generated
   */
  void setEndLevel(int value);

} // Level
