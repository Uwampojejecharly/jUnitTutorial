package fr.charly.jUnitTutorial;

/**
 * A model to represent a two argument integer calculation
 * which needs to be performed.
 *
 */
public class CalculationModel {
    private static final String SEPARATOR = " ";
    private Integer leftArgument;
    private Integer rightArgument;
    private CalculationType type;
    private Integer solution;
    private String formattedSolution;

    public CalculationModel(CalculationType calculationType, int leftArgument, int rightArgument) {
        this.type = calculationType;
        this.leftArgument = leftArgument;
        this.rightArgument = rightArgument;
    }
    /**
     * Convenience Constructor used in test
     */
    public CalculationModel(CalculationType calculationType, int leftArgument, int rightArgument, Integer solution) {
        this.type = calculationType;
        this.leftArgument = leftArgument;
        this.rightArgument = rightArgument;
        this.solution = solution;
    }

    /**
     * Builds a Calculation from a string such as 2 + 2
     * @param calculation in written form
     * @return model representing the calculatoin
     */
    public static CalculationModel fromText(String calculation) {
        String[] parts = calculation.split(SEPARATOR);
        int leftArgument = Integer.parseInt(parts[0]);
        int rightArgument = Integer.parseInt(parts[2]);
        CalculationType calculationType = CalculationType.fromSymbol(parts[1]);

        return new CalculationModel(
                calculationType, leftArgument, rightArgument);
    }

    public Integer getLeftArgument() {
        return leftArgument;
    }

    public void setLeftArgument(Integer leftArgument) {
        this.leftArgument = leftArgument;
    }

    public Integer getRightArgument() {
        return rightArgument;
    }

    public void setRightArgument(Integer rightArgument) {
        this.rightArgument = rightArgument;
    }

    public CalculationType getType() {
        return type;
    }

    public void setType(CalculationType type) {
        this.type = type;
    }

    public Integer getSolution() {
        return solution;
    }

    public void setSolution(Integer solution) {
        this.solution = solution;
    }

    public String getFormattedSolution() {
        return formattedSolution;
    }

    public void setFormattedSolution(String formattedSolution) {
        this.formattedSolution = formattedSolution;
    }
}
