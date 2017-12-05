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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENamedElementImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.ocl.ecore.EcorePackage;
import org.eclipse.ocl.utilities.ExpressionInOCL;
import org.melanee.ocl.service.ocl.lml.Constraint;

/**
 * The ConstraintImpl Class
 * @author Dominik Kantner
 *
 */
public class ConstraintImpl extends ENamedElementImpl implements Constraint{

	
	/**
	 * stores the specification
	 */
	protected ExpressionInOCL<EObject, EObject> specification;
	
	/**
	 * stores a list of constrained Elements
	 */
	protected EList<EModelElement> constrainedElements;
	
	/**
	 * stores the stereotype
	 */
	protected String stereotype="";
	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.Constraint#getSpecification()
	 */
	@Override
	public ExpressionInOCL<EObject, EObject> getSpecification() {
		// TODO Auto-generated method stub
		return this.specification;
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.Constraint#setSpecification(org.eclipse.ocl.utilities.ExpressionInOCL)
	 */
	@Override
	public void setSpecification(ExpressionInOCL<EObject, EObject> value) {
	 this.specification=value;
		
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.Constraint#getConstrainedElements()
	 */
	@Override
	public EList<EModelElement> getConstrainedElements() {
		// TODO Auto-generated method stub
		if (constrainedElements == null) {
			constrainedElements = new EObjectResolvingEList<EModelElement>(
				EModelElement.class, this,
				EcorePackage.CONSTRAINT__CONSTRAINED_ELEMENTS);
		}
		return constrainedElements;

	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.Constraint#getStereotype()
	 */
	@Override
	public String getStereotype() {
		// TODO Auto-generated method stub
		return this.stereotype;
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.Constraint#setStereotype(java.lang.String)
	 */
	@Override
	public void setStereotype(String value) {
		this.stereotype=value;
		
	}

}
