package org.melanee.ocl2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.core.models.plm.PLM.Subtype;
import org.melanee.core.models.plm.PLM.Supertype;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class InheritanceRulesPaper {

	private DeepModel dm;
	private Level level0;
	private Level level1;

	@Before
	public void setUp() {
		this.dm = PLMFactory.eINSTANCE.createDeepModel();
		this.level0 = PLMFactory.eINSTANCE.createLevel();
		level0.setName("O0");
		this.level1 = PLMFactory.eINSTANCE.createLevel();
		level0.setName("O1");
		dm.getContent().add(level0);
		dm.getContent().add(level1);
	}

	@After

	public void tearDown() {
		this.dm = null;
		this.level0 = null;
		this.level1 = null;
	}

	@Test
	public void durabilityValueSame() {
		Clabject a = PLMFactory.eINSTANCE.createEntity();
		a.setName("A");
		level0.getContent().add(a);
		a.setPotency(2);
		Clabject b = PLMFactory.eINSTANCE.createEntity();
		b.setName("B");
		b.setPotency(2);
		Clabject c = PLMFactory.eINSTANCE.createEntity();
		c.setName("C");
		c.setPotency(2);
		level0.getContent().add(c);
		Attribute a1 = PLMFactory.eINSTANCE.createAttribute();
		a1.setDurability(2);
		a1.setMutability(2);
		a1.setName("a");

		Attribute b1 = PLMFactory.eINSTANCE.createAttribute();
		b1.setDurability(2);
		b1.setMutability(2);
		b1.setName("a");

		// Inheritance between supertype and c
		Subtype sub = PLMFactory.eINSTANCE.createSubtype();
		Supertype sup = PLMFactory.eINSTANCE.createSupertype();
		Inheritance connect = PLMFactory.eINSTANCE.createInheritance();
		connect.getSubtype().add(sub);
		connect.getSupertype().add(sup);
		sub.setSubtype(b);
		sup.setSupertype(a);
		level0.getContent().add(connect);

		Subtype sub1 = PLMFactory.eINSTANCE.createSubtype();
		Supertype sup1 = PLMFactory.eINSTANCE.createSupertype();
		Inheritance connect1 = PLMFactory.eINSTANCE.createInheritance();
		connect1.getSubtype().add(sub1);
		connect1.getSupertype().add(sup1);
		sub1.setSubtype(c);
		sup1.setSupertype(b);
		level0.getContent().add(connect1);

		EList<Clabject> expected = new BasicEList<>();
		expected.add(c);
		expected.add(b);

		assertTrue(expected.equals(a.getSubtypes()));

		// DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
		// "context dm inv durability: Clabject -> select(c|c.#getSubtypes()# > 0) ->
		// forAll(cc|cc.#getAllAttributes()# -> select(a|a.##))"));
		// DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		// ParseTree tree = parser.contextDeclCS();
		// DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
		// long startTime = System.currentTimeMillis();
		// Object returnValue = visitor.visit(tree);
		// long endTime = System.currentTimeMillis();
		// System.out
		// .println("self -> closure(parent) -> size() = 5 --> " + (endTime - startTime)
		// + " ms");
		// assertEquals("true", returnValue.toString());
		// }
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
				"context dm inv firstPotency: Feature -> select(f|f.#getDurability()# > 0) -> forAll(f|f.#getDurability()# = (f.#getClabject()#.#getSubtypes()# -> select(s|s.#getFeatures()# -> select(ff|ff.#getName()#=f.#getName()#).#getDurability()#)))"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
		long startTime = System.currentTimeMillis();
		Object returnValue = visitor.visit(tree);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + " ms");
		assertEquals("true", returnValue.toString());
	}
}
