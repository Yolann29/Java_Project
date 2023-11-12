package controller.entities;

import controller.manager.TileManager;
import views.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    private int worldX, worldY, x, y, speed;
    private String direction;
    protected boolean reversed;
    public boolean collisionOn;
    protected Rectangle solidArea;

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

    public String getDirection() {
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

    protected void setDirection(String direction) {
        this.direction = direction;
    }

    protected void setReversed(boolean reversed) {
        this.reversed = reversed;
    }
}