/*******************************************************************************
 * Copyright (c) 2012, 2013 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.models.definition.constraint.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.melanee.ocl2.models.definition.constraint.ConstraintPackage;
import org.melanee.ocl2.models.definition.constraint.Level;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Level</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.melanee.ocl2.models.definition.constraint.impl.LevelImpl#getStartLevel
 * <em>Start Level</em>}</li>
 * <li>{@link org.melanee.ocl2.models.definition.constraint.impl.LevelImpl#getEndLevel
 * <em>End Level</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LevelImpl extends MinimalEObjectImpl.Container implements Level {
  /**
   * The default value of the '{@link #getStartLevel() <em>Start Level</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getStartLevel()
   * @generated
   * @ordered
   */
  protected static final int START_LEVEL_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getStartLevel() <em>Start Level</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getStartLevel()
   * @generated
   * @ordered
   */
  protected int startLevel = START_LEVEL_EDEFAULT;

  /**
   * The default value of the '{@link #getEndLevel() <em>End Level</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getEndLevel()
   * @generated
   * @ordered
   */
  protected static final int END_LEVEL_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEndLevel() <em>End Level</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getEndLevel()
   * @generated
   * @ordered
   */
  protected int endLevel = END_LEVEL_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected LevelImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ConstraintPackage.Literals.LEVEL;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public int getStartLevel() {
    return startLevel;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setStartLevel(int newStartLevel) {
    int oldStartLevel = startLevel;
    startLevel = newStartLevel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.LEVEL__START_LEVEL,
          oldStartLevel, startLevel));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public int getEndLevel() {
    return endLevel;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setEndLevel(int newEndLevel) {
    int oldEndLevel = endLevel;
    endLevel = newEndLevel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.LEVEL__END_LEVEL,
          oldEndLevel, endLevel));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case ConstraintPackage.LEVEL__START_LEVEL:
      return getStartLevel();
    case ConstraintPackage.LEVEL__END_LEVEL:
      return getEndLevel();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case ConstraintPackage.LEVEL__START_LEVEL:
      setStartLevel((Integer) newValue);
      return;
    case ConstraintPackage.LEVEL__END_LEVEL:
      setEndLevel((Integer) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case ConstraintPackage.LEVEL__START_LEVEL:
      setStartLevel(START_LEVEL_EDEFAULT);
      return;
    case ConstraintPackage.LEVEL__END_LEVEL:
      setEndLevel(END_LEVEL_EDEFAULT);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case ConstraintPackage.LEVEL__START_LEVEL:
      return startLevel != START_LEVEL_EDEFAULT;
    case ConstraintPackage.LEVEL__END_LEVEL:
      return endLevel != END_LEVEL_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (startLevel: ");
    result.append(startLevel);
    result.append(", endLevel: ");
    result.append(endLevel);
    result.append(')');
    return result.toString();
  }

} // LevelImpl
