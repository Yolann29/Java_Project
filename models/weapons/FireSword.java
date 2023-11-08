package models.weapons;

import models.types.Type;
import models.weapons.attacks.Explosion;
import models.weapons.attacks.Strike;

public class FireSword extends Weapon {
    public FireSword() {
        super("FireSword", Type.FIRE, 10, 1);
        this.attacks[0] = new Strike();
        this.attacks[1] = new Explosion();
    }
}
