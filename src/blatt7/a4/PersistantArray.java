package blatt7.a4;

import java.io.*;

public class PersistantArray {

    Integer[] array;
    String name;

    public PersistantArray(String name) throws IOException, ClassNotFoundException {
        this.name = name;
        if (!load()) {
            throw new IOException("Wrong object-type");
        }
    }

    public PersistantArray(String name, Integer[] array) {
        this.array = array;
        this.name = name;
    }

    public int length() {
        return array.length;
    }

    public Integer get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("index out of range");
        }
        return new Integer(array[index]);
    }

    public boolean set(int index, Integer value) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("index out of range");
        }
        array[index] = value;

        try {
            save();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void save() throws IOException {
        File path = new File(name + ".IntArr");
        if (path.exists()) {
            path.delete();
        }

        path.createNewFile();
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(path));
        ObjectOutput out = new ObjectOutputStream(os);
        out.writeObject(array);
    }

    public boolean load() throws ClassNotFoundException, IOException {
        File path = new File(name + ".IntArr");

        BufferedInputStream is = new BufferedInputStream(new FileInputStream(path));
        ObjectInput in = new ObjectInputStream(is);
        Object obj = in.readObject();

        return obj instanceof Integer[];
    }
}
