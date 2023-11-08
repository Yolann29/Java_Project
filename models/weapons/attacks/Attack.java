package models.weapons.attacks;

import java.util.ArrayList;

public abstract class Attack {

    protected String name;
    protected int damage;
//    protected static ArrayList<Attack> attacks = new ArrayList<>();

    public Attack(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

}
