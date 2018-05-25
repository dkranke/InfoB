package blatt5.a3;

import blatt5.a2.List;
import util.HashFunction;
import util.HashSet;

import java.security.InvalidParameterException;

public class HashList<T> implements HashSet<T> {

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

    public HashList(int count) {
        this(count, DEFAULT_HF);
    }

    public HashList(int count, HashFunction hf) {
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
                if (hf.equals(o, entry.next())) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean containsAny(T... elements) {
        for (T element : elements) {
            if (contains(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(T... elements) {
        for (T element : elements) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
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

    public void insert(T... elements) {
        for (T element : elements) {
            insert(element);
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

    public void delete(T... elements) {
        for (T element : elements) {
            delete(element);
        }
    }

    // TODO
    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < map.length; i++) {
            list += ", " + i + ": " + map[i];
        }
        return this.getClass().getSimpleName() + String.format("{%s}", list.substring(2));
    }
}
