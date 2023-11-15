package controller.entities;

import controller.manager.FighterClasseManager;
import models.fighters.Fighter;
import models.fighters.Merchant;
import views.GamePanel;

import java.awt.*;
import java.util.Objects;

public class NotPlayableCharacter extends Entity {

    GamePanel gamePanel;
    private final String movement;
    public final String classe;
    public Fighter fighter;
//    String directionWanted = "";
//    boolean directionChanged = false;

    public NotPlayableCharacter(GamePanel gamePanel, int positionX, int positionY, int speed, String movement, String classe) {
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
                if (gamePanel.imageCount <= 50 && gamePanel.imageCount > 0) {
                    if (this.getWorldY() > 10 * GamePanel.tileSize) {
                        this.setDirection("up");
                    } else {
                        this.setDirection("idle");
                    }
                } else if (gamePanel.imageCount <= 100 && gamePanel.imageCount > 50) {
                    if (this.getWorldX() < 10 * GamePanel.tileSize) {
                        this.setDirection("right");
                    } else {
                        this.setDirection("idle");
                    }
                } else if (gamePanel.imageCount <= 150 && gamePanel.imageCount > 100) {
                    if (this.getWorldY() < 11 * GamePanel.tileSize) {
                        this.setDirection("down");
                    } else {
                        this.setDirection("idle");
                    }
                } else if (gamePanel.imageCount <= 200 && gamePanel.imageCount > 150) {
                    if (this.getWorldX() > 9 * GamePanel.tileSize) {
                        this.setDirection("left");
                    } else {
                        this.setDirection("idle");
                    }
                }
                move(getDirection());
                break;
            case "left-right":
                if (gamePanel.imageCount <= 100 && gamePanel.imageCount > 0) {
                    if (this.getWorldX() < 36 * GamePanel.tileSize) {
                        this.setDirection("right");
                    } else {
                        this.setDirection("idle");
                    }
                } else if (gamePanel.imageCount <= 200 && gamePanel.imageCount > 100) {
                    if (this.getWorldX() > 33 * GamePanel.tileSize) {
                        this.setDirection("left");
                    } else {
                        this.setDirection("idle");
                    }
                }
                move(getDirection());
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
                pursuePlayer(5, 5, 5, 5);
                break;
            case "smart magician":
                pursuePlayer(10, 2, 2, 2);
                break;
            case "mad warrior":
                pursuePlayer(2, 2, 1, 2.5f);
                break;
        }
    }

    public void pursuePlayer(int nombreTiles1, int nombreTiles2, int nombreTiles3, float nombreTiles4) {
        String direction;
        if (gamePanel.player.getWorldX() < (getWorldX() + nombreTiles1 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - nombreTiles1 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() +nombreTiles3 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - nombreTiles4 * GamePanel.tileSize)) {
            if (this.getWorldX() < gamePanel.player.getWorldX() - 5) {
                direction = "right";
            } else if (this.getWorldX() > gamePanel.player.getWorldX() + 5) {
                direction = "left";
            } else {
                direction = "idle";
            }
            if (this.getWorldY() < gamePanel.player.getWorldY() - 5) {
                this.setDirection("down");
            } else if (this.getWorldY() > gamePanel.player.getWorldY() + 5) {
                this.setDirection("up");
            } else {
                this.setDirection("idle");
            }
            collisionOn = false;
            gamePanel.collision.checkTile(this);
            if (!collisionOn && !getDirection().equals("idle")) {
                move(getDirection());
            } else {
                this.setDirection(direction);
                collisionOn = false;
                gamePanel.collision.checkTile(this);
                if (!collisionOn) move(direction);
            }
        } else {
            this.setDirection("idle");
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
    public void drawInfoBar(Graphics2D g2, int x, int y){

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Courier", Font.BOLD, 18));
        if (this.fighter != null && !(this.fighter instanceof Merchant)){
            g2.drawString(this.fighter.getName(), x - (this.fighter.getName().length() * 4), y - 10);

            g2.setColor(fighter.getWeapon().getType().getColor());
            g2.fillRoundRect(x + (this.fighter.getName().length() * 7) - 5, y - 25, 47, 20, 10, 10);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Courier", Font.BOLD, 16));
            g2.drawString("Lv." + this.fighter.getLevel(), x + (this.fighter.getName().length() * 7), y - 10);
        } else if(fighter != null){
            g2.setColor(Color.CYAN);
            g2.fillRoundRect(x - 10, y - 26, "Merchant".length() * 12, 22, 10, 10);
            g2.setColor(Color.WHITE);
            g2.drawString("Merchant", x - 5, y - 10);
        }
    }

    public void draw(Graphics2D g2) {

        int screenX = gamePanel.player.screenX + (this.getWorldX() - gamePanel.player.getWorldX());
        int screenY = gamePanel.player.screenY + (this.getWorldY() - gamePanel.player.getWorldY());

        drawInfoBar(g2, screenX, screenY);
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
