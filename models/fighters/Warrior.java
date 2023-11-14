package models.fighters;
import models.types.Type;

public class Warrior extends Fighter {
    public Warrior(String name, Type type) {
        super(name, type, 10);
        System.out.println(String.format("%s is a %s warrior", name, type.getName()));
    }
}
