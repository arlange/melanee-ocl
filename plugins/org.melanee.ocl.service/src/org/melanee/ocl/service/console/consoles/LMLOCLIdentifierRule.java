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
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;


/**
 * The LMLOCLIdentifierRule class
 * @author Dominik Kantner
 *
 */
public class LMLOCLIdentifierRule extends WordRule {
    
    /**
     * Constuctor
     */
    LMLOCLIdentifierRule() {
        super(new OCLKeywordDetector(), new Token(new TextAttribute(null)));
    }
    
    /**
     * private class OCLKeywordDetector
     * @author Dominik Kantner
     *
     */
    private static class OCLKeywordDetector
        implements IWordDetector {
    
        public boolean isWordPart(char c) {
            return isWordStart(c) || c == '$' || (c >= '0' && c <= '9');
        }
    
        public boolean isWordStart(char c) {
            return c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
        }
    }
}