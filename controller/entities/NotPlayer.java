package controller.entities;

import controller.manager.FighterClasseManager;
import models.fighters.Fighter;
import views.GamePanel;

import java.awt.*;
import java.util.Objects;

public class NotPlayer extends Entity {

    GamePanel gamePanel;
    private final String movement;
    public final String classe;
    public Fighter fighter;
//    String directionWanted = "";
//    boolean directionChanged = false;

    public NotPlayer(GamePanel gamePanel, int positionX, int positionY, int speed, String movement, String classe) {
        this.gamePanel = gamePanel;
        this.movement = movement;
        this.classe = classe;
        setDefault(positionX, positionY, speed);
    }

    public void setDefault(int positionX, int positionY, int speed) {
        this.setWorldX(positionX);
        this.setWorldY(positionY);
        this.setSpeed(speed);
        this.setDirection("idle");
        this.setReversed(true);
    }

    public void update() {
        if (isDead) return;
        switch (movement) {
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
                break;
            case "scared archer":
                String directionX4;
                if (gamePanel.player.getWorldX() < (getWorldX() + 3 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - 3 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() + 3 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - 3 * GamePanel.tileSize)) {
                    if (this.getWorldX() < gamePanel.player.getWorldX() - 5) {
                        directionX4 = "left";
                    } else if (this.getWorldX() > gamePanel.player.getWorldX() + 5) {
                        directionX4 = "right";
                    } else {
                        directionX4 = "idle";
                    }
                    if (this.getWorldY() < gamePanel.player.getWorldY() - 5) {
                        this.setDirection("up");
                    } else if (this.getWorldY() > gamePanel.player.getWorldY() + 5) {
                        this.setDirection("down");
                    } else {
                        this.setDirection("idle");
                    }
                    collisionOn = false;
                    gamePanel.collision.checkTile(this);
                    if (!collisionOn && !getDirection().equals("idle")) {
                        move(getDirection());
                    } else {
                        this.setDirection(directionX4);
                        collisionOn = false;
                        gamePanel.collision.checkTile(this);
                        if (!collisionOn) move(directionX4);
                    }
                } else if (gamePanel.player.getWorldX() < (getWorldX() + 3.5 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - 3.5 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() + 3.5 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - 3.5 * GamePanel.tileSize)) {
                    this.setDirection("idle");
                } else {
                    if (this.getWorldX() < 12 * GamePanel.tileSize - 5) {
                        directionX4 = "right";
                    } else if (this.getWorldX() > 12 * GamePanel.tileSize + 5) {
                        directionX4 = "left";
                    } else {
                        directionX4 = "idle";
                    }
                    if (this.getWorldY() < 14 * GamePanel.tileSize - 5) {
                        this.setDirection("down");
                    } else if (this.getWorldY() > 14 * GamePanel.tileSize + 5) {
                        this.setDirection("up");
                    } else {
                        this.setDirection("idle");
                    }
                    collisionOn = false;
                    gamePanel.collision.checkTile(this);
                    if (!collisionOn && !getDirection().equals("idle")) {
                        move(getDirection());
                    } else {
                        this.setDirection(directionX4);
                        collisionOn = false;
                        gamePanel.collision.checkTile(this);
                        if (!collisionOn) move(directionX4);
                    }
                }
                break;
            case "pursuer":
                String directionX;
                if (gamePanel.player.getWorldX() < (getWorldX() + 5 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - 5 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() + 5 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - 5 * GamePanel.tileSize)) {
                    if (this.getWorldX() < gamePanel.player.getWorldX() - 5) {
                        directionX = "right";
                    } else if (this.getWorldX() > gamePanel.player.getWorldX() + 5) {
                        directionX = "left";
                    } else {
                        directionX = "attack";
                    }
                    if (this.getWorldY() < gamePanel.player.getWorldY() - 5) {
                        this.setDirection("down");
                    } else if (this.getWorldY() > gamePanel.player.getWorldY() + 5) {
                        this.setDirection("up");
                    } else {
                        this.setDirection("attack");
                    }
                    collisionOn = false;
                    gamePanel.collision.checkTile(this);
                    if (!collisionOn && !getDirection().equals("attack")) {
                        move(getDirection());
                    } else {
                        this.setDirection(directionX);
                        collisionOn = false;
                        gamePanel.collision.checkTile(this);
                        if (!collisionOn) move(directionX);
                    }
                } else {
                    this.setDirection("idle");
                }
                break;
            case "smart magician":
                String directionX2;
                if (gamePanel.player.getWorldX() < (getWorldX() + 10 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - 2 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() + 2 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - 2 * GamePanel.tileSize)) {
                    if (this.getWorldX() < gamePanel.player.getWorldX() - 5) {
                        directionX2 = "right";
                    } else if (this.getWorldX() > gamePanel.player.getWorldX() + 5) {
                        directionX2 = "left";
                    } else {
                        directionX2 = "attack";
                    }
                    if (this.getWorldY() < gamePanel.player.getWorldY() - 5) {
                        this.setDirection("down");
                    } else if (this.getWorldY() > gamePanel.player.getWorldY() + 5) {
                        this.setDirection("up");
                    } else {
                        this.setDirection("attack");
                    }
                    collisionOn = false;
                    gamePanel.collision.checkTile(this);
                    if (!collisionOn && !getDirection().equals("attack")) {
                        move(getDirection());
                    } else {
                        this.setDirection(directionX2);
                        collisionOn = false;
                        gamePanel.collision.checkTile(this);
                        if (!collisionOn) move(directionX2);
                    }
                } else {
                    this.setDirection("idle");
                }
                break;
            case "mad warrior":
                String directionX3;
                if (gamePanel.player.getWorldX() < (getWorldX() + 2 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - 2 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() + GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - 2.5 * GamePanel.tileSize)) {
                    if (this.getWorldX() < gamePanel.player.getWorldX() - 5) {
                        directionX3 = "right";
                    } else if (this.getWorldX() > gamePanel.player.getWorldX() + 5) {
                        directionX3 = "left";
                    } else {
                        directionX3 = "attack";
                    }
                    if (this.getWorldY() < gamePanel.player.getWorldY() - 5) {
                        this.setDirection("down");
                    } else if (this.getWorldY() > gamePanel.player.getWorldY() + 5) {
                        this.setDirection("up");
                    } else {
                        this.setDirection("attack");
                    }
                    collisionOn = false;
                    gamePanel.collision.checkTile(this);
                    if (!collisionOn && !getDirection().equals("attack")) {
                        move(getDirection());
                    } else {
                        this.setDirection(directionX3);
                        collisionOn = false;
                        gamePanel.collision.checkTile(this);
                        if (!collisionOn) move(directionX3);
                    }
                } else {
                    this.setDirection("attack");
                }
                break;
        }
    }

    public void move(String direction) {
        switch(direction) {
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

    public void draw(Graphics2D g2) {

        int screenX = gamePanel.player.screenX + (this.getWorldX() - gamePanel.player.getWorldX());
        int screenY = gamePanel.player.screenY + (this.getWorldY() - gamePanel.player.getWorldY());

        switch(this.getDirection()) {
            case "up":
            case "down":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "walk")).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "left":
                this.setReversed(true);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "walk")).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, true);
                break;
            case "right":
                this.setReversed(false);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "walk")).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, false);
                break;
            case "idle":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "idle")).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "attack":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "attack")).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case "dead":
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, "dead")).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
        }
    }
}
