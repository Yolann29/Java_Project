package views.customwidgets;

import javax.swing.*;
import java.awt.*;

public class PButton extends JButton {


    Color dialogColor = new Color(13, 81, 96);
    Color color;

    public PButton(String text){
        super(text);
//        this.color = color;
//        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(100, 50));
//        this.setBorderPainted(false);
        this.setBackground(new Color(0,0,0,0));
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setFont(new Font("Courier", Font.BOLD, 16));

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g.setColor(this.dialogColor);
        g2.fillRect(0, 0, getWidth(), getHeight());
//        g2.setColor(Color.WHITE);
//        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

//        //hover
//        if (getModel().isRollover()) {
//            g2.setColor(new Color(255, 255, 255, 100));
//            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
//        }

        super.paintComponent(g);
    }
}
