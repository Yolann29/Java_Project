package weapons;
import types.Type;
import attacks.Attack;

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
