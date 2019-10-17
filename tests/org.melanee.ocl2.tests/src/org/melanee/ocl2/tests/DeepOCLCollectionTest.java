/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Ralph Gerbig - initial API and implementation and initial documentation Arne Lange
 * - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.tests;

import static org.junit.Assert.assertEquals;
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

/**
 * This test class checks for the collection capabilities
 * 
 * @author Arne Lange
 *
 */
public class DeepOCLCollectionTest {

  DeepModel dm;
  Level l1;
  Level l2;

  @Before
  public void setUp() {
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.l1 = PLMFactory.eINSTANCE.createLevel();
    this.l2 = PLMFactory.eINSTANCE.createLevel();
    dm.getContent().add(l1);
    dm.getContent().add(l2);
  }


  @After
  public void tearDown() {
    this.dm = null;
    this.l1 = null;
    this.l2 = null;
  }

  @Test
  public void setIncludesOperationTest() {
    Clabject flight = PLMFactory.eINSTANCE.createEntity();
    flight.setName("Flight1");
    Clabject airportAmsterdam = PLMFactory.eINSTANCE.createEntity();
    airportAmsterdam.setName("Amsterdam");
    Attribute nameAmsterdam = PLMFactory.eINSTANCE.createAttribute();
    nameAmsterdam.setName("name");
    nameAmsterdam.setValue("Amsterdam");
    airportAmsterdam.getFeature().add(nameAmsterdam);
    Clabject airportEindhoven = PLMFactory.eINSTANCE.createEntity();
    airportEindhoven.setName("Eindhoven");
    Attribute nameEindhoven = PLMFactory.eINSTANCE.createAttribute();
    nameEindhoven.setName("name");
    nameEindhoven.setValue("Eindhoven");
    airportEindhoven.getFeature().add(nameEindhoven);
    Clabject klmAirline = PLMFactory.eINSTANCE.createEntity();
    klmAirline.setName("KLM");
    Attribute klmName = PLMFactory.eINSTANCE.createAttribute();
    klmName.setName("name");
    klmName.setValue("KLM");
    klmName.setDatatype("String");
    klmAirline.getFeature().add(klmName);

    Connection originConnection = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd origin = PLMFactory.eINSTANCE.createConnectionEnd();
    origin.setConnection(originConnection);
    origin.setMoniker("origin");
    origin.setDestination(airportAmsterdam);
    ConnectionEnd flight1 = PLMFactory.eINSTANCE.createConnectionEnd();
    flight1.setConnection(originConnection);
    flight1.setDestination(flight);

    Connection destinationConnection = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd destination = PLMFactory.eINSTANCE.createConnectionEnd();
    destination.setConnection(destinationConnection);
    destination.setMoniker("destination");
    destination.setDestination(airportEindhoven);
    ConnectionEnd flight2 = PLMFactory.eINSTANCE.createConnectionEnd();
    flight2.setConnection(destinationConnection);
    flight2.setDestination(flight);

    Connection flightAirline = PLMFactory.eINSTANCE.createConnection();
    ConnectionEnd flights = PLMFactory.eINSTANCE.createConnectionEnd();
    flights.setConnection(flightAirline);
    flights.setDestination(flight);
    ConnectionEnd airline = PLMFactory.eINSTANCE.createConnectionEnd();
    airline.setConnection(flightAirline);
    airline.setMoniker("airline");
    airline.setDestination(klmAirline);

    l1.getContent().add(flightAirline);
    l1.getContent().add(flight);
    l1.getContent().add(destinationConnection);
    l1.getContent().add(originConnection);
    l1.getContent().add(airportAmsterdam);
    l1.getContent().add(airportEindhoven);
    l1.getContent().add(klmAirline);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream(
        "Set{\"Amsterdam\",\"Den Haag\",\"Eindhoven\"}->includes(self.origin.name) and Set{\"Amsterdam\",\"Den Haag\",\"Eindhoven\"}->includes(self.destination.name) implies self.airline.name = \"KLM\""));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(flight);
    Object returnValue = visitor.visit(tree);
    assertEquals(true, returnValue);

  }


}
