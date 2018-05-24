import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class TickerTest {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Company> map = new HashMap<>();

    public static void main(String[] args) {
        int selection = 0;
        do {
            //Ask user what to do
            do {
                System.out.println("Enter...\n" +
                        "1: create/delete a Company\n" +
                        "2: change stock price\n" +
                        "3: call GC\n" +
                        "4: end");

                selection = readInt();
            } while (selection < 1 || selection > 4);

            if (selection == 1) {           //creating/removing a company
                createOrDeleteCompany();
            } else if (selection == 2) {    //changing stock price
                changeStockPrice();
            } else if (selection == 3) {    //calling GC
                System.out.println("------------------");
                System.gc();
                System.out.println("------------------");
            }


        } while (selection != 4);
    }

    private static String readCompanyName() {
        String companyName = "";
        do {
            System.out.print("Company Name: ");
            companyName = read();
        } while (companyName.isEmpty());
        return companyName;
    }

    private static void createOrDeleteCompany() {
        String companyName = readCompanyName();

        if (map.containsKey(companyName)) {
            map.remove(companyName);
            System.out.println(companyName + " was removed.");
        } else {
            map.put(companyName, new Company(companyName));
            System.out.println(companyName + " was added.");
        }
    }

    private static void changeStockPrice() {
        //Selecting company and stock price
        if (map.isEmpty()) {
            return;
        }
        String companyName = "";
        do {
            companyName = readCompanyName();
        } while (!map.containsKey(companyName));

        System.out.print("Stock price: ");
        Double stockPrice = readDouble();

        //Updating stock price
        System.out.println("------------------");
        map.get(companyName).changeStockPrice(stockPrice);
        System.out.println("------------------");
    }

    private static Double readDouble() {
        return scanner.nextDouble();
    }

    private static String read() {
        return scanner.next();
    }

    public static int readInt() {
        int returnValue = 0;
        boolean repeat = true;
        do {
            try {
                returnValue = Integer.valueOf(read());
                repeat = false;
            } catch (NumberFormatException e) {
                System.out.println("Fehler! Bitte ein int: ");
            }
        } while (repeat);
        return returnValue;
    }

}
