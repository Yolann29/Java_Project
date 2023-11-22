package models.fighters;

import models.items.DamageBooster;
import models.items.HealPotion;
import models.types.Type;
import models.weapons.Weapon;
import models.items.Item;
import models.weapons.attacks.Attack;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Fighter {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int defense;
    protected Weapon weapon;
    protected ArrayList<Item> items;
    protected int experience = 1;
    protected long walkingTime = 0;
    protected long damageTime = 0;
    protected boolean usingItem = false;
    public Fighter(String name, int experience) {
        this.experience = experience;
        this.name = name;
        this.maxHp = 100 + (this.getLevel() * 10);
        this.hp = maxHp;
        this.defense = 100;
        this.items = new ArrayList<>(Arrays.asList(new HealPotion(), new DamageBooster()));

    }

    public void regenItems(){
        this.items = new ArrayList<>(Arrays.asList(new HealPotion(), new DamageBooster()));
    }

    public void pickWeapon(Weapon weapon) {
        if(this.weapon == null){
            System.out.println(String.format("Fighter -> %s picked %s", this.name, weapon.getName()));
        } else {
            System.out.println(String.format("Fighter -> %s replaced his %s by %s", this.name, this.weapon.getName(), weapon.getName()));
        }
        this.weapon = weapon;

    }

    public void useItem(Item item){
        if(this.items.contains(item)){
            this.items.remove(item);
            ItemEffection(item);
            this.setUsingItem(true);
        }
    }

    private void ItemEffection(Item item) {
        if(item.getName().equalsIgnoreCase("Heal Potion")){
            this.hp = Math.min(this.maxHp, this.hp + item.getHeal());

        } else if(item.getName().equalsIgnoreCase("Damage Booster")){
            this.weapon.boostDamage(item.getDamage());
        }
    }

    public boolean attack(Fighter target, Attack attack){
        int damage = (int) ((this.weapon.getDamage() + attack.getDamage() + this.getLevel()) * (Type.hasWeakness(this.weapon.getType(), target.weapon.getType()) ? 1.25 : 1));
        target.takeDamage(damage);
        return true;

    }

    public void takeDamage(int damage){
        this.hp = Math.max(0, this.hp - damage);
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
    public Weapon getWeapon() {
        return weapon;
    }

    public ArrayList<Item> getItems() {
        return items;
    }


    public int getLevel() {
        return (int) Math.floor(Math.sqrt(experience) / 10 * 2);
    }

    public long getWalkingTime() {
        return walkingTime;
    }

    public boolean isDead(){
        return getHp() < 1;
    }

    public void setWalkingTime(long walkingTime) {
        this.walkingTime = walkingTime;
    }

    public boolean isUsingItem() {
        return usingItem;
    }

    public void setUsingItem(boolean usingItem) {
        this.usingItem = usingItem;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void giveXp(int amount){
        this.experience += amount;
    }

    public void restoreHpMax() {;
//        System.out.println("Fighter -> MaxHP: " + this.maxHp + ", HP: " + this.hp);
        this.maxHp = this.getLevel() * 10 + 100;
        this.hp = maxHp;
    }

}
