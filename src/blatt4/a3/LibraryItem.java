package blatt4.a3;

/**
 * Aufgabe 4.3: UML umsetzen
 */
public abstract class LibraryItem {

    private boolean isBorrowed;

    /**
     * Erzeugt ein neues zu verleihendes Objekt
     */
    public LibraryItem() {
        isBorrowed = false;
    }

    /**
     * Gibt wieder ob das Objekt verliehen wurde
     *
     * @return isBorrowed
     */
    public boolean isBorrowed() {
        return isBorrowed;
    }

    /**
     * Setzt den Wert ob das Objekt verliehen wurde
     *
     * @param borrowed
     */
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    /**
     * Erzeugt eine Beschreibung (abstrakt)
     *
     * @return Beschreibung
     */
    public abstract String getDescription();
}
