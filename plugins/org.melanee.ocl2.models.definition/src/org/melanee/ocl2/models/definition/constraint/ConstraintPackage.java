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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.melanee.core.models.plm.PLM.PLMPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.melanee.ocl2.models.definition.constraint.ConstraintFactory
 * @model kind="package"
 * @generated
 */
public interface ConstraintPackage extends EPackage {
  /**
   * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNAME = "constraint";

  /**
   * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_URI = "http://melanee.org/Constraint";

  /**
   * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_PREFIX = "constraint";

  /**
   * The singleton instance of the package. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  ConstraintPackage eINSTANCE = org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl
      .init();

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.ConstraintImpl
   * <em>Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getConstraint()
   * @generated
   */
  int CONSTRAINT = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CONSTRAINT__NAME = PLMPackage.ABSTRACT_CONSTRAINT__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CONSTRAINT__EXPRESSION = PLMPackage.ABSTRACT_CONSTRAINT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CONSTRAINT__MESSAGE = PLMPackage.ABSTRACT_CONSTRAINT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CONSTRAINT__SEVERITY = PLMPackage.ABSTRACT_CONSTRAINT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CONSTRAINT__LEVEL = PLMPackage.ABSTRACT_CONSTRAINT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CONSTRAINT_FEATURE_COUNT = PLMPackage.ABSTRACT_CONSTRAINT_FEATURE_COUNT + 4;

  /**
   * The operation id for the '<em>Get Text</em>' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CONSTRAINT___GET_TEXT = PLMPackage.ABSTRACT_CONSTRAINT_OPERATION_COUNT + 0;

  /**
   * The number of operations of the '<em>Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int CONSTRAINT_OPERATION_COUNT = PLMPackage.ABSTRACT_CONSTRAINT_OPERATION_COUNT + 1;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.DeriveConstraintImpl
   * <em>Derive Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.DeriveConstraintImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getDeriveConstraint()
   * @generated
   */
  int DERIVE_CONSTRAINT = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DERIVE_CONSTRAINT__NAME = CONSTRAINT__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DERIVE_CONSTRAINT__EXPRESSION = CONSTRAINT__EXPRESSION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DERIVE_CONSTRAINT__MESSAGE = CONSTRAINT__MESSAGE;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DERIVE_CONSTRAINT__SEVERITY = CONSTRAINT__SEVERITY;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DERIVE_CONSTRAINT__LEVEL = CONSTRAINT__LEVEL;

  /**
   * The number of structural features of the '<em>Derive Constraint</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DERIVE_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Text</em>' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DERIVE_CONSTRAINT___GET_TEXT = CONSTRAINT___GET_TEXT;

  /**
   * The number of operations of the '<em>Derive Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DERIVE_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.PostConstraintImpl
   * <em>Post Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.PostConstraintImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getPostConstraint()
   * @generated
   */
  int POST_CONSTRAINT = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POST_CONSTRAINT__NAME = CONSTRAINT__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POST_CONSTRAINT__EXPRESSION = CONSTRAINT__EXPRESSION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POST_CONSTRAINT__MESSAGE = CONSTRAINT__MESSAGE;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POST_CONSTRAINT__SEVERITY = CONSTRAINT__SEVERITY;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POST_CONSTRAINT__LEVEL = CONSTRAINT__LEVEL;

  /**
   * The number of structural features of the '<em>Post Constraint</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POST_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Text</em>' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POST_CONSTRAINT___GET_TEXT = CONSTRAINT___GET_TEXT;

  /**
   * The number of operations of the '<em>Post Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POST_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.PreConstraintImpl
   * <em>Pre Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.PreConstraintImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getPreConstraint()
   * @generated
   */
  int PRE_CONSTRAINT = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PRE_CONSTRAINT__NAME = CONSTRAINT__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PRE_CONSTRAINT__EXPRESSION = CONSTRAINT__EXPRESSION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PRE_CONSTRAINT__MESSAGE = CONSTRAINT__MESSAGE;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PRE_CONSTRAINT__SEVERITY = CONSTRAINT__SEVERITY;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PRE_CONSTRAINT__LEVEL = CONSTRAINT__LEVEL;

  /**
   * The number of structural features of the '<em>Pre Constraint</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PRE_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Text</em>' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PRE_CONSTRAINT___GET_TEXT = CONSTRAINT___GET_TEXT;

  /**
   * The number of operations of the '<em>Pre Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PRE_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.DefinitionConstraintImpl
   * <em>Definition Constraint</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.DefinitionConstraintImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getDefinitionConstraint()
   * @generated
   */
  int DEFINITION_CONSTRAINT = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DEFINITION_CONSTRAINT__NAME = CONSTRAINT__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DEFINITION_CONSTRAINT__EXPRESSION = CONSTRAINT__EXPRESSION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DEFINITION_CONSTRAINT__MESSAGE = CONSTRAINT__MESSAGE;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DEFINITION_CONSTRAINT__SEVERITY = CONSTRAINT__SEVERITY;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DEFINITION_CONSTRAINT__LEVEL = CONSTRAINT__LEVEL;

  /**
   * The number of structural features of the '<em>Definition Constraint</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DEFINITION_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Text</em>' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DEFINITION_CONSTRAINT___GET_TEXT = CONSTRAINT___GET_TEXT;

  /**
   * The number of operations of the '<em>Definition Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DEFINITION_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.InvariantConstraintImpl
   * <em>Invariant Constraint</em>}' class. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.InvariantConstraintImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getInvariantConstraint()
   * @generated
   */
  int INVARIANT_CONSTRAINT = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INVARIANT_CONSTRAINT__NAME = CONSTRAINT__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INVARIANT_CONSTRAINT__EXPRESSION = CONSTRAINT__EXPRESSION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INVARIANT_CONSTRAINT__MESSAGE = CONSTRAINT__MESSAGE;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INVARIANT_CONSTRAINT__SEVERITY = CONSTRAINT__SEVERITY;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INVARIANT_CONSTRAINT__LEVEL = CONSTRAINT__LEVEL;

  /**
   * The number of structural features of the '<em>Invariant Constraint</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INVARIANT_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Text</em>' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INVARIANT_CONSTRAINT___GET_TEXT = CONSTRAINT___GET_TEXT;

  /**
   * The number of operations of the '<em>Invariant Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INVARIANT_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.BodyConstraintImpl
   * <em>Body Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.BodyConstraintImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getBodyConstraint()
   * @generated
   */
  int BODY_CONSTRAINT = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BODY_CONSTRAINT__NAME = CONSTRAINT__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BODY_CONSTRAINT__EXPRESSION = CONSTRAINT__EXPRESSION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BODY_CONSTRAINT__MESSAGE = CONSTRAINT__MESSAGE;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BODY_CONSTRAINT__SEVERITY = CONSTRAINT__SEVERITY;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BODY_CONSTRAINT__LEVEL = CONSTRAINT__LEVEL;

  /**
   * The number of structural features of the '<em>Body Constraint</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BODY_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Text</em>' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BODY_CONSTRAINT___GET_TEXT = CONSTRAINT___GET_TEXT;

  /**
   * The number of operations of the '<em>Body Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int BODY_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.InitConstraintImpl
   * <em>Init Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.InitConstraintImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getInitConstraint()
   * @generated
   */
  int INIT_CONSTRAINT = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INIT_CONSTRAINT__NAME = CONSTRAINT__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INIT_CONSTRAINT__EXPRESSION = CONSTRAINT__EXPRESSION;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INIT_CONSTRAINT__MESSAGE = CONSTRAINT__MESSAGE;

  /**
   * The feature id for the '<em><b>Severity</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INIT_CONSTRAINT__SEVERITY = CONSTRAINT__SEVERITY;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INIT_CONSTRAINT__LEVEL = CONSTRAINT__LEVEL;

  /**
   * The number of structural features of the '<em>Init Constraint</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INIT_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 0;

  /**
   * The operation id for the '<em>Get Text</em>' operation. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INIT_CONSTRAINT___GET_TEXT = CONSTRAINT___GET_TEXT;

  /**
   * The number of operations of the '<em>Init Constraint</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INIT_CONSTRAINT_OPERATION_COUNT = CONSTRAINT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.ExpressionImpl
   * <em>Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.ExpressionImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 8;

  /**
   * The number of structural features of the '<em>Expression</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The number of operations of the '<em>Expression</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int EXPRESSION_OPERATION_COUNT = 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.TextImpl
   * <em>Text</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.TextImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getText()
   * @generated
   */
  int TEXT = 9;

  /**
   * The feature id for the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int TEXT__TEXT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Text</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int TEXT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Text</em>' class. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int TEXT_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.PointerImpl
   * <em>Pointer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.PointerImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getPointer()
   * @generated
   */
  int POINTER = 10;

  /**
   * The feature id for the '<em><b>Pointer</b></em>' reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POINTER__POINTER = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pointer</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POINTER_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Pointer</em>' class. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int POINTER_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.impl.LevelImpl
   * <em>Level</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.impl.LevelImpl
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getLevel()
   * @generated
   */
  int LEVEL = 11;

  /**
   * The feature id for the '<em><b>Start Level</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LEVEL__START_LEVEL = 0;

  /**
   * The feature id for the '<em><b>End Level</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LEVEL__END_LEVEL = 1;

  /**
   * The number of structural features of the '<em>Level</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LEVEL_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Level</em>' class. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LEVEL_OPERATION_COUNT = 0;

  /**
   * The meta object id for the
   * '{@link org.melanee.ocl2.models.definition.constraint.Severity
   * <em>Severity</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.melanee.ocl2.models.definition.constraint.Severity
   * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getSeverity()
   * @generated
   */
  int SEVERITY = 12;

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.Constraint
   * <em>Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Constraint</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Constraint
   * @generated
   */
  EClass getConstraint();

  /**
   * Returns the meta object for the containment reference list
   * '{@link org.melanee.ocl2.models.definition.constraint.Constraint#getExpression
   * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference list
   *         '<em>Expression</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Constraint#getExpression()
   * @see #getConstraint()
   * @generated
   */
  EReference getConstraint_Expression();

  /**
   * Returns the meta object for the attribute
   * '{@link org.melanee.ocl2.models.definition.constraint.Constraint#getMessage
   * <em>Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Message</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Constraint#getMessage()
   * @see #getConstraint()
   * @generated
   */
  EAttribute getConstraint_Message();

  /**
   * Returns the meta object for the attribute
   * '{@link org.melanee.ocl2.models.definition.constraint.Constraint#getSeverity
   * <em>Severity</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Severity</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Constraint#getSeverity()
   * @see #getConstraint()
   * @generated
   */
  EAttribute getConstraint_Severity();

  /**
   * Returns the meta object for the containment reference
   * '{@link org.melanee.ocl2.models.definition.constraint.Constraint#getLevel
   * <em>Level</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference '<em>Level</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Constraint#getLevel()
   * @see #getConstraint()
   * @generated
   */
  EReference getConstraint_Level();

  /**
   * Returns the meta object for the
   * '{@link org.melanee.ocl2.models.definition.constraint.Constraint#getText()
   * <em>Get Text</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the '<em>Get Text</em>' operation.
   * @see org.melanee.ocl2.models.definition.constraint.Constraint#getText()
   * @generated
   */
  EOperation getConstraint__GetText();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.DeriveConstraint
   * <em>Derive Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Derive Constraint</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.DeriveConstraint
   * @generated
   */
  EClass getDeriveConstraint();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.PostConstraint <em>Post
   * Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Post Constraint</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.PostConstraint
   * @generated
   */
  EClass getPostConstraint();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.PreConstraint <em>Pre
   * Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Pre Constraint</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.PreConstraint
   * @generated
   */
  EClass getPreConstraint();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.DefinitionConstraint
   * <em>Definition Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @return the meta object for class '<em>Definition Constraint</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.DefinitionConstraint
   * @generated
   */
  EClass getDefinitionConstraint();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.InvariantConstraint
   * <em>Invariant Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @return the meta object for class '<em>Invariant Constraint</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.InvariantConstraint
   * @generated
   */
  EClass getInvariantConstraint();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.BodyConstraint <em>Body
   * Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Body Constraint</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.BodyConstraint
   * @generated
   */
  EClass getBodyConstraint();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.InitConstraint <em>Init
   * Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Init Constraint</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.InitConstraint
   * @generated
   */
  EClass getInitConstraint();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.Expression
   * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.Text <em>Text</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Text</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Text
   * @generated
   */
  EClass getText();

  /**
   * Returns the meta object for the attribute
   * '{@link org.melanee.ocl2.models.definition.constraint.Text#getText
   * <em>Text</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Text</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Text#getText()
   * @see #getText()
   * @generated
   */
  EAttribute getText_Text();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.Pointer
   * <em>Pointer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Pointer</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Pointer
   * @generated
   */
  EClass getPointer();

  /**
   * Returns the meta object for the reference
   * '{@link org.melanee.ocl2.models.definition.constraint.Pointer#getPointer
   * <em>Pointer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference '<em>Pointer</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Pointer#getPointer()
   * @see #getPointer()
   * @generated
   */
  EReference getPointer_Pointer();

  /**
   * Returns the meta object for class
   * '{@link org.melanee.ocl2.models.definition.constraint.Level <em>Level</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Level</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Level
   * @generated
   */
  EClass getLevel();

  /**
   * Returns the meta object for the attribute
   * '{@link org.melanee.ocl2.models.definition.constraint.Level#getStartLevel
   * <em>Start Level</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Start Level</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Level#getStartLevel()
   * @see #getLevel()
   * @generated
   */
  EAttribute getLevel_StartLevel();

  /**
   * Returns the meta object for the attribute
   * '{@link org.melanee.ocl2.models.definition.constraint.Level#getEndLevel
   * <em>End Level</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>End Level</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Level#getEndLevel()
   * @see #getLevel()
   * @generated
   */
  EAttribute getLevel_EndLevel();

  /**
   * Returns the meta object for enum
   * '{@link org.melanee.ocl2.models.definition.constraint.Severity
   * <em>Severity</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for enum '<em>Severity</em>'.
   * @see org.melanee.ocl2.models.definition.constraint.Severity
   * @generated
   */
  EEnum getSeverity();

  /**
   * Returns the factory that creates the instances of the model. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ConstraintFactory getConstraintFactory();

  /**
   * <!-- begin-user-doc --> Defines literals for the meta objects that represent
   * <ul>
   * <li>each class,</li>
   * <li>each feature of each class,</li>
   * <li>each operation of each class,</li>
   * <li>each enum,</li>
   * <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.ConstraintImpl
     * <em>Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getConstraint()
     * @generated
     */
    EClass CONSTRAINT = eINSTANCE.getConstraint();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment
     * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference CONSTRAINT__EXPRESSION = eINSTANCE.getConstraint_Expression();

    /**
     * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute CONSTRAINT__MESSAGE = eINSTANCE.getConstraint_Message();

    /**
     * The meta object literal for the '<em><b>Severity</b></em>' attribute feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute CONSTRAINT__SEVERITY = eINSTANCE.getConstraint_Severity();

    /**
     * The meta object literal for the '<em><b>Level</b></em>' containment reference
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference CONSTRAINT__LEVEL = eINSTANCE.getConstraint_Level();

    /**
     * The meta object literal for the '<em><b>Get Text</b></em>' operation. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EOperation CONSTRAINT___GET_TEXT = eINSTANCE.getConstraint__GetText();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.DeriveConstraintImpl
     * <em>Derive Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.DeriveConstraintImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getDeriveConstraint()
     * @generated
     */
    EClass DERIVE_CONSTRAINT = eINSTANCE.getDeriveConstraint();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.PostConstraintImpl
     * <em>Post Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.PostConstraintImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getPostConstraint()
     * @generated
     */
    EClass POST_CONSTRAINT = eINSTANCE.getPostConstraint();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.PreConstraintImpl
     * <em>Pre Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.PreConstraintImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getPreConstraint()
     * @generated
     */
    EClass PRE_CONSTRAINT = eINSTANCE.getPreConstraint();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.DefinitionConstraintImpl
     * <em>Definition Constraint</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.DefinitionConstraintImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getDefinitionConstraint()
     * @generated
     */
    EClass DEFINITION_CONSTRAINT = eINSTANCE.getDefinitionConstraint();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.InvariantConstraintImpl
     * <em>Invariant Constraint</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.InvariantConstraintImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getInvariantConstraint()
     * @generated
     */
    EClass INVARIANT_CONSTRAINT = eINSTANCE.getInvariantConstraint();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.BodyConstraintImpl
     * <em>Body Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.BodyConstraintImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getBodyConstraint()
     * @generated
     */
    EClass BODY_CONSTRAINT = eINSTANCE.getBodyConstraint();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.InitConstraintImpl
     * <em>Init Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.InitConstraintImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getInitConstraint()
     * @generated
     */
    EClass INIT_CONSTRAINT = eINSTANCE.getInitConstraint();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.ExpressionImpl
     * <em>Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.ExpressionImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.TextImpl
     * <em>Text</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.TextImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getText()
     * @generated
     */
    EClass TEXT = eINSTANCE.getText();

    /**
     * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute TEXT__TEXT = eINSTANCE.getText_Text();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.PointerImpl
     * <em>Pointer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.PointerImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getPointer()
     * @generated
     */
    EClass POINTER = eINSTANCE.getPointer();

    /**
     * The meta object literal for the '<em><b>Pointer</b></em>' reference feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EReference POINTER__POINTER = eINSTANCE.getPointer_Pointer();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.impl.LevelImpl
     * <em>Level</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.impl.LevelImpl
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getLevel()
     * @generated
     */
    EClass LEVEL = eINSTANCE.getLevel();

    /**
     * The meta object literal for the '<em><b>Start Level</b></em>' attribute
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute LEVEL__START_LEVEL = eINSTANCE.getLevel_StartLevel();

    /**
     * The meta object literal for the '<em><b>End Level</b></em>' attribute
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    EAttribute LEVEL__END_LEVEL = eINSTANCE.getLevel_EndLevel();

    /**
     * The meta object literal for the
     * '{@link org.melanee.ocl2.models.definition.constraint.Severity
     * <em>Severity</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.melanee.ocl2.models.definition.constraint.Severity
     * @see org.melanee.ocl2.models.definition.constraint.impl.ConstraintPackageImpl#getSeverity()
     * @generated
     */
    EEnum SEVERITY = eINSTANCE.getSeverity();

  }

} // ConstraintPackage
