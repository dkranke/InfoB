package blatt2.a2;

public class Arena {
    /**
     * Berechne den Bereich in dem sich ein Tribut befindet
     *
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return Index des Bereiches oder -1
     */
    public int getArea(double x, double y) {
        // Falls der Radius größer als 3 (Meilen) ist gebe -1 zurück
        if (Math.abs(x) > 1.5 || Math.abs(y) > 1.5 || Math.sqrt(x * x + y * y) > 1.5) {
            return -1;
        }
        // Falls die Koordinate (0,0) ist, also in der Mitte der Arena gebe 0 zurück
        else if (x == 0 && y == 0) {
            return 0;
        }

        // Sonst berechne den Bereich
        return (int) (Math.toDegrees(2.5 * Math.PI - Math.atan2(x, y)) % 360) / 30 + 1;
    }
}