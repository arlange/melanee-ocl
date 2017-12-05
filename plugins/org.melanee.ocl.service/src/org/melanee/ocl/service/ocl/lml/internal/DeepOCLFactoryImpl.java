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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ecore.OppositePropertyCallExp;
import org.eclipse.ocl.ecore.utilities.OCLFactoryWithHiddenOpposite;
import org.eclipse.ocl.expressions.AssociationClassCallExp;
import org.eclipse.ocl.expressions.BooleanLiteralExp;
import org.eclipse.ocl.expressions.CollectionItem;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.expressions.CollectionLiteralExp;
import org.eclipse.ocl.expressions.CollectionRange;
import org.eclipse.ocl.expressions.EnumLiteralExp;
import org.eclipse.ocl.expressions.IfExp;
import org.eclipse.ocl.expressions.IntegerLiteralExp;
import org.eclipse.ocl.expressions.InvalidLiteralExp;
import org.eclipse.ocl.expressions.IterateExp;
import org.eclipse.ocl.expressions.IteratorExp;
import org.eclipse.ocl.expressions.LetExp;
import org.eclipse.ocl.expressions.MessageExp;
import org.eclipse.ocl.expressions.NullLiteralExp;
import org.eclipse.ocl.expressions.OperationCallExp;
import org.eclipse.ocl.expressions.PropertyCallExp;
import org.eclipse.ocl.expressions.RealLiteralExp;
import org.eclipse.ocl.expressions.StateExp;
import org.eclipse.ocl.expressions.StringLiteralExp;
import org.eclipse.ocl.expressions.TupleLiteralExp;
import org.eclipse.ocl.expressions.TupleLiteralPart;
import org.eclipse.ocl.expressions.TypeExp;
import org.eclipse.ocl.expressions.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.expressions.UnspecifiedValueExp;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.expressions.VariableExp;
import org.eclipse.ocl.types.BagType;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.types.MessageType;
import org.eclipse.ocl.types.OrderedSetType;
import org.eclipse.ocl.types.SequenceType;
import org.eclipse.ocl.types.SetType;
import org.eclipse.ocl.types.TupleType;
import org.eclipse.ocl.types.TypeType;
import org.eclipse.ocl.utilities.OCLFactory;
import org.eclipse.ocl.utilities.TypedElement;
import org.melanee.ocl.service.ocl.lml.impl.BagTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.BooleanLiteralExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.CollectionItemImpl;
import org.melanee.ocl.service.ocl.lml.impl.CollectionLiteralExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.CollectionTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.IfExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.IntegerLiteralExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.IteratorExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.LetExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.OperationCallExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.OrderedSetTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.PropertyCallExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.RealLiteralExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.SequenceTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.SetTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.StringLiteralExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.TypeExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.TypeTypeImpl;
import org.melanee.ocl.service.ocl.lml.impl.VariableExpImpl;
import org.melanee.ocl.service.ocl.lml.impl.VariableImpl;


/**
 * The OCLFactoryImplClass for the deep OCL dialect
 * @author Dominik Kantner
 *
 */
public class DeepOCLFactoryImpl implements OCLFactory,OCLFactoryWithHiddenOpposite {

	
	 /**
	 * the current instance
	 */
	public static OCLFactory INSTANCE = new DeepOCLFactoryImpl();
	    
	 /**
	 * private constructor
	 */
	private DeepOCLFactoryImpl() {
	        super();
	 }
	/* (non-Javadoc)
	 * @see org.eclipse.ocl.ecore.utilities.OCLFactoryWithHiddenOpposite#createOppositePropertyCallExp()
	 */
	@Override
	public OppositePropertyCallExp createOppositePropertyCallExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createBagType(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, O> BagType<C, O> createBagType(C elementType) {
		org.melanee.ocl.service.ocl.lml.BagType result = new BagTypeImpl((EObject)elementType);
		return (BagType<C, O>) result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createCollectionType(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, O> CollectionType<C, O> createCollectionType(C elementType) {
		
		org.melanee.ocl.service.ocl.lml.CollectionType result = new CollectionTypeImpl();
		result.setElementType((EObject)elementType);
		return (CollectionType<C, O>) result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createCollectionType(org.eclipse.ocl.expressions.CollectionKind, java.lang.Object)
	 */
	@Override
	public <C, O> CollectionType<C, O> createCollectionType(
			CollectionKind kind, C elementType) {
	     switch (kind) {
	        case BAG_LITERAL:
	            return createBagType(elementType);
	        case SET_LITERAL:
	            return createSetType(elementType);
	        case SEQUENCE_LITERAL:
	            return createSequenceType(elementType);
	        case ORDERED_SET_LITERAL:
	            return createOrderedSetType(elementType);
	        default:
	            return createCollectionType(elementType);
	        }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createOrderedSetType(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, O> OrderedSetType<C, O> createOrderedSetType(C elementType) {
		org.melanee.ocl.service.ocl.lml.OrderedSetType result= new OrderedSetTypeImpl((EObject)elementType);
		return (OrderedSetType<C, O>) result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createSequenceType(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, O> SequenceType<C, O> createSequenceType(C elementType) {
		org.melanee.ocl.service.ocl.lml.SequenceType result = new SequenceTypeImpl((EObject)elementType);
		return (SequenceType<C, O>) result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createSetType(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, O> SetType<C, O> createSetType(C elementType) {
		org.melanee.ocl.service.ocl.lml.SetType result = new SetTypeImpl((EObject)elementType);
		return (SetType<C, O>) result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createSignalMessageType(java.lang.Object)
	 */
	@Override
	public <C, O, P> MessageType<C, O, P> createSignalMessageType(C signal) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createOperationMessageType(java.lang.Object)
	 */
	@Override
	public <C, O, P> MessageType<C, O, P> createOperationMessageType(O operation) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createTupleType(java.util.List)
	 */
	@Override
	public <C, O, P> TupleType<O, P> createTupleType(
			List<? extends TypedElement<C>> parts) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createTypeType(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, O> TypeType<C, O> createTypeType(C type) {
		org.melanee.ocl.service.ocl.lml.TypeType typeType= TypeTypeImpl.createTypeType((EObject)type);
		return (TypeType<C, O>) typeType;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createAssociationClassCallExp()
	 */
	@Override
	public <C, P> AssociationClassCallExp<C, P> createAssociationClassCallExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createBooleanLiteralExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> BooleanLiteralExp<C> createBooleanLiteralExp() {
		org.melanee.ocl.service.ocl.lml.BooleanLiteralExp booleanLiteralExp = new BooleanLiteralExpImpl();
		return (BooleanLiteralExp<C>) booleanLiteralExp;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createCollectionItem()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> CollectionItem<C> createCollectionItem() {
		CollectionItemImpl collectionItem = new CollectionItemImpl();
		return (CollectionItem<C>) collectionItem;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createCollectionLiteralExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> CollectionLiteralExp<C> createCollectionLiteralExp() {
		CollectionLiteralExpImpl collectionLiteral=new CollectionLiteralExpImpl();
		return (CollectionLiteralExp<C>) collectionLiteral;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createCollectionRange()
	 */
	@Override
	public <C> CollectionRange<C> createCollectionRange() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createEnumLiteralExp()
	 */
	@Override
	public <C, EL> EnumLiteralExp<C, EL> createEnumLiteralExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createIfExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> IfExp<C> createIfExp() {
		IfExpImpl ifExpImpl=new IfExpImpl();
		return (IfExp<C>) ifExpImpl;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createIntegerLiteralExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> IntegerLiteralExp<C> createIntegerLiteralExp() {
		IntegerLiteralExpImpl integerLiteralExpImpl = new IntegerLiteralExpImpl();
		return (IntegerLiteralExp<C>) integerLiteralExpImpl;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createUnlimitedNaturalLiteralExp()
	 */
	@Override
	public <C> UnlimitedNaturalLiteralExp<C> createUnlimitedNaturalLiteralExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createInvalidLiteralExp()
	 */
	@Override
	public <C> InvalidLiteralExp<C> createInvalidLiteralExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createIterateExp()
	 */
	@Override
	public <C, PM> IterateExp<C, PM> createIterateExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createIteratorExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, PM> IteratorExp<C, PM> createIteratorExp() {
		org.melanee.ocl.service.ocl.lml.IteratorExp iteratorExp=new IteratorExpImpl();
		
		return (IteratorExp<C, PM>) iteratorExp;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createLetExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, PM> LetExp<C, PM> createLetExp() {
		org.melanee.ocl.service.ocl.lml.LetExp letExp=new LetExpImpl();
		return (LetExp<C, PM>) letExp;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createMessageExp()
	 */
	@Override
	public <C, COA, SSA> MessageExp<C, COA, SSA> createMessageExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createNullLiteralExp()
	 */
	@Override
	public <C> NullLiteralExp<C> createNullLiteralExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createOperationCallExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, O> OperationCallExp<C, O> createOperationCallExp() {
		org.melanee.ocl.service.ocl.lml.OperationCallExp operationCall=new OperationCallExpImpl();
		return (OperationCallExp<C, O>) operationCall;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createPropertyCallExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, P> PropertyCallExp<C, P> createPropertyCallExp() {
		org.melanee.ocl.service.ocl.lml.PropertyCallExp propertyCall=new PropertyCallExpImpl();
		return (PropertyCallExp<C, P>) propertyCall;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createRealLiteralExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> RealLiteralExp<C> createRealLiteralExp() {
		org.melanee.ocl.service.ocl.lml.RealLiteralExp realLiteral = new RealLiteralExpImpl();
		return (RealLiteralExp<C>) realLiteral;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createStateExp()
	 */
	@Override
	public <C, S> StateExp<C, S> createStateExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createStringLiteralExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> StringLiteralExp<C> createStringLiteralExp() {
		org.melanee.ocl.service.ocl.lml.StringLiteralExp stringLiteralExp = new StringLiteralExpImpl();
		return (StringLiteralExp<C>) stringLiteralExp;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createTupleLiteralExp()
	 */
	@Override
	public <C, P> TupleLiteralExp<C, P> createTupleLiteralExp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createTupleLiteralPart()
	 */
	@Override
	public <C, P> TupleLiteralPart<C, P> createTupleLiteralPart() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createTypeExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> TypeExp<C> createTypeExp() {
		org.melanee.ocl.service.ocl.lml.TypeExp typeExp = new TypeExpImpl();
		return (TypeExp<C>) typeExp;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createUnspecifiedValueExp()
	 */
	@Override
	public <C> UnspecifiedValueExp<C> createUnspecifiedValueExp() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createVariable()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C,PM> Variable<C,PM> createVariable() {
		@SuppressWarnings("rawtypes")
		Variable variable = new VariableImpl(); 
		return (Variable<C, PM>) variable;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ocl.utilities.OCLFactory#createVariableExp()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C, PM> VariableExp<C, PM> createVariableExp() {
		org.melanee.ocl.service.ocl.lml.VariableExp variableExp=new VariableExpImpl();
		return  (VariableExp<C, PM>)variableExp;
		
	}

}
