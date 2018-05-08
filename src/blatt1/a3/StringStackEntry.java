/**
 * Represents an entry of a <code>StringStack</code> which holds a String and a
 * reference to the next StringStackEntry.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
class StringStackEntry {

    /**
     * The next StringStackEntry
     */
    private StringStackEntry next;
    /**
     * The String which this StringStackEntry represents
     */
    private String s;

    /**
     * @param s String in Stack which is represented by this StringStackEntry
     */
    StringStackEntry(String s) {
        this(s, null);
    }

    // Copy-Konstruktor (deep copy)
    StringStackEntry(StringStackEntry entry) {
        this.s = new String(entry.getString());

        // Falls ein nächstes Element existiert kopiere es, sonst setze es zu null
        if (entry.getNext() != null) {
            this.next = new StringStackEntry(entry.getNext());
        } else {
            this.next = null;
        }
    }

    /**
     * @param s    String in Stack which is represented by this StringStackEntry
     * @param next the StringStackEntry next will be visible if this
     *             StringStackEntry is removed from the stack.
     */
    StringStackEntry(String s, StringStackEntry next) {
        this.s = s;
        this.next = next;
    }

    StringStackEntry getNext() {
        return this.next;
    }

    String getString() {
        return this.s;
    }
}
