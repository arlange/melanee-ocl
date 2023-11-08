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
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.core.models.plm.PLM.Subtype;
import org.melanee.core.models.plm.PLM.Supertype;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class DOCLStylesTests {
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
  public void testAbsenceOfNonMonotonicAbstractionStyle() {

    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    this.l2.getContent().add(clabjectA);

    Clabject clabjectX = PLMFactory.eINSTANCE.createEntity();
    clabjectX.setName("X");
    this.l1.getContent().add(clabjectX);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    this.l2.getContent().add(clabjectB);

    Clabject clabjectY = PLMFactory.eINSTANCE.createEntity();
    clabjectY.setName("Y");
    this.l3.getContent().add(clabjectY);

    Classification classificationXA = PLMFactory.eINSTANCE.createClassification();

    classificationXA.setInstance(clabjectA);
    classificationXA.setType(clabjectX);
    this.l2.getContent().add(classificationXA);

    Classification classificationBY = PLMFactory.eINSTANCE.createClassification();

    classificationBY.setInstance(clabjectB);
    classificationBY.setType(clabjectY);
    this.l2.getContent().add(classificationBY);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.getDirectInstances() -> forAll(inst|inst.#getLevelIndex()# > self.#getLevelIndex()#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        if (clabject.equals(clabjectY)) {
          assertFalse(Boolean.parseBoolean(returnValue.toString()));
        } else {
          assertTrue(Boolean.parseBoolean(returnValue.toString()));
        }
      }
    }
  }

  @Test
  public void absenceOfCircularClassificationTest() {
    // context is clabject
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    Clabject clabjectC = PLMFactory.eINSTANCE.createEntity();
    clabjectC.setName("C");

    this.l1.getContent().add(clabjectA);
    this.l2.getContent().add(clabjectB);
    this.l3.getContent().add(clabjectC);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);


    Classification classificationBC = PLMFactory.eINSTANCE.createClassification();
    classificationBC.setInstance(clabjectC);
    classificationBC.setType(clabjectB);
    this.l3.getContent().add(classificationBC);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self -> nonReflexiveClosure(clabject|clabject.#getDirectTypes()#) -> excludes(self)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        assertTrue(Boolean.parseBoolean(returnValue.toString()));
      }
    }

    Classification classificationCA = PLMFactory.eINSTANCE.createClassification();
    classificationCA.setInstance(clabjectA);
    classificationCA.setType(clabjectC);
    this.l1.getContent().add(classificationCA);

    // negative test, this should be false for every clabject of the model
    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        assertFalse(Boolean.parseBoolean(returnValue.toString()));
      }
    }
  }

  @Test
  public void absenceOfCircularInheritanceTest() {
    // context is clabject
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    Clabject clabjectC = PLMFactory.eINSTANCE.createEntity();
    clabjectC.setName("C");
    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");

    Inheritance inheritanceAB = PLMFactory.eINSTANCE.createInheritance();
    Supertype superA = PLMFactory.eINSTANCE.createSupertype();
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    superA.setSupertype(clabjectA);
    subB.setSubtype(clabjectB);
    inheritanceAB.getSupertype().add(superA);
    inheritanceAB.getSubtype().add(subB);

    Inheritance inheritanceBC = PLMFactory.eINSTANCE.createInheritance();
    Supertype superB = PLMFactory.eINSTANCE.createSupertype();
    Subtype subC = PLMFactory.eINSTANCE.createSubtype();
    superB.setSupertype(clabjectB);
    subC.setSubtype(clabjectC);
    inheritanceBC.getSupertype().add(superB);
    inheritanceBC.getSubtype().add(subC);

    Inheritance inheritanceCD = PLMFactory.eINSTANCE.createInheritance();
    Supertype superC = PLMFactory.eINSTANCE.createSupertype();
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    superC.setSupertype(clabjectC);
    subD.setSubtype(clabjectD);
    inheritanceCD.getSupertype().add(superC);
    inheritanceCD.getSubtype().add(subD);

    this.l1.getContent().add(clabjectA);
    this.l1.getContent().add(clabjectB);
    this.l1.getContent().add(clabjectC);
    this.l1.getContent().add(clabjectD);
    this.l1.getContent().add(inheritanceAB);
    this.l1.getContent().add(inheritanceBC);
    this.l1.getContent().add(inheritanceCD);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self -> nonReflexiveClosure(clabject|clabject.#getSupertypes()#) -> excludes(self)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        assertTrue(Boolean.parseBoolean(returnValue.toString()));
      }
    }

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);

    this.l1.getContent().add(inheritanceDA);


    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        assertFalse(Boolean.parseBoolean(returnValue.toString()));
      }
    }
  }

  @Test
  public void characterizationPotencyTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(3);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(2);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectC = PLMFactory.eINSTANCE.createEntity();
    clabjectC.setName("C");
    clabjectC.setPotency(1);
    this.l3.getContent().add(clabjectC);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l3.getContent().add(clabjectD);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationBC = PLMFactory.eINSTANCE.createClassification();
    classificationBC.setInstance(clabjectC);
    classificationBC.setType(clabjectB);
    this.l3.getContent().add(classificationBC);

    Classification classificationBD = PLMFactory.eINSTANCE.createClassification();
    classificationBD.setInstance(clabjectD);
    classificationBD.setType(clabjectB);
    this.l3.getContent().add(classificationBD);

    // context is clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.getDirectInstances() -> forAll(p|p.#getPotency()# < self.#getPotency()#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();

    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        assertTrue(Boolean.parseBoolean(returnValue.toString()));
      }
    }

    clabjectD.setPotency(99);
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectB);
    Object returnValue = visitor.visit(tree);
    assertFalse(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void characterizationInheritancePotencyTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(0);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l1.getContent().add(clabjectB);

    Clabject clabjectC = PLMFactory.eINSTANCE.createEntity();
    clabjectC.setName("C");
    clabjectC.setPotency(1);
    this.l1.getContent().add(clabjectC);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    this.l1.getContent().add(clabjectD);


    Inheritance inheritanceAB = PLMFactory.eINSTANCE.createInheritance();
    Supertype superA = PLMFactory.eINSTANCE.createSupertype();
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    superA.setSupertype(clabjectA);
    subB.setSubtype(clabjectB);
    inheritanceAB.getSupertype().add(superA);
    inheritanceAB.getSubtype().add(subB);

    Inheritance inheritanceAC = PLMFactory.eINSTANCE.createInheritance();
    Supertype superA1 = PLMFactory.eINSTANCE.createSupertype();
    Subtype subC = PLMFactory.eINSTANCE.createSubtype();
    superA1.setSupertype(clabjectA);
    subC.setSubtype(clabjectC);
    inheritanceAC.getSupertype().add(superA1);
    inheritanceAC.getSubtype().add(subC);

    Inheritance inheritanceBD = PLMFactory.eINSTANCE.createInheritance();
    Supertype superB = PLMFactory.eINSTANCE.createSupertype();
    Subtype subD = PLMFactory.eINSTANCE.createSubtype();
    superB.setSupertype(clabjectB);
    subD.setSubtype(clabjectD);
    inheritanceBD.getSupertype().add(superB);
    inheritanceBD.getSubtype().add(subD);

    this.l1.getContent().add(inheritanceAB);
    this.l1.getContent().add(inheritanceAC);
    this.l1.getContent().add(inheritanceBD);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("if self.#getPotency()# = 0 \n"
        + "        then (self.#getSubtypes()# -> exists(sub|sub.#getPotency()# > 0))\n"
        + "        else (self.#getSubtypes()# -> forAll(sub|sub.#getPotency()# >= \n"
        + "            self.#getPotency()#))\n" + "    endif"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        assertTrue(Boolean.parseBoolean(returnValue.toString()));
      }
    }
  }

  @Test
  public void durabilityClassificationTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Feature
    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("self.#getClabject()#.#getTypes()# -> first().#getFeatures()# ->\n"
            + "    iterate(feature; valid:Boolean=false|\n"
            + "        if feature.#name# = self.#name#\n"
            + "        then valid = feature.#getDurability()# > self.#getDurability()#\n"
            + "        else valid = false\n" + "        endif"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }


  @Test
  public void durabilityInheritanceTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Feature
    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("let durability = self.#getDurability()#  in \n"
            + "    self.#getDirectSuperType()#.#getAttributes()# -> select(a|a.#getName()# = self.#getName()#) -> iterate(a; result = true|\n"
            + "        if a.#getDurability()# = 0\n" + "            then result = true\n"
            + "            else if durability <> a.#getDurability()#\n"
            + "                then result = false\n" + "            endif\n" + "        endif)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void mutabilityClassificationTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Attribute
    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("self.#getClabject()#.#getTypes()# -> first().#getFeatures()# -> \n"
            + "    iterate(feature; valid:Boolean=false|\n"
            + "        if feature.#name# = self.#name# and feature.#getMutability()# > 0\n"
            + "        then valid = feature.#getMutability()# > self.#getMutability()#\n"
            + "        else if feature.#name# = self.#name# and feature.#getMutability()# = 0\n"
            + "            then valid = self.#getMutability()# = 0\n"
            + "            else valid = false\n" + "            endif\n" + "        endif"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void mutabilityInheritanceTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Attribute
    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("let mutability = self.#getMutability()#  in \n"
            + "    self.#getDirectSuperType()#.#getAttributes()# -> select(a|a.#getName()# = self.#getName()#) -> iterate(a; result = true|\n"
            + "        if a.#getDutability()# = 0 and a.#getMutability()# = 0\n"
            + "            then result = true\n"
            + "            else if a.#getMutability()# = 1 and mutability >= 1\n"
            + "                then result = false\n"
            + "            else if mutability <> a.#getMutability()#\n"
            + "                then result = false\n" + "                endif\n"
            + "            endif\n" + "        endif)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void strictModelingInstanceOfTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Connection
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getParticipants()# -> forAll(participant|participant.#getLevel()# = self.#getLeve()#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void strictModelingInheritanceTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Inheritance
    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("self.#superType#.#superType#.#levelIndex# -> asSet() -> size() = 1 \n"
            + "and self.#subType#.#subType#.#levelIndex# -> asSet() -> size() = 1 \n"
            + "and self.#superType#.#superType#.#levelIndex# -> asSet() -> first() =\n"
            + "self.#subType#.#subType#.#levelIndex# -> asSet() -> first()"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void strictMonotonicTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getDirectInstances()# -> forAll(clabject|clabject.#getLevelIndex()# = self.#getLevelIndex()# + 1)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void universalOntologicalClassificationStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is DeepModel
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> reject(c|c.#getLevel()#.#name# = 'O0') -> forAll(c|c.#getDirectType()# -> size() = 1)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void mandatorySelectiveClassificationStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is DeepModel
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Connection -> reject(c|c.#getLevel()#.#name# = 'O0') -> forAll(c|c.#getDirectType()# -> size() = 1)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void universalIsonymicStyleTest1() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is DeepModel
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> forAll(select(c|c.#getFeature()# -> select(f|f.#getDurability()# > 0)) -> size() = c.#getDirectInstances()# -> select(cc|cc.#getFeature()#) -> size())"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void universalIsonymicStyleTest2() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is DeepModel
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> forAll(select(c|c.#getFeature()# -> select(f|f.#getDurability()# > 0) -> collect(#name#)) -> includesAll(c.#getDirectInstances()# -> select(cc|cc.#getFeature()#) -> collect(#name#)))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  /**
   * If a clabject, I, satisfies the structural requirements needed to be an instance of a clabject,
   * T, (it is either a hyponym or isonym of T) I MUST be regarded as an instance of T (or,
   * alternatively, T must be regarded as a type of I).
   */
  @Test
  public void structuralTypingST1Test() {

    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> exists(c|(self.doclIsHyponymOf(c) or self.doclIsIsonymOf(c)) implies self.#isInstanceOf(c)#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  /**
   * If the set of clabjects that must be regarded as instances of a clabject X (according to
   * criteria ST1) is a proper subset of the set of clabjects that must be regarded as instances of
   * clabject Y, then Y MUST be regarded as a being a supertype of X, possibly with intermediate
   * types. In other words, X must be below Y in a chain of inheritance relationships,
   */
  @Test
  public void structuralTypingST2Test() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> exists(c|c.#getInstances()# -> includesAll(self.#getInstances()#) implies c.#getSubTypes()# -> includes(self))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  /**
   * If a clabject I must be an instance of T according to ST1, and there is no subtype of T that I
   * must be regarded as an instance of (the subtype) according to ST1, then T MUST be regarded as
   * the direct type of I. In other words, there must be a classification relationship between T and
   * I.
   */
  @Test
  public void structuralTypingST3Test() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> exists(c|(self.doclIsHyponymOf(c) or self.doclIsIsonymOf(c) and c.#getSubTypes()# -> size() = 0) implies self.#getDirectType()# = c)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  /**
   * A clabject cannot have more than one direct type.
   */
  @Test
  public void structuralTypingST4Test() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Clabject
    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("self.#getDirectType()# -> size() <= 1"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void exclusiveAbstractSupertypeStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Inheritance
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#supertype#.#supertype# -> forAll(super|super.#getPotency()# = 0 and super.#getDirectInstances()# -> size() = 0) and self.#complete# = true"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void exclusiveConcreteSupertypeStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Inheritance
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#supertype#.#supertype# -> forAll(super|super.#getPotency()# > 0) and self.#complete# = false"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void universalRootStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Level
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("if self.#isLeafLevel()#\n"
        + "    then true\n" + "    else self.#getClabjects()# -> select(c|c.#getSupertypes()# -> \n"
        + "    size() = 0) -> closure(c|c.#getSubtypes()#) \n"
        + "    -> includesAll(self.#getClabjects()#)\n" + "    endif"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void classicPotencyStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getDirectInstances()# -> forAll(c|c.#getPotency()# = self.#getPotency()# - 1)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void untypedAbstractTypeStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();
    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("if self.#getPotency()# = 0 and self.#getSubtypes()# -> size() > 0\n"
            + "        then self.#getDirectType()# -> isEmpty()\n" + "        else true\n"
            + "        endif"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void harminiousHorizontalSupertypesStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "not (self.#getSupertypes()# -> exists(super|super.oclIsTypeOf(self.#getDirectType()#))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void absenceOfMetacycleStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self -> nonReflexiveClosure(clabject|clabject.#getDirectTypes()# -> union(clabject.#getAllNavigations()#.#destination#)) -> excludes(self)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void absenceOfMetabombStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self -> nonReflexiveClosure(clabject|clabject.#getDirectTypes()#) -> excludesAll(self.#getAllNavigations()#.#destination#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void absenceOfTypeSupertypeStyleTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("self.#getSupertypes()# -> excludesAll(self.#getTypes()#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }
}
