package blatt6.a3;

import blatt5.a2.Entry;

import java.util.Iterator;

/**
 * Klonbare (shallow-copy) typensichere Liste mit Iterator-Support :-)
 * Endlich eine (fast) normale Liste.
 *
 * @param <T> Datentyp
 */
public class List<T> implements Cloneable, Iterable<T> {

    private int count = 0;
    private Entry<T> begin, current;

    /**
     * Öffentlicher Konstruktor um eine leere Liste zu erstellen
     */
    public List() {
        this(null, 0);
    }

    /**
     * Privater Konstruktor um eine gefüllte Liste zu erstellen
     *
     * @param begin Erster Eintrag
     */
    private List(Entry<T> begin, int count) {
        this.begin = begin;
    }

    /**
     * Prüft ob die Liste leer ist
     *
     * @return Ergebins
     */
    public boolean empty() {
        return begin == null;
    }

    /**
     * Gibt die Anzahl gespeicherter Elemente zurück
     *
     * @return Anzahl
     */
    public int count() {
        return count;
    }

    /**
     * Prüft ob das Ende der Liste erreicht wurde
     *
     * @return Ergebnis
     */
    public boolean endpos() {
        return current != null && current.getNext() == null;
    }

    /**
     * Gehe zurück zum Anfang der Liste
     */
    public void reset() {
        current = null;
    }

    /**
     * Gehe zum nächsten Element in der Liste
     */
    public void advance() {
        if (current == null) {
            current = begin;
        } else if (hasNext()) {
            current = current.getNext();
        }
    }

    /**
     * Gebe das aktuelle Element aus
     *
     * @return aktuelle Element
     */
    public T elem() {
        return current.getValue();
    }

    /**
     * Füge ein Element zur Liste hinzu
     *
     * @param element neues Element
     */
    public void add(T element) {
        if (empty()) {
            begin = new Entry<T>(element);
        } else if (current != null) {
            Entry<T> entry = new Entry<>(current, element, current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrevious(entry);
            }
            current.setNext(entry);
        } else {
            Entry<T> entry = new Entry<>(element, begin);
            begin.setPrevious(entry);
            begin = entry;
        }
        advance();

        count++;
    }

    /**
     * Füge mehrere Elemente zur Liste hinzu
     *
     * @param elements neue Elemente
     */
    public void add(T... elements) {
        for (T element : elements) {
            add(element);
        }
    }

    /**
     * Entferne das aktuelle Element aus der Liste
     * (Alias von delete)
     */
    public void remove() {
        delete();
    }

    /**
     * Entferne ein Element aus der Liste
     *
     * @param element zu löschendes Element
     */
    public void remove(T element) {
        if (empty()) {
            return;
        } else if (element == current) {
            Entry<T> cur = current;
            delete();
            current = current.getPrevious();
        } else {
            Entry<T> cur = current;
            reset();
            while (hasNext()) {
                T temp = next();
                if (element.equals(temp)) {
                    delete();
                    break;
                }
            }
            current = cur;
        }
    }

    /**
     * Entferne mehrere Elemente aus der Liste
     *
     * @param elements zu löschende Elemente
     */
    public void remove(T... elements) {
        for (T element : elements) {
            remove(element);
        }
    }

    @Override
    public List<T> clone() {
        if (empty()) {
            return new List<T>();
        } else {
            return new List<T>(begin.clone(), count);
        }
    }

    @Override
    public String toString() {
        if (empty()) {
            return this.getClass().getSimpleName() + "{}";
        } else {
            Entry<T> cur = current;
            String text = "";
            reset();
            while (hasNext()) {
                T elem = next();
                if (elem != null) {
                    if (elem instanceof String) {
                        text += ", \"" + elem.toString() + "\"";
                    } else {
                        text += ", " + elem.toString();
                    }
                }
            }
            current = cur;
            return this.getClass().getSimpleName() + String.format("{%s}", text.substring(2));
        }
    }

    // Normal iteration support
    public boolean hasNext() {
        return !empty() && (current == null || current.getNext() != null);
    }

    public T next() {
        if (hasNext()) {
            advance();
            return current.getValue();
        } else {
            return null;
        }
    }

    public void delete() {
        if (current != null) {
            if (current == begin) {
                begin = current.getPrevious();
            }
            if (current.getPrevious() != null) {
                if (current.getNext() != null) {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                } else {
                    current.getPrevious().setNext(null);
                }
                current = current.getPrevious();
            } else {
                if (current.getNext() != null) {
                    current.getNext().setPrevious(null);
                }
                current = null;
            }
            count--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>(this);
    }
}
