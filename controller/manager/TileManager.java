package controller.manager;

import views.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        getTilesImages();
        this.mapTileNum = new int[GamePanel.columns][GamePanel.rows];
        getMap();
    }

    public void getTilesImages() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-cobble.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/floor/floor-water.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMap() {
        try {
            InputStream map = getClass().getResourceAsStream("/textures/floor/map.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(map)));

            int column = 0;
            int row = 0;
            while(column < GamePanel.columns && row < GamePanel.rows) {
                String line = bufferedReader.readLine();
                while(column < GamePanel.columns) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[column]);
                    mapTileNum[column][row] = num;
                    column++;
                }
                if (column == GamePanel.columns) {
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

        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        System.out.println("ici");
        while(column < GamePanel.columns && row < GamePanel.rows) {

            int tilesIndex = mapTileNum[column][row];

            g2.drawImage(tiles[tilesIndex].image, x, y, 64, 64, null);
            x += 64;
            column++;
            if (column == GamePanel.columns) {
                column = 0;
                x = 0;
                y += 64;
                row++;
            }
        }
    }
}
