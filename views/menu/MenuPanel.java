package views.menu;

import views.GamePanel;
import views.customwidgets.HoverPanel;
import views.customwidgets.MenuText;
import views.customwidgets.PButton;
import views.customwidgets.PTextPane;
import views.menu.submenus.ArcherPanel;
import views.menu.submenus.MagePanel;
import views.menu.submenus.WarriorPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MenuPanel extends HoverPanel {

    private WarriorPanel warriorPanel;
    private ArcherPanel archerPanel;
    private MagePanel magePanel;
    private JPanel actionPanel;
    private PButton play;
    private Image texture, background;
    public static boolean magicianPicked;
    public static boolean warriorPicked;
    public static boolean archerPicked;

    public MenuPanel() {
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());

        loadRessources();

        this.warriorPanel = new WarriorPanel();
        this.archerPanel = new ArcherPanel();
        this.magePanel = new MagePanel();
        this.actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 2, 10, 10));
        this.play = new PButton("Play");

        MenuText textPane = new MenuText("\n  Choose your avatar !");
        textPane.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, 150));
        actionPanel.add(textPane);

        this.add(archerPanel, BorderLayout.WEST);
        this.add(warriorPanel, BorderLayout.CENTER);
        this.add(magePanel, BorderLayout.EAST);
        this.add(textPane, BorderLayout.SOUTH);
    }

    public void loadRessources(){
        try{
            background = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-fight.png")));
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-menu.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (magicianPicked || warriorPicked || archerPicked) {
            actionPanel.add(play);
        } else {
            actionPanel.remove(play);
        }
        actionPanel.revalidate();
        actionPanel.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(texture, 0, GamePanel.FRAME_HEIGHT - 150, null);
        g.drawImage(background, 0, 0, null);
    }
}
