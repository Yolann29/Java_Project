package controller.entities;

import models.Action;

import java.awt.*;

public class Entity {

    private int worldX, worldY, x, y, speed;
    private Action direction;
    protected boolean reversed;
    public boolean collisionOn, doorHere, exteriorNear, isDead = false;
    protected Rectangle solidArea = new Rectangle(12, 40, 40, 24);



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public Action getDirection() {
        return direction;
    }

    public boolean isReversed() {
        return reversed;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    protected void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(Action direction) {
        this.direction = direction;
    }

    protected void setReversed(boolean reversed) {
        this.reversed = reversed;
    }
}
