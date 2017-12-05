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
package org.melanee.ocl.service.ocl.lml.util;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.utilities.UMLReflection;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.ocl.service.ocl.lml.Constraint;
import org.melanee.ocl.service.ocl.lml.DeepOCLEnvironment;

/**
 * DeepOCLStandardLibrary which provides additional OCL operations which are necessary in deep models
 * @author Dominik Kantner
 *
 */
public final class DeepOCLStandardLibraryUtil {
	

	/**
	 * The oclIsDeepTypeOf operation name
	 */
	public static final String OCL_IS_DEEP_TYPE_OF_NAME="oclIsDeepTypeOf";
	
	/**
	 * The oclIsDeepKindOf operation name
	 */
	public static final String OCL_IS_DEEP_KIND_OF_NAME="oclIsDeepKindOf";
	
	/**
	 * The oclIsDeepKindOf operation name
	 */
	public static final String OCL_AS_DEEP_TYPE_NAME="oclAsDeepType";
	/**
	 * The oclAllInstances operation name
	 */
	
	public static final String OCL_All_INSTANCES_NAME="allInstances";
	
	/**
	 * The oclAllDeepInstance method name
	 */
	public static final String OCL_All_DEEP_INSTANCES_NAME="allDeepInstances";
	
	/**
	 * counter for the amount of any operations
	 */
	private static final int ANY_OPERATION_COUNT = 2;
	
	
	/**
	 * counter for the amount of type operations
	 */
	private static final int TYPE_OPERATION_COUNT = 2;
	/**
	 * @param lmlEnvironment A LMLEnvironment
	 * @return A list of created AnyType operations
	 */
	public static List<EObject> createAnyTypeOperations(DeepOCLEnvironment lmlEnvironment){
		List<EObject> result = new java.util.ArrayList<EObject>(ANY_OPERATION_COUNT);
		OCLStandardLibrary<EObject> stdlib = lmlEnvironment.getOCLStandardLibrary();
		UMLReflection<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint> uml= lmlEnvironment.getUMLReflection();
		
		result.add(createBinaryOperation(uml, stdlib.getT(), OCL_AS_DEEP_TYPE_NAME, stdlib.getOclType(),"typespec"));
		result.add(createBinaryOperation(uml, stdlib.getBoolean(), OCL_IS_DEEP_TYPE_OF_NAME, stdlib.getOclType(),"typespec"));
		result.add(createBinaryOperation(uml, stdlib.getBoolean(), OCL_IS_DEEP_KIND_OF_NAME, stdlib.getOclType(),"typespec"));
		
		return result;
	}
	
	
	
	/**
	 * @param lmlEnvironment A LMLEnvironment
	 * @return A list of created TypeType operations
	 */
	public static List<EObject> createTypeTypeOperations(DeepOCLEnvironment lmlEnvironment){
		List<EObject> result = new java.util.ArrayList<EObject>(TYPE_OPERATION_COUNT);
		OCLStandardLibrary<EObject> stdlib = lmlEnvironment.getOCLStandardLibrary();
		UMLReflection<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint> uml= lmlEnvironment.getUMLReflection();
		
		result.add(createBinaryOperation(uml,  stdlib.getSet(), OCL_All_INSTANCES_NAME, stdlib.getInteger(),"levelDistance"));
		result.add(createTernaryOperation(uml,  stdlib.getSet(), OCL_All_INSTANCES_NAME, stdlib.getInteger(),"levelDistance1", stdlib.getInteger(),"levelDistance2"));
		result.add(createUnaryOperation(uml, stdlib.getSet(), OCL_All_DEEP_INSTANCES_NAME));
		result.add(createBinaryOperation(uml,  stdlib.getSet(), OCL_All_DEEP_INSTANCES_NAME, stdlib.getInteger(),"levelDistance"));
		result.add(createTernaryOperation(uml,  stdlib.getSet(), OCL_All_DEEP_INSTANCES_NAME, stdlib.getInteger(),"levelDistance1", stdlib.getInteger(),"levelDistance2"));
		
		return result;
	}
	
	/**
	 * @param uml A UMLReflection
	 * @param resultType The type of the result
	 * @param name The name of the operations
	 * @param paramType The type of the parameter
	 * @param paramName The name of the parameter
	 * @return
	 */
	private static EObject createBinaryOperation(UMLReflection<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint> uml, EObject resultType, String name, EObject paramType, String paramName){
		List<String> paramNames = Collections.singletonList(paramName);
		List<EObject> paramTypes = Collections.singletonList(paramType);
		
		return uml.createOperation(name, resultType, paramNames, paramTypes);
	}
	
	/**
	 * @param uml A UMLReflection
	 * @param resultType The type of the result
	 * @param name The name of the operations
	 * @param param1Type The type of the first parameter
	 * @param param1Name The name of the  first parameter
	 * @param param2Type The type of the second parameter
	 * @param param2Name The name of the  second parameter
	 * @return
	 */
	private static EObject createTernaryOperation(
			UMLReflection<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint> uml,
			EObject resultType, String name, EObject param1Type, String param1Name,
			EObject param2Type, String param2Name) {

		List<String> paramNames = new java.util.ArrayList<String>(2);
		List<EObject> paramTypes = new java.util.ArrayList<EObject>(2);

		paramNames.add(param1Name);
		paramTypes.add(param1Type);
		paramNames.add(param2Name);
		paramTypes.add(param2Type);

		return uml.createOperation(name, resultType, paramNames, paramTypes);
	}
	
	/**
	 * @param uml A UMLReflection
	 * @param resultType The type of the result
	 * @param name The name of the operation
	 * @return
	 */
	private static EObject createUnaryOperation(UMLReflection<EPackage, EObject,EObject, EObject, Enumeration, EObject, EObject, CallOperationAction, SendSignalAction,  Constraint> uml, EObject resultType, String name){
		
		List<String> paramNames = Collections.emptyList();
		List<EObject> paramTypes = Collections.emptyList();

		return uml.createOperation(name, resultType, paramNames, paramTypes);
	}
	
	
}
