package blatt7.a2;

import util.Visitable;
import util.Visitor;

public class VisitableList<T> extends blatt6.a3.List<T> implements Visitable<T> {

    @Override
    public void accept(Visitor<T> v) {
        reset();
        while (hasNext()) {
            T elem = next();
            if (!v.visit(elem)) {
                return;
            }
        }
    }
}
