package views;

import controller.entities.Player;
import controller.manager.TileManager;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    final GamePanel gamePanel;
    final private Player player;
    final private TileManager tileManager;

    public WorldPanel(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.tileManager = new TileManager(gamePanel);
        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
