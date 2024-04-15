package fr.charly.jUnitTutorial;

import org.junit.Test;

public class AgeValidatorTest {

	@Test(expected = InsaneAgeException.class)
	public void validate_shouldThrowAnException_forSomeoneOver200YearsOld() throws InsaneAgeException {
		AgeValidator ageValidator = new AgeValidator();
		ageValidator.validate(200);
	}

	@Test
	public void validate_shouldNotThrow_forSomeoneUnder200YearsOld() throws InsaneAgeException {
		AgeValidator ageValidator = new AgeValidator();
		ageValidator.validate(199);
		// This passes by virtual of the fact that we do not fail with an exception
	}
}
