package controller.entities;

import controller.manager.FighterClasseManager;
import models.fighters.Fighter;
import models.fighters.Warrior;
import views.GamePanel;

import java.awt.*;
import java.util.Objects;

public class NotPlayer extends Entity {

    GamePanel gamePanel;
    private final String movement;
    public final String classe;
    public Fighter fighter;

    public NotPlayer(GamePanel gamePanel, int positionX, int positionY, String movement, String classe) {
        this.gamePanel = gamePanel;
        this.movement = movement;
        this.classe = classe;
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
        if (isDead) return;
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
                break;
            case "attack":
                if (gamePanel.imageCount <= 50 && gamePanel.imageCount > 0) {
                    this.setDirection("attack");
                } else if (gamePanel.imageCount <= 150 && gamePanel.imageCount > 100) {
                    this.setDirection("attack");
                } else {
                    this.setDirection("idle");
                }
                break;
            case "smart":
                if (this.getWorldX() < gamePanel.player.getWorldX() - 5) {
                    this.setDirection("right");
                    this.setWorldX(this.getWorldX() + this.getSpeed());
                } else if (this.getWorldX() > gamePanel.player.getWorldX() + 5) {
                    this.setDirection("left");
                    this.setWorldX(this.getWorldX() - this.getSpeed());
                } else if (this.getWorldY() < gamePanel.player.getWorldY() - 5) {
                    this.setDirection("down");
                    this.setWorldY(this.getWorldY() + this.getSpeed());
                } else if (this.getWorldY() > gamePanel.player.getWorldY() + 5) {
                    this.setDirection("up");
                    this.setWorldY(this.getWorldY() - this.getSpeed());
                } else {
                    this.setDirection("attack");
                }
        }
    }

    public void drawInfoBar(Graphics2D g2, int x, int y){

        g2.setColor(Color.WHITE);
//        g2.drawString(String.valueOf(this.fighter.getLevel()), x, y - 10);
//        g2.fillRect(x, y - 10, GamePanel.tileSize, 5);
        g2.setFont(new Font("Courier", Font.BOLD, 18));
        if (this.fighter != null) {
            g2.drawString(this.fighter.getName(), x - (this.fighter.getName().length() * 4), y - 10);

            g2.setColor(Color.ORANGE);
            g2.fillRoundRect(x + (this.fighter.getName().length() * 7) - 5, y - 25, 50, 20, 10, 10);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Courier", Font.BOLD, 16));
            g2.drawString("Lv." + this.fighter.getLevel(), x + (this.fighter.getName().length() * 7), y - 10);
        } else {
            g2.setColor(Color.GREEN.darker());
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
