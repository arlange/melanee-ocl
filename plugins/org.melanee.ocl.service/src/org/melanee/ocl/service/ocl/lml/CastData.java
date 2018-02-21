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

import org.melanee.core.models.plm.PLM.Clabject;

/**
 * This class helps to store the cast info when perfoming a levelcast
 * 
 * @author Dominik Kantner
 *
 */
public class CastData {

  /**
   * stores the source of the cast
   */
  private Clabject source;

  /**
   * stores the destination of the cast
   */
  private Clabject destination;

  /**
   * @return the source of the levelcast
   */
  public Clabject getSource() {
    return source;
  }

  /**
   * @param source
   */
  public void setSource(Clabject source) {
    this.source = source;
  }

  /**
   * @return the destination of the levelcast
   */
  public Clabject getDestination() {
    return destination;
  }

  /**
   * @param destination
   */
  public void setDestination(Clabject destination) {
    this.destination = destination;
  }

}
