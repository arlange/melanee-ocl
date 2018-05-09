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
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.level0 = PLMFactory.eINSTANCE.createLevel();
    this.level1 = PLMFactory.eINSTANCE.createLevel();
    this.level2 = PLMFactory.eINSTANCE.createLevel();
    this.dm.setName("dm");
    this.dm.getContent().add(level0);
    this.dm.getContent().add(level1);

    this.level0.getContent().add(CatchEvent);
    this.level0.getContent().add(ThrowEvent);
    this.level0.getContent().add(InteractionNode);
    this.level0.getContent().add(StartEvent);
    this.level0.getContent().add(EndEvent);
    this.level0.getContent().add(MessageStartEvent);

    // Connection Interaction Node
    connection1 = PLMFactory.eINSTANCE.createConnection();
    connection1.setName("InteractionNodeConnection");
    connection1.setPotency(2);
    ConnectionEnd receive = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd send = PLMFactory.eINSTANCE.createConnectionEnd();
    receive.setMoniker("receive");
    send.setMoniker("send");
    receive.setDestination(InteractionNode);
    send.setDestination(InteractionNode);
    receive.setNavigable(true);
    send.setNavigable(false);
    send.setConnection(connection1);
    receive.setConnection(connection1);
    this.level0.getContent().add(connection1);

    // SuperSubTypes InteractionNode -> ThrowEvent/CatchEvent
    Subtype subThrow = PLMFactory.eINSTANCE.createSubtype();
    Supertype sup = PLMFactory.eINSTANCE.createSupertype();
    Inheritance InteractionThrowCatch = PLMFactory.eINSTANCE.createInheritance();
    InteractionThrowCatch.getSubtype().add(subThrow);
    InteractionThrowCatch.getSupertype().add(sup);
    subThrow.setSubtype(ThrowEvent);
    sup.setSupertype(InteractionNode);
    level0.getContent().add(InteractionThrowCatch);

    Subtype subCatch = PLMFactory.eINSTANCE.createSubtype();
    Supertype super1 = PLMFactory.eINSTANCE.createSupertype();
    Inheritance InteractionThrowCatch1 = PLMFactory.eINSTANCE.createInheritance();
    InteractionThrowCatch1.getSubtype().add(subCatch);
    InteractionThrowCatch1.getSupertype().add(super1);
    subCatch.setSubtype(CatchEvent);
    super1.setSupertype(InteractionNode);
    level0.getContent().add(InteractionThrowCatch1);

    // SuperSubTypes ThrowEvent -> EndEvent
    Subtype subEnd = PLMFactory.eINSTANCE.createSubtype();
    Supertype superThrow = PLMFactory.eINSTANCE.createSupertype();
    Inheritance throwEnd = PLMFactory.eINSTANCE.createInheritance();
    throwEnd.getSubtype().add(subEnd);
    throwEnd.getSupertype().add(superThrow);
    subEnd.setSubtype(EndEvent);
    superThrow.setSupertype(ThrowEvent);
    level0.getContent().add(throwEnd);

    // SuperSubTypes CatchEvent -> StartEvent
    Subtype subStart = PLMFactory.eINSTANCE.createSubtype();
    Supertype superCatch = PLMFactory.eINSTANCE.createSupertype();
    Inheritance catchStart = PLMFactory.eINSTANCE.createInheritance();
    catchStart.getSubtype().add(subStart);
    catchStart.getSupertype().add(superCatch);
    subStart.setSubtype(StartEvent);
    superCatch.setSupertype(CatchEvent);
    level0.getContent().add(catchStart);

    // SuperSubTypes StartEvent -> MessageStartEvent
    Subtype messageStart = PLMFactory.eINSTANCE.createSubtype();
    Supertype startEventSup = PLMFactory.eINSTANCE.createSupertype();
    Inheritance startMessage = PLMFactory.eINSTANCE.createInheritance();
    startMessage.getSubtype().add(messageStart);
    startMessage.getSupertype().add(startEventSup);
    messageStart.setSubtype(MessageStartEvent);
    startEventSup.setSupertype(StartEvent);
    level0.getContent().add(startMessage);
    
    // level 1
    this.hungryForPizza = PLMFactory.eINSTANCE.createEntity();
    this.hungerSatisfied = PLMFactory.eINSTANCE.createEntity();

    this.hungerSatisfied.setName("hungerSatisfied");
    this.hungryForPizza.setName("hungryForPizza");

    level1.getContent().add(hungerSatisfied);
    level1.getContent().add(hungryForPizza);

    Classification startEvent = PLMFactory.eINSTANCE.createClassification();
    startEvent.setInstance(hungryForPizza);
    startEvent.setType(MessageStartEvent);
    level1.getContent().add(startEvent);

    Classification endEvent = PLMFactory.eINSTANCE.createClassification();
    endEvent.setInstance(hungerSatisfied);
    endEvent.setType(EndEvent);
    level1.getContent().add(endEvent);

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
  public void MessageFlowConstraintTestForInstances() {
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
        "self.receive.isDeepKindOf(CatchEvent) and self.send.isDeepKindOf(ThrowEvent)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(connection1);
    Object returnValue = visitor.visit(tree);
    System.out.println(returnValue.toString());
    assertEquals(false, Boolean.parseBoolean(returnValue.toString()));
  }
}
