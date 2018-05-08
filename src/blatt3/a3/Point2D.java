package blatt3.a3;

/**
 * Aufgabe 3.3: Rectangle (Volume with fixed dimensions)
 */
public class Point2D extends Point {
    public Point2D() {
        super(2);
    }

    public Point2D(int x, int y) {
        super(x, y);
    }

    /**
     * Get x-coordinate
     *
     * @return value
     */
    public double getX() {
        return getDimension(0);
    }

    /**
     * Set x-coordinate
     *
     * @param x value
     */
    public void setX(double x) {
        setDimension(0, x);
    }

    /**
     * Get y-coordinate
     *
     * @return value
     */
    public double getY() {
        return getDimension(1);
    }

    /**
     * Set y-coordinate
     *
     * @param y value
     */
    public void setY(double y) {
        setDimension(1, y);
    }
}