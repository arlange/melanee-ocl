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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.examples.interpreter.console.IOCLFactory;
import org.eclipse.ocl.examples.interpreter.console.ModelingLevel;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.helper.Choice;
import org.eclipse.ocl.helper.ConstraintKind;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.utilities.OCLFactory;
import org.eclipse.ocl.utilities.UMLReflection;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.ocl.service.ocl.lml.Constraint;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironmentFactory;

/**
 * The LMLOCLDocument class
 * @author Dominik Kantner
 *
 */
public class LMLOCLDocument extends Document {

    private EObject context;
	private Map<String, EClassifier> parameters;
    private IOCLFactory<Object> oclFactory;
    private ModelingLevel level;
    

	/**
	 * Constructor
	 * @param initialContent
	 */
	public LMLOCLDocument(String initialContent) {
		super(initialContent);

		IDocumentPartitioner partitioner = new FastPartitioner(
			new LMLOCLPartitionScanner(),
			new String[]{LMLOCLPartitionScanner.COMMENT});

		partitioner.connect(this);
		setDocumentPartitioner(partitioner);
	}

    /**
     * Constructor
     */
    public LMLOCLDocument() {
    	this(null);
    }
    
    /**
     * @param context
     */
    public void setOCLContext(EObject context) {
        this.context = context;
    }
    
    /**
     * @return The context
     */
    public EObject getOCLContext() {
        return context;
    }
    
	
	/**
	 * @param parameters
	 */
	public void setOCLParameters(Map<String, EClassifier> parameters) {
		this.parameters = parameters;
	}

	
	/**
	 * @return The OCLParameters
	 */
	public Map<String, EClassifier> getOCLParameters() {
		return parameters;
	}

	/**
	 * @param factory
	 */
	public void setOCLFactory(IOCLFactory<Object> factory) {
        this.oclFactory = factory;
    }
    
    /**
     * @return The OCLFactory
     */
    public IOCLFactory<Object> getOCLFactory() {
        return oclFactory;
    }
    
    /**
     * @param level
     */
    public void setModelingLevel(ModelingLevel level) {
        this.level = level;
    }
    
    /**
     * @return A ModelingLevel
     */
    public ModelingLevel getModelingLevel() {
        return level;
    }
    
    /**
     * This method returns the completion choices
     * @param offset
     * @return
     */
    List<Choice> getOCLChoices(int offset) {
        if (context == null) {
            return Collections.emptyList();
        }
        
        try {
            String text = get(0, offset);
            
            OCL<EPackage,EObject,EObject,EObject,Enumeration,EObject,EObject,CallOperationAction,SendSignalAction,Constraint,EObject,EObject> ocl =  OCL.newInstance(new DeepOCLEnvironmentFactory());
            OCLHelper<EObject, EObject, EObject, Constraint> helper =  ocl.createOCLHelper();
            
			if (parameters != null) {
				// create variables with specified names and types
				@SuppressWarnings("unchecked")
				Environment<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint, EObject, EObject> environment =  (Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject>) helper
					.getEnvironment();
				OCLFactory oclFactory = environment.getOCLFactory();
				UMLReflection<EPackage,EObject,EObject,EObject,Enumeration,EObject,EObject,CallOperationAction,SendSignalAction,Constraint> umlReflection = environment
					.getUMLReflection();

				for (Map.Entry<String, EClassifier> entry : parameters
					.entrySet()) {
					Variable<EObject, EObject> variable = oclFactory
						.createVariable();
					variable.setName(entry.getKey());
					variable
						.setType(umlReflection.getOCLType(entry.getValue()));

					environment.addElement(entry.getKey(), variable, true);
				}
			}

			helper.setContext(context);
            return helper.getSyntaxHelp(
            	ConstraintKind.INVARIANT,
                text);
        } catch (Exception e) {
            // just don't provide proposals
            return Collections.emptyList();
        }
    }
}
