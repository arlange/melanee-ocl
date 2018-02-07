package org.melanee.ocl2.tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.melanee.core.models.plm.PLM.Element;
import org.melanee.core.models.plm.PLM.Feature;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOCLClabjectWrapperImpl;
import org.melanee.ocl2.service.DeepOclRuleVisitor;
import org.melanee.ocl2.service.util.OclInvalid;

public class ClosureOperationTest {

	Clabject Person;
	Clabject Abe;
	Clabject Mona;
	Clabject Homer;
	Clabject Bart;
	Clabject Maggie;
	Clabject Lisa;
	Clabject Marge;
	DeepModel dm;
	Level O1;
	Level O2;

	@Before
	public void setUp() {
		this.Person = PLMFactory.eINSTANCE.createEntity();
		this.Person.setName("Person");
		this.Abe = PLMFactory.eINSTANCE.createEntity();
		this.Abe.setName("Abe Simpson");
		this.Mona = PLMFactory.eINSTANCE.createEntity();
		this.Mona.setName("Mona Simpson");
		this.Homer = PLMFactory.eINSTANCE.createEntity();
		this.Homer.setName("Homer Simpson");
		this.Marge = PLMFactory.eINSTANCE.createEntity();
		this.Marge.setName("Marge Simpson");
		this.Bart = PLMFactory.eINSTANCE.createEntity();
		this.Bart.setName("Bart Simpson");
		this.Lisa = PLMFactory.eINSTANCE.createEntity();
		this.Lisa.setName("Lisa Simpson");
		this.Maggie = PLMFactory.eINSTANCE.createEntity();
		this.Maggie.setName("Maggie Simpson");
		this.dm = PLMFactory.eINSTANCE.createDeepModel();
		this.O1 = PLMFactory.eINSTANCE.createLevel();
		this.O2 = PLMFactory.eINSTANCE.createLevel();
		this.dm.getContent().add(O1);
		this.dm.getContent().add(O2);
		this.O1.getContent().add(Person);

		Connection parentsChild = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parents = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd child = PLMFactory.eINSTANCE.createConnectionEnd();
		parents.setMoniker("parent");
		child.setMoniker("child");

		parents.setConnection(parentsChild);
		child.setConnection(parentsChild);
		parents.setDestination(Person);
		child.setDestination(Person);
		parents.setNavigable(true);
		child.setNavigable(true);

		this.O1.getContent().add(parentsChild);

		// second level
		this.O2.getContent().add(Abe);
		this.O2.getContent().add(Homer);
		this.O2.getContent().add(Mona);
		this.O2.getContent().add(Bart);
		this.O2.getContent().add(Marge);
		this.O2.getContent().add(Maggie);
		this.O2.getContent().add(Lisa);

		Classification AbePerson = PLMFactory.eINSTANCE.createClassification();
		AbePerson.setInstance(Abe);
		AbePerson.setType(Person);
		O2.getContent().add(AbePerson);

		Classification MonaPerson = PLMFactory.eINSTANCE.createClassification();
		MonaPerson.setInstance(Mona);
		MonaPerson.setType(Person);
		O2.getContent().add(MonaPerson);

		Classification HomerPerson = PLMFactory.eINSTANCE.createClassification();
		HomerPerson.setInstance(Homer);
		HomerPerson.setType(Person);
		O2.getContent().add(HomerPerson);

		Classification MargePerson = PLMFactory.eINSTANCE.createClassification();
		MargePerson.setInstance(Marge);
		MargePerson.setType(Person);
		O2.getContent().add(MargePerson);

		Classification BartPerson = PLMFactory.eINSTANCE.createClassification();
		BartPerson.setInstance(Bart);
		BartPerson.setType(Person);
		O2.getContent().add(BartPerson);

		Classification LisaPerson = PLMFactory.eINSTANCE.createClassification();
		LisaPerson.setInstance(Lisa);
		LisaPerson.setType(Person);
		O2.getContent().add(LisaPerson);

		Classification MaggiePerson = PLMFactory.eINSTANCE.createClassification();
		MaggiePerson.setInstance(Maggie);
		MaggiePerson.setType(Person);
		O2.getContent().add(MaggiePerson);

		// Abe -> Homer
		Connection abeHomer = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd abe = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd homer = PLMFactory.eINSTANCE.createConnectionEnd();
		abe.setMoniker("parent");
		homer.setMoniker("child");

		abe.setConnection(abeHomer);
		homer.setConnection(abeHomer);
		abe.setDestination(Abe);
		homer.setDestination(Homer);
		homer.setNavigable(true);
		abe.setNavigable(true);

		this.O2.getContent().add(abeHomer);

		// Mona -> Homer
		Connection monaHomer = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd mona = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd homer1 = PLMFactory.eINSTANCE.createConnectionEnd();
		mona.setMoniker("parent");
		homer1.setMoniker("child");

		mona.setConnection(monaHomer);
		homer1.setConnection(monaHomer);
		mona.setDestination(Mona);
		homer1.setDestination(Homer);
		homer1.setNavigable(true);
		mona.setNavigable(true);

		this.O2.getContent().add(monaHomer);

		// Homer -> Bart
		Connection homerBart = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd homer2 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd bart = PLMFactory.eINSTANCE.createConnectionEnd();
		homer2.setMoniker("parent");
		bart.setMoniker("child");

		homer2.setConnection(homerBart);
		bart.setConnection(homerBart);
		homer2.setDestination(Homer);
		bart.setDestination(Bart);
		homer2.setNavigable(true);
		bart.setNavigable(true);

		this.O2.getContent().add(homerBart);

		// Marge -> Bart
		Connection margeBart = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd marge = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd bart1 = PLMFactory.eINSTANCE.createConnectionEnd();
		marge.setMoniker("parent");
		bart1.setMoniker("child");

		marge.setConnection(margeBart);
		bart1.setConnection(margeBart);
		marge.setDestination(Marge);
		bart1.setDestination(Bart);
		marge.setNavigable(true);
		bart1.setNavigable(true);

		this.O2.getContent().add(margeBart);

		// Homer -> Lisa
		Connection homerLisa = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd homer3 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd lisa = PLMFactory.eINSTANCE.createConnectionEnd();
		homer3.setMoniker("parent");
		lisa.setMoniker("child");

		homer3.setConnection(homerLisa);
		lisa.setConnection(homerLisa);
		homer3.setDestination(Homer);
		lisa.setDestination(Lisa);
		homer3.setNavigable(true);
		lisa.setNavigable(true);

		this.O2.getContent().add(homerLisa);

		// Homer -> MAggie
		Connection homerMaggie = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd homer4 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd maggie = PLMFactory.eINSTANCE.createConnectionEnd();
		homer4.setMoniker("parent");
		maggie.setMoniker("child");

		homer4.setConnection(homerMaggie);
		maggie.setConnection(homerMaggie);
		homer4.setDestination(Homer);
		maggie.setDestination(Maggie);
		homer4.setNavigable(true);
		maggie.setNavigable(true);

		this.O2.getContent().add(homerMaggie);
	}

	@After
	public void destroy() {

	}

	@Test
	public void closureOperationParentsOfBartTest() {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream("context Bart inv parents: self -> closure(parent) -> size() = 5"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Bart);
		long startTime = System.currentTimeMillis();
		Object returnValue = visitor.visit(tree);
		long endTime = System.currentTimeMillis();
		System.out.println("self -> closure(parent) -> size() = 5 --> " + (endTime - startTime) + " ms");
		assertEquals("true", returnValue.toString());
	}

	@Test
	public void closureOperationParentsOfBartTest1() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self -> closure(parent)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Bart);
		Object returnValue = visitor.visit(tree);
		assertTrue(returnValue instanceof Collection);
		List<Element> family = new ArrayList<Element>();
		family.add(Bart);
		family.add(Homer);
		family.add(Marge);
		family.add(Abe);
		family.add(Mona);
		// assert that the collections have all the same entries (order is not
		// important)
		assertTrue(family.containsAll((Collection<?>) returnValue));
	}

	@Test
	public void closureOperationChildrenOfAbeTest() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self -> closure(child)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Abe);
		Object returnValue = visitor.visit(tree);
		assertTrue(returnValue instanceof Collection);
		List<Element> family = new ArrayList<Element>();
		family.add(Bart);
		family.add(Homer);
		family.add(Abe);
		family.add(Lisa);
		family.add(Maggie);
		assertTrue(family.containsAll((Collection<?>) returnValue));
	}

	@Test
	public void closureOperationChildrenOfAbeTest1() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self -> closure(p|p.child)"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.specificationCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(this.Abe);
		long startTime = System.currentTimeMillis();
		Object returnValue = visitor.visit(tree);
		long endTime = System.currentTimeMillis();
		System.out.println("self -> closure(p|p.child) --> " + (endTime - startTime) + " ms");
		assertTrue(returnValue instanceof Collection);
		List<Element> family = new ArrayList<Element>();
		family.add(Bart);
		family.add(Homer);
		family.add(Abe);
		family.add(Lisa);
		family.add(Maggie);
		assertTrue(family.containsAll((Collection<?>) returnValue));
	}

}
