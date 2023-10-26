package org.melanee.ocl2.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
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
}
