package fr.charly.jUnitTutorial;

public class SolutionFormatterImpl implements SolutionFormatter {
	@Override
	public String format(Integer solution) {
		return String.format("%,d", solution);
	}

	@Override
	public String format(int value) {
		return String.format("%,d", value);
	}
}
