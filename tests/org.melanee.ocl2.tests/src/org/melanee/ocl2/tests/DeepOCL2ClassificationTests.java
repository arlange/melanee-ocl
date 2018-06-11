/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Ralph Gerbig - initial API and implementation and initial documentation Arne Lange
 * - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collection;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

/**
 * This test class checks for Deep Classification
 * 
 * @author Arne Lange
 *
 */
public class DeepOCL2ClassificationTests {

  DeepModel dm;
  Level l1;
  Level l2;

  @Before
  public void setUp() {
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.l1 = PLMFactory.eINSTANCE.createLevel();
    this.l2 = PLMFactory.eINSTANCE.createLevel();
    dm.getContent().add(l1);
    dm.getContent().add(l2);
  }

  /**
   * This test should only return instances that are directly connected to the source clabject.
   * source.allInstances()
   */
  @Test
  public void allInstancesTest_One() {
    // c1 is the type for c2
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    Clabject c2 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("AutoType");
    c2.setName("SUV");
    this.l1.getContent().add(c1);
    this.l2.getContent().add(c2);

    // c1 -- c2 classification
    Classification classification = PLMFactory.eINSTANCE.createClassification();
    classification.setInstance(c2);
    classification.setType(c1);
    l1.getContent().add(classification);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.allInstances()"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(c1);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(c2), returnValue);
  }

  /**
   * This test should only return one level of the classification tree hierarchy. c1 - c2; not c2
   * and c3
   */
  @Test
  public void allInstancesTest_More() {
    Level l3 = PLMFactory.eINSTANCE.createLevel();
    this.dm.getContent().add(l3);

    // c1 is the type for c2
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    Clabject c2 = PLMFactory.eINSTANCE.createEntity();
    Clabject c3 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("AutoType");
    c2.setName("SUV");
    c3.setName("Mercedes G-Type");
    this.l1.getContent().add(c1);
    this.l2.getContent().add(c2);
    l3.getContent().add(c3);

    // c1 -- c2 classification
    Classification classification = PLMFactory.eINSTANCE.createClassification();
    classification.setInstance(c2);
    classification.setType(c1);
    l1.getContent().add(classification);

    // c1 -- c3 classification
    Classification classification1 = PLMFactory.eINSTANCE.createClassification();
    classification1.setInstance(c3);
    classification1.setType(c2);
    l2.getContent().add(classification1);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.allInstances()"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(c1);
    Object returnValue = visitor.visit(tree);
    assertTrue(((Collection) returnValue).contains(c2) && ((Collection) returnValue).contains(c3));
  }

  /**
   * This test should only return one level of the classification tree hierarchy. c1 - c2; not c2
   * and c3
   */
  @Test
  public void allInstancesTest_OneMore() {
    Level l3 = PLMFactory.eINSTANCE.createLevel();
    this.dm.getContent().add(l3);

    // c1 is the type for c2
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    Clabject c2 = PLMFactory.eINSTANCE.createEntity();
    Clabject c3 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("AutoType");
    c2.setName("SUV");
    c3.setName("Mercedes G-Type");
    this.l1.getContent().add(c1);
    this.l2.getContent().add(c2);
    l3.getContent().add(c3);

    // c1 -- c2 classification
    Classification classification = PLMFactory.eINSTANCE.createClassification();
    classification.setInstance(c2);
    classification.setType(c1);
    l1.getContent().add(classification);

    // c1 -- c3 classification
    Classification classification1 = PLMFactory.eINSTANCE.createClassification();
    classification1.setInstance(c3);
    classification1.setType(c2);
    l2.getContent().add(classification1);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.allInstances()"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(c2);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(c3), returnValue);
  }

  @Test
  public void oclIsKindOfNotClabjectTest() {
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("Test");
    l1.getContent().add(c);
    Attribute attr = PLMFactory.eINSTANCE.createAttribute();
    attr.setName("attr");
    c.getFeature().add(attr);
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.attr.oclIsKindOf(Level)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(c);
    Object returnValue = visitor.visit(tree);
    System.out.println(returnValue);
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void oclIsKindOfClabjectTest() {
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("Test");
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.oclIsKindOf(Clabject)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(c);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void oclIsKindOfAttributeTest() {
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("Test");
    Attribute attr = PLMFactory.eINSTANCE.createAttribute();
    attr.setName("attr");
    c.getFeature().add(attr);
    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("self.attr.oclIsKindOf(Attribute)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(c);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void oclIsTypeOfClabjectTest() {
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("Test");
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.oclIsTypeOf(Clabject)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(c);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void oclIsTypeOfAttributeTest() {
    Clabject c = PLMFactory.eINSTANCE.createEntity();
    c.setName("Test");
    Attribute attr = PLMFactory.eINSTANCE.createAttribute();
    attr.setName("attr");
    c.getFeature().add(attr);
    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("self.attr.oclIsTypeOf(Attribute)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(c);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

}
