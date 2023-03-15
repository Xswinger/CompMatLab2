package DefaultData;

//TODO Найти способ адекватного вывода системы уравнений

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

    public static String getFirstSystem() {return FIRST_SYSTEM;}

    public static String getSecondSystem() {
        return SECOND_SYSTEM;
    }

    public static String getThirdSystem() {
        return THIRD_SYSTEM;
    }
}
