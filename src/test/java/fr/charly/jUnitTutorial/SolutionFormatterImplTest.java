package fr.charly.jUnitTutorial;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SolutionFormatterImplTest {

	private SolutionFormatter underTest = new SolutionFormatterImpl();

	@Test
	public void format_returnsNoCommansFor_onAValueLessThanOneThousdand() {
		String result = underTest.format(999);
		assertThat(result, is(equalTo("999")));
	}

	@Test
	public void format_returnsACommaAfterThreeDigits_onOneHundredThousandAndTwenty() {
		String result = underTest.format(100020);
		assertThat(result, is(equalTo("100,020")));
	}

	@Test
	public void format_returnsACommaAfterThreeDigits_onMinusOneHundredThousandAndTwenty() {
		String result = underTest.format(-100020);
		assertThat(result, is(equalTo("-100,020")));
	}

	@Test
	public void format_returnsACommaAfterOneAndFourDigits_onOneMillionAndTwenty() {
		String result = underTest.format(1000020);
		assertThat(result, is(equalTo("1,000,020")));
	}
}