package models.weapons;

import models.types.Type;
import models.weapons.attacks.Attack;
import models.weapons.attacks.Strike;

import java.util.List;
import java.util.Random;

public class IceSword extends Weapon {

    public IceSword() {
        super("IceSword", Type.GROUND, 35, 1, 4);
    }
}
