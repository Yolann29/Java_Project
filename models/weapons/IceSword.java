package models.weapons;

import models.types.Type;
import models.weapons.attacks.Strike;

import java.util.Random;

public class IceSword extends Weapon {

    public IceSword() {
        super("IceSword", Type.WATER, 5, 1);
        this.attacks[0] = new Strike();
    }
}
