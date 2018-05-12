package blatt4.a3;

import util.List;
import util.Test;

public class LibraryTest extends Test {

    public static void main(String[] args) {
        Library lib = new Library();

        BluRay bluRay = new BluRay("Blue", "Ray");
        Book book = new Book("Book", "Author");

        System.out.println("Add");
        System.out.println(" - Before: " + listLib(lib.search("")));
        lib.addItem(bluRay);
        lib.addItem(book);
        System.out.println(" - After: " + listLib(lib.search("")));

        System.out.println("Search");
        System.out.println(" - Book: " + listLib(lib.search("Book")));
        System.out.println(" - Ray: " + listLib(lib.search("Ray")));
        System.out.println(" - \"\": " + listLib(lib.search("")));
        System.out.println(" - Item: " + listLib(lib.search("item")));


        System.out.println("Remove");
        System.out.println(" - Before: " + listLib(lib.search("")));
        lib.deleteItem(bluRay);
        lib.deleteItem(book);
        System.out.println(" - After: " + listLib(lib.search("")));

    }

    public static String listLib(List list) {
        if (list.empty()) {
            return "[]";
        } else {
            String str = "";
            list.reset();
            while (!list.endpos()) {
                LibraryItem elem = (LibraryItem) list.elem();
                str += ", " + elem.getDescription();
                list.advance();
            }
            return "[" + str.substring(2) + "]";
        }
    }
}
