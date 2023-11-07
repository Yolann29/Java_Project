package fighters;
import types.Type;
import weapons.Weapon;

public abstract class Fighter {
    public Fighter(Type type) {
        this.type = type;
    }
    protected String name;
    protected Type type;
    protected int hp;
    protected Weapon weapon;
}
