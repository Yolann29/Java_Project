package views.dialog;

import views.GamePanel;
import views.customwidgets.PButton;

import javax.swing.*;
import java.awt.*;

public class DialogActions extends JPanel {

    Color dialogColor = new Color(40, 79, 104);
    public DialogActions(){
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, 150));
        this.setLayout(new BorderLayout());

        JPanel buttonsPannel = new JPanel();
        buttonsPannel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonsPannel.setPreferredSize(new Dimension(250, 100));
        buttonsPannel.setBackground(Color.RED);

        PButton attack = new PButton("> Attack");
        PButton items = new PButton("> Items");
        PButton leave = new PButton("> Leave");

        buttonsPannel.add(attack);
        buttonsPannel.add(items);
        buttonsPannel.add(leave);

        this.add(buttonsPannel, BorderLayout.EAST);


//
//        this.add(attack);
//        this.add(items);
//        this.add(leave);


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g.setColor(dialogColor.brighter());
        g2.fillRoundRect(12, 15, GamePanel.FRAME_WIDTH - 25, 125, 20, 20);

    }



}
