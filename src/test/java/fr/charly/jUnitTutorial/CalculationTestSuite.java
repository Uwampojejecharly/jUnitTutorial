package fr.charly.jUnitTutorial;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CalculatorTest.class, ConversionCalculatorTest.class })
public class CalculationTestSuite {
}
