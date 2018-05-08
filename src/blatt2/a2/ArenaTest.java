package blatt2.a2;

public class ArenaTest {

    /**
     * Main method of ArenaTest
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        Arena arena = new Arena();

        boolean total = true;

        // Check if (0,0) is Area 0 (arena center)
        total &= arena.getArea(0, 0) == 0;
        System.out.printf("(0,0) == 0 ? %s\n\n", total);

        // Check each area at radius 1
        for (int angle = 15; angle < 360; angle += 30) {
            // Calculate Area by angle
            int area = angle / 30 + 1;

            // Calculate position by angle (at radius 1)
            double x = Math.cos(Math.toRadians(angle));
            double y = Math.sin(Math.toRadians(angle));

            boolean test = arena.getArea(x, y) == area;
            total &= test;
            System.out.printf("(%f,%f) == %d ? %s\n", x, y, area, test);
        }
        System.out.println();

        // Check each area at radius 2
        for (int angle = 15; angle < 360; angle += 30) {
            // Calculate position by angle (at radius 2)
            double x = Math.cos(Math.toRadians(angle)) * 2;
            double y = Math.sin(Math.toRadians(angle)) * 2;

            boolean test = arena.getArea(x, y) == -1;
            total &= test;
            System.out.printf("(%f,%f) == -1 ? %s\n", x, y, test);
        }
        System.out.println();

        System.out.printf("Tests successful ? %s\n", total);
    }
}