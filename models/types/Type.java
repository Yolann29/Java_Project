package models.types;

import java.awt.*;

public enum Type {
    FIRE("Fire","Water", new Color(255, 81, 31)),
    WATER("Water", "Elec", new Color(0, 162, 255)),
    PLANT("Plant", "Fire", new Color(0, 194, 0)),
    GROUND("Ground", "Plant", new Color(141, 63, 34)),
    ELECTRICITY("Elec", "Ground", new Color(220, 207, 0));


    private final String name;
    private final String weakness;
    private final Color color;

    Type(String name, String weakness, Color color) {
        this.name = name;
        this.weakness = weakness;
        this.color = color;
    }

    public static boolean hasWeakness(Type initialType, Type targetType){
        System.out.println(String.format("Type -> %s vs %s", initialType.name, targetType.name));
        if(targetType.weakness.equalsIgnoreCase(initialType.name)){
            System.out.println(String.format("Type -> %s has weakness against %s", targetType.name, initialType.name));
            return true;
        } else {
            System.out.println(String.format("Type -> %s has no weakness against %s", targetType.name, initialType.name));
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public String getWeakness(){
        return this.weakness;
    }

    public Color getColor(){
        return this.color;
    }

}
