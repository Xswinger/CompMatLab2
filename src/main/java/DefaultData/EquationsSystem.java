package DefaultData;

//TODO Найти способ адекватного вывода системы уравнений

import java.util.HashMap;
import java.util.Map;

public class EquationsSystem {

    private static final String FIRST_SYSTEM = "" +
            "\\begin{cases}" +
            "0,1x^2 + x + 0,2y^2 - 0,3 = 0" +
            "\\\\" +
            "0,2x^2 + y + 0,1xy - 0,7 = 0" +
            "\\\\" +
            "\\end{cases}";

    private static final String SECOND_SYSTEM = "" +
            "\\begin{cases}" +
            "x^2 + y^2 - 4 = 0" +
            "\\\\" +
            "-3x^2 + y = 0" +
            "\\\\" +
            "\\end{cases}";

    private static final String THIRD_SYSTEM = "" +
            "\\begin{cases}" +
            "x^2 + x - y^2 - 0,15 = 0" +
            "\\\\" +
            "x^2 - y + y^2 + 0,17 = 0" +
            "\\\\" +
            "\\end{cases}";

    private static final Map[] firstSystemMapOfValues = new Map[] {new HashMap<String, Double>() {{
        put("x^2", 0.1);
        put("x", 1.0);
        put("y^2", 0.2);
        put("-", -0.3);
    }}, new HashMap<String, Double>() {{
        put("x^2", 0.2);
        put("y", 1.0);
        put("xy", 0.1);
        put("-", -0.7);
    }}};

    private static final Map[] secondSystemMapOfValues = new Map[] {new HashMap<String, Double>() {{
        put("x^2", 1.0);
        put("y^2", 1.0);
        put("-", -4.0);
    }}, new HashMap<String, Double>() {{
        put("x^2", -3.0);
        put("y", 1.0);
    }}};

    private static final Map[] thirdSystemMapOfValues = new Map[] {new HashMap<String, Double>() {{
        put("x^2", 1.0);
        put("x", 1.0);
        put("y^2", -1.0);
        put("-", -0.15);
    }}, new HashMap<String, Double>() {{
        put("x^2", 1.0);
        put("y", -1.0);
        put("y^2", 1.0);
        put("-", 0.17);
    }}};

    public static String getFirstSystem() {return FIRST_SYSTEM;}

    public static String getSecondSystem() {
        return SECOND_SYSTEM;
    }

    public static String getThirdSystem() {
        return THIRD_SYSTEM;
    }

    public static Map[] getFirstSystemMapOfValues() {
        return firstSystemMapOfValues;
    }

    public static Map[] getSecondSystemMapOfValues() {
        return secondSystemMapOfValues;
    }

    public static Map[] getThirdSystemMapOfValues() {
        return thirdSystemMapOfValues;
    }
}
