package fr.charly.jUnitTutorial;


import static java.lang.Math.PI;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("ConversionTests")
@DisplayName("We should be able to convert between different units.")
public class ConversionCalculatorTest {

	private ConversionCalculator calculatorUnderTest = new ConversionCalculator();

	@Nested
	@Tag("TemperatureTests")
	@DisplayName("We should be able to convert between Fahrenheit and Celsius")
	class TemperatureTests {

		@Test
		@Tag("FahrenheitTests")
		@DisplayName("Given zero degrees in Celsius, when converted to Fahrenheit, then the result is 32")
		public void celsiusToFahrenheit_returnsAFahrenheitTemperature_whenCelsiusIsZero() {
			Double actualFahrenheit = calculatorUnderTest.celsiusToFahrenheit(0.0);
			assertThat(actualFahrenheit, is(equalTo(32.0)));
		}

		@Test
		@DisplayName("Given 32 degrees in Fahrenheit, when converted to Celsius, then the result is zero")
		public void fahrenheitToCelsius() {
			Double actualCelsius = calculatorUnderTest.fahrenheitToCelsius(32.0);
			assertThat(actualCelsius, is(equalTo(0.0)));
		}
	}

	@Test
	@DisplayName("Given a volume in litres, when converted to gallons, then the result is rounded up")
	public void litresToGallons_returnsOneGallon_whenConvertingTheEquivalentLitres() {
		Double actualLitres = calculatorUnderTest.litresToGallons(3.78541);
		assertThat(actualLitres, is(equalTo(1.0)));
	}

	@Test
	@DisplayName("Given a radius of one when converted then the result is Pi")
	public void radiusToAreaOfCircle() {
		Double actualArea = calculatorUnderTest.radiusToAreaOfCircle(1.0);
		assertThat(actualArea, is(equalTo(PI)));
	}
}
