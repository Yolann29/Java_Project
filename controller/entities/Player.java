package controller.entities;

import controller.handler.KeyHandler;
import views.GamePanel;

import java.awt.*;

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
    }

    public void update() {
        if (keyHandler.top) {
            this.setY(this.getY() - this.getSpeed());
        } else if (keyHandler.bottom) {
            this.setY(this.getY() + this.getSpeed());
        } else if (keyHandler.left) {
            this.setX(this.getX() - this.getSpeed());
        } else if (keyHandler.right){
            this.setX(this.getX() + this.getSpeed());
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(this.getX(), this.getY(), 50, 50);
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}
