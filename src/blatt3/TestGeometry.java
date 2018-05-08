package blatt3;

public class TestGeometry {
    public static void main(String[] args) {
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(10, 10, 10);
        Point p3 = new Point(10, 10);

        System.out.println("p1 == [0.0, 0.0, 0.0]? " + p1.toString().equals("[0.0, 0.0, 0.0]"));
        System.out.println("p2 == [10.0, 10.0, 10.0]? " + p2.toString().equals("[10.0, 10.0, 10.0]"));
        System.out.println("p3 == [10.0, 10.0]? " + p3.toString().equals("[10.0, 10.0]"));

        Point2D p2d1 = new Point2D(0, 0);
        Point2D p2d2 = new Point2D(10, 10);

        System.out.println("p2d1 == [0.0, 0.0]? " + p2d1.toString().equals("[0.0, 0.0]"));
        System.out.println("p2d2 == [10.0, 10.0]? " + p2d2.toString().equals("[10.0, 10.0]"));

        Rectangle rect = new Rectangle(p2d1, p2d2);
        System.out.println("rect(p2d1,p2d2): " + rect + "\t volume: " + rect.volume());

        Volume volume = new Volume(p1, p2);
        System.out.println("volume(p1,p2): " + volume + "\t volume: " + volume.volume());

        Volume vol2 = new Volume(p2d1, p3);
        System.out.println("vol2(p2d1, p3): " + vol2 + "\t volume: " + vol2.volume());


    }


}
