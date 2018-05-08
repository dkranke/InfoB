package blatt4.a1;

/*
 * Aufgabe 1.2: Fraction
 */
public class Fraction extends Number {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        // Initialisiere die Eigenschaften
        this.numerator = numerator;
        this.denominator = denominator;

        // Kürze den Bruch
        int ggT = getGGT(numerator, denominator);
        if (ggT > 0) {
            this.numerator /= ggT;
            this.denominator /= ggT;
        }
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    /**
     * Converts a string to a fraction
     *
     * @param fraction A string with the format <numerator>/<denominator>
     * @return The parsed fraction
     * @throws NumberFormatException Exception if the format is wrong
     */
    public static Fraction parseFraction(String fraction) {
        if (!fraction.matches("\\d+\\/[1-9]\\d*")) {
            return null;
        }

        String[] numbers = fraction.split("/");
        int numerator = Integer.parseInt(numbers[0]);
        int denominator = Integer.parseInt(numbers[1]);

        return new Fraction(numerator, denominator);
    }

    // Euklidischer Algorithmus
    private int getGGT(int a, int b) {
        if (a == 0) {
            return b;
        }

        while (b != 0) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }

        return a;
    }

    public Fraction multiply(int factor) {
        return new Fraction(numerator * factor, denominator);
    }

    public Fraction multiply(Fraction factor) {
        return new Fraction(numerator * factor.numerator, denominator * factor.denominator);
    }

    public Fraction divide(Fraction divisor) {
        return new Fraction(numerator * divisor.denominator, denominator * divisor.numerator);
    }

    public Fraction multiply(Fraction... factors) {
        Fraction value = this.clone();
        for (Fraction factor : factors) {
            value = value.multiply(factor);
        }
        return value;
    }

    /**
     * Adds a fraction to another
     *
     * @param addend Another fraction
     * @return The calculated fraction
     */
    public Fraction add(Fraction addend) {
        return new Fraction(numerator * addend.denominator + denominator * addend.numerator, denominator * addend.denominator);
    }

    /**
     * Subtracts a fraction to another
     *
     * @param subtrahend Another fraction
     * @return The calculated fraction
     */
    public Fraction substract(Fraction subtrahend) {
        return new Fraction(numerator * subtrahend.denominator - denominator * subtrahend.numerator, denominator * subtrahend.denominator);
    }

    // Überladung von clone um das Kopieren des eigenen Objektes zu vereinfachen
    @Override
    public Fraction clone() {
        return new Fraction(numerator, denominator);
    }

    // Überladung von toString um eine bessere Ausgabe zu ermöglichen
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public int intValue() {
        return numerator / denominator;
    }

    @Override
    public long longValue() {
        return numerator / denominator;
    }

    @Override
    public float floatValue() {
        return numerator / denominator;
    }

    @Override
    public double doubleValue() {
        return numerator / denominator;
    }
}
