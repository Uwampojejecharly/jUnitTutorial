package fr.charly.jUnitTutorial;

import org.springframework.stereotype.Component;

/**
 * An interface for classes which want to solve a calculation
 */
@Component
public interface Solver {

	/**
	 * Takes a definition of a calculation and solves it
	 * 
	 * @param calculationModel defines the calulation
	 * @param calculator       to be used to solve the calculation
	 * @return A model with the result of the calculation
	 */
	default CalculationModel solve(CalculationModel calculationModel, Calculator calculator,
			SolutionFormatter formatter) {
		CalculationType type = calculationModel.getType();

		Integer response;
		switch (type) {
		case ADDITION:
			response = calculator.add(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case MULTIPLICATION:
			response = calculator.multiply(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case DIVISION:
			response = calculator.divide(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case SUBTRACTION:
			response = calculator.subtract(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		default:
			throw new UnsupportedOperationException("Unsupported calculations");
		}

		calculationModel.setSolution(response);
		calculationModel.setFormattedSolution(formatter.format(response));
		return calculationModel;
	}
}
