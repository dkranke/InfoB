package util;

public interface TestOutput {

    default boolean enableOutput() {
        return true;
    }

    default String toString(Object o) {
        return o.toString();
    }

    default String toHash(Object o) {
        return toString(o) + "(Hash: " + o.hashCode() + ")";
    }

    default String stringFormat() {
        return "%s %s %s ? %s%n";
    }

    default String stringPassed() {
        return "Passed";
    }

    default String stringFailed(int errorCode) {
        return "Failed(" + (errorCode < 4 ? "Equal" : "Hash") + ")";
    }
}
