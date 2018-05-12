package blatt4.a3;

/**
 * Aufgabe 4.3: UML umsetzen
 */

import util.List;

public class Library {

    private List inventory;

    /**
     * Erzeugt eine neue Bibliothek
     */
    public Library() {
        inventory = new List();
    }

    /**
     * Fügt ein neues Objekt der Bibliothek hinzu
     * (jetzt: ohne Duplikate)
     *
     * @param item neues Objekt
     */
    public void addItem(LibraryItem item) {
        // Anti Duplikate
        if (!inventory.empty()) {
            inventory.reset();
            while (!inventory.endpos()) {
                if (item.equals(inventory.elem())) {
                    return;
                }
                inventory.advance();
            }
        }

        inventory.add(item);
    }

    /**
     * Entfernt ein Objekt aus der Bibliothek
     * (jetzt: ohne Duplikate)
     *
     * @param item Zu entfernendes Objekt
     */
    public void deleteItem(LibraryItem item) {
        if (inventory.empty())
            return;

        inventory.reset();
        while (!inventory.endpos()) {
            if (item.equals(inventory.elem())) {
                inventory.delete();
                // Mit Duplikaten
                //return;
            }
            if (!inventory.empty()) {
                inventory.advance();
            }
        }
    }

    /**
     * Sucht nach (einem) Objekt(en) in der Bibliothek
     *
     * @param text Suchparameter
     * @return Liste mit Ergebnissen
     */
    public List search(String text) {
        List result = new List();

        if (inventory.empty()) {
            return result;
        }

        inventory.reset();
        while (!inventory.endpos()) {
            LibraryItem item = (LibraryItem) inventory.elem();
            if (item.getDescription().contains(text)) {
                result.add(item);
            }
            inventory.advance();
        }

        return result;
    }
}
