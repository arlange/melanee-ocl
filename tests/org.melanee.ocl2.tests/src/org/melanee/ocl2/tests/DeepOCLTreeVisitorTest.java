/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
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
import org.melanee.core.models.plm.PLM.Feature;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOCLClabjectWrapperImpl;
import org.melanee.ocl2.service.DeepOclRuleVisitor;
import org.melanee.ocl2.service.util.OclInvalid;

public class DeepOCLTreeVisitorTest {

	Clabject c;
	DeepModel dm;
	Level l;

	@Before
	public void setUp() {
		this.c = PLMFactory.eINSTANCE.createEntity();
		this.c.setName("Test");
		this.dm = PLMFactory.eINSTANCE.createDeepModel();
		this.l = PLMFactory.eINSTANCE.createLevel();
	}

	@After
	public void destroy() {
		this.c = null;
		this.dm = null;
		this.l = null;
	}

	// @Test
	public void test() {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("context CustomerCard\ninv checkDates: validFrom.isBefore(goodThru)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		visitor.visit(tree);
	}

	// @Test
	public void testNavigation() {
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		attr.setName("age");
		this.c.getFeature().add(attr);
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context Customer\ninv ofAge: self.age"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		visitor.visit(tree);
		DeepOCLClabjectWrapperImpl wrapper = visitor.getWrapper();
		assertEquals(attr, wrapper.getNavigationStack().peek().getSecond().toArray()[0]);
	}

	@Test
	public void testInv() {
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		attr.setName("age");
		attr.setValue("20");
		attr.setDatatype("Integer");
		Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
		attr1.setDatatype("Boolean");
		attr1.setName("ofAge");
		attr1.setValue("true");
		this.c.getFeature().add(attr);
		this.c.getFeature().add(attr1);
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
				"context Customer\ninv ofAge: self.age > 18 implies self.age < 150 and self.age = 20"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		assertEquals(Boolean.parseBoolean(returnValue.toString()), true);
		// DeepOCLClabjectWrapperImpl wrapper = visitor.getWrapper();

	}

	@Test
	public void nestedSelectTest() {
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		// first level
		this.c.setName("Company");
		Clabject customer = PLMFactory.eINSTANCE.createEntity();
		customer.setName("Customer");
		Clabject transaction = PLMFactory.eINSTANCE.createEntity();
		transaction.setName("Transaction");
		// second level
		Clabject customer1 = PLMFactory.eINSTANCE.createEntity();
		customer1.setName("Hans Jörg");
		Clabject customer2 = PLMFactory.eINSTANCE.createEntity();
		customer2.setName("Detlef");
		Clabject transaction1 = PLMFactory.eINSTANCE.createEntity();
		transaction1.setName("HAJO kauft CD");
		Clabject transaction2 = PLMFactory.eINSTANCE.createEntity();
		transaction2.setName("Dette kauft ein");

		dm.getContent().add(l);
		Level l1 = PLMFactory.eINSTANCE.createLevel();
		dm.getContent().add(l1);
		l.getContent().add(c);
		l.getContent().add(customer);
		l.getContent().add(transaction);
		l1.getContent().add(customer1);
		l.getContent().add(transaction1);

		// company classification
		Classification classi9 = PLMFactory.eINSTANCE.createClassification();
		classi9.setInstance(this.c);
		classi9.setType(customer);
		l1.getContent().add(classi9);
		// customer classification
		Classification classi = PLMFactory.eINSTANCE.createClassification();
		classi.setInstance(customer1);
		classi.setType(customer);
		l1.getContent().add(classi);
		Classification classi1 = PLMFactory.eINSTANCE.createClassification();
		classi1.setInstance(customer2);
		classi1.setType(customer);
		l1.getContent().add(classi1);
		// transaction classification
		Classification classi2 = PLMFactory.eINSTANCE.createClassification();
		classi2.setInstance(transaction1);
		classi2.setType(transaction);
		l1.getContent().add(classi2);
		Classification classi3 = PLMFactory.eINSTANCE.createClassification();
		classi3.setInstance(transaction2);
		classi3.setType(transaction);
		l1.getContent().add(classi3);

		// Connection setUp company -> customer
		Connection connect = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti2.setMoniker("customer");
		parti.setConnection(connect);
		parti.setDestination(this.c);
		parti2.setDestination(customer);
		parti2.setNavigable(true);
		parti2.setConnection(connect);

		// Connection setUp customer -> transaction
		Connection connect2 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti5 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti6 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti5.setConnection(connect2);
		parti5.setDestination(customer);
		parti6.setDestination(transaction);
		parti6.setMoniker("transaction");
		parti6.setNavigable(true);
		parti6.setConnection(connect2);

		Connection connect20 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti15 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti16 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti15.setConnection(connect20);
		parti15.setDestination(customer);
		parti16.setDestination(transaction1);
		parti16.setMoniker("transaction");
		parti16.setNavigable(true);
		parti16.setConnection(connect20);

		l.getContent().add(connect);
		l.getContent().add(connect2);
		l.getContent().add(connect20);

		Connection connect3 = PLMFactory.eINSTANCE.createConnection();
		Classification classi8 = PLMFactory.eINSTANCE.createClassification();
		classi8.setInstance(connect3);
		classi8.setType(connect2);
		ConnectionEnd parti7 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti8 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti7.setDestination(customer1);
		parti8.setDestination(transaction1);
		parti8.setNavigable(true);
		parti7.setConnection(connect3);
		parti8.setConnection(connect3);

		Connection connect4 = PLMFactory.eINSTANCE.createConnection();
		Classification classi7 = PLMFactory.eINSTANCE.createClassification();
		classi7.setInstance(connect4);
		classi7.setType(connect2);
		ConnectionEnd parti9 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti10 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti9.setDestination(customer2);
		parti10.setDestination(transaction2);
		parti10.setNavigable(true);
		parti9.setConnection(connect4);
		parti10.setConnection(connect4);

		l1.getContent().add(connect3);
		l1.getContent().add(connect4);

		attr.setName("value");
		attr.setValue("101");
		attr.setDatatype("Integer");
		Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
		transaction.getFeature().add(attr);
		attr1.setName("value");
		attr1.setValue("108");
		attr1.setDatatype("Integer");
		transaction1.getFeature().add(attr1);
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
				"context Company \ninv test: customer -> select(c|c.transaction -> select(value>100)->size()=2)->size()=1"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		assertEquals("true", returnValue.toString());
	}

	@Test
	public void nestedSelectTestWithClassificationOclInvalid() {
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		// first level
		this.c.setName("Company");
		Clabject customer = PLMFactory.eINSTANCE.createEntity();
		customer.setName("Customer");
		Clabject transaction = PLMFactory.eINSTANCE.createEntity();
		transaction.setName("Transaction");
		// second level
		Clabject company = PLMFactory.eINSTANCE.createEntity();
		company.setName("IBM");
		Clabject customer1 = PLMFactory.eINSTANCE.createEntity();
		customer1.setName("Hans Jörg");
		Clabject customer2 = PLMFactory.eINSTANCE.createEntity();
		customer2.setName("Detlef");
		Clabject transaction1 = PLMFactory.eINSTANCE.createEntity();
		transaction1.setName("HAJO kauft CD");
		Clabject transaction2 = PLMFactory.eINSTANCE.createEntity();
		transaction2.setName("Dette kauft ein");

		dm.getContent().add(l);
		Level l1 = PLMFactory.eINSTANCE.createLevel();
		dm.getContent().add(l1);
		l.getContent().add(this.c);
		l.getContent().add(customer);
		l.getContent().add(transaction);
		l1.getContent().add(company);
		l1.getContent().add(customer1);
		l1.getContent().add(transaction1);
		l1.getContent().add(customer2);
		l1.getContent().add(transaction2);

		// company classification
		Classification classi9 = PLMFactory.eINSTANCE.createClassification();
		classi9.setInstance(this.c);
		classi9.setType(company);
		l1.getContent().add(classi9);
		// customer classification
		Classification classi = PLMFactory.eINSTANCE.createClassification();
		classi.setInstance(customer1);
		classi.setType(customer);
		l1.getContent().add(classi);
		Classification classi1 = PLMFactory.eINSTANCE.createClassification();
		classi1.setInstance(customer2);
		classi1.setType(customer);
		l1.getContent().add(classi1);
		// transaction classification
		Classification classi2 = PLMFactory.eINSTANCE.createClassification();
		classi2.setInstance(transaction1);
		classi2.setType(transaction);
		l1.getContent().add(classi2);
		Classification classi3 = PLMFactory.eINSTANCE.createClassification();
		classi3.setInstance(transaction2);
		classi3.setType(transaction);
		l1.getContent().add(classi3);

		// Connection setUp company -> customer
		Connection connect = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti2.setMoniker("customer");
		parti.setConnection(connect);
		parti.setDestination(this.c);
		parti2.setDestination(customer);
		parti2.setNavigable(true);
		parti2.setConnection(connect);

		// Connection setUp customer -> transaction
		Connection connect2 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti5 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti6 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti5.setConnection(connect2);
		parti5.setDestination(customer);
		parti6.setDestination(transaction);
		parti6.setMoniker("transaction");
		parti6.setNavigable(true);
		parti6.setConnection(connect2);

		l.getContent().add(connect);
		l.getContent().add(connect2);

		Connection connect10 = PLMFactory.eINSTANCE.createConnection();
		Classification classi10 = PLMFactory.eINSTANCE.createClassification();
		classi10.setInstance(connect10);
		classi10.setType(connect);
		ConnectionEnd parti18 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti19 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti18.setDestination(company);
		parti19.setDestination(customer1);
		parti19.setNavigable(true);
		parti18.setConnection(connect10);
		parti19.setConnection(connect10);

		Connection connect11 = PLMFactory.eINSTANCE.createConnection();
		Classification classi11 = PLMFactory.eINSTANCE.createClassification();
		classi11.setInstance(connect11);
		classi11.setType(connect2);
		ConnectionEnd parti20 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti21 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti20.setDestination(company);
		parti21.setDestination(customer2);
		parti21.setNavigable(true);
		parti20.setConnection(connect11);
		parti21.setConnection(connect11);

		Connection connect3 = PLMFactory.eINSTANCE.createConnection();
		Classification classi8 = PLMFactory.eINSTANCE.createClassification();
		classi8.setInstance(connect3);
		classi8.setType(connect2);
		ConnectionEnd parti7 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti8 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti7.setDestination(customer1);
		parti8.setDestination(transaction1);
		parti8.setNavigable(true);
		parti7.setConnection(connect3);
		parti8.setConnection(connect3);

		Connection connect4 = PLMFactory.eINSTANCE.createConnection();
		Classification classi7 = PLMFactory.eINSTANCE.createClassification();
		classi7.setInstance(connect4);
		classi7.setType(connect2);
		ConnectionEnd parti9 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti10 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti9.setDestination(customer2);
		parti10.setDestination(transaction2);
		parti10.setNavigable(true);
		parti9.setConnection(connect4);
		parti10.setConnection(connect4);

		l1.getContent().add(connect3);
		l1.getContent().add(connect4);

		attr.setName("value");
		attr.setValue("101");
		attr.setDatatype("Integer");
		Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
		transaction1.getFeature().add(attr);
		attr1.setName("value");
		attr1.setValue("108");
		attr1.setDatatype("Integer");
		transaction2.getFeature().add(attr1);
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
				"context(1) Company \ninv test: customer -> select(c|c.transaction -> select(value>100))->size()=1"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));

		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});

		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		assertTrue(returnValue instanceof OclInvalid);
	}

	@Test
	public void nestedRejectTest() {
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		// first level
		this.c.setName("Company");
		Clabject customer = PLMFactory.eINSTANCE.createEntity();
		customer.setName("Customer");
		Clabject transaction = PLMFactory.eINSTANCE.createEntity();
		transaction.setName("Transaction");
		// second level
		Clabject customer1 = PLMFactory.eINSTANCE.createEntity();
		customer1.setName("Hans Jörg");
		Clabject customer2 = PLMFactory.eINSTANCE.createEntity();
		customer2.setName("Detlef");
		Clabject transaction1 = PLMFactory.eINSTANCE.createEntity();
		transaction1.setName("HAJO kauft CD");
		Clabject transaction2 = PLMFactory.eINSTANCE.createEntity();
		transaction2.setName("Dette kauft ein");

		dm.getContent().add(l);
		Level l1 = PLMFactory.eINSTANCE.createLevel();
		dm.getContent().add(l1);
		l.getContent().add(c);
		l.getContent().add(customer);
		l.getContent().add(transaction);
		l1.getContent().add(customer1);
		l.getContent().add(transaction1);

		// company classification
		Classification classi9 = PLMFactory.eINSTANCE.createClassification();
		classi9.setInstance(this.c);
		classi9.setType(customer);
		l1.getContent().add(classi9);
		// customer classification
		Classification classi = PLMFactory.eINSTANCE.createClassification();
		classi.setInstance(customer1);
		classi.setType(customer);
		l1.getContent().add(classi);
		Classification classi1 = PLMFactory.eINSTANCE.createClassification();
		classi1.setInstance(customer2);
		classi1.setType(customer);
		l1.getContent().add(classi1);
		// transaction classification
		Classification classi2 = PLMFactory.eINSTANCE.createClassification();
		classi2.setInstance(transaction1);
		classi2.setType(transaction);
		l1.getContent().add(classi2);
		Classification classi3 = PLMFactory.eINSTANCE.createClassification();
		classi3.setInstance(transaction2);
		classi3.setType(transaction);
		l1.getContent().add(classi3);

		// Connection setUp company -> customer
		Connection connect = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti2.setMoniker("customer");
		parti.setConnection(connect);
		parti.setDestination(this.c);
		parti2.setDestination(customer);
		parti2.setNavigable(true);
		parti2.setConnection(connect);

		// Connection setUp customer -> transaction
		Connection connect2 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti5 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti6 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti5.setConnection(connect2);
		parti5.setDestination(customer);
		parti6.setDestination(transaction);
		parti6.setMoniker("transaction");
		parti6.setNavigable(true);
		parti6.setConnection(connect2);

		Connection connect20 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti15 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti16 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti15.setConnection(connect20);
		parti15.setDestination(customer);
		parti16.setDestination(transaction1);
		parti16.setMoniker("transaction");
		parti16.setNavigable(true);
		parti16.setConnection(connect20);

		l.getContent().add(connect);
		l.getContent().add(connect2);
		l.getContent().add(connect20);

		Connection connect3 = PLMFactory.eINSTANCE.createConnection();
		Classification classi8 = PLMFactory.eINSTANCE.createClassification();
		classi8.setInstance(connect3);
		classi8.setType(connect2);
		ConnectionEnd parti7 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti8 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti7.setDestination(customer1);
		parti8.setDestination(transaction1);
		parti8.setNavigable(true);
		parti7.setConnection(connect3);
		parti8.setConnection(connect3);

		Connection connect4 = PLMFactory.eINSTANCE.createConnection();
		Classification classi7 = PLMFactory.eINSTANCE.createClassification();
		classi7.setInstance(connect4);
		classi7.setType(connect2);
		ConnectionEnd parti9 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti10 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti9.setDestination(customer2);
		parti10.setDestination(transaction2);
		parti10.setNavigable(true);
		parti9.setConnection(connect4);
		parti10.setConnection(connect4);

		l1.getContent().add(connect3);
		l1.getContent().add(connect4);

		attr.setName("value");
		attr.setValue("101");
		attr.setDatatype("Integer");
		Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
		transaction.getFeature().add(attr);
		attr1.setName("value");
		attr1.setValue("108");
		attr1.setDatatype("Integer");
		transaction1.getFeature().add(attr1);
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
				"context Company \ninv test: customer -> select(c|c.transaction -> reject(value>100)->size()=0)->size()=1"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		assertEquals("true", returnValue.toString());
	}

	@Test
	public void nestedReject1Test() {
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		// first level
		Clabject customer = PLMFactory.eINSTANCE.createEntity();
		customer.setName("Customer");
		Clabject transaction = PLMFactory.eINSTANCE.createEntity();
		transaction.setName("Transaction");
		Clabject customer1 = PLMFactory.eINSTANCE.createEntity();
		customer1.setName("Hans Jörg");
		Clabject customer2 = PLMFactory.eINSTANCE.createEntity();
		customer2.setName("Detlef");
		Clabject transaction1 = PLMFactory.eINSTANCE.createEntity();
		transaction1.setName("HAJO kauft CD");
		Clabject transaction2 = PLMFactory.eINSTANCE.createEntity();
		transaction2.setName("Dette kauft ein");

		Clabject dummy = PLMFactory.eINSTANCE.createEntity();
		dummy.setName("dummy");

		dm.getContent().add(l);
		l.getContent().add(customer);
		l.getContent().add(transaction);
		l.getContent().add(customer1);
		l.getContent().add(transaction1);
		l.getContent().add(dummy);

		// Connection setUp customer -> transaction
		Connection connect2 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti5 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti6 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti5.setConnection(connect2);
		parti5.setDestination(customer);
		parti6.setDestination(transaction);
		parti6.setMoniker("transaction");
		parti6.setNavigable(true);
		parti6.setConnection(connect2);

		Connection connect20 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti15 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti16 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti15.setConnection(connect20);
		parti15.setDestination(customer);
		parti16.setDestination(transaction1);
		parti16.setMoniker("transaction");
		parti16.setNavigable(true);
		parti16.setConnection(connect20);

		l.getContent().add(connect2);
		l.getContent().add(connect20);

		Connection connect3 = PLMFactory.eINSTANCE.createConnection();
		Classification classi8 = PLMFactory.eINSTANCE.createClassification();
		classi8.setInstance(connect3);
		classi8.setType(connect2);
		ConnectionEnd parti7 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti8 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti7.setDestination(customer1);
		parti8.setDestination(transaction);
		parti8.setMoniker("transaction");
		parti8.setNavigable(true);
		parti7.setConnection(connect3);
		parti8.setConnection(connect3);

		Connection connect5 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti20 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti21 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti20.setDestination(transaction);
		parti21.setDestination(dummy);
		parti21.setMoniker("dummy");
		parti21.setNavigable(true);
		parti20.setConnection(connect5);
		parti21.setConnection(connect5);

		Connection connect6 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti22 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti23 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti22.setDestination(transaction2);
		parti23.setDestination(dummy);
		parti23.setMoniker("dummy");
		parti23.setNavigable(true);
		parti22.setConnection(connect6);
		parti23.setConnection(connect6);

		Connection connect7 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti24 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti25 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti24.setDestination(dummy);
		parti25.setDestination(customer);
		parti25.setMoniker("customer");
		parti25.setNavigable(true);
		parti24.setConnection(connect7);
		parti25.setConnection(connect7);

		Connection connect8 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti26 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti27 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti26.setDestination(dummy);
		parti27.setDestination(customer1);
		parti27.setMoniker("customer");
		parti27.setNavigable(true);
		parti26.setConnection(connect8);
		parti27.setConnection(connect8);

		l.getContent().add(connect3);
		l.getContent().add(connect5);
		l.getContent().add(connect6);
		l.getContent().add(connect7);
		l.getContent().add(connect8);

		// this test is navigating from the 'customer' to transaction and then
		// to dummy. From the 'dummy' entity both customer entities are
		// connected and navigatable. Then the reject operation with the
		// parameter 'self' = 'customer' is executed and just the 'customer1'
		// entity is expected to be in the return value as a list.

		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("context Customer \ninv reject1: transaction.dummy.customer -> reject(self)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(customer);
		Object returnValue = visitor.visit(tree);
		assertEquals(Arrays.asList(customer1), returnValue);
	}

	@Test
	public void asSetTest() {
		this.c.setName("A");
		Clabject clab1 = PLMFactory.eINSTANCE.createEntity();
		clab1.setName("1");
		Clabject clab2 = PLMFactory.eINSTANCE.createEntity();
		clab2.setName("2");
		Clabject clab3 = PLMFactory.eINSTANCE.createEntity();
		clab3.setName("3");

		// Connection setUp customer -> transaction
		Connection connect1 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti5 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti6 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti5.setConnection(connect1);
		parti5.setDestination(this.c);
		parti6.setDestination(clab1);
		parti6.setMoniker("a");
		parti6.setNavigable(true);
		parti6.setConnection(connect1);

		Connection connect2 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti15 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti16 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti15.setConnection(connect2);
		parti15.setDestination(this.c);
		parti16.setDestination(clab2);
		parti16.setMoniker("a");
		parti16.setNavigable(true);
		parti16.setConnection(connect2);

		Connection connect3 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti7 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti8 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti7.setConnection(connect3);
		parti7.setDestination(clab2);
		parti8.setDestination(clab3);
		parti8.setMoniker("b");
		parti8.setNavigable(true);
		parti8.setConnection(connect3);

		Connection connect4 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti9 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti10 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti9.setConnection(connect4);
		parti9.setDestination(clab1);
		parti10.setDestination(clab3);
		parti10.setMoniker("b");
		parti10.setNavigable(true);
		parti10.setConnection(connect4);

		this.l.getContent().add(clab3);
		this.l.getContent().add(clab1);
		this.l.getContent().add(clab2);
		this.l.getContent().add(this.c);
		this.l.getContent().add(connect4);
		this.l.getContent().add(connect1);
		this.l.getContent().add(connect2);
		this.l.getContent().add(connect3);
		this.dm.getContent().add(this.l);

		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context A \ninv test: self.a.b->asSet()"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		Set<Clabject> resultSet = new HashSet<>();
		resultSet.add(clab3);
		assertEquals(resultSet, returnValue);
		// controll test case, if not as set the clab3 is twice occurring in a
		// list
		DeepOclLexer oclLexer1 = new DeepOclLexer(new ANTLRInputStream("context A \ninv test: self.a.b"));
		DeepOclParser parser1 = new DeepOclParser(new CommonTokenStream(oclLexer1));
		ParseTree tree1 = parser1.contextDeclCS();
		Object returnValue1 = visitor.visit(tree1);
		assertEquals(Arrays.asList(clab3, clab3), returnValue1);
	}

	/**
	 * test for let variables
	 */
	@Test
	public void letVariableTest() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		attr.setDatatype("Boolean");
		attr.setName("notValid");
		attr.setValue("false");
		Attribute attr1 = PLMFactory.eINSTANCE.createAttribute();
		attr1.setDatatype("Boolean");
		attr1.setName("valid");
		attr1.setValue("false");

		this.c.getFeature().add(attr1);
		this.c.getFeature().add(attr);

		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
				"context A \ninv test: let correctDate:Boolean=self.valid in if self.notValid then correctDate = false else correctDate = true endif"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		assertEquals(true, Boolean.parseBoolean(returnValue.toString()));
	}

	/**
	 * test for linguistic aspects
	 */
	@Test
	public void lingoFeatureTest() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		attr.setDatatype("Boolean");
		attr.setName("notValid");
		attr.setValue("false");
		Method meth = PLMFactory.eINSTANCE.createMethod();
		meth.setName("test()");

		this.c.getFeature().add(meth);
		this.c.getFeature().add(attr);

		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context A \ninv test: self.#getAllFeatures()#"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		Collection<Feature> result = (Collection<Feature>) returnValue;
		assertEquals(2, result.size());
		// not ordered.
		// assertEquals(Arrays.asList(attr, meth), result);
	}

	/**
	 * test for linguistic aspects
	 */
	@Test
	public void lingoAttributeTest() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		attr.setDatatype("Boolean");
		attr.setName("notValid");
		attr.setValue("false");
		this.c.getFeature().add(attr);
		Method meth = PLMFactory.eINSTANCE.createMethod();
		meth.setName("test()");

		this.c.getFeature().add(meth);

		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("context A \ninv test: self.#getAllAttributes()#"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		Collection<Feature> result = (Collection<Feature>) returnValue;
		assertEquals(1, result.size());
		assertEquals(attr, result.toArray()[0]);
	}

	/**
	 * test for linguistic aspects
	 */
	@Test
	public void lingoPotencyTest() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		attr.setDatatype("Boolean");
		attr.setName("notValid");
		attr.setValue("false");
		this.c.getFeature().add(attr);

		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context A \ninv test: self.#potency#"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		Collection<Element> attrsa = ((Collection<Element>) returnValue);
		Attribute a = (Attribute) attrsa.toArray()[0];
		assertEquals(1, Integer.parseInt(a.getValue()));
	}

	@Test
	public void lingoDefinedNavigationsest() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		attr.setDatatype("Boolean");
		attr.setName("notValid");
		attr.setValue("false");
		this.c.getFeature().add(attr);

		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("context A \ninv test: self.#getDefinedNavigations()#"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.c);
		Object returnValue = visitor.visit(tree);
		Collection<Object> result = (Collection<Object>) returnValue;
		assertTrue(result.isEmpty());
	}

	@Test
	public void lingoDeepModelLevelsTest() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Attribute attr = PLMFactory.eINSTANCE.createAttribute();
		attr.setDatatype("Boolean");
		attr.setName("notValid");
		attr.setValue("false");
		this.c.getFeature().add(attr);

		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context DeepModel \ninv test: self.#content#"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
		Object returnValue = visitor.visit(tree);
		Collection<Object> result = (Collection<Object>) returnValue;
		assertEquals(Arrays.asList(this.l), result);
	}

	@Test
	public void lingoDirectTypes() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Level l1 = PLMFactory.eINSTANCE.createLevel();
		this.dm.getContent().add(l1);
		Clabject car = PLMFactory.eINSTANCE.createEntity();
		car.setName("Car");
		Classification classi = PLMFactory.eINSTANCE.createClassification();
		classi.setInstance(car);
		classi.setType(this.c);
		l1.getContent().add(classi);
		l1.getContent().add(car);

		// SmallInfo classification
		Classification classi4 = PLMFactory.eINSTANCE.createClassification();
		classi4.setInstance(car);
		classi4.setType(this.c);
		l1.getContent().add(classi4);

		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("context DeepModel \ninv test: self.#getDirectTypes()#"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(car);
		Object returnValue = visitor.visit(tree);
		Collection<Object> result = (Collection<Object>) returnValue;
		assertEquals(Arrays.asList(this.c), result);
	}

	@Test
	public void lingoConnections() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Level l1 = PLMFactory.eINSTANCE.createLevel();
		this.dm.getContent().add(l1);
		Clabject car = PLMFactory.eINSTANCE.createEntity();
		car.setName("Car");
		this.l.getContent().add(car);
		// Connection setUp ArnesCar -> rearRight
		Connection connect9 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti18 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti19 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti19.setMoniker("test");
		parti18.setConnection(connect9);
		parti18.setDestination(this.c);
		parti19.setDestination(car);
		parti19.setNavigable(true);
		parti19.setConnection(connect9);
		parti19.setLower(1);
		parti19.setUpper(1);
		parti18.setUpper(1);
		parti18.setLower(1);

		this.l.getContent().add(connect9);

		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("context DeepModel \ninv test: self.#getConnections()#"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(car);
		Object returnValue = visitor.visit(tree);
		Collection<Object> result = (Collection<Object>) returnValue;
		assertEquals(Arrays.asList(connect9), result);
	}

	@Test
	public void lingoConnectionsParticipants() {
		this.dm.getContent().add(this.l);
		this.l.getContent().add(this.c);
		Level l1 = PLMFactory.eINSTANCE.createLevel();
		this.dm.getContent().add(l1);
		Clabject car = PLMFactory.eINSTANCE.createEntity();
		car.setName("Car");
		this.l.getContent().add(car);
		// Connection setUp ArnesCar -> rearRight
		Connection connect9 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti18 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti19 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti19.setMoniker("test");
		parti18.setConnection(connect9);
		parti18.setDestination(this.c);
		parti19.setDestination(car);
		parti19.setNavigable(true);
		parti19.setConnection(connect9);
		parti19.setLower(1);
		parti19.setUpper(1);
		parti18.setUpper(1);
		parti18.setLower(1);

		this.l.getContent().add(connect9);

		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("context DeepModel \ninv test: self.#getConnections()#.#getParticipants()#"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(car);
		Object returnValue = visitor.visit(tree);
		Collection<Object> result = (Collection<Object>) returnValue;
		assertEquals(Arrays.asList(this.c, car), result);
	}

}
