package models.fighters;

import models.types.Type;

public class Warrior extends Fighter {
    public Warrior(String name, int experience) {
        super(name, experience);
        System.out.println(String.format("%s is a warrior", name));
    }
}
