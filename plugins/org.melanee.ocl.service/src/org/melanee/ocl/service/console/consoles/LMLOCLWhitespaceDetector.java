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
package org.melanee.ocl.service.console.consoles;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * The LMLOCLWhitespaceDetector class
 * 
 * @author Dominik Kantner
 *
 */
public class LMLOCLWhitespaceDetector implements IWhitespaceDetector {

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
   */
  public boolean isWhitespace(char c) {
    switch (c) {
    case ' ':
    case '\t':
    case '\n':
    case '\r':
      return true;
    default:
      return false;
    }
  }

}
