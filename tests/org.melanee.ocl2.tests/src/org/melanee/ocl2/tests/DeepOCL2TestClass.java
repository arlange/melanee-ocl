package org.melanee.ocl2.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DeepOclClabjectWrapperTest.class, DeepOclNavigationTests.class,
    DeepOCLTreeVisitorTest.class, RoyalLoyalTest.class, SavingConstraintsTests.class,
    DeepOCL2ClassificationTests.class, ClosureOperationTest.class, DeepClassificationChecking.class,
    DeepReflectiveConstraints.class, BPMN2Tests.class })
public class DeepOCL2TestClass {

}
