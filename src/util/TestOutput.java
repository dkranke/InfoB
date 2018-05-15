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
        if (errorCode == 0) {
            return "Failed";
        } else if (errorCode < Test.ERR_EQUAL_HASH) {
            return "Failed(Equals)";
        } else if (errorCode < Test.ERR_EQUAL_STRING) {
            return "Failed(Hash)";
        } else if (errorCode < 64) {
            return "Failed(String)";
        } else {
            return "Failed(Unknown)";
        }
    }
}
