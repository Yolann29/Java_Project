package views.dialog;

import controller.handler.KeyHandler;
import views.GamePanel;
import views.customwidgets.PButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class FightLauncher extends JPanel {

    private KeyHandler keyHandler;
    private Image texture;

    public FightLauncher(KeyHandler keyHandler) {
        this.setBackground(new Color(141, 141, 141,255));

        loadRessources();

        JPanel fightButton = new JPanel();
        fightButton.setPreferredSize(new Dimension(100, 50));
        fightButton.setBackground(new Color(0,0,0,0));

        PButton fight = new PButton("Fight");
        fightButton.add(fight);
        fight.addActionListener(e -> {
            keyHandler.overWorld = false;
        });
        fightButton.revalidate();

        this.add(fightButton);
    }

    public void loadRessources(){

        try{
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-menu.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(texture, 0, 0, null);
    }
}
