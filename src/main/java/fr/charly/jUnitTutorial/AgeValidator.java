package fr.charly.jUnitTutorial;

public class AgeValidator {

	public static final int MINIMUM_UNLIKELY_AGE = 200;

	public void validate(int age) throws InsaneAgeException {

		if (age >= MINIMUM_UNLIKELY_AGE) {
			throw new InsaneAgeException(String.format("%d is an unlikely age unless you have pointy ears", age));
		}
	}
}