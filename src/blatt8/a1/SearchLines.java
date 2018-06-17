package blatt8.a1;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchLines extends Reader {

    private InputStream in;
    private Pattern pattern;
    private int line = -1, matches;

    public SearchLines(InputStream in, String regex) {
        this.in = in;
        this.pattern = Pattern.compile(regex);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java blatt8.a1.SearchLines <pattern> [<File]");
            return;
        }

        SearchLines sl = new SearchLines(System.in, args[1]);
        String line = null;
        while ((line = sl.readLine()) != null) {
            if (sl.getAmountOfMatches() > 0) {
                //System.out.println("Zeile " + sl.getLineNumber() + ": " + line);
                System.out.printf("Zeile %d: %s%n", sl.getLineNumber(), line);
            }
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        byte[] buf = new byte[len];
        int r = in.read(buf, off, len);

        String s = new String(buf);
        cbuf = s.toCharArray();
        return r;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    public String readLine() throws IOException {
        int i = -1;
        String str = "";
        while ((i = in.read()) > -1 && i != '\n') {
            str += (char) i;
        }

        if (str.length() > 0) {
            findMatches(str);

            line++;
            return str;
        } else {
            return null;
        }
    }

    public int getLineNumber() {
        return line;
    }

    public int getAmountOfMatches() {
        return matches;
    }

    private void findMatches(String str) {
        Matcher m = pattern.matcher(str);

        matches = 0;
        while (m.find()) {
            matches++;
        }
    }
}
