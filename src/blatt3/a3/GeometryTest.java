package blatt3.a3;

import util.Test;

public class GeometryTest {

    private static Test test = new Test();

    public static void main(String[] args) {
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(10, 10, 10);
        Point p3 = new Point(10, 10);

        Point2D p2d1 = new Point2D(0, 0);
        Point2D p2d2 = new Point2D(10, 10);

        Rectangle rect = new Rectangle(p2d1, p2d2);
        Volume volume = new Volume(p1, p2);
        Volume vol2 = new Volume(p2d1, p3);

        test.stringEquals(p1, "Point[0.0, 0.0, 0.0]");
        test.stringEquals(p2, "Point[10.0, 10.0, 10.0]");
        test.stringEquals(p3, "Point[10.0, 10.0]");
        System.out.println();

        test.stringEquals(p2d1, "Point2D[0.0, 0.0]");
        test.stringEquals(p2d2, "Point2D[10.0, 10.0]");
        System.out.println();

        test.addTotal(volumeTest(rect, 100.0));
        test.addTotal(volumeTest(volume, 1000.0));
        test.addTotal(volumeTest(vol2, 100.0));
        System.out.println();

        test.printTotal();
    }

    public static boolean volumeTest(Volume v, double volume) {
        if (v.volume() == volume) {
            System.out.printf(test.getOutput().stringFormat(), v, "==", volume + ".volume()", test.getOutput().stringPassed());
        } else {
            System.out.printf(test.getOutput().stringFormat(), v, "==", volume + ".volume()", test.getOutput().stringFailed(0));
        }
        return v.volume() == volume;
    }

}
