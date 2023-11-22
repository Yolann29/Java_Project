package test;

import models.types.Type;
import models.weapons.ElectricGauntlet;
import models.weapons.FireSword;
import models.weapons.GroundSpear;
import models.weapons.IceSword;

public class TestsWeapon {

    @Test
    public void getDamage_shouldReturnTheRightDamage() {
        // arrange
        int electricGauntlet_expected = 15;
        int fireSword_expected = 15;
        int groundSpear_expected = 15;
        int iceSword_expected = 15;

        ElectricGauntlet electricGauntlet = new ElectricGauntlet();
        FireSword fireSword = new FireSword();
        GroundSpear groundSpear = new GroundSpear();
        IceSword iceSword = new IceSword();

        // act
        int electricGauntlet_result = electricGauntlet.getDamage();
        int fireSword_result = fireSword.getDamage();
        int groundSpear_result = groundSpear.getDamage();
        int iceSword_result = iceSword.getDamage();

        // assert
        Assert.assertEqual("An error occurred for method getDamage Test1 (ElectricGauntlet) !", electricGauntlet_expected, electricGauntlet_result);
        Assert.assertEqual("An error occurred for method getDamage Test1 (FireSword) !", fireSword_expected, fireSword_result);
        Assert.assertEqual("An error occurred for method getDamage Test1 (GroundSpear) !", groundSpear_expected, groundSpear_result);
        Assert.assertEqual("An error occurred for method getDamage Test1 (IceSword) !", iceSword_expected, iceSword_result);
    }

    @Test
    public void getName_shouldReturnTheRightName() {
        // arrange
        String electricGauntlet_expected = "Electric gauntlet";
        String fireSword_expected = "Fire Sword";
        String groundSpear_expected = "Ground Spear";
        String iceSword_expected = "Ice Sword";

        ElectricGauntlet electricGauntlet = new ElectricGauntlet();
        FireSword fireSword = new FireSword();
        GroundSpear groundSpear = new GroundSpear();
        IceSword iceSword = new IceSword();

        // act
        String electricGauntlet_result = electricGauntlet.getName();
        String fireSword_result = fireSword.getName();
        String groundSpear_result = groundSpear.getName();
        String iceSword_result = iceSword.getName();

        // assert
        Assert.assertEqual("An error occurred for method getName Test2 (ElectricGauntlet) !", electricGauntlet_expected, electricGauntlet_result);
        Assert.assertEqual("An error occurred for method getName Test2 (FireSword) !", fireSword_expected, fireSword_result);
        Assert.assertEqual("An error occurred for method getName Test2 (GroundSpear) !", groundSpear_expected, groundSpear_result);
        Assert.assertEqual("An error occurred for method getName Test2 (IceSword) !", iceSword_expected, iceSword_result);
    }

    @Test
    public void getInitialDamage_shouldReturnTheRightDamage_and_boostDamage() {
        // arrange
        int electricGauntlet_boost = 25;
        int fireSword_boost = 15;
        int groundSpear_boost = 25;
        int iceSword_boost = 80;

        int electricGauntlet_expected = 15 + electricGauntlet_boost;
        int fireSword_expected = 15 + fireSword_boost;
        int groundSpear_expected = 15;
        int iceSword_expected = 15;

        ElectricGauntlet electricGauntlet = new ElectricGauntlet();
        FireSword fireSword = new FireSword();
        GroundSpear groundSpear = new GroundSpear();
        IceSword iceSword = new IceSword();

        // act
        electricGauntlet.boostDamage(electricGauntlet_boost);
        int electricGauntlet_result = electricGauntlet.getDamage();
        fireSword.boostDamage(fireSword_boost);
        int fireSword_result = fireSword.getDamage();
        groundSpear.boostDamage(groundSpear_boost);
        int groundSpear_result = groundSpear.getInitialDamage();
        iceSword.boostDamage(iceSword_boost);
        int iceSword_result = iceSword.getInitialDamage();

        // assert
        Assert.assertEqual("An error occurred for method boostDamage Test3 (ElectricGauntlet) !", electricGauntlet_expected, electricGauntlet_result);
        Assert.assertEqual("An error occurred for method boostDamage Test3 (FireSword) !", fireSword_expected, fireSword_result);
        Assert.assertEqual("An error occurred for method getInitialDamage Test3 (GroundSpear) !", groundSpear_expected, groundSpear_result);
        Assert.assertEqual("An error occurred for method getInitialDamage Test3 (IceSword) !", iceSword_expected, iceSword_result);
    }

    @Test
    public void setDamage_shouldputTheRightDamage() {
        // arrange
        int electricGauntlet_boost = 25;
        int fireSword_boost = 15;
        int groundSpear_boost = 25;
        int iceSword_boost = 80;

        int electricGauntlet_expected = electricGauntlet_boost;
        int fireSword_expected = fireSword_boost;
        int groundSpear_expected = groundSpear_boost;
        int iceSword_expected = iceSword_boost;

        ElectricGauntlet electricGauntlet = new ElectricGauntlet();
        FireSword fireSword = new FireSword();
        GroundSpear groundSpear = new GroundSpear();
        IceSword iceSword = new IceSword();

        // act
        electricGauntlet.setDamage(electricGauntlet_boost);
        int electricGauntlet_result = electricGauntlet.getDamage();
        fireSword.setDamage(fireSword_boost);
        int fireSword_result = fireSword.getDamage();
        groundSpear.setDamage(groundSpear_boost);
        int groundSpear_result = groundSpear.getInitialDamage();
        iceSword.setDamage(iceSword_boost);
        int iceSword_result = iceSword.getInitialDamage();

        // assert
        Assert.assertEqual("An error occurred for method setDamage Test4 (ElectricGauntlet) !", electricGauntlet_expected, electricGauntlet_result);
        Assert.assertEqual("An error occurred for method setDamage Test4 (FireSword) !", fireSword_expected, fireSword_result);
        Assert.assertEqual("An error occurred for method setDamage Test4 (GroundSpear) !", groundSpear_expected, groundSpear_result);
        Assert.assertEqual("An error occurred for method setDamage Test4 (IceSword) !", iceSword_expected, iceSword_result);
    }

    @Test
    public void getType_shouldReturnTheRighType() {
        // arrange
        Type electricGauntlet_expected = Type.ELECTRICITY;
        Type fireSword_expected = Type.FIRE;
        Type groundSpear_expected = Type.GROUND;
        Type iceSword_expected = Type.WATER;

        ElectricGauntlet electricGauntlet = new ElectricGauntlet();
        FireSword fireSword = new FireSword();
        GroundSpear groundSpear = new GroundSpear();
        IceSword iceSword = new IceSword();

        // act
        Type electricGauntlet_result = electricGauntlet.getType();
        Type fireSword_result = fireSword.getType();
        Type groundSpear_result = groundSpear.getType();
        Type iceSword_result = iceSword.getType();

        // assert
        Assert.assertEqual("An error occurred for method getType Test5 (ElectricGauntlet) !", electricGauntlet_expected, electricGauntlet_result);
        Assert.assertEqual("An error occurred for method getType Test5 (FireSword) !", fireSword_expected, fireSword_result);
        Assert.assertEqual("An error occurred for method getType Test5 (GroundSpear) !", groundSpear_expected, groundSpear_result);
        Assert.assertEqual("An error occurred for method getType Test5 (IceSword) !", iceSword_expected, iceSword_result);
    }
}