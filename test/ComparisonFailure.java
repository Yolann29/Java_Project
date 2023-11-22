package test;

public class ComparisonFailure extends Exception {
    private final Object expected;
    private final Object actual;
    public static boolean error = false;

    public ComparisonFailure(String message, Object expected, Object actual) {
        super(message);
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "expected: " + this.expected + "|actual: " + this.actual;
    }

    public void message() {
        System.setOut(Assert.actualOut);
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        System.out.println(red + this.getMessage() + reset);
        System.setOut(Assert.newOut);
    }
}
