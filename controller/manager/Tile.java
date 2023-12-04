package controller.manager;

import java.awt.image.BufferedImage;

public class Tile {

    protected BufferedImage image;
    protected boolean collision = false;
    protected boolean door = false;
    protected boolean exterior = false;

    public boolean isCollision() {
        return collision;
    }

    public boolean isDoor() {
        return door;
    }

    public boolean isExterior() { return exterior; }
}
