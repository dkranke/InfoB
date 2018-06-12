package blatt7.a3;

import util.Visitor;

public interface FileVisitor<T> extends Visitor<T> {

    void enterFolder();

    void leaveFolder();
}
