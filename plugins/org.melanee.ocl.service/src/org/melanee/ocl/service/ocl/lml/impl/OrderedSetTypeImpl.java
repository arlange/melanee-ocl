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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.expressions.CollectionKind;
import org.melanee.ocl.service.ocl.lml.OrderedSetType;

/**
 * The OrderedSetTypeImpl class
 * @author Dominik Kantner
 *
 */
public class OrderedSetTypeImpl extends CollectionTypeImpl implements
		OrderedSetType {
	
	/**
	 * Constructor
	 * @param elementType
	 */
	public OrderedSetTypeImpl(EObject elementType) {
		super(elementType);
		if(elementType instanceof EClass){
			String elemName=((EClass)elementType).getName();
			this.name="OrderedSet" + "("+elemName+")";
		}
		else{
			this.name="OrderedSet" + "()";
		}
	}
	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.CollectionTypeImpl#getKind()
	 */
	public CollectionKind getKind() {
		return CollectionKind.ORDERED_SET_LITERAL;
	}
}
