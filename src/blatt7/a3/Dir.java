package blatt7.a3;

import java.io.File;
import java.util.regex.Pattern;

public class Dir {
    private static boolean showSubDirs = false;
    private static String path;

    public static void main(String[] args) {
        init(args);
        File root = new File(path);

    }

    /**
     * reading arguments [-r] [path]
     *
     * @param args main args
     */
    private static void init(String[] args) {
        if (args.length > 0) {
            Pattern pSubdir = Pattern.compile("-[rR]");
            if (pSubdir.matcher(args[0]).matches()) {
                showSubDirs = true;
                if (args.length > 1) {
                    path = args[1];
                }
            } else if (args.length > 1 && pSubdir.matcher(args[1]).matches()) {
                showSubDirs = true;
                path = args[0];
            } else {
                path = args[0];
            }
        }
        if (path == null) {
            path = Dir.class.getResource("Dir.class").getPath();
        }
    }
}
