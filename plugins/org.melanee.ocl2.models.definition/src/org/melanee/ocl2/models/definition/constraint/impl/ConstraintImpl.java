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

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.melanee.core.models.plm.PLM.impl.AbstractConstraintImpl;

import org.melanee.ocl2.models.definition.constraint.Constraint;
import org.melanee.ocl2.models.definition.constraint.ConstraintPackage;
import org.melanee.ocl2.models.definition.constraint.Expression;
import org.melanee.ocl2.models.definition.constraint.Level;
import org.melanee.ocl2.models.definition.constraint.Severity;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Constraint</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.melanee.ocl2.models.definition.constraint.impl.ConstraintImpl#getExpression
 * <em>Expression</em>}</li>
 * <li>{@link org.melanee.ocl2.models.definition.constraint.impl.ConstraintImpl#getMessage
 * <em>Message</em>}</li>
 * <li>{@link org.melanee.ocl2.models.definition.constraint.impl.ConstraintImpl#getSeverity
 * <em>Severity</em>}</li>
 * <li>{@link org.melanee.ocl2.models.definition.constraint.impl.ConstraintImpl#getLevel
 * <em>Level</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstraintImpl extends AbstractConstraintImpl implements Constraint {
  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}'
   * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected EList<Expression> expression;

  /**
   * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected static final String MESSAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected String message = MESSAGE_EDEFAULT;

  /**
   * The default value of the '{@link #getSeverity() <em>Severity</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getSeverity()
   * @generated
   * @ordered
   */
  protected static final Severity SEVERITY_EDEFAULT = Severity.INFO;

  /**
   * The cached value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getSeverity()
   * @generated
   * @ordered
   */
  protected Severity severity = SEVERITY_EDEFAULT;

  /**
   * The cached value of the '{@link #getLevel() <em>Level</em>}' containment
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getLevel()
   * @generated
   * @ordered
   */
  protected Level level;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ConstraintImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ConstraintPackage.Literals.CONSTRAINT;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EList<Expression> getExpression() {
    if (expression == null) {
      expression = new EObjectContainmentEList<Expression>(Expression.class, this,
          ConstraintPackage.CONSTRAINT__EXPRESSION);
    }
    return expression;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getMessage() {
    return message;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setMessage(String newMessage) {
    String oldMessage = message;
    message = newMessage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.CONSTRAINT__MESSAGE,
          oldMessage, message));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Severity getSeverity() {
    return severity;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setSeverity(Severity newSeverity) {
    Severity oldSeverity = severity;
    severity = newSeverity == null ? SEVERITY_EDEFAULT : newSeverity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.CONSTRAINT__SEVERITY,
          oldSeverity, severity));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Level getLevel() {
    return level;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetLevel(Level newLevel, NotificationChain msgs) {
    Level oldLevel = level;
    level = newLevel;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          ConstraintPackage.CONSTRAINT__LEVEL, oldLevel, newLevel);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setLevel(Level newLevel) {
    if (newLevel != level) {
      NotificationChain msgs = null;
      if (level != null)
        msgs = ((InternalEObject) level).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - ConstraintPackage.CONSTRAINT__LEVEL, null, msgs);
      if (newLevel != null)
        msgs = ((InternalEObject) newLevel).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - ConstraintPackage.CONSTRAINT__LEVEL, null, msgs);
      msgs = basicSetLevel(newLevel, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.CONSTRAINT__LEVEL,
          newLevel, newLevel));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getText() {
    String result = "";
    for (Expression e : this.getExpression()) {
      if (e instanceof org.melanee.ocl2.models.definition.constraint.Pointer) {
        result += ((org.melanee.ocl2.models.definition.constraint.Pointer) e).getPointer()
            .getName();
      }
      if (e instanceof org.melanee.ocl2.models.definition.constraint.Text) {
        result += ((org.melanee.ocl2.models.definition.constraint.Text) e).getText();
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
    case ConstraintPackage.CONSTRAINT__EXPRESSION:
      return ((InternalEList<?>) getExpression()).basicRemove(otherEnd, msgs);
    case ConstraintPackage.CONSTRAINT__LEVEL:
      return basicSetLevel(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case ConstraintPackage.CONSTRAINT__EXPRESSION:
      return getExpression();
    case ConstraintPackage.CONSTRAINT__MESSAGE:
      return getMessage();
    case ConstraintPackage.CONSTRAINT__SEVERITY:
      return getSeverity();
    case ConstraintPackage.CONSTRAINT__LEVEL:
      return getLevel();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case ConstraintPackage.CONSTRAINT__EXPRESSION:
      getExpression().clear();
      getExpression().addAll((Collection<? extends Expression>) newValue);
      return;
    case ConstraintPackage.CONSTRAINT__MESSAGE:
      setMessage((String) newValue);
      return;
    case ConstraintPackage.CONSTRAINT__SEVERITY:
      setSeverity((Severity) newValue);
      return;
    case ConstraintPackage.CONSTRAINT__LEVEL:
      setLevel((Level) newValue);
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
    case ConstraintPackage.CONSTRAINT__EXPRESSION:
      getExpression().clear();
      return;
    case ConstraintPackage.CONSTRAINT__MESSAGE:
      setMessage(MESSAGE_EDEFAULT);
      return;
    case ConstraintPackage.CONSTRAINT__SEVERITY:
      setSeverity(SEVERITY_EDEFAULT);
      return;
    case ConstraintPackage.CONSTRAINT__LEVEL:
      setLevel((Level) null);
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
    case ConstraintPackage.CONSTRAINT__EXPRESSION:
      return expression != null && !expression.isEmpty();
    case ConstraintPackage.CONSTRAINT__MESSAGE:
      return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
    case ConstraintPackage.CONSTRAINT__SEVERITY:
      return severity != SEVERITY_EDEFAULT;
    case ConstraintPackage.CONSTRAINT__LEVEL:
      return level != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
    switch (operationID) {
    case ConstraintPackage.CONSTRAINT___GET_TEXT:
      return getText();
    }
    return super.eInvoke(operationID, arguments);
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
    result.append(" (message: ");
    result.append(message);
    result.append(", severity: ");
    result.append(severity);
    result.append(')');
    return result.toString();
  }

} // ConstraintImpl
