package blatt3.a1;

import util.Test;
import util.TestOutput;

public class StudentTest {
    public static void main(String[] args) {
        Person p1 = new Person("P1");
        Person p2 = new Person("P2");
        Person pLike1 = new Person("P1");

        Student s1 = new Student("S1", 101);
        Student s2 = new Student("S2", 102);
        Student sLike1 = new Student("S1", 101);
        Student sLikeP1 = new Student("P1", 103);

        Test test = new Test(new TestOutput() {
            @Override
            public String toString(Object o) {
                if (o instanceof Student) {
                    Student s = (Student) o;
                    return String.format("Student(Name: %s, MatNr: %d)", s.getName(), s.getMatNr());
                } else if (o instanceof Person) {
                    return String.format("Person(Name: %s)", ((Person) o).getName());
                } else {
                    return TestOutput.super.toString(o);
                }
            }
        });

        //Person
        test.assertEquals(p1, p1);
        test.assertUnequals(p1, p2);
        test.assertEquals(p1, pLike1);
        System.out.println();

        //Student
        test.assertEquals(s1, s1);
        test.assertUnequals(s1, s2);
        test.assertEquals(s1, sLike1);
        System.out.println();

        //Person + Student
        test.assertUnequals(p1, sLikeP1);
        System.out.println();

        test.printTotal();
    }
}