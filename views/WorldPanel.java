package views;

import controller.entities.Player;
import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    final private Player player;

    public WorldPanel(Player player) {
        this.player = player;
        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}
