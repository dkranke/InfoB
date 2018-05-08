package blatt3.a3;

public abstract class Geometry {
    private int dimension;

    public Geometry(int n) {
        if (n < 2) {
            throw new RuntimeException("dimension is < 2");
        }
        this.dimension = n;
    }

    public int dimensions() {
        return this.dimension;
    }

    public abstract double volume();

    public abstract Geometry encapsulate(Geometry var1);
}
