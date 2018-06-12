package blatt7.a3;

import util.Visitable;
import util.Visitor;

import java.io.File;

public class FileVisitable implements Visitable<File> {

    private File root;
    private boolean recursive;

    public FileVisitable(File root) {
        this(root, false);
    }

    public FileVisitable(File root, boolean recursive) {
        this.root = root;
        this.recursive = recursive;
    }

    @Override
    public void accept(Visitor<File> v) {
        if (root.exists()) {
            if (root.isDirectory()) {
                if (v instanceof FileVisitor) {
                    FileVisitor fv = (FileVisitor) v;
                    for (File file : root.listFiles()) {
                        fv.visit(file);
                        if (file.isDirectory()) {
                            fv.enterFolder();
                            FileVisitable fileVisitable = new FileVisitable(file, recursive);
                            fileVisitable.accept(fv);
                            fv.leaveFolder();
                        }
                    }
                } else {
                    for (File file : root.listFiles()) {
                        v.visit(file);
                        if (file.isDirectory()) {
                            FileVisitable fileVisitable = new FileVisitable(file, recursive);
                            fileVisitable.accept(v);
                        }
                    }
                }
            } else {
                v.visit(root);
            }
        }
    }
}
