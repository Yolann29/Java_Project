package tests;
import tests.Assert;
import tests.ComparisonFailure;
import tests.Test;
import fighters.*;
import types.Type;

public class TestsFighter {

    @Test
    public void getHp_shouldReturnTheActualHp() {
        // arange
        int expected = 100;
        Warrior fighter = new Warrior("Philippe", Type.FIRE);

        // act
        int result = fighter.getHp();

        // assert
        try {
            Assert.assertEquals("An error occured for method getHp!", expected, result);
        } catch (ComparisonFailure e) {
            e.message();
        }
    }

    @Test
    public void getDefense_shouldReturnTheFighterDefense() {
        // arange
        int expected = 100;
        Warrior fighter = new Warrior("Gwendal", Type.AIR);

        // act
        int result = fighter.getDefense();

        // assert
        try {
            Assert.assertEquals("An error occured for method getDefense!", expected, result);
        } catch (ComparisonFailure e) {
            e.message();
        }

    }
}
