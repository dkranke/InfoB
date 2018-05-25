package blatt5.a2;

/**
 * Ein typensicherer Listeneintrag mit Unterstützung für Vorwärts- und Rückwärts-Iterierung
 *
 * @param <T> Datentyp für die Typensicherheit
 */
public class Entry<T> implements Cloneable {

    private T value;
    private Entry<T> previous, next;

    /**
     * Standard-Konstruktor um alle Attribute zu setzen
     *
     * @param previous vorheriger Eintrag
     * @param value    Wert dieses Eintrages
     * @param next     nächster Eintrag
     */
    public Entry(Entry<T> previous, T value, Entry<T> next) {
        this.previous = previous;
        this.value = value;
        this.next = next;
    }

    // Vereinfachte Konstruktoren mit Standartwerten (null)
    public Entry(T content) {
        this(null, content, null);
    }

    public Entry(Entry<T> previous, T value) {
        this(previous, value, null);
    }

    public Entry(T value, Entry<T> next) {
        this(null, value, next);
    }

    // Getter
    public Entry<T> getPrevious() {
        return previous;
    }

    public T getValue() {
        return value;
    }

    public Entry<T> getNext() {
        return next;
    }

    // Setter
    public void setPrevious(Entry<T> previous) {
        this.previous = previous;
    }

    public void setNext(Entry<T> next) {
        this.next = next;
    }

    /**
     * Methode zum Erstellen einer Shadow-Copy (Deep-Copy geht wegen der Typensicherung nicht)
     * @return Klon vom Eintrag
     */
    @Override
    public Entry<T> clone() {
        Entry<T> nxt = next != null ? next.clone() : null;
        return new Entry<T>(previous, value, nxt);
    }
}
