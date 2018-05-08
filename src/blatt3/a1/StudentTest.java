package blatt3.a1;

public class StudentTest {
    public static void main(String[] args) {
        Person p1 = new Person("P1");
        Person p2 = new Person("P2");
        Person pLike1 = new Person("P1");

        Student s1 = new Student("S1", 101);
        Student s2 = new Student("S2", 102);
        Student sLike1 = new Student("S1", 101);
        Student sLikeP1 = new Student("P1", 103);

        //Person
        assertEqual(p1, p1);
        assertUnequal(p1, p2);
        assertEqual(p1, pLike1);

        //Student
        assertEqual(s1, s1);
        assertUnequal(s1, s2);
        assertEqual(s1, sLike1);

        //Person + Student
        assertUnequal(p1, sLikeP1);
    }

    private static void assertEqual(Person a, Person b) {
        boolean equal = equalsTest(a, b);
        boolean hashEqual = hashComparison(a, b);
        if (equal && hashEqual) {
            System.out.println("Test OK\n");
        } else {
            System.out.println("Test FAILED\n");
        }
    }

    private static void assertUnequal(Person a, Person b) {
        boolean equal = equalsTest(a, b);
        boolean hashEqual = hashComparison(a, b);
        if (!(equal || hashEqual)) {
            System.out.println("Test OK\n");
        } else {
            System.out.println("Test FAILED\n");
        }
    }

    private static boolean equalsTest(Person a, Person b) {
        boolean equal = a.equals(b);
        System.out.println("Is " + a.getName() + " = " + b.getName() + "? " + equal);
        return equal;
    }

    private static boolean hashComparison(Person a, Person b) {
        boolean equal = a.hashCode() == b.hashCode();
        System.out.println("Is " + a.hashCode() + " = " + b.hashCode() + "? " + equal);
        return equal;
    }
}