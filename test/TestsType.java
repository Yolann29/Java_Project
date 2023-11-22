package test;

import models.types.Type;

import java.awt.*;

public class TestsType {

    @Test
    public void getName_shouldReturnTheRightName() {
        // arrange
        String fire_expected = "Fire";
        String water_expected = "Water";
        String plant_expected = "Plant";
        String ground_expected = "Ground";
        String elec_expected = "Elec";

        Type fire = Type.FIRE;
        Type water = Type.WATER;
        Type plant = Type.PLANT;
        Type ground = Type.GROUND;
        Type elec = Type.ELECTRICITY;

        // act
        String fire_result = fire.getName();
        String water_result = water.getName();
        String plant_result = plant.getName();
        String ground_result = ground.getName();
        String elec_result = elec.getName();

        // assert
        Assert.assertEqual("An error occurred for method getName Test1 (FIRE) !", fire_expected, fire_result);
        Assert.assertEqual("An error occurred for method getName Test1 (WATER) !", water_expected, water_result);
        Assert.assertEqual("An error occurred for method getName Test1 (PLANT) !", plant_expected, plant_result);
        Assert.assertEqual("An error occurred for method getName Test1 (GROUND) !", ground_expected, ground_result);
        Assert.assertEqual("An error occurred for method getName Test1 (ELECTRICITY) !", elec_expected, elec_result);
    }

    @Test
    public void getWeakness_shouldReturnTheRightWeakness() {
        // arrange
        String fire_expected = "Water";
        String water_expected = "Elec";
        String plant_expected = "Fire";
        String ground_expected = "Plant";
        String elec_expected = "Ground";

        Type fire = Type.FIRE;
        Type water = Type.WATER;
        Type plant = Type.PLANT;
        Type ground = Type.GROUND;
        Type elec = Type.ELECTRICITY;

        // act
        String fire_result = fire.getWeakness();
        String water_result = water.getWeakness();
        String plant_result = plant.getWeakness();
        String ground_result = ground.getWeakness();
        String elec_result = elec.getWeakness();

        // assert
        Assert.assertEqual("An error occurred for method getWeakness Test2 (FIRE) !", fire_expected, fire_result);
        Assert.assertEqual("An error occurred for method getWeakness Test2 (WATER) !", water_expected, water_result);
        Assert.assertEqual("An error occurred for method getWeakness Test2 (PLANT) !", plant_expected, plant_result);
        Assert.assertEqual("An error occurred for method getWeakness Test2 (GROUND) !", ground_expected, ground_result);
        Assert.assertEqual("An error occurred for method getWeakness Test2 (ELECTRICITY) !", elec_expected, elec_result);
    }

    @Test
    public void getColor_shouldReturnTheRightColor() {
        // arrange
        Color fire_expected = new Color(255, 81, 31);
        Color water_expected = new Color(0, 162, 255);
        Color plant_expected = new Color(0, 194, 0);
        Color ground_expected = new Color(141, 63, 34);
        Color elec_expected = new Color(220, 207, 0);

        Type fire = Type.FIRE;
        Type water = Type.WATER;
        Type plant = Type.PLANT;
        Type ground = Type.GROUND;
        Type elec = Type.ELECTRICITY;

        // act
        Color fire_result = fire.getColor();
        Color water_result = water.getColor();
        Color plant_result = plant.getColor();
        Color ground_result = ground.getColor();
        Color elec_result = elec.getColor();

        // assert
        Assert.assertEqual("An error occurred for method getColor Test3 (FIRE) !", fire_expected, fire_result);
        Assert.assertEqual("An error occurred for method getColor Test3 (WATER) !", water_expected, water_result);
        Assert.assertEqual("An error occurred for method getColor Test3 (PLANT) !", plant_expected, plant_result);
        Assert.assertEqual("An error occurred for method getColor Test3 (GROUND) !", ground_expected, ground_result);
        Assert.assertEqual("An error occurred for method getColor Test3 (ELECTRICITY) !", elec_expected, elec_result);
    }
}
