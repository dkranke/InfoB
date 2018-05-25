package blatt5.a3;

import blatt5.a2.List;
import util.Test;

import java.util.Random;

public class HashListTest {

    private static Random r = new Random();

    public static void main(String[] args) {
        Test test = new Test();

        List<String> list = new List<String>();
        HashList<String> hashList = new HashList<String>(5);

        test.label("Insert");
        String tmp = hashList.toString();
        hashList.insert("0");

        int i = 200;
        while (i > 0) {
            String key = keygen();
            if (hashList.insert(key)) {
                list.add(key);
                i--;
            }

            if (i == 182) {
                test.stringUnequals(list, tmp);
            }
        }

        test.label("Contains");
        System.out.println(" - Contains \"0\" (true): " + hashList.contains("0"));
        test.addTotal(hashList.contains("0"));
        System.out.println(" - Contains \"1\" (false): " + hashList.contains("1"));
        test.addTotal(!hashList.contains("1"));

        test.label("Remove");
        boolean temp = hashList.contains("0");
        System.out.println(" - Remove \"0\" (true): " + temp);
        test.addTotal(temp);
        temp = hashList.contains("1");
        System.out.println(" - Remove \"1\" (false): " + temp);
        test.addTotal(temp);

        test.label("Speed Test");
        String[] keys = new String[5000];
        for (i = 0; i < keys.length; i++) {
            keys[i] = keygen();
        }

        double speed = test.compareListSpeedMs(keys, (v) -> listContains(list, v), (v) -> hashList.contains(v));
        if (speed > 0) {
            System.out.printf(" - HashList is %6.3f ms faster than List%n", speed);
            test.addTotal(true);
        } else {
            System.out.printf(" - List is %6.3f ms faster than HashList%n", -speed);
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
