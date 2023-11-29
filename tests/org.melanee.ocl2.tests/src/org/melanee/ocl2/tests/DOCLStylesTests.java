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
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
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
    clabjectB.setPotency(2);
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
        + "        else (self.#getSubtypes()# -> forAll(sub|sub.#getPotency()# <= \n"
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
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);



    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);

    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);

    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);

    this.l2.getContent().add(classificationAB);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "if self.#getDirectType()# -> size() = 1 then true else self.#getDirectType()#.#getDefinedAttributes()# -> select(attr|attr.#durability# > 0) -> collect(#name#) -> includesAll(self.#getAllAttributes()# -> collect(#name#)) endif"));
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
  public void durabilityInheritanceTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("y");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);


    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getDirectSupertypes()#.#getAllAttributes()# -> reject(a|a.#getDurability()# = 0) -> forAll(a| let name:String = a.#name# in self.#getAttributeByName(name)#.#getDurability# = a.#getDurability()#)"));
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
  public void mutabilityClassificationTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);



    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);

    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);

    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);

    // context is Attribute
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getClabject()#.#getDirectType()#.#getDefinedAttributes()# -> reject(a|a.#getDurability()# = 0) -> forAll(a| if a.#name# = self.#name# and a.#getMutabilityAsString()# > 0 then a.#getMutabilityAsString()# > self.#getMutabilityAsString()# else if a.#name# = self.#name# and a.#getMutabilityAsString()# = 0 then self.#getMutabilityAsString()# = 0 else true endif endif)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();

    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        for (Attribute attribute : clabject.getAllAttributes()) {
          DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(attribute);
          Object returnValue = visitor.visit(tree);
          assertTrue(Boolean.parseBoolean(returnValue.toString()));
        }
      }
    }
  }

  @Test
  public void mutabilityInheritanceTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);

    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("a");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);

    // context is Attribute
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let mutability:Integer = self.#getMutability()#  in self.#getClabject()#.#getDirectSupertypes()#.#getAllAttributes()# -> select(a|a.#getName()# = self.#getName()# and a.#getDurability()# > 0) -> forAll(a| if a.#getMutability()# = 1 and mutability >= 1 then false else if mutability <> a.#getMutability()# then false else true endif endif)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        for (Attribute attribute : clabject.getAllAttributes()) {
          DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(attribute);
          Object returnValue = visitor.visit(tree);
          assertTrue(Boolean.parseBoolean(returnValue.toString()));
        }
      }
    }
  }

  @Test
  public void strictModelingInstanceOfTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l1.getContent().add(clabjectD);


    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);

    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);


    Connection connectionAD = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd endA = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd endD = PLMFactory.eINSTANCE.createConnectionEnd();
    endA.setDestination(clabjectA);
    endA.setConnection(connectionAD);
    endD.setDestination(clabjectD);
    endD.setConnection(connectionAD);

    this.l1.getContent().add(connectionAD);
    /*
     * this makes the test fail: Connection connectionAB = PLMFactory.eINSTANCE.createConnection();
     * ConnectionEnd endAA = PLMFactory.eINSTANCE.createConnectionEnd(); ConnectionEnd endB =
     * PLMFactory.eINSTANCE.createConnectionEnd(); endAA.setDestination(clabjectA);
     * endAA.setConnection(connectionAB); endB.setDestination(clabjectB);
     * endB.setConnection(connectionAB);
     * 
     * this.l1.getContent().add(connectionAB);
     */


    // context is Connection
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getParticipants()# -> forAll(participant|participant.#getLevelIndex()# = self.#getLevelIndex()#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : dm.getContent()) {
      for (Connection connection : level.getConnections()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(connection);
        Object returnValue = visitor.visit(tree);
        assertTrue(Boolean.parseBoolean(returnValue.toString()));
      }
    }
  }

  @Test
  public void strictModelingInheritanceTest() {

    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);

    // context is Inheritance... should it be clabject
    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("self.#getSupertypes()#.#getLevelIndex()# -> asSet() -> size() = 1 \n"
            + "and self.#getSubtypes()#.#getLevelIndex()# -> asSet() -> size() = 1 \n"
            + "and self.#getSupertypes()#.#getLevelIndex()# -> asSet() =\n"
            + "self.#getSubtypes()#.#getLevelIndex()# -> asSet()"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : dm.getContent()) {
      for (Inheritance inheritance : level.getAllInheritances()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(inheritance);
        Object returnValue = visitor.visit(tree);
        assertTrue(Boolean.parseBoolean(returnValue.toString()));
      }
    }
  }

  @Test
  public void strictMonotonicTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);


    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    this.l2.getContent().add(clabjectD);


    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);

    this.l2.getContent().add(classificationAB);
    this.l2.getContent().add(classificationAD);



    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let levelIndex:Integer = self.#getLevelIndex()# in if self.getDirectInstances() -> size() = 0 then true else self.getDirectInstances() -> forAll(c|c.#getLevelIndex()# = levelIndex + 1) endif"));
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
  public void universalOntologicalClassificationStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l2.getContent().add(clabjectD);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);

    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);


    // context is DeepModel
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> reject(c|c.#getLevelIndex()# = 0) -> forAll(c|c.#getDirectType()# -> size() = 1)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void mandatorySelectiveClassificationStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Connection connection1 = PLMFactory.eINSTANCE.createConnection();
    this.l1.getContent().add(connection1);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    this.l2.getContent().add(clabjectD);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    this.l2.getContent().add(clabjectB);

    Connection connection1Instance = PLMFactory.eINSTANCE.createConnection();
    this.l2.getContent().add(connection1Instance);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    Classification classificationConnection = PLMFactory.eINSTANCE.createClassification();
    classificationConnection.setInstance(connection1Instance);
    classificationConnection.setType(connection1);
    this.l2.getContent().add(classificationConnection);

    // context is DeepModel
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Connection -> reject(c|c.#getLevelIndex()# = 0) -> forAll(c|c.#getDirectType()# -> size() = 1)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void universalIsonymicStyleTest1() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(1);
    attributeY.setMutability(1);
    attributeY.setName("a");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l2.getContent().add(clabjectD);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "if self.getDirectInstances() -> size > 0 then let featureSize:Integer = self.#getFeature()# -> select(f|f.#getDurability()# > 0) -> size() in self.getDirectInstances() -> forAll(i|i.#getFeature()# -> size() = featureSize) else true endif"));
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
  public void universalIsonymicStyleTest2() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(1);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(1);
    attributeY.setMutability(1);
    attributeY.setName("a");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l2.getContent().add(clabjectD);

    Classification classificationAD = PLMFactory.eINSTANCE.createClassification();
    classificationAD.setInstance(clabjectD);
    classificationAD.setType(clabjectA);
    this.l2.getContent().add(classificationAD);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "if self.getDirectInstances() -> size > 0 then let featureNames:Set = self.#getFeature()# -> select(f|f.#getDurability()# > 0) -> collect(#name#) in self.getDirectInstances() -> forAll(i|i.#getFeature()# -> collect(#name#) -> includesAll(featureNames)) else true endif"));
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

  /**
   * If a clabject, I, satisfies the structural requirements needed to be an instance of a clabject,
   * T, (it is either a hyponym or isonym of T) I MUST be regarded as an instance of T (or,
   * alternatively, T must be regarded as a type of I).
   */
  @Test
  public void structuralTypingST1Test() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);
    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);
    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> exists(c|(self.doclIsHyponymOf(c) or self.doclIsIsonymOf(c)) implies self.doclIsInstanceOf(c))"));
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

  /**
   * If the set of clabjects that must be regarded as instances of a clabject X (according to
   * criteria ST1) is a proper subset of the set of clabjects that must be regarded as instances of
   * clabject Y, then Y MUST be regarded as a being a supertype of X, possibly with intermediate
   * types. In other words, X must be below Y in a chain of inheritance relationships,
   */
  @Test
  public void structuralTypingST2Test() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);
    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);
    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> reject(self) -> select(c|c.doclGetInstances() -> size() > 0 and c.doclGetInstances() -> includesAll(self.doclGetInstances())) -> forAll(c| if c.#getSubtypes()# -> size() = 0 then true else c.#getSubtypes()# -> includes(self) endif)"));
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

  /**
   * If a clabject I must be an instance of T according to ST1, and there is no subtype of T that I
   * must be regarded as an instance of (the subtype) according to ST1, then T MUST be regarded as
   * the direct type of I. In other words, there must be a classification relationship between T and
   * I.
   */
  @Test
  public void structuralTypingST3Test() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);
    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);
    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> select(c|self.doclIsHyponymOf(c) or self.doclIsIsonymOf(c) and c.#getSubtypes()# -> size() = 0) -> forAll(c|self.#getDirectType()# = c)"));
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

  /**
   * A clabject cannot have more than one direct type.
   */
  @Test
  public void structuralTypingST4Test() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);
    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);
    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);
    // context is Clabject
    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("self.#getDirectType()# -> size() <= 1"));
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
  public void exclusiveAbstractSupertypeStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    inheritanceDA.setComplete(true);// this is important for this test!!!!
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);
    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);
    this.l2.getContent().add(clabjectB);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);
    // context is Inheritance
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getSupertypes()# -> forAll(s|s.#getPotency()# = 0 and s.getDirectInstances() -> size() = 0) and self.#isComplete()# = true"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : dm.getContent()) {
      for (Inheritance inheritance : level.getAllInheritances()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(inheritance);
        Object returnValue = visitor.visit(tree);
        assertTrue(Boolean.parseBoolean(returnValue.toString()));
      }
    }
  }

  @Test
  public void exclusiveConcreteSupertypeStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    inheritanceDA.setComplete(false);// this is important for this test!!!!
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);
    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);
    this.l2.getContent().add(clabjectB);


    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(0);
    this.l2.getContent().add(clabjectF);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Inheritance inheritanceBF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superF = PLMFactory.eINSTANCE.createSupertype();
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    superF.setSupertype(clabjectF);
    subB.setSubtype(clabjectB);
    inheritanceBF.getSubtype().add(subB);
    inheritanceBF.getSupertype().add(superF);
    inheritanceBF.setComplete(true);// this is important for this test!!!!
    this.l2.getContent().add(inheritanceBF);


    // context is Inheritance
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getSupertypes()# -> forAll(super|super.#getPotency()# > 0) and self.#isComplete()# = false"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(inheritanceDA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));

    DeepOclRuleVisitor visitor1 = new DeepOclRuleVisitor(inheritanceBF);
    Object returnValue1 = visitor1.visit(tree);
    assertFalse(Boolean.parseBoolean(returnValue1.toString()));
  }

  @Test
  public void universalRootStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    inheritanceDA.setComplete(false);// this is important for this test!!!!
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);
    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);
    this.l2.getContent().add(clabjectB);


    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(0);
    this.l2.getContent().add(clabjectF);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Inheritance inheritanceBF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superF = PLMFactory.eINSTANCE.createSupertype();
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    superF.setSupertype(clabjectF);
    subB.setSubtype(clabjectB);
    inheritanceBF.getSubtype().add(subB);
    inheritanceBF.getSupertype().add(superF);
    inheritanceBF.setComplete(true);// this is important for this test!!!!
    this.l2.getContent().add(inheritanceBF);

    // context is Level
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("if self.#isLeafLevel()#\n"
        + "    then true\n" + "    else self.#getClabjects()# -> select(c|c.#getSupertypes()# -> \n"
        + "    size() = 0) -> closure(c|c.#getSubtypes()#) \n"
        + "    -> includesAll(self.#getClabjects()#)\n" + "    endif"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    for (Level level : this.dm.getContent()) {
      DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(level);
      Object returnValue = visitor.visit(tree);
      assertTrue(Boolean.parseBoolean(returnValue.toString()));
    }
  }

  @Test
  public void classicPotencyStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    Attribute attributeA = PLMFactory.eINSTANCE.createAttribute();
    attributeA.setDurability(2);
    attributeA.setMutability(2);
    attributeA.setName("a");
    attributeA.setDatatype("String");
    attributeA.setValue("test");
    clabjectA.getFeature().add(attributeA);
    Attribute attributeZ = PLMFactory.eINSTANCE.createAttribute();
    attributeZ.setDurability(0);
    attributeZ.setMutability(0);
    attributeZ.setName("z");
    attributeZ.setDatatype("String");
    attributeZ.setValue("test");
    clabjectA.getFeature().add(attributeZ);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(2);
    Attribute attributeY = PLMFactory.eINSTANCE.createAttribute();
    attributeY.setDurability(2);
    attributeY.setMutability(2);
    attributeY.setName("y");
    attributeY.setDatatype("String");
    attributeY.setValue("test");
    clabjectD.getFeature().add(attributeY);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    inheritanceDA.setComplete(false);// this is important for this test!!!!
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(1);
    Attribute attributeB = PLMFactory.eINSTANCE.createAttribute();
    attributeB.setDurability(1);
    attributeB.setMutability(1);
    attributeB.setName("a");
    attributeB.setDatatype("String");
    attributeB.setValue("test");
    clabjectB.getFeature().add(attributeB);
    Attribute attributeV = PLMFactory.eINSTANCE.createAttribute();
    attributeV.setDurability(1);
    attributeV.setMutability(1);
    attributeV.setName("y");
    attributeV.setDatatype("String");
    attributeV.setValue("test");
    clabjectB.getFeature().add(attributeV);
    this.l2.getContent().add(clabjectB);


    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(0);

    this.l3.getContent().add(clabjectG);

    Classification classificationBG = PLMFactory.eINSTANCE.createClassification();
    classificationBG.setInstance(clabjectG);
    classificationBG.setType(clabjectB);

    this.l3.getContent().add(classificationBG);
    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.getDirectInstances() -> forAll(c|c.#getPotency()# = self.#getPotency()# - 1)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertTrue(aggregatedResult);
  }

  @Test
  public void untypedAbstractTypeStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(2);
    this.l1.getContent().add(clabjectE);


    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l1.getContent().add(clabjectD);

    Inheritance inheritanceDA = PLMFactory.eINSTANCE.createInheritance();
    Supertype superD = PLMFactory.eINSTANCE.createSupertype();
    Subtype subA = PLMFactory.eINSTANCE.createSubtype();
    superD.setSupertype(clabjectD);
    subA.setSubtype(clabjectA);
    inheritanceDA.getSubtype().add(subA);
    inheritanceDA.getSupertype().add(superD);
    inheritanceDA.setComplete(false);// this is important for this test!!!!
    this.l1.getContent().add(inheritanceDA);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l2.getContent().add(clabjectB);


    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);


    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(1);
    this.l2.getContent().add(clabjectG);

    Classification classificationEG = PLMFactory.eINSTANCE.createClassification();
    classificationEG.setInstance(clabjectG);
    classificationEG.setType(clabjectE);
    this.l2.getContent().add(classificationEG);

    Inheritance inheritanceBG = PLMFactory.eINSTANCE.createInheritance();
    Supertype superB = PLMFactory.eINSTANCE.createSupertype();
    Subtype subG = PLMFactory.eINSTANCE.createSubtype();
    superB.setSupertype(clabjectB);
    subG.setSubtype(clabjectG);
    inheritanceBG.getSubtype().add(subG);
    inheritanceBG.getSupertype().add(superB);
    inheritanceBG.setComplete(false);// this is important for this test!!!!
    this.l2.getContent().add(inheritanceBG);

    Clabject clabjectH = PLMFactory.eINSTANCE.createEntity();
    clabjectH.setName("H");
    clabjectH.setPotency(1);
    this.l2.getContent().add(clabjectH);

    Classification classificationEH = PLMFactory.eINSTANCE.createClassification();
    classificationEH.setInstance(clabjectH);
    classificationEH.setType(clabjectE);
    this.l2.getContent().add(classificationEH);

    Inheritance inheritanceBH = PLMFactory.eINSTANCE.createInheritance();
    Supertype superB1 = PLMFactory.eINSTANCE.createSupertype();
    Subtype subH = PLMFactory.eINSTANCE.createSubtype();
    superB1.setSupertype(clabjectB);
    subH.setSubtype(clabjectH);
    inheritanceBH.getSubtype().add(subH);
    inheritanceBH.getSupertype().add(superB1);
    inheritanceBH.setComplete(false);// this is important for this test!!!!
    this.l2.getContent().add(inheritanceBH);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("if self.#getPotency()# = 0 and self.#getSubtypes()# -> size() > 0\n"
            + "        then self.getDirectType() -> isEmpty()\n" + "        else true\n"
            + "        endif"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertTrue(aggregatedResult);
  }

  @Test
  public void harminiousHorizontalSupertypesStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(2);
    this.l1.getContent().add(clabjectE);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l1.getContent().add(clabjectD);


    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(1);
    this.l2.getContent().add(clabjectG);

    Clabject clabjectH = PLMFactory.eINSTANCE.createEntity();
    clabjectH.setName("H");
    clabjectH.setPotency(1);
    this.l2.getContent().add(clabjectH);

    Clabject clabjectI = PLMFactory.eINSTANCE.createEntity();
    clabjectI.setName("I");
    clabjectI.setPotency(1);
    this.l2.getContent().add(clabjectI);

    Classification classificationEH = PLMFactory.eINSTANCE.createClassification();
    classificationEH.setInstance(clabjectH);
    classificationEH.setType(clabjectE);
    this.l2.getContent().add(classificationEH);

    Classification classificationEG = PLMFactory.eINSTANCE.createClassification();
    classificationEG.setInstance(clabjectG);
    classificationEG.setType(clabjectE);
    this.l2.getContent().add(classificationEG);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Classification classificationDI = PLMFactory.eINSTANCE.createClassification();
    classificationDI.setInstance(clabjectI);
    classificationDI.setType(clabjectD);
    this.l2.getContent().add(classificationDI);

    Inheritance inheritanceBG = PLMFactory.eINSTANCE.createInheritance();
    Supertype superB = PLMFactory.eINSTANCE.createSupertype();
    Subtype subG = PLMFactory.eINSTANCE.createSubtype();
    superB.setSupertype(clabjectB);
    subG.setSubtype(clabjectG);
    inheritanceBG.getSubtype().add(subG);
    inheritanceBG.getSupertype().add(superB);
    this.l2.getContent().add(inheritanceBG);

    Inheritance inheritanceBH = PLMFactory.eINSTANCE.createInheritance();
    Supertype superB1 = PLMFactory.eINSTANCE.createSupertype();
    Subtype subH = PLMFactory.eINSTANCE.createSubtype();
    superB1.setSupertype(clabjectB);
    subH.setSubtype(clabjectH);
    inheritanceBH.getSubtype().add(subH);
    inheritanceBH.getSupertype().add(superB1);
    this.l2.getContent().add(inheritanceBH);

    Inheritance inheritanceHF = PLMFactory.eINSTANCE.createInheritance();
    Supertype superH = PLMFactory.eINSTANCE.createSupertype();
    Subtype subF = PLMFactory.eINSTANCE.createSubtype();
    superH.setSupertype(clabjectH);
    subF.setSubtype(clabjectF);
    inheritanceHF.getSubtype().add(subF);
    inheritanceHF.getSupertype().add(superH);
    this.l2.getContent().add(inheritanceHF);

    Inheritance inheritanceHI = PLMFactory.eINSTANCE.createInheritance();
    Supertype superH1 = PLMFactory.eINSTANCE.createSupertype();
    Subtype subI = PLMFactory.eINSTANCE.createSubtype();
    superH1.setSupertype(clabjectH);
    subI.setSubtype(clabjectI);
    inheritanceHI.getSubtype().add(subI);
    inheritanceHI.getSupertype().add(superH1);
    this.l2.getContent().add(inheritanceHI);

    // context is Clabject
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let type:Clabject = self.#getDirectType()# in not (self.#getSupertypes()# -> exists(s|s.doclIsDeepTypeOf(type)))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertTrue(aggregatedResult);
  }

  @Test
  public void absenceOfMetacycleStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(2);
    this.l1.getContent().add(clabjectE);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l1.getContent().add(clabjectD);

    Connection connectionAD = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndA = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndA.setDestination(clabjectA);
    connectEndA.setConnection(connectionAD);
    ConnectionEnd connectEndD = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndD.setDestination(clabjectD);
    connectEndD.setConnection(connectionAD);
    this.l1.getContent().add(connectionAD);

    Connection connectionDE = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndD1 = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndD1.setDestination(clabjectD);
    connectEndD1.setConnection(connectionDE);
    ConnectionEnd connectEndE = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndE.setDestination(clabjectE);
    connectEndE.setConnection(connectionDE);
    this.l1.getContent().add(connectionDE);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(1);
    this.l2.getContent().add(clabjectG);

    Clabject clabjectH = PLMFactory.eINSTANCE.createEntity();
    clabjectH.setName("H");
    clabjectH.setPotency(1);
    this.l2.getContent().add(clabjectH);

    Clabject clabjectI = PLMFactory.eINSTANCE.createEntity();
    clabjectI.setName("I");
    clabjectI.setPotency(1);
    this.l2.getContent().add(clabjectI);

    Connection connectionIH = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndI = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndI.setDestination(clabjectI);
    connectEndI.setConnection(connectionIH);
    ConnectionEnd connectEndH = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndH.setDestination(clabjectH);
    connectEndH.setConnection(connectionIH);
    this.l2.getContent().add(connectionIH);

    Classification classificationEH = PLMFactory.eINSTANCE.createClassification();
    classificationEH.setInstance(clabjectH);
    classificationEH.setType(clabjectE);
    this.l2.getContent().add(classificationEH);

    Classification classificationEG = PLMFactory.eINSTANCE.createClassification();
    classificationEG.setInstance(clabjectG);
    classificationEG.setType(clabjectE);
    this.l2.getContent().add(classificationEG);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Classification classificationDI = PLMFactory.eINSTANCE.createClassification();
    classificationDI.setInstance(clabjectI);
    classificationDI.setType(clabjectD);
    this.l2.getContent().add(classificationDI);


    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getClassificationTreeAsInstance()# -> closure(c|c.#getAllNavigations()#.#destination#) -> excludes(self)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertTrue(aggregatedResult);
  }

  @Test
  public void detectMetacycleStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(2);
    this.l1.getContent().add(clabjectE);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectH = PLMFactory.eINSTANCE.createEntity();
    clabjectH.setName("H");
    clabjectH.setPotency(1);
    this.l2.getContent().add(clabjectH);

    Connection connectionAD = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndA = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndA.setDestination(clabjectA);
    connectEndA.setConnection(connectionAD);
    ConnectionEnd connectEndD = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndD.setDestination(clabjectD);
    connectEndD.setConnection(connectionAD);
    this.l1.getContent().add(connectionAD);

    Connection connectionEH = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndE1 = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndE1.setDestination(clabjectE);
    connectEndE1.setConnection(connectionEH);
    ConnectionEnd connectEndH = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndH.setDestination(clabjectH);
    connectEndH.setConnection(connectionEH);
    this.l1.getContent().add(connectionEH);

    Connection connectionDE = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndD1 = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndD1.setDestination(clabjectD);
    connectEndD1.setConnection(connectionDE);
    ConnectionEnd connectEndE = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndE.setDestination(clabjectE);
    connectEndE.setConnection(connectionDE);
    this.l1.getContent().add(connectionDE);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(1);
    this.l2.getContent().add(clabjectG);

    Clabject clabjectI = PLMFactory.eINSTANCE.createEntity();
    clabjectI.setName("I");
    clabjectI.setPotency(1);
    this.l2.getContent().add(clabjectI);

    Connection connectionIH = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndI = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndI.setDestination(clabjectI);
    connectEndI.setConnection(connectionIH);
    ConnectionEnd connectEndH1 = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndH1.setDestination(clabjectH);
    connectEndH1.setConnection(connectionIH);
    this.l2.getContent().add(connectionIH);

    Classification classificationEH = PLMFactory.eINSTANCE.createClassification();
    classificationEH.setInstance(clabjectH);
    classificationEH.setType(clabjectE);
    this.l2.getContent().add(classificationEH);

    Classification classificationEG = PLMFactory.eINSTANCE.createClassification();
    classificationEG.setInstance(clabjectG);
    classificationEG.setType(clabjectE);
    this.l2.getContent().add(classificationEG);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Classification classificationDI = PLMFactory.eINSTANCE.createClassification();
    classificationDI.setInstance(clabjectI);
    classificationDI.setType(clabjectD);
    this.l2.getContent().add(classificationDI);


    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getClassificationTreeAsInstance()# -> closure(c|c.#getAllNavigations()#.#destination#) -> excludes(self)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertFalse(aggregatedResult);
  }

  @Test
  public void absenceOfMetabombStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(2);
    this.l1.getContent().add(clabjectE);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l1.getContent().add(clabjectD);

    Connection connectionAD = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndA = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndA.setDestination(clabjectA);
    connectEndA.setConnection(connectionAD);
    ConnectionEnd connectEndD = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndD.setDestination(clabjectD);
    connectEndD.setConnection(connectionAD);
    this.l1.getContent().add(connectionAD);

    Connection connectionDE = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndD1 = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndD1.setDestination(clabjectD);
    connectEndD1.setConnection(connectionDE);
    ConnectionEnd connectEndE = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndE.setDestination(clabjectE);
    connectEndE.setConnection(connectionDE);
    this.l1.getContent().add(connectionDE);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(1);
    this.l2.getContent().add(clabjectG);

    Clabject clabjectH = PLMFactory.eINSTANCE.createEntity();
    clabjectH.setName("H");
    clabjectH.setPotency(1);
    this.l2.getContent().add(clabjectH);

    Clabject clabjectI = PLMFactory.eINSTANCE.createEntity();
    clabjectI.setName("I");
    clabjectI.setPotency(1);
    this.l2.getContent().add(clabjectI);

    Connection connectionIH = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndI = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndI.setDestination(clabjectI);
    connectEndI.setConnection(connectionIH);
    ConnectionEnd connectEndH = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndH.setDestination(clabjectH);
    connectEndH.setConnection(connectionIH);
    this.l2.getContent().add(connectionIH);

    Classification classificationEH = PLMFactory.eINSTANCE.createClassification();
    classificationEH.setInstance(clabjectH);
    classificationEH.setType(clabjectE);
    this.l2.getContent().add(classificationEH);

    Classification classificationEG = PLMFactory.eINSTANCE.createClassification();
    classificationEG.setInstance(clabjectG);
    classificationEG.setType(clabjectE);
    this.l2.getContent().add(classificationEG);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Classification classificationDI = PLMFactory.eINSTANCE.createClassification();
    classificationDI.setInstance(clabjectI);
    classificationDI.setType(clabjectD);
    this.l2.getContent().add(classificationDI);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getClassificationTreeAsInstance()# -> closure(c|c.#getSupertypes()#) -> excludesAll(self.#getAllNavigations()#.#destination#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertFalse(aggregatedResult);
  }

  @Test
  public void detectMetabombStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(2);
    this.l1.getContent().add(clabjectE);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectH = PLMFactory.eINSTANCE.createEntity();
    clabjectH.setName("H");
    clabjectH.setPotency(1);
    this.l2.getContent().add(clabjectH);

    Connection connectionAD = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndA = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndA.setDestination(clabjectA);
    connectEndA.setConnection(connectionAD);
    ConnectionEnd connectEndD = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndD.setDestination(clabjectD);
    connectEndD.setConnection(connectionAD);
    this.l1.getContent().add(connectionAD);

    Connection connectionEH = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndE1 = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndE1.setDestination(clabjectE);
    connectEndE1.setConnection(connectionEH);
    ConnectionEnd connectEndH = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndH.setDestination(clabjectH);
    connectEndH.setConnection(connectionEH);
    this.l1.getContent().add(connectionEH);

    Connection connectionDE = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndD1 = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndD1.setDestination(clabjectD);
    connectEndD1.setConnection(connectionDE);
    ConnectionEnd connectEndE = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndE.setDestination(clabjectE);
    connectEndE.setConnection(connectionDE);
    this.l1.getContent().add(connectionDE);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(1);
    this.l2.getContent().add(clabjectG);

    Clabject clabjectI = PLMFactory.eINSTANCE.createEntity();
    clabjectI.setName("I");
    clabjectI.setPotency(1);
    this.l2.getContent().add(clabjectI);

    Connection connectionIH = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd connectEndI = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndI.setDestination(clabjectI);
    connectEndI.setConnection(connectionIH);
    ConnectionEnd connectEndH1 = PLMFactory.eINSTANCE.createConnectionEnd();
    connectEndH1.setDestination(clabjectH);
    connectEndH1.setConnection(connectionIH);
    this.l2.getContent().add(connectionIH);

    Classification classificationEH = PLMFactory.eINSTANCE.createClassification();
    classificationEH.setInstance(clabjectH);
    classificationEH.setType(clabjectE);
    this.l2.getContent().add(classificationEH);

    Classification classificationEG = PLMFactory.eINSTANCE.createClassification();
    classificationEG.setInstance(clabjectG);
    classificationEG.setType(clabjectE);
    this.l2.getContent().add(classificationEG);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Classification classificationDI = PLMFactory.eINSTANCE.createClassification();
    classificationDI.setInstance(clabjectI);
    classificationDI.setType(clabjectD);
    this.l2.getContent().add(classificationDI);


    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getClassificationTreeAsInstance()# -> closure(c|c.#getSupertypes()#) -> excludesAll(self.#getAllNavigations()#.#destination#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertFalse(aggregatedResult);
  }

  @Test
  public void detectTypeSupertypeStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(2);
    this.l1.getContent().add(clabjectE);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectH = PLMFactory.eINSTANCE.createEntity();
    clabjectH.setName("H");
    clabjectH.setPotency(1);
    this.l2.getContent().add(clabjectH);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(1);
    this.l2.getContent().add(clabjectG);

    Clabject clabjectI = PLMFactory.eINSTANCE.createEntity();
    clabjectI.setName("I");
    clabjectI.setPotency(1);
    this.l2.getContent().add(clabjectI);

    Classification classificationEH = PLMFactory.eINSTANCE.createClassification();
    classificationEH.setInstance(clabjectH);
    classificationEH.setType(clabjectE);
    this.l2.getContent().add(classificationEH);

    Classification classificationEG = PLMFactory.eINSTANCE.createClassification();
    classificationEG.setInstance(clabjectG);
    classificationEG.setType(clabjectE);
    this.l2.getContent().add(classificationEG);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Classification classificationDI = PLMFactory.eINSTANCE.createClassification();
    classificationDI.setInstance(clabjectI);
    classificationDI.setType(clabjectD);
    this.l2.getContent().add(classificationDI);

    Inheritance inheritanceAB = PLMFactory.eINSTANCE.createInheritance();
    Supertype superA = PLMFactory.eINSTANCE.createSupertype();
    Subtype subB = PLMFactory.eINSTANCE.createSubtype();
    superA.setSupertype(clabjectA);
    superA.setInheritance(inheritanceAB);
    subB.setSubtype(clabjectB);
    subB.setInheritance(inheritanceAB);
    this.l2.getContent().add(inheritanceAB);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getDirectType()# -> closure(c|c.#getTypes()#) -> excludesAll(self.#getSupertypes()#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertFalse(aggregatedResult);
  }

  @Test
  public void absenceOfTypeSupertypeStyleTest() {
    Clabject clabjectA = PLMFactory.eINSTANCE.createEntity();
    clabjectA.setName("A");
    clabjectA.setPotency(2);
    this.l1.getContent().add(clabjectA);

    Clabject clabjectE = PLMFactory.eINSTANCE.createEntity();
    clabjectE.setName("E");
    clabjectE.setPotency(2);
    this.l1.getContent().add(clabjectE);

    Clabject clabjectD = PLMFactory.eINSTANCE.createEntity();
    clabjectD.setName("D");
    clabjectD.setPotency(0);
    this.l1.getContent().add(clabjectD);

    Clabject clabjectH = PLMFactory.eINSTANCE.createEntity();
    clabjectH.setName("H");
    clabjectH.setPotency(1);
    this.l2.getContent().add(clabjectH);

    Clabject clabjectB = PLMFactory.eINSTANCE.createEntity();
    clabjectB.setName("B");
    clabjectB.setPotency(0);
    this.l2.getContent().add(clabjectB);

    Clabject clabjectF = PLMFactory.eINSTANCE.createEntity();
    clabjectF.setName("F");
    clabjectF.setPotency(1);
    this.l2.getContent().add(clabjectF);

    Clabject clabjectG = PLMFactory.eINSTANCE.createEntity();
    clabjectG.setName("G");
    clabjectG.setPotency(1);
    this.l2.getContent().add(clabjectG);

    Clabject clabjectI = PLMFactory.eINSTANCE.createEntity();
    clabjectI.setName("I");
    clabjectI.setPotency(1);
    this.l2.getContent().add(clabjectI);

    Classification classificationEH = PLMFactory.eINSTANCE.createClassification();
    classificationEH.setInstance(clabjectH);
    classificationEH.setType(clabjectE);
    this.l2.getContent().add(classificationEH);

    Classification classificationEG = PLMFactory.eINSTANCE.createClassification();
    classificationEG.setInstance(clabjectG);
    classificationEG.setType(clabjectE);
    this.l2.getContent().add(classificationEG);

    Classification classificationAB = PLMFactory.eINSTANCE.createClassification();
    classificationAB.setInstance(clabjectB);
    classificationAB.setType(clabjectA);
    this.l2.getContent().add(classificationAB);

    Classification classificationDF = PLMFactory.eINSTANCE.createClassification();
    classificationDF.setInstance(clabjectF);
    classificationDF.setType(clabjectD);
    this.l2.getContent().add(classificationDF);

    Classification classificationDI = PLMFactory.eINSTANCE.createClassification();
    classificationDI.setInstance(clabjectI);
    classificationDI.setType(clabjectD);
    this.l2.getContent().add(classificationDI);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getDirectType()# -> closure(c|c.#getTypes()#) -> excludesAll(self.#getSupertypes()#)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    Boolean aggregatedResult = true;
    for (Level level : this.dm.getContent()) {
      for (Clabject clabject : level.getClabjects()) {
        DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
        Object returnValue = visitor.visit(tree);
        aggregatedResult = aggregatedResult && Boolean.parseBoolean(returnValue.toString());
      }
    }
    assertTrue(aggregatedResult);
  }
}
