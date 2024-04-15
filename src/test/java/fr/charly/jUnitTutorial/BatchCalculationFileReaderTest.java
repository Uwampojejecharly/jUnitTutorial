package fr.charly.jUnitTutorial;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ BatchCalculationFileServiceImpl.class })
public class BatchCalculationFileReaderTest {
	@Test
	public void getCalculationsFromBatchFile_shouldReturnAStreamOfCalculations_whenGivenAValidFile()
			throws IOException {
		// ARRANGE
		BatchCalculationFileServiceImpl classUnderTest = new BatchCalculationFileServiceImpl();
		String fakeBatchFile = "/path/to/batchFile.txt";
		List<String> expectedCalculations = Arrays.asList("1 + 2", "3 - 2");
		PowerMockito.mockStatic(Files.class);
		PowerMockito.mockStatic(Paths.class); 
		// Mock
		Path path = mock(Path.class);
		when(Paths.get(fakeBatchFile)).thenReturn(path);
		when(Files.lines(path)).thenReturn(expectedCalculations.stream());
		// ACT
		Stream<String> response = classUnderTest.read(fakeBatchFile);
		List<String> responseAsList = response.collect(Collectors.toList());
		// ASSERT

		assertThat(responseAsList, containsInAnyOrder(expectedCalculations.toArray()));
	}
}
