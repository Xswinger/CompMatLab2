package Methods.NonLinearEquations;

import EquationData.EquationData;

public interface NonlinearEquationMethodInterface {

    double accuracy = 0;

    void iterationsCycle(EquationData selection);

    double solutionOfEquation(double border);

    boolean checkEndCriterion();

}
