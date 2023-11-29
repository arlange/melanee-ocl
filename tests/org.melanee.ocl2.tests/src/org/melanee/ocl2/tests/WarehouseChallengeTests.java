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
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;
import org.melanee.ocl2.models.definition.constraint.BodyConstraint;
import org.melanee.ocl2.models.definition.constraint.ConstraintFactory;
import org.melanee.ocl2.models.definition.constraint.Text;
import org.melanee.ocl2.service.DeepOclRuleVisitor;

public class WarehouseChallengeTests {

  private DeepModel dm;
  private Level l1;
  private Level l2;

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
    this.l1 = null;
    this.l2 = null;
    this.dm = null;
  }

  @Test
  public void testBodyFinalPrice() {

    Clabject sellableProductType = PLMFactory.eINSTANCE.createEntity();
    sellableProductType.setName("SellableProductType");
    sellableProductType.setPotency(1);

    Attribute reducedPrice = PLMFactory.eINSTANCE.createAttribute();
    reducedPrice.setDatatype("Real");
    reducedPrice.setName("reducedPrice");
    reducedPrice.setDurability(1);
    reducedPrice.setMutability(1);

    Attribute standardSalesPrice = PLMFactory.eINSTANCE.createAttribute();
    standardSalesPrice.setDatatype("Real");
    standardSalesPrice.setName("standardSalesPrice");
    standardSalesPrice.setDurability(1);
    standardSalesPrice.setMutability(1);

    Method finalPrice = PLMFactory.eINSTANCE.createMethod();
    finalPrice.setName("finalPrice");
    finalPrice.setDurability(1);

    sellableProductType.getFeature().add(reducedPrice);
    sellableProductType.getFeature().add(standardSalesPrice);
    sellableProductType.getFeature().add(finalPrice);


    this.l1.getContent().add(sellableProductType);

    standardSalesPrice.setValue("9999");


    BodyConstraint constraint = ConstraintFactory.eINSTANCE.createBodyConstraint();
    constraint.setName("bodyConstraintTest");
    Text constraintText = ConstraintFactory.eINSTANCE.createText();
    constraintText.setText(
        "if self.reducedPrice.oclIsUndefined()\n" + "            then self.standardSalesPrice \n"
            + "            else self.reducedPrice \n" + "        endif");
    constraint.getExpression().add(constraintText);

    finalPrice.getConstraint().add(constraint);

    DeepOclLexer oclLexer = new DeepOclLexer(new ANTLRInputStream("self.finalPrice()"));
    DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
    ParseTree tree = parser.specificationCS();
    DeepOclRuleVisitor visitor = new DeepOclRuleVisitor(sellableProductType);
    Object returnValue = visitor.visit(tree);
    if (returnValue instanceof Attribute) {
      Attribute attr = (Attribute) returnValue;
      assertEquals(9999, Integer.parseInt((attr.getValue().toString())));
    }


  }

}
