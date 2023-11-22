package views.customwidgets;

import controller.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class HoverPanel extends JPanel {

    protected MouseHandler mouseH;

    public HoverPanel() {
        this.mouseH = new MouseHandler();
        addMouseListener(mouseH);
    }

    public void update() {
        if (mouseH.changed) {
            this.repaint();
            mouseH.changed = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
