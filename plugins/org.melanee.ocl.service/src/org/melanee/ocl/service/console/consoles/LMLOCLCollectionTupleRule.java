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
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.ocl.examples.interpreter.console.text.ColorManager;
import org.eclipse.swt.SWT;

/**
 * The LMLOCLCollectionTupleRule class
 * @author Dominik Kantner
 *
 */
public class LMLOCLCollectionTupleRule extends WordRule {

    /**
     * stores the collection keywords
     */
    private static final String[] KEYWORDS = {
        "Set", //$NON-NLS-1$
        "OrderedSet", //$NON-NLS-1$
        "Bag", //$NON-NLS-1$
        "Sequence", //$NON-NLS-1$
        "Tuple", //$NON-NLS-1$
    };

    /**
     * stores the braces kinds
     */
    private static final String[] BRACES = {
        "{", //$NON-NLS-1$
        "}", //$NON-NLS-1$
        "}}", //$NON-NLS-1$
        "}}}", //$NON-NLS-1$
    };
    
   
    /**
     * Constructor
     * @param colorManager
     * @param isBraces
     */
    LMLOCLCollectionTupleRule(ColorManager colorManager, boolean isBraces) {
        super(new CollectionTupleDetector(isBraces));
        
        IToken token = new Token(new TextAttribute(
            colorManager.getColor(ColorManager.COLLECTION_TUPLE), null, SWT.BOLD));
        
        if (isBraces) {
            for (String word : BRACES) {
                addWord(word, token);
            }
        } else {
            for (String word : KEYWORDS) {
                addWord(word, token);
            }
        }
    }
    
    /**
     * private class CollectionTupleDetector
     * @author Dominik Kantner
     *
     */
    private static class CollectionTupleDetector implements IWordDetector {
        private final boolean isBraces;
        
        CollectionTupleDetector(boolean isBraces) {
            this.isBraces = isBraces;
        }
        
        public boolean isWordPart(char c) {
            return isBraces? (c == '{') || (c == '}') :
                (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
        }

        public boolean isWordStart(char c) {
            return isWordPart(c);
        }
    }
}

