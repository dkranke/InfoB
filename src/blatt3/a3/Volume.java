package blatt3.a3;

import java.util.Arrays;

/**
 * Aufgabe 3.3: Volume
 */
public class Volume extends Geometry implements Comparable {
    private Point minPos, maxPos;

    /**
     * Create a new volume with 2 Points as input
     *
     * @param pos1 First Point
     * @param pos2 Second Point
     * @return new Volume
     */
    public Volume(Point pos1, Point pos2) {
        super(pos1.dimensions());

        minPos = new Point(pos1.dimensions());
        maxPos = new Point(pos2.dimensions());

        for (int i = 0; i < dimensions(); i++) {
            minPos.setDimension(i, Math.min(pos1.getDimension(i), pos2.getDimension(i)));
            maxPos.setDimension(i, Math.max(pos1.getDimension(i), pos2.getDimension(i)));
        }
    }

    /**
     * Get top-left (minimal) position of the volume
     *
     * @return array of coordinates
     */
    public double[] getMinPos() {
        return minPos.getCoords();
    }

    /**
     * Get a single dimension of the top-left (minimal) position
     *
     * @param dimension selected dimension
     * @return value of the selected position
     */
    public double getMinDimension(int dimension) {
        return minPos.getDimension(dimension);
    }

    /**
     * Get bottom-right (maximal) position of the volume
     *
     * @return array of coordinates
     */
    public double[] getMaxPos() {
        return maxPos.getCoords();
    }

    /**
     * Get a single dimension of the bottom-right (maximal) position
     *
     * @param dimension selected dimension
     * @return value of the selected position
     */
    public double getMaxDimension(int dimension) {
        return maxPos.getDimension(dimension);
    }

    /**
     * Calculate the volume
     *
     * @return volume
     */
    public double volume() {
        double result = 1;
        for (int i = 0; i < this.dimensions(); i++) {
            result *= Math.abs(maxPos.getCoords()[i] - minPos.getCoords()[i]);
        }
        return result;
    }

    /**
     * Encapsulate this volume with another geometry
     *
     * @param other any geometry
     * @return new volume of the encapsulated geometries
     */
    public Geometry encapsulate(Geometry other) {
        if (dimensions() != other.dimensions()) {
            return null;
        }

        if (other instanceof Volume) {
            Point p1 = new Point(minPos.dimensions());
            Point p2 = new Point(maxPos.dimensions());

            for (int i = 0; i < dimensions(); i++) {
                double min = Math.min(getMinDimension(i), getMaxDimension(i));
                double max = Math.max(getMinDimension(i), getMaxDimension(i));
                double otherMin = Math.min(((Volume) other).getMinDimension(i), ((Volume) other).getMaxDimension(i));
                double otherMax = Math.max(((Volume) other).getMinDimension(i), ((Volume) other).getMaxDimension(i));

                p1.setDimension(i, Math.min(min, otherMin));
                p2.setDimension(i, Math.max(max, otherMax));
            }

            return new Volume(p1, p2);
        } else {
            Point p1 = new Point(minPos.dimensions());
            Point p2 = new Point(maxPos.dimensions());

            for (int i = 0; i < dimensions(); i++) {
                double min = Math.min(getMinDimension(i), ((Point) other).getDimension(i));
                double max = Math.max(getMinDimension(i), ((Point) other).getDimension(i));

                p1.setDimension(i, Math.min(min, ((Point) other).getDimension(i)));
                p2.setDimension(i, Math.max(max, ((Point) other).getDimension(i)));
            }

            return new Volume(p1, p2);
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
        return this.getClass().getSimpleName() + Arrays.toString(minPos.getCoords()).replace("]", "; ") + Arrays.toString(maxPos.getCoords()).replace("[", "");
    }
}