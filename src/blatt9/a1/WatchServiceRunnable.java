package blatt9.a1;

public class WatchServiceRunnable implements Runnable {

    private WatchServiceTimerTask wstt;

    public WatchServiceRunnable(WatchServiceTimerTask wstt) {
        this.wstt = wstt;
    }

    @Override
    public void run() {
        System.out.printf("While running the size changed from %d to %d!%n", wstt.getInitSize(), wstt.getCurrentSize());
    }
}
