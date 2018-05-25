package util;

import java.util.function.Consumer;

public class Test {

    public static final int ERR_EQUAL_OBJECT = 1;
    public static final int ERR_UNEQUAL_OBJECT = 2;
    public static final int ERR_EQUAL_HASH = 4;
    public static final int ERR_UNEQUAL_HASH = 8;
    public static final int ERR_EQUAL_STRING = 16;
    public static final int ERR_UNEQUAL_STRING = 32;

    public static final Test STATIC = new Test();
    private boolean total, labeler;
    private TestOutput to;

    public Test() {
        this(new TestOutput() {
        });
    }

    public Test(TestOutput to) {
        this.to = to;
        this.total = true;
    }

    public void resetOutput() {
        to = new TestOutput() {
        };
    }

    public void resetTotal() {
        total = true;
    }

    public void addTotal(boolean value) {
        total &= value;
    }

    public boolean getTotal() {
        return total;
    }

    public void printTotal() {
        if (labeler) {
            System.out.println();
        }
        System.out.println("Total: " + (total ? to.stringPassed() : to.stringFailed(0)));
    }

    public TestOutput getOutput() {
        return to;
    }

    public void setOutput(TestOutput nv) {
        to = nv;
    }

    public boolean assertEquals(Object a, Object b) {
        boolean equal = a.equals(b);
        boolean hash = a.hashCode() == b.hashCode();

        if (to.enableOutput()) {
            if (equal && hash) {
                System.out.printf(labeler ? to.labelStringFormat() : to.stringFormat(), to.toString(a), "==", to.toString(b), to.stringPassed());
            } else {
                int error = 0;
                error += equal ? 0 : ERR_UNEQUAL_OBJECT;
                error += hash ? 0 : ERR_UNEQUAL_HASH;
                System.out.printf(labeler ? to.labelStringFormat() : to.stringFormat(), to.toString(a), "==", to.toString(b), to.stringFailed(error));
            }
        }

        total &= equal && hash;
        return equal && hash;
    }

    public boolean assertUnequals(Object a, Object b) {
        boolean equal = a.equals(b);
        boolean hash = a.hashCode() == b.hashCode();

        if (to.enableOutput()) {
            if (!(equal && hash)) {
                System.out.printf(labeler ? to.labelStringFormat() : to.stringFormat(), to.toString(a), "!=", to.toString(b), to.stringPassed());
            } else {
                int error = 0;
                error += equal ? ERR_EQUAL_OBJECT : 0;
                error += hash ? ERR_EQUAL_HASH : 0;
                System.out.printf(labeler ? to.labelStringFormat() : to.stringFormat(), to.toString(a), "!=", to.toString(b), to.stringFailed(error));
            }
        }

        total &= !(equal && hash);
        return !(equal && hash);
    }

    public boolean stringEquals(Object a, String b) {
        boolean equal = a.toString().equals(b);

        if (to.enableOutput()) {
            if (equal) {
                System.out.printf(labeler ? to.labelStringFormat() : to.stringFormat(), to.toString(a.toString()), "==", to.toString(b), to.stringPassed());
            } else {
                int error = 0;
                error += equal ? 0 : ERR_UNEQUAL_STRING;
                System.out.printf(labeler ? to.labelStringFormat() : to.stringFormat(), to.toString(a.toString()), "==", to.toString(b), to.stringFailed(error));
            }
        }

        total &= equal;
        return equal;
    }

    public boolean stringUnequals(Object a, String b) {
        boolean equal = a.toString().equals(b);

        if (to.enableOutput()) {
            if (!equal) {
                System.out.printf(labeler ? to.labelStringFormat() : to.stringFormat(), to.toString(a.toString()), "!=", to.toString(b), to.stringPassed());
            } else {
                int error = 0;
                error += equal ? ERR_EQUAL_STRING : 0;
                System.out.printf(labeler ? to.labelStringFormat() : to.stringFormat(), to.toString(a.toString()), "!=", to.toString(b), to.stringFailed(error));
            }
        }

        total &= !equal;
        return !equal;
    }

    public long speedTestMs(Runnable test) {
        long time = -System.currentTimeMillis();
        test.run();
        time += System.currentTimeMillis();
        return time;
    }

    public double speedTestMs(Runnable test, int count) {
        double average = 0;
        for (int i = 0; i < count; i++) {
            average += speedTestMs(test);
        }
        average /= (double) count;
        return average;
    }

    public long speedTestNs(Runnable test) {
        long time = -System.nanoTime();
        test.run();
        time += System.nanoTime();
        return time;
    }

    public double speedTestNs(Runnable test, int count) {
        double average = 0;
        for (int i = 0; i < count; i++) {
            average += speedTestNs(test);
        }
        average /= (double) count;
        return average;
    }

    public double compareSpeedMs(Runnable test1, Runnable test2) {
        return speedTestMs(test1) - speedTestMs(test2);
    }

    public double compareSpeedNs(Runnable test1, Runnable test2) {
        return speedTestNs(test1) - speedTestNs(test2);
    }

    public double compareSpeedMs(Runnable test1, Runnable test2, int count) {
        return speedTestMs(test1, count) - speedTestMs(test2, count);
    }

    public double compareSpeedNs(Runnable test1, Runnable test2, int count) {
        return speedTestNs(test1, count) - speedTestNs(test2, count);
    }

    public <T> double compareSpeedMs(T parameter, Consumer<T> test1, Consumer<T> test2) {
        Runnable run1 = () -> test1.accept(parameter);
        Runnable run2 = () -> test2.accept(parameter);
        return speedTestMs(run1) - speedTestMs(run2);
    }

    public <T> double compareSpeedNs(T parameter, Consumer<T> test1, Consumer<T> test2) {
        Runnable run1 = () -> test1.accept(parameter);
        Runnable run2 = () -> test2.accept(parameter);
        return speedTestNs(run1) - speedTestNs(run2);
    }

    public <T> double compareListSpeedMs(T[] parameters, Consumer<T> test1, Consumer<T> test2) {
        double total = 0;
        for (T parameter : parameters) {
            total += compareSpeedMs(parameter, test1, test2);
        }
        return total;
    }

    public <T> double compareListSpeedNs(T[] parameters, Consumer<T> test1, Consumer<T> test2) {
        double total = 0;
        for (T parameter : parameters) {
            total += compareSpeedNs(parameter, test1, test2);
        }
        return total;
    }

    public void label(String label) {
        if (labeler) {
            System.out.println();
        } else {
            labeler = true;
        }

        System.out.printf(to.labelFormat(), label);
    }

    public String toString(Object o) {
        return to.toString(o);
    }
}
