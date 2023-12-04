package org.melanee.ocl2.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.ConnectionEndKind;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.core.models.plm.PLM.Subtype;
import org.melanee.core.models.plm.PLM.Supertype;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class DOCLPatternTests {
  private DeepModel dm;
  private Level l1;
  private Level l2;
  private Level l3;

  @Before
  public void setUp() {
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.l1 = PLMFactory.eINSTANCE.createLevel();
    this.l2 = PLMFactory.eINSTANCE.createLevel();
    this.l3 = PLMFactory.eINSTANCE.createLevel();
    dm.getContent().add(l1);
    dm.getContent().add(l2);
    dm.getContent().add(l3);
  }

  @After
  public void tearDown() {
    this.l1 = null;
    this.l2 = null;
    this.l3 = null;
    this.dm = null;
  }

  @Test
  public void abstractClabjectPatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(0);
    this.l3.getContent().add(clabjectY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationBY = PLMFactory.eINSTANCE.createClassification();
    classificationBY.setInstance(clabjectY);
    classificationBY.setType(clabjectB);
    this.l3.getContent().add(classificationBY);

    Inheritance inheritanceXA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superX.setSupertype(clabjectX);
    subA.setSubtype(clabjectA);
    superX.setInheritance(inheritanceXA);
    subA.setInheritance(inheritanceXA);
    this.l1.getContent().add(inheritanceXA);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getSubtypes()# -> size() > 0 and self.#getPotency()# = 0 and self.doclGetDirectInstances() -> size() = 0"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectX);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void individualClabjectPatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(0);
    this.l3.getContent().add(clabjectY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationBY = PLMFactory.eINSTANCE.createClassification();
    classificationBY.setType(clabjectB);
    classificationBY.setInstance(clabjectY);
    this.l3.getContent().add(classificationBY);

    Inheritance inheritanceXA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superX.setSupertype(clabjectX);
    subA.setSubtype(clabjectA);
    superX.setInheritance(inheritanceXA);
    subA.setInheritance(inheritanceXA);
    this.l1.getContent().add(inheritanceXA);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getSupertypes()# -> size() = 0 and self.#getSubtypes()# -> size() = 0 and self.#getPotency()# = 0 and self.doclGetDirectInstances() -> size() = 0"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectY);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void singletonClabjectPatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(0);
    this.l3.getContent().add(clabjectY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationBY = PLMFactory.eINSTANCE.createClassification();
    classificationBY.setType(clabjectB);
    classificationBY.setInstance(clabjectY);
    this.l3.getContent().add(classificationBY);

    Inheritance inheritanceXA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superX.setSupertype(clabjectX);
    subA.setSubtype(clabjectA);
    superX.setInheritance(inheritanceXA);
    subA.setInheritance(inheritanceXA);
    this.l1.getContent().add(inheritanceXA);

    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("self.doclGetDirectInstances() -> size() = 1"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void classicPotencyPatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(0);
    this.l3.getContent().add(clabjectY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationBY = PLMFactory.eINSTANCE.createClassification();
    classificationBY.setType(clabjectB);
    classificationBY.setInstance(clabjectY);
    this.l3.getContent().add(classificationBY);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.doclGetDirectOffspring() -> iterate(offspring; currentPotency:Integer = self.#getPotency()#| currentPotency - offspring.#getPotency()#) = 1"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void basicDiscriminationPatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l1.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(0);
    this.l1.getContent().add(clabjectY);

    Inheritance inheritanceXDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDA);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDA);
    subD.setSubtype(clabjectD);
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    subA.setInheritance(inheritanceXDA);
    subA.setSubtype(clabjectA);
    this.l1.getContent().add(inheritanceXDA);

    Inheritance inheritanceYB = PLMFactory.eINSTANCE.createInheritance();
    Supertype superY = PLMFactory.eINSTANCE.createSupertype();
    superY.setInheritance(inheritanceYB);
    superY.setSupertype(clabjectY);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceYB);
    subB.setSubtype(clabjectB);
    this.l1.getContent().add(inheritanceYB);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "(self.#getSupertypes()# -> size() = 1 and self.#getSupertypes()#.#getPotency()# > 0) or self.#getSubtypes()# -> size() >= 2"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(inheritanceXDA);
    Object returnValue = visitor.visit(tree);

    assertTrue(Boolean.parseBoolean(returnValue.toString()));

    DeepOclRuleVisitor visitor1 = new DeepOclRuleVisitor(inheritanceYB);
    Object returnValue1 = visitor1.visit(tree);

    assertFalse(Boolean.parseBoolean(returnValue1.toString()));
  }

  @Test
  public void completeDiscriminationPatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l1.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(0);
    this.l1.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);


    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(1);
    this.l2.getContent().add(clabjectE);

    Classification classificationDE = PLMFactory.eINSTANCE.createClassification();
    classificationDE.setInstance(clabjectE);
    classificationDE.setType(clabjectD);
    this.l2.getContent().add(classificationDE);



    Inheritance inheritanceXDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDA);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDA);
    subD.setSubtype(clabjectD);
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    subA.setInheritance(inheritanceXDA);
    subA.setSubtype(clabjectA);
    this.l1.getContent().add(inheritanceXDA);

    assertTrue(inheritanceXDA.getSupertypes().size() == 1);
    assertTrue(inheritanceXDA.getSubtypes().size() >= 2);


    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let type: Clabject = self.#getSupertypes()# -> first() in self.#getSupertypes()# -> size() = 1 and self.#getSupertypes()# -> forAll(s|s.#getPotency()# = 0) and self.#getSubtypes()# -> size() >= 2 and self.#getSubtypes()#.doclGetInstances() -> forAll(i|i.doclIsInstanceOf(type))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(inheritanceXDA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void disjointDiscriminationPatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l1.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(0);
    this.l1.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);


    Inheritance inheritanceXDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDA);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDA);
    subD.setSubtype(clabjectD);
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    subA.setInheritance(inheritanceXDA);
    subA.setSubtype(clabjectA);
    this.l1.getContent().add(inheritanceXDA);

    Inheritance inheritanceYB = PLMFactory.eINSTANCE.createInheritance();
    Supertype superY = PLMFactory.eINSTANCE.createSupertype();
    superY.setInheritance(inheritanceYB);
    superY.setSupertype(clabjectY);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceYB);
    subB.setSubtype(clabjectB);
    this.l1.getContent().add(inheritanceYB);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getSupertypes()# -> size() = 1 and self.#getSupertypes()# -> first().#getPotency()# = 0 and self.#getSubtypes()# -> size() >= 2 and self.#getSubtypes()# -> forAll(s|s.#getInstances()# -> excludesAll(self.#getSubtypes()# -> excluding(s).#getInstances()#))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(inheritanceXDA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void partinionedDiscriminationPatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l1.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(0);
    this.l1.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);


    Inheritance inheritanceXDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDA);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDA);
    subD.setSubtype(clabjectD);
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    subA.setInheritance(inheritanceXDA);
    subA.setSubtype(clabjectA);
    this.l1.getContent().add(inheritanceXDA);

    Inheritance inheritanceYB = PLMFactory.eINSTANCE.createInheritance();
    Supertype superY = PLMFactory.eINSTANCE.createSupertype();
    superY.setInheritance(inheritanceYB);
    superY.setSupertype(clabjectY);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceYB);
    subB.setSubtype(clabjectB);
    this.l1.getContent().add(inheritanceYB);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getSupertypes()# -> size() = 1 and self.#getSupertypes()# -> first().#getPotency()# = 0 and self.#getSubtypes()# -> size() >= 2 and self.#getSubtypes()# -> forAll(sub|sub.#getInstanes()# -> excludesAll(self.#getSubtypes()# -> excluding(s).#getInstances()#))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(inheritanceXDA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

/*  @Test
  public void compositePatternTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l1.getContent().add(clabjectX);


    Inheritance inheritanceXDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDA);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDA);
    subD.setSubtype(clabjectD);
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    subA.setInheritance(inheritanceXDA);
    subA.setSubtype(clabjectA);
    this.l1.getContent().add(inheritanceXDA);

    Connection connecetionXA = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd endA = PLMFactory.eINSTANCE.createConnectionEnd();
    endA.setConnection(connecetionXA);
    endA.setDestination(clabjectA);
    endA.setKind(ConnectionEndKind.COMPOSITION);
    ConnectionEnd endX = PLMFactory.eINSTANCE.createConnectionEnd();
    endX.setConnection(connecetionXA);
    endX.setDestination(clabjectX);

    // System.out.println(endA.getKind().getName());

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getPotency()# = 0 and self.#getSubtypes()# -> size() > 1 and self.#getConnections()# -> exists(c|c.#getAllConnectionEnd()# -> size() = 2 and c.#getAllConnectionEnd()# -> one(cE|cE.#getDestination()# = self) and c.#getAllConnectionEnd()# -> one(cE|self.#getSubtypes()# -> includes(cE.#getDestination()#))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectX);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }*/

  @Test
  public void basicCharacterizationPattern() {
    // A is powertype and X is basetype
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l2.getContent().add(clabjectX);


    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    this.l2.getContent().add(clabjectD);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(1);
    this.l2.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);

    Classification classificationAY = PLMFactory.eINSTANCE.createClassification();
    classificationAY.setInstance(clabjectY);
    classificationAY.setType(clabjectA);
    this.l2.getContent().add(classificationAY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    // Inheritance where X is supertype and D,B,Y,F are subtypes
    Inheritance inheritanceXDBYF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDBYF);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDBYF);
    subD.setSubtype(clabjectD);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceXDBYF);
    subB.setSubtype(clabjectB);
    Subtype subY = PLMFactory.eINSTANCE.createSubtype();
    subY.setInheritance(inheritanceXDBYF);
    subY.setSubtype(clabjectY);
    Subtype subF = PLMFactory.eINSTANCE.createSubtype();
    subF.setInheritance(inheritanceXDBYF);
    subF.setSubtype(clabjectF);
    this.l2.getContent().add(inheritanceXDBYF);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritance:Inheritance = self.doclGetDirectInstances() -> first().#getInheritancesAsSubtype()# -> first() in\n"
            + "        let baseType:Clabject = inheritance.#getSupertypes()# -> first() in\n"
            + "        self.doclGetDirectInstances() -> forAll(inst|inst.#getInheritancesAsSubtype()# -> first() = inheritance)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void completeCharacterizationPattern() {
    // A is powertype and X is basetype
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l2.getContent().add(clabjectX);


    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    this.l2.getContent().add(clabjectD);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(1);
    this.l2.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(0);
    this.l3.getContent().add(clabjectE);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);

    Classification classificationAY = PLMFactory.eINSTANCE.createClassification();
    classificationAY.setInstance(clabjectY);
    classificationAY.setType(clabjectA);
    this.l2.getContent().add(classificationAY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    Classification classificationFE = PLMFactory.eINSTANCE.createClassification();
    classificationFE.setInstance(clabjectE);
    classificationFE.setType(clabjectF);
    this.l3.getContent().add(classificationFE);

    // Inheritance where X is supertype and D,B,Y,F are subtypes E instance of F
    Inheritance inheritanceXDBYF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDBYF);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDBYF);
    subD.setSubtype(clabjectD);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceXDBYF);
    subB.setSubtype(clabjectB);
    Subtype subY = PLMFactory.eINSTANCE.createSubtype();
    subY.setInheritance(inheritanceXDBYF);
    subY.setSubtype(clabjectY);
    Subtype subF = PLMFactory.eINSTANCE.createSubtype();
    subF.setInheritance(inheritanceXDBYF);
    subF.setSubtype(clabjectF);
    this.l2.getContent().add(inheritanceXDBYF);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritance:Inheritance = self.doclGetDirectInstances() -> first().#getInheritancesAsSubtype()# -> first() in\n"
            + "        let baseType:Clabject = inheritance.#getSupertypes()# -> first() in\n"
            + "        self.doclGetDirectInstances() -> forAll(inst|inst.#getInheritancesAsSubtype()# -> first() = inheritance)\n"
            + "        baseType.#getPotency()# = 0 and\n"
            + "        baseType.#getSubtypes()#.doclGetDirectInstances() -> forAll(inst|inst.doclIsIndirectInstanceOf(baseType))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void disjointCharacterizationPattern() {
    // A is powertype and X is basetype
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l2.getContent().add(clabjectX);


    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    this.l2.getContent().add(clabjectD);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(1);
    this.l2.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(0);
    this.l3.getContent().add(clabjectE);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);

    Classification classificationAY = PLMFactory.eINSTANCE.createClassification();
    classificationAY.setInstance(clabjectY);
    classificationAY.setType(clabjectA);
    this.l2.getContent().add(classificationAY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    Classification classificationFE = PLMFactory.eINSTANCE.createClassification();
    classificationFE.setInstance(clabjectE);
    classificationFE.setType(clabjectF);
    this.l3.getContent().add(classificationFE);

    // Inheritance where X is supertype and D,B,Y,F are subtypes E instance of F
    Inheritance inheritanceXDBYF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDBYF);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDBYF);
    subD.setSubtype(clabjectD);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceXDBYF);
    subB.setSubtype(clabjectB);
    Subtype subY = PLMFactory.eINSTANCE.createSubtype();
    subY.setInheritance(inheritanceXDBYF);
    subY.setSubtype(clabjectY);
    Subtype subF = PLMFactory.eINSTANCE.createSubtype();
    subF.setInheritance(inheritanceXDBYF);
    subF.setSubtype(clabjectF);
    this.l2.getContent().add(inheritanceXDBYF);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritance:Inheritance = self.doclGetDirectInstances() -> first().#getInheritancesAsSubtype()# -> first() in\n"
            + "let baseType:Clabject = inheritance.#getSupertypes()# -> first() in\n"
            + "self.doclGetDirectInstances() -> forAll(inst|inst.#getInheritancesAsSubtype()# -> first() = inheritance) \n"
            + "and  baseType.doclGetInstances() -> forAll(baseInst|baseType.#getSubtypes()# -> \n"
            + "excluding(baseInst.#getDirectType()#) ->  (not exists(sub|baseInst.doclIsInstanceOf(sub))))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void partitionedCharacterizationPattern() {
    // A is powertype and X is basetype
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l2.getContent().add(clabjectX);


    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    this.l2.getContent().add(clabjectD);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(1);
    this.l2.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(0);
    this.l3.getContent().add(clabjectE);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);

    Classification classificationAY = PLMFactory.eINSTANCE.createClassification();
    classificationAY.setInstance(clabjectY);
    classificationAY.setType(clabjectA);
    this.l2.getContent().add(classificationAY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    Classification classificationFE = PLMFactory.eINSTANCE.createClassification();
    classificationFE.setInstance(clabjectE);
    classificationFE.setType(clabjectF);
    this.l3.getContent().add(classificationFE);

    // Inheritance where X is supertype and D,B,Y,F are subtypes E instance of F
    Inheritance inheritanceXDBYF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDBYF);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDBYF);
    subD.setSubtype(clabjectD);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceXDBYF);
    subB.setSubtype(clabjectB);
    Subtype subY = PLMFactory.eINSTANCE.createSubtype();
    subY.setInheritance(inheritanceXDBYF);
    subY.setSubtype(clabjectY);
    Subtype subF = PLMFactory.eINSTANCE.createSubtype();
    subF.setInheritance(inheritanceXDBYF);
    subF.setSubtype(clabjectF);
    this.l2.getContent().add(inheritanceXDBYF);


    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritance:Inheritance = self.doclGetDirectInstances() -> first().#getInheritancesAsSubtype()# -> first() in\n"
            + "let baseType:Clabject = inheritance.#getSupertypes()# -> first() in\n"
            + "self.doclGetDirectInstances() -> forAll(inst|inst.#getInheritancesAsSubtype()# -> first() = inheritance) and\n"
            + "baseType.doclGetInstances() -> forAll(baseInst|baseType.#getSubtypes()# -> excluding(baseInst.#getDirectType()#) -> (not exists(sub|baseInst.doclIsInstanceOf(sub))))\n"
            + "and baseType.#getPotency()# = 0"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void strongOdellCharacterizationPattern() {
    // A is powertype and X is basetype
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l2.getContent().add(clabjectX);


    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    this.l2.getContent().add(clabjectD);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(1);
    this.l2.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(0);
    this.l3.getContent().add(clabjectE);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);

    Classification classificationAY = PLMFactory.eINSTANCE.createClassification();
    classificationAY.setInstance(clabjectY);
    classificationAY.setType(clabjectA);
    this.l2.getContent().add(classificationAY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    Classification classificationFE = PLMFactory.eINSTANCE.createClassification();
    classificationFE.setInstance(clabjectE);
    classificationFE.setType(clabjectF);
    this.l3.getContent().add(classificationFE);

    // Inheritance where X is supertype and D,B,Y,F are subtypes E instance of F
    Inheritance inheritanceXDBYF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDBYF);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDBYF);
    subD.setSubtype(clabjectD);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceXDBYF);
    subB.setSubtype(clabjectB);
    Subtype subY = PLMFactory.eINSTANCE.createSubtype();
    subY.setInheritance(inheritanceXDBYF);
    subY.setSubtype(clabjectY);
    Subtype subF = PLMFactory.eINSTANCE.createSubtype();
    subF.setInheritance(inheritanceXDBYF);
    subF.setSubtype(clabjectF);
    this.l2.getContent().add(inheritanceXDBYF);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritance:Inheritance = self.doclGetDirectInstances() -> first().#getInheritancesAsSubtype()# -> first() in\n"
            + "        let baseType:Clabject = inheritance.#getSupertypes()# -> first() in\n"
            + "        self.doclGetDirectInstances() -> forAll(inst|inst.#getInheritancesAsSubtype()# -> first() = inheritance) and\n"
            + "        baseType.#getSubtypes()# -> forAll(sub|sub.doclIsInstanceOf(self))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void cardelliCharacterizationPattern() {
    // A is powertype and X is basetype
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    clabjectX.setPotency(0);
    this.l2.getContent().add(clabjectX);


    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    this.l2.getContent().add(clabjectD);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    clabjectY.setPotency(1);
    this.l2.getContent().add(clabjectY);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(0);
    this.l3.getContent().add(clabjectE);


    Classification classificationAX = PLMFactory.eINSTANCE.createClassification();
    classificationAX.setInstance(clabjectX);
    classificationAX.setType(clabjectA);
    this.l2.getContent().add(classificationAX);

    Classification classificationAF = PLMFactory.eINSTANCE.createClassification();
    classificationAF.setInstance(clabjectF);
    classificationAF.setType(clabjectA);
    this.l2.getContent().add(classificationAF);

    Classification classificationAY = PLMFactory.eINSTANCE.createClassification();
    classificationAY.setInstance(clabjectY);
    classificationAY.setType(clabjectA);
    this.l2.getContent().add(classificationAY);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    Classification classificationFE = PLMFactory.eINSTANCE.createClassification();
    classificationFE.setInstance(clabjectE);
    classificationFE.setType(clabjectF);
    this.l3.getContent().add(classificationFE);

    // Inheritance where X is supertype and D,B,Y,F are subtypes E instance of F
    Inheritance inheritanceXDBYF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superX = PLMFactory.eINSTANCE.createSupertype();
    superX.setInheritance(inheritanceXDBYF);
    superX.setSupertype(clabjectX);
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    subD.setInheritance(inheritanceXDBYF);
    subD.setSubtype(clabjectD);
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    subB.setInheritance(inheritanceXDBYF);
    subB.setSubtype(clabjectB);
    Subtype subY = PLMFactory.eINSTANCE.createSubtype();
    subY.setInheritance(inheritanceXDBYF);
    subY.setSubtype(clabjectY);
    Subtype subF = PLMFactory.eINSTANCE.createSubtype();
    subF.setInheritance(inheritanceXDBYF);
    subF.setSubtype(clabjectF);
    this.l2.getContent().add(inheritanceXDBYF);


    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritance:Inheritance = self.doclGetDirectInstances() -> reject(inst|inst.#getSupertypes()# -> size() = 0) -> first().#getInheritancesAsSubtype()# -> first() in\n"
            + "        let baseType:Clabject = inheritance.#getSupertypes()# -> first() in\n"
            + "        self.doclGetDirectInstances() -> reject(inst|inst.#getSupertypes()# -> size() = 0) -> forAll(inst|inst.#getInheritancesAsSubtype()# -> first() = inheritance) and\n"
            + "        baseType.doclIsInstanceOf(self) and \n"
            + "        baseType.#getSubtypes()# -> forAll(sub|sub.doclIsInstanceOf(self))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }
}
