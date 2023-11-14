package controller.entities;

import controller.handler.KeyHandler;
import controller.manager.AnimationManager;
import controller.manager.FighterClasseManager;
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
    public String classe;
    public boolean changeMap = false;

    public Player(GamePanel gamePanel, KeyHandler keyHandler, String classe) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.classe = classe;

        setDefault();
    }

    public void setDefault() {
        this.setWorldX(4*GamePanel.tileSize);
        this.setWorldY(4*GamePanel.tileSize);
        this.setSpeed(6);
        this.screenX = midScreenX;
        this.screenY = midScreenY;
        this.setDirection("right");
        this.setReversed(false);
    }

    public void update() {
        if (isDead) return;
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
            doorHere = false;
            gamePanel.collision.checkTile(this);
            if (!collisionOn) {
                if (doorHere) {
                    switch(this.getDirection()) {
                        case "up":
                            this.setWorldY(this.getWorldY() - this.getSpeed());
                            break;
                        case "down":
                            this.setWorldY(this.getWorldY() + this.getSpeed());
                            break;
                        case "left":
                            changeMap = true;
                            this.setWorldX(GamePanel.worldWidth - 96 - this.getSpeed());
                            break;
                        case "right":
                            changeMap = true;
                            this.setWorldX(this.getSpeed());
                            break;
                    }
                } else {
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
            }
        } else if (keyHandler.jump) {
            this.setDirection("jump");
        } else if (keyHandler.attack) {
            this.setDirection("attack");
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

        switch (this.getDirection()) {
            case "up":
            case "down":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "walk")).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "left":
                this.setReversed(true);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "walk")).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "right":
                this.setReversed(false);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "walk")).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "idle":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "idle")).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "jump":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "jump")).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "attack":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "attack")).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "dead":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "dead")).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
        }
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}
