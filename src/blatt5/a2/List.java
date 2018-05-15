package blatt5.a2;

import java.util.Iterator;

/**
 * Klonbare (shallow-copy) typensichere Liste mit Iterator-Support :-)
 * Endlich eine Liste die sich normal verhält.
 *
 * @param <T> Datentyp
 */
public class List<T> implements Cloneable, Iterator<T>, Iterable<T> {

    private Entry<T> begin, current;

    public List() {
        this(null);
    }

    private List(Entry<T> begin) {
        this.begin = begin;
    }

    public boolean empty() {
        return begin == null;
    }

    public boolean endpos() {
        return current != null && current.getNext() == null;
    }

    public void reset() {
        current = null;
    }

    public void advance() {
        if (current == null) {
            current = begin;
        } else if (hasNext()) {
            current = current.getNext();
        }
    }

    public T elem() {
        return current.getValue();
    }

    public void add(T element) {
        if (empty()) {
            begin = new Entry<T>(element);
        } else if (current != null) {
            Entry<T> entry = new Entry<>(current, element, current.getNext());
            current.setNext(entry);
        } else {
            Entry<T> entry = new Entry<>(element, begin);
            begin = entry;
        }
        advance();
    }

    public void add(T... elements) {
        for (T element : elements) {
            add(element);
        }
    }

    public void remove(T element) {
        if (empty()) {
            return;
        } else {
            Entry<T> cur = current;
            reset();
            while (hasNext()) {
                T temp = next();
                if (element.equals(temp)) {
                    remove();
                    break;
                }
            }
            current = cur;
        }
    }

    public void remove(T... elements) {
        for (T element : elements) {
            remove(element);
        }
    }

    @Override
    public List<T> clone() {
        return new List<T>(begin.clone());
    }

    @Override
    public boolean hasNext() {
        return !empty() && (current == null || current.getNext() != null);
    }

    @Override
    public T next() {
        if (hasNext()) {
            advance();
            return current.getValue();
        } else {
            return null;
        }
    }

    @Override
    public void remove() {
        if (current != null) {
            if (current == begin) {
                begin = current.getNext();
            }
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            current = current.getPrevious();
        }
    }

    @Override
    public String toString() {
        if (empty()) {
            return this.getClass().getSimpleName() + "[]";
        } else {
            Entry<T> cur = current;
            String text = "";
            reset();
            while (hasNext()) {
                text += ", " + next().toString();
            }
            current = cur;
            return this.getClass().getSimpleName() + String.format("[%s]", text.substring(2));
        }
    }

    @Override
    public Iterator<T> iterator() {
        reset();
        return this;
    }
}
