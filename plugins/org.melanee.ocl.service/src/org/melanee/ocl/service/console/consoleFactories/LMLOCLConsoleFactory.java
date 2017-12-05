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
package org.melanee.ocl.service.console.consoleFactories;


import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.melanee.ocl.service.console.consoles.LMLOCLConsole;

/**
 * The LMLOCLConsoltFactory class
 * @author Dominik Kantner
 *
 */
public class LMLOCLConsoleFactory implements IConsoleFactory {
	/* (non-Javadoc)
	 * @see org.eclipse.ui.console.IConsoleFactory#openConsole()
	 */
	public void openConsole() {
		LMLOCLConsole console = LMLOCLConsole.getInstance();
		
		IConsoleManager mgr = ConsolePlugin.getDefault().getConsoleManager();
		
		
		// must do this twice due to a bug in the Console API
		mgr.showConsoleView(console);
		
		mgr.showConsoleView(console);
	
	}
}
