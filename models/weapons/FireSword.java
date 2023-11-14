package models.weapons;

import models.types.Type;
import models.weapons.attacks.Explosion;
import models.weapons.attacks.Strike;
import models.weapons.attacks.Tornado;
import models.weapons.attacks.Wave;

public class FireSword extends Weapon {
    public FireSword() {
        super("FireSword", Type.FIRE, 90, 1, 4);
    }
}
