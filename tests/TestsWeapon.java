package tests;

import models.weapons.FireSword;

public class TestsWeapon {

    @Test
    public void getDamage_shouldReturnTheRightDamage() {
        // arrange
        int expected = 10;
        FireSword sword = new FireSword();

        // act
        int result = sword.getDamage();

        // assert
        Assert.assertEqual("An error occurred for method getDamage Test1)!", expected, result);
    }
}
