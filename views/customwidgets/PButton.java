package views.customwidgets;

import javax.swing.*;
import java.awt.*;

public class PButton extends JButton {


    Color dialogColor = new Color(40, 79, 104);
    public PButton(String text){
        super(text);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(100, 50));
        this.setBorderPainted(false);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setFont(new Font("Courier", Font.BOLD, 20));

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        g.setColor(dialogColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        if (getModel().isPressed()) {
            this.setForeground(Color.BLACK);
        } else if (getModel().isRollover()) {
            this.setForeground(Color.WHITE);
            dialogColor =  new Color(40, 79, 104).darker();
        } else {
            this.setForeground(Color.WHITE);
            dialogColor =  new Color(40, 79, 104);
        }
        super.paintComponent(g);
    }
}
