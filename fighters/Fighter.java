package fighters;
import fighters.types.Type;
import fighters.weapons.Weapon;
import fighters.items.Item;

import java.util.ArrayList;

public abstract class Fighter {
    protected String name;
    protected Type type;
    protected int hp;
    protected int defense;
    protected Weapon weapon;
    protected ArrayList<Item> items;
    protected int level;
    protected int experience = 0;

    public Fighter(String name, Type type) {
        this.type = type;
        this.name = name;
        this.hp = 100;
        this.defense = 100;
        this.items = new ArrayList<>();
    }

    public void pickWeapon(Weapon weapon) {
        if (this.weapon == null) {
            this.weapon = weapon;
            System.out.printf("%s pick %s%n", this.name, weapon.getName());
        }
    }

    public boolean canAttack(Fighter fighter) {
        return true;
    }


    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDefense() {
        return defense;
    }

    public Type getType() {
        return type;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }
}
