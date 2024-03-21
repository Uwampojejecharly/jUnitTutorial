package fr.charly.jUnitTutorial;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CalculatorTest.class, ConversionCalculatorTest.class })
public class CalculationTestSuite {
}
