package controller.manager;

import models.Action;
import models.Role;

public class FighterClasseManager {

    public static Animation returnRightAnimation(Role classe, Action action) {

        switch(action) {
            case WALK:
                switch (classe) {
                    case VAGRANT:
                        return AnimationManager.VAGRANT_WALK;
                    case WARRIOR:
                        return AnimationManager.WARRIOR_WALK;
                    case ARCHER:
                        return AnimationManager.ARCHER_WALK;
                    case MAGICIAN:
                        return AnimationManager.MAGICIAN_WALK;
                    case CAT_GRAY:
                        return AnimationManager.CAT_WALK_GRAY;
                    case CAT_ORANGE:
                        return AnimationManager.CAT_WALK_ORANGE;
                }
            case IDLE:
                switch (classe) {
                    case VAGRANT:
                        return AnimationManager.VAGRANT_IDLE;
                    case WARRIOR:
                        return AnimationManager.WARRIOR_IDLE;
                    case ARCHER:
                        return AnimationManager.ARCHER_IDLE;
                    case MAGICIAN:
                        return AnimationManager.MAGICIAN_IDLE;
                    case CAT_GRAY:
                        return AnimationManager.CAT_IDLE_GRAY;
                    case CAT_ORANGE:
                        return AnimationManager.CAT_IDLE_ORANGE;
                }
            case JUMP:
                switch (classe) {
                    case VAGRANT:
                        return AnimationManager.VAGRANT_JUMP;
                    case WARRIOR:
                        return AnimationManager.WARRIOR_JUMP;
                    case ARCHER:
                        return AnimationManager.ARCHER_JUMP;
                    case MAGICIAN:
                        return AnimationManager.MAGICIAN_JUMP;
                }
            case ATTACK:
                switch (classe) {
                    case VAGRANT:
                        return AnimationManager.VAGRANT_ATTACK;
                    case WARRIOR:
                        return AnimationManager.WARRIOR_ATTACK;
                    case ARCHER:
                        return AnimationManager.ARCHER_ATTACK;
                    case MAGICIAN:
                        return AnimationManager.MAGICIAN_ATTACK;
                }
            case HIT:
                switch (classe) {
                    case VAGRANT:
                        return AnimationManager.VAGRANT_HIT;
                    case WARRIOR:
                        return AnimationManager.WARRIOR_HIT;
                    case ARCHER:
                        return AnimationManager.ARCHER_HIT;
                    case MAGICIAN:
                        return AnimationManager.MAGICIAN_HIT;
                }
            case DEAD:
                switch (classe) {
                    case VAGRANT:
                        return AnimationManager.VAGRANT_DEAD;
                    case WARRIOR:
                        return AnimationManager.WARRIOR_DEAD;
                    case ARCHER:
                        return AnimationManager.ARCHER_DEAD;
                    case MAGICIAN:
                        return AnimationManager.MAGICIAN_DEAD;
                }
            case OBJECT:
                switch (classe) {
                    case VAGRANT:
                    case WARRIOR:
                        return null;
                    case ARCHER:
                        return AnimationManager.ARCHER_OBJECT;
                    case MAGICIAN:
                        return AnimationManager.MAGICIAN_OBJECT;
                }

        }
        return null;
    }
}
