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

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EDataTypeImpl;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.util.OCLStandardLibraryUtil;
import org.melanee.ocl.service.ocl.lml.CollectionType;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironmentFactory;

/**
 * The CollectionTypeImpl class
 * 
 * @author Dominik Kantner
 *
 */
public class CollectionTypeImpl extends EDataTypeImpl implements CollectionType {

  /**
   * stores the element type
   */
  protected EObject elementType;

  /**
   * stores a list op operation
   */
  protected EList<EObject> operations;

  /**
   * constructor with same behavior of super class
   */
  public CollectionTypeImpl() {
    super();
  }

  /**
   * Constructor which sets the element type
   * 
   * @param elementType
   */
  protected CollectionTypeImpl(EObject elementType) {
    this();

    this.elementType = elementType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.types.CollectionType#getElementType()
   */
  @Override
  public EObject getElementType() {
    // TODO Auto-generated method stub
    return this.elementType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.types.CollectionType#setElementType(java.lang.Object)
   */
  @Override
  public void setElementType(EObject value) {
    // TODO Auto-generated method stub
    this.elementType = value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.types.CollectionType#getKind()
   */
  @Override
  public CollectionKind getKind() {
    // TODO Auto-generated method stub
    return CollectionKind.COLLECTION_LITERAL;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.types.CollectionType#checkCollectionTypeName(org.eclipse.emf.
   * common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkCollectionTypeName(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ocl.types.CollectionType#checkNoInvalidValues(org.eclipse.emf.
   * common.util.DiagnosticChain, java.util.Map)
   */
  @Override
  public boolean checkNoInvalidValues(DiagnosticChain diagnostics, Map<Object, Object> context) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.types.CollectionType#oclIterators()
   */
  @Override
  public EList<EObject> oclIterators() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.PredefinedType#oclOperations()
   */
  @Override
  public EList<EObject> oclOperations() {
    // TODO Auto-generated method stub
    switch (getKind()) {
    case SET_LITERAL:
      if (operations == null || operations.isEmpty()) {
        operations = ECollections.asEList(OCLStandardLibraryUtil
            .createSetOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
        // create a new factory with new environment , may be later use already existing
        // env
      }
      return operations;
    case ORDERED_SET_LITERAL:
      if (operations == null || operations.isEmpty()) {
        operations = ECollections.asEList(OCLStandardLibraryUtil
            .createOrderedSetOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
        // create a new factory with new environment , may be later use already existing
        // env
      }
      return operations;
    case BAG_LITERAL:
      if (operations == null || operations.isEmpty()) {
        operations = ECollections.asEList(OCLStandardLibraryUtil
            .createBagOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
        // create a new factory with new environment , may be later use already existing
        // env
      }
      return operations;

    case SEQUENCE_LITERAL:
      if (operations == null || operations.isEmpty()) {
        operations = ECollections.asEList(OCLStandardLibraryUtil
            .createSequenceOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
        // create a new factory with new environment , may be later use already existing
        // env
      }
      return operations;

    default:
      if (operations == null || operations.isEmpty()) {
        operations = ECollections.asEList(OCLStandardLibraryUtil
            .createCollectionOperations(new DeepOCLEnvironmentFactory().createEnvironment()));
        // create a new factory with new environment , may be later use already existing
        // env
      }
      return operations;

    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedASTNode#getTypeStartPosition()
   */
  @Override
  public int getTypeStartPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedASTNode#setTypeStartPosition(int)
   */
  @Override
  public void setTypeStartPosition(int value) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedASTNode#getTypeEndPosition()
   */
  @Override
  public int getTypeEndPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.TypedASTNode#setTypeEndPosition(int)
   */
  @Override
  public void setTypeEndPosition(int value) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.ASTNode#getStartPosition()
   */
  @Override
  public int getStartPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.ASTNode#setStartPosition(int)
   */
  @Override
  public void setStartPosition(int value) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.ASTNode#getEndPosition()
   */
  @Override
  public int getEndPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.ASTNode#setEndPosition(int)
   */
  @Override
  public void setEndPosition(int value) {
    // TODO Auto-generated method stub

  }

}
