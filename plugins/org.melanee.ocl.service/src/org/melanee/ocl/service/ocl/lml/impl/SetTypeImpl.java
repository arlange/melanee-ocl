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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.expressions.CollectionKind;
import org.melanee.ocl.service.ocl.lml.SetType;

/**
 * The SetTypeImpl class
 * 
 * @author Dominik Kantner
 *
 */
public class SetTypeImpl extends CollectionTypeImpl implements SetType {

  /**
   * Constructor
   * 
   * @param elementType
   */
  public SetTypeImpl(EObject elementType) {
    super(elementType);
    this.name = "Set" + "()";
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.ocl.service.CollectionTypeImpl#getKind()
   */
  public CollectionKind getKind() {
    // TODO Auto-generated method stub
    return CollectionKind.SET_LITERAL;
  }
}
