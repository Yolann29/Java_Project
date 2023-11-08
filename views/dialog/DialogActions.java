package views.dialog;

import views.GamePanel;

import javax.swing.*;
import java.awt.*;

public class DialogActions extends JPanel {

    Color dialogColor = new Color(40, 79, 104);

    public DialogActions(){

        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH - 10, 125));

        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);

//      this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        g.fillRect(5, GamePanel.FRAME_HEIGHT - 110, GamePanel.FRAME_WIDTH - 10, 100);
    }



}
