package fr.charly.jUnitTutorial;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {
	private CalculatorService underTest;

	@Mock
	private Calculator calculator;

	@Mock
	private SolutionFormatter formatter;

	@Before
	public void setUp() {
		underTest = new CalculatorService(calculator, formatter);
	}

	@Test
    public void calculate_usesCalculatorToAdd_whenGivenAnAdditionCalculation() {
        // Arrange
        when(calculator.add(4,2)).thenReturn(6);
        CalculationModel calculationModel = new CalculationModel(CalculationType.ADDITION, 4, 2);
        // Act
        CalculationModel responseModel = underTest.calculate(calculationModel);

        // Assert
        verify(calculator, times(1)).add(4,2);
    }

	@Test
    public void calculate_usesCalculatorToSubtract_whenGivenASubtractionCalculation() {
        // Arrange
        when(calculator.subtract(4,2)).thenReturn(2);
        CalculationModel calculationModel = new CalculationModel(
                CalculationType.SUBTRACTION, 4, 2);
        // Act
        CalculationModel responseModel = underTest.calculate(calculationModel);

        // Assert
        verify(calculator, times(1)).subtract(4,2);
    }

	@Test
    public void calculate_usesCalculatorToMultiply_whenGivenAMultiplicationCalculation() {
        // Arrange
        when(calculator.multiply(4,2)).thenReturn(8);
        CalculationModel calculationModel = new CalculationModel(
                CalculationType.MULTIPLICATION, 4, 2);
        // Act
        CalculationModel responseModel = underTest.calculate(calculationModel);

        // Assert
        verify(calculator, times(1)).multiply(4,2);
    }

	@Test
    public void calculate_usesCalculatorToDivide_whenGivenADivisionCalculation() {
        // Arrange
        when(calculator.divide(4,2)).thenReturn(2);
        CalculationModel calculationModel = new CalculationModel(
                CalculationType.DIVISION, 4, 2);
        // Act
        CalculationModel responseModel = underTest.calculate(calculationModel);

        // Assert
        verify(calculator, times(1)).divide(4,2);
    }

	@Test
    public void calculate_returnsAModelWithASolution_whenGivenADivisionCalculation() {
        // Arrange
        when(calculator.divide(4,2)).thenReturn(2);
        CalculationModel calculationModel = new CalculationModel(
                CalculationType.DIVISION, 4, 2);
        // Act
        CalculationModel responseModel = underTest.calculate(calculationModel);

        // Assert
        assertThat(responseModel.getSolution(), is(equalTo(2)));
    }

	@Test
    public void calculate_returnsAModelWithALeftArgument_whenGivenADivisionCalculation() {
        // Arrange
        when(calculator.divide(4,2)).thenReturn(2);
        CalculationModel calculationModel = new CalculationModel(
                CalculationType.DIVISION, 4, 2);
        // Act
        CalculationModel responseModel = underTest.calculate(calculationModel);

        // Assert
        assertThat(responseModel.getLeftArgument(), is(equalTo(4)));
    }

	@Test
    public void calculate_returnsAModelWithARightArgument_whenGivenADivisionCalculation() {
        // Arrange
        when(calculator.divide(4,2)).thenReturn(2);
        CalculationModel calculationModel = new CalculationModel(
                CalculationType.DIVISION, 4, 2);
        // Act
        CalculationModel responseModel = underTest.calculate(calculationModel);

        // Assert
        assertThat(responseModel.getSolution(), is(equalTo(2)));
    }

	@Test
	@Ignore("TODO - Remove: This just retests calculator")
	public void calculate_returnsTheQuotient_whenDividingTwoPositiveNumbers() {
		// Arrange
		CalculationModel calculation = new CalculationModel(CalculationType.DIVISION, 4, 2);

		// Act
		CalculationModel response = underTest.calculate(calculation);

		// Assert
		assertThat(response.getSolution(), is(equalTo(2)));
	}

	@Test
	@Ignore("TODO - Remove: This just retests calculator")
	public void calculate_returnsTheQuotient_whenDividingTwoNegativeNumbers() {
		// Arrange
		CalculationModel calculation = new CalculationModel(CalculationType.DIVISION, -2, -2);

		// Act
		CalculationModel response = underTest.calculate(calculation);

		// Assert
		assertThat(response.getSolution(), is(equalTo(1)));
	}

	@Test(expected = IllegalArgumentException.class)
    public void calculate_shouldThrowAnException_whenDividingByZero() {
        // Arrange
        when(calculator.divide(1, 0)).thenThrow(new IllegalArgumentException(("Division by zero")));
        CalculationModel calculation = new CalculationModel(
                CalculationType.DIVISION, 1, 0);

        // Act
        CalculationModel response = underTest.calculate(calculation);
    }

	@Test
	public void calculate_shouldReturnNull_ifGivenANullModel() {
		// Act
		CalculationModel calculation = null;
		CalculationModel response = underTest.calculate(calculation);
		// assert
		assertThat(response, is(nullValue()));
	}

	@Test
	public void calculate_shouldNotUtiliseTheCalculator_ifGivenANullModel() {
		// Act
		CalculationModel calculation = null;
		CalculationModel response = underTest.calculate(calculation);
		// assert
		verifyZeroInteractions(calculator);
	}

	@Test
    public void calculate_shouldReturnAFormattedSolution_forAnyLargeCalculation() {
        // Arrange
        when(calculator.add(100000, 20)).thenReturn(100020);
        when(formatter.format(100020)).thenReturn("100,020");
        CalculationModel calculation = new CalculationModel(
                CalculationType.ADDITION, 100000, 20);

        // Act
        CalculationModel response = underTest.calculate(calculation);

        // Assert
        assertThat(response.getFormattedSolution(), is(equalTo("100,020")));
    }
}
