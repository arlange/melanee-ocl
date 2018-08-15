package org.melanee.ocl2.tests;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.emf.common.util.EList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.AbstractConstraint;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.core.models.plm.PLM.PLMPackage;
import org.melanee.core.models.plm.PLM.Subtype;
import org.melanee.core.models.plm.PLM.Supertype;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.models.definition.constraint.Constraint;
import org.melanee.ocl2.models.definition.constraint.Expression;
import org.melanee.ocl2.models.definition.constraint.Pointer;
import org.melanee.ocl2.models.definition.constraint.Text;
import org.melanee.ocl2.service.DeepOclPersistenceService;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class DeepReflectiveConstraints {
  DeepModel dm;
  Level level0;
  Level level1;
  Level level2;
  Clabject Abstract;
  Clabject NonAbstract;

  @Before
  public void setUp() {
    this.Abstract = PLMFactory.eINSTANCE.createEntity();
    this.Abstract.setName("Abstract");
    this.NonAbstract = PLMFactory.eINSTANCE.createEntity();
    this.NonAbstract.setName("NonAbstract");
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.level0 = PLMFactory.eINSTANCE.createLevel();
    this.level1 = PLMFactory.eINSTANCE.createLevel();
    this.level2 = PLMFactory.eINSTANCE.createLevel();
    this.dm.setName("dm");

  }

  @After
  public void destroy() {
    this.Abstract = null;
    this.NonAbstract = null;
    this.dm = null;
    this.level0 = null;
    this.level2 = null;
    this.level1 = null;
  }

  @Test
  public void testSaveConstraintOnDeepModel() {
    DeepOclPersistenceService persistanceService = new DeepOclPersistenceService(dm);
    String deepModelConstraint = "context dm \nClabject -> select(c|c.#getPotency()# = 0)";
    persistanceService.save(deepModelConstraint);
    assertTrue(dm.getConstraint().size() == 1);
  }

  @Test
  public void testSaveConstraintOnDeepModel1() {
    DeepOclPersistenceService saveConstraints = new DeepOclPersistenceService(dm);
    saveConstraints.save("context dm inv Clabject -> select(c|c.#getPotency()# = 0) -> size()=0");
    StringBuilder oclExpression = new StringBuilder();
    EList<AbstractConstraint> constraints = dm.getConstraint();
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
    assertEquals("context dm inv Clabject -> select(c|c.#getPotency()# = 0) -> size()=0\n",
        oclExpression.toString());
  }

  @Test
  public void testReflectiveDeepModelConstraint() {
    this.dm.getContent().add(level0);
    this.level0.getContent().add(Abstract);
    this.Abstract.setPotency(0);
    this.level0.getContent().add(NonAbstract);
    this.NonAbstract.setPotency(1);

    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("Clabject -> select(c|c.#getPotency()# = 0)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(this.Abstract), returnValue);
  }

  @Test
  public void testReflectiveDeepModelConstraintConnections() {
    this.dm.getContent().add(level0);
    this.level0.getContent().add(Abstract);
    this.Abstract.setPotency(0);
    this.level0.getContent().add(NonAbstract);
    this.NonAbstract.setPotency(1);
    Connection connection = PLMFactory.eINSTANCE.createConnection();
    this.level0.getContent().add(connection);
    connection.setName("connection");
    connection.setPotency(0);

    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("Clabject -> select(c|c.#getPotency()# = 0)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(this.Abstract), returnValue);
  }

  @Test
  public void testReflectiveDeepModelConnection() {
    this.dm.getContent().add(level0);
    this.level0.getContent().add(Abstract);
    this.Abstract.setPotency(0);
    this.level0.getContent().add(NonAbstract);
    this.NonAbstract.setPotency(1);
    Connection connection = PLMFactory.eINSTANCE.createConnection();
    this.level0.getContent().add(connection);
    connection.setName("connection");
    connection.setPotency(0);

    Connection connection1 = PLMFactory.eINSTANCE.createConnection();
    this.level0.getContent().add(connection1);
    connection1.setName("connection");
    connection1.setPotency(1);

    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("Connection -> select(c|c.#getPotency()# = 0)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(connection), returnValue);
  }

  @Test
  public void testReflectiveDeepModelLevelEntities() {
    this.dm.getContent().add(level0);
    this.level0.getContent().add(Abstract);
    this.Abstract.setPotency(0);
    this.level0.getContent().add(NonAbstract);
    this.NonAbstract.setPotency(1);
    Connection connection = PLMFactory.eINSTANCE.createConnection();
    this.level0.getContent().add(connection);
    connection.setName("connection");
    connection.setPotency(0);
    level0.setName("level0");

    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("Level -> first().#getEntities()#"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(this.Abstract, this.NonAbstract), returnValue);
  }

  @Test
  public void testReflectiveDeepModelLevelConnections() {
    this.dm.getContent().add(level0);
    this.level0.getContent().add(Abstract);
    this.Abstract.setPotency(0);
    this.level0.getContent().add(NonAbstract);
    this.NonAbstract.setPotency(1);
    Connection connection = PLMFactory.eINSTANCE.createConnection();
    this.level0.getContent().add(connection);
    connection.setName("connection");
    connection.setPotency(0);
    level0.setName("level0");

    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("Level -> first().#getConnections()#"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(connection), returnValue);
  }

  @Test
  public void testReflectiveDeepModelConstraintClabjectByName() {
    this.dm.getContent().add(level0);
    this.level0.getContent().add(Abstract);
    this.Abstract.setPotency(0);
    this.Abstract.setName("abstract");
    this.level0.getContent().add(NonAbstract);
    this.NonAbstract.setPotency(1);
    Connection connection = PLMFactory.eINSTANCE.createConnection();
    this.level0.getContent().add(connection);
    connection.setName("connection");
    connection.setPotency(0);

    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("Clabject -> select(c|c.#getName()# = \"abstract\")"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(Arrays.asList(this.Abstract), returnValue);
  }

  @Test
  public void testLevelConstraint() {
    this.dm.getContent().add(level0);
    this.dm.getContent().add(level1);
    this.level0.getContent().add(Abstract);
    this.Abstract.setPotency(1);
    this.Abstract.setName("Start");
    this.level1.getContent().add(NonAbstract);
    this.NonAbstract.setPotency(1);
    this.NonAbstract.setName("NonAbstract");

    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setDatatype("Boolean");
    attribute.setName("isStart");
    attribute.setValue("true");

    this.NonAbstract.getFeature().add(attribute);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Level -> at(1).#getEntities()# -> select(c|c.isStart = true) -> size() > 0"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, returnValue);
  }

  @Test
  public void testIsonymicInstantiation() {
    this.dm.getContent().add(level0);
    this.dm.getContent().add(level1);
    this.level0.getContent().add(Abstract);
    this.Abstract.setPotency(1);
    this.Abstract.setName("Start");
    this.level1.getContent().add(NonAbstract);
    this.NonAbstract.setPotency(1);
    this.NonAbstract.setName("NonAbstract");

    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setDatatype("Boolean");
    attribute.setName("isStart");
    attribute.setValue("true");

    this.NonAbstract.getFeature().add(attribute);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Clabject -> forAll(select(c|c.#getFeature()# -> select(f|f.#getDurability()# >= 1)) -> size() = self.getDirectInstances() -> select(c|c.#getFeature()#) -> size())"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, returnValue);
  }
}
