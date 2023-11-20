package controller.manager;

import controller.entities.Entity;
import views.GamePanel;

public class Collision {

    public void checkTile(Entity entity) {
        int solidAreaLeft = entity.getWorldX() + entity.getSolidArea().x;
        int solidAreaRight = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int solidAreaTop = entity.getWorldY() + entity.getSolidArea().y;
        int solidAreaBottom = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int leftX = solidAreaLeft / GamePanel.tileSize;
        int topY = solidAreaTop / GamePanel.tileSize;
        int rightX = solidAreaRight / GamePanel.tileSize;
        int bottomY = solidAreaBottom / GamePanel.tileSize;

        int indexTileNordOuest;
        int indexTileSudOuest;
        int indexTileNordEst;
        int indexTileSudEst;

        switch(entity.getDirection()) {
            case UP:
                topY = (solidAreaTop - entity.getSpeed()) / GamePanel.tileSize;
                indexTileNordOuest = TileManager.mapTileNum[leftX][topY];
                indexTileNordEst = TileManager.mapTileNum[rightX][topY];
                if (TileManager.tiles[indexTileNordOuest].isCollision() || TileManager.tiles[indexTileNordEst].isCollision()) {
                    entity.collisionOn = true;
                }
                if (TileManager.tiles[indexTileNordOuest].isDoor() || TileManager.tiles[indexTileNordEst].isDoor()) {
                    entity.doorHere = true;
                }
                break;
            case DOWN:
                bottomY = (solidAreaBottom + entity.getSpeed()) / GamePanel.tileSize;
                indexTileSudOuest = TileManager.mapTileNum[leftX][bottomY];
                indexTileSudEst = TileManager.mapTileNum[rightX][bottomY];
                if (TileManager.tiles[indexTileSudOuest].isCollision() || TileManager.tiles[indexTileSudEst].isCollision()) {
                    entity.collisionOn = true;
                }
                if (TileManager.tiles[indexTileSudOuest].isDoor() || TileManager.tiles[indexTileSudEst].isDoor()) {
                    entity.doorHere = true;
                }
                break;
            case LEFT:
                leftX = (solidAreaLeft - entity.getSpeed()) / GamePanel.tileSize;
                indexTileNordOuest = TileManager.mapTileNum[leftX][topY];
                indexTileSudOuest = TileManager.mapTileNum[leftX][bottomY];
                if (TileManager.tiles[indexTileNordOuest].isCollision() || TileManager.tiles[indexTileSudOuest].isCollision()) {
                    entity.collisionOn = true;
                }
                if (TileManager.tiles[indexTileNordOuest].isDoor() || TileManager.tiles[indexTileSudOuest].isDoor()) {
                    entity.doorHere = true;
                }
                break;
            case RIGHT:
                rightX = (solidAreaRight + entity.getSpeed()) / GamePanel.tileSize;
                indexTileNordEst = TileManager.mapTileNum[rightX][topY];
                indexTileSudEst = TileManager.mapTileNum[rightX][bottomY];
                if (TileManager.tiles[indexTileNordEst].isCollision() || TileManager.tiles[indexTileSudEst].isCollision()) {
                    entity.collisionOn = true;
                }
                if (TileManager.tiles[indexTileNordEst].isDoor() || TileManager.tiles[indexTileSudEst].isDoor()) {
                    entity.doorHere = true;
                }
        }
    }
}
