package tests;

public class TestsMain {
    public static void main(String[] args) {
        TestsFighter testsFighter = new TestsFighter();
        testsFighter.getHp_shouldReturnTheActualHp();
        testsFighter.getDefense_shouldReturnTheFighterDefense();
    }
}
