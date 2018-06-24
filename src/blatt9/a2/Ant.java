package blatt9.a2;

import blatt9.a2.AntField.Field;

/**
 * An {@code Ant} is created at a specific position of an {@link AntField} with
 * an initial {@code stepCount}. When running an Ant, it will lookup the values
 * on the current and all surrounding {@link Field}
 * (Moore-neighborhood) instances and test if the position is free, i.e. has a
 * value of {@code 0}, or if the value is greater than the {@code stepCount} of
 * this Ant. For both cases, the Ant will set the value of the {@code Field} at
 * the visited position to its own {@code stepCount+1}. After an {@code Ant} has
 * successfully visited one field, it will create new {@code Ant} instances with
 * an incremented {@code stepCount} to visit the other available {@code Field}
 * elements. The Ant will run until it finds no more {@code Field} elements in
 * its neighborhood to be altered.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Ant implements Runnable {

    private AntField fields;
    private int x, y, stepCount;

    /**
     * @param fields    the {@code AntField} on which this {@code Ant} operates
     * @param x         x-axis value of the starting position
     * @param y         y-axis value of the starting position
     * @param stepCount initial stepCount of this {@code Ant}.
     * @throws IllegalArgumentException If the {@code Field} at position {@code x,y} does not exist, or
     *                                  if its value is < 0
     */
    public Ant(AntField fields, int x, int y, int stepCount) {
        this.fields = fields;
        this.x = x;
        this.y = y;
        this.stepCount = stepCount;
    }

    public void run() {
        Field field = fields.getField(x, y);
        if (field != null) {
            if (field.getValue() == 0 || field.getValue() > stepCount) {
                field.setValue(stepCount);

                ThreadGroup tg = new ThreadGroup("antX" + x + "Y" + y);
                int newX = x, newY = y;
                for (int ry = -1; ry <= 1; ry++) {
                    for (int rx = -1; rx <= 1; rx++) {
                        if (rx == 0 && ry == 0) {
                            continue;
                        }

                        field = fields.getField(x + rx, y + ry);
                        if (field != null) {
                            if (field.getValue() == 0 || field.getValue() > stepCount + 1) {
                                if (newX == x && newY == y) {
                                    newX += rx;
                                    newY += ry;
                                } else {
                                    Ant ant = new Ant(fields, x + rx, y + ry, stepCount + 1);
                                    Thread t = new Thread(tg, ant);
                                    t.start();
                                }
                            }
                        }
                    }
                }

                if (newX != x || newY != y) {
                    x = newX;
                    y = newY;
                    stepCount++;
                    run();
                }

                while (tg.activeCount() > 0) {
                    Thread.yield();
                }
                tg.destroy();
            }
        }
    }

}
