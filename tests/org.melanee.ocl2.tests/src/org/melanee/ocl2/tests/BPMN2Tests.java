package org.melanee.ocl2.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

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
  Clabject InteractionNode;
  Clabject ThrowEvent;
  Clabject CatchEvent;
  Clabject StartEvent;
  Clabject EndEvent;
  Clabject hungryForPizza;
  Clabject hungerSatisfied;
  Clabject MessageStartEvent;
  Clabject Activity;
  Clabject Task;
  Clabject OrderPizza;
  Connection connection1;

  @Before
  public void setUp() {
    this.InteractionNode = PLMFactory.eINSTANCE.createEntity();
    this.InteractionNode.setName("InteractionNode");
    this.ThrowEvent = PLMFactory.eINSTANCE.createEntity();
    this.ThrowEvent.setName("ThrowEvent");
    this.MessageStartEvent = PLMFactory.eINSTANCE.createEntity();
    this.MessageStartEvent.setName("MessageStartEvent");
    this.CatchEvent = PLMFactory.eINSTANCE.createEntity();
    this.CatchEvent.setName("CatchEvent");
    this.StartEvent = PLMFactory.eINSTANCE.createEntity();
    this.StartEvent.setName("StartEvent");
    this.EndEvent = PLMFactory.eINSTANCE.createEntity();
    this.EndEvent.setName("EndEvent");
    this.Task = PLMFactory.eINSTANCE.createEntity();
    this.Task.setName("Task");
    this.Activity = PLMFactory.eINSTANCE.createEntity();
    this.Activity.setName("Activity");
    this.OrderPizza = PLMFactory.eINSTANCE.createEntity();
    this.OrderPizza.setName("OrderPizza");
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.level0 = PLMFactory.eINSTANCE.createLevel();
    this.level1 = PLMFactory.eINSTANCE.createLevel();
    this.level2 = PLMFactory.eINSTANCE.createLevel();
    this.dm.setName("dm");
    this.dm.getContent().add(this.level0);
    this.dm.getContent().add(this.level1);

    this.level0.getContent().add(this.CatchEvent);
    this.level0.getContent().add(this.ThrowEvent);
    this.level0.getContent().add(this.InteractionNode);
    this.level0.getContent().add(this.StartEvent);
    this.level0.getContent().add(this.EndEvent);
    this.level0.getContent().add(this.MessageStartEvent);
    this.level0.getContent().add(this.Activity);
    this.level0.getContent().add(this.Task);

    // Connection Interaction Node
    this.connection1 = PLMFactory.eINSTANCE.createConnection();
    this.connection1.setName("InteractionNodeConnection");
    this.connection1.setPotency(2);
    ConnectionEnd receive = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd send = PLMFactory.eINSTANCE.createConnectionEnd();
    receive.setMoniker("receive");
    send.setMoniker("send");
    receive.setDestination(this.InteractionNode);
    send.setDestination(this.InteractionNode);
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
    Inheritance InteractionThrowCatch = PLMFactory.eINSTANCE.createInheritance();
    InteractionThrowCatch.getSubtype().add(subThrow);
    InteractionThrowCatch.getSubtype().add(subActivity);
    InteractionThrowCatch.getSubtype().add(subCatch);
    InteractionThrowCatch.getSupertype().add(sup);
    subThrow.setSubtype(this.ThrowEvent);
    subActivity.setSubtype(this.Activity);
    subCatch.setSubtype(this.CatchEvent);
    sup.setSupertype(this.InteractionNode);
    this.level0.getContent().add(InteractionThrowCatch);

    // SuperSubTypes ThrowEvent -> EndEvent
    Subtype subEnd = PLMFactory.eINSTANCE.createSubtype();
    Supertype superThrow = PLMFactory.eINSTANCE.createSupertype();
    Inheritance throwEnd = PLMFactory.eINSTANCE.createInheritance();
    throwEnd.getSubtype().add(subEnd);
    throwEnd.getSupertype().add(superThrow);
    subEnd.setSubtype(this.EndEvent);
    superThrow.setSupertype(this.ThrowEvent);
    this.level0.getContent().add(throwEnd);

    // SuperSubTypes CatchEvent -> StartEvent
    Subtype subStart = PLMFactory.eINSTANCE.createSubtype();
    Supertype superCatch = PLMFactory.eINSTANCE.createSupertype();
    Inheritance catchStart = PLMFactory.eINSTANCE.createInheritance();
    catchStart.getSubtype().add(subStart);
    catchStart.getSupertype().add(superCatch);
    subStart.setSubtype(this.StartEvent);
    superCatch.setSupertype(this.CatchEvent);
    this.level0.getContent().add(catchStart);

    // SuperSubTypes StartEvent -> MessageStartEvent
    Subtype messageStart = PLMFactory.eINSTANCE.createSubtype();
    Supertype startEventSup = PLMFactory.eINSTANCE.createSupertype();
    Inheritance startMessage = PLMFactory.eINSTANCE.createInheritance();
    startMessage.getSubtype().add(messageStart);
    startMessage.getSupertype().add(startEventSup);
    messageStart.setSubtype(this.MessageStartEvent);
    startEventSup.setSupertype(this.StartEvent);
    this.level0.getContent().add(startMessage);

    // SuperSubTypes Activity -> Task
    Subtype subTask = PLMFactory.eINSTANCE.createSubtype();
    Supertype supActivity = PLMFactory.eINSTANCE.createSupertype();
    Inheritance activityTask = PLMFactory.eINSTANCE.createInheritance();
    activityTask.getSubtype().add(subTask);
    activityTask.getSupertype().add(supActivity);
    subTask.setSubtype(this.Task);
    supActivity.setSupertype(this.Activity);
    this.level0.getContent().add(activityTask);

    // level 1
    this.hungryForPizza = PLMFactory.eINSTANCE.createEntity();
    this.hungerSatisfied = PLMFactory.eINSTANCE.createEntity();

    this.hungerSatisfied.setName("hungerSatisfied");
    this.hungryForPizza.setName("hungryForPizza");

    this.level1.getContent().add(this.hungerSatisfied);
    this.level1.getContent().add(this.hungryForPizza);
    this.level1.getContent().add(this.OrderPizza);

    Classification startEvent = PLMFactory.eINSTANCE.createClassification();
    startEvent.setInstance(this.hungryForPizza);
    startEvent.setType(this.MessageStartEvent);
    this.level1.getContent().add(startEvent);

    Classification endEvent = PLMFactory.eINSTANCE.createClassification();
    endEvent.setInstance(this.hungerSatisfied);
    endEvent.setType(this.EndEvent);
    this.level1.getContent().add(endEvent);

    Classification taskOrder = PLMFactory.eINSTANCE.createClassification();
    taskOrder.setInstance(this.OrderPizza);
    taskOrder.setType(this.Task);
    this.level1.getContent().add(taskOrder);

  }

  @After
  public void destroy() {
    this.InteractionNode = null;
    this.ThrowEvent = null;
    this.CatchEvent = null;
    this.StartEvent = null;
    this.EndEvent = null;
    this.dm = null;
    this.level0 = null;
    this.level2 = null;
    this.level1 = null;
    this.hungerSatisfied = null;
    this.hungryForPizza = null;
    this.Activity = null;
    this.connection1 = null;
    this.MessageStartEvent = null;
  }

  @Test
  public void MessageFlowConstraintTest() {
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
    interactionConnection.setType(connection1);
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
  public void MessageFlowConstraintTest1() {
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
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.OrderPizza);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void isDeepKindOfTest6() {
    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepKindOf(Task)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.OrderPizza);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void isDeepKindOfTest7() {
    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("self.isDeepKindOf(ThrowWEvent)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.OrderPizza);
    Object returnValue = visitor.visit(tree);
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }

  @Test
  public void MessageFlowConstraintTaskToStartTrue() {
    Connection startToEnd = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd start = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd end = PLMFactory.eINSTANCE.createConnectionEnd();
    start.setMoniker("send");
    end.setMoniker("receive");
    startToEnd.setName("startToEnd");

    start.setConnection(startToEnd);
    end.setConnection(startToEnd);
    end.setDestination(this.OrderPizza);
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
  public void MessageFlowConstraintSendFromStartFalse() {
    Connection startToEnd = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd start = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd end = PLMFactory.eINSTANCE.createConnectionEnd();
    start.setMoniker("send");
    end.setMoniker("receive");
    startToEnd.setName("startToEnd");

    start.setConnection(startToEnd);
    end.setConnection(startToEnd);
    end.setDestination(this.OrderPizza);
    start.setDestination(this.hungryForPizza);
    end.setNavigable(true);
    start.setNavigable(false);

    Classification interactionConnection = PLMFactory.eINSTANCE.createClassification();
    interactionConnection.setInstance(startToEnd);
    interactionConnection.setType(connection1);
    this.level1.getContent().add(interactionConnection);

    this.level1.getContent().add(startToEnd);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "not(self.send -> forAll(r|r.isDeepKindOf(CatchEvent)))"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(startToEnd);
    Object returnValue = visitor.visit(tree);
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }

}
