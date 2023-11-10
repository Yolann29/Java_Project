package controller.entities;

import controller.handler.KeyHandler;
import controller.manager.AnimationManager;
import views.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefault();
    }

    public void setDefault() {
        this.setX(100);
        this.setY(100);
        this.setSpeed(4);
        this.setDirection("right");
        this.setReversed(false);
    }

    public void update() {
        if (keyHandler.top || keyHandler.bottom || keyHandler.left || keyHandler.right) {
            if (keyHandler.top) {
                this.setDirection("up");
                this.setY(this.getY() - this.getSpeed());
            } else if (keyHandler.bottom) {
                this.setDirection("down");
                this.setY(this.getY() + this.getSpeed());
            } else if (keyHandler.left) {
                this.setDirection("left");
                this.setX(this.getX() - this.getSpeed());
            } else {
                this.setDirection("right");
                this.setX(this.getX() + this.getSpeed());
            }
        } else if (keyHandler.jump) {
            this.setDirection("jump");
        } else {
            this.setDirection("idle");
        }
    }

    public void draw(Graphics2D g2) {

        switch(this.getDirection()) {
            case "up":
            case "down":
                AnimationManager.WARRIOR_WALK.paint(g2, this.getX(), this.getY(), 64, 64, this.isReversed());
                break;
            case "left":
                this.setReversed(true);
                AnimationManager.WARRIOR_WALK.paint(g2, this.getX(), this.getY(), 64, 64, true);
                break;
            case "right":
                this.setReversed(false);
                AnimationManager.WARRIOR_WALK.paint(g2, this.getX(), this.getY(), 64, 64, false);
                break;
            case "idle":
                AnimationManager.WARRIOR_IDLE.paint(g2, this.getX(), this.getY(), 64, 64, this.isReversed());
                break;
        }
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}
