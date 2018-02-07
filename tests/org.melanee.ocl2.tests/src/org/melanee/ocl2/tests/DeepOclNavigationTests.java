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
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class DeepOclNavigationTests {

	DeepModel dm = PLMFactory.eINSTANCE.createDeepModel();
	Level l1 = PLMFactory.eINSTANCE.createLevel();
	Level l2 = PLMFactory.eINSTANCE.createLevel();
	Level l3 = PLMFactory.eINSTANCE.createLevel();
	Clabject Car = PLMFactory.eINSTANCE.createEntity();
	Clabject Wheel = PLMFactory.eINSTANCE.createEntity();
	Clabject QualityInfo = PLMFactory.eINSTANCE.createEntity();

	Clabject Dragster = PLMFactory.eINSTANCE.createEntity();
	Clabject SmallWheel = PLMFactory.eINSTANCE.createEntity();
	Clabject BroadWheel = PLMFactory.eINSTANCE.createEntity();
	Clabject SmallInfo = PLMFactory.eINSTANCE.createEntity();
	Clabject BroadInfo = PLMFactory.eINSTANCE.createEntity();
	Clabject ArnesCar = PLMFactory.eINSTANCE.createEntity();
	Clabject frontLeft = PLMFactory.eINSTANCE.createEntity();
	Clabject frontRight = PLMFactory.eINSTANCE.createEntity();
	Clabject rearLeft = PLMFactory.eINSTANCE.createEntity();
	Clabject rearRight = PLMFactory.eINSTANCE.createEntity();
	Clabject x = PLMFactory.eINSTANCE.createEntity();

	@Before
	public void setUp() throws Exception {

		Car.setName("Car");
		Wheel.setName("Wheel");
		QualityInfo.setName("QualityInfo");
		Dragster.setName("Dragster");
		SmallInfo.setName("SmallInfo");
		SmallWheel.setName("SmallWheel");
		BroadInfo.setName("BroadInfo");
		BroadWheel.setName("BroadWheel");
		ArnesCar.setName("ArneCar");
		frontLeft.setName("frontLeft");
		frontRight.setName("frontRight");
		rearLeft.setName("rearLeft");
		rearRight.setName("rearRight");
		x.setName("x");
		this.dm.getContent().add(l1);
		this.dm.getContent().add(l2);
		this.dm.getContent().add(l3);

		this.l1.getContent().add(Car);
		this.l1.getContent().add(Wheel);
		this.l1.getContent().add(QualityInfo);

		this.l2.getContent().add(Dragster);
		this.l2.getContent().add(SmallWheel);
		this.l2.getContent().add(BroadWheel);
		this.l2.getContent().add(SmallInfo);
		this.l2.getContent().add(BroadInfo);

		this.l3.getContent().add(ArnesCar);
		this.l3.getContent().add(frontLeft);
		this.l3.getContent().add(frontRight);
		this.l3.getContent().add(rearLeft);
		this.l3.getContent().add(rearRight);
		this.l3.getContent().add(x);

		// Car classification
		Classification classi1 = PLMFactory.eINSTANCE.createClassification();
		classi1.setInstance(Dragster);
		classi1.setType(Car);
		l2.getContent().add(classi1);
		// SmallWheel classification
		Classification classi2 = PLMFactory.eINSTANCE.createClassification();
		classi2.setInstance(SmallWheel);
		classi2.setType(Wheel);
		l2.getContent().add(classi2);
		// BroadWheel classification
		Classification classi3 = PLMFactory.eINSTANCE.createClassification();
		classi3.setInstance(BroadWheel);
		classi3.setType(Wheel);
		l2.getContent().add(classi3);

		// SmallInfo classification
		Classification classi4 = PLMFactory.eINSTANCE.createClassification();
		classi4.setInstance(SmallInfo);
		classi4.setType(QualityInfo);
		l2.getContent().add(classi4);

		// BroadInfo classification
		Classification classi5 = PLMFactory.eINSTANCE.createClassification();
		classi5.setInstance(BroadInfo);
		classi5.setType(QualityInfo);
		l2.getContent().add(classi5);

		// ArnesCar classification
		Classification classi6 = PLMFactory.eINSTANCE.createClassification();
		classi6.setInstance(ArnesCar);
		classi6.setType(Dragster);
		l3.getContent().add(classi6);
		// frontRight classification
		Classification classi7 = PLMFactory.eINSTANCE.createClassification();
		classi7.setInstance(frontRight);
		classi7.setType(SmallWheel);
		l3.getContent().add(classi7);
		// frontLeft classification
		Classification classi8 = PLMFactory.eINSTANCE.createClassification();
		classi8.setInstance(frontLeft);
		classi8.setType(SmallWheel);
		l3.getContent().add(classi8);

		// rearLeft classification
		Classification classi9 = PLMFactory.eINSTANCE.createClassification();
		classi9.setInstance(rearLeft);
		classi9.setType(BroadWheel);
		l3.getContent().add(classi9);

		// rearRight classification
		Classification classi10 = PLMFactory.eINSTANCE.createClassification();
		classi10.setInstance(rearRight);
		classi10.setType(BroadWheel);
		l3.getContent().add(classi10);

		// x classification
		Classification classi11 = PLMFactory.eINSTANCE.createClassification();
		classi11.setInstance(x);
		classi11.setType(SmallInfo);
		l3.getContent().add(classi11);

		// Connections
		// Connection setUp car -> Wheel
		Connection connect = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti1 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti1.setMoniker("wheel");
		parti.setConnection(connect);
		parti.setDestination(Car);
		parti1.setDestination(Wheel);
		parti1.setNavigable(true);
		parti1.setConnection(connect);
		parti1.setLower(4);
		parti1.setUpper(4);
		parti.setLower(1);
		parti.setUpper(1);
		// Connection setUp Wheel -> QualityInfo
		Connection connect1 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti2 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti3 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti3.setMoniker("quality");
		parti2.setConnection(connect1);
		parti2.setDestination(Wheel);
		parti3.setDestination(QualityInfo);
		parti3.setNavigable(true);
		parti3.setConnection(connect1);
		parti3.setLower(1);
		parti3.setUpper(1);
		parti2.setLower(1);
		parti2.setUpper(1);

		// Connection setUp Wheel -> QualityInfo
		Connection connect2 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti4 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti5 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti5.setMoniker("quality");
		parti4.setConnection(connect2);
		parti4.setDestination(Wheel);
		parti5.setDestination(QualityInfo);
		parti5.setNavigable(true);
		parti5.setConnection(connect2);
		parti5.setLower(1);
		parti5.setUpper(1);
		parti4.setLower(1);
		parti4.setUpper(1);

		l1.getContent().add(connect);
		l1.getContent().add(connect1);
		l1.getContent().add(connect2);

		// Connection setUp Dragster -> SmallWheel
		Connection connect3 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti6 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti7 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti7.setMoniker("front");
		parti6.setConnection(connect3);
		parti6.setDestination(Dragster);
		parti7.setDestination(SmallWheel);
		parti7.setNavigable(true);
		parti7.setConnection(connect3);
		parti7.setLower(2);
		parti7.setUpper(2);

		// Connection setUp Dragster -> BroadWheel
		Connection connect4 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti8 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti9 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti9.setMoniker("rear");
		parti8.setConnection(connect4);
		parti8.setDestination(Dragster);
		parti9.setDestination(BroadWheel);
		parti9.setNavigable(true);
		parti9.setConnection(connect4);
		parti9.setLower(2);
		parti9.setUpper(2);

		// Connection setUp SmallWheel -> SmallInfo
		Connection connect5 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti10 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti11 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti11.setMoniker("smallInfo");
		parti10.setConnection(connect5);
		parti10.setDestination(SmallWheel);
		parti11.setDestination(SmallInfo);
		parti11.setNavigable(true);
		parti11.setConnection(connect5);
		parti11.setLower(1);
		parti11.setUpper(1);
		parti10.setUpper(1);
		parti10.setLower(1);

		// Connection setUp BroadWheel -> BroadInfo
		Connection connect6 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti12 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti13 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti13.setMoniker("broadInfo");
		parti12.setConnection(connect6);
		parti12.setDestination(BroadWheel);
		parti13.setDestination(BroadInfo);
		parti13.setNavigable(true);
		parti13.setConnection(connect6);
		parti13.setLower(1);
		parti13.setUpper(1);
		parti12.setUpper(1);
		parti12.setLower(1);

		l2.getContent().add(connect3);
		l2.getContent().add(connect4);
		l2.getContent().add(connect5);
		l2.getContent().add(connect6);

		// Level 3 Connections
		// Connection setUp ArnesCar -> frontLeft
		Connection connect7 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti14 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti15 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti15.setMoniker("frontLeft");
		parti14.setConnection(connect7);
		parti14.setDestination(ArnesCar);
		parti15.setDestination(frontLeft);
		parti15.setNavigable(true);
		parti15.setConnection(connect7);
		parti15.setLower(1);
		parti15.setUpper(1);
		parti14.setUpper(1);
		parti14.setLower(1);

		// Connection setUp ArnesCar -> frontRight
		Connection connect8 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti16 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti17 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti17.setMoniker("frontRight");
		parti16.setConnection(connect8);
		parti16.setDestination(ArnesCar);
		parti17.setDestination(frontRight);
		parti17.setNavigable(true);
		parti17.setConnection(connect8);
		parti17.setLower(1);
		parti17.setUpper(1);
		parti16.setUpper(1);
		parti16.setLower(1);

		// Connection setUp ArnesCar -> rearRight
		Connection connect9 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti18 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti19 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti19.setMoniker("rearRight");
		parti18.setConnection(connect9);
		parti18.setDestination(ArnesCar);
		parti19.setDestination(rearRight);
		parti19.setNavigable(true);
		parti19.setConnection(connect9);
		parti19.setLower(1);
		parti19.setUpper(1);
		parti18.setUpper(1);
		parti18.setLower(1);

		// Connection setUp ArnesCar -> rearLeft
		Connection connect10 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti20 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti21 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti21.setMoniker("rearLeft");
		parti20.setConnection(connect10);
		parti20.setDestination(ArnesCar);
		parti21.setDestination(rearLeft);
		parti21.setNavigable(true);
		parti21.setConnection(connect10);
		parti21.setLower(1);
		parti21.setUpper(1);
		parti20.setUpper(1);
		parti20.setLower(1);

		// Connection setUp frontRight -> x
		Connection connect11 = PLMFactory.eINSTANCE.createConnection();
		ConnectionEnd parti22 = PLMFactory.eINSTANCE.createConnectionEnd();
		ConnectionEnd parti23 = PLMFactory.eINSTANCE.createConnectionEnd();
		parti23.setMoniker("x");
		parti22.setConnection(connect11);
		parti22.setDestination(frontRight);
		parti23.setDestination(x);
		parti23.setNavigable(true);
		parti23.setConnection(connect11);
		parti23.setLower(1);
		parti23.setUpper(1);
		parti22.setUpper(1);
		parti22.setLower(1);

		l3.getContent().add(connect7);
		l3.getContent().add(connect8);
		l3.getContent().add(connect9);
		l3.getContent().add(connect10);
		l3.getContent().add(connect11);

	}

	@After
	public void tearDown() throws Exception {
		dm = null;
	}

	@Test
	public void firstTest() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context ArnesCar \ninv test: self.$Car$.wheel"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(ArnesCar);
		long startTime = System.currentTimeMillis();
		Object returnValue = visitor.visit(tree);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + " ms");
		assertEquals(Arrays.asList(frontLeft, frontRight, rearLeft, rearRight), returnValue);
	}
	
	@Test
	public void secondTest() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context ArnesCar \ninv test: self.$Dragster$.front"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(ArnesCar);
		Object returnValue = visitor.visit(tree);
		assertEquals(Arrays.asList(frontLeft, frontRight), returnValue);
	}
	@Test
	public void thirdTest() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context ArnesCar \ninv test: self.frontLeft"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(ArnesCar);
		Object returnValue = visitor.visit(tree);
		assertEquals(frontLeft, returnValue);
	}
	
	@Test
	public void fourthTest() {
		DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("context Dragster \ninv test: self.$Car$.wheel"));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParseTree tree = parser.contextDeclCS();
		DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(Dragster);
		long startTime = System.currentTimeMillis();
		Object returnValue = visitor.visit(tree);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + " ms");
		assertEquals(Arrays.asList(SmallWheel, SmallWheel, BroadWheel, BroadWheel), returnValue);
	}

}
