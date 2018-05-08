public class Ticker {
    private static Ticker ticker;
    private String message = "";

    private Ticker() {
        //DO NOT DELETE
        //hiding default constructor so that it cannot be instanciated from outside
    }

    public static Ticker getInstance() {
        if (ticker == null) {
            ticker = new Ticker();
        }
        return ticker;
    }

    public void print(String msg) {
        if (!message.isEmpty()) {
            message += "+++";
        }
        message = message + msg.replaceAll("\n", " ");
        System.out.println(message);
    }
}
