package tests;

import java.io.PrintStream;

public class ComparisonFailure extends Exception {
    private Object expected;
    private Object actual;
    private final String reset = "\u001B[0m";
    private final String red = "\u001B[31m";

    public ComparisonFailure(String message, Object expected, Object actual) {
        super(message);
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "|expected: " + this.expected + "|actual: " + this.actual;
    }

    public void message() {
        System.out.println(red + this.getMessage() + reset);
    }
}
