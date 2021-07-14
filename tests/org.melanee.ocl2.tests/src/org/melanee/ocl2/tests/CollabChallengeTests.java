package org.melanee.ocl2.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Entity;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class CollabChallengeTests {
	DeepModel dm;
	Level level0;
	Level level1;
	Level level2;

	@Before
	public void initialize() {
		this.dm = PLMFactory.eINSTANCE.createDeepModel();
		this.level0 = PLMFactory.eINSTANCE.createLevel();
		this.level0.setName("O0");
		this.level1 = PLMFactory.eINSTANCE.createLevel();
		this.level1.setName("O1");
		this.level2 = PLMFactory.eINSTANCE.createLevel();
		this.level2.setName("O2");
		this.dm.getContent().add(this.level0);
		this.dm.getContent().add(this.level1);
		this.dm.getContent().add(this.level2);
	}

	@After
	public void tearDown() {
		this.dm = null;
		this.level0 = null;
		this.level1 = null;
		this.level2 = null;
	}

	@Test
	public void PowertTypeCheckTest() {
		Entity Manufacturer = PLMFactory.eINSTANCE.createEntity();
		Manufacturer.setName("Manufacturer");
		Manufacturer.setPotency(1);

		Entity ProductionFacility = PLMFactory.eINSTANCE.createEntity();
		ProductionFacility.setName("ProductionFacility");
		ProductionFacility.setPotency(1);

		Attribute manufacturerName = PLMFactory.eINSTANCE.createAttribute();
		manufacturerName.setDatatype("String");
		manufacturerName.setDurability(1);
		manufacturerName.setMutability(1);
		manufacturerName.setName("name");
		Manufacturer.getFeature().add(manufacturerName);

		Attribute productionName = PLMFactory.eINSTANCE.createAttribute();
		productionName.setDatatype("String");
		productionName.setDurability(1);
		productionName.setMutability(1);
		productionName.setName("name");
		ProductionFacility.getFeature().add(productionName);

		this.level0.getContent().add(ProductionFacility);
		this.level0.getContent().add(Manufacturer);

		Entity Factory = PLMFactory.eINSTANCE.createEntity();
		Factory.setPotency(1);
		Factory.setName("Factory");
		Attribute factoryName = PLMFactory.eINSTANCE.createAttribute();
		factoryName.setDatatype("String");
		factoryName.setDurability(1);
		factoryName.setMutability(1);
		factoryName.setName("name");
		Factory.getFeature().add(factoryName);

		Entity Company = PLMFactory.eINSTANCE.createEntity();
		Company.setPotency(1);
		Company.setName("Company");
		Attribute companyName = PLMFactory.eINSTANCE.createAttribute();
		companyName.setDatatype("String");
		companyName.setDurability(1);
		companyName.setMutability(1);
		companyName.setName("name");
		Company.getFeature().add(companyName);

		Entity HUW = PLMFactory.eINSTANCE.createEntity();
		HUW.setPotency(0);
		HUW.setName("HUW");
		Attribute huwName = PLMFactory.eINSTANCE.createAttribute();
		huwName.setDatatype("String");
		huwName.setDurability(0);
		huwName.setMutability(0);
		huwName.setName("name");
		huwName.setValue("Huawei");
		HUW.getFeature().add(huwName);

		Entity M124 = PLMFactory.eINSTANCE.createEntity();
		M124.setPotency(0);
		M124.setName("M124");
		Attribute m124Name = PLMFactory.eINSTANCE.createAttribute();
		m124Name.setDatatype("String");
		m124Name.setDurability(0);
		m124Name.setMutability(0);
		m124Name.setName("name");
		m124Name.setValue("Factory124");
		M124.getFeature().add(m124Name);

		Classification ManufacturerClassification = PLMFactory.eINSTANCE.createClassification();
		ManufacturerClassification.setInstance(HUW);
		ManufacturerClassification.setType(Manufacturer);

		Classification ProductionClassification = PLMFactory.eINSTANCE.createClassification();
		ProductionClassification.setInstance(M124);
		ProductionClassification.setType(ProductionFacility);

		Connection M124HUW = PLMFactory.eINSTANCE.createConnection();
		M124HUW.setPotency(0);
		ConnectionEnd M124End = PLMFactory.eINSTANCE.createConnectionEnd();
		M124End.setDestination(M124);
		M124End.setConnection(M124HUW);
		M124End.setMoniker("owns");
		ConnectionEnd HUWEnd = PLMFactory.eINSTANCE.createConnectionEnd();
		HUWEnd.setDestination(HUW);
		HUWEnd.setConnection(M124HUW);
		M124HUW.getConnectionEnd().add(HUWEnd);

		this.level1.getContent().add(HUW);
		this.level1.getContent().add(M124);
		this.level1.getContent().add(ManufacturerClassification);
		this.level1.getContent().add(ProductionClassification);
		this.level1.getContent().add(M124HUW);
		this.level1.getContent().add(Factory);
		this.level1.getContent().add(Company);

		Entity Factory124 = PLMFactory.eINSTANCE.createEntity();
		Factory124.setName("Factory124");
		Factory124.setPotency(0);
		Attribute factory124Name = PLMFactory.eINSTANCE.createAttribute();
		factory124Name.setDatatype("String");
		factory124Name.setDurability(0);
		factory124Name.setMutability(0);
		factory124Name.setName("name");
		factory124Name.setValue("Factory124");
		Factory124.getFeature().add(factory124Name);

		Entity Huawei = PLMFactory.eINSTANCE.createEntity();
		Huawei.setName("Huawei");
		Huawei.setPotency(0);
		Attribute huaweiName = PLMFactory.eINSTANCE.createAttribute();
		huaweiName.setDatatype("String");
		huaweiName.setDurability(0);
		huaweiName.setMutability(0);
		huaweiName.setName("name");
		huaweiName.setValue("Huawei");
		Huawei.getFeature().add(huaweiName);

		Classification HuaweiCompanyClass = PLMFactory.eINSTANCE.createClassification();
		HuaweiCompanyClass.setType(Company);
		HuaweiCompanyClass.setInstance(Huawei);

		Classification Factory124Class = PLMFactory.eINSTANCE.createClassification();
		Factory124Class.setType(Factory);
		Factory124Class.setInstance(Factory124);
		
		Connection HuaweiFactory = PLMFactory.eINSTANCE.createConnection();
		HuaweiFactory.setPotency(0);
		ConnectionEnd factoryEnd = PLMFactory.eINSTANCE.createConnectionEnd();
		factoryEnd.setDestination(Factory124);
		factoryEnd.setConnection(HuaweiFactory);
		factoryEnd.setMoniker("operates");
		ConnectionEnd huaweiEnd = PLMFactory.eINSTANCE.createConnectionEnd();
		huaweiEnd.setDestination(Huawei);
		huaweiEnd.setConnection(HuaweiFactory);
		huaweiEnd.setMoniker("company");
		
		this.level2.getContent().add(Factory124);
		this.level2.getContent().add(Huawei);
		this.level2.getContent().add(HuaweiCompanyClass);
		this.level2.getContent().add(Factory124Class);
		this.level2.getContent().add(HuaweiFactory);

		String constraint = "Clabject -> select(c|c.isDeepOclTypeOf(Manufacturer)) -> select(c|c.#getPotency()# = 0) -> forAll(c|let manufacturerName:String = c.name in c.owns -> collect(name) -> includesAll(Clabject -> select(c|c.isDeepOclTypeOf(Company)->select(c|c.#getPotency()# = 0).operates -> select(c|c.name = manufacturerName))))";
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(constraint));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.dm);
		Boolean returnValue = Boolean.valueOf(visitor.visit(tree).toString());
		assertTrue(returnValue);

	}
}
