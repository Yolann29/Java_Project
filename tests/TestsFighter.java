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
        Assert.assertEqual("An error occured for method getHp (Test1)!", expected, result);
    }

    @Test(enabled = true)
    public void getDefense_shouldReturnTheFighterDefense() {
        // arange
        int expected = 100;
        Warrior fighter = new Warrior("Gwendal", Type.AIR);

        // act
        int result = fighter.getDefense();

        // assert
        Assert.assertEqual("An error occured for method getDefense Test2)!", expected, result);
    }

    @Test
    public void getName_shouldReturnTheFighterName() {
        // arange
        String expected = "Gwendal";
        Warrior fighter = new Warrior("Gwendal", Type.AIR);

        // act
        String result = fighter.getName();

        // assert
        Assert.assertEqual("An error occured for method getName Test3)!", expected, result);
    }

    @Test
    public void getName_shouldReturnTheFighterNameEmpty() {
        // arange
        String expected = "";
        Warrior fighter = new Warrior("", Type.AIR);

        // act
        String result = fighter.getName();

        // assert
        Assert.assertEqual("An error occured for method getName empty (Test4)!", expected, result);
    }

    @Test(enabled = true)
    public void getWeapon_shouldReturnTheFighterWeapon() {
        // arange
        Warrior fighter = new Warrior("Gwendal", Type.AIR);
        FireSword sword = new FireSword();

        // act
        Weapon result1 = fighter.getWeapon();
        fighter.pickWeapon(sword);
        Weapon result2 = fighter.getWeapon();

        // assert
        Assert.assertEqual("An error occured for method getWeapon null (Test5)!", null, result1);
        Assert.assertEqual("An error occured for method getWeapon (Test6)!", sword, result2);
    }

    @Test(enabled = true)
    public void getWeapon_shouldReturnTheFighterWeaponReplaced() {
        // arange
        Warrior fighter = new Warrior("Gwendal", Type.AIR);
        FireSword firesword = new FireSword();
        IceSword icesword = new IceSword();

        // act
        fighter.pickWeapon(icesword);
        Weapon result1 = fighter.getWeapon();
        fighter.pickWeapon(firesword);
        Weapon result2 = fighter.getWeapon();

        // assert
        Assert.assertEqual("An error occured for method getWeapon (Test7)!", icesword, result1);
        Assert.assertEqual("An error occured for method getWeapon (Test8)!", firesword, result2);
    }

    @Test(enabled = true)
    public void attack_shouldRemoveTheRightHp() {
        // arange
        Warrior fighterOffense = new Warrior("Gwendal", Type.AIR);
        Warrior fighterDefense = new Warrior("Arthur", Type.FIRE);
        FireSword firesword = new FireSword();
        IceSword icesword = new IceSword();

        // act
        fighterOffense.pickWeapon(icesword);
        fighterDefense.pickWeapon(firesword);
        //fighterOffense.attack(fighterDefense);

        // assert
        //Assert.assertEqual("An error occured for method getWeapon (Test7)!", icesword, result1);
        //Assert.assertEqual("An error occured for method getWeapon (Test8)!", firesword, result2);
    }
}
