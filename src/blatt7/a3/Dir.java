package blatt7.a3;

import java.io.File;

public class Dir implements FileVisitor {
    private static boolean showSubDirs = false;
    private static String path;

    private int count = 0;

    public static void main(String[] args) {
        init(args);
        File root = new File(path);

        FileVisitable fv = new FileVisitable(root, showSubDirs);
        fv.accept(new Dir());
    }

    /**
     * reading arguments [-r] [path]
     *
     * @param args main args
     */
    private static void init(String[] args) {
        if (args.length > 0 && args.length < 3) {
            showSubDirs = args[0].equals("-r");
            if (args.length == 2) {
                path = args[1];
            }
        } else if (args.length >= 3) {
            System.out.println("Usage: java Dir [-r] [path]");
        }
        if (path == null) {
            path = System.getProperty("user.dir");
            //path = Dir.class.getResource("Dir.class").getPath();
        }
    }

    @Override
    public void enterFolder() {
        count++;
    }

    @Override
    public void leaveFolder() {
        count--;
    }

    @Override
    public boolean visit(File o) {
        for (int i = 0; i < count; i++) {
            System.out.print("  ");
        }
        System.out.println(o.getName());
        return true;
    }
}
