package blatt4.a3;

/**
 * Aufgabe 4.3: UML umsetzen
 */
public class BluRay extends LibraryItem {

    private String title;
    private String director;

    /**
     * Konstruktor: Erstellt eine BluRay aus einem Titel und einem Direktor
     *
     * @param title    BluRay Titel
     * @param director Direktor Name
     */
    public BluRay(String title, String director) {
        super();
        this.title = title;
        this.director = director;
    }

    /**
     * Gibt den Titel wieder
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gibt den Direktor wieder
     *
     * @return
     */
    public String getDirector() {
        return director;
    }

    /**
     * Erzeugt eine Berschreibung aus dem Titel und dem Direktor
     *
     * @return Beschreibung
     */
    @Override
    public String getDescription() {
        return title + " by " + director;
    }
}
