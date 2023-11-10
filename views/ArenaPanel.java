package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ArenaPanel extends JPanel {

    Image plateforme;

    public ArenaPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        loadRessources();


    }

    public void loadRessources() {

        try {
            plateforme = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/plateforme.png")));
            System.out.println("Image bien récupéré");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //antialisaing
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(plateforme, GamePanel.FRAME_WIDTH - 350, 150, null);
        g.drawImage(plateforme, 0, 350, null);

    }
}

