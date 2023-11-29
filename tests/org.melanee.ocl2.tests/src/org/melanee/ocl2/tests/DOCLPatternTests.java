package org.melanee.ocl2.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Calendar;
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
        "self.#getSubtypes()# -> size() > 0 and self.#getPotency()# = 0 and self.getDirectInstances() -> size() = 0"));
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
        "self.#getSupertypes()# -> size() = 0 and self.#getSubtypes()# -> size() = 0 and self.#getPotency()# = 0 and self.getDirectInstances() -> size() = 0"));
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
        new DeepOclLexer(new ANTLRInputStream("self.getDirectInstances() -> size() = 1"));
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
        "self.getDirectOffspring() -> iterate(offspring; currentPotency:Integer = self.#getPotency()#| currentPotency - offspring.#getPotency()#) = 1"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabjectA);
    Object returnValue = visitor.visit(tree);
    assertTrue(Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void basicDiscriminationPatternTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "(self.#getSupertype()# -> size() = 1 and self.#getSupertype()#.#getPotency()# > 0 ) or (self.#getSubtype()# -> size() => 2)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void completeDiscriminationPatternTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#supertype#.#supertype# -> size() = 1 and self.#supertype#.#supertype# -> first().#getPotency()# > 0  and self.#substype#.#substype# -> size() => 2 and self.#substype#.#substype# -> forAll(sub|sub.#getInstances()# -> forAll(i|i.isInstanceOf(self.#supertype#.#supertype# -> first())))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void disjointDiscriminationPatternTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#supertype#.#supertype# -> size() = 1 and self.#supertype#.#supertype# -> first().#getPotency()# = 0 and self.#substype#.#substype# -> size() => 2 and self.#substype#.#substype# -> forAll(sub|sub.#getInstanes()# -> excludesAll(self.#substype#.#substype# -> excluding(sub).#getInstances()#))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void partinionedDiscriminationPatternTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#supertype#.#supertype# -> size() = 1 and self.#supertype#.#supertype# -> first().#getPotency()# = 0 and self.#substype#.#substype# -> size() => 2 and self.#substype#.#substype# -> forAll(sub|sub.#getInstanes()# -> excludesAll(self.#substype#.#substype# -> excluding(sub).#getInstances()#))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void compositePatternTest() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.#getPotency()# = 0 and self.#Subtypes()# -> size() > 1 and self.#getConnections()# -> exists(connection|connection.#getAllConnectionEnd()# -> size() = 2 and connection.#getAllConnectionEnd()# -> one(cE|cE.#destination# = self) and connection.#getAllConnectionEnd()# -> one(cE|self.#Subtypes()# -> includes(cE.#destination#) and cE.#kind# = 'COMPOSITION'))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void basicCharacterizationPattern() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritace = self.#getDirectInstances()# -> first().#getInheritanceAsSubtype()# in\n"
            + "        inheritance.#supertype#.#supertype# -> size() = 1\n"
            + "        let baseType = inheritance.#supertype#.#supertype# -> first() in\n"
            + "        self.#getDirectInstances()# -> forAll(inst|inst.#getInheritanceAsSubtype()# = inheritance)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void completeCharacterizationPattern() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritace = self.#getDirectInstances()# -> first().#getInheritanceAsSubtype()# in\n"
            + "        inheritance.#supertype#.#supertype# -> size() = 1\n"
            + "        let baseType = inheritance.#supertype#.#supertype# -> first() in\n"
            + "        self.#getDirectInstances()# -> forAll(inst|inst.#getInheritanceAsSubtype()# = inheritance)\n"
            + "        baseType.#getPotency()# = 0 and\n"
            + "        baseType.#getSubtypes()#.#getDirectInstances()# -> forAll(inst|inst.isIndirectInstanceOf(baseType))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void disjointCharacterizationPattern() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritace = self.#getDirectInstances()# -> first().#getInheritanceAsSubtype()# in\n"
            + "        inheritance.#supertype#.#supertype# -> size() = 1\n"
            + "        let baseType = inheritance.#supertype#.#supertype# -> first() in\n"
            + "        self.#getDirectInstances()# -> forAll(inst|inst.#getInheritanceAsSubtype()# = inheritance) and\n"
            + "        baseType.#getInstances()# -> forAll(baseInst|baseType.#getSubtypes()# -> excluding(baseInst.#getDirectType()#) -> not(exists(sub|baseInst.isInstanceOf(sub)))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void partitionedCharacterizationPattern() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritace = self.#getDirectInstances()# -> first().#getInheritanceAsSubtype()# in\n"
            + "        inheritance.#supertype#.#supertype# -> size() = 1\n"
            + "        let baseType = inheritance.#supertype#.#supertype# -> first() in\n"
            + "        self.#getDirectInstances()# -> forAll(inst|inst.#getInheritanceAsSubtype()# = inheritance) and\n"
            + "        baseType.#getInstances()# -> forAll(baseInst|baseType.#getSubtypes()# -> excluding(baseInst.#getDirectType()#) -> not(exists(sub|baseInst.isInstanceOf(sub)))\n"
            + "        and baseType.#getPotency()# = 0"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void strongOdellCharacterizationPattern() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritace = self.#getDirectInstances()# -> first().#getInheritanceAsSubtype()# in\n"
            + "        inheritance.#supertype#.#supertype# -> size() = 1\n"
            + "        let baseType = inheritance.#supertype#.#supertype# -> first() in\n"
            + "        self.#getDirectInstances()# -> forAll(inst|inst.#getInheritanceAsSubtype()# = inheritance) and\n"
            + "        baseType.#getSubtypes()# -> forAll(sub|sub.isInstanceOf(self))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }

  @Test
  public void cardelliCharacterizationPattern() {
    Clabject clabject = PLMFactory.eINSTANCE.createEntity();

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "let inheritace = self.#getDirectInstances()# -> reject(inst|inst.#getSupertypes()# -> size() = 0) first().#getInheritanceAsSubtype()# in\n"
            + "        inheritance.#supertype#.#supertype# -> size() = 1\n"
            + "        let baseType = inheritance.#supertype#.#supertype# -> first() in\n"
            + "        self.#getDirectInstances()# -> forAll(inst|inst.#getInheritanceAsSubtype()# = inheritance) and\n"
            + "        basType.isInstanceOf(self) and \n"
            + "        baseType.#getSubtypes()# -> forAll(sub|sub.isInstanceOf(self))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(clabject);
    Object returnValue = visitor.visit(tree);

    fail();
  }


}
