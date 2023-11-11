package controller.entities;

import controller.handler.KeyHandler;
import controller.manager.AnimationManager;
import views.GamePanel;

import java.awt.*;

public class NotPlayer extends Entity {

    GamePanel gamePanel;
    private String movement;

    public NotPlayer(GamePanel gamePanel, int positionX, int positionY, String movement) {
        this.gamePanel = gamePanel;
        this.movement = movement;
        setDefault(positionX, positionY);
    }

    public void setDefault(int positionX, int positionY) {
        this.setWorldX(positionX);
        this.setWorldY(positionY);
        this.setSpeed(4);
        this.setDirection("idle");
        this.setReversed(true);
    }

    public void update() {
        switch(movement) {
            case "circle":
                if (gamePanel.imageCount <= 15 && gamePanel.imageCount > 0) {
                    this.setDirection("up");
                    this.setWorldY(this.getWorldY() - this.getSpeed());
                } else if (gamePanel.imageCount <= 65 && gamePanel.imageCount > 50) {
                    this.setDirection("right");
                    this.setWorldX(this.getWorldX() + this.getSpeed());
                } else if (gamePanel.imageCount <= 115 && gamePanel.imageCount > 100) {
                    this.setDirection("down");
                    this.setWorldY(this.getWorldY() + this.getSpeed());
                } else if (gamePanel.imageCount <= 165 && gamePanel.imageCount > 150) {
                    this.setDirection("left");
                    this.setWorldX(this.getWorldX() - this.getSpeed());
                } else {
                    this.setDirection("idle");
                }
                break;
            case "up-down":
                if (gamePanel.imageCount <= 15 && gamePanel.imageCount > 0) {
                    this.setDirection("up");
                    this.setWorldY(this.getWorldY() - this.getSpeed());
                } else if (gamePanel.imageCount <= 115 && gamePanel.imageCount > 100) {
                    this.setDirection("down");
                    this.setWorldY(this.getWorldY() + this.getSpeed());
                } else {
                    this.setDirection("idle");
                }
                break;
            case "left-right":
                if (gamePanel.imageCount <= 50 && gamePanel.imageCount > 0) {
                    this.setDirection("left");
                    this.setWorldX(this.getWorldX() - this.getSpeed());
                } else if (gamePanel.imageCount <= 150 && gamePanel.imageCount > 100) {
                    this.setDirection("right");
                    this.setWorldX(this.getWorldX() + this.getSpeed());
                } else {
                    this.setDirection("idle");
                }
        }
    }

    public void draw(Graphics2D g2) {

        int screenX = gamePanel.player.screenX + (this.getWorldX() - gamePanel.player.getWorldX());
        int screenY = gamePanel.player.screenY + (this.getWorldY() - gamePanel.player.getWorldY());

        switch(this.getDirection()) {
            case "up":
            case "down":
                AnimationManager.WARRIOR_WALK.paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "left":
                this.setReversed(true);
                AnimationManager.WARRIOR_WALK.paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, true);
                break;
            case "right":
                this.setReversed(false);
                AnimationManager.WARRIOR_WALK.paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, false);
                break;
            case "idle":
                AnimationManager.WARRIOR_IDLE.paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
        }
    }
}
