package blatt5.a3;

import blatt5.a2.List;
import util.Test;

import java.util.Random;

public class HashSetTest {

    private static Random r = new Random();

    public static void main(String[] args) {
        Test test = new Test();

        List<String> list = new List<String>();
        HashSet<String> hashSet = new HashSet<String>(5);

        test.label("Insert");
        String tmp = hashSet.toString();
        hashSet.insert("0");

        int i = 200;
        while (i > 0) {
            String key = keygen();
            if (hashSet.insert(key)) {
                list.add(key);
                i--;
            }

            if (i == 182) {
                test.stringUnequals(list, tmp);
            }
        }

        test.label("Contains");
        System.out.println(" - Contains \"0\" (true): " + hashSet.contains("0"));
        test.addTotal(hashSet.contains("0"));
        System.out.println(" - Contains \"1\" (false): " + hashSet.contains("1"));
        test.addTotal(!hashSet.contains("1"));

        test.label("Remove");
        boolean temp = hashSet.contains("0");
        System.out.println(" - Remove \"0\" (true): " + temp);
        test.addTotal(temp);
        temp = hashSet.contains("1");
        System.out.println(" - Remove \"1\" (false): " + temp);
        test.addTotal(temp);

        test.label("Speed Test");
        String[] keys = new String[5000];
        for (i = 0; i < keys.length; i++) {
            keys[i] = keygen();
        }

        double speed = test.compareListSpeedMs(keys, (v) -> listContains(list, v), (v) -> hashSet.contains(v));
        if (speed > 0) {
            System.out.printf(" - HashSet is %6.3f ms faster than List%n", speed);
            test.addTotal(true);
        } else {
            System.out.printf(" - List is %6.3f ms faster than HashSet%n", -speed);
            test.addTotal(false);
        }
    }

    private static String keygen() {
        char c = (char) ((int) 'A' + r.nextInt(26));
        int x = r.nextInt(10);
        return "" + c + x;
    }

    private static boolean listContains(List<String> list, String value) {
        list.reset();
        while (list.hasNext()) {
            if (value.equals(list.next())) {
                return true;
            }
        }
        return false;
    }
}
