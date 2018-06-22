package blatt9.a1;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class WatchService {

    private static long initSize, oldSize;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java WatchService <path>");
            return;
        }

        File path = new File(args[0]);
        if (!path.exists() || !path.canRead()) {
            System.out.println("ERROR: Invalid path");
            return;
        }

        initSize = size(path);
        oldSize = initSize;
        System.out.println("Initial size: " + initSize);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                long size = size(path);
                if (size != oldSize) {
                    System.out.printf("Size changed from %d to %d%n", oldSize, size);
                    oldSize = size;
                }
            }
        };

        Thread shutdown = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf("While running the size changed from %d to %d!", initSize, oldSize);
            }
        });
        Runtime.getRuntime().addShutdownHook(shutdown);

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, 1000);

        while (true) ;
    }

    private static long size(File path) {
        if (path.isDirectory()) {
            long size = 0;
            File[] files = path.listFiles();
            if (files != null) {
                for (File f : files) {
                    size += size(f);
                }
            }
            return size;
        } else {
            return path.length();
        }
    }
}
