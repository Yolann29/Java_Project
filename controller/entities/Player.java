package controller.entities;

import controller.handler.KeyHandler;
import views.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    Image image;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefault();
        getImage();
    }

    public void setDefault() {
        this.setX(100);
        this.setY(100);
        this.setSpeed(4);
    }

    public void getImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/player/vagrant.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        g2.drawImage(image, this.getX(), this.getY(), null);
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}
