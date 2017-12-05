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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.examples.interpreter.OCLExamplePlugin;
import org.eclipse.ocl.examples.interpreter.console.IOCLFactory;
import org.eclipse.ocl.examples.interpreter.console.ModelingLevel;
import org.eclipse.ocl.examples.interpreter.console.TargetMetamodel;
import org.eclipse.ocl.examples.interpreter.console.text.ColorManager;
import org.eclipse.ocl.examples.interpreter.internal.l10n.OCLInterpreterMessages;
import org.eclipse.ocl.types.TupleType;
import org.eclipse.ocl.util.Tuple;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.Page;
import org.melanee.core.workbench.ExtensionPointService;
import org.melanee.core.workbench.interfaces.IConstraintLanguageService;
import org.melanee.ocl.service.ocl.lml.internal.DeepOCLStandardLibraryImpl;

/**
 * The LMLOCLConsolePage class
 * 
 * @author Dominik Kantner
 *
 */
@SuppressWarnings("restriction")
public class LMLOCLConsolePage extends Page {
	private Composite page;

	private ITextViewer output;
	private LMLOCLSourceViewer input;
	private LMLOCLDocument document;
	private ColorManager colorManager;
	private IOCLFactory<Object> oclFactory = new ClabjectOCLFactory();
	private EObject context;
	private ModelingLevel modelingLevel = ModelingLevel.M2;
	private ISelectionService selectionService;
	private ISelectionListener selectionListener;

	private static final AdapterFactory reflectiveAdapterFactory = new ReflectiveItemProviderAdapterFactory();

	private static final AdapterFactory defaultAdapterFactory = new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	

	public IItemLabelProvider tupleTypeLabelProvider = new IItemLabelProvider() {

		public Object getImage(Object object) {
			return null;
		}

		public String getText(Object object) {
			@SuppressWarnings("unchecked")
			Tuple<?, Object> tuple = (Tuple<?, Object>) object;
			TupleType<?, ?> tupleType = tuple.getTupleType();

			StringBuilder result = new StringBuilder();
			result.append("Tuple{");//$NON-NLS-1$

			for (Iterator<?> iter = tupleType.oclProperties().iterator(); iter
					.hasNext();) {

				Object next = iter.next();

				result.append(oclFactory.getName(next));
				result.append(" = "); //$NON-NLS-1$
				result.append(LMLOCLConsolePage.this.toString(tuple
						.getValue(next)));

				if (iter.hasNext()) {
					result.append(", "); //$NON-NLS-1$
				}
			}

			result.append('}');

			return result.toString();
		}
	};

	/**
	 * Constructor
	 */
	LMLOCLConsolePage() {
		super();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.Page#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {

		page = new SashForm(parent, SWT.VERTICAL | SWT.LEFT_TO_RIGHT);

		output = new TextViewer(page, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
				| SWT.H_SCROLL);
		output.getTextWidget().setLayoutData(new GridData(GridData.FILL_BOTH));
		output.getTextWidget().setFont(
				JFaceResources.getFont(JFaceResources.TEXT_FONT));
		output.setEditable(false);
		output.setDocument(new Document());

		colorManager = new ColorManager();
		document = new LMLOCLDocument();
		document.setOCLFactory(oclFactory);
		document.setModelingLevel(modelingLevel);

		input = new LMLOCLSourceViewer(page, colorManager, SWT.BORDER
				| SWT.MULTI);
		input.setDocument(document);
		input.getTextWidget().addKeyListener(new InputKeyListener());

		selectionListener = new ISelectionListener() {
			public void selectionChanged(IWorkbenchPart part,
					ISelection selection) {
				LMLOCLConsolePage.this.selectionChanged(selection);
			}
		};
		selectionService = getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addPostSelectionListener(selectionListener);

		ISelection selection = selectionService.getSelection();

		selectionChanged(selection);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#getControl()
	 */
	@Override
	public Control getControl() {

		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#setFocus()
	 */
	@Override
	public void setFocus() {
		input.getTextWidget().setFocus();

	}

	/**
	 * private class ClabjectOCLFactory
	 * 
	 * @author Dominik Kantner
	 *
	 */
	private class ClabjectOCLFactory implements IOCLFactory<Object> {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ocl.examples.interpreter.console.IOCLFactory#
		 * getTargetMetamodel()
		 */
		public TargetMetamodel getTargetMetamodel() {
			return TargetMetamodel.Ecore;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ocl.examples.interpreter.console.IOCLFactory#
		 * getContextClassifier(org.eclipse.emf.ecore.EObject)
		 */
		public Object getContextClassifier(EObject object) {
			return context.eClass();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.ocl.examples.interpreter.console.IOCLFactory#getName(
		 * java.lang.Object)
		 */
		public String getName(Object modelElement) {
			return ((ENamedElement) modelElement).getName();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.ocl.examples.interpreter.console.IOCLFactory#createOCL
		 * (org.eclipse.ocl.examples.interpreter.console.ModelingLevel)
		 */
		@Override
		public <PK, O, P, EL, PM, S, COA, SSA, CT, CLS, E> OCL<PK, Object, O, P, EL, PM, S, COA, SSA, CT, CLS, E> createOCL(
				ModelingLevel level) {
			// TODO Auto-generated method stub
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.ocl.examples.interpreter.console.IOCLFactory#createOCL
		 * (org.eclipse.ocl.examples.interpreter.console.ModelingLevel,
		 * org.eclipse.emf.ecore.resource.Resource)
		 */
		@Override
		public <PK, O, P, EL, PM, S, COA, SSA, CT, CLS, E> OCL<PK, Object, O, P, EL, PM, S, COA, SSA, CT, CLS, E> createOCL(
				ModelingLevel level, Resource res) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/**
	 * @param expression
	 * @return The evaluation of the expression
	 */
	boolean evaluate(String expression) {
		boolean result = true;
		if (context == null) {
			result = false;

		} else {
			Color outputDefault = colorManager.getColor(ColorManager.DEFAULT);
			Color outputResults = colorManager
					.getColor(ColorManager.OUTPUT_RESULTS);

			IDocument doc = getDocument();

			if (doc.getLength() > 0) {

				append("", outputDefault, false); //$NON-NLS-1$
			}

			append("Evaluating", outputDefault, true);
			append(expression, outputDefault, false);
			append("Results", outputDefault, true);

			try {

				IConstraintLanguageService constraintLanguageService = ExtensionPointService
						.Instance().getActiveConstraintLanguageService();

				if (constraintLanguageService == null) {
					append("No constraint language service available. Please install one via the PluginManager or enable in Melanee preferences.", outputDefault, false);
					return false;
				}

				print(constraintLanguageService.evaluate(context, expression),
						outputResults, false);
			} catch (Exception e) {
				result = false;
				error((e.getLocalizedMessage() == null) ? e.getClass()
						.getName() : e.getLocalizedMessage());
				e.printStackTrace();
			}
			

		}

		return result;
	}

	/**
	 * @return The IDocument
	 */
	private IDocument getDocument() {
		return output.getDocument();
	}

	/**
	 * appends text to the output
	 * @param text
	 * @param color
	 * @param bold
	 */
	private void append(String text, Color color, boolean bold) {

		IDocument doc = getDocument();
		try {
			int offset = doc.getLength();
			int length = text.length();

			text = text + '\n';

			if (offset > 0) {
				doc.replace(offset, 0, text);
			} else {
				doc.set(text);
			}

			StyleRange style = new StyleRange();
			style.start = offset;
			style.length = length;
			style.foreground = color;

			if (bold) {
				style.fontStyle = SWT.BOLD;
			}

			output.getTextWidget().setStyleRange(style);
		} catch (BadLocationException e) {
			IStatus status = new Status(IStatus.ERROR,
					OCLExamplePlugin.getPluginId(), 1,
					OCLInterpreterMessages.console_outputExc, e);

			OCLExamplePlugin.getDefault().getLog().log(status);
		}
	}

	/**
	 * @param object
	 * @param color
	 * @param bold
	 */
	private void print(Object object, Color color, boolean bold) {
		Collection<?> toPrint;

		if (object == null) {
			toPrint = Collections.EMPTY_SET;
		} else if (object instanceof Collection) {
			toPrint = (Collection<?>) object;
		} else if (object.getClass().isArray()) {
			toPrint = Arrays.asList((Object[]) object);
		} else {
			toPrint = Collections.singleton(object);
		}

		for (Iterator<?> iter = toPrint.iterator(); iter.hasNext();) {
			append(toString(iter.next()), color, bold);
		}

		scrollText();

	}

	/**
	 * @param message
	 */
	private void error(String message) {
		append(message, colorManager.getColor(ColorManager.OUTPUT_ERROR), false);
		scrollText();
	}

	/**
	 * Ensures that the last text printed to the output viewer is shown.
	 */
	private void scrollText() {
		output.revealRange(getDocument().getLength(), 0);
	}

	/**
	 * @param object
	 * @return Object as String
	 */
	String toString(Object object) {
		if (DeepOCLStandardLibraryImpl.INSTANCE.getInvalid() == object) {
			return "OclInvalid"; //$NON-NLS-1$
		}
		if (object instanceof String) {
			return "'" + object + "'"; //$NON-NLS-1$//$NON-NLS-2$
		} else if (object instanceof Tuple) {
			return tupleTypeLabelProvider.getText(object);
		} else if (object instanceof EObject) {
			EObject eObject = (EObject) object;

			IItemLabelProvider labeler = (IItemLabelProvider) defaultAdapterFactory
					.adapt(eObject, IItemLabelProvider.class);

			if (labeler == null) {
				labeler = (IItemLabelProvider) reflectiveAdapterFactory.adapt(
						eObject, IItemLabelProvider.class);
			}

			if (labeler != null) {
				return labeler.getText(object);
			}
		}

		return String.valueOf(object);
	}

	/**
	 * listens if selction is changed
	 * @param sel
	 */
	private void selectionChanged(ISelection sel) {
		if (sel instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) sel;

			if (!ssel.isEmpty()) {
				Object selected = ssel.getFirstElement();

				if (selected instanceof EObject) {
					context = (EObject) selected;
				} else if (selected instanceof IAdaptable) {
					context = (EObject) ((IAdaptable) selected)
							.getAdapter(EObject.class);
				}

				document.setOCLContext(context);
			}
		}

	}

	/**
	 * private class InputKeyListenr
	 * @author Dominik Kantner
	 *
	 */
	private class InputKeyListener implements KeyListener {
		private boolean evaluationSuccess = false;
		private List<String> history = new ArrayList<String>();
		private int historyPointer;
	
		public void keyPressed(KeyEvent e) {
			switch (e.keyCode) {
			case SWT.CR:
				if (!input.isContentAssistActive()
						&& (e.stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {
					String text = document.get();

					evaluationSuccess = evaluate(text.trim());
					history.add(text.trim());
					historyPointer = history.size();
					
					//Scroll to end
					output.getTextWidget().setTopIndex(output.getTextWidget().getLineCount() - 1);
				}
				break;
				
			case SWT.PAGE_UP :
				if (!input.isContentAssistActive()
						&& (e.stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {					
					//history
					if (historyPointer <= history.size() && historyPointer > 0) {
						historyPointer--;
						setTextFromHistory();
					}
				}				
				break;
			case SWT.PAGE_DOWN :
				if (!input.isContentAssistActive()
						&& (e.stateMask & (SWT.CTRL | SWT.SHIFT)) == 0) {					
					// history
					if (historyPointer >= 0 && historyPointer <= history.size()-1) {
						historyPointer++;
						setTextFromHistory();
						}
				}				
				break;
			}
		}
		

		public void keyReleased(KeyEvent e) {
			switch (e.keyCode) {
			case SWT.CR:
				if ((e.stateMask & SWT.CTRL) == 0) {
					if (evaluationSuccess) {
						document.set(""); //$NON-NLS-1$
						
					}
					evaluationSuccess = false;
				}
				break;
			case ' ':
				if ((e.stateMask & SWT.CTRL) == SWT.CTRL) {
					input.getContentAssistant().showPossibleCompletions();
				}
			}
		}

		/**
		 * Shows the history commands or nothing in the console
		 * @author Nicole Wesemeyer
		 */
		protected void setTextFromHistory() {
			if(historyPointer >= 0 && historyPointer < history.size()){
				String newText = history.get(historyPointer);
				document.set(newText);
				input.setSelectedRange(newText.length(), 0);
			}
			else{
				String newText = "";
				document.set(newText);
				input.setSelectedRange(newText.length(), 0);
			}
		}
	}
}