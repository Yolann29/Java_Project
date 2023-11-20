package controller.entities;

import controller.manager.FighterClasseManager;
import models.Action;
import models.Pattern;
import models.Role;
import models.fighters.Fighter;
import models.fighters.Merchant;
import views.GamePanel;

import java.awt.*;
import java.util.Objects;

public class NotPlayableCharacter extends Entity {

    GamePanel gamePanel;
    private final Pattern movement;
    public final Role classe;
    public Fighter fighter;
    private boolean blocked;
    private int counter;

    public NotPlayableCharacter(GamePanel gamePanel, int positionX, int positionY, int speed, Pattern movement, Role classe) {
        this.gamePanel = gamePanel;
        this.movement = movement;
        this.classe = classe;
        setDefault(positionX, positionY, speed);
    }

    public void setDefault(int positionX, int positionY, int speed) {
        this.setWorldX(positionX);
        this.setWorldY(positionY);
        this.setSpeed(speed);
        this.setDirection(Action.IDLE);
        this.setReversed(true);
    }

    public void update() {
        if (isDead) return;
        switch (movement) {
            case CIRCLE:
                if (gamePanel.imageCount <= 50 && gamePanel.imageCount > 0) {
                    if (this.getWorldY() > 10 * GamePanel.tileSize) {
                        this.setDirection(Action.UP);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                } else if (gamePanel.imageCount <= 100 && gamePanel.imageCount > 50) {
                    if (this.getWorldX() < 10 * GamePanel.tileSize) {
                        this.setDirection(Action.RIGHT);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                } else if (gamePanel.imageCount <= 150 && gamePanel.imageCount > 100) {
                    if (this.getWorldY() < 11 * GamePanel.tileSize) {
                        this.setDirection(Action.DOWN);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                } else if (gamePanel.imageCount <= 200 && gamePanel.imageCount > 150) {
                    if (this.getWorldX() > 9 * GamePanel.tileSize) {
                        this.setDirection(Action.LEFT);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                }
                move(getDirection());
                break;
            case LEFT_RIGHT:
                if (gamePanel.imageCount <= 100 && gamePanel.imageCount > 0) {
                    if (this.getWorldX() < 36 * GamePanel.tileSize) {
                        this.setDirection(Action.RIGHT);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                } else if (gamePanel.imageCount <= 200 && gamePanel.imageCount > 100) {
                    if (this.getWorldX() > 33 * GamePanel.tileSize) {
                        this.setDirection(Action.LEFT);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                }
                move(getDirection());
                break;
            case SCARED:
                Action patternScared;
                Action patternScared2;
                if (gamePanel.player.getWorldX() < (getWorldX() + 3 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - 3 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() + 3 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - 3 * GamePanel.tileSize)) {
                    if (this.getWorldX() < gamePanel.player.getWorldX()) {
                        patternScared = Action.LEFT;
                    } else if (this.getWorldX() > gamePanel.player.getWorldX()) {
                        patternScared = Action.RIGHT;
                    } else {
                        patternScared = Action.IDLE;
                    }
                    if (this.getWorldY() < gamePanel.player.getWorldY()) {
                        this.setDirection(Action.UP);
                    } else if (this.getWorldY() > gamePanel.player.getWorldY()) {
                        this.setDirection(Action.DOWN);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                    if (Math.abs(this.getWorldX() - gamePanel.player.getWorldX()) > Math.abs(this.getWorldY() - gamePanel.player.getWorldY())) {
                        if (this.getWorldY() < gamePanel.player.getWorldY()) {
                            patternScared2 = Action.DOWN;
                        } else {
                            patternScared2 = Action.UP;
                        }
                    } else {
                        if (this.getWorldX() < gamePanel.player.getWorldX()) {
                            patternScared2 = Action.RIGHT;
                        } else {
                            patternScared2 = Action.LEFT;
                        }
                    }
                    collisionOn = false;
                    doorHere = false;
                    gamePanel.collision.checkTile(this);
                    if (!collisionOn && !doorHere && !getDirection().equals(Action.IDLE) && !blocked) {
                        move(getDirection());
                    } else {
                        this.setDirection(patternScared);
                        collisionOn = false;
                        doorHere = false;
                        gamePanel.collision.checkTile(this);
                        if (!collisionOn && !doorHere && !blocked) {
                            move(patternScared);
                        } else {
                            blocked = true;
                            counter = counter + this.getSpeed();
                            if (counter > 64 + this.getSpeed()) {
                                blocked = false;
                                counter = 0;
                                collisionOn = false;
                                doorHere = false;
                                gamePanel.collision.checkTile(this);
                                if (!collisionOn && !doorHere) move(patternScared);
                            }
                            this.setDirection(patternScared2);
                            collisionOn = false;
                            doorHere = false;
                            gamePanel.collision.checkTile(this);
                            if (!collisionOn && !doorHere) move(patternScared2);
                        }
                    }
                } else if (gamePanel.player.getWorldX() < (getWorldX() + 3.5 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - 3.5 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() + 3.5 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - 3.5 * GamePanel.tileSize)) {
                    this.setDirection(Action.IDLE);
                } else {
                    if (this.getWorldX() < 12 * GamePanel.tileSize - 5) {
                        patternScared = Action.RIGHT;
                    } else if (this.getWorldX() > 12 * GamePanel.tileSize + 5) {
                        patternScared = Action.LEFT;
                    } else {
                        patternScared = Action.IDLE;
                    }
                    if (this.getWorldY() < 14 * GamePanel.tileSize - 5) {
                        this.setDirection(Action.DOWN);
                    } else if (this.getWorldY() > 14 * GamePanel.tileSize + 5) {
                        this.setDirection(Action.UP);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                    if (Math.abs(this.getWorldX() - 12 * GamePanel.tileSize) > Math.abs(this.getWorldY() - 14 * GamePanel.tileSize)) {
                        if (this.getWorldY() < 14 * GamePanel.tileSize) {
                            patternScared2 = Action.DOWN;
                        } else {
                            patternScared2 = Action.UP;
                        }
                    } else {
                        if (this.getWorldX() < 12 * GamePanel.tileSize) {
                            patternScared2 = Action.RIGHT;
                        } else {
                            patternScared2 = Action.LEFT;
                        }
                    }
                    collisionOn = false;
                    doorHere = false;
                    gamePanel.collision.checkTile(this);
                    if (!collisionOn && !doorHere && !getDirection().equals(Action.IDLE) && !blocked) {
                        move(getDirection());
                    } else {
                        this.setDirection(patternScared);
                        collisionOn = false;
                        doorHere = false;
                        gamePanel.collision.checkTile(this);
                        if (!collisionOn && !doorHere && !blocked) {
                            move(patternScared);
                        } else {
                            blocked = true;
                            counter = counter + this.getSpeed();
                            if (counter > 64 + this.getSpeed()) {
                                blocked = false;
                                counter = 0;
                                collisionOn = false;
                                doorHere = false;
                                gamePanel.collision.checkTile(this);
                                if (!collisionOn && !doorHere) move(patternScared);
                            }
                            this.setDirection(patternScared2);
                            collisionOn = false;
                            doorHere = false;
                            gamePanel.collision.checkTile(this);
                            if (!collisionOn && !doorHere) move(patternScared2);
                        }
                    }
                }
                break;
            case MEDIUM_SMART:
                pursuePlayer(5, 5, 5, 5);
                break;
            case SLOW_SMART:
                pursuePlayer(10, 2, 2, 2);
                break;
            case FAST_SMART:
                pursuePlayer(2, 2, 1, 2.5f);
                break;
        }
    }

    public void pursuePlayer(int nombreTiles1, int nombreTiles2, int nombreTiles3, float nombreTiles4) {
        Action pattern;
        if (gamePanel.player.getWorldX() < (getWorldX() + nombreTiles1 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - nombreTiles1 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() +nombreTiles3 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - nombreTiles4 * GamePanel.tileSize)) {
            if (this.getWorldX() < gamePanel.player.getWorldX() - 5) {
                pattern = Action.RIGHT;
            } else if (this.getWorldX() > gamePanel.player.getWorldX() + 5) {
                pattern = Action.LEFT;
            } else {
                pattern = Action.IDLE;
            }
            if (this.getWorldY() < gamePanel.player.getWorldY() - 5) {
                this.setDirection(Action.DOWN);
            } else if (this.getWorldY() > gamePanel.player.getWorldY() + 5) {
                this.setDirection(Action.UP);
            } else {
                this.setDirection(Action.IDLE);
            }
            collisionOn = false;
            gamePanel.collision.checkTile(this);
            if (!collisionOn && !getDirection().equals(Action.IDLE)) {
                move(getDirection());
            } else {
                this.setDirection(pattern);
                collisionOn = false;
                gamePanel.collision.checkTile(this);
                if (!collisionOn) move(pattern);
            }
        } else {
            this.setDirection(Action.IDLE);
        }
    }

    public void move(Action direction) {
        switch(direction) {
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
            case UP:
            case DOWN:
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.WALK)).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case LEFT:
                this.setReversed(true);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.WALK)).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, true);
                break;
            case RIGHT:
                this.setReversed(false);
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.WALK)).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, false);
                break;
            case IDLE:
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.IDLE)).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case ATTACK:
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.ATTACK)).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
                break;
            case DEAD:
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.DEAD)).paint(g2, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, this.isReversed());
        }
    }
}
