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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.util.OCLStandardLibraryUtil;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.ocl.service.ocl.lml.AnyType;
import org.melanee.ocl.service.ocl.lml.Constraint;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironment;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironmentFactory;
import org.melanee.ocl.service.ocl.lml.util.DeepOCLStandardLibraryUtil;

/**
 * The AnyTypeImpl class
 * 
 * @author Dominik Kantner
 */
public class AnyTypeImpl extends EObjectImpl implements AnyType {

  /**
   * Stores the name
   */
  protected String name;

  /**
   * Stores a list of operations
   */
  protected EList<EObject> operations;

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.PredefinedType#getName()
   */
  @Override
  public String getName() {
    if (name == null) {
      name = SINGLETON_NAME;
    }

    return name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ocl.utilities.PredefinedType#oclOperations()
   */
  @Override
  public EList<EObject> oclOperations() {
    if (operations == null || operations.isEmpty()) {
      Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> lmlEnvironment = new DeepOCLEnvironmentFactory()
          .createEnvironment();
      List<EObject> standardAnyOperations = OCLStandardLibraryUtil
          .createAnyTypeOperations(lmlEnvironment);
      List<EObject> deepAnyOperations = DeepOCLStandardLibraryUtil
          .createAnyTypeOperations((DeepOCLEnvironment) lmlEnvironment);
      List<EObject> allAnyOperations = new ArrayList<EObject>();
      allAnyOperations.addAll(standardAnyOperations);
      allAnyOperations.addAll(deepAnyOperations);
      operations = ECollections.asEList(allAnyOperations);
    }
    return operations;
  }

}
