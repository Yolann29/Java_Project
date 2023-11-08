package views;

import controller.handler.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final public static int FRAME_WIDTH = 650;
    final public static int FRAME_HEIGHT = 600;
    final public static int MAX_FPS = 60;
    private int characterPositionX = 100;
    private int characterPositionY = 100;

    //TEMPORAIRE POUR FOLLOWUP
    private double tempCharacterPositionX = 300;
    private long startTime = System.currentTimeMillis();

    private final int velocity = 10;

    Thread gameLoop = null;
    KeyHandler keyHandler = new KeyHandler();

    public GamePanel() {

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandler);
        this.setFocusable(true);


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
//            imageCount++;

            try {
//                System.out.println("imagecount : " + imageCount);
                Thread.sleep(1000/MAX_FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){

        long currentTime = System.currentTimeMillis();
        double elapsedTime = (currentTime - startTime) / 1000.0; // Convertir en secondes

        // Utiliser sin pour obtenir une position douce de droite à gauche
        tempCharacterPositionX = Math.sin(elapsedTime) * 100 + 300;

        System.out.println(tempCharacterPositionX);


       if(keyHandler.top){
           characterPositionY = characterPositionY - velocity;
       } else if (keyHandler.right){
           characterPositionX = characterPositionX + velocity;
       } else if (keyHandler.left){
           characterPositionX = characterPositionX - velocity;
       } else if (keyHandler.bottom){
           characterPositionY = characterPositionY + velocity;
       }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.red);
        g.fillRect((int) tempCharacterPositionX, 0, 100, 50);
        g.setColor(Color.blue);
        g.fillRect(characterPositionX, characterPositionY, 50, 50);
    }

}
