package blatt9.a1;

import java.io.File;
import java.util.TimerTask;

public class WatchServiceTimerTask extends TimerTask {

    private File path;
    private long initSize, previousSize, currentSize;

    public WatchServiceTimerTask(File path) {
        this.path = path;
        initSize = size(path);
        previousSize = initSize;
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

    public long getInitSize() {
        return initSize;
    }

    public long getCurrentSize() {
        return currentSize;
    }

    public long getPreviousSize() {
        return previousSize;
    }

    @Override
    public void run() {
        currentSize = size(path);
        if (currentSize != previousSize) {
            System.out.printf("Size changed from %d to %d%n", previousSize, currentSize);
            previousSize = currentSize;
        }
    }
}
