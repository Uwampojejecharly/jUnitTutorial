package fr.charly.jUnitTutorial;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void add_shouldReturnTheSum_ofTwoNumbers() {
		// arrange
		Integer expected = 3; // 1+2
		Calculator calculator = new Calculator();

		// act
		Integer result = calculator.add(1, 2);

		// assert
		assertEquals(expected, result);
	}

	@Test
	public void multiply_shouldReturnTheSum_ofTwoNumbers() {
		// arrange
		Calculator calculator = new Calculator();

		// act
		Integer product = calculator.multiply(2, 3);

		// assert
		assertThat(product, is(equalTo(6))); // 2*3
	}
}
