package blatt5.a2;

import util.Test;

public class ListTest {

    public static void main(String[] args) {
        String[] set = {"A", "B", "C", "D", "E"};
        Test test = new Test();

        List<String> list1 = new List<String>(), list2;
        list2 = list1.clone();

        test.label("Konstruktor");
        test.assertUnequals(list1, list2);

        test.label("Add");
        list1.add(set);
        test.assertUnequals(list1, list2);

        test.label("Remove");
        String string1 = list1.toString();
        list1.remove("D", "E");
        test.stringUnequals(list1, string1);

        test.label("Klone");
        list2 = list1.clone();
        test.assertUnequals(list1, list2);

        test.label("Iterieren (mit advance)");
        list1.reset();
        while (!list1.endpos()) {
            list1.advance();
            System.out.println(" - " + test.toString(list1.elem()));
        }

        test.label("Iterieren (mit next)");
        list2.reset();
        while (list2.hasNext()) {
            System.out.println(" - " + test.toString(list2.next()));
        }

        test.printTotal();
    }
}
