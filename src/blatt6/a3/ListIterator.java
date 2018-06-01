package blatt6.a3;

import java.util.Iterator;

/**
 * Iterator der den Pointer von der Liste durch eine Kopie trennt
 * (Alternative: protected Inhalt)
 *
 * @param <T> Datentyp der Liste
 */
public class ListIterator<T> implements Iterator {

    private List<T> original, copy;

    public ListIterator(List<T> list) {
        this.original = list;
        this.copy = list.clone();
    }

    @Override
    public boolean hasNext() {
        return copy.hasNext();
    }

    @Override
    public T next() {
        return copy.next();
    }

    @Override
    public void remove() {
        original.remove(copy.elem());
        copy.remove();
    }
}
