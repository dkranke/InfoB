/*
 * Klasse zum Testen der Fraction-Klasse.
 * Tests bestehen aus einer Ausgabe eines Vergleiches und dessen Evaluierung
 */
public class FractionTest {

    public static void main(String[] args) {
        // Gesamtergebnis
        boolean passed = true;

        // Teste ob beide Konstruktor funktionieren
        System.out.println("Konstruktor Test:");
        System.out.print("\tFraction(2) == '2/1' ? ");
        System.out.println(new Fraction(2).toString().equals("2/1"));
        passed &= new Fraction(2).toString().equals("2/1");

        System.out.print("\tFraction(1,3) == '1/3' ? ");
        System.out.println(new Fraction(1, 3).toString().equals("1/3"));
        passed &= new Fraction(1, 3).toString().equals("1/3");
        System.out.println();

        // Teste ob das Kürzen funktioniert
        System.out.println("Kuerzen Test");
        System.out.print("\tFraction(2,2) == '1/1' ? ");
        System.out.println(new Fraction(2, 2).toString().equals("1/1"));
        passed &= new Fraction(2, 2).toString().equals("1/1");

        System.out.print("\tFraction(4,6) == '2/3' ? ");
        System.out.println(new Fraction(4, 6).toString().equals("2/3"));
        passed &= new Fraction(4, 6).toString().equals("2/3");

        System.out.print("\tFraction(2,3) == '2/3' ? ");
        System.out.println(new Fraction(2, 3).toString().equals("2/3"));
        passed &= new Fraction(2, 3).toString().equals("2/3");
        System.out.println();

        // Teste ob die multiply-Methoden funktionieren
        System.out.println("Multiplizieren Test");
        System.out.println("a = Fraction(1,2), b = Fraction(2,3), c = Fraction(3,5)");
        Fraction a = new Fraction(1, 2), b = new Fraction(2, 3), c = new Fraction(3, 5);

        System.out.print("\ta.multiply(2) == '2/2' ? ");
        System.out.println(a.multiply(2).toString().equals("1/1"));
        passed &= a.multiply(2).toString().equals("1/1");

        System.out.print("\ta.multiply(b) == '2/6' ? ");
        System.out.println(a.multiply(b).toString().equals("1/3"));
        passed &= a.multiply(b).toString().equals("1/3");

        System.out.print("\ta.multiply(b,c) == '6/30' ? ");
        System.out.println(a.multiply(b, c).toString().equals("1/5"));
        passed &= a.multiply(b, c).toString().equals("1/5");
        System.out.println();

        // Teste ob das Teilen funktioniert
        System.out.println("Division Test");
        System.out.println("a = Fraction(4,5), b = Fraction(1,2)");
        a = new Fraction(4, 5);
        b = new Fraction(1, 2);

        System.out.print("\ta.divide(b) == '8/5' ? ");
        System.out.println(a.divide(b).toString().equals("8/5"));
        passed &= a.divide(b).toString().equals("8/5");
        System.out.println("\n");

        System.out.println("Tests bestanden? " + passed);
    }
}
