package util;

/**
 * A <code>HashSet</code> holds any object in a set, i.e. each unique object
 * can only be inserted once. It arranges the objects using the
 * {@link Object#hashCode()} of the inserted objects.
 */
public interface HashSet<T> {

    /**
     * @param o the object that may be contained in this <code>HashSet</code>.
     * @return returns <code>true</code> if the given object is contained in this <code>HashSet</code>,
     * <code>false</code> otherwise
     */
    boolean contains(T o);

    /**
     * @param o inserts the given object into this <code>HashSet</code> if it is
     *          not already contained
     * @return returns <code>true</code> if the object has been inserted and <code>false</code>
     * if it already is contained
     */
    boolean insert(T o);

    /**
     * @param o deletes the given object from this <code>HashSet</code> if it is contained
     * @return returns <code>true</code> if the object has been deleted and <code>false</code>
     * if the object has not be contained
     */
    boolean delete(T o);

}
