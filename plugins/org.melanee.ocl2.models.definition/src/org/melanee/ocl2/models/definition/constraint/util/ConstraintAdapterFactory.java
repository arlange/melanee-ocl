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
package org.melanee.ocl2.models.definition.constraint.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.melanee.core.models.plm.PLM.AbstractConstraint;

import org.melanee.ocl2.models.definition.constraint.*;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.melanee.ocl2.models.definition.constraint.ConstraintPackage
 * @generated
 */
public class ConstraintAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static ConstraintPackage modelPackage;

  /**
   * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public ConstraintAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = ConstraintPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object. <!--
   * begin-user-doc --> This implementation returns <code>true</code> if the
   * object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * 
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ConstraintSwitch<Adapter> modelSwitch = new ConstraintSwitch<Adapter>() {
    @Override
    public Adapter caseConstraint(Constraint object) {
      return createConstraintAdapter();
    }

    @Override
    public Adapter caseDeriveConstraint(DeriveConstraint object) {
      return createDeriveConstraintAdapter();
    }

    @Override
    public Adapter casePostConstraint(PostConstraint object) {
      return createPostConstraintAdapter();
    }

    @Override
    public Adapter casePreConstraint(PreConstraint object) {
      return createPreConstraintAdapter();
    }

    @Override
    public Adapter caseDefinitionConstraint(DefinitionConstraint object) {
      return createDefinitionConstraintAdapter();
    }

    @Override
    public Adapter caseInvariantConstraint(InvariantConstraint object) {
      return createInvariantConstraintAdapter();
    }

    @Override
    public Adapter caseBodyConstraint(BodyConstraint object) {
      return createBodyConstraintAdapter();
    }

    @Override
    public Adapter caseInitConstraint(InitConstraint object) {
      return createInitConstraintAdapter();
    }

    @Override
    public Adapter caseExpression(Expression object) {
      return createExpressionAdapter();
    }

    @Override
    public Adapter caseText(Text object) {
      return createTextAdapter();
    }

    @Override
    public Adapter casePointer(Pointer object) {
      return createPointerAdapter();
    }

    @Override
    public Adapter caseLevel(Level object) {
      return createLevelAdapter();
    }

    @Override
    public Adapter caseAbstractConstraint(AbstractConstraint object) {
      return createAbstractConstraintAdapter();
    }

    @Override
    public Adapter defaultCase(EObject object) {
      return createEObjectAdapter();
    }
  };

  /**
   * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param target
   *          the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject) target);
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.Constraint
   * <em>Constraint</em>}'. <!-- begin-user-doc --> This default implementation
   * returns null so that we can easily ignore cases; it's useful to ignore a case
   * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.Constraint
   * @generated
   */
  public Adapter createConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.DeriveConstraint
   * <em>Derive Constraint</em>}'. <!-- begin-user-doc --> This default
   * implementation returns null so that we can easily ignore cases; it's useful
   * to ignore a case when inheritance will catch all the cases anyway. <!--
   * end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.DeriveConstraint
   * @generated
   */
  public Adapter createDeriveConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.PostConstraint <em>Post
   * Constraint</em>}'. <!-- begin-user-doc --> This default implementation
   * returns null so that we can easily ignore cases; it's useful to ignore a case
   * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.PostConstraint
   * @generated
   */
  public Adapter createPostConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.PreConstraint <em>Pre
   * Constraint</em>}'. <!-- begin-user-doc --> This default implementation
   * returns null so that we can easily ignore cases; it's useful to ignore a case
   * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.PreConstraint
   * @generated
   */
  public Adapter createPreConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.DefinitionConstraint
   * <em>Definition Constraint</em>}'. <!-- begin-user-doc --> This default
   * implementation returns null so that we can easily ignore cases; it's useful
   * to ignore a case when inheritance will catch all the cases anyway. <!--
   * end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.DefinitionConstraint
   * @generated
   */
  public Adapter createDefinitionConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.InvariantConstraint
   * <em>Invariant Constraint</em>}'. <!-- begin-user-doc --> This default
   * implementation returns null so that we can easily ignore cases; it's useful
   * to ignore a case when inheritance will catch all the cases anyway. <!--
   * end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.InvariantConstraint
   * @generated
   */
  public Adapter createInvariantConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.BodyConstraint <em>Body
   * Constraint</em>}'. <!-- begin-user-doc --> This default implementation
   * returns null so that we can easily ignore cases; it's useful to ignore a case
   * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.BodyConstraint
   * @generated
   */
  public Adapter createBodyConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.InitConstraint <em>Init
   * Constraint</em>}'. <!-- begin-user-doc --> This default implementation
   * returns null so that we can easily ignore cases; it's useful to ignore a case
   * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.InitConstraint
   * @generated
   */
  public Adapter createInitConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.Expression
   * <em>Expression</em>}'. <!-- begin-user-doc --> This default implementation
   * returns null so that we can easily ignore cases; it's useful to ignore a case
   * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.Expression
   * @generated
   */
  public Adapter createExpressionAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.Text <em>Text</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.Text
   * @generated
   */
  public Adapter createTextAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.Pointer
   * <em>Pointer</em>}'. <!-- begin-user-doc --> This default implementation
   * returns null so that we can easily ignore cases; it's useful to ignore a case
   * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.Pointer
   * @generated
   */
  public Adapter createPointerAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.ocl2.models.definition.constraint.Level <em>Level</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.ocl2.models.definition.constraint.Level
   * @generated
   */
  public Adapter createLevelAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link org.melanee.core.models.plm.PLM.AbstractConstraint <em>Abstract
   * Constraint</em>}'. <!-- begin-user-doc --> This default implementation
   * returns null so that we can easily ignore cases; it's useful to ignore a case
   * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.melanee.core.models.plm.PLM.AbstractConstraint
   * @generated
   */
  public Adapter createAbstractConstraintAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case. <!-- begin-user-doc --> This
   * default implementation returns null. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} // ConstraintAdapterFactory
