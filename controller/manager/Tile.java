package controller.manager;

import java.awt.image.BufferedImage;

public class Tile {

    protected BufferedImage image;
    protected boolean collision = false;
    protected boolean door = false;

    public boolean isCollision() {
        return collision;
    }

    public boolean isDoor() {
        return door;
    }
}
