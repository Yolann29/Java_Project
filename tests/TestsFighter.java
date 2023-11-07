import tests.Test;
import fighters.Fighter;

public class TestsFighter {

    @Test
    public void add_shouldReturnTheSum_OfTwoNumbers() {
        // arange
        int expected = 3;
        Fighter fighter = new Fighter();

        // act
        int result = fighter.getHp();

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void multiply_shouldReturnTheProduct_OfTwoNumbers() {
        // arange
        Integer expected = 9; // 1+2
        Calculator calculator = new Calculator();

        // act
        Integer result = calculator.multiply(3, 3);

        // assert
        assertEquals(expected, result);
    }
}
