package Methods.NonLinearEquations;

import EquationData.EquationData;
import Methods.AbstractClasses.SimpleIterationMethodAbs;

public class SimpleIterationMethod extends SimpleIterationMethodAbs implements NonlinearEquationMethodInterface {

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
