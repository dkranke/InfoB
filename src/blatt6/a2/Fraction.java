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
        pool(this);
    }

    public Fraction(int numerator) {
        this(numerator, 1);
        pool(this);
    }

    /**
     * Gibt eine bekannte Referenz zurück oder fügt diese dem Pool hinzu
     *
     * @param fraction Ziel Bruch
     * @return Bekannter Bruch
     */
    private static Fraction pool(Fraction fraction) {
        String key = fraction.toString();
        Fraction f = pool.get(key);
        if (f == null) {
            pool.put(key, fraction);
            return fraction;
        }
        return f;
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
        return pool(frac);
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
        return pool(new Fraction(numerator * factor, denominator));
    }

    public Fraction multiply(Fraction factor) {
        return pool(new Fraction(numerator * factor.numerator, denominator * factor.denominator));
    }

    public Fraction divide(Fraction divisor) {
        return pool(new Fraction(numerator * divisor.denominator, denominator * divisor.numerator));
    }

    public Fraction multiply(Fraction... factors) {
        Fraction value = this.clone();
        for (Fraction factor : factors) {
            value = pool(value.multiply(factor));
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
        Fraction f = new Fraction(numerator * fraction.denominator + denominator * fraction.numerator, denominator * fraction.denominator);
        return pool(f);
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
        Fraction f = new Fraction(numerator * subtrahend.denominator - denominator * subtrahend.numerator, denominator * subtrahend.denominator);
        return pool(f);
    }
}
