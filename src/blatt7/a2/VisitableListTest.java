package blatt7.a2;

public class VisitableListTest {

    public static void main(String[] args) {
        VisitableList<Integer> list1 = new VisitableList<Integer>();
        VisitableList<Integer> list2 = new VisitableList<Integer>();

        list1.add(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        list1.accept((e) -> {
            list2.add(e * 2);
            return e < 5;
        });

        System.out.println(list1);
        System.out.println(list2);
    }
}
