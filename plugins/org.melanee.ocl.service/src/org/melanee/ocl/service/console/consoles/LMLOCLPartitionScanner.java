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

import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * The LMLOCLPartitionScanner class
 * 
 * @author Dominik Kantner
 *
 */

public class LMLOCLPartitionScanner extends RuleBasedPartitionScanner {

  public static final String COMMENT = "__ocl_comment"; //$NON-NLS-1$

  /**
   * Constructor
   */
  LMLOCLPartitionScanner() {
    super();

    List<IRule> rules = new java.util.ArrayList<IRule>();

    IToken commentToken = new Token(COMMENT);

    // rule for single-line comments
    rules.add(new EndOfLineRule("--", commentToken)); //$NON-NLS-1$

    // rule for paragraph comments
    rules.add(new MultiLineRule("/*", "*/", commentToken)); //$NON-NLS-1$ //$NON-NLS-2$

    setPredicateRules(rules.toArray(new IPredicateRule[rules.size()]));
  }
}
