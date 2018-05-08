package blatt2.a1;

public class Fibonacci {
    private int number;
    private Fibonacci previous;
    private Fibonacci next;

    /**
     * Initializes a first Element of the Fibonacci sequence, starting with 0.
     */
    public Fibonacci() {
        number = 0;
    }

    /**
     * Initializes a new Fibonacci number as a following Element in the sequence.
     *
     * @param previous Fibonacci object to continue from
     */
    private Fibonacci(Fibonacci previous) {
        this.previous = previous;
        if (previous.number == 0) {
            this.number = 1;
        } else {
            this.number = previous.number + previous.previous.number;
        }
    }

    /**
     * Next Finbonacci number in the sequence
     *
     * @return Next Fibonacci object
     */
    public Fibonacci next() {
        if (next == null) {
            next = new Fibonacci(this);
        }
        return next;
    }

    /**
     * Numerical value
     *
     * @return Integer value of this Fibonacci object
     */
    public int getNumber() {
        return number;
    }
}
