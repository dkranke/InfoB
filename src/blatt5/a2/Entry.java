package blatt5.a2;

public class Entry<T> implements Cloneable {

    private T value;
    private Entry<T> previous, next;

    public Entry(T content) {
        this(null, content, null);
    }

    public Entry(Entry<T> previous, T value) {
        this(previous, value, null);
    }

    public Entry(T value, Entry<T> next) {
        this(null, value, next);
    }

    public Entry(Entry<T> previous, T value, Entry<T> next) {
        this.value = value;
        this.next = next;
    }

    public Entry<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Entry<T> previous) {
        this.previous = previous;
    }

    public T getValue() {
        return value;
    }

    public Entry<T> getNext() {
        return next;
    }

    public void setNext(Entry<T> next) {
        this.next = next;
    }

    @Override
    public Entry<T> clone() {
        Entry<T> nxt = next != null ? next.clone() : null;
        return new Entry<T>(previous, value, nxt);
    }
}
