package controller.manager;

import views.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    public static Tile[] tiles;
    public static int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[12];
        getTilesImages();
        mapTileNum = new int[GamePanel.worldColumns][GamePanel.worldRows];
        getMap("/textures/floor/map");
    }

    public void getTilesImages() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/wallSimple5.png")));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-water.jpg")));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-wood.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-cobble.png")));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/waterfall.png")));
            tiles[5].collision = true;

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/wallLeft.png")));
            tiles[6].collision = true;

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-cobble.png")));
            tiles[7].collision = true;

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/wall-side.png")));
            tiles[8].collision = true;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-grass.png")));
            tiles[9].collision = true;

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/tree-bottom.png")));
            tiles[10].collision = true;

            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/tree-top.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMap(String mapPath) {
        try {
            InputStream map = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(map)));

            int column = 0;
            int row = 0;
            while(column < GamePanel.worldColumns && row < GamePanel.worldRows) {
                String line = bufferedReader.readLine();
                while(column < GamePanel.worldColumns) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[column]);
                    mapTileNum[column][row] = num;
                    column++;
                }
                if (column == GamePanel.worldColumns) {
                    column = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < GamePanel.worldColumns && worldRow < GamePanel.worldRows) {

            int tilesIndex = mapTileNum[worldColumn][worldRow];
            int worldX = worldColumn * GamePanel.tileSize;
            int worldY = worldRow * GamePanel.tileSize;
            int screenX = gamePanel.player.screenX + (worldX - gamePanel.player.getWorldX());
            int screenY = gamePanel.player.screenY + (worldY - gamePanel.player.getWorldY());

            g2.drawImage(tiles[0].image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
            if (tilesIndex != 11) {
                g2.drawImage(tiles[tilesIndex].image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
            }

            worldColumn++;
            if (worldColumn == GamePanel.worldColumns) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }

        public void drawTopPlayer(Graphics2D g2) {

            int worldColumn = 0;
            int worldRow = 0;

            while(worldColumn < GamePanel.worldColumns && worldRow < GamePanel.worldRows) {

                int tilesIndex = mapTileNum[worldColumn][worldRow];
                int worldX = worldColumn * GamePanel.tileSize;
                int worldY = worldRow * GamePanel.tileSize;
                int screenX = gamePanel.player.screenX + (worldX - gamePanel.player.getWorldX());
                int screenY = gamePanel.player.screenY + (worldY - gamePanel.player.getWorldY());

                if (tilesIndex == 11) {
                    g2.drawImage(tiles[tilesIndex].image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
                }

                worldColumn++;
                if (worldColumn == GamePanel.worldColumns) {
                    worldColumn = 0;
                    worldRow++;
                }
            }
    }
}
