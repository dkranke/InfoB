package blatt2.a1;

public class FibonacciPrint {
    public static void main(String[] args) {
        //Validating argument value
        int maxValue, n = 0;
        try {
            maxValue = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Please enter a integer value as argument.");
            return;
        }

        //Output of the Fibonacci sequence
        System.out.println("| n  |  f(n)   |");
        System.out.println("+----+---------+");
        for (Fibonacci fib = new Fibonacci(); n <= maxValue; fib = fib.next()) {
            StringBuilder line = new StringBuilder();
            String num = justify(n, 4);
            String fibNum = justify(fib.getNumber(), 9);
            line.append("|").append(num).append("|").append(fibNum).append("|");
            System.out.println(line.toString());
            n++;
        }
    }

    /**
     * Converts a number to a String with a given length (number standing on the right)
     *
     * @param value         Number to convert
     * @param justification Expected output length
     * @return Converted number String
     */
    private static String justify(int value, int justification) {
        StringBuilder justified = new StringBuilder(justification);
        String intString = String.valueOf(value);
        int i = 0;
        while (i + intString.length() < justification) {
            justified.append(" ");
            i++;
        }
        justified.append(intString);
        return justified.toString();
    }
}
