package blatt9.a3;

import io.FileVisitResult;
import io.FileVisitor;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SearchLineVisitor implements FileVisitor {

    private static ThreadPoolExecutor pool;

    static {
        // Formula: Processor Count * target cpu utilization * (1 + wait time / compute time) according to JCIP
        int count = (int) (Runtime.getRuntime().availableProcessors() * 0.75 * (1 + 0.25 / 0.75));
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(count);
    }

    private String pattern;
    private boolean recursive;

    public SearchLineVisitor(String pattern, boolean recursive) {
        this.pattern = pattern;
        this.recursive = recursive;
    }

    public void awaitTermination() {
        while (pool.getActiveCount() > 0) {
            Thread.yield();
        }

        pool.shutdown();
    }

    @Override
    public FileVisitResult postVisitDirectory(File dir) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(File dir) {
        if (recursive) {
            return FileVisitResult.CONTINUE;
        } else {
            return FileVisitResult.SKIP_SUBTREE;
        }
    }

    @Override
    public FileVisitResult visitFailed(File dir) {
        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult visitFile(File file) {
        if (file.isFile() && file.canRead()) {
            pool.submit(new SearchLineRunnable(file, pattern));
        }
        return FileVisitResult.CONTINUE;
    }
}
