package fr.charly.jUnitTutorial;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsCalculatorTest {

	@Spy
	IntSummaryStatistics summaryStatistics = new IntSummaryStatistics();

	StatisticsCalculator underTest;

	@Before
	public void setUp() {
		underTest = new StatisticsCalculator(summaryStatistics);
	}

	@Test
	public void average_shouldSample_allIntegersProvided() {
		ArgumentCaptor<Integer> sampleCaptor = ArgumentCaptor.forClass(Integer.class);
		List<Integer> samples = Arrays.asList(2, 8, 5, 3, 7);

		Integer result = underTest.average(samples);
		verify(summaryStatistics, times(samples.size())).accept(sampleCaptor.capture());

		List<Integer> capturedList = sampleCaptor.getAllValues();
		assertThat(capturedList, IsIterableContainingInOrder.contains(samples.toArray()));
	}

	@Test
	public void average_shouldReturnTheMean_ofAListOfIntegers() {
		ArgumentCaptor<Integer> sampleCaptor = ArgumentCaptor.forClass(Integer.class);
		List<Integer> samples = Arrays.asList(2, 8, 5, 3, 7);
		Integer result = underTest.average(samples);

		assertThat(result, is(equalTo(5)));
	}
}