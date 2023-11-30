package org.melanee.ocl2.tests;

import static org.junit.Assert.assertEquals;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

public class Ocl2StringTests {
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
  }
  
  @Test
  public void InvoicePriceYearTests() {
    Clabject product = PLMFactory.eINSTANCE.createEntity();
    product.setName("Product");
    Clabject customer = PLMFactory.eINSTANCE.createEntity();
    customer.setName("Customer");

    level0.getContent().add(customer);
    level0.getContent().add(product);
    customer.setPotency(1);
    product.setPotency(1);

    Clabject susanStorm = PLMFactory.eINSTANCE.createEntity();
    susanStorm.setName("SusanStorm");
    Clabject bike = PLMFactory.eINSTANCE.createEntity();
    susanStorm.setPotency(0);
    bike.setPotency(0);

    Clabject susanStorm1 = PLMFactory.eINSTANCE.createEntity();
    susanStorm1.setName("SusanStorm222");
    Clabject bike1 = PLMFactory.eINSTANCE.createEntity();
    susanStorm1.setPotency(0);
    bike1.setPotency(0);

    level1.getContent().add(susanStorm1);
    level1.getContent().add(bike1);
    level1.getContent().add(susanStorm);
    level1.getContent().add(bike);

    Classification customerSusan = PLMFactory.eINSTANCE.createClassification();
    customerSusan.setInstance(susanStorm);
    customerSusan.setType(customer);
    this.level1.getContent().add(customerSusan);

    Classification productBike = PLMFactory.eINSTANCE.createClassification();
    productBike.setInstance(bike);
    productBike.setType(product);
    this.level1.getContent().add(productBike);

    Classification customerSusan1 = PLMFactory.eINSTANCE.createClassification();
    customerSusan1.setInstance(susanStorm1);
    customerSusan1.setType(customer);
    this.level1.getContent().add(customerSusan1);

    Classification productBike1 = PLMFactory.eINSTANCE.createClassification();
    productBike1.setInstance(bike1);
    productBike1.setType(product);
    this.level1.getContent().add(productBike1);

    Connection invoice = PLMFactory.eINSTANCE.createConnection();
    invoice.setName("Invoice");
    ConnectionEnd productEnd = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd customerEnd = PLMFactory.eINSTANCE.createConnectionEnd();
    productEnd.setDestination(bike);
    customerEnd.setDestination(susanStorm);
    productEnd.setMoniker("product");
    customerEnd.setMoniker("customer");
    productEnd.setNavigable(true);
    customerEnd.setNavigable(true);
    productEnd.setConnection(invoice);
    customerEnd.setConnection(invoice);

    Connection invoice1 = PLMFactory.eINSTANCE.createConnection();
    invoice1.setName("Invoice");
    ConnectionEnd productEnd1 = PLMFactory.eINSTANCE.createConnectionEnd();
    ConnectionEnd customerEnd1 = PLMFactory.eINSTANCE.createConnectionEnd();
    productEnd1.setDestination(bike1);
    customerEnd1.setDestination(susanStorm1);
    productEnd1.setMoniker("product");
    customerEnd1.setMoniker("customer");
    productEnd1.setNavigable(true);
    customerEnd1.setNavigable(true);
    productEnd1.setConnection(invoice1);
    customerEnd1.setConnection(invoice1);

    Attribute price = PLMFactory.eINSTANCE.createAttribute();
    Attribute date = PLMFactory.eINSTANCE.createAttribute();

    Attribute price1 = PLMFactory.eINSTANCE.createAttribute();
    Attribute date1 = PLMFactory.eINSTANCE.createAttribute();

    price.setName("price");
    date.setName("date");
    price1.setName("price");
    date1.setName("date");

    price.setDatatype("Real");
    date.setDatatype("String");
    price1.setDatatype("Real");
    date1.setDatatype("String");

    price.setValue("433.99");
    date.setValue("12.05.2017");

    price1.setValue("432222.99");
    date1.setValue("12.05.2018");

    invoice.getFeature().add(date);
    invoice.getFeature().add(price);

    invoice1.getFeature().add(date1);
    invoice1.getFeature().add(price1);

    level1.getContent().add(invoice);
    level1.getContent().add(invoice1);

    DeepOclLexer oclLexer = new DeepOclLexer(
        new ANTLRInputStream("self.allInstances() -> select(c|c.#getPotency()# = 0) -> select(c|c.Invoice.date.substring(7,10) = \"2017\") "
            + "-> collectNested(Invoice.price) -> sum() / self.allInstances() -> select(c|c.#getPotency()# = 0) -> select(c|c.Invoice.date.substring(7,10) = \"2017\") -> size()"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(product);
    Object returnValue = visitor.visit(tree);
    assertEquals(433.99, returnValue);
  }
}
