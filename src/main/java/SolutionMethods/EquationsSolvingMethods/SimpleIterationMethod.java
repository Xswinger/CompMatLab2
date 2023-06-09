package SolutionMethods.EquationsSolvingMethods;

import DefaultData.DefaultEquations;
import SolutionsData.EquationData;
import SolutionMethods.AbstractClasses.SimpleIterationMethodAbs;

public class SimpleIterationMethod extends SimpleIterationMethodAbs implements EquationSolvingMethodInterface {

    private EquationData data;

    private double approximateRootValue;
    private double pastApproximateRootValue;

    private double lambda;

    private final String[] outputData = new String[3];

    public SimpleIterationMethod(EquationData data) {
        this.data = data;
    }

    private double leftBorder;
    private double rightBorder;

    @Override
    public String[] iterationsCycle() {
        getLambda();

        leftBorder = data.getLeftBorder();
        rightBorder = data.getRightBorder();

        if (solutionOfDerivationEquation(leftBorder) > 1 && solutionOfDerivationEquation(rightBorder) > 1) {
            return null;
        }

        if (solutionOfDerivationEquation(leftBorder) < 1) {
            pastApproximateRootValue = leftBorder;
        } else {
            pastApproximateRootValue = rightBorder;
        }

        int iterationNumber = 0;

        while (iterationNumber <= 999) {
            approximateRootValue = solutionOfEquation(pastApproximateRootValue);
            if (checkEndCriterion()) {
                break;
            }
            pastApproximateRootValue = approximateRootValue;
            iterationNumber++;
        }
        outputData[0] = String.valueOf(iterationNumber);
        outputData[1] = String.valueOf(approximateRootValue);
        outputData[2] = String.valueOf(DefaultEquations.solutionOfEquation(approximateRootValue, data));
        return outputData;
    }

    public double solutionOfEquation(double value) {
        return value + lambda *(DefaultEquations.solutionOfEquation(value, this.data));
    }

    public double solutionOfDerivationEquation(double value) {
        return 1 + lambda*(DefaultEquations.solutionOfDerivativeEquation(value, this.data));
    }

    private void getLambda() {
        lambda = -1/(Math.max(DefaultEquations.solutionOfDerivativeEquation(leftBorder, this.data),
                DefaultEquations.solutionOfDerivativeEquation(rightBorder, this.data)));
    }

    @Override
    public boolean checkEndCriterion() {
        return Math.abs(approximateRootValue - pastApproximateRootValue) <= accuracy;
    }
}
