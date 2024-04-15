package fr.charly.jUnitTutorial;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements Solver {
	private Calculator calculator;
	private SolutionFormatter formatter;

	public CalculatorService(Calculator calculator, SolutionFormatter formatter) {
		this.calculator = calculator;
		this.formatter = formatter;
	}

    public CalculationModel calculate(CalculationModel calculationModel) {
        // FIXME : This really needs a custom exception
        if (null == calculationModel) {
            return null;
        }

        return solve(calculationModel, calculator, formatter);
    }
}
 