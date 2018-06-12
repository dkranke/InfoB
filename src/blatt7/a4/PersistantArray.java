package blatt7.a4;

import java.io.*;
import java.util.Arrays;

public class PersistantArray {

    Integer[] array;
    String name;

    public PersistantArray(String name) throws IOException {
        this.name = name;
        if (!load()) {
            throw new IOException("Wrong object-type");
        }
    }

    public PersistantArray(String name, Integer[] array) throws IOException {
        this.array = array;
        this.name = name;
        save();
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

    private void save() throws IOException {
        File path = new File(name + ".IntArr");
        if (path.exists()) {
            path.delete();
        }

        path.createNewFile();
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(path));
        ObjectOutput out = new ObjectOutputStream(os);
        out.writeObject(array);
        out.close();
    }

    private boolean load() throws IOException {
        File path = new File(name + ".IntArr");
        if (!path.exists()) {
            throw new IOException("File not found");
        }

        BufferedInputStream is = new BufferedInputStream(new FileInputStream(path));
        ObjectInput in = new ObjectInputStream(is);
        try {
            Object obj = in.readObject();
            if (obj instanceof Integer[]) {
                array = (Integer[]) obj;
            }
            in.close();
            return true;
        } catch (ClassNotFoundException e) {
            in.close();
            return false;
        }
    }

    @Override
    public String toString() {
        return "PersistantArray" + Arrays.toString(array);
    }
}
