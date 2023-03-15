package Methods.NonLinearEquations;

import EquationData.EquationData;

public class ChordMethod implements NonlinearEquationMethodInterface{
    private double[] function;

    public static final String METHOD_NAME = "Метод хорд";

    private double leftBorder;

    private double rightBorder;

    private double approximateRootValue;

    public ChordMethod() {
    }

    public ChordMethod(double leftBorder, double rightBorder, double[] function) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.function = function;
    }

    @Override
    public void iterationsCycle(EquationData selection) {
        while (!checkEndCriterion()) {
            approximateRootValue = (leftBorder* solutionOfEquation(rightBorder)-rightBorder* solutionOfEquation(leftBorder))/
                    (solutionOfEquation(leftBorder) - solutionOfEquation(rightBorder));
            choiceNewInterval();
        }
    }

    private void choiceNewInterval() {
        double rightBorder = solutionOfEquation(approximateRootValue);
        double firstLeftBorder = solutionOfEquation(leftBorder);
        double secondLeftBorder = solutionOfEquation(rightBorder);

        if (rightBorder - firstLeftBorder < 0 || firstLeftBorder - rightBorder < 0) {
            leftBorder = firstLeftBorder;
        } else {
            leftBorder = secondLeftBorder;
        }
    }

    @Override
    public boolean checkEndCriterion() {
        return true;
    }

    @Override
    public double solutionOfEquation(double border) {
        return border;
    }
}
