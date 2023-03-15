package Methods.NonLinearEquations;

import EquationData.EquationData;

public class NewtonsMethod implements NonlinearEquationMethodInterface{

    public static final String METHOD_NAME = "Метод Ньютона";

    @Override
    public void iterationsCycle(EquationData selection) {
    }

    @Override
    public double solutionOfEquation(double border) {
        return 0;
    }

    @Override
    public boolean checkEndCriterion() {
        return false;
    }
}
