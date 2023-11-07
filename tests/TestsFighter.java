package tests;
import tests.Assert;
import tests.ComparisonFailure;
import tests.Test;
import fighters.*;
import types.Type;

public class TestsFighter {

    @Test(enabled = true)
    public void getHp_shouldReturnTheActualHp() {
        // arange
        int expected = 100;
        Warrior fighter = new Warrior("Philippe", Type.FIRE);

        // act
        int result = fighter.getHp();

        // assert
        Assert.assertEqual("An error occured for method getHp!", expected, result);
    }

    @Test(enabled = true)
    public void getDefense_shouldReturnTheFighterDefense() {
        // arange
        int expected = 100;
        Warrior fighter = new Warrior("Gwendal", Type.AIR);

        // act
        int result = fighter.getDefense();

        // assert
        Assert.assertEqual("An error occured for method getDefense!", expected, result);
    }

    @Test
    public void getName_shouldReturnTheFighterName() {
        // arange
        String expected = "Gwendal";
        Warrior fighter = new Warrior("Gwendal", Type.AIR);

        // act
        String result = fighter.getName();

        // assert
        Assert.assertEqual("An error occured for method getName!", expected, result);
    }

    @Test
    public void getName_shouldReturnTheFighterNameEmpty() {
        // arange
        String expected = "";
        Warrior fighter = new Warrior("", Type.AIR);

        // act
        String result = fighter.getName();

        // assert
        Assert.assertEqual("An error occured for method getName empty!", expected, result);
    }
}
