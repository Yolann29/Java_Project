package views.menu.submenus;

import controller.manager.FighterClasseManager;
import models.Action;
import models.Role;
import views.GamePanel;
import views.customwidgets.HoverPanel;
import views.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MagePanel extends HoverPanel {

    public MagePanel(){
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH/3, GamePanel.FRAME_HEIGHT));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (mouseH.clicked) {
            if (!MenuPanel.magicianPicked) {
                MenuPanel.magicianPicked = true;
                MenuPanel.warriorPicked = false;
                MenuPanel.archerPicked = false;
                MenuPanel.classePlayer = Role.MAGICIAN;
            } else {
                MenuPanel.magicianPicked = false;
            }
            mouseH.clicked = false;
        }
        if (MenuPanel.magicianPicked) {
            this.setBackground(new Color(200,0,150, 40).brighter());
        } else {
            this.setOpaque(true);
            if (mouseH.isHovered) {
                this.setBackground(new Color(200,0,150, 140).brighter());
            } else {
                this.setBackground(new Color(200,0,150, 140).darker());
            }
        }


        Objects.requireNonNull(FighterClasseManager.returnRightAnimation(Role.MAGICIAN, Action.IDLE)).paint(g2, GamePanel.FRAME_WIDTH/6 - 64, GamePanel.FRAME_HEIGHT/2 - 128, 2 * GamePanel.tileSize, 2 * GamePanel.tileSize, false);
    }
}
