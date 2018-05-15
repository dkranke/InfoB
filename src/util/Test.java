package util;

public class Test {

    public static final int ERR_EQUAL_OBJECT = 1;
    public static final int ERR_UNEQUAL_OBJECT = 2;
    public static final int ERR_EQUAL_HASH = 4;
    public static final int ERR_UNEQUAL_HASH = 8;
    public static final int ERR_EQUAL_STRING = 16;
    public static final int ERR_UNEQUAL_STRING = 32;

    public static final Test STATIC = new Test();
    public boolean total;
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
                System.out.printf(to.stringFormat(), to.toString(a), "==", to.toString(b), to.stringPassed());
            } else {
                int error = 0;
                error += equal ? 0 : ERR_UNEQUAL_OBJECT;
                error += hash ? 0 : ERR_UNEQUAL_HASH;
                System.out.printf(to.stringFormat(), to.toString(a), "==", to.toString(b), to.stringFailed(error));
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
                System.out.printf(to.stringFormat(), to.toString(a), "!=", to.toString(b), to.stringPassed());
            } else {
                int error = 0;
                error += equal ? ERR_EQUAL_OBJECT : 0;
                error += hash ? ERR_EQUAL_HASH : 0;
                System.out.printf(to.stringFormat(), to.toString(a), "!=", to.toString(b), to.stringFailed(error));
            }
        }

        total &= !(equal && hash);
        return !(equal && hash);
    }

    public boolean stringEquals(Object a, String b) {
        boolean equal = a.toString().equals(b);

        if (to.enableOutput()) {
            if (equal) {
                System.out.printf(to.stringFormat(), to.toString(a), "==", to.toString(b), to.stringPassed());
            } else {
                int error = 0;
                error += equal ? 0 : ERR_UNEQUAL_STRING;
                System.out.printf(to.stringFormat(), to.toString(a), "==", to.toString(b), to.stringFailed(error));
            }
        }

        total &= equal;
        return equal;
    }

    public boolean stringUnequals(Object a, Object b) {
        boolean equal = a.toString().equals(b);

        if (to.enableOutput()) {
            if (!equal) {
                System.out.printf(to.stringFormat(), to.toString(a), "!=", to.toString(b), to.stringPassed());
            } else {
                int error = 0;
                error += equal ? ERR_EQUAL_STRING : 0;
                System.out.printf(to.stringFormat(), to.toString(a), "!=", to.toString(b), to.stringFailed(error));
            }
        }

        total &= !equal;
        return !equal;
    }

    public String toString(Object o) {
        return to.toString(o);
    }
}
