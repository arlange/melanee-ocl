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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.AbstractEnvironment;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.utilities.OCLFactory;
import org.eclipse.ocl.utilities.UMLReflection;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.ocl.service.ocl.lml.impl.ConstraintImpl;
import org.melanee.ocl.service.ocl.lml.impl.ExpressionInOCLImpl;
import org.melanee.ocl.service.ocl.lml.impl.StringLiteralExpImpl;
import org.melanee.ocl.service.ocl.lml.internal.DeepOCLFactoryImpl;
import org.melanee.ocl.service.ocl.lml.internal.DeepOCLStandardLibraryImpl;
import org.melanee.ocl.service.ocl.lml.internal.TypeResolverImpl;
import org.melanee.ocl.service.ocl.lml.internal.DeepModelReflectionImpl;

/**
 * The LMLEnvironment 
 * @author Dominik Kantner
 *
 */
public class DeepOCLEnvironment extends AbstractEnvironment<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint, EObject, EObject>{

	/**
	 *  stores the EnvironmentFactory
	 */
	private EnvironmentFactory<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint, EObject, EObject> factory;
	
	/**
	 * stores the TypeResolver
	 */
	private TypeResolver<EObject,EObject,EObject> typeResolver;
	
	/**
	 * @param fac A LMLEnvironmentFactory
	 * @param resource A Resource
	 */
	protected DeepOCLEnvironment(DeepOCLEnvironmentFactory fac, Resource resource){
		factory=fac;
		typeResolver = createTypeResolver(resource); 
		
	}
	
	/**
	 * @param parent A environment
	 */
	protected DeepOCLEnvironment(Environment<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint, EObject, EObject> parent ){
		
		super((DeepOCLEnvironment) parent);
		DeepOCLEnvironment eparent=(DeepOCLEnvironment) parent;
		if(eparent!=null){
			factory=eparent.factory;
			typeResolver=parent.getTypeResolver();
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#getFactory()
	 */
	@Override
	public EnvironmentFactory<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint, EObject, EObject> getFactory() {
		// TODO Auto-generated method stub
		return factory;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#setParent(org.eclipse.ocl.Environment)
	 */
	@Override
	public void setParent(
			Environment<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint, EObject, EObject> env) {
		super.setParent((DeepOCLEnvironment)env);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#getOCLStandardLibrary()
	 */
	@Override
	public OCLStandardLibrary<EObject> getOCLStandardLibrary() {
		// TODO Auto-generated method stub
		return  (OCLStandardLibrary<EObject>) DeepOCLStandardLibraryImpl.INSTANCE;
	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#getTypeResolver()
	 */
	@Override
	public TypeResolver<EObject,EObject,EObject> getTypeResolver() {
		// TODO Auto-generated method stub
		return typeResolver;               //maybe needed
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#lookupPackage(java.util.List)
	 */
	@Override
	public EPackage lookupPackage(List<String> names) {
		// TODO Auto-generated method stub
		return null;          //maybe needed
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#lookupClassifier(java.util.List)
	 */
	@Override
	public EObject lookupClassifier(List<String> names) {  
		EPackage currPkg = getContextPackage();
		EObject result=currPkg.getEClassifier(names.get(0));
		
		if(result==null){//result not found in metaclasses, search in clabjectNames of domain 
		org.melanee.ocl.service.ocl.lml.Variable var=(org.melanee.ocl.service.ocl.lml.Variable) getSelfVariable();
		EObject type= var.getType();
		Object [] levels=null;
		if(type  instanceof Clabject){
			 levels=(((Clabject) type).getDeepModel().getContent()).toArray();		
		}
		else if(type instanceof Level){
			levels=((Level)type).getDeepModel().getContent().toArray();
		}
		else if(type instanceof DeepModel){
			levels=((DeepModel)type).getContent().toArray();
		}
		
		ArrayList<Clabject> clabjectList= new ArrayList<Clabject>();
		if(levels!=null){
			for(int i =0;i<levels.length;i++){
				Level level=(Level) levels[i];
				Object [] levelClabjects=level.getClabjects().toArray();
				for(int j=0; j<levelClabjects.length;j++){
					Clabject clabject=(Clabject)levelClabjects[j];
					clabjectList.add(clabject);
				}
			}
		}
		
		for(int i=0; i<clabjectList.size();i++){
			Clabject clabject=(Clabject)clabjectList.get(i);
			String clabjectName=clabject.getName();
			String name=names.get(0);
			if(clabjectName!=null && clabjectName.equals(name)){
				result=clabject;
				return result;
			}
		}
		
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#getStates(java.lang.Object, java.util.List)
	 */
	@Override
	public List<EObject> getStates(EObject owner, List<String> pathPrefix) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#isInPostcondition(org.eclipse.ocl.expressions.OCLExpression)
	 */
	@Override
	public boolean isInPostcondition(OCLExpression<EObject> exp) {
		// TODO Auto-generated method stub
		return false;         //maybe needed
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#defineAttribute(java.lang.Object, org.eclipse.ocl.expressions.Variable, java.lang.Object)
	 */
	@Override
	public Attribute defineAttribute(EObject owner,
			Variable<EObject, EObject> variable, Constraint constraint) {
		// TODO Auto-generated method stub
		return null;        
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#defineOperation(java.lang.Object, java.lang.String, java.lang.Object, java.util.List, java.lang.Object)
	 */
	@Override
	public Method defineOperation(EObject owner, String name, EObject type,
			List<Variable<EObject, EObject>> params, Constraint constraint) {
		// TODO Auto-generated method stub
		return null;  
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#getDefinition(java.lang.Object)
	 */
	@Override
	public Constraint getDefinition(Object feature) {
		// TODO Auto-generated method stub
		return null;   
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#undefine(java.lang.Object)
	 */
	@Override
	public void undefine(Object feature) {
	
		// TODO Auto-generated method stub   
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.AbstractEnvironment#getBodyCondition(java.lang.Object)
	 */
	public Constraint getBodyCondition(EObject operation) {
		
		if(operation instanceof EOperation){
			return super.getBodyCondition(operation);
		}
		ConstraintImpl constraint=new ConstraintImpl();
		
		
		StringLiteralExp stringLiteral=new StringLiteralExpImpl();
		stringLiteral.setStringSymbol(((Method) operation).getBody());
		
		ExpressionInOCL expressionInOcl = new ExpressionInOCLImpl();
		expressionInOcl.setBodyExpression(stringLiteral);
		
		constraint.setSpecification(expressionInOcl);
		
		constraint.setStereotype(((Method) operation).getBody());
		
		return constraint;
		
	}
    

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#getOCLFactory()
	 */
	@Override
	public OCLFactory getOCLFactory() {
		 return DeepOCLFactoryImpl.INSTANCE;

	}

	/**
	 * @param resource
	 * @return A TypeResolverImpl
	 */
	protected TypeResolver<EObject,EObject,EObject> createTypeResolver(Resource resource) {
		return new TypeResolverImpl(this, resource);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.Environment#getUMLReflection()
	 */
	@Override
	public UMLReflection<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint> getUMLReflection() {
		// TODO Auto-generated method stub
		//System.out.println("LMLENVIRONMENT:getUMLReflection");
		return DeepModelReflectionImpl.INSTANCE;
	}
	
	
}
