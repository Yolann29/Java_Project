package controller.entities;

import controller.Game;
import controller.Main;
import controller.manager.AudioManagerWAV;
import controller.manager.FighterClasseManager;
import models.Action;
import models.Pattern;
import models.Role;
import models.fighters.Fighter;
import models.fighters.Merchant;
import views.GamePanel;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class NotPlayableCharacter extends Entity {

    Game game;
    private final Pattern movement;
    public final Role classe;
    public Fighter fighter;
    private boolean blocked;
    private int counter;
    final private int positionX;
    final private int positionY;

    //CAT ATTRIBUTES
    final private AudioManagerWAV[] meows = new AudioManagerWAV[2];
    final private Random r = new Random();
    private boolean hasMeow = false;

    private int meowChoice = 0;

    public NotPlayableCharacter(Game game, int positionX, int positionY, int speed, Pattern movement, Role classe) {
        this.game = game;
        this.movement = movement;
        this.classe = classe;
        this.positionX = positionX;
        this.positionY = positionY;
        setDefault(positionX, positionY, speed);
        meows[0] = new AudioManagerWAV("cats","meow1.wav");
        meows[0].setVolume(5);
        meows[1] = new AudioManagerWAV("cats","meow2.wav");
        meows[1].setVolume(5);

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

        //MIAULEMENT DU CHAT
        if(this.classe == Role.CAT_BLACK || this.classe == Role.CAT_GRAY || this.classe == Role.CAT_ORANGE){
            int random = r.nextInt(1000);
            if(!meows[meowChoice].getClip().isActive()){
                hasMeow = false;
            }
            if(random == 1){
                meowChoice = r.nextInt(meows.length);
                hasMeow = true;
                meows[meowChoice].playSound();
            }
        }

        switch (movement) {
            case CIRCLE:
                if (Main.imageCount <= 50 && Main.imageCount > 0) {
                    if (this.getWorldY() > this.positionY - GamePanel.tileSize) {
                        this.setDirection(Action.UP);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                } else if (Main.imageCount <= 100 && Main.imageCount > 50) {
                    if (this.getWorldX() < this.positionX + GamePanel.tileSize) {
                        this.setDirection(Action.RIGHT);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                } else if (Main.imageCount <= 150 && Main.imageCount > 100) {
                    if (this.getWorldY() < this.positionY) {
                        this.setDirection(Action.DOWN);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                } else if (Main.imageCount <= 200 && Main.imageCount > 150) {
                    if (this.getWorldX() > this.positionX) {
                        this.setDirection(Action.LEFT);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                }
                move(getDirection());
                break;
            case LEFT_RIGHT:
                if (Main.imageCount <= 100 && Main.imageCount > 0) {
                    if (this.getWorldX() < this.positionX) {
                        this.setDirection(Action.RIGHT);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                } else if (Main.imageCount <= 200 && Main.imageCount > 100) {
                    if (this.getWorldX() > this.positionX - 3 * GamePanel.tileSize) {
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
                if (game.getPlayer().getWorldX() < (getWorldX() + 3 * GamePanel.tileSize) && game.getPlayer().getWorldX() > (getWorldX() - 3 * GamePanel.tileSize) && game.getPlayer().getWorldY() < (getWorldY() + 3 * GamePanel.tileSize) && game.getPlayer().getWorldY() > (getWorldY() - 3 * GamePanel.tileSize)) {
                    if (this.getWorldX() < game.getPlayer().getWorldX()) {
                        patternScared = Action.LEFT;
                    } else if (this.getWorldX() > game.getPlayer().getWorldX()) {
                        patternScared = Action.RIGHT;
                    } else {
                        patternScared = Action.IDLE;
                    }
                    if (this.getWorldY() < game.getPlayer().getWorldY()) {
                        this.setDirection(Action.UP);
                    } else if (this.getWorldY() > game.getPlayer().getWorldY()) {
                        this.setDirection(Action.DOWN);
                    } else {
                        this.setDirection(Action.IDLE);
                    }
                    if (Math.abs(this.getWorldX() - game.getPlayer().getWorldX()) > Math.abs(this.getWorldY() - game.getPlayer().getWorldY())) {
                        if (this.getWorldY() < game.getPlayer().getWorldY()) {
                            patternScared2 = Action.DOWN;
                        } else {
                            patternScared2 = Action.UP;
                        }
                    } else {
                        if (this.getWorldX() < game.getPlayer().getWorldX()) {
                            patternScared2 = Action.RIGHT;
                        } else {
                            patternScared2 = Action.LEFT;
                        }
                    }
                    collisionOn = false;
                    doorHere = false;
                    game.getCollision().checkTile(this);
                    if (!collisionOn && !doorHere && !getDirection().equals(Action.IDLE) && !blocked) {
                        move(getDirection());
                    } else {
                        this.setDirection(patternScared);
                        collisionOn = false;
                        doorHere = false;
                        game.getCollision().checkTile(this);
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
                                game.getCollision().checkTile(this);
                                if (!collisionOn && !doorHere) move(patternScared);
                            }
                            this.setDirection(patternScared2);
                            collisionOn = false;
                            doorHere = false;
                            game.getCollision().checkTile(this);
                            if (!collisionOn && !doorHere) move(patternScared2);
                        }
                    }
                } else if (game.getPlayer().getWorldX() < (getWorldX() + 3.5 * GamePanel.tileSize) && game.getPlayer().getWorldX() > (getWorldX() - 3.5 * GamePanel.tileSize) && game.getPlayer().getWorldY() < (getWorldY() + 3.5 * GamePanel.tileSize) && game.getPlayer().getWorldY() > (getWorldY() - 3.5 * GamePanel.tileSize)) {
                    this.setDirection(Action.IDLE);
                } else {
                    if (this.getWorldX() < this.positionX - 5) {
                        patternScared = Action.RIGHT;
                    } else if (this.getWorldX() > this.positionX + 5) {
                        patternScared = Action.LEFT;
                    } else {
                        patternScared = Action.IDLE;
                    }
                    if (this.getWorldY() < this.positionY - 5) {
                        this.setDirection(Action.DOWN);
                    } else if (this.getWorldY() > this.positionY + 5) {
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
                    game.getCollision().checkTile(this);
                    if (!collisionOn && !doorHere && !getDirection().equals(Action.IDLE) && !blocked) {
                        move(getDirection());
                    } else {
                        this.setDirection(patternScared);
                        collisionOn = false;
                        doorHere = false;
                        game.getCollision().checkTile(this);
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
                                game.getCollision().checkTile(this);
                                if (!collisionOn && !doorHere) move(patternScared);
                            }
                            this.setDirection(patternScared2);
                            collisionOn = false;
                            doorHere = false;
                            game.getCollision().checkTile(this);
                            if (!collisionOn && !doorHere) move(patternScared2);
                        }
                    }
                }
                break;
            case MEDIUM_PURSUER:
                pursuePlayer(5, 5, 5, 5);
                break;
            case SLOW_PURSUER:
                pursuePlayer(10, 2, 2, 2);
                break;
            case FAST_PURSUER:
                pursuePlayer(2, 2, 1, 2.5f);
                break;
        }
    }

    public void pursuePlayer(int nombreTiles1, int nombreTiles2, int nombreTiles3, float nombreTiles4) {
        Action pattern;
        if (game.getPlayer().getWorldX() < (getWorldX() + nombreTiles1 * GamePanel.tileSize) && game.getPlayer().getWorldX() > (getWorldX() - nombreTiles1 * GamePanel.tileSize) && game.getPlayer().getWorldY() < (getWorldY() +nombreTiles3 * GamePanel.tileSize) && game.getPlayer().getWorldY() > (getWorldY() - nombreTiles4 * GamePanel.tileSize)) {
            if (this.getWorldX() < game.getPlayer().getWorldX() - 5) {
                pattern = Action.RIGHT;
            } else if (this.getWorldX() > game.getPlayer().getWorldX() + 5) {
                pattern = Action.LEFT;
            } else {
                pattern = Action.IDLE;
            }
            if (this.getWorldY() < game.getPlayer().getWorldY() - 5) {
                this.setDirection(Action.DOWN);
            } else if (this.getWorldY() > game.getPlayer().getWorldY() + 5) {
                this.setDirection(Action.UP);
            } else {
                this.setDirection(Action.IDLE);
            }
            collisionOn = false;
            game.getCollision().checkTile(this);
            if (!collisionOn && !getDirection().equals(Action.IDLE)) {
                move(getDirection());
            } else {
                this.setDirection(pattern);
                collisionOn = false;
                game.getCollision().checkTile(this);
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
        if(this.classe == Role.CAT_GRAY || this.classe == Role.CAT_ORANGE || this.classe == Role.CAT_BLACK){
            if(hasMeow){
                if(this.classe == Role.CAT_BLACK) g2.setColor(Color.BLACK);
                else if(this.classe == Role.CAT_GRAY) g2.setColor(Color.GRAY);
                else g2.setColor(Color.ORANGE);
                g2.setFont(GamePanel.gloucester.deriveFont(14f));
                g2.drawString("Meooww", x - 10, y - 10);

            }
            return;
        }

        g2.setColor(Color.WHITE);
        g2.setFont(GamePanel.gloucester.deriveFont(11f));
        if (this.fighter != null && !(this.fighter instanceof Merchant)){
            g2.drawString(this.fighter.getName(), x - (this.fighter.getName().length() * 4), y - 8);

            //BACKGROUND TYPE
            g2.setColor(fighter.getWeapon().getType().getColor());
            g2.fillRoundRect(x + (this.fighter.getName().length() * 6) + 3, y - 25, (("Lv." + this.fighter.getLevel()).length() * 12), 21, 10, 10);

            //LVL
            g2.setColor(Color.WHITE);
            g2.setFont(GamePanel.gloucester.deriveFont(10f));
            g2.drawString("Lv." + this.fighter.getLevel(), x + (this.fighter.getName().length() * 7) + 1, y - 8);
        } else if(fighter != null){
            g2.setColor(Color.CYAN.darker());
            g2.fillRoundRect(x - 10, y - 26, "Merchant".length() * 12, 25, 10, 10);
            g2.setColor(Color.WHITE);
            g2.drawString("Merchant", x - 5, y - 4);
        }
    }

    public void draw(Graphics2D g2) {

        int screenX = game.getPlayer().screenX + (this.getWorldX() - game.getPlayer().getWorldX());
        int screenY = game.getPlayer().screenY + (this.getWorldY() - game.getPlayer().getWorldY());

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
