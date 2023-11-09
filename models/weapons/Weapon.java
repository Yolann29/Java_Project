package models.weapons;
import models.fighters.Fighter;
import models.types.Type;
import models.weapons.attacks.Attack;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Class.forName;

public abstract class Weapon {
    protected String name;
    protected Type type;
    protected int damage;
    protected int level;
    protected Attack[] attacks;
    protected Attack currentAttack;

    public Weapon(String name, Type type, int damage, int level, int numberAttacks) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.level = level;
        this.attacks = new Attack[4];
        if (numberAttacks > 4) {
            numberAttacks = 4;
        }

        List<Class<?>> allAttacks = getAllAttacks();
        Random random = new Random();

        try {
            int index;
            for (int i = 0; i < numberAttacks; i++) {
                index = random.nextInt(allAttacks.size());
                Class<?> attackClass = allAttacks.get(index);
                allAttacks.remove(index);
                this.attacks[i] = (Attack) attackClass.getDeclaredConstructor().newInstance();
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

    public int getLevel() {
        return level;
    }

    public Attack[] getAttacks() {
        return attacks;
    }


    public Attack[] getAttack(Attack attack) {
        return attacks;
    }

    public String[] getNameAttacks() {
        String[] attackNames = new String[attacks.length];
        for (int i = 0; i < attacks.length; i++) {
            if (attacks[i] != null) {
                attackNames[i] = attacks[i].getName();
            }
        }
        return attackNames;
    }
}
