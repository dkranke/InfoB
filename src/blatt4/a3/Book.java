package blatt4.a3;

/**
 * Aufgabe 4.3: UML umsetzen
 */
public class Book extends LibraryItem {

    private String title;
    private String author;

    /**
     * Konstruktor: Erstellt ein Buch aus einem Titel und einem Author
     *
     * @param title  Titel des Buches
     * @param author Author des Buches
     */
    public Book(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    /**
     * Gibt den Titel wieder
     *
     * @return titel
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gibt den Author wieder
     *
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Erzeugt eine Berschreibung aus dem Titel und dem Author
     *
     * @return Beschreibung
     */
    @Override
    public String getDescription() {
        return title + " by " + author + (isBorrowed() ? " (Borrowed)" : "");
    }
}
