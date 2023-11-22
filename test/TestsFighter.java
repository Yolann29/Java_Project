package test;

import models.fighters.*;
import models.types.Type;
import models.weapons.*;
import test.Assert;
import test.Test;

public class TestsFighter {

    @Test
    public void getHp_shouldReturnTheActualHp() {
        // arrange
        int expected = 110;
        Warrior fighter = new Warrior("Philippe", Type.FIRE, 50);

        // act
        int result = fighter.getHp();

        // assert
        Assert.assertEqual("An error occurred for method getHp Test1 !", expected, result);
    }

    @Test
    public void getDefense_shouldReturnTheFighterDefense() {
        // arrange
        int expected = 100;
        Warrior fighter = new Warrior("Gwendal", Type.PLANT, 100);

        // act
        int result = fighter.getDefense();

        // assert
        Assert.assertEqual("An error occurred for method getDefense Test2 !", expected, result);
    }

    @Test
    public void getName_shouldReturnTheFighterName() {
        // arrange
        String expected = "Gwendal";
        Warrior fighter = new Warrior("Gwendal", Type.PLANT, 100);

        // act
        String result = fighter.getName();

        // assert
        Assert.assertEqual("An error occurred for method getName Test3 !", expected, result);
    }

    @Test
    public void getName_shouldReturnTheFighterNameEmpty() {
        // arrange
        String expected = "";
        Warrior fighter = new Warrior("", Type.PLANT, 100);

        // act
        String result = fighter.getName();

        // assert
        Assert.assertEqual("An error occurred for method getName empty Test4 !", expected, result);
    }

    @Test
    public void getWeapon_shouldReturnTheFighterWeapon() {
        // arrange
        Warrior fighter = new Warrior("Gwendal", Type.PLANT, 100);
        FireSword sword = new FireSword();

        // act
        Weapon result1 = fighter.getWeapon();
        fighter.pickWeapon(sword);
        Weapon result2 = fighter.getWeapon();

        // assert
        Assert.assertEqual("An error occurred for method getWeapon null Test5 !", null, result1);
        Assert.assertEqual("An error occurred for method getWeapon Test6 !", sword, result2);
    }

    @Test
    public void getWeapon_shouldReturnTheFighterWeaponReplaced() {
        // arrange
        Warrior fighter = new Warrior("Gwendal", Type.PLANT, 100);
        FireSword firesword = new FireSword();
        IceSword icesword = new IceSword();

        // act
        fighter.pickWeapon(icesword);
        Weapon result1 = fighter.getWeapon();
        fighter.pickWeapon(firesword);
        Weapon result2 = fighter.getWeapon();

        // assert
        Assert.assertEqual("An error occurred for method getWeapon Test7 !", icesword, result1);
        Assert.assertEqual("An error occurred for method getWeapon Test8 !", firesword, result2);
    }

    @Test
    public void attack_shouldRemoveTheRightHp() {
        // arrange
        Warrior fighterOffense = new Warrior("Gwendal", Type.WATER, 100);
        Warrior fighterDefense = new Warrior("Arthur", Type.FIRE, 100);
        FireSword firesword = new FireSword();
        IceSword icesword = new IceSword();

        // act
        fighterOffense.pickWeapon(icesword);
        fighterDefense.pickWeapon(firesword);
        //fighterOffense.attack(fighterDefense);

        // assert
        //test.Assert.assertEqual("An error occurred for method getWeapon (Test7)!", icesword, result1);
        //test.Assert.assertEqual("An error occurred for method getWeapon (Test8)!", firesword, result2);
    }
}
