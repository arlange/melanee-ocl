package org.melanee.ocl2.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javax.swing.text.PlainDocument;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class DeepOclSortedByTests {

  DeepModel dm;
  Level level0;
  Level level1;

  @Before
  public void setUp() {
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.level0 = PLMFactory.eINSTANCE.createLevel();
    this.level1 = PLMFactory.eINSTANCE.createLevel();
    this.dm.getContent().add(level0);
    this.dm.getContent().add(level1);

  }

  @After
  public void destroy() {
    this.dm = null;
    this.level0 = null;
    this.level1 = null;
  }

  @Test
  public void sorteByIntegerTest() {
    Clabject c0 = PLMFactory.eINSTANCE.createEntity();
    c0.setName("C0");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("C1");
    Attribute attr0 = PLMFactory.eINSTANCE.createAttribute();
    Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
    attr0.setDatatype("Integer");
    attr1.setDatatype("Integer");
    attr0.setName("price");
    attr1.setName("price");
    attr0.setValue("32");
    attr1.setValue("30");
    c0.getFeature().add(attr0);
    c1.getFeature().add(attr1);
    level0.getContent().add(c0);
    level0.getContent().add(c1);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("Clabject -> sortedBy(price)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(c1, c0), returnValue);
  }

  @Test
  public void sorteByIntegerTest1() {
    Clabject c0 = PLMFactory.eINSTANCE.createEntity();
    c0.setName("C0");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("C1");
    Attribute attr0 = PLMFactory.eINSTANCE.createAttribute();
    Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
    attr0.setDatatype("Integer");
    attr1.setDatatype("Integer");
    attr0.setName("price");
    attr1.setName("price");
    attr0.setValue("32");
    attr1.setValue("35");
    c0.getFeature().add(attr0);
    c1.getFeature().add(attr1);
    level0.getContent().add(c0);
    level0.getContent().add(c1);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("Clabject -> sortedBy(price)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(c0, c1), returnValue);
  }

  @Test
  public void sorteByStringTest() {
    Clabject c0 = PLMFactory.eINSTANCE.createEntity();
    c0.setName("C0");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("C1");
    Attribute attr0 = PLMFactory.eINSTANCE.createAttribute();
    Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
    attr0.setDatatype("String");
    attr1.setDatatype("String");
    attr0.setName("test");
    attr1.setName("test");
    attr0.setValue("Ab");
    attr1.setValue("Bc");
    c0.getFeature().add(attr0);
    c1.getFeature().add(attr1);
    level0.getContent().add(c0);
    level0.getContent().add(c1);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("Clabject -> sortedBy(test)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(c0, c1), returnValue);
  }

  @Test
  public void sorteByStringTest1() {
    Clabject c0 = PLMFactory.eINSTANCE.createEntity();
    c0.setName("C0");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("C1");
    Attribute attr0 = PLMFactory.eINSTANCE.createAttribute();
    Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
    attr0.setDatatype("String");
    attr1.setDatatype("String");
    attr0.setName("test");
    attr1.setName("test");
    attr0.setValue("Bb");
    attr1.setValue("Bc");
    c0.getFeature().add(attr0);
    c1.getFeature().add(attr1);
    level0.getContent().add(c0);
    level0.getContent().add(c1);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("Clabject -> sortedBy(test)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(c0, c1), returnValue);
  }
  
  @Test
  public void sorteByStringTest2() {
    Clabject c0 = PLMFactory.eINSTANCE.createEntity();
    c0.setName("C0");
    Clabject c1 = PLMFactory.eINSTANCE.createEntity();
    c1.setName("C1");
    Attribute attr0 = PLMFactory.eINSTANCE.createAttribute();
    Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
    attr0.setDatatype("String");
    attr1.setDatatype("String");
    attr0.setName("test");
    attr1.setName("test");
    attr0.setValue("Cb");
    attr1.setValue("Bc");
    c0.getFeature().add(attr0);
    c1.getFeature().add(attr1);
    level0.getContent().add(c0);
    level0.getContent().add(c1);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("Clabject -> sortedBy(test)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(c1, c0), returnValue);
  }
}
