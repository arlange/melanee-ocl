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
package org.melanee.ocl.service.ocl.lml.impl;

import org.eclipse.emf.common.util.EList;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.core.models.plm.PLM.impl.FeatureImpl;
import org.melanee.ocl.service.ocl.lml.NavigationAttribute;

/**
 * The NavigationAttributeImpl class
 * @author Dominik
 *
 */
public class NavigationAttributeImpl extends FeatureImpl implements NavigationAttribute {

	
	/**
	 * stores the value
	 */
	protected String value;
	/**
	 * stores the navigationValue
	 */
	protected Clabject navigationValue;
	/**
	 * stores the datatype
	 */
	protected String datatype;
	/**
	 * stores the according clabject
	 */
	protected Clabject clabject;
	/**
	 * stores the according connection
	 */
	protected Connection connection;
	/**
	 * stores if the result type is single or not
	 */
	protected boolean isSingle;
	
	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.NavigationAttribute#setClabject(org.melanee.core.models.plm.PLM.Clabject)
	 */
	public void setClabject(Clabject clabject){
		this.clabject=clabject;
	}
	
	/* (non-Javadoc)
	 * @see org.melanee.core.models.plm.PLM.impl.FeatureImpl#getClabject()
	 */
	public Clabject getClabject(){
		return this.clabject;
	}
	/* (non-Javadoc)
	 * @see org.melanee.core.models.plm.PLM.Attribute#getValue()
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	/* (non-Javadoc)
	 * @see org.melanee.core.models.plm.PLM.Attribute#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		this.value=value;
		
	}

	/* (non-Javadoc)
	 * @see org.melanee.core.models.plm.PLM.Attribute#getMutability()
	 */
	@Override
	public int getMutability() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.melanee.core.models.plm.PLM.Attribute#setMutability(int)
	 */
	@Override
	public void setMutability(int value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.melanee.core.models.plm.PLM.Attribute#getDatatype()
	 */
	@Override
	public String getDatatype() {
		// TODO Auto-generated method stub
		return this.datatype;
	}

	/* (non-Javadoc)
	 * @see org.melanee.core.models.plm.PLM.Attribute#setDatatype(java.lang.String)
	 */
	@Override
	public void setDatatype(String value) {
		this.datatype=value;
		
	}

	/* (non-Javadoc)
	 * @see org.melanee.core.models.plm.PLM.Attribute#getMutabilityAsString()
	 */
	@Override
	public String getMutabilityAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.NavigationAttribute#setNavigationValue(org.melanee.core.models.plm.PLM.Clabject)
	 */
	@Override
	public void setNavigationValue(Clabject clabject) {
		this.navigationValue=clabject;
		
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.NavigationAttribute#getNavigationValue()
	 */
	@Override
	public Clabject getNavigationValue() {
		// TODO Auto-generated method stub
		return this.navigationValue;
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.NavigationAttribute#setConnection(org.melanee.core.models.plm.PLM.Connection)
	 */
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		this.connection = connection;
		
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.NavigationAttribute#getConnection()
	 */
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return this.connection;
	}

	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.NavigationAttribute#setSingle(boolean)
	 */
	@Override
	public void setSingle(boolean single) {
		// TODO Auto-generated method stub
		isSingle=single;
	}
	
	/* (non-Javadoc)
	 * @see org.melanee.ocl.service.NavigationAttribute#isSingle()
	 */
	public boolean isSingle(){
		return isSingle;
	}

	@Override
	public EList<String> getPossibleDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<String> getPrimitiveDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<String> getEnumerationDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnumeration() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration getEnumeration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<String> getLiterals() {
		// TODO Auto-generated method stub
		return null;
	}

}