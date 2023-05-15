package SolutionMethods.SystemSolvingMethods;

import DefaultData.DefaultSystemsOfEquations;
import SolutionsData.SystemData;
import SolutionMethods.AbstractClasses.SimpleIterationMethodAbs;

import java.util.Objects;

public class SimpleIterationMethod extends SimpleIterationMethodAbs implements SystemSolvingMethodInterface {

    private SystemData data;

    private double approximateRootValue;

    private final String[] outputData = new String[3];

    public SimpleIterationMethod() {
    }

    private double firstOldValue;
    private double secondOldValue;

    private double firstNewValue;
    private double secondNewValue;

    @Override
    public String[] iterationsCycle() {
        firstOldValue = data.getLeftBorder();
        secondOldValue = data.getRightBorder();
        if (!checkProcess()) {
            return null;
        }

        int iterationNumber = 0;

        firstNewValue = firstOldValue;
        secondNewValue = secondOldValue;

        while (iterationNumber <= 999) {

            if (Objects.equals(data.getSystemName(), "FIRST_SYSTEM")) {
                firstNewValue = DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationSolution(firstOldValue);
                secondNewValue = DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationSolution(secondOldValue);
                if (checkEndCriterion()) {
                    break;
                }
                firstOldValue = firstNewValue;
                secondOldValue = secondNewValue;
            } else {
                firstNewValue = DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationSolution(firstOldValue);
                secondNewValue = DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationSolution(secondOldValue);
                if (checkEndCriterion()) {
                    break;
                }
                firstOldValue = firstNewValue;
                secondOldValue = secondNewValue;
            }

            iterationNumber++;
        }

        outputData[0] = String.valueOf(iterationNumber);
        outputData[1] = String.valueOf(approximateRootValue);
        if (Objects.equals(data.getSystemName(), "FIRST_SYSTEM")) {
            outputData[2] = String.valueOf(DefaultSystemsOfEquations.solutionOfFirstEquationSystem(approximateRootValue, data));
        } else {
            outputData[2] = String.valueOf(DefaultSystemsOfEquations.solutionOfSecondEquationSystem(approximateRootValue, data));
        }
        return outputData;
    }

    private boolean checkProcess() {
        if (Objects.equals(data.getSystemName(), "FIRST_SYSTEM")) {
            return Math.max(Math.abs(DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationXDerivative(firstOldValue) +
                            DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationYDerivative(secondOldValue)),
                    Math.abs(DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationXDerivative(firstOldValue, secondOldValue) +
                            DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationYDerivative(secondOldValue))) < 1;
        } else {
            return Math.max(Math.abs(DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationXDerivative(firstOldValue) +
                            DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationYDerivative(secondOldValue)),
                    Math.abs(DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationXDerivative(firstOldValue) +
                            DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationYDerivative(secondOldValue))) < 1;
        }
    }

    @Override
    public double solutionOfEquation(double border) {
        return 0;
    }

    @Override
    public boolean checkEndCriterion() {
        return (Math.abs(firstNewValue - firstOldValue) <= accuracy || Math.abs(secondNewValue - secondOldValue) <= accuracy);
    }

    public void setData(SystemData data) {
        this.data = data;
    }

    public SystemData getData() {
        return data;
    }
}
