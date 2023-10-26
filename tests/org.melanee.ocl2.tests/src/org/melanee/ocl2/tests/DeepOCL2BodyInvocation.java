package org.melanee.ocl2.tests;

import static org.junit.Assert.assertEquals;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.core.models.plm.PLM.Parameter;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.models.definition.constraint.BodyConstraint;
import org.melanee.ocl2.models.definition.constraint.ConstraintFactory;
import org.melanee.ocl2.models.definition.constraint.PreConstraint;
import org.melanee.ocl2.models.definition.constraint.Text;
import org.melanee.ocl2.service.DeepOclRuleVisitor;
import com.sun.crypto.provider.Preconditions;

public class DeepOCL2BodyInvocation {

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

  @After
  public void tearDown() {
    this.l1 = null;
    this.l2 = null;
    this.dm = null;
  }

  @Test
  public void testBodyConstraintInvocation() {
    Clabject membership = PLMFactory.eINSTANCE.createEntity();
    membership.setName("Membership");
    Clabject level = PLMFactory.eINSTANCE.createEntity();
    level.setName("Level");
    Attribute name = PLMFactory.eINSTANCE.createAttribute();
    name.setName("name");
    name.setValue("gold");

    Method getCurrentLevelName = PLMFactory.eINSTANCE.createMethod();
    getCurrentLevelName.setName("getCurrentLevelName");

    Parameter param1 = PLMFactory.eINSTANCE.createPrimitiveParameter();
    param1.setName("test");
    param1.setExpression("String");

    Parameter output = PLMFactory.eINSTANCE.createPrimitiveParameter();
    output.setName("result");
    output.setExpression("String");
    output.setOutput(true);

    getCurrentLevelName.getParameter().add(param1);
    getCurrentLevelName.getParameter().add(output);


    membership.getFeature().add(getCurrentLevelName);

    level.getFeature().add(name);

    Connection memberLevel = PLMFactory.eINSTANCE.createConnection();

    ConnectionEnd memberEnd = PLMFactory.eINSTANCE.createConnectionEnd();
    memberEnd.setConnection(memberLevel);
    memberEnd.setDestination(membership);
    memberEnd.setMoniker("membership");
    ConnectionEnd levelEnd = PLMFactory.eINSTANCE.createConnectionEnd();
    levelEnd.setConnection(memberLevel);
    levelEnd.setDestination(level);
    levelEnd.setMoniker("currentLevel");

    l1.getContent().add(membership);
    l1.getContent().add(level);
    l1.getContent().add(memberLevel);



    BodyConstraint constraint = ConstraintFactory.eINSTANCE.createBodyConstraint();
    constraint.setName("bodyConstraintTest");
    Text constraintText = ConstraintFactory.eINSTANCE.createText();
    constraintText.setText("currentLevel.name");
    constraint.getExpression().add(constraintText);

    getCurrentLevelName.getConstraint().add(constraint);

    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("self.getCurrentLevelName() = \"gold\""));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(getCurrentLevelName);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));

  }

  @Test
  public void testPreBeforeBodyConstraintInvocation() {
    Clabject membership = PLMFactory.eINSTANCE.createEntity();
    membership.setName("Membership");
    Clabject level = PLMFactory.eINSTANCE.createEntity();
    level.setName("Level");
    Attribute name = PLMFactory.eINSTANCE.createAttribute();
    name.setName("name");
    name.setValue("gold");

    Method getCurrentLevelName = PLMFactory.eINSTANCE.createMethod();
    getCurrentLevelName.setName("getCurrentLevelName");

    Parameter param1 = PLMFactory.eINSTANCE.createPrimitiveParameter();
    param1.setName("test");
    param1.setExpression("String");

    Parameter output = PLMFactory.eINSTANCE.createPrimitiveParameter();
    output.setName("result");
    output.setExpression("String");
    output.setOutput(true);

    getCurrentLevelName.getParameter().add(param1);
    getCurrentLevelName.getParameter().add(output);


    membership.getFeature().add(getCurrentLevelName);

    level.getFeature().add(name);

    Connection memberLevel = PLMFactory.eINSTANCE.createConnection();

    ConnectionEnd memberEnd = PLMFactory.eINSTANCE.createConnectionEnd();
    memberEnd.setConnection(memberLevel);
    memberEnd.setDestination(membership);
    memberEnd.setMoniker("membership");
    ConnectionEnd levelEnd = PLMFactory.eINSTANCE.createConnectionEnd();
    levelEnd.setConnection(memberLevel);
    levelEnd.setDestination(level);
    levelEnd.setMoniker("currentLevel");

    l1.getContent().add(membership);
    l1.getContent().add(level);
    l1.getContent().add(memberLevel);



    BodyConstraint constraint = ConstraintFactory.eINSTANCE.createBodyConstraint();
    constraint.setName("bodyConstraintTest");
    Text constraintText = ConstraintFactory.eINSTANCE.createText();
    constraintText.setText("currentLevel.name");
    constraint.getExpression().add(constraintText);


    getCurrentLevelName.getConstraint().add(constraint);

    PreConstraint preConstraint = ConstraintFactory.eINSTANCE.createPreConstraint();
    preConstraint.setName("preConstraint");
    Text preConstraintText = ConstraintFactory.eINSTANCE.createText();
    preConstraintText.setText("self.currentLevel -> size() = 0");
    preConstraint.getExpression().add(preConstraintText);

    getCurrentLevelName.getConstraint().add(preConstraint);

    DeepOclLexer oclLexer =
        new DeepOclLexer(new ANTLRInputStream("self.getCurrentLevelName() = \"gold\""));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(getCurrentLevelName);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));

  }

}
