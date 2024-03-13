package fr.charly.jUnitTutorial;

import org.springframework.stereotype.Component;

/**
 * A very simple calculator
 */
@Component
public class Calculator {

	/**
	 * Adds two values
	 * 
	 * @param augend for addition
	 * @param addend for addition
	 * @return Sum of the two arguments
	 */
	public Integer add(int augend, int addend) {
		return augend + addend;
	}

	/**
	 * Multiplies two values
	 * 
	 * @param multiplicand for multiplication
	 * @param multiplyer   for multiplication
	 * @return The product of multiplying two values
	 */
	public Integer multiply(int multiplicand, int multiplyer) {
		return multiplicand * multiplyer;
	}

	public void cos(double v) {
		throw new UnsupportedOperationException("Trigonometry not supported!");
	}

	public void slowCalculation() {
		try {
			Thread.sleep(0001L);
		} catch (InterruptedException e) {
			System.out.println("THIS OPERATION COMPLETED AFTER 3 SECONDS");
		}
	}

	public Integer divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException("Divisor cannot be zero!");
		}
		return dividend / divisor;
	}



	public Integer subtract(int minuend, int subtrahend) {
		// TODO Auto-generated method stub
		return  minuend - subtrahend;
	}
}
