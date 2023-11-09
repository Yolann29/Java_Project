package views;

import controller.handler.KeyHandler;
import views.dialog.DialogActions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    final public static int MAX_FPS = 60;
    final public static int FRAME_WIDTH = 750;
    final public static int FRAME_HEIGHT = 600;

    Thread gameLoop = null;
//    KeyHandler keyHandler = new KeyHandler();
    public GamePanel() {

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());

        this.add(new ArenaPanel(), BorderLayout.CENTER);
        this.add(new DialogActions(), BorderLayout.SOUTH);

    }

    public void startGameLoop(){

            if(gameLoop == null){
                gameLoop = new Thread(this);
                gameLoop.start();
            }
    }

    @Override
    public void run() {

        int imageCount = 0;

        while(gameLoop != null){

            update();
            repaint();

            try {
                Thread.sleep(1000/MAX_FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);


    }

}
