package blatt2.a4;

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
            // Versuche die Brüche zu parsen, sonst gebe eine Fehlermeldung aus
            Fraction first = Fraction.parseFraction(args[0]);
            Fraction second = Fraction.parseFraction(args[2]);
            Fraction result;

            // Versuche einen Operator anzuwenden, sonst gebe eine Fehlermeldung aus
            switch (args[1].charAt(0)) {
                case '+':
                    result = first.add(second);
                    break;
                case '-':
                    result = first.substract(second);
                    break;
                case '*':
                    result = first.multiply(second);
                    break;
                case '/':
                    result = first.divide(second);
                    break;
                default:
                    System.out.println("Invalid operator, operators: +-*/");
                    return;
            }

            // Gebe das Ergebnis aus
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Usage: java Calculator <fraction> <operator> <fraction>");
        }
    }
}