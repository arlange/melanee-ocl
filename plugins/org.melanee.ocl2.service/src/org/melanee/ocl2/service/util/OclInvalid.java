/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.service.util;

/**
 * 
 * @author Arne Lange
 *
 */
public class OclInvalid {

	private String value;
	private String message;

	public OclInvalid() {
		this.value = "oclInvalid";
	}

	public OclInvalid(String message) {
		this.message = message;
	}

	public String getValue() {
		return this.value;
	}

	public String getMessage() {
		return this.message;
	}
}
