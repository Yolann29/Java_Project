package controller.manager;

import controller.Game;
import views.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class TileManager {

    Game game;
    public static Tile[] tiles;
    public static int[][] mapTileNum;
    public static int[][] mapTileNum1;
    public static int[][] mapTileNum2;

    public TileManager(Game game) {
        this.game = game;
        tiles = new Tile[13];
        getTilesImages();
        mapTileNum = new int[GamePanel.worldColumns][GamePanel.worldRows];
        mapTileNum1 = new int[GamePanel.worldColumns][GamePanel.worldRows];
        mapTileNum2 = new int[GamePanel.worldColumns][GamePanel.worldRows];

        int column = 0;
        int row = 0;
        for (int i = 0; i < mapTileNum1.length; i++) {
            for (int j = 0; j < mapTileNum1[0].length; j++) {
                mapTileNum1[i][j] = 29;
            }
        }
        getMap("/textures/floor/map.csv");
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
            tiles[7].door = true;

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/wall-side.png")));
            tiles[8].collision = true;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-grass.png")));
            tiles[9].door = true;

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/tree-bottom.png")));
            tiles[10].collision = true;

            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/tree-top.png")));

            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-cobble.png")));
            tiles[12].collision = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMap(String mapPath) {
        try {
            InputStream map = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(map)));

            Random random = new Random();
            int column = 0;
            int row = 0;
            ArrayList<Integer> tilesRandom = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 2, 11, 11));
            ArrayList<Integer> tilesRandom2 = new ArrayList<Integer>(Arrays.asList(0, 2));
            while(column < GamePanel.worldColumns && row < GamePanel.worldRows) {
                String line = bufferedReader.readLine();
                while(column < GamePanel.worldColumns) {
                    String[] numbers = line.split(",");
                    int num = Integer.parseInt(numbers[column]);
                    mapTileNum[column][row] = num;
                    if (row == 0 || column == 0 || column == GamePanel.worldColumns - 1 || row == GamePanel.worldRows - 1) {
                        if (num == 2 || num == 5 || num == 12) {
                            mapTileNum1[column][row] = 1;
                        } else {
                            mapTileNum1[column][row] = num;
                        }
                    } else {
                        if (row >= 11 && (column == 1 || column == GamePanel.worldColumns - 2)) {
                            mapTileNum1[column][row] = 0;
                        }
                        int index = random.nextInt(tilesRandom.size());
                        int index2 = random.nextInt(tilesRandom2.size());
                        if (mapTileNum1[column][row] == 29) {
                            mapTileNum1[column][row] = tilesRandom.get(index);
                            if (mapTileNum1[column][row] == 2) {
                                if (mapTileNum1[column][row + 1] == 29) {
                                    mapTileNum1[column][row + 1] = tilesRandom2.get(index2);
                                }
                                if (mapTileNum1[column + 1][row] == 29) {
                                    index2 = random.nextInt(tilesRandom2.size());
                                    mapTileNum1[column + 1][row] = tilesRandom2.get(index2);
                                }
                                if (mapTileNum1[column + 1][row + 1] == 29) {
                                    index2 = random.nextInt(tilesRandom2.size());
                                    mapTileNum1[column + 1][row + 1] = tilesRandom2.get(index2);
                                }
                            }
                            if (row == GamePanel.worldRows - 2 && mapTileNum1[column][row] == 11) {
                                mapTileNum1[column][row] = 0;
                            }
                            if (row == 10 && (column == 1 || column == GamePanel.worldColumns - 2)) {
                                mapTileNum1[column][row] = 0;
                            }
                        }
                        if (mapTileNum1[column][row] == 11) {
                            mapTileNum1[column][row + 1] = 10;
                        }
                    }
                    column++;
                }
                if (column == GamePanel.worldColumns) {
                    column = 0;
                    row++;
                }
            }
            mapTileNum2 = mapTileNum;
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
            int screenX = game.getPlayer().screenX + (worldX - game.getPlayer().getWorldX());
            int screenY = game.getPlayer().screenY + (worldY - game.getPlayer().getWorldY());

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

    public void changeMap() {
        if (mapTileNum == mapTileNum1)
            mapTileNum = mapTileNum2;
        else {
            mapTileNum = mapTileNum1;
        }
    }

    public void drawTopPlayer(Graphics2D g2) {

        int worldColumn = 0;
        int worldRow = 0;

        while(worldColumn < GamePanel.worldColumns && worldRow < GamePanel.worldRows) {

            int tilesIndex = mapTileNum[worldColumn][worldRow];
            int worldX = worldColumn * GamePanel.tileSize;
            int worldY = worldRow * GamePanel.tileSize;
            int screenX = game.getPlayer().screenX + (worldX - game.getPlayer().getWorldX());
            int screenY = game.getPlayer().screenY + (worldY - game.getPlayer().getWorldY());

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
