package views.customwidgets;

import controller.manager.AudioManager;
import views.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

public class PButton extends JButton {

    Color dialogColor = new Color(13, 81, 96);

    PButton pbutton;

    public PButton(String text){
        super(text);
        this.pbutton = this;
        this.setPreferredSize(new Dimension(120, 60));
        this.setBackground(new Color(0,0,0,0));
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setFont(GamePanel.gloucester.deriveFont(10F));
        AudioManager hoverBtnSound = new AudioManager("widgets","hoverButton.wav");
        AudioManager clickBtnSound = new AudioManager("widgets","clickButton.wav");
        hoverBtnSound.setVolume(5);
        clickBtnSound.setVolume(5);


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(pbutton.isEnabled()) clickBtnSound.playSound();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(pbutton.isEnabled()) hoverBtnSound.playSound();
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

    }



    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g.setColor(this.dialogColor);
        g2.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }
}
