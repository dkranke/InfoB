package blatt3;

public class StudentTest {
    public static void main(String[] args) {
        boolean total = true;

        Person p1 = new Person("Test P1");
        Person p2 = new Person("Test P2");
        Student s1 = new Student("Test S1", 101);
        Student s2 = new Student("Test S2", 100);

        System.out.println("Konstruktor Test");
        total &= test(p1.getName(), "Test P1");
        total &= test(p2.getName(), "Test P2");
        total &= test(s1.getName(), "Test S1");
        total &= test(s2.getName(), "Test S2");

        System.out.println("Equals Test");
        total &= test(p1, p1);
        total &= test(p1, p2);
        total &= !test(p1, "Test P1");
        total &= test(s1, s1);
        total &= test(s1, s2);

        System.out.println("HashCode Test");
        total &= test(p1.hashCode(), p1.hashCode());
        total &= test(p1.hashCode(), p2.hashCode());
        total &= !test(p1.hashCode(), "Test P1");
        total &= test(s1.hashCode(), s1.hashCode());
        total &= test(s1.hashCode(), s2.hashCode());

        System.out.println("Total: " + total);
    }

    public static boolean test(Object a, Object b) {
        boolean equal = a.equals(b);
        System.out.println(a + " == " + b + " ? " + equal);
        return equal;
    }
}