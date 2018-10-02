package org.melanee.ocl2.tests;

import static org.junit.Assert.assertEquals;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Clabject;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class MddTutorial {
  DeepModel dm;
  Level level0;
  Level level1;

  @Before
  public void setUp() {
    this.dm = PLMFactory.eINSTANCE.createDeepModel();
    this.level0 = PLMFactory.eINSTANCE.createLevel();
    this.level1 = PLMFactory.eINSTANCE.createLevel();
    this.dm.getContent().add(level0);
    this.dm.getContent().add(level1);
  }

  @After
  public void destroy() {
    this.dm = null;
    this.level0 = null;
    this.level1 = null;
  }

  @Test
  public void songUniqueTest() {
    Clabject sdCard = PLMFactory.eINSTANCE.createEntity();
    sdCard.setName("SdCard");
    Attribute size = PLMFactory.eINSTANCE.createAttribute();
    size.setName("size");
    size.setDatatype("Integer");
    sdCard.getFeature().add(size);
    Attribute averageSongLength = PLMFactory.eINSTANCE.createAttribute();
    averageSongLength.setDatatype("Real");
    averageSongLength.setName("averageSongLength");
    sdCard.getFeature().add(averageSongLength);
    Clabject Song1 = PLMFactory.eINSTANCE.createEntity();
    Song1.setName("Song1");
    Attribute title1 = PLMFactory.eINSTANCE.createAttribute();
    title1.setName("title");
    title1.setDatatype("String");
    title1.setValue("test");
    Song1.getFeature().add(title1);
    Clabject Song2 = PLMFactory.eINSTANCE.createEntity();
    Song2.setName("Song2");
    Attribute title2 = PLMFactory.eINSTANCE.createAttribute();
    title2.setName("title");
    title2.setDatatype("String");
    title2.setValue("test");
    Song2.getFeature().add(title2);

    // Connections
    Connection song1Connection = PLMFactory.eINSTANCE.createConnection();
    song1Connection.setName("song1Connection");
    ConnectionEnd song1End = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd SdCard1End = PLMFactory.eINSTANCE.createConnectionEnd();
    song1End.setDestination(Song1);
    song1End.setMoniker("song");
    song1End.setNavigable(true);
    SdCard1End.setDestination(sdCard);
    SdCard1End.setMoniker("sdCard");
    SdCard1End.setNavigable(true);
    SdCard1End.setConnection(song1Connection);
    song1End.setConnection(song1Connection);

    Connection song2Connection = PLMFactory.eINSTANCE.createConnection();
    song2Connection.setName("song2Connection");
    ConnectionEnd song2End = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd SdCard2End = PLMFactory.eINSTANCE.createConnectionEnd();
    song2End.setDestination(Song2);
    song2End.setMoniker("song");
    song2End.setNavigable(true);
    SdCard2End.setDestination(sdCard);
    SdCard2End.setMoniker("sdCard");
    SdCard2End.setNavigable(true);
    SdCard2End.setConnection(song2Connection);
    song2End.setConnection(song2Connection);

    level0.getContent().add(Song2);
    level0.getContent().add(Song1);
    level0.getContent().add(sdCard);

    level0.getContent().add(song1Connection);
    level0.getContent().add(song2Connection);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("song -> isUnique(title)"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(sdCard);
    Object returnValue = visitor.visit(tree);
    assertEquals(false, returnValue);
  }
}
