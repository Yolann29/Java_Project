package models.weapons.attacks;

public abstract class Attack {

    protected String name;
    protected int damage;

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
