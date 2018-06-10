package blatt7.a1;

import util.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class SpeedTest {

    public static void main(String[] args) {
        Test test = new Test();
        Random random = new Random();
        int amount = 10000;

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        HashSet<Integer> hashSet = new HashSet<Integer>();

        test.label("Add (AL<HS<LL)");
        Integer[] array = new Integer[amount];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length);
        }
        System.out.printf(" - LinkedList: %fms%n", test.speedTestNs((x) -> linkedList.add(x), array));
        System.out.printf(" - ArrayList:  %fms%n", test.speedTestNs((x) -> arrayList.add(x), array));
        System.out.printf(" - HashSet:    %fms%n", test.speedTestNs((x) -> hashSet.add(x), array));

        test.label("Contains (HS<LL<AL)");
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length);
        }
        System.out.printf(" - LinkedList: %fms%n", test.speedTestNs((x) -> linkedList.contains(x), array));
        System.out.printf(" - ArrayList:  %fms%n", test.speedTestNs((x) -> arrayList.contains(x), array));
        System.out.printf(" - HashSet:      %fms%n", test.speedTestNs((x) -> hashSet.contains(x), array));

        test.label("Remove (HS<LL<AL)");
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length);
        }
        System.out.printf(" - LinkedList: %fms%n", test.speedTestNs((x) -> linkedList.remove(x), array));
        System.out.printf(" - ArrayList:  %fms%n", test.speedTestNs((x) -> arrayList.remove(x), array));
        System.out.printf(" - HashSet:      %fms%n", test.speedTestNs((x) -> hashSet.remove(x), array));


    }
}
