package controller.manager;

import models.fighters.Fighter;

public class AnimationManager {

    //VAGRANT
    public static final Animation VAGRANT_IDLE = new Animation("/sprites/vagrant/vagrant-idle-animation.png", 4, 175);
    public static final Animation VAGRANT_WALK = new Animation("/sprites/vagrant/vagrant-walk-animation.png", 4, 75);
    public static final Animation VAGRANT_ATTACK = new Animation("/sprites/vagrant/vagrant-punch-animation.png", 4, 60);
    public static final Animation VAGRANT_HIT = new Animation("/sprites/vagrant/vagrant-damage-animation.png", 2, 100);
    public static final Animation VAGRANT_DEAD = new Animation("/sprites/vagrant/vagrant-dead-animation.png", 1, 100);
    public static final Animation VAGRANT_JUMP = new Animation("/sprites/vagrant/vagrant-jump-animation.png", 4, 100);

    //WARRIOR
    public static final Animation WARRIOR_IDLE = new Animation("/sprites/warrior/warrior-idle-animation.png", 4, 175);
    public static final Animation WARRIOR_WALK = new Animation("/sprites/warrior/warrior-walk-animation.png", 4, 75);
    public static final Animation WARRIOR_ATTACK = new Animation("/sprites/warrior/warrior-slash-animation.png", 4, 100);
    public static final Animation WARRIOR_HIT = new Animation("/sprites/warrior/warrior-hit-animation.png", 2, 125);
    public static final Animation WARRIOR_DEAD = new Animation("/sprites/warrior/warrior-dead-animation.png", 1, 100);
    public static final Animation WARRIOR_JUMP = new Animation("/sprites/warrior/warrior-jump-animation.png", 4, 100);

    //ARCHER
    public static final Animation ARCHER_IDLE = new Animation("/sprites/archer/archer-idle-animation.png", 4, 175);
    public static final Animation ARCHER_WALK = new Animation("/sprites/archer/archer-walk-animation.png", 4, 75);
    public static final Animation ARCHER_ATTACK = new Animation("/sprites/archer/archer-shoot-animation.png", 4, 100);
    public static final Animation ARCHER_HIT = new Animation("/sprites/archer/archer-damage-animation.png", 2, 125);
    public static final Animation ARCHER_DEAD = new Animation("/sprites/archer/archer-dead-animation.png", 1, 100);
    public static final Animation ARCHER_JUMP = new Animation("/sprites/archer/archer-jump-animation.png", 4, 100);
    public static final Animation ARCHER_OBJECT = new Animation("/sprites/archer/archer-arrow-animation.png", 1, 100);

    //MAGICIAN
    public static final Animation MAGICIAN_IDLE = new Animation("/sprites/magician/magician-idle-animation.png", 4, 175);
    public static final Animation MAGICIAN_WALK = new Animation("/sprites/magician/magician-walk-animation.png", 4, 75);
    public static final Animation MAGICIAN_ATTACK = new Animation("/sprites/magician/magician-power-animation.png", 4, 100);
    public static final Animation MAGICIAN_HIT = new Animation("/sprites/magician/magician-damage-animation.png", 2, 100);
    public static final Animation MAGICIAN_DEAD = new Animation("/sprites/magician/magician-dead-animation.png", 1, 100);
    public static final Animation MAGICIAN_JUMP = new Animation("/sprites/magician/magician-jump-animation.png", 4, 100);
    public static final Animation MAGICIAN_OBJECT = new Animation("/sprites/magician/magician-magic-animation.png", 1, 100);
}
