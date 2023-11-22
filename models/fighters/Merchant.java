package models.fighters;

import models.types.Type;

public class Merchant extends Fighter {

    public Merchant(String name) {
        super(name, 1);
        System.out.println(String.format("%s is a merchant", name));
    }

}
