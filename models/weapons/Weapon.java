package models.weapons;
import models.fighters.Fighter;
import models.types.Type;
import models.weapons.attacks.Attack;

public abstract class Weapon {
    protected String name;
    protected Type type;
    protected int damage;
    protected int level;
    protected Attack[] attacks;

    public Weapon(String name, Type type, int damage, int level, Attack[] attacks) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.level = level;
        this.attacks = attacks;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public Attack[] getAttacks() {
        return attacks;
    }

    public void attack(Fighter fighter){
        //TODO: implement attack
    }
}
