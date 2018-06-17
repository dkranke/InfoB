package blatt8.a4;

import blatt7.a3.FileVisitable;
import util.Visitor;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search implements Visitor<File> {
    private static boolean showSubDirs = false;
    private static String path;

    private static Pattern pattern;

    public static void main(String[] args) {
        if (init(args)) {
            File root = new File(path);
            if (root.exists()) {
                FileVisitable fv = new FileVisitable(root, showSubDirs);
                fv.accept(new Search());
            } else {
                System.out.println("Error: Path does not exist");
            }
        }
    }

    /**
     * reading arguments [-r] [path]
     *
     * @param args main args
     */
    private static boolean init(String[] args) {
        if (args.length == 1) {
            if (args[0].equals("-r")) {
                showSubDirs = true;
            } else if (!args[0].equals("-p")) {
                path = args[0];
            }
        } else if (args.length == 2) {
            if (args[0].equals("-r")) {
                showSubDirs = true;
                if (!args[1].equals("-p")) {
                    path = args[1];
                } else {
                    System.out.println("Usage: java Search [-r]  [-p Pattern] [path]");
                    return false;
                }
            } else if (args[0].equals("-p")) {
                pattern = Pattern.compile(args[1]);
            } else {
                System.out.println("Usage: java Search [-r]  [-p Pattern] [path]");
                return false;
            }
        } else if (args.length == 3) {
            if (args[0].equals("-r")) {
                showSubDirs = true;
                if (args[1].equals("-p")) {
                    pattern = Pattern.compile(args[2]);
                } else {
                    System.out.println("Usage: java Search [-r]  [-p Pattern] [path]");
                    return false;
                }
            } else if (args[0].equals("-p")) {
                pattern = Pattern.compile(args[1]);
                path = args[2];
            } else {
                System.out.println("Usage: java Search [-r]  [-p Pattern] [path]");
                return false;
            }
        } else if (args.length == 4) {
            if (args[0].equals("-r") && args[1].equals("-p")) {
                showSubDirs = true;
                pattern = Pattern.compile(args[2]);
                path = args[3];
            } else {
                System.out.println("Usage: java Search [-r]  [-p Pattern] [path]");
                return false;
            }
        } else {
            System.out.println("Usage: java Search [-r]  [-p Pattern] [path]");
            return false;
        }

        if (pattern == null) {
            pattern = Pattern.compile(".*");
        }

        if (path == null) {
            path = System.getProperty("user.dir");
        }

        return true;
    }

    @Override
    public boolean visit(File o) {
        Matcher matcher = pattern.matcher(o.getName());

        if (matcher.find()) {
            System.out.println(o.getAbsolutePath());
        }

        return true;
    }
}
