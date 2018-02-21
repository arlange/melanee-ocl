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
import org.eclipse.emf.ecore.EClass;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.core.models.plm.PLM.impl.FeatureImpl;
import org.melanee.ocl.service.ocl.lml.LinguisticIdentifierAttribute;

/**
 * The LinguisticIdentifierAttributeImpl class
 * 
 * @author Dominik Kantner
 *
 */
public class LinguisticIdentifierAttributeImpl extends FeatureImpl
    implements LinguisticIdentifierAttribute {

  /**
   * the identifier for a linguistic perspective switch
   */
  public static String IDENTIFIER_NAME = "_l_";

  /**
   * stores the value
   */
  protected String value;

  /**
   * stores the datatype
   */
  protected String datatype;

  /**
   * stores the clabject
   */
  protected Clabject clabject;

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.core.models.plm.PLM.Attribute#getValue()
   */
  @Override
  public String getValue() {
    // TODO Auto-generated method stub
    return this.value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.core.models.plm.PLM.Attribute#setValue(java.lang.String)
   */
  @Override
  public void setValue(String value) {
    // TODO Auto-generated method stub
    this.value = value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.core.models.plm.PLM.Attribute#getMutability()
   */
  @Override
  public int getMutability() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.core.models.plm.PLM.Attribute#setMutability(int)
   */
  @Override
  public void setMutability(int value) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.core.models.plm.PLM.Attribute#getDatatype()
   */
  @Override
  public String getDatatype() {
    // TODO Auto-generated method stub
    return this.datatype;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.core.models.plm.PLM.Attribute#setDatatype(java.lang.String)
   */
  @Override
  public void setDatatype(String value) {
    this.datatype = value;

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.core.models.plm.PLM.Attribute#getMutabilityAsString()
   */
  @Override
  public String getMutabilityAsString() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.melanee.ocl.service.LinguisticIdentifierAttribute#setClabject(org.melanee
   * .core.models.plm.PLM.Clabject)
   */
  @Override
  public void setClabject(Clabject clabject) {
    // TODO Auto-generated method stub
    this.clabject = clabject;

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.melanee.core.models.plm.PLM.impl.FeatureImpl#getClabject()
   */
  public Clabject getClabject() {
    return this.clabject;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.melanee.ocl.service.LinguisticIdentifierAttribute#getLinguisticType()
   */
  @Override
  public EClass getLinguisticType() {
    // TODO Auto-generated method stub
    return this.clabject.eClass();
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
