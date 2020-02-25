package org.melanee.ocl2.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

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
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class OCLFilterTest {
	Clabject customer;
	Clabject bicycleConfiguration;
	Connection invoice;
	DeepModel dm;
	Level l;

	@Before
	public void setUp() {
		this.customer = PLMFactory.eINSTANCE.createEntity();
		this.customer.setName("Customer");
		this.bicycleConfiguration = PLMFactory.eINSTANCE.createEntity();
		this.bicycleConfiguration.setName("BicycleConfiguration");
		this.invoice = PLMFactory.eINSTANCE.createConnection();
		this.invoice.setName("Invoice");
		this.dm = PLMFactory.eINSTANCE.createDeepModel();
		this.l = PLMFactory.eINSTANCE.createLevel();
	}

	@After
	public void destroy() {
		this.customer = null;
		this.bicycleConfiguration = null;
		this.invoice = null;
		this.dm = null;
		this.l = null;
	}

	@Test
	public void filterOCL() {
		Level l1 = PLMFactory.eINSTANCE.createLevel();

		Attribute price = PLMFactory.eINSTANCE.createAttribute();
		price.setDatatype("Real");
		price.setName("price");
		price.setValue("4500");

		ConnectionEnd customerEnd = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd bicycleEnd = PLMFactory.eINSTANCE.createConnectionEnd();
		customerEnd.setConnection(invoice);
		customerEnd.setDestination(customer);

		bicycleEnd.setConnection(invoice);
		bicycleEnd.setDestination(bicycleConfiguration);

		l.getContent().add(bicycleConfiguration);
		l.getContent().add(invoice);
		l.getContent().add(customer);

		Clabject customerInstance = PLMFactory.eINSTANCE.createEntity();
		customerInstance.setName("CustomerInstance");
		Clabject bikeInstance = PLMFactory.eINSTANCE.createEntity();
		bikeInstance.setName("bikeInstance");
		Connection invoiceInstance = PLMFactory.eINSTANCE.createConnection();
		invoiceInstance.setName("invoiceInstance");

		l1.getContent().add(customerInstance);
		l1.getContent().add(bikeInstance);
		l1.getContent().add(invoiceInstance);

		bikeInstance.getFeature().add(price);

		Classification bikeClassification = PLMFactory.eINSTANCE.createClassification();
		bikeClassification.setInstance(bikeInstance);
		bikeClassification.setType(bicycleConfiguration);
		l1.getContent().add(bikeClassification);

		Classification cutomerClassification = PLMFactory.eINSTANCE.createClassification();
		cutomerClassification.setInstance(customerInstance);
		cutomerClassification.setType(customer);
		l1.getContent().add(cutomerClassification);

		Classification invoiceClassification = PLMFactory.eINSTANCE.createClassification();
		invoiceClassification.setInstance(invoiceInstance);
		invoiceClassification.setType(invoice);
		l1.getContent().add(invoiceClassification);

		dm.getContent().add(l);
		dm.getContent().add(l1);

		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
				"Clabject -> select(c|c.isDeepInstanceOf(Invoice) or c.isDeepInstanceOf(Customer) or c.isDeepInstanceOf(BicycleConfiguration))"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(l1);
		Object returnValue = visitor.visit(tree);
		assertTrue(Arrays.asList(invoiceInstance, bikeInstance, customerInstance)
				.containsAll((Collection<?>) returnValue));
	}

	@Test
	public void filterOCL1() {
		Level l1 = PLMFactory.eINSTANCE.createLevel();

		Attribute price = PLMFactory.eINSTANCE.createAttribute();
		price.setDatatype("Real");
		price.setName("price");
		price.setValue("4500");

		ConnectionEnd customerEnd = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd bicycleEnd = PLMFactory.eINSTANCE.createConnectionEnd();
		customerEnd.setConnection(invoice);
		customerEnd.setDestination(customer);

		bicycleEnd.setConnection(invoice);
		bicycleEnd.setDestination(bicycleConfiguration);

		l.getContent().add(bicycleConfiguration);
		l.getContent().add(invoice);
		l.getContent().add(customer);

		Clabject customerInstance = PLMFactory.eINSTANCE.createEntity();
		customerInstance.setName("CustomerInstance");
		Clabject bikeInstance = PLMFactory.eINSTANCE.createEntity();
		bikeInstance.setName("bikeInstance");
		Connection invoiceInstance = PLMFactory.eINSTANCE.createConnection();
		invoiceInstance.setName("invoiceInstance");

		l1.getContent().add(customerInstance);
		l1.getContent().add(bikeInstance);
		l1.getContent().add(invoiceInstance);

		bikeInstance.getFeature().add(price);

		Classification bikeClassification = PLMFactory.eINSTANCE.createClassification();
		bikeClassification.setInstance(bikeInstance);
		bikeClassification.setType(bicycleConfiguration);
		l1.getContent().add(bikeClassification);

		Classification cutomerClassification = PLMFactory.eINSTANCE.createClassification();
		cutomerClassification.setInstance(customerInstance);
		cutomerClassification.setType(customer);
		l1.getContent().add(cutomerClassification);

		Classification invoiceClassification = PLMFactory.eINSTANCE.createClassification();
		invoiceClassification.setInstance(invoiceInstance);
		invoiceClassification.setType(invoice);
		l1.getContent().add(invoiceClassification);

		dm.getContent().add(l);
		dm.getContent().add(l1);

		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
				"Clabject -> select(c|c.isDeepInstanceOf(Invoice) or c.isDeepInstanceOf(Customer)) ->union (Clabject -> (select(c|c.isDeepInstanceOf(BicycleConfiguration))->select(b|b.price>4000)))"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(l1);
		Object returnValue = visitor.visit(tree);
		assertTrue(Arrays.asList(invoiceInstance, bikeInstance, customerInstance)
				.containsAll((Collection<?>) returnValue));
	}

}
