package blatt1.a4;

public class Company {
    private String name;
    private double stockPrice;

    public Company(String name) {
        this.name = name;
        stockPrice = 0;
    }

    @Override
    protected void finalize() throws Throwable {
        Ticker.getInstance().print(name + " is insolvent");
        super.finalize();
    }

    public void changeStockPrice(double d) {
        this.stockPrice = d;
        Ticker.getInstance().print(name + " " + stockPrice);
    }
}
