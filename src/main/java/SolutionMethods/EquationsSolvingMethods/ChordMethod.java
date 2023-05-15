package SolutionMethods.EquationsSolvingMethods;

import DefaultData.DefaultEquations;
import SolutionsData.EquationData;

public class ChordMethod implements EquationSolvingMethodInterface {
    private EquationData data;

    public static final String METHOD_NAME = "Метод хорд";

    private double approximateRootValue;

    private String[] outputData = new String[3];

    public ChordMethod(EquationData data) {
        this.data = data;
    }

    private double leftBorder;
    private double rightBorder;

    @Override
    public String[] iterationsCycle() {
        if (!checkSufficientCondition()) {
            return null;
        }
        int iterationNumber = 0;
        leftBorder = data.getLeftBorder();
        rightBorder = data.getRightBorder();
        while (!checkEndCriterion() && iterationNumber <= 999) {
            approximateRootValue = (leftBorder * DefaultEquations.solutionOfEquation(rightBorder, this.data)
                    - rightBorder * DefaultEquations.solutionOfEquation(leftBorder, this.data)/
                    (DefaultEquations.solutionOfEquation(leftBorder, this.data) - DefaultEquations.solutionOfEquation(rightBorder, this.data)));
            choiceNewInterval();
            iterationNumber++;
        }
        outputData[0] = String.valueOf(iterationNumber);
        outputData[1] = String.valueOf(approximateRootValue);
        outputData[2] = String.valueOf(DefaultEquations.solutionOfEquation(approximateRootValue, data));
        return outputData;
    }

    private void choiceNewInterval() {
        double newBorder = DefaultEquations.solutionOfEquation(approximateRootValue, this.data);
        double localLeftBorder = DefaultEquations.solutionOfEquation(data.getLeftBorder(), this.data);

        if (localLeftBorder*newBorder < 0) {
            rightBorder = newBorder;
        } else {
            leftBorder = newBorder;
        }
    }

    private boolean checkSufficientCondition() {
        double leftBorder = DefaultEquations.solutionOfEquation(data.getLeftBorder(), this.data);
        double rightBorder = DefaultEquations.solutionOfEquation(data.getRightBorder(), this.data);
        if (leftBorder*rightBorder > 0) {
            return false;
        }
        double step = (data.getRightBorder() - data.getLeftBorder()) / 49.0;
        for (int i = 1; i < 50; i++) {
            double currentValue = DefaultEquations.solutionOfDerivativeEquation(1.0 + i * step, this.data);
            double pastValue = DefaultEquations.solutionOfDerivativeEquation(1.0 + (i-1) * step, this.data);
            if (currentValue*pastValue < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkEndCriterion() {
        return Math.abs(DefaultEquations.solutionOfEquation(approximateRootValue, this.data)) <= data.getAccuracy();
    }
}
