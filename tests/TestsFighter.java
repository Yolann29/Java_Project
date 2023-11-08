package tests;
import models.fighters.*;
import models.types.Type;
import models.weapons.*;

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
        Warrior fighter = new Warrior("Gwendal", Type.PLANT);

        // act
        int result = fighter.getDefense();

        // assert
        Assert.assertEqual("An error occured for method getDefense!", expected, result);
    }

    @Test
    public void getName_shouldReturnTheFighterName() {
        // arange
        String expected = "Gwendal";
        Warrior fighter = new Warrior("Gwendal", Type.PLANT);

        // act
        String result = fighter.getName();

        // assert
        Assert.assertEqual("An error occured for method getName!", expected, result);
    }

    @Test
    public void getName_shouldReturnTheFighterNameEmpty() {
        // arange
        String expected = "";
        Warrior fighter = new Warrior("", Type.PLANT);

        // act
        String result = fighter.getName();

        // assert
        Assert.assertEqual("An error occured for method getName empty!", expected, result);
    }

    @Test(enabled = true)
    public void getWeapon_shouldReturnTheFighterWeapon() {
        // arange
        Warrior fighter = new Warrior("Gwendal", Type.PLANT);
        FireSword sword = new FireSword();

        // act
        Weapon result1 = fighter.getWeapon();
        fighter.pickWeapon(sword);
        Weapon result2 = fighter.getWeapon();

        // assert
        Assert.assertEqual("An error occured for method getWeapon null!", null, result1);
        Assert.assertEqual("An error occured for method getWeapon!", sword, result2);
    }
}
