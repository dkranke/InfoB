package util;

/**
 * The definition of a hash function different from the default
 * {@link Object#hashCode()} and {@link Object#equals} methods.
 *
 * @param <T> type this function is defined on
 */
public interface HashFunction<T> {

    /**
     * Defines the equality of two objects as defined by {@link Object#equals(Object)}
     * but in relation to the {@link HashFunction#hashCode(Object)} method in this
     * <code>HashFunction</code>.
     */
    boolean equals(T o1, T o2);

    /**
     * Defines the hash code of an object as defined by {@link Object#hashCode()}
     * but in relation to the {@link HashFunction#equals(Object, Object)} method in
     * this <code>HashFunctions</code>.
     */
    int hashCode(T o);

}
