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
package org.melanee.ocl.service.ocl.lml;

import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;

/**
 * The deep OCL NavigationAttribute interface
 * @author Dominik Kantner
 */
public interface NavigationAttribute extends Attribute{
	
	/**
	 * @param clabject
	 */
	public void setClabject(Clabject clabject);
	
	/**
	 * @param clabject
	 */
	public void setNavigationValue(Clabject clabject);
	
	/**
	 * @param single
	 */
	public void setSingle(boolean single);
	
	/**
	 * @return
	 */
	public boolean isSingle();
	
	/**
	 * @param connection
	 */
	public void setConnection (Connection connection);
	
	/**
	 * @return
	 */
	public Connection getConnection();
	
	/**
	 * @return
	 */
	public Clabject getNavigationValue();
	
		

	
}
