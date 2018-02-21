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
package org.melanee.ocl2.service.exception;

public class NavigationException extends Exception {
  private static final long serialVersionUID = 1L;

  String exceptionMessage;

  public NavigationException(Throwable cause) {
    super(cause);
  }

  public NavigationException(String message, Throwable cause) {
    super("OCL2 Interpreter Exception\n" + message, cause);
  }

  // Constructor that accepts a message
  public NavigationException(String message) {
    super("OCL2 Interpreter Exception\n" + message);
  }
}
