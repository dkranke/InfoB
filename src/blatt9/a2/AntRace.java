package blatt9.a2;

public class AntRace implements AntFields {

    public static void main(String[] args) {

        AntField field = new AntField(FIELD4);
        Ant ant = new Ant(field, 2, 4, 1);
        Thread t = new Thread(ant);
        t.start();

        while (t.isAlive()) {
            Thread.yield();
        }

        System.out.println(field.toString());
    }
}
