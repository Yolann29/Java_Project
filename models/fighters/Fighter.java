package models.fighters;
import models.types.Type;
import models.weapons.Weapon;
import models.items.Item;

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
        if(this.weapon == null){
            System.out.println(String.format("%s picked %s", this.name, weapon.getName()));
        } else {
            System.out.println(String.format("%s replaced his %s by %s", this.name, this.weapon.getName(), weapon.getName()));
        }
        this.weapon = weapon;

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