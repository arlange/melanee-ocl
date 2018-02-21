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

import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistantExtension2;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.ocl.examples.interpreter.console.text.ColorManager;

import org.eclipse.swt.widgets.Composite;

/**
 * The LMLOCLSourceViewer class
 * 
 * @author Dominik Kantner
 *
 */
public class LMLOCLSourceViewer extends SourceViewer {
  private long contentAssistLastActive;

  /**
   * Constructor
   * 
   * @param parent
   * @param colorManager
   * @param styles
   */
  public LMLOCLSourceViewer(Composite parent, ColorManager colorManager, int styles) {
    super(parent, null, styles);

    configure(new LMLOCLConfiguration(colorManager));

    ((IContentAssistantExtension2) fContentAssistant)
        .addCompletionListener(new ICompletionListener() {
          public void assistSessionEnded(ContentAssistEvent event) {
            contentAssistLastActive = System.currentTimeMillis();
          }

          public void assistSessionStarted(ContentAssistEvent event) {
            // not interesting
          }

          public void selectionChanged(ICompletionProposal proposal, boolean smartToggle) {
            // not interesting
          }
        });
  }

  /**
   * @return whether content assist is active
   */
  public boolean isContentAssistActive() {
    return System.currentTimeMillis() - contentAssistLastActive < 500L;
  }

  /**
   * @return content assistant
   */
  public IContentAssistant getContentAssistant() {
    return fContentAssistant;
  }
}
