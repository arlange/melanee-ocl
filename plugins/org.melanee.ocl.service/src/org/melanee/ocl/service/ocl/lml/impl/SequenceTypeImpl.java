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
import org.melanee.ocl.service.ocl.lml.SequenceType;

/**
 * The SequenceTypeImpl Class
 * 
 * @author Dominik Kantner
 *
 */
public class SequenceTypeImpl extends CollectionTypeImpl implements SequenceType {

  /**
   * Constructor
   * 
   * @param elementType
   */
  public SequenceTypeImpl(EObject elementType) {
    super(elementType);
    this.name = "Sequence" + "()";
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.ocl.service.CollectionTypeImpl#getKind()
   */
  public CollectionKind getKind() {
    // TODO Auto-generated method stub
    return CollectionKind.SEQUENCE_LITERAL;
  }
}
