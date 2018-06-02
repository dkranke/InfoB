package blatt6.a2;

import blatt5.a2.List;

/*
 * Aufgabe 1.2: Fraction
 */
public class Fraction extends Number {

    private static final List<Fraction> known = new List<Fraction>();

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

        Fraction frac = new Fraction(numerator, denominator);
        return getKnown(frac);
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

    /**
     * Suche nach einem bekannten Bruch, oder registriere den neuen
     *
     * @param frac Ziel Bruch
     * @return Bekannter Bruch
     */
    private static Fraction getKnown(Fraction frac) {
        while (known.hasNext()) {
            Fraction f = known.next();
            if (frac.equals(f)) {
                return f;
            }
        }

        known.add(frac);
        return frac;
    }

    public Fraction multiply(int factor) {
        return getKnown(new Fraction(numerator * factor, denominator));
    }

    public Fraction multiply(Fraction factor) {
        return getKnown(new Fraction(numerator * factor.numerator, denominator * factor.denominator));
    }

    public Fraction divide(Fraction divisor) {
        return getKnown(new Fraction(numerator * divisor.denominator, denominator * divisor.numerator));
    }

    public Fraction multiply(Fraction... factors) {
        Fraction value = this.clone();
        for (Fraction factor : factors) {
            value = getKnown(value.multiply(factor));
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
        return getKnown(new Fraction(numerator * addend.denominator + denominator * addend.numerator, denominator * addend.denominator));
    }

    // Überladung von equals um Objekte vergleichen zu können
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fraction) {
            Fraction f = (Fraction) obj;
            return numerator == f.numerator && denominator == f.denominator;
        }
        return false;
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
        return numerator / ((long) denominator);
    }

    @Override
    public float floatValue() {
        return numerator / ((float) denominator);
    }

    @Override
    public double doubleValue() {
        return numerator / ((double) denominator);
    }

    /**
     * Subtracts a fraction to another
     *
     * @param subtrahend Another fraction
     * @return The calculated fraction
     */
    public Fraction substract(Fraction subtrahend) {
        return getKnown(new Fraction(numerator * subtrahend.denominator - denominator * subtrahend.numerator, denominator * subtrahend.denominator));
    }
}
