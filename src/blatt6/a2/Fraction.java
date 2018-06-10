package blatt6.a2;

import java.util.HashMap;

/*
 * Aufgabe 1.2: Fraction
 */
public class Fraction extends Number {

    private static final HashMap<String, Fraction> pool = new HashMap<>();

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

        // Bestehende Referenz vorrangig nutzen
        pool.putIfAbsent(toString(), this);
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
        return pool.getOrDefault(frac.toString(), frac);
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
        Fraction frac = new Fraction(numerator * factor, denominator);
        return pool.getOrDefault(frac.toString(), frac);
    }

    public Fraction multiply(Fraction factor) {
        Fraction frac = new Fraction(numerator * factor.numerator, denominator * factor.denominator);
        return pool.getOrDefault(frac.toString(), frac);
    }

    public Fraction divide(Fraction divisor) {
        Fraction frac = new Fraction(numerator * divisor.denominator, denominator * divisor.numerator);
        return pool.getOrDefault(frac.toString(), frac);
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
     * @param fraction Another fraction
     * @return The calculated fraction
     */
    public Fraction add(Fraction fraction) {
        Fraction frac = new Fraction(numerator * fraction.denominator + denominator * fraction.numerator, denominator * fraction.denominator);
        return pool.getOrDefault(frac.toString(), frac);
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
        Fraction frac = new Fraction(numerator * subtrahend.denominator - denominator * subtrahend.numerator, denominator * subtrahend.denominator);
        return pool.getOrDefault(frac.toString(), frac);
    }
}
