package models.types;

public enum Type {
    FIRE("Fire","Water"),
    WATER("Water", "Electricity"),
    PLANT("Plant", "Fire"),
    GROUND("Ground", "Plant"),
    ELECTRICITY("Electricity", "Ground");


    private String name;
    private String weakness;

    Type(String name, String weakness) {
        this.name = name;
        this.weakness = weakness;
    }

    public String getName() {
        return name;
    }

}
