package views.menu.submenus;

import controller.manager.FighterClasseManager;
import models.Action;
import models.Role;
import views.GamePanel;
import views.customwidgets.HoverPanel;
import views.menu.MenuPanel;

import java.awt.*;
import java.util.Objects;

public class ArcherPanel extends HoverPanel {

    public ArcherPanel(){
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH/3, GamePanel.FRAME_HEIGHT));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (mouseH.clicked) {
            if (!MenuPanel.archerPicked) {
                MenuPanel.magicianPicked = false;
                MenuPanel.warriorPicked = false;
                MenuPanel.archerPicked = true;
            } else {
                MenuPanel.archerPicked = false;
            }
            mouseH.clicked = false;
        }
        if (MenuPanel.archerPicked) {
            this.setOpaque(false);
        } else {
            this.setOpaque(true);
            if (mouseH.isHovered) {
                this.setBackground(Color.GREEN.brighter());
            } else {
                this.setBackground(Color.GREEN.darker());
            }
        }

        Objects.requireNonNull(FighterClasseManager.returnRightAnimation(Role.ARCHER, Action.IDLE)).paint(g2, GamePanel.FRAME_WIDTH/6 - 64, GamePanel.FRAME_HEIGHT/2 - 128, 2 * GamePanel.tileSize, 2 * GamePanel.tileSize, false);
    }
}
