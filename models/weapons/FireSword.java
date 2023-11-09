package models.weapons;

import models.types.Type;
import models.weapons.attacks.Explosion;
import models.weapons.attacks.Strike;
import models.weapons.attacks.Tornado;
import models.weapons.attacks.Wave;

public class FireSword extends Weapon {
    public FireSword() {
        super("FireSword", Type.FIRE, 10, 1);
        this.attacks[0] = new Strike();
        this.attacks[1] = new Explosion();
        this.attacks[2] = new Tornado();
        this.attacks[3] = new Wave();
    }
}
