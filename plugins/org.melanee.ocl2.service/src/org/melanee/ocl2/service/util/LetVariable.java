/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.service.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.service.exception.InterpreterException;

public class LetVariable {
  private String type;
  private String name;
  private Object value;
  private Clabject context;

  public LetVariable(String letName, String letType, String value, Clabject context)
      throws InterpreterException {
    this.type = letType;
    this.name = letName;
    this.context = context;
    setValue(value);
  }

  public void setValue(String value) throws InterpreterException {
    if (this.type.equals("Integer")) {
      this.value = Integer.parseInt(value);
      return;
    } else if (this.type.equals("Real")) {
      this.value = Double.parseDouble(value);
      return;
    } else if (this.type.equals("String")) {
      this.value = value;
      return;
    } else if (this.type.equals("Boolean")) {
      this.value = Boolean.parseBoolean(value);
      return;
    } else if (this.type.equals("oclAny")) {
      this.value = new Object();
      return;
    } else if (this.type.equals("Set") || this.type.equals("OrderedSet")) {
      this.value = new HashSet<>();
      return;
    } else if (this.type.equals("Bag") || this.type.equals("Sequence")) {
      this.value = new ArrayList<>();
      return;
    }
    Iterator<?> it = this.context.getDeepModel().eAllContents();
    while (it.hasNext()) {
      Element e = (Element) it.next();
      if (e.getName().equals(this.type)) {
        this.value = PLMFactory.eINSTANCE.createClabjectFromTemplate((Clabject) e, null);
      } else {
        throw new InterpreterException(
            "could not instantiate let variable " + this.name + " with the type " + this.type);
      }
    }
  }

  public Object getValue() {
    return this.value;
  }

  public String getName() {
    return this.name;
  }

  public String getDataType() {
    return this.type;
  }
}
