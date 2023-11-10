package controller.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    private int x, y, speed;
    private String direction;
    private boolean reversed;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public boolean isReversed() {
        return reversed;
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

    protected void setDirection(String direction) {
        this.direction = direction;
    }

    protected void setReversed(boolean reversed) {
        this.reversed = reversed;
    }
}
