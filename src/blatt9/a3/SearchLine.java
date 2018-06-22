package blatt9.a3;

import io.FileSystem;

import java.io.File;

public class SearchLine {

    private static boolean recursive = false;
    private static String path, pattern;

    public static void main(String[] args) {
        if (init(args)) {
            File root = new File(path);
            if (root.exists()) {
                FileSystem fs = new FileSystem(root);
                SearchLineVisitor slv = new SearchLineVisitor(pattern, recursive);
                fs.accept(slv);
                slv.awaitTermination();
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
                recursive = true;
            } else if (!args[0].equals("-p")) {
                path = args[0];
            }
        } else if (args.length == 2) {
            if (args[0].equals("-r")) {
                recursive = true;
                if (!args[1].equals("-p")) {
                    path = args[1];
                } else {
                    System.out.println("Usage: java SearchLine [-r]  [-p Pattern] [path]");
                    return false;
                }
            } else if (args[0].equals("-p")) {
                pattern = args[1];
            } else {
                System.out.println("Usage: java SearchLine [-r]  [-p Pattern] [path]");
                return false;
            }
        } else if (args.length == 3) {
            if (args[0].equals("-r")) {
                recursive = true;
                if (args[1].equals("-p")) {
                    pattern = args[2];
                } else {
                    System.out.println("Usage: java SearchLine [-r]  [-p Pattern] [path]");
                    return false;
                }
            } else if (args[0].equals("-p")) {
                pattern = args[1];
                path = args[2];
            } else {
                System.out.println("Usage: java SearchLine [-r]  [-p Pattern] [path]");
                return false;
            }
        } else if (args.length == 4) {
            if (args[0].equals("-r") && args[1].equals("-p")) {
                recursive = true;
                pattern = args[2];
                path = args[3];
            } else {
                System.out.println("Usage: java SearchLine [-r]  [-p Pattern] [path]");
                return false;
            }
        } else if (args.length > 4) {
            System.out.println("Usage: java SearchLine [-r]  [-p Pattern] [path]");
            return false;
        }

        if (pattern == null) {
            pattern = ".*";
        }

        if (path == null) {
            path = System.getProperty("user.dir");
        }

        return true;
    }
}
