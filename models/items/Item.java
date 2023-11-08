package models.items;

public abstract class Item {

    protected String name;
    protected int heal;
    protected int damage;

    public Item(String name, int heal, int damage) {
        this.name = name;
        this.heal = heal;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }


    public int getHeal() {
        return heal;
    }

    public int getDamage() {
        return damage;
    }
}
