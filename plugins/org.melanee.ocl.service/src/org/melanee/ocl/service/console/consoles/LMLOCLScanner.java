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

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.ocl.examples.interpreter.console.text.ColorManager;


/**
 * The LMLOCLScanner class
 * @author Dominik Kantner
 *
 */
public class LMLOCLScanner extends RuleBasedScanner {

    /**
     *Constructor
     * @param manager
     */
    LMLOCLScanner(ColorManager manager) {
        super();
        
        IToken literal = new Token(
            new TextAttribute(manager.getColor(ColorManager.LITERAL)));

        IRule[] rules = new IRule[7];

        // Add rule for strings
        rules[0] = new SingleLineRule("'", "'", literal); //$NON-NLS-1$ //$NON-NLS-2$

        // Keyword (and pseudo-keyword) rule
        rules[1] = new LMLOCLKeywordRule();
        
        // Collection and Tuple Literal rules
        rules[2] = new LMLOCLCollectionTupleRule(manager, false);
        rules[3] = new LMLOCLCollectionTupleRule(manager, true);

        // identifier rule
        rules[4] = new LMLOCLIdentifierRule();
        
        // Add a rule for numbers
        rules[5] = new NumberRule(literal);
        
        // Add generic whitespace rule
        rules[6] = new WhitespaceRule(new LMLOCLWhitespaceDetector());
        
        setRules(rules);
    }
}
