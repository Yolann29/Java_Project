package models;

public enum Role {
    VAGRANT("Vagrant"),
    WARRIOR("Warrior"),
    ARCHER("Archer"),
    MAGICIAN("Magician");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
