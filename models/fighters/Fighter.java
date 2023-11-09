package models.fighters;
import models.types.Type;
import models.weapons.Weapon;
import models.items.Item;
import models.weapons.attacks.Attack;

import java.util.ArrayList;
import java.util.List;

public abstract class Fighter {
    protected String name;
    protected Type type;
    protected int hp;
    protected int defense;
    protected Weapon weapon;
    protected ArrayList<Item> items;
    protected int level;
    protected int experience = 0;
    protected long walkingTime = 0;
    protected long damageTime = 0;

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

    public void pickItem(Item item){
        this.items.add(item);
        System.out.println(String.format("%s picked %s", this.name, item.getName()));
    }

    public void pickItems(List<Item> items){
        for (Item item : items) {
            this.pickItem(item);
        }
    }

    public Item item(int index){
        if(index >= this.items.size()){
            return null;
        }
        return this.items.get(index);
    }

    public Item item(Item item){
        if(this.items.contains(item)){
            return item;
        }
        return null;
    }

    public void useItem(Item item){
        if(this.items.contains(item)){
            this.items.remove(item);
        }

        ItemEffection(item);
    }

    public void useItem(int index){
        Item item = this.item(index);
        this.getItems().remove(index);

        ItemEffection(item);


    }

    private void ItemEffection(Item item) {
        if(item.getName().equalsIgnoreCase("Heal Potion")){
            this.hp = Math.min(100, this.hp + item.getHeal());
            System.out.println(String.format("%s used %s and healed %d hp", this.name, item.getName(), item.getHeal()));

        } else if(item.getName().equalsIgnoreCase("Damage Booster")){
            this.weapon.boostDamage(item.getDamage());
            System.out.println(String.format("%s used %s and boosted his weapon damage by %d", this.name, item.getName(), item.getDamage()));
        }
    }

    public boolean attack(Fighter target, Attack attack){
        target.takeDamage(this.weapon.getDamage() + attack.getDamage());
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

    public long getWalkingTime() {
        return walkingTime;
    }

    public boolean isDead(){
        return getHp() < 1;
    }

    public void setWalkingTime(long walkingTime) {
        this.walkingTime = walkingTime;
    }
}
