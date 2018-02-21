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

import org.eclipse.ui.console.AbstractConsole;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.part.IPageBookViewPage;

/**
 * The LMLOCLConsole class
 * 
 * @author Dominik Kantner
 *
 */
public class LMLOCLConsole extends AbstractConsole {

  private static LMLOCLConsole instance;
  private LMLOCLConsolePage page;

  /**
   * Initializes me.
   */
  private LMLOCLConsole() {
    super("DeepOCL", null); //$NON-NLS-1$
  }

  /**
   * Obtains the singleton instance. It is created, if necessary.
   * 
   * @return the singleton console instance
   */
  public static LMLOCLConsole getInstance() {
    if (instance == null) {
      instance = new LMLOCLConsole();
      ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { instance });
    }

    return instance;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.console.IConsole#createPage(org.eclipse.ui.console.
   * IConsoleView)
   */
  public IPageBookViewPage createPage(IConsoleView view) {
    page = new LMLOCLConsolePage();
    return page;
  }

  /**
   * Closes me and clears the singleton instance reference, so that it will be
   * reinitialized when another console is requested.
   */
  void close() {
    try {
      ConsolePlugin.getDefault().getConsoleManager().removeConsoles(new IConsole[] { this });
      dispose();
    } finally {
      instance = null;
    }
  }

}
