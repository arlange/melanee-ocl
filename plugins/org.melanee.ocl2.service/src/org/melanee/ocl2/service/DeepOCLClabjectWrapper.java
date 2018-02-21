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
package org.melanee.ocl2.service;

import java.util.Collection;

import org.melanee.core.models.plm.PLM.Element;
import org.melanee.ocl2.service.exception.NavigationException;

public interface DeepOCLClabjectWrapper {

  Object navigate(String target) throws NavigationException;

  Boolean oclIsTypeOf(String target);

  Boolean oclIsKindOf(String target);

  Integer oclIntegerMax(int i1, int i2);

  Integer oclIntegerMin(int i1, int i2);

  Object invoke(String operation, Object[] args) throws Exception;

}
