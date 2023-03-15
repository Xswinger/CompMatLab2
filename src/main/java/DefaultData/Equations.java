package DefaultData;


import java.util.HashMap;
import java.util.Map;

//TODO Найти способ адекватного вывода уравнения
public final class Equations {

    private static final String FIRST_EQUATION = "x^3 - 0,2x^2 + 0,5x + 1,5 = 0";
    private static final String SECOND_EQUATION = "3x^4 - 8x^3 - 18x^2 + 2 = 0";
    private static final String THIRD_EQUATION = "x^2 - 20\\sin{x} = 0";
    private static final String FOURTH_EQUATION = "\\sqrt{x+1} = \\frac{1}{x}";

    private static final Map<String, Double> firstEquation = new HashMap() {{

    }};

    public static String getFirstEquation() {
        return FIRST_EQUATION;
    }

    public static String getSecondEquation() {
        return SECOND_EQUATION;
    }

    public static String getThirdEquation() {
        return THIRD_EQUATION;
    }

    public static String getFourthEquation() {
        return FOURTH_EQUATION;
    }
}
