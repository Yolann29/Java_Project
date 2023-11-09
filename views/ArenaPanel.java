package views;

import controller.Arena;
import models.fighters.Warrior;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ArenaPanel extends JPanel {

    Image image;
    Image fighter1;
    Arena arena;

    public ArenaPanel(Arena arena) {
        this.arena = arena;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        loadRessources();


    }

    public void loadRessources() {

        try {
            image = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-fight.png")));

            if (arena.getFighter1() instanceof Warrior) {
                fighter1 = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/sprites/warrior/warrior-right-1.png")));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(image, 0, 0, null);
        g2.drawImage(fighter1, 100, 270, fighter1.getWidth(null) * 3, fighter1.getHeight(null) * 3, null);


    }
}

