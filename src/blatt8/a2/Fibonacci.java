package blatt8.a2;

import java.io.*;
import java.util.HashMap;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Fibonacci {

    private final static HashMap<Integer, Long> fibonacciHash;

    static {
        fibonacciHash = load();
    }

    /**
     * Calculates the fibonacci value of n.
     *
     * @param n a natural number >= 0 to calculate the fibonacci value of
     * @return fibonacci value of n
     */
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n = " + n);
        }
        return getFibonacci(n);
    }

    private static long getFibonacci(int n) {
        if (fibonacciHash.containsKey(n)) {
            return fibonacciHash.get(n);
        } else {
            long nMinus1 = getFibonacci(n - 1);
            long nMinus2 = getFibonacci(n - 2);
            long fibonacci = nMinus1 + nMinus2;

            fibonacciHash.put(n, fibonacci);
            return fibonacci;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
        } else {
            try {
                System.out.println(fibonacci(Integer.parseInt(args[0])));
                save();
            } catch (IllegalArgumentException ex) {
                printUsage();
            }
        }
    }

    private static void printUsage() {
        System.out.println("java calc/Fiboncci n");
        System.out.println("n must be a natural number >= 0");
    }

    private static HashMap<Integer, Long> load() {
        File path = new File("Fibonacci.HashMap");
        HashMap<Integer, Long> map = null;
        if (!path.exists()) {
            map = new HashMap<Integer, Long>();
            map.put(0, 0L);
            map.put(1, 1L);
        }

        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(path));
            ObjectInput in = new ObjectInputStream(is);
            Object obj = in.readObject();
            if (obj instanceof HashMap) {
                map = (HashMap<Integer, Long>) obj;
            }
            in.close();
        } catch (Exception e) {
            map = new HashMap<Integer, Long>();
            map.put(0, 0L);
            map.put(1, 1L);
        }
        return map;
    }

    private static void save() {
        File path = new File("Fibonacci.HashMap");
        if (path.exists()) {
            File backup = new File("Fibonacci.HashMap.old");
            backup.delete();
            path.renameTo(backup);
            //path.delete();
        }

        try {
            path.createNewFile();
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(path));
            ObjectOutput out = new ObjectOutputStream(os);
            out.writeObject(fibonacciHash);
            out.close();
        } catch (Exception e) {
            System.err.println("Error while saving Hashmap:");
            e.printStackTrace();
        }
    }
}
