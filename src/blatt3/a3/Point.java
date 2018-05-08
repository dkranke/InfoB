package blatt3.a3;

import java.util.Arrays;

/**
 * Aufgabe 3.3: Point
 */
public class Point extends Geometry implements Comparable {
    private double coords[];

    /**
     * Creates a empty point
     *
     * @param dimensions Amount of dimensions
     * @return new Point
     */
    public Point(int dimensions) {
        super(dimensions);
        coords = new double[dimensions];
    }

    /**
     * Creates a point from a array of coordinations
     *
     * @param coords double array
     * @return new Point
     */
    public Point(double... coords) {
        super(coords.length);
        this.coords = coords;
    }

    /**
     * Get the volume of the point
     *
     * @return 0 a point has no volume...
     */
    public double volume() {
        return 0;
    }

    /**
     * Get the coordinates
     *
     * @return array of coordinates
     */
    public double[] getCoords() {
        return coords;
    }

    /**
     * Set the coordinates
     *
     * @param point array of coordinates
     */
    public void setCoords(double[] point) {
        if (point.length == dimensions()) {
            coords = point;
        }
    }

    /**
     * Get only a single dimension of the coordinates
     *
     * @param dimension selected dimension
     * @return selected dimension
     */
    public double getDimension(int dimension) {
        return coords[dimension];
    }

    /**
     * Set a single dimension of the coordinates
     *
     * @param dimension selected dimension
     * @param value     value of dimension
     */
    public void setDimension(int dimension, double value) {
        if (dimension < dimensions()) {
            coords[dimension] = value;
        }
    }

    /**
     * Encapsulate this point with another geometry
     *
     * @param other any geometry
     * @return new volume of the encapsulated geometries
     */
    public Geometry encapsulate(Geometry other) {
        if (dimensions() != other.dimensions()) {
            return null;
        }

        if (other instanceof Volume) {
            Point p1 = new Point(dimensions());
            Point p2 = new Point(other.dimensions());

            for (int i = 0; i < dimensions(); i++) {
                double otherMin = Math.min(((Volume) other).getMinDimension(i), ((Volume) other).getMaxDimension(i));
                double otherMax = Math.max(((Volume) other).getMinDimension(i), ((Volume) other).getMaxDimension(i));

                p1.setDimension(i, Math.min(getDimension(i), otherMin));
                p2.setDimension(i, Math.max(getDimension(i), otherMax));
            }

            return new Volume(p1, p2);
        } else {
            return new Volume(this, ((Point) other));
        }
    }

    /**
     * Compare the volumes of geometries
     *
     * @param o Any object
     * @return -1 if this object is smaller, 1 if bigger, 0 if same or not comparable
     */
    public int compareTo(Object o) {
        if (o instanceof Geometry) {
            if (volume() == ((Geometry) o).volume()) {
                return 0;
            } else {
                return volume() < ((Geometry) o).volume() ? -1 : 1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(coords);
    }
}