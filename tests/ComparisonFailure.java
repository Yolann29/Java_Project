package tests;

public class ComparisonFailure extends Exception {
    private String expected;
    private String actual;

    public ComparisonFailure(String message, String expected, String actual) {
        super(message);
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "|expected: " + this.expected + "|actual: " + this.actual;
    }
}
