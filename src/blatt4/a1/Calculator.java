package blatt4.a1;

public class Calculator {

    /**
     * Main method of Calculator
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        // Überprüfe ob die richtige Anzahl an Argumenten vorhanden sind
        if (args.length != 3 || args[1].length() != 1) {
            System.out.println("Usage: java Calculator <fraction> <operator> <fraction>");
            return;
        }

        try {
            // Versuche die Zahlen zu parsen, sonst gebe eine Fehlermeldung aus
            Number first = parseNumber(args[0]);
            Number second = parseNumber(args[2]);
            Number result;

            if (first instanceof Fraction && second instanceof Fraction) {
                result = calc((Fraction) first, args[1].charAt(0), (Fraction) second);
            } else {
                result = calc(first, args[1].charAt(0), second);
            }

            // Gebe das Ergebnis aus
            if (result != null) {
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println("Usage: java Calculator <fraction> <operator> <fraction>");
        }
    }

    /**
     * Converts a string into a Number
     *
     * @param str number string
     * @return a Number
     */
    public static Number parseNumber(String str) {
        Number num = Fraction.parseFraction(str);
        if (num == null) {
            num = Double.parseDouble(str);
        }
        return num;
    }

    /**
     * Applies a primitive mathematic function to two fractions
     *
     * @param first    First fraction
     * @param operator Primitive operator
     * @param second   Second fraction
     * @return result
     */
    public static Fraction calc(Fraction first, char operator, Fraction second) {
        // Versuche einen Operator anzuwenden, sonst gebe eine Fehlermeldung aus
        switch (operator) {
            case '+':
                return first.add(second);
            case '-':
                return first.substract(second);
            case '*':
                return first.multiply(second);
            case '/':
                return first.divide(second);
            default:
                System.out.println("Invalid operator, operators: +-*/");
                return null;
        }
    }

    /**
     * Applies a primitive mathematic function to two fractions
     *
     * @param first    First fraction
     * @param operator Primitive operator
     * @param second   Second fraction
     * @return result
     */
    public static Double calc(Number first, char operator, Number second) {
        // Versuche einen Operator anzuwenden, sonst gebe eine Fehlermeldung aus
        double v1 = first.doubleValue();
        double v2 = second.doubleValue();
        switch (operator) {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            case '*':
                return v1 * v2;
            case '/':
                return v1 / v2;
            default:
                System.out.println("Invalid operator, operators: +-*/");
                return null;
        }
    }
}