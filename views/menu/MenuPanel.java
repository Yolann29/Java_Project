package views.menu;

import views.GamePanel;
import views.menu.submenus.ArcherPanel;
import views.menu.submenus.MagePanel;
import views.menu.submenus.WarriorPanel;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());

        WarriorPanel warriorPanel = new WarriorPanel();
        ArcherPanel archerPanel = new ArcherPanel();
        MagePanel magePanel = new MagePanel();

        this.add(archerPanel, BorderLayout.WEST);
        this.add(warriorPanel, BorderLayout.CENTER);
        this.add(magePanel, BorderLayout.EAST);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        g.setColor(Color.WHITE);
//        //FIRST PANEL
//        g.fillRoundRect(5, 5, GamePanel.FRAME_WIDTH / 3 - 10, GamePanel.FRAME_HEIGHT - 200, 20, 20);
//        //SECOND PANEL
//        g.fillRoundRect(255, 5, GamePanel.FRAME_WIDTH / 3 - 10, GamePanel.FRAME_HEIGHT - 200, 20, 20);
//        //THIRD PANEL
//        g.fillRoundRect(505, 5, GamePanel.FRAME_WIDTH / 3 - 10, GamePanel.FRAME_HEIGHT - 200, 20, 20);
//        //FOURTH PANEL
//        g.fillRoundRect(5, GamePanel.FRAME_HEIGHT - 185, GamePanel.FRAME_WIDTH - 13, 180, 20, 20);

    }
}
