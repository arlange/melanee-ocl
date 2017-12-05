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
package org.melanee.ocl.service.ocl.lml.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.melanee.ocl.service.ocl.lml.CollectionType;
import org.melanee.ocl.service.ocl.lml.PrimitiveType;
import org.melanee.ocl.service.ocl.lml.impl.AnyTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.BagTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.CollectionTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.InvalidTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.OrderedSetTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.PrimitiveTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.SequenceTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.SetTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.TypeTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.VoidTypeImpl;


/**
 * The OCLStandardLibraryImpl for a deep OCL dialect
 * @author Dominik Kantner
 *
 */
public class DeepOCLStandardLibraryImpl implements OCLStandardLibrary<EObject>{

	private static EObject OCL_ANY;
	private static EObject OCL_ELEMENT;
	private static EObject OCL_BOOLEAN;
	private static EObject OCL_INTEGER;
    private static EObject OCL_UNLIMITED_NATURAL;
	private static EObject OCL_REAL;
	private static EObject OCL_STRING;
	private static EObject OCL_VOID;
	private static EObject OCL_MESSAGE;
	private static EObject OCL_TYPE;

	private static EObject OCL_INVALID;
	
	private static EObject OCL_T ;
	private static EObject OCL_T2;
	
	private static EObject OCL_SET;
	private static EObject OCL_ORDERED_SET;
	private static EObject OCL_BAG;
	private static EObject OCL_SEQUENCE;
	private static EObject OCL_COLLECTION;
	
	private static EObject STATE;
	private static EObject OCL_EXPRESSION;
	
	/**
	 * The Invalid type
	 */
	public static final EObject INVALID = new EObjectImpl()
	    {
	    	public String toString() { return "invalid"; } //$NON-NLS-1$
	 };
	
    /**
     * returns the instance of the OCLStandardLibraryImpl
     */
    public static final DeepOCLStandardLibraryImpl INSTANCE = new DeepOCLStandardLibraryImpl();
    
	/**
	 * stores the stdlibPackage
	 */
	public static EPackage stdlibPackage = init();
	
	/**
	 * private constructor
	 */
	private DeepOCLStandardLibraryImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getOclAny()
	 */
	@Override
	public EObject getOclAny() {
	
		return OCL_ANY;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getOclVoid()
	 */
	@Override
	public EObject getOclVoid() {
		
		return OCL_VOID;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getOclInvalid()
	 */
	@Override
	public EObject getOclInvalid() {
		
		return OCL_INVALID;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getInvalid()
	 */
	@Override
	public Object getInvalid() {
		
		return INVALID;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getBoolean()
	 */
	@Override
	public EObject getBoolean() {
		
	
		return OCL_BOOLEAN;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getString()
	 */
	@Override
	public EObject getString() {
		
		return OCL_STRING;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getReal()
	 */
	@Override
	public EObject getReal() {
		
		return OCL_REAL;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getInteger()
	 */
	@Override
	public EObject getInteger() {
		
		return OCL_INTEGER;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getUnlimitedNatural()
	 */
	@Override
	public EObject getUnlimitedNatural() {
		
		return OCL_UNLIMITED_NATURAL;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getOclType()
	 */
	@Override
	public EObject getOclType() {
		
	
		return OCL_TYPE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getOclElement()
	 */
	@Override
	public EObject getOclElement() {
		
		return OCL_ELEMENT;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getOclMessage()
	 */
	@Override
	public EObject getOclMessage() {
		
		return OCL_MESSAGE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getSet()
	 */
	@Override
	public EObject getSet() {
		
		return OCL_SET;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getOrderedSet()
	 */
	@Override
	public EObject getOrderedSet() {
		
		 return OCL_ORDERED_SET;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getBag()
	 */
	@Override
	public EObject getBag() {
		
		return OCL_BAG;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getSequence()
	 */
	@Override
	public EObject getSequence() {
		
		return OCL_SEQUENCE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getCollection()
	 */
	@Override
	public EObject getCollection() {
		
		return OCL_COLLECTION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getState()
	 */
	@Override
	public EObject getState() {
	
		return STATE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getOclExpression()
	 */
	@Override
	public EObject getOclExpression() {
	
		return OCL_EXPRESSION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getT()
	 */
	@Override
	public EObject getT() {
		
		return OCL_T;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.types.OCLStandardLibrary#getT2()
	 */
	@Override
	public EObject getT2() {
		
		return OCL_T2;
	}

	/**
	 * initialize OCL types
	 * @return A EPackage
	 */
	private static EPackage init(){
	    OCL_ANY = (EObject) new AnyTypeImpl();
	    
	    OCL_T = (EObject) new AnyTypeImpl();
	    
	    OCL_VOID=new VoidTypeImpl();
	    
		OCL_TYPE = TypeTypeImpl.createTypeType(OCL_T);
		
		OCL_BOOLEAN=new PrimitiveTypeImpl();
		((PrimitiveType) OCL_BOOLEAN).setName(org.eclipse.ocl.types.PrimitiveType.BOOLEAN_NAME);

	    OCL_INTEGER= (EObject)new PrimitiveTypeImpl();
	    ((PrimitiveType) OCL_INTEGER).setName(org.eclipse.ocl.types.PrimitiveType.INTEGER_NAME);
	 
	    OCL_REAL= (EObject)new PrimitiveTypeImpl();
	    ((PrimitiveType) OCL_REAL).setName(org.eclipse.ocl.types.PrimitiveType.REAL_NAME);
	    
	    OCL_STRING=(EObject)new PrimitiveTypeImpl();
	    ((PrimitiveType) OCL_STRING).setName(org.eclipse.ocl.types.PrimitiveType.STRING_NAME);
	    
	    OCL_INVALID =new InvalidTypeImpl();

	    
	   OCL_SET =(EObject) new SetTypeImpl(OCL_T);  
	  
	   
	   OCL_ORDERED_SET =(EObject) new OrderedSetTypeImpl(OCL_T);  
	   
	   OCL_BAG =(EObject) new BagTypeImpl(OCL_T);  
	 
	   
	   OCL_SEQUENCE = (EObject) new SequenceTypeImpl(OCL_T);
	   
	   
		OCL_COLLECTION =(EObject) new CollectionTypeImpl();
	   ((CollectionType) OCL_COLLECTION).setElementType(OCL_T);

	   return null;
	
	}
	
	
}
