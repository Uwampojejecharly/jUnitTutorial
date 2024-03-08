package fr.charly.jUnitTutorial;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Instant;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class CalculatorTest {

	private static Instant startTime;
	private Calculator calculator;

	@BeforeClass
	public static void beforeClass() {
		startTime = Instant.now();
		System.out.println("CALLED ONLY ONCE BEFORE ALL TESTS AT " + startTime);
	}

	@AfterClass
	public static void afterClass() {
		Instant endedAt = Instant.now();
		Duration duration = Duration.between(startTime, endedAt);
		System.out.println("CALLED AFTER ALL TESTS - ONLY ONCE - AT " + duration);
	}

	@Before
	public void setup() {
		System.out.println("CALLED BEFORE A TEST @Before");
		calculator = new Calculator();
	}

	@After
	public void tearDown() {
		System.out.println("CALLED AFTER A TEST @After");
		calculator = null;
	}

	@Test
	public void add_shouldReturnTheSum_ofTwoNumbers() {
		// arrange
		Integer expected = 3; // 1+2

		// act
		Integer result = calculator.add(1, 2);

		// assert
		assertEquals(expected, result);
	}

	@Test
	public void multiply_shouldReturnTheProduct_ofTwoNumbers() {
		// arrange
		Integer expected = 6;

		// act
		Integer product = calculator.multiply(2, 3);

		// assert
		assertThat(product, is(equalTo(6))); // 2*3
	}

	@Test(expected = UnsupportedOperationException.class)
	public void cos_shouldNotBeSupported_WhenCalledWithAnyValue() {
		// arrange

		// act
		calculator.cos(0, 8);

		// assert
	}

	@Test(timeout = 20001)
	public void slowCalculation_shouldTakeUnreasonablyLong_WhenCalled() {
		// arrange

		// Act by calling a slow calculation
		calculator.slowCalculation();

		// assert
	}
}
