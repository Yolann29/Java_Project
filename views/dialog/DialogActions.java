package views.dialog;

import controller.Main;
import views.GamePanel;
import views.customwidgets.PButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Objects;

public class DialogActions extends JPanel {

    Color dialogColor = new Color(40, 79, 104);
    Image texture;
    public DialogActions(){
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, 150));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(new Color(141, 141, 141,255));
        this.setBorder(new EmptyBorder(20,20,20,20));

        JPanel buttonsPannel = new JPanel();
        buttonsPannel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonsPannel.setPreferredSize(new Dimension(275, 100));
        buttonsPannel.setBackground(new Color(0,0,0,0));

        PButton attack = new PButton("> Attack");
        PButton items = new PButton("> Items");
        PButton fake1 = new PButton("> Fake");
        PButton fake2 = new PButton("> Fake");

        JLabel text = new JLabel();
        JTextPane textPane = new JTextPane(){
            @Override
            public void repaint(long tm, int x, int y, int width, int height) {
            }
        };

        textPane.setText("Ceci est un text qui se wrap automatiquement, youpii");
        textPane.setFont(new Font("Courier", Font.BOLD, 20));
        textPane.setBackground(new Color(0,0,0,0));
        textPane.setForeground(Color.WHITE);
        textPane.setHighlighter(null);

        buttonsPannel.add(attack);
        buttonsPannel.add(items);
//        buttonsPannel.add(fake1);
//        buttonsPannel.add(fake2);

        this.add(textPane, BorderLayout.CENTER);
        this.add(buttonsPannel, BorderLayout.EAST);

        loadRessources();

    }

    public void loadRessources(){

        try{
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-menu.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.drawImage(texture, 0, 0, null);
//        g.setColor(Color.WHITE);
//        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
//        g2.fillRoundRect(12, 15, GamePanel.FRAME_WIDTH - 320, 125, 20, 20);

    }



}
