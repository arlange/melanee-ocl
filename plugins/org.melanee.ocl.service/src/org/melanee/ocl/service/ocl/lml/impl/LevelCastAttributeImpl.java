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


import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.impl.AttributeImpl;
import org.melanee.ocl.service.ocl.lml.LevelCastAttribute;

/**
 * The LevelCastAttributeImpl class
 * @author Dominik Kantner
 *
 */
public class LevelCastAttributeImpl extends AttributeImpl implements LevelCastAttribute  {

	
	/**
	 * stores the Clabject to which the cast is done
	 */
	private Clabject cast;
	
	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.LevelCastAttribute#setCast(org.melanee.core.models.plm.PLM.Clabject)
	 */
	@Override
	public void setCast(Clabject cast) {
		// TODO Auto-generated method stub
		this.cast=cast;
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.LevelCastAttribute#getCast()
	 */
	@Override
	public Clabject getCast() {
		// TODO Auto-generated method stub
		return this.cast;
	}


}
