package models.fighters;
import models.types.Type;

public class Warrior extends Fighter {
    public Warrior(String name, Type type, int experience) {
        super(name, type, experience);
        System.out.println(String.format("%s is a %s warrior", name, type.getName()));
    }
}
