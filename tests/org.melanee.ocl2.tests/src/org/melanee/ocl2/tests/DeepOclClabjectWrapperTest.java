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

package org.melanee.ocl2.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.service.DeepOCLClabjectWrapper;
import org.melanee.ocl2.service.DeepOCLClabjectWrapperImpl;

/**
 * the ocl implementation or the the CollectioUtils that are derived from ocl
 * which is used in the wrapper class to perform collection operations starts
 * the index at 1. so be aware!! from the CollectionUtil class: @param index the
 * 1-based (in OCL fashion) index
 */
public class DeepOclClabjectWrapperTest {

  DeepOCLClabjectWrapper wrapper;
  Clabject clabject;
  Object[] args;

  /**
   * initialize the tests.
   */
  @Before
  public void create() {
    this.clabject = PLMFactory.eINSTANCE.createEntity();
    this.wrapper = new DeepOCLClabjectWrapperImpl(this.clabject);
  }

  /**
   * destroy after test.
   */
  @After
  public void destroy() {
    this.clabject = null;
    this.wrapper = null;
    this.args = null;
  }

  @Test
  public void atTest() {
    Collection<Object> collection = new ArrayList<>();
    collection.add(this.clabject);
    this.args = new Object[2];
    this.args[0] = collection;
    int one = 1;
    this.args[1] = one;
    try {
      assertEquals(this.clabject, this.wrapper.invoke("at", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void insertAtTest() {
    Collection<Object> collection = new ArrayList<>();
    collection.add(this.clabject);
    collection.add(PLMFactory.eINSTANCE.createEntity());
    assertEquals(2, collection.size());
    Clabject newClabject = PLMFactory.eINSTANCE.createEntity();
    this.args = new Object[3];
    this.args[0] = collection;
    this.args[1] = newClabject;
    this.args[2] = 2;
    try {
      Object newCollection = this.wrapper.invoke("insertAt", args);
      Collection<?> newCollection1 = (Collection<?>) newCollection;
      assertEquals(3, newCollection1.size());
      assertEquals(newClabject, newCollection1.toArray()[1]);
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void sizeTest() {
    Collection<Object> collection = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      collection.add(new Object());
    }
    this.args = new Object[1];
    this.args[0] = collection;
    try {
      assertTrue(10 == (Integer) this.wrapper.invoke("size", args));
      assertFalse(0 == (Integer) this.wrapper.invoke("size", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void sumTest() {
    Collection<Object> collection = new ArrayList<>();
    int sum = 0;
    for (int i = 0; i < 10; i++) {
      sum += i * 3;
      collection.add(i * 3);
    }
    this.args = new Object[1];
    this.args[0] = collection;
    try {
      assertTrue(sum == (Integer) this.wrapper.invoke("sum", args));
      assertFalse(0 == (Integer) this.wrapper.invoke("sum", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void asSetTest() {
    List<Integer> collection = new ArrayList<Integer>();
    collection.add(3);
    collection.add(3);
    assertEquals(2, collection.size());
    this.args = new Object[1];
    this.args[0] = collection;
    try {
      assertEquals(1, ((Collection<?>) this.wrapper.invoke("asSet", args)).size());
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void appendTest() {
    Collection<Object> collection = new ArrayList<>();
    assertTrue(collection.isEmpty());
    this.args = new Object[2];
    this.args[0] = collection;
    this.args[1] = this.clabject;
    Collection<?> result;
    try {
      result = (Collection<?>) this.wrapper.invoke("append", args);
      assertEquals(1, result.size());
      assertEquals(this.clabject, result.toArray()[0]);
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void prependTest() {
    List<Object> collection = new ArrayList<>();
    collection.add(this.clabject);
    assertEquals(this.clabject, collection.get(0));
    Object object = new Object();
    this.args = new Object[2];
    this.args[0] = collection;
    this.args[1] = object;
    try {
      assertEquals(object, ((Collection<?>) this.wrapper.invoke("prepend", args)).toArray()[0]);
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void oneTest() {
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    a.setName("age");
    a.setDatatype("Integer");
    a.setValue("18");
    this.clabject.getFeature().add(a);
    this.clabject.setName("test1");
    Collection<Clabject> list = new ArrayList<>();
    list.add(this.clabject);
    this.args = new Object[2];
    this.args[0] = list;
    this.args[1] = "age = 18";
    try {
      assertTrue((Boolean) this.wrapper.invoke("one", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }

    Clabject cl = PLMFactory.eINSTANCE.createEntity();
    cl.setName("test2");
    Attribute a1 = PLMFactory.eINSTANCE.createAttribute();
    a1.setName("age");
    a1.setDatatype("Integer");
    a1.setValue("18");
    cl.getFeature().add(a1);
    list.add(cl);
    assertEquals(list.size(), 2);
    this.args = new Object[2];
    this.args[0] = list;
    this.args[1] = "age = 18";
    try {
      assertFalse((Boolean) this.wrapper.invoke("one", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }

  }

  @Test
  public void isUniqueTest() {
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    a.setName("name");
    a.setDatatype("String");
    a.setValue("klaus");
    this.clabject.getFeature().add(a);
    Collection<Clabject> list = new ArrayList<>();
    list.add(this.clabject);
    this.args = new Object[2];
    this.args[0] = list;
    this.args[1] = "acc|acc.name";
    try {
      assertTrue((Boolean) this.wrapper.invoke("isUnique", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
    Attribute a1 = PLMFactory.eINSTANCE.createAttribute();
    a1.setName("name");
    a1.setDatatype("String");
    a1.setValue("klaus");
    Clabject cl = PLMFactory.eINSTANCE.createEntity();
    cl.getFeature().add(a1);
    list.add(cl);
    this.args = new Object[2];
    this.args[0] = list;
    this.args[1] = "acc|acc.name";
    try {
      assertFalse((Boolean) this.wrapper.invoke("isUnique", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void forAllTest() {
    Attribute attr = PLMFactory.eINSTANCE.createAttribute();
    attr.setDatatype("Integer");
    attr.setValue("19");
    attr.setName("age");
    this.clabject.getFeature().add(attr);

    Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
    attr1.setDatatype("Integer");
    attr1.setValue("19");
    attr1.setName("age");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.getFeature().add(attr1);

    Collection<Clabject> collection = new ArrayList<>();
    collection.add(this.clabject);
    collection.add(c1);
    this.args = new Object[2];
    this.args[0] = collection;
    this.args[1] = "age <= 20";
    try {
      assertTrue((Boolean) this.wrapper.invoke("forAll", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
    this.args[1] = "age >= 20";
    try {
      assertFalse((Boolean) this.wrapper.invoke("forAll", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }

  }

  @Test
  public void collectTest() {
    Clabject transaction = PLMFactory.eINSTANCE.createEntity();
    transaction.setName("Transaction");
    Attribute points = PLMFactory.eINSTANCE.createAttribute();
    points.setName("points");
    points.setDatatype("Integer");
    points.setValue("103");
    transaction.getFeature().add(points);

    Attribute points1 = PLMFactory.eINSTANCE.createAttribute();
    points1.setName("points");
    points1.setDatatype("Integer");
    points1.setValue("99");
    this.clabject.getFeature().add(points1);

    List<Clabject> collection = new ArrayList<>();
    collection.add(transaction);
    collection.add(this.clabject);
    this.args = new Object[2];
    this.args[0] = collection;
    this.args[1] = "points";
    try {
      assertEquals(((Collection<?>) this.wrapper.invoke("collect", args)).size(), 2);
      assertEquals(((Collection<?>) this.wrapper.invoke("collect", args)).toArray()[0], 103);
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

  @Test
  public void anyTest() {
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    Attribute a1 = PLMFactory.eINSTANCE.createAttribute();
    a.setName("number");
    a1.setName("number");
    a.setDatatype("Integer");
    a1.setDatatype("Integer");
    a.setValue("100");
    a1.setValue("100000");
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.getFeature().add(a);
    this.clabject.getFeature().add(a1);

    List<Clabject> collection = new ArrayList<>();
    collection.add(this.clabject);
    collection.add(c);
    this.args = new Object[2];
    this.args[0] = collection;
    this.args[1] = "number < 10000";
    try {
      assertEquals(1, ((Collection<?>) this.wrapper.invoke("any", args)).size());
      assertEquals(c, ((Collection<?>) this.wrapper.invoke("any", args)).toArray()[0]);
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }

    ((Attribute) this.clabject.getFeature().get(0)).setValue("100");

    try {
      assertEquals(1, ((Collection<?>) this.wrapper.invoke("any", args)).size());
      assertEquals(this.clabject, ((Collection<?>) this.wrapper.invoke("any", args)).toArray()[0]);
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }

  }

  @Test
  public void existsTest() {
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    a.setName("points");
    a.setDatatype("Integer");
    a.setValue("10");
    this.clabject.getFeature().add(a);
    List<Clabject> collection = new ArrayList<>();
    collection.add(this.clabject);
    this.args = new Object[2];
    this.args[0] = collection;
    this.args[1] = "t | t.points > 0";
    try {
      assertTrue((Boolean) this.wrapper.invoke("exists", args));
    } catch (Exception e) {
      fail("something went wrong in the try/catch block");
      e.printStackTrace();
    }
  }

}
