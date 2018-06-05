package blatt6.a2;

public class FractionTest {
    private static String out;

    public static void main(String[] args) {
        Fraction f = new Fraction(2, 4);
        out = "Fraction f = new Fraction(2,4)";
        print();
        out = "Fraction.parseFraction(\"1/2\") == f is ";
        out += Fraction.parseFraction("1/2") == f;
        print();

        f = new Fraction(0).add(new Fraction(1, 2));
        out = "\nf = new Fraction(0).add(new Fraction(1,2));";
        print();
        out = "Fraction.parseFraction(\"1/2\") == f is ";
        out += Fraction.parseFraction("1/2") == f;
        print();

        f = new Fraction(1).substract(new Fraction(1, 2));
        out = "\nf = new Fraction(1).substract(new Fraction(1,2));";
        print();
        out = "Fraction.parseFraction(\"1/2\") == f is ";
        out += Fraction.parseFraction("1/2") == f;
        print();

        f = new Fraction(1).multiply(new Fraction(1, 2));
        out = "\nf = new Fraction(1).multiply(new Fraction(1,2));";
        print();
        out = "Fraction.parseFraction(\"1/2\") == f is ";
        out += Fraction.parseFraction("1/2") == f;
        print();

        f = new Fraction(2).divide(new Fraction(4));
        out = "\nf = new Fraction(2).divide(new Fraction(4));";
        print();
        out = "Fraction.parseFraction(\"1/2\") == f is ";
        out += Fraction.parseFraction("1/2") == f;
        print();
    }

    private static void print() {
        System.out.println(out);
    }





}
