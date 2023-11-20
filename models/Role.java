package models;

public enum Role {
    VAGRANT("Vagrant"),
    WARRIOR("Warrior"),
    ARCHER("Archer"),
    MAGICIAN("Magician"),
    CAT_GRAY("Cat gray"),
    CAT_ORANGE("Cat orange"),
    DOG("Dog");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
