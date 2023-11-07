package tests;

public class Assert {
    protected Assert() {
    }

    private static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }

    private static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        } else {
            return isEquals(expected, actual);
        }
    }

    public static void assertEquals(String message, Object expected, Object actual) throws ComparisonFailure {
        if (!equalsRegardingNull(expected, actual)) {
            if (expected instanceof String && actual instanceof String) {
                String cleanMessage = message == null ? "" : message;
                throw new ComparisonFailure(cleanMessage, (String)expected, (String)actual);
            } else {
                String expectedString = String.valueOf(expected);
                String actualString = String.valueOf(actual);
                String cleanMessage = message == null ? "" : "Got different objects:\nexpected: " + expected.getClass().getName() + "\nactual: " + actual.getClass().getName();
                throw new ComparisonFailure(cleanMessage, expectedString, actualString);
            }
        }
    }

    public static void assertEquals(Object expected, Object actual) throws ComparisonFailure {
        assertEquals((String)null, (Object)expected, (Object)actual);
    }
}
