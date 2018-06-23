package blatt9.a3;

import io.SearchLineReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class SearchLineRunnable implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    private File file;
    private String pattern;

    public SearchLineRunnable(File file, String pattern) {
        this.file = file;
        this.pattern = pattern;
    }

    @Override
    public void run() {
        ArrayList<String> entries = new ArrayList<String>();
        try {

            FileReader fr = new FileReader(file);
            SearchLineReader slr = new SearchLineReader(fr, pattern);

            String line;
            while ((line = slr.readLine()) != null) {
                int i = slr.getAmountOfMatches();
                if (i > 0) {
                    entries.add(" - Line " + slr.getLineNumber() + ", " + i + " matche(s): " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error while reading file: " + file.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        if (!entries.isEmpty()) {
            lock.lock();
            System.out.println(entries.size() + " matche(s) in File '" + file.getAbsolutePath() + "':");
            for (String entry : entries) {
                System.out.println(entry);
            }
            lock.unlock();
        }
    }
}
