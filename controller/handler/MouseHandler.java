package controller.handler;

import views.customwidgets.HoverPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    public boolean isHovered = false, changed = false, clicked = false;

    @Override
    public void mouseEntered(MouseEvent e) {
        isHovered = true;
        changed = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isHovered = false;
        changed = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clicked = !clicked;
        System.out.println("Clicked state: " + clicked);
    }
}
