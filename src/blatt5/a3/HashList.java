package blatt5.a3;

import blatt5.a2.List;
import util.HashFunction;
import util.HashSet;

import java.security.InvalidParameterException;

public class HashList<T> implements HashSet<T> {

    /**
     * Standard HashFunction-Methoden
     */
    private static final HashFunction DEFAULT_HF = new HashFunction() {
        @Override
        public boolean equals(Object o1, Object o2) {
            return o1.equals(o2);
        }

        @Override
        public int hashCode(Object o) {
            return o.hashCode();
        }
    };

    private List<T>[] map;
    private HashFunction<T> hf;

    /**
     * Konstruktor für den Pflichtparameter
     *
     * @param count Anzahl der Elemente
     */
    public HashList(int count) {
        this(count, null);
    }

    /**
     * Konkstruktor mit Optionaler Hashfunction
     *
     * @param count Anzahl der Elemente
     * @param hf    optionale HashFunction
     */
    public HashList(int count, HashFunction<T> hf) {
        if (count <= 0) {
            throw new InternalError(new InvalidParameterException("Count must be greater than 0."));
        } else if (hf == null) {
            hf = DEFAULT_HF;
        }

        map = new List[count];
        for (int i = 0; i < map.length; i++) {
            map[i] = new List<T>();
        }

        if (hf != null) {
            this.hf = hf;
        } else {
            this.hf = DEFAULT_HF;
        }
    }

    /**
     * @param o the object that may be contained in this <code>HashSet</code>.
     * @return
     */
    @Override
    public boolean contains(T o) {
        if (map.length == 0) {
            return false;
        }

        List<T> entry = map[hf.hashCode(o) % map.length];
        if (entry.empty()) {
            return false;
        } else {
            entry.reset();
            while (entry.hasNext()) {
                if (hf.equals(o, entry.next())) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Prüft ob mindestens eins von mehreren Elementen in der Liste vorhanden ist
     * @param elements Liste von Elementen
     * @return Ergebnis
     */
    public boolean containsAny(T... elements) {
        for (T element : elements) {
            if (contains(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prüft ob alle Elemente in der Liste vorhanden sind
     * @param elements Liste von Elementen
     * @return Ergebnis
     */
    public boolean containsAll(T... elements) {
        for (T element : elements) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param o inserts the given object into this <code>HashSet</code> if it is
     *          not already contained
     * @return
     */
    @Override
    public boolean insert(T o) {
        if (!contains(o)) {
            map[o.hashCode() % map.length].add(o);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fügt eine Liste von Elementen der HashList hinzu
     * @param elements Liste von Elementen
     */
    public void insert(T... elements) {
        for (T element : elements) {
            insert(element);
        }
    }

    /**
     * @param o deletes the given object from this <code>HashSet</code> if it is contained
     * @return
     */
    @Override
    public boolean delete(T o) {
        if (contains(o)) {
            map[o.hashCode() % map.length].remove(o);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Entfernt eine Liste von Elementen aus der HashList
     * @param elements Liste von Elementen
     */
    public void delete(T... elements) {
        for (T element : elements) {
            delete(element);
        }
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < map.length; i++) {
            list += ", " + i + ": " + map[i];
        }
        return this.getClass().getSimpleName() + String.format("{%s}", list.substring(2));
    }
}
