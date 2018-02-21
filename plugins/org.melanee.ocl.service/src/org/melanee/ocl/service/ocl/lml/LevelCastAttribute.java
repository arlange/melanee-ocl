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

/**
 * Represents the levelcast attribute interface
 * 
 * @author Dominik Kantner
 *
 */
public interface LevelCastAttribute extends Attribute {

  /**
   * @param cast
   *          Set the Clabject to which the levelcast is done
   */
  public void setCast(Clabject cast);

  /**
   * @return the Clabject to which levelcast is done
   */
  public Clabject getCast();
}
