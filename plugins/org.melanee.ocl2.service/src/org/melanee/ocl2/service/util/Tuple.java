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
 * Tuple class for the navigation stack, it is a workaround for a normal map. JAVA just provides a HashMap.
 * @author Arne Lange
 *
 * @param <X>
 * @param <Y>
 */
public class Tuple<X, Y> {
	public final X x;
	public final Y y;

	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X getFirst() {
		return this.x;
	}

	public Y getSecond() {
		return this.y;
	}
}