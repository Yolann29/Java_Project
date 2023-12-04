package controller.entities;

import controller.Game;
import controller.handler.KeyHandler;
import controller.manager.FighterClasseManager;
import models.Action;
import models.Role;
import models.fighters.Fighter;
import views.GamePanel;

import java.awt.*;
import java.util.Objects;

public class Player extends Entity {

    Game game;
    KeyHandler keyHandler;
    public final int midScreenX = GamePanel.FRAME_WIDTH/2 - 32;
    public final int midScreenY = GamePanel.FRAME_HEIGHT/2 - 32;
    public int screenX;
    public int screenY;
    public Role classe;
    public boolean changeMap = false;
    public Fighter fighter;

    public Player(Game game, KeyHandler keyHandler, Role classe) {
        this.game = game;
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
        this.setDirection(Action.RIGHT);
        this.setReversed(false);
    }

    public void update() {
        if (isDead) return;
        if (keyHandler.top || keyHandler.bottom || keyHandler.left || keyHandler.right) {
            if (keyHandler.top) {
                this.setDirection(Action.UP);
            } else if (keyHandler.bottom) {
                this.setDirection(Action.DOWN);
            } else if (keyHandler.left) {
                this.setDirection(Action.LEFT);
            } else {
                this.setDirection(Action.RIGHT);
            }
            collisionOn = false;
            doorHere = false;
            game.getCollision().checkTile(this);
            if (!collisionOn) {
                if (doorHere) {
                    switch (this.getDirection()) {
                        case UP:
                            this.setWorldY(this.getWorldY() - this.getSpeed());
                            break;
                        case DOWN:
                            this.setWorldY(this.getWorldY() + this.getSpeed());
                            break;
                        case LEFT:
                            changeMap = true;
                            this.setWorldX(GamePanel.worldWidth - 65 - this.getSpeed());
                            break;
                        case RIGHT:
                            changeMap = true;
                            this.setWorldX(this.getSpeed());
                            break;
                    }
                } else if (exteriorNear) {
                    switch(this.getDirection()) {
                        case UP:
                            changeMap = true;
                            this.setWorldY(GamePanel.worldHeight - 65 - this.getSpeed());
                            break;
                        case DOWN:
                            changeMap = true;
                            this.setWorldY(this.getSpeed());
                            break;
                        case LEFT:
                            this.setWorldX(this.getWorldX() - this.getSpeed());
                            break;
                        case RIGHT:
                            this.setWorldX(this.getWorldX() + this.getSpeed());
                            break;
                    }
                } else {
                    switch(this.getDirection()) {
                        case UP:
                            this.setWorldY(this.getWorldY() - this.getSpeed());
                            break;
                        case DOWN:
                            this.setWorldY(this.getWorldY() + this.getSpeed());
                            break;
                        case LEFT:
                            this.setWorldX(this.getWorldX() - this.getSpeed());
                            break;
                        case RIGHT:
                            this.setWorldX(this.getWorldX() + this.getSpeed());
                            break;
                    }
                }
            }
        } else if (keyHandler.jump) {
            this.setDirection(Action.JUMP);
        } else if (keyHandler.attack) {
            this.setDirection(Action.ATTACK);
        } else {
            this.setDirection(Action.IDLE);
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

    public void drawInfoBar(Graphics2D g2, int x, int y){

        g2.setColor(Color.WHITE);
        g2.setFont(GamePanel.gloucester.deriveFont(11f));
        g2.drawString(this.fighter.getName(), x - (this.fighter.getName().length() * 4), y - 8);

        //BACKGROUND TYPE
        g2.setColor(this.fighter.getWeapon().getType().getColor());
        g2.fillRoundRect(x + (this.fighter.getName().length() * 8) + 6, y - 25, (("Lv." + this.fighter.getLevel()).length() * 12), 21, 10, 10);
        //LVL
        g2.setColor(Color.WHITE);
        g2.setFont(GamePanel.gloucester.deriveFont(10f));
        g2.drawString("Lv." + this.fighter.getLevel(), x + (this.fighter.getName().length() * 8) + 11, y - 8);

    }

    public void draw(Graphics2D g2) {

        drawInfoBar(g2, screenX, screenY);
        switch (this.getDirection()) {
            case UP:
            case DOWN:
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.WALK)).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case LEFT:
                this.setReversed(true);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.WALK)).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case RIGHT:
                this.setReversed(false);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.WALK)).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case IDLE:
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.IDLE)).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case JUMP:
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.JUMP)).paint(g2, this.screenX, this.screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case ATTACK:
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.ATTACK)).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case DEAD:
                g2.setColor(new Color(0, 0, 0, 0.75f));
                g2.fillRect(0, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);
                g2.setColor(Color.RED.darker());
                g2.setFont(GamePanel.gloucester.deriveFont(48f));
                g2.drawString("You died", GamePanel.FRAME_WIDTH/2 - 145, GamePanel.FRAME_HEIGHT/2 - 32);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.DEAD)).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());

        }
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}
