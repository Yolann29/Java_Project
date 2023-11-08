package fighters.weapons;
import fighters.types.Type;
import fighters.weapons.attacks.Attack;

public abstract class Weapon {
    protected String name;
    protected Type type;
    protected int damage;
    protected int level;
    protected Attack[] attacks;

    public String getName() {
        return name;
    }
}
