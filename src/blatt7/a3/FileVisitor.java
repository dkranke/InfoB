package blatt7.a3;

import util.Visitor;

import java.io.File;

public interface FileVisitor extends Visitor<File> {

    void enterFolder();

    void leaveFolder();
}
