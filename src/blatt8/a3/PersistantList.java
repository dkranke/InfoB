package blatt8.a3;

import blatt6.a3.List;
import blatt6.a3.ListIterator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersistantList<T> extends List<T> implements Serializable {

    private void writeObject(ObjectOutputStream out) throws IOException {
        // Standard Serialisierungskram
        out.defaultWriteObject();

        // Anzahl der Objekte speichern
        out.writeInt(this.count());

        // Die Objekte speichern
        ListIterator<T> iter = this.iterator();
        while (iter.hasNext()) {
            out.writeObject(iter.next());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Standard Serialisierungskram
        in.defaultReadObject();

        // Lese die Anzahl der Objekte
        int size = in.readInt();

        // Die Objekte laden
        for (int i = 0; i < size; i++) {
            add((T) in.readObject());
        }
    }
}
