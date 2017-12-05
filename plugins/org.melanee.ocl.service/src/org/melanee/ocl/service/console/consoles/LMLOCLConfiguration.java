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

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ocl.examples.interpreter.console.text.ColorManager;


/**
 * The LMLOCLConfiguration class
 * @author Dominik Kantner
 *
 */
public class LMLOCLConfiguration extends SourceViewerConfiguration {
    private LMLOCLScanner oclScanner;
    private LMLOCLCommentScanner oclCommentScanner;
    private ColorManager colorManager;

    /**
     * Constructor
     * @param colorManager
     */
    LMLOCLConfiguration(ColorManager colorManager) {
        this.colorManager = colorManager;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getConfiguredContentTypes(org.eclipse.jface.text.source.ISourceViewer)
     */
    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] {
            IDocument.DEFAULT_CONTENT_TYPE,
            LMLOCLPartitionScanner.COMMENT};
    }

    /**
     * @return LMLOCLScanner
     */
    protected LMLOCLScanner getOCLScanner() {
        if (oclScanner == null) {
            oclScanner = new LMLOCLScanner(colorManager);
            oclScanner.setDefaultReturnToken(
                new Token(
                    new TextAttribute(colorManager.getColor(ColorManager.DEFAULT))));
        }
        
        return oclScanner;
    }

    /**
     * @return LMLOCLCommentScanner
     */
    protected LMLOCLCommentScanner getOCLCommentScanner() {
        if (oclCommentScanner == null) {
            oclCommentScanner = new LMLOCLCommentScanner(colorManager);
            oclCommentScanner.setDefaultReturnToken(
                new Token(
                    new TextAttribute(colorManager.getColor(ColorManager.COMMENT))));
        }
        
        return oclCommentScanner;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
     */
    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();

        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getOCLScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        dr = new DefaultDamagerRepairer(getOCLCommentScanner());
        reconciler.setDamager(dr, LMLOCLPartitionScanner.COMMENT);
        reconciler.setRepairer(dr, LMLOCLPartitionScanner.COMMENT);

        return reconciler;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant(org.eclipse.jface.text.source.ISourceViewer)
     */
    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant result = new ContentAssistant();
        
        result.setContentAssistProcessor(new LMLOCLCompletionProcessor(),
            IDocument.DEFAULT_CONTENT_TYPE);
        result.enableAutoActivation(true);
        //result.enablePrefixCompletion(true);
        
        return result;
    }
}

