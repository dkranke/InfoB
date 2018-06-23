package blatt9.a1;

import java.io.File;
import java.util.Timer;

public class WatchService {

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

        WatchServiceTimerTask wstt = new WatchServiceTimerTask(path);
        System.out.println("Initial size: " + wstt.getInitSize());

        Thread shutdown = new Thread(new WatchServiceRunnable(wstt));
        Runtime.getRuntime().addShutdownHook(shutdown);

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(wstt, 0, 1000);

        while (true) ;
    }
}
