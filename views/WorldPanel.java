package views;

import controller.entities.NotPlayer;
import controller.entities.Player;
import controller.manager.TileManager;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    final GamePanel gamePanel;
    final private Player player;
    final private NotPlayer npc1;
    final private NotPlayer npc2;
    final private NotPlayer npc3;
    final private NotPlayer npc4;
    final private NotPlayer npc5;
    final private TileManager tileManager;

    public WorldPanel(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.npc1 = new NotPlayer(gamePanel, 36*GamePanel.tileSize, 7*GamePanel.tileSize, "left-right");
        this.npc2 = new NotPlayer(gamePanel, 9*GamePanel.tileSize, 11*GamePanel.tileSize, "circle");
        this.npc3 = new NotPlayer(gamePanel, 12*GamePanel.tileSize, 14*GamePanel.tileSize, "up-down");
        this.npc4 = new NotPlayer(gamePanel, 18*GamePanel.tileSize, GamePanel.tileSize, null);
        this.npc5 = new NotPlayer(gamePanel, 27*GamePanel.tileSize, 11*GamePanel.tileSize, null);
        this.tileManager = new TileManager(gamePanel);
        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);
    }

    public void update() {
        npc1.update();
        npc2.update();
        npc3.update();
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        npc1.draw(g2);
        npc2.draw(g2);
        npc3.draw(g2);
        npc4.draw(g2);
        npc5.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
