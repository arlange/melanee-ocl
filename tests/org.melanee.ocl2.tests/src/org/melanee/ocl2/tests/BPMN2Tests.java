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

public class BPMN2Tests {
  DeepModel dm;
  Level level0;
  Level level1;
  Level level2;
  Clabject interactionNode;
  Clabject throwEvent;
  Clabject catchEvent;
  Clabject startEvent;
  Clabject endEvent;
  Clabject hungryForPizza;
  Clabject hungerSatisfied;
  Clabject messageStartEvent;
  Clabject activity;
  Clabject task;
  Clabject orderPizza;
  Connection connection1;

  /**
   * set up tests.
   */
  @Before
  public void setUp() {
    this.interactionNode = PLMFactory.eINSTANCE.createEntity();
    this.interactionNode.setName("InteractionNode");
    this.throwEvent = PLMFactory.eINSTANCE.createEntity();
    this.throwEvent.setName("ThrowEvent");
    this.messageStartEvent = PLMFactory.eINSTANCE.createEntity();
    this.messageStartEvent.setName("MessageStartEvent");
    this.catchEvent = PLMFactory.eINSTANCE.createEntity();
    this.catchEvent.setName("CatchEvent");
    this.startEvent = PLMFactory.eINSTANCE.createEntity();
    this.startEvent.setName("StartEvent");
    this.endEvent = PLMFactory.eINSTANCE.createEntity();
    this.endEvent.setName("EndEvent");
    this.task = PLMFactory.eINSTANCE.createEntity();
    this.task.setName("Task");
    this.activity = PLMFactory.eINSTANCE.createEntity();
    this.activity.setName("Activity");
    this.orderPizza = PLMFactory.eINSTANCE.createEntity();
    this.orderPizza.setName("OrderPizza");
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.level0 = PLMFactory.eINSTANCE.createLevel();
    this.level1 = PLMFactory.eINSTANCE.createLevel();
    this.level2 = PLMFactory.eINSTANCE.createLevel();
    this.dm.setName("dm");
    this.dm.getContent().add(this.level0);
    this.dm.getContent().add(this.level1);

    this.level0.getContent().add(this.catchEvent);
    this.level0.getContent().add(this.throwEvent);
    this.level0.getContent().add(this.interactionNode);
    this.level0.getContent().add(this.startEvent);
    this.level0.getContent().add(this.endEvent);
    this.level0.getContent().add(this.messageStartEvent);
    this.level0.getContent().add(this.activity);
    this.level0.getContent().add(this.task);

    // Connection Interaction Node
    this.connection1 = PLMFactory.eINSTANCE.createConnection();
    this.connection1.setName("InteractionNodeConnection");
    this.connection1.setPotency(2);
    ConnectionEnd receive = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd send = PLMFactory.eINSTANCE.createConnectionEnd();
    receive.setMoniker("receive");
    send.setMoniker("send");
    receive.setDestination(this.interactionNode);
    send.setDestination(this.interactionNode);
    receive.setNavigable(true);
    send.setNavigable(false);
    send.setConnection(this.connection1);
    receive.setConnection(this.connection1);
    this.level0.getContent().add(this.connection1);

    // SuperSubTypes InteractionNode -> ThrowEvent/CatchEvent/Activity
    Subtype subThrow = PLMFactory.eINSTANCE.createSubtype();
    Subtype subActivity = PLMFactory.eINSTANCE.createSubtype();
    Subtype subCatch = PLMFactory.eINSTANCE.createSubtype();
    Supertype sup = PLMFactory.eINSTANCE.createSupertype();
    Inheritance interactionThrowCatch = PLMFactory.eINSTANCE.createInheritance();
    interactionThrowCatch.getSubtype().add(subThrow);
    interactionThrowCatch.getSubtype().add(subActivity);
    interactionThrowCatch.getSubtype().add(subCatch);
    interactionThrowCatch.getSupertype().add(sup);
    subThrow.setSubtype(this.throwEvent);
    subActivity.setSubtype(this.activity);
    subCatch.setSubtype(this.catchEvent);
    sup.setSupertype(this.interactionNode);
    this.level0.getContent().add(interactionThrowCatch);

    // SuperSubTypes ThrowEvent -> EndEvent
    Subtype subEnd = PLMFactory.eINSTANCE.createSubtype();
    Supertype superThrow = PLMFactory.eINSTANCE.createSupertype();
    Inheritance throwEnd = PLMFactory.eINSTANCE.createInheritance();
    throwEnd.getSubtype().add(subEnd);
    throwEnd.getSupertype().add(superThrow);
    subEnd.setSubtype(this.endEvent);
    superThrow.setSupertype(this.throwEvent);
    this.level0.getContent().add(throwEnd);

    // SuperSubTypes CatchEvent -> StartEvent
    Subtype subStart = PLMFactory.eINSTANCE.createSubtype();
    Supertype superCatch = PLMFactory.eINSTANCE.createSupertype();
    Inheritance catchStart = PLMFactory.eINSTANCE.createInheritance();
    catchStart.getSubtype().add(subStart);
    catchStart.getSupertype().add(superCatch);
    subStart.setSubtype(this.startEvent);
    superCatch.setSupertype(this.catchEvent);
    this.level0.getContent().add(catchStart);

    // SuperSubTypes StartEvent -> MessageStartEvent
    Subtype messageStart = PLMFactory.eINSTANCE.createSubtype();
    Supertype startEventSup = PLMFactory.eINSTANCE.createSupertype();
    Inheritance startMessage = PLMFactory.eINSTANCE.createInheritance();
    startMessage.getSubtype().add(messageStart);
    startMessage.getSupertype().add(startEventSup);
    messageStart.setSubtype(this.messageStartEvent);
    startEventSup.setSupertype(this.startEvent);
    this.level0.getContent().add(startMessage);

    // SuperSubTypes Activity -> Task
    Subtype subTask = PLMFactory.eINSTANCE.createSubtype();
    Supertype supActivity = PLMFactory.eINSTANCE.createSupertype();
    Inheritance activityTask = PLMFactory.eINSTANCE.createInheritance();
    activityTask.getSubtype().add(subTask);
    activityTask.getSupertype().add(supActivity);
    subTask.setSubtype(this.task);
    supActivity.setSupertype(this.activity);
    this.level0.getContent().add(activityTask);

    // level 1
    this.hungryForPizza = PLMFactory.eINSTANCE.createEntity();
    this.hungerSatisfied = PLMFactory.eINSTANCE.createEntity();

    this.hungerSatisfied.setName("hungerSatisfied");
    this.hungryForPizza.setName("hungryForPizza");

    this.level1.getContent().add(this.hungerSatisfied);
    this.level1.getContent().add(this.hungryForPizza);
    this.level1.getContent().add(this.orderPizza);

    Classification startEvent = PLMFactory.eINSTANCE.createClassification();
    startEvent.setInstance(this.hungryForPizza);
    startEvent.setType(this.messageStartEvent);
    this.level1.getContent().add(startEvent);

    Classification endEvent = PLMFactory.eINSTANCE.createClassification();
    endEvent.setInstance(this.hungerSatisfied);
    endEvent.setType(this.endEvent);
    this.level1.getContent().add(endEvent);

    Classification taskOrder = PLMFactory.eINSTANCE.createClassification();
    taskOrder.setInstance(this.orderPizza);
    taskOrder.setType(this.task);
    this.level1.getContent().add(taskOrder);

  }

  /**
   * destroy all elements after tests.
   */
  @After
  public void destroy() {
    this.interactionNode = null;
    this.throwEvent = null;
    this.catchEvent = null;
    this.startEvent = null;
    this.endEvent = null;
    this.dm = null;
    this.level0 = null;
    this.level2 = null;
    this.level1 = null;
    this.hungerSatisfied = null;
    this.hungryForPizza = null;
    this.activity = null;
    this.connection1 = null;
    this.messageStartEvent = null;
  }

  @Test
  public void messageFlowConstraintTest() {
    Connection startToEnd = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd start = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd end = PLMFactory.eINSTANCE.createConnectionEnd();
    start.setMoniker("send");
    end.setMoniker("receive");
    startToEnd.setName("startToEnd");

    start.setConnection(startToEnd);
    end.setConnection(startToEnd);
    start.setDestination(this.hungryForPizza);
    end.setDestination(this.hungerSatisfied);
    end.setNavigable(true);
    start.setNavigable(false);

    Classification interactionConnection = PLMFactory.eINSTANCE.createClassification();
    interactionConnection.setInstance(startToEnd);
    interactionConnection.setType(this.connection1);
    this.level1.getContent().add(interactionConnection);

    this.level1.getContent().add(startToEnd);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.allInstances() -> forAll(p|p.receive->forAll(r|r.isDeepKindOf(CatchEvent)) and p.send->forAll(s|s.isDeepKindOf(ThrowEvent)))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(connection1);
    Object returnValue = visitor.visit(tree);
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void messageFlowConstraintTest1() {
    Connection startToEnd = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd start = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd end = PLMFactory.eINSTANCE.createConnectionEnd();
    start.setMoniker("receive");
    end.setMoniker("send");
    startToEnd.setName("startToEnd");

    start.setConnection(startToEnd);
    end.setConnection(startToEnd);
    start.setDestination(this.hungryForPizza);
    end.setDestination(this.hungerSatisfied);
    end.setNavigable(false);
    start.setNavigable(true);

    Classification interactionConnection = PLMFactory.eINSTANCE.createClassification();
    interactionConnection.setInstance(startToEnd);
    interactionConnection.setType(connection1);
    this.level1.getContent().add(interactionConnection);

    this.level1.getContent().add(startToEnd);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.allInstances() -> forAll(p|p.receive->forAll(r|r.isDeepKindOf(CatchEvent)) and p.send->forAll(s|s.isDeepKindOf(ThrowEvent)))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(connection1);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));

  }

  @Test
  public void isDeepKindOfTest1() {
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepKindOf(CatchEvent)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.hungryForPizza);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void isDeepKindOfTest2() {
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepKindOf(ThrowEvent)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.hungryForPizza);
    Object returnValue = visitor.visit(tree);
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void isDeepKindOfTest3() {
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepKindOf(ThrowEvent)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.hungerSatisfied);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void isDeepKindOfTest4() {
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepKindOf(CatchEvent)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.hungerSatisfied);
    Object returnValue = visitor.visit(tree);
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void isDeepKindOfTest5() {
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepKindOf(Activity)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.orderPizza);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void isDeepKindOfTest6() {
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepKindOf(Task)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.orderPizza);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void isDeepKindOfTest7() {
    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("self.isDeepKindOf(ThrowWEvent)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.orderPizza);
    Object returnValue = visitor.visit(tree);
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void messageFlowConstraintTaskToStartTrue() {
    Connection startToEnd = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd start = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd end = PLMFactory.eINSTANCE.createConnectionEnd();
    start.setMoniker("send");
    end.setMoniker("receive");
    startToEnd.setName("startToEnd");

    start.setConnection(startToEnd);
    end.setConnection(startToEnd);
    end.setDestination(this.orderPizza);
    start.setDestination(this.hungryForPizza);
    end.setNavigable(true);
    start.setNavigable(false);

    Classification interactionConnection = PLMFactory.eINSTANCE.createClassification();
    interactionConnection.setInstance(startToEnd);
    interactionConnection.setType(connection1);
    this.level1.getContent().add(interactionConnection);

    this.level1.getContent().add(startToEnd);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "self.receive -> forAll(r|r.isDeepKindOf(CatchEvent)) or self.receive -> forAll(s|s.isDeepKindOf(Activity))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(startToEnd);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void messageFlowConstraintSendFromStartFalse() {
    Connection startToEnd = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd start = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd end = PLMFactory.eINSTANCE.createConnectionEnd();
    start.setMoniker("send");
    end.setMoniker("receive");
    startToEnd.setName("startToEnd");

    start.setConnection(startToEnd);
    end.setConnection(startToEnd);
    end.setDestination(this.orderPizza);
    start.setDestination(this.hungryForPizza);
    end.setNavigable(true);
    start.setNavigable(false);

    Classification interactionConnection = PLMFactory.eINSTANCE.createClassification();
    interactionConnection.setInstance(startToEnd);
    interactionConnection.setType(connection1);
    this.level1.getContent().add(interactionConnection);

    this.level1.getContent().add(startToEnd);

    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("not(self.send -> forAll(r|r.isDeepKindOf(CatchEvent)))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(startToEnd);
    Object returnValue = visitor.visit(tree);
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void checkForEmptyString() {
    Attribute attribute = PLMFactory.eINSTANCE.createAttribute();
    attribute.setDatatype("String");
    attribute.setName("leer");

    this.hungerSatisfied.getFeature().add(attribute);

    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("leer <> \"\""));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.hungerSatisfied);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }
}
