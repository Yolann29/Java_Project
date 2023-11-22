package test;

import models.items.DamageBooster;
import models.items.HealPotion;

public class TestsItem {

    @Test
    public void getName_shouldReturnTheRighName() {
        // arrange
        String healPotion_expected = "Heal Potion";
        String damageBooster_expected = "Damage Booster";

        HealPotion healPotion = new HealPotion();
        DamageBooster damageBooster = new DamageBooster();

        // act
        String healPotion_result = healPotion.getName();
        String damageBooster_result = damageBooster.getName();

        // assert
        Assert.assertEqual("An error occurred for method getName Test1 (ElectricGauntlet) !", healPotion_expected, healPotion_result);
        Assert.assertEqual("An error occurred for method getName Test1 (FireSword) !", damageBooster_expected, damageBooster_result);
    }

    @Test
    public void getHeal_shouldReturnTheRighAmountOfHeal() {
        // arrange
        int healPotion_expected = 40;
        int damageBooster_expected = 0;

        HealPotion healPotion = new HealPotion();
        DamageBooster damageBooster = new DamageBooster();

        // act
        int healPotion_result = healPotion.getHeal();
        int damageBooster_result = damageBooster.getHeal();

        // assert
        Assert.assertEqual("An error occurred for method getHeal Test2 (ElectricGauntlet) !", healPotion_expected, healPotion_result);
        Assert.assertEqual("An error occurred for method getheal Test2 (FireSword) !", damageBooster_expected, damageBooster_result);
    }

    @Test
    public void getDamage_shouldReturnTheRighAmountOfBoost() {
        // arrange
        int healPotion_expected = 0;
        int damageBooster_expected = 20;

        HealPotion healPotion = new HealPotion();
        DamageBooster damageBooster = new DamageBooster();

        // act
        int healPotion_result = healPotion.getDamage();
        int damageBooster_result = damageBooster.getDamage();

        // assert
        Assert.assertEqual("An error occurred for method getDamage Test3 (ElectricGauntlet) !", healPotion_expected, healPotion_result);
        Assert.assertEqual("An error occurred for method getDamage Test3 (FireSword) !", damageBooster_expected, damageBooster_result);
    }
}
