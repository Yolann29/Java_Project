package controller.manager;

import views.GamePanel;

public class FighterClasseManager {

    public static Animation returnRightAnimation(String classe, String action) {

        switch(action) {
            case "walk":
                switch (classe) {
                    case "Vagrant":
                        return AnimationManager.VAGRANT_WALK;
                    case "Warrior":
                        return AnimationManager.WARRIOR_WALK;
                    case "Archer":
                        return AnimationManager.ARCHER_WALK;
                    case "Magician":
                        return AnimationManager.MAGICIAN_WALK;
                }
            case "idle":
                switch (classe) {
                    case "Vagrant":
                        return AnimationManager.VAGRANT_IDLE;
                    case "Warrior":
                        return AnimationManager.WARRIOR_IDLE;
                    case "Archer":
                        return AnimationManager.ARCHER_IDLE;
                    case "Magician":
                        return AnimationManager.MAGICIAN_IDLE;
                }
            case "jump":
                switch (classe) {
                    case "Vagrant":
                        return AnimationManager.VAGRANT_JUMP;
                    case "Warrior":
                        return AnimationManager.WARRIOR_JUMP;
                    case "Archer":
                        return AnimationManager.ARCHER_JUMP;
                    case "Magician":
                        return AnimationManager.MAGICIAN_JUMP;
                }
            case "attack":
                switch (classe) {
                    case "Vagrant":
                        return AnimationManager.VAGRANT_ATTACK;
                    case "Warrior":
                        return AnimationManager.WARRIOR_ATTACK;
                    case "Archer":
                        return AnimationManager.ARCHER_ATTACK;
                    case "Magician":
                        return AnimationManager.MAGICIAN_ATTACK;
                }
            case "hit":
                switch (classe) {
                    case "Vagrant":
                        return AnimationManager.VAGRANT_HIT;
                    case "Warrior":
                        return AnimationManager.WARRIOR_HIT;
                    case "Archer":
                        return AnimationManager.ARCHER_HIT;
                    case "Magician":
                        return AnimationManager.MAGICIAN_HIT;
                }
            case "dead":
                switch (classe) {
                    case "Vagrant":
                        return AnimationManager.VAGRANT_DEAD;
                    case "Warrior":
                        return AnimationManager.WARRIOR_DEAD;
                    case "Archer":
                        return AnimationManager.ARCHER_DEAD;
                    case "Magician":
                        return AnimationManager.MAGICIAN_DEAD;
                }
            case "object":
                switch (classe) {
                    case "Vagrant":
                    case "Warrior":
                        return null;
                    case "Archer":
                        return AnimationManager.ARCHER_OBJECT;
                    case "Magician":
                        return AnimationManager.MAGICIAN_OBJECT;
                }

        }
        return null;
    }
}
