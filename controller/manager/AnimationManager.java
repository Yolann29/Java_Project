package controller.manager;

import models.fighters.Fighter;

public class AnimationManager {

    //WARRIOR
    public static final Animation WARRIOR_IDLE = new Animation("/sprites/warrior/warrior-idle-animation.png", 3, 150);
    public static final Animation WARRIOR_WALK = new Animation("/sprites/warrior/warrior-walk-animation.png", 4, 75);
    public static final Animation WARRIOR_ATTACK = new Animation("/sprites/warrior/warrior-slash-animation.png", 4, 100);
    public static final Animation WARRIOR_HIT = new Animation("/sprites/warrior/warrior-hit-animation.png", 2, 125);
    public static final Animation WARRIOR_DEAD = new Animation("/sprites/warrior/warrior-dead-animation.png", 1, 100);
    public static final Animation WARRIOR_JUMP = new Animation("/sprites/warrior/warrior-jump-animation.png", 4, 100);

    //ARCHER
    public static final Animation ARCHER_IDLE = new Animation("/sprites/archer/archer-idle-animation.png", 4, 175);
    public static final Animation ARCHER_WALK = new Animation("/sprites/archer/archer-walk-animation.png", 4, 75);
    public static final Animation ARCHER_ATTACK = new Animation("/sprites/archer/archer-shoot-animation.png", 4, 100);


}
