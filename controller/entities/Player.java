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
    public final int midScreenX = GamePanel.FRAME_WIDTH/2 - 32;
    public final int midScreenY = GamePanel.FRAME_HEIGHT/2 - 32;
    public int screenX;
    public int screenY;
    private String classe;

    public Player(GamePanel gamePanel, KeyHandler keyHandler, String classe) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.classe = classe;

        solidArea = new Rectangle(12, 40, 40, 24);

        setDefault();
    }

    public void setDefault() {
        this.setWorldX(4*GamePanel.tileSize);
        this.setWorldY(4*GamePanel.tileSize);
        this.setSpeed(10);
        this.screenX = midScreenX;
        this.screenY = midScreenY;
        this.setDirection("right");
        this.setReversed(false);
    }

    public void update() {
        if (keyHandler.top || keyHandler.bottom || keyHandler.left || keyHandler.right) {
            if (keyHandler.top) {
                this.setDirection("up");
            } else if (keyHandler.bottom) {
                this.setDirection("down");
            } else if (keyHandler.left) {
                this.setDirection("left");
            } else {
                this.setDirection("right");
            }
            collisionOn = false;
            gamePanel.collision.checkTile(this);
            if (!collisionOn) {
                switch(this.getDirection()) {
                    case "up":
                        this.setWorldY(this.getWorldY() - this.getSpeed());
                        break;
                    case "down":
                        this.setWorldY(this.getWorldY() + this.getSpeed());
                        break;
                    case "left":
                        this.setWorldX(this.getWorldX() - this.getSpeed());
                        break;
                    case "right":
                        this.setWorldX(this.getWorldX() + this.getSpeed());
                        break;
                }
            }
        } else if (keyHandler.jump) {
            this.setDirection("jump");
        } else {
            this.setDirection("idle");
        }

        if (GamePanel.worldWidth - this.getWorldX() <= this.midScreenX + 32) {
            this.screenX = GamePanel.FRAME_WIDTH - (GamePanel.worldWidth - this.getWorldX());
        }
        if (GamePanel.worldHeight - this.getWorldY() <= this.midScreenY + 32) {
            this.screenY = GamePanel.FRAME_HEIGHT - (GamePanel.worldHeight - this.getWorldY());
        }
        if (this.getWorldX() <= this.midScreenX + 32) {
            this.screenX = this.getWorldX();
        }
        if (this.getWorldY() <= this.midScreenY + 32) {
            this.screenY = this.getWorldY();
        }
    }

    public void draw(Graphics2D g2) {

        switch(this.getDirection()) {
            case "up":
            case "down":
                switch(classe) {
                    case "Vagrant":
                        AnimationManager.VAGRANT_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Warrior":
                        AnimationManager.WARRIOR_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Magician":
                        AnimationManager.MAGICIAN_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                }
                break;
            case "left":
                this.setReversed(true);
                switch(classe) {
                    case "Vagrant":
                        AnimationManager.VAGRANT_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Warrior":
                        AnimationManager.WARRIOR_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Magician":
                        AnimationManager.MAGICIAN_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                }
                break;
            case "right":
                this.setReversed(false);
                switch(classe) {
                    case "Vagrant":
                        AnimationManager.VAGRANT_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Warrior":
                        AnimationManager.WARRIOR_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Magician":
                        AnimationManager.MAGICIAN_WALK.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                }
                break;
            case "idle":
                switch(classe) {
                    case "Vagrant":
                        AnimationManager.VAGRANT_IDLE.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Warrior":
                        AnimationManager.WARRIOR_IDLE.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Magician":
                        AnimationManager.MAGICIAN_IDLE.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                }
                break;
            case "jump":
                switch(classe) {
                    case "Vagrant":
                        AnimationManager.VAGRANT_JUMP.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Warrior":
                        AnimationManager.WARRIOR_JUMP.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                        break;
                    case "Magician":
                        AnimationManager.MAGICIAN_JUMP.paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                }
        }
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}
