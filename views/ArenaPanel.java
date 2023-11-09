package views;

import controller.Arena;
import controller.manager.Animation;
import controller.manager.AnimationManager;
import models.fighters.Fighter;
import models.fighters.Warrior;
import views.dialog.DialogActions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ArenaPanel extends JPanel {

    Image image;
    Arena arena;
    DialogActions da;

    public ArenaPanel(Arena arena, DialogActions da) {
        this.da = da;
        this.arena = arena;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        loadRessources();


    }

    public void loadRessources() {

        try {
            image = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-fight.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void moveToTarget(Graphics2D g2, Fighter fighter, int startX, boolean reversed){

        long time = System.currentTimeMillis() - fighter.getWalkingTime();
        float timeInSec = time / 1300f;
        int x = startX + (int)(370 * Math.min(1, timeInSec)) * (reversed?-1:1);

        if(timeInSec >= 1 && timeInSec <= 1.3) {
            AnimationManager.WARRIOR_ATTACK.paint(g2, x, 270, 96, 96, reversed);
        } else if (timeInSec < 1){
            AnimationManager.WARRIOR_WALK.paint(g2, x, 270, 96, 96, reversed);
        } else {
            arena.applyAttack();
            int reversedX = startX + (int) (370 * Math.max(0, 1 - (timeInSec - 1.3))  * (reversed?-1:1));
            AnimationManager.WARRIOR_WALK.paint(g2, reversedX, 270, 96, 96, !reversed);
            if(reversedX == startX){
                arena.switchTurn();
                fighter.setWalkingTime(0);
            }
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //BACKGROUND
        g.drawImage(image, 0, 0, null);


        if(arena.getFighter1().isDead()){
            AnimationManager.WARRIOR_DEAD.paint(g2, 100, 270, 96, 96, false);
        } else if(arena.getFighter1().getWalkingTime() != 0){
            moveToTarget(g2, arena.getFighter1(), 100, false);
        } else {
            AnimationManager.WARRIOR_IDLE.paint(g2, 100, 270, 96, 96, false);
        }

        if(arena.getFighter2().isDead()){
            AnimationManager.WARRIOR_DEAD.paint(g2, 550, 270, 96, 96, true);
        } else if(arena.getFighter2().getWalkingTime() != 0){
            moveToTarget(g2, arena.getFighter2(), 550, true);
        } else {
            AnimationManager.WARRIOR_IDLE.paint(g2, 550, 270, 96, 96, true);
        }

        da.setInteraction(arena.getFighter1().getWalkingTime() == 0
                && arena.getFighter2().getWalkingTime() == 0
                && !arena.getFighter1().isDead()
                && !arena.getFighter2().isDead()
                && arena.isYourTurn());

        if(!arena.getFighter1().isDead() && arena.getFighter2().isDead()){
            da.setDialogText(arena.getFighter1().getName() + " won !");
        }
        if(!arena.getFighter2().isDead() && arena.getFighter1().isDead()){
            da.setDialogText(arena.getFighter2().getName() + " won !");
        }

    }
}

