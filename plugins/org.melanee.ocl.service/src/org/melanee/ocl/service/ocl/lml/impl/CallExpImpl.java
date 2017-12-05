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
import org.eclipse.ocl.expressions.OCLExpression;
import org.melanee.ocl.service.ocl.lml.CallExp;

/**
 * The CallExpImpl class
 * @author Dominik Kantner
 *
 */
public class CallExpImpl extends OCLExpressionImpl implements CallExp {

	/**
	 * stores the source
	 */
	protected OCLExpression<EObject> source;
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.CallExp#getSource()
	 */
	@Override
	public OCLExpression<EObject> getSource() {
		// TODO Auto-generated method stub
		return this.source;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.CallExp#setSource(org.eclipse.ocl.expressions.OCLExpression)
	 */
	@Override
	public void setSource(OCLExpression<EObject> value) {
		// TODO Auto-generated method stub
		this.source=value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.CallingASTNode#getPropertyStartPosition()
	 */
	@Override
	public int getPropertyStartPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.CallingASTNode#setPropertyStartPosition(int)
	 */
	@Override
	public void setPropertyStartPosition(int value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.CallingASTNode#getPropertyEndPosition()
	 */
	@Override
	public int getPropertyEndPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.CallingASTNode#setPropertyEndPosition(int)
	 */
	@Override
	public void setPropertyEndPosition(int value) {
		// TODO Auto-generated method stub
		
	}

}
