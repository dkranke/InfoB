package blatt5.a3;

import blatt5.a2.List;
import util.HashFunction;
import util.HashSet;

import java.security.InvalidParameterException;

public class HashMap<T> implements HashSet<T> {

    private static final HashFunction DEFAULT_HF = new HashFunction() {
        @Override
        public boolean equals(Object o1, Object o2) {
            return o1.equals(o2);
        }

        @Override
        public int hashCode(Object o) {
            return o.hashCode();
        }
    };

    private List<T>[] map;
    private HashFunction hf;

    public HashMap(int count) {
        this(count, DEFAULT_HF);
    }

    public HashMap(int count, HashFunction hf) {
        if (count <= 0) {
            throw new InternalError(new InvalidParameterException("Count must be greater than 0."));
        }

        map = new List[count];
        for (int i = 0; i < map.length; i++) {
            map[i] = new List<T>();
        }

        if (hf != null) {
            this.hf = hf;
        } else {
            this.hf = DEFAULT_HF;
        }
    }

    @Override
    public boolean contains(T o) {
        if (map.length == 0) {
            return false;
        }

        List<T> entry = map[hf.hashCode(o) % map.length];
        if (entry.count() == 0) {
            return false;
        } else {
            entry.reset();
            while (entry.hasNext()) {
                if (o.equals(entry.next())) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean insert(T o) {
        if (!contains(o)) {
            map[o.hashCode() % map.length].add(o);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(T o) {
        if (contains(o)) {
            map[o.hashCode() % map.length].remove(o);
            return true;
        } else {
            return false;
        }
    }

    // TODO
    @Override
    public String toString() {
        return "TODO: HashMap.toString";
    }
}
