package org.melanee.ocl2.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.core.models.plm.PLM.Subtype;
import org.melanee.core.models.plm.PLM.Supertype;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class DeepClassificationChecking {

	DeepModel dm;
	Level level0;
	Level level1;
	Level level2;
	Clabject EmployeeType;
	Clabject ManagementEmployeeType;
	Clabject ManagementEmployee;
	Clabject Steve;

	@Before
	public void initialize() {
		this.dm = PLMFactory.eINSTANCE.createDeepModel();
		this.level0 = PLMFactory.eINSTANCE.createLevel();
		this.level1 = PLMFactory.eINSTANCE.createLevel();
		this.level2 = PLMFactory.eINSTANCE.createLevel();
		// add levels to deep model
		this.dm.getContent().add(level0);
		this.dm.getContent().add(level1);
		this.dm.getContent().add(level2);

		this.EmployeeType = PLMFactory.eINSTANCE.createEntity();
		this.EmployeeType.setName("EmployeeType");
		this.ManagementEmployeeType = PLMFactory.eINSTANCE.createEntity();
		this.ManagementEmployeeType.setName("ManagementEmployeeType");
		this.ManagementEmployee = PLMFactory.eINSTANCE.createEntity();
		this.ManagementEmployee.setName("ManagementEmployee");
		this.Steve = PLMFactory.eINSTANCE.createEntity();
		this.Steve.setName("Steve");
		// add entities to levels
		this.level0.getContent().add(EmployeeType);
		this.level0.getContent().add(ManagementEmployeeType);

		this.level1.getContent().add(ManagementEmployee);
		
		this.level2.getContent().add(Steve);

		// inheritance chain
		// EmployeeType
		// |
		// ManagementEmployeeType
		Subtype sub = PLMFactory.eINSTANCE.createSubtype();
		Supertype sup = PLMFactory.eINSTANCE.createSupertype();
		Inheritance connect = PLMFactory.eINSTANCE.createInheritance();
		connect.getSubtype().add(sub);
		connect.getSupertype().add(sup);
		sub.setSubtype(ManagementEmployeeType);
		sup.setSupertype(EmployeeType);
		level0.getContent().add(connect);

		// classification to ManagementEmployee
		Classification level0ToLevel1 = PLMFactory.eINSTANCE.createClassification();
		level0ToLevel1.setInstance(ManagementEmployee);
		level0ToLevel1.setType(ManagementEmployeeType);
		level1.getContent().add(level0ToLevel1);

		// classification to Steve
		Classification level1ToLevel2 = PLMFactory.eINSTANCE.createClassification();
		level1ToLevel2.setInstance(Steve);
		level1ToLevel2.setType(ManagementEmployee);
		level2.getContent().add(level1ToLevel2);
	}

	@Test
	public void testIsInstanceOfTrue() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isInstanceOf(ManagementEmployee)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertTrue(returnValue);

	}

	@Test
	public void testIsInstanceOfFalseET() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isInstanceOf(EmployeeType)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertFalse(returnValue);
	}

	@Test
	public void testIsInstanceOfFalseMET() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isInstanceOf(ManagementEmployeeType)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertFalse(returnValue);
	}

	@Test
	public void testIsDeepInstanceOfMET() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepInstanceOf(ManagementEmployeeType)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertTrue(returnValue);
	}

	@Test
	public void testIsDeepInstanceOfET() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepInstanceOf(EmployeeType)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertTrue(returnValue);

	}

	@Test
	public void testIsDeepInstanceOfME() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDeepInstanceOf(ManagementEmployee)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertTrue(returnValue);
	}

	@Test
	public void testIsDirectInstanceOfME() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDirectInstanceOf(ManagementEmployee)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertTrue(returnValue);

	}

	@Test
	public void testIsDirectInstanceOfET() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.isDirectInstanceOf(EmployeeType)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertFalse(returnValue);
	}

	@Test
	public void testIsDirectInstanceOfMET() {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("self.isDirectInstanceOf(ManagementEmployeeType)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertFalse(returnValue);
	}

	@Test
	public void testIsDeepDirectInstanceOfMET() {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("self.isDeepDirectInstanceOf(ManagementEmployeeType)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertTrue(returnValue);
	}

	@Test
	public void testIsDeepDirectInstanceOfME() {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("self.isDeepDirectInstanceOf(ManagementEmployee)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertTrue(returnValue);
	}
	
	@Test
	public void testIsDeepDirectInstanceOfET() {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("self.isDeepDirectInstanceOf(EmployeeType)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Steve);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertFalse(returnValue);
	}
}
