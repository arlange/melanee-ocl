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
package org.melanee.ocl.service.ocl.lml;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.AbstractEnvironmentFactory;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.EvaluationVisitor;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.parser.AbstractOCLParser;
import org.eclipse.ocl.utilities.Visitor;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.ocl.service.ocl.lml.parser.DeepOCLAnalyzer;
import org.melanee.ocl.service.ocl.lml.parser.ValidationVisitor;

/**
 * The LMLEnvironmentFactory
 * @author Dominik Kantner
 *
 */
public class DeepOCLEnvironmentFactory extends AbstractEnvironmentFactory<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint, EObject, EObject>{

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.EnvironmentFactory#createEnvironment()
	 */
	@Override
	public Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> createEnvironment() {
		DeepOCLEnvironment result = new DeepOCLEnvironment(this,null);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.EnvironmentFactory#loadEnvironment(org.eclipse.emf.ecore.resource.Resource)
	 */
	@Override
	public Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> loadEnvironment(
			Resource resource) {
		DeepOCLEnvironment result = new DeepOCLEnvironment(this,resource);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.EnvironmentFactory#createEnvironment(org.eclipse.ocl.Environment)
	 */
	@Override
	public Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> createEnvironment(
			Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject>parent) {
		if (!(parent instanceof DeepOCLEnvironment)) {
			throw new IllegalArgumentException(
				"Parent environment must be an LML environment: " + parent); //$NON-NLS-1$
		}
		
		DeepOCLEnvironment result = new DeepOCLEnvironment(parent);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.EnvironmentFactory#createEvaluationEnvironment()
	 */
	@Override
	public EvaluationEnvironment<EObject, EObject, EObject, EObject, EObject> createEvaluationEnvironment() {
		return new DeepOCLEvaluationEnvironment(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.EnvironmentFactory#createEvaluationEnvironment(org.eclipse.ocl.EvaluationEnvironment)
	 */
	@Override
	public EvaluationEnvironment<EObject, EObject, EObject, EObject, EObject>  createEvaluationEnvironment(
			EvaluationEnvironment<EObject, EObject, EObject, EObject, EObject> parent) {
		return new DeepOCLEvaluationEnvironment(parent);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractEnvironmentFactory#lookupPackage(java.util.List)
	 */
	@Override
	protected EPackage lookupPackage(List<String> pathname) {
		// TODO Auto-generated method stub
		return null;                
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractEnvironmentFactory#getClassifier(java.lang.Object)
	 */
	@Override
	protected Clabject getClassifier(Object context) {
	
		if(context instanceof Clabject){
			return (Clabject) context;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractEnvironmentFactory#createEvaluationVisitor(org.eclipse.ocl.Environment, org.eclipse.ocl.EvaluationEnvironment, java.util.Map)
	 */
	public EvaluationVisitor<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> createEvaluationVisitor(
			Environment<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint, EObject, EObject> env,
			EvaluationEnvironment<EObject,EObject,EObject,EObject,EObject> evalEnv,
			Map<? extends EObject, ? extends Set<? extends EObject>> extentMap ){
		EvaluationVisitor<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> result = new DeepOCLEvaluationVisitorImpl(
			env, evalEnv, extentMap);

		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractEnvironmentFactory#createOCLAnalyzer(org.eclipse.ocl.Environment, java.lang.String)
	 */
	public DeepOCLAnalyzer createOCLAnalyzer(Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> env, String input){
		return new DeepOCLAnalyzer(env, input);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractEnvironmentFactory#createOCLAnalyzer(org.eclipse.ocl.parser.AbstractOCLParser)
	 */
	public DeepOCLAnalyzer createOCLAnalyzer(
			AbstractOCLParser parser) {
		return new DeepOCLAnalyzer(parser);
	}
	
	 @Override
	  public Visitor<Boolean, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint> createValidationVisitor(
			Environment<EPackage, EObject, EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction, Constraint, EObject, EObject> env) {
			return new ValidationVisitor(env);
		}

}