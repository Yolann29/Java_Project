package controller;

import controller.manager.AnimationManager;
import models.fighters.Fighter;
import models.weapons.attacks.Attack;
import models.weapons.attacks.Strike;
import views.customwidgets.PTextPane;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Arena {

    private Fighter fighter1;
    private Fighter fighter2;

    private Fighter attacker;
    private Fighter target;
    private Attack attack;

    private boolean isYourTurn = true;
    PTextPane textPane;

    Scanner scanner = new Scanner(System.in);

    public Arena(Fighter fighter1, Fighter fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    public void startAttack(Fighter attacker, Fighter target, Attack attack){

        this.attacker = attacker;
        this.target = target;
        this.attack = attack;

        if (attack != null && attacker.getWalkingTime() == 0) {
            this.attacker.setWalkingTime(System.currentTimeMillis());
            this.textPane.setTextWithTypingEffect(String.format("%s use %s on %s!", attacker.getName(), attack.getName(), target.getName()));

        }
    }

    public void applyAttack(Graphics g2){
        if(attack != null){
            if(attacker.attack(target, attack)){
                this.target = null;
                this.attack = null;
            }
        }
    }


    public void switchTurn(){
        this.isYourTurn = !this.isYourTurn;
        if(isYourTurn){
            this.textPane.setTextWithTypingEffect("Your turn!");
        }
        this.attacker = null;
    }

    public void update() {

        if(!isYourTurn && attacker != fighter2 && !fighter2.isDead() && !fighter1.isUsingItem()){
            startAttack(fighter2, fighter1, fighter2.getWeapon().getWeaponAttacks()[new Random().nextInt(fighter2.getWeapon().getNumberAttacks())]);
        }

    }

    public void setTextPane(PTextPane textPane) {
        this.textPane = textPane;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public Fighter getFighter1() {
        return fighter1;
    }

    public Fighter getFighter2() {
        return fighter2;
    }
}

