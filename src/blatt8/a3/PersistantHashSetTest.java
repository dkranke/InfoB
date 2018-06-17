package blatt8.a3;

import blatt6.a3.List;
import util.Test;

import java.io.*;

public class PersistantHashSetTest {

    public static void main(String[] args) {

        Test test = new Test();

        PersistantHashSet<Integer> phsOriginal = new PersistantHashSet<Integer>(3);
        List<Integer> list = new List<Integer>();

        test.label("Add");
        for (int i = 0; i < 10; i++) {
            phsOriginal.insert(i);
            list.add(i);
        }
        System.out.println(" - " + test.toString(phsOriginal));

        test.label("Save");
        boolean result = save("test.phs", phsOriginal);
        System.out.println(" - " + (result ? "Saving successful" : "Saving failed"));
        test.isTrue(result);

        test.label("Load");
        PersistantHashSet<Integer> phsLoad = (PersistantHashSet<Integer>) load("test.phs");
        result = phsLoad != null;
        System.out.println(" - " + (result ? "Loading successful" : "Loading failed"));
        test.isTrue(result);

        test.label("Compare (original vs loaded)");
        test.equals(phsOriginal.toString(), phsLoad.toString());

        test.printTotal();
    }

    private static boolean save(String path, Object o) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            ObjectOutput out = new ObjectOutputStream(os);
            out.writeObject(o);
            out.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error while saving Object:");
            e.printStackTrace();
            return false;
        }
    }

    private static Object load(String path) {
        File file = new File(path);

        Object obj = null;
        if (file.exists()) {
            try {
                BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
                ObjectInput in = new ObjectInputStream(is);
                obj = in.readObject();
                in.close();
            } catch (Exception e) {
                System.err.println("Error while loading Object:");
                e.printStackTrace();
            }
        }

        return obj;
    }
}
