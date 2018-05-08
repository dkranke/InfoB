/*
 * Aufgabe 1.2: Fraction
 */
public class Fraction {

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
}
