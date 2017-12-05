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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.expressions.Variable;
import org.melanee.ocl.service.ocl.lml.IterateExp;

/**
 * The IterateExpImpl class
 * @author Dominik Kantner
 *
 */
public class IterateExpImpl extends LoopExpImpl implements IterateExp {

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IterateExp#getResult()
	 */
	@Override
	public Variable<EObject, EObject> getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IterateExp#setResult(org.eclipse.ocl.expressions.Variable)
	 */
	@Override
	public void setResult(Variable<EObject, EObject> value) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IterateExp#checkIterateType(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean checkIterateType(DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IterateExp#checkBodyType(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean checkBodyType(DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.expressions.IterateExp#checkResultInit(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 */
	@Override
	public boolean checkResultInit(DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
