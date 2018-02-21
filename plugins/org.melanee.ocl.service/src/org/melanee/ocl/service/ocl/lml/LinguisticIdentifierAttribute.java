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

import org.eclipse.emf.ecore.EClass;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;

/**
 * Represent the linguistic identifier attribute interface
 * 
 * @author Dominik Kantner
 *
 */
public interface LinguisticIdentifierAttribute extends Attribute {

  /**
   * @param clabject
   *          The clabject on which the perspective switch is performed
   */
  public void setClabject(Clabject clabject);

  /**
   * @return The linguistic type
   */
  public EClass getLinguisticType();
}
