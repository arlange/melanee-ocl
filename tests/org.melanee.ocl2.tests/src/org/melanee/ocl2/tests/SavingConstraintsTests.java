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

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.AbstractConstraint;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Enumeration;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.models.definition.constraint.Constraint;
import org.melanee.ocl2.models.definition.constraint.Expression;
import org.melanee.ocl2.models.definition.constraint.Pointer;
import org.melanee.ocl2.models.definition.constraint.Text;
import org.melanee.ocl2.service.DeepOclPersistenceService;

/**
 * kind of obsolete now, because saving is done slightly different now.
 * 
 * @author Arne Lange
 *
 */
public class SavingConstraintsTests {
  @Test
  public void testInv() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_inv.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("Customer");
    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setName("age");
    c.getFeature().add(attribute);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testInv1() throws FileNotFoundException, IOException {
    /**
     * there is a special case here, because the method isBerfore is in a utility
     * Date class that is not connected to the clabject CustomerCard
     */
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_inv1.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("CustomerCard");
    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setName("validFrom");
    c.getFeature().add(attribute);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testInv2() throws FileNotFoundException, IOException {
    /**
     * there is a special case here, because the method isBerfore is in a utility
     * Date class that is not connected to the clabject CustomerCard
     */
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_inv2.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    Clabject c2 = PLMFactory.eINSTANCE.createEntity();
    c.setName("LoyaltyProgram");
    c1.setName("Membership");
    c2.setName("ServiceLevel");

    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);
    l.getContent().add(c2);

    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("currentLevel");
    parti.setConnection(connect);
    parti.setDestination(c1);
    parti2.setDestination(c2);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything.trim(), oclExpression.toString().trim());
  }

  @Test
  public void testInv3() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_inv3.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("Membership");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("ServiceLevel");
    Clabject c2 = PLMFactory.eINSTANCE.createEntity();
    c2.setName("CustomerCard");
    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setName("name");
    Attribute attribute1 = PLMFactory.eINSTANCE.createAttribute();
    attribute1.setName("color");
    c1.getFeature().add(attribute);
    c2.getFeature().add(attribute1);
    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);
    l.getContent().add(c2);

    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("currentLevel");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    Connection connect1 = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti3 = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti4 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect1.setName("card");
    parti3.setConnection(connect1);
    parti3.setDestination(c);
    parti4.setDestination(c2);
    parti4.setNavigable(true);
    parti4.setConnection(connect1);
    l.getContent().add(connect1);

    Enumeration enumeration = PLMFactory.eINSTANCE.createEnumeration();
    enumeration.setName("Color");
    enumeration.getLiteral().add("silver");
    enumeration.getLiteral().add("gold");
    dm.getEnumeration().add(enumeration);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testInv4() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_inv4.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("LoyaltyProgram");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("Partner");
    Clabject c2 = PLMFactory.eINSTANCE.createEntity();
    c2.setName("Service");
    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);
    l.getContent().add(c2);

    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("partners");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    Connection connect1 = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti3 = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti4 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect1.setName("deliveredServices");
    parti3.setConnection(connect1);
    parti3.setDestination(c1);
    parti4.setDestination(c2);
    parti4.setNavigable(true);
    parti4.setConnection(connect1);
    l.getContent().add(connect1);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testInv5() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_inv5.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("LoyaltyProgram");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("Partner");
    Clabject c2 = PLMFactory.eINSTANCE.createEntity();
    c2.setName("Service");
    Clabject c3 = PLMFactory.eINSTANCE.createEntity();
    c3.setName("Membership");
    Clabject c4 = PLMFactory.eINSTANCE.createEntity();
    c4.setName("LoyaltyAccount");
    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);
    l.getContent().add(c2);
    l.getContent().add(c3);
    l.getContent().add(c4);

    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("partners");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    Connection connect1 = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti3 = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti4 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect1.setName("deliveredServices");
    parti3.setConnection(connect1);
    parti3.setDestination(c1);
    parti4.setDestination(c2);
    parti4.setNavigable(true);
    parti4.setConnection(connect1);
    l.getContent().add(connect1);

    Connection connect2 = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti5 = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti6 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect1.setName("account");
    parti5.setConnection(connect2);
    parti5.setDestination(c3);
    parti6.setDestination(c4);
    parti6.setNavigable(true);
    parti6.setConnection(connect2);
    l.getContent().add(connect2);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testInv6() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_inv6.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("LoyaltyProgram");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("ServiceLevel");
    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);

    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("levels");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testBody1() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_body1.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("Partner");
    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setName("deliveredServices");
    c1.getFeature().add(attribute);

    c.setName("LoyaltyProgram");
    Method method = PLMFactory.eINSTANCE.createMethod();
    method.setName("getServices()");
    c.getFeature().add(method);
    dm.getContent().add(l);
    l.getContent().add(c);

    // connection between c and d
    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("partners");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testBody() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_body.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("Partner");
    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setName("deliveredServices");
    c1.getFeature().add(attribute);

    c.setName("LoyaltyProgram");
    Method method = PLMFactory.eINSTANCE.createMethod();
    method.setName("getServices()");
    c.getFeature().add(method);
    dm.getContent().add(l);
    l.getContent().add(c);

    // connection between c and d
    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("partners");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testDef() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_def.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();

    c.setName("LoyaltyAccount");
    c1.setName("Test");
    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);

    // connection between c and d
    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("transactions");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testDef1() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_def1.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();

    c.setName("LoyaltyProgram");
    c1.setName("Test");
    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);

    // connection between c and d
    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("levels");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testDef2() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_def2.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();

    c.setName("Membership");
    c1.setName("ServiceLevel");

    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setName("name");
    c1.getFeature().add(attribute);

    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);

    // connection between c and d
    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("currentLevel");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testDerive() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_derive.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    a.setName("printedname");
    Attribute a2 = PLMFactory.eINSTANCE.createAttribute();
    a2.setName("name");
    Attribute a1 = PLMFactory.eINSTANCE.createAttribute();
    a1.setName("title");
    c.setName("CustomerCard");
    c.getFeature().add(a);
    c1.setName("Owner");
    c1.getFeature().add(a1);
    c1.getFeature().add(a2);
    dm.getContent().add(l);
    l.getContent().add(c);
    l.getContent().add(c1);

    // connection between c and d
    Connection connect = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
    connect.setName("owner");
    parti.setConnection(connect);
    parti.setDestination(c);
    parti2.setDestination(c1);
    parti2.setNavigable(true);
    parti2.setConnection(connect);
    l.getContent().add(connect);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testInit() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_init.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    a.setName("valid");
    c.setName("CustomerCard");
    c.getFeature().add(a);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testInit1() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_init1.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    c.setName("LoyaltyAccount");
    a.setName("points");
    c.getFeature().add(a);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testLet() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_let.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    Attribute a1 = PLMFactory.eINSTANCE.createAttribute();
    a.setName("validFrom");
    a1.setName("goodThru");
    c.setName("CustomerCard");
    c.getFeature().add(a);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testPost() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_post.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    Method m = PLMFactory.eINSTANCE.createMethod();
    m.setName("birthdayHappens()");
    a.setName("age");
    c.setName("Customer");
    c.getFeature().add(a);
    c.getFeature().add(m);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testPost1() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_post1.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    Method m = PLMFactory.eINSTANCE.createMethod();
    m.setName("calcPoints()");
    Method m1 = PLMFactory.eINSTANCE.createMethod();
    m1.setName("upgradePointsEarned()");
    a.setName("age");
    c.setName("Service");
    c.getFeature().add(a);
    c.getFeature().add(m);
    c.getFeature().add(m1);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testPost2() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_post2.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Attribute a = PLMFactory.eINSTANCE.createAttribute();
    Method m = PLMFactory.eINSTANCE.createMethod();
    m.setName("hasChanged()");
    a.setName("observer");
    c.setName("Subject");
    c.getFeature().add(a);
    c.getFeature().add(m);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void testPrePost() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_prePost.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    Method m = PLMFactory.eINSTANCE.createMethod();
    m.setName("enroll()");
    c.setName("LoyaltyProgram");
    c.getFeature().add(m);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);
    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());
  }

  @Test
  public void saveOtherConstraintWithSameName() throws FileNotFoundException, IOException {
    String everything = "";
    try (BufferedReader br = new BufferedReader(
        new FileReader("src/org/melanee/ocl2/tests/resources/rl_inv.ocl"))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      everything = sb.toString();
    }

    DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
    Level l = PLMFactory.eINSTANCE.createLevel();
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("Customer");
    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setName("age");
    c.getFeature().add(attribute);
    dm.getContent().add(l);
    l.getContent().add(c);

    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(c);

    saveConstraints.save(everything);
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = c.getConstraint();
    for (AbstractConstraint constraint : constraints) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.eClass()
            .getInstanceTypeName() == "org.melanee.ocl2.models.definition.constraint.Pointer") {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + everything);
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(everything, oclExpression.toString());

    StringBuilder sb = new StringBuilder();
    sb.append("context Customer");
    sb.append(System.lineSeparator());
    sb.append("inv ofAge: age = 18");

    saveConstraints.save(sb.toString());

    oclExpression = new StringBuilder();

    EList<AbstractConstraint> constraints1 = c.getConstraint();
    for (AbstractConstraint constraint : constraints1) {
      Constraint constr = (Constraint) constraint;
      EList<Expression> expressions = constr.getExpression();
      for (Expression expression : expressions) {
        // System.out.println(expression.eClass().getInstanceTypeName());
        if (expression.getClass().getSimpleName().equals("PointerImpl")) {
          Pointer pointer = (Pointer) expression;
          oclExpression.append(pointer.getPointer().getName());
        } else {
          Text text = (Text) expression;
          oclExpression.append(text.getText());
        }
      }
    }
    System.out.println("Original: \n" + sb.toString());
    System.out.println("Generated: \n" + oclExpression);
    assertEquals(sb.toString().trim(), oclExpression.toString().trim());
  }

}
