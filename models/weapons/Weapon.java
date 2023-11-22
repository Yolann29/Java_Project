package models.weapons;
import models.types.Type;
import models.weapons.attacks.Attack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Class.forName;

public abstract class Weapon {
    protected String name;
    protected Type type;
    protected int damage;
    protected int initialDamage;
    protected int level;
    protected Attack[] weaponAttacks;
    protected int numberAttacks;

    public Weapon(String name, Type type, int damage, int level, int numberAttacks) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.initialDamage = damage;
        this.level = level;
        this.weaponAttacks = new Attack[4];
        this.numberAttacks = Math.min(numberAttacks, 4);

        List<Class<?>> allAttacks = getAllAttacks();
        Random random = new Random();

        try {
            int index;
            for (int i = 0; i < numberAttacks; i++) {
                index = random.nextInt(allAttacks.size());
                Class<?> attackClass = allAttacks.get(index);
                allAttacks.remove(index);
                this.weaponAttacks[i] = (Attack) attackClass.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Class<?>> getAllAttacks() {
        List<Class<?>> allAttacks = new ArrayList<>();

        String packageName = Attack.class.getPackage().getName();
        String packagePath = packageName.replace('.', '/');
        String[] subClasses = new File(packagePath).list();

        for (String subClass : subClasses) {
            try {
                Class<?> sub = Class.forName(packageName + "." + subClass.replace(".java", ""));
                if (Attack.class.isAssignableFrom(sub) && !sub.equals(Attack.class)) {
                    allAttacks.add(sub);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return allAttacks;
    }


    public void boostDamage(int damage){
        this.damage += damage;
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

    public int getInitialDamage() {
        return initialDamage;
    }

    public Attack[] getWeaponAttacks() {
        return weaponAttacks;
    }

    public int getNumberAttacks() {
        return numberAttacks;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
