package fr.charly.jUnitTutorial;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class BatchCalculatorTest {
	// index of fixtures by type
	private static final int MULTIPLICATION_INDEX = 0;
	private static final int ADDITION_INDEX = 1;

	@Mock
	BatchCalculationFileService batchCalculationFileService;

	@Mock
	CalculatorService calculatorService;

	// The class we're testing
	private BatchCalculator classUnderTest;

	@Before
	public void setup() throws IOException {
		// This is our fake file content
		Stream<String> calculations = Stream.of("3 x 2", "4 + 2");

		// Let's make sure that the batch calculation service returns this
		when(batchCalculationFileService.read(Mockito.any(String.class))).thenReturn(calculations);
		setupCalculatorServiceMock();
		classUnderTest = new BatchCalculator(batchCalculationFileService, calculatorService);
	}

	private void setupCalculatorServiceMock() {
        // Set up the calculator service mock
        when(calculatorService.calculate(ArgumentMatchers.any(CalculationModel.class)))
                .thenAnswer(
                        new Answer<CalculationModel>() {

                            private CalculationModel multiplicationModel = new CalculationModel(
                                    CalculationType.MULTIPLICATION, 3, 2, 6);


                            private CalculationModel additionModel = new CalculationModel(
                                    CalculationType.ADDITION, 4, 2, 6);

                            private Integer invocations = 0;

                            @Override
                            public CalculationModel answer(InvocationOnMock invocationOnMock){
                                // count the number of calls
                                invocations++;
                                if (invocations == 1) {
                                    return multiplicationModel;
                                }
                                // FIXME : throw an execption if we call this too many times.
                                return additionModel;

                            }
                        }
                );
    }

	@Test
	public void calculateFromFile_shouldOpenTheRightFile_whenGivenAPath() throws IOException {
		// ACT
		List<CalculationModel> actual = classUnderTest.calculateFromFile("/path/to/fake/file");

		// ASSERT we get back usable models
		verify(batchCalculationFileService).read("/path/to/fake/file");
	}

	@Test
	public void calculateFromFile_shouldReturnTwoSolutions_forTwoCalculations() throws IOException {
		// ACT
		List<CalculationModel> actual = classUnderTest.calculateFromFile("/path/to/fake/fail");

		// ASSERT we get back usable models
		assertThat(actual, hasSize(2));
	}

	@Test
	// @Ignore("Needs a mock CalculatorService with multiple solutions")
	public void calculateFromFile_shouldReturnTheCorrectAnswer_forAdditions() throws IOException {
		// ACT
		List<CalculationModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
		Integer answer = solutions.get(ADDITION_INDEX).getSolution();

		// ASSERT we get back usable models
		assertThat(answer, is(equalTo(6))); // 4+2
	}

	@Test
	public void calculateFromFile_shouldUseACorrectModel_forTheFirstCalculation() throws IOException {
		// ARRANGE
		ArgumentCaptor<CalculationModel> calculationModelCaptor = ArgumentCaptor.forClass(CalculationModel.class);

		// ACT
		List<CalculationModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");

		// ASSERT (Addition and Multiplication)
		verify(calculatorService, times(2)).calculate(calculationModelCaptor.capture());
		List<CalculationModel> calculationModels = calculationModelCaptor.getAllValues();
		assertThat(calculationModels.get(0).getType(), is(equalTo(CalculationType.MULTIPLICATION))); // 3x2
		assertThat(calculationModels.get(0).getLeftArgument(), is(equalTo(3))); // 3x2
		assertThat(calculationModels.get(0).getRightArgument(), is(equalTo(2))); // 3x2
	}

	@Test
	public void calculateFromFile_shouldUseACorrectModel_forTheSecondCalculation() throws IOException {
		// ARRANGE
		ArgumentCaptor<CalculationModel> calculationModelCaptor = ArgumentCaptor.forClass(CalculationModel.class);

		// ACT
		List<CalculationModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");

		// ASSERT (Addition and Multiplication)
		verify(calculatorService, times(2)).calculate(calculationModelCaptor.capture());
		List<CalculationModel> calculationModels = calculationModelCaptor.getAllValues();
		assertThat(calculationModels.get(1).getType(), is(equalTo(CalculationType.ADDITION)));
		assertThat(calculationModels.get(1).getLeftArgument(), is(equalTo(4))); // 4+2
		assertThat(calculationModels.get(1).getRightArgument(), is(equalTo(2))); // 4+2
	}

	@Test
	public void calculateFromFile_shouldReturnTheCorrectAnswer_forMultiplication() throws IOException {
		// ACT
		List<CalculationModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
		Integer answer = solutions.get(MULTIPLICATION_INDEX).getSolution();

		// ASSERT we get back usable models
		assertThat(answer, is(equalTo(6)));
	}

	@Test
	public void calculateFromFile_shouldCorrectlyMultiplyWithTheCalculator_forProducts() throws IOException {
		// ACT
		List<CalculationModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
		Integer answer = solutions.get(ADDITION_INDEX).getSolution();

		// ASSERT we get back usable models
		verify(calculatorService, times(2)).calculate(any());
	}

	@Test
	public void calculateFromFile_shouldPassbackTheCorrectModel_forCalculatoins() throws IOException {
		// ACT
		List<CalculationModel> solutions = classUnderTest.calculateFromFile("/path/to/fake/fail");
		CalculationModel answer = solutions.get(MULTIPLICATION_INDEX);

		// ASSERT we get back usable models
		assertThat(answer, allOf(hasProperty("leftArgument", is(3)), hasProperty("rightArgument", is(2)),
				hasProperty("type", is(equalTo(CalculationType.MULTIPLICATION))), hasProperty("solution", is(6))));
	}

}