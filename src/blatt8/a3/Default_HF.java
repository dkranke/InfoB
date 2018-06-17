package blatt8.a3;

import util.HashFunction;

import java.io.Serializable;

public class Default_HF implements HashFunction, Serializable {

    public static final HashFunction INSTANCE = new Default_HF();

    private Default_HF() {
    }

    @Override
    public boolean equals(Object o1, Object o2) {
        return o1.equals(o2);
    }

    @Override
    public int hashCode(Object o) {
        return o.hashCode();
    }
}
