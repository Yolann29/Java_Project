package controller.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    private int x, y, speed;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }
}
