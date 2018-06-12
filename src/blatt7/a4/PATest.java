package blatt7.a4;

import java.io.IOException;
import java.util.Calendar;

public class PATest {

    public static void main(String[] args) throws IOException {
        try {
            PersistantArray pa = new PersistantArray("date");
            System.out.println("Get: " + pa);
        } catch (IOException e) {
            Calendar c = Calendar.getInstance();
            Integer array[] = new Integer[]{c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR)};
            PersistantArray pa = new PersistantArray("date", array);
            System.out.println("Set: " + pa);
        }
    }
}
