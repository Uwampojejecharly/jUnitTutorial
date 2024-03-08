package fr.charly.jUnitTutorial;

public class Calculator {

	public Integer add(int i, int i1) {
		return i + i1;
	}

	public Integer multiply(int i, int i1) {
		return i * i1;
	}

	public void cos(int i, int i1) {
		throw new UnsupportedOperationException("Trigonometry not supported!");

	}

	public void slowCalculation() {
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			System.out.println("THIS OPERATION COMPLETED AFTER 3 SECONDS");

		}

	}

}
