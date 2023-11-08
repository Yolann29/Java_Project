package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static int FRAME_WIDTH = 650;
    public static int FRAME_HEIGHT = 600;
    public static int MAX_FPS = 60;
    private boolean top, bottom, right, left;

    Thread gameLoop = null;

    public GamePanel() {

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(this);

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

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            top = true;
            System.out.println(top);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            bottom = true;
            System.out.println(bottom);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
            System.out.println(right);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
            System.out.println(left);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            top = false;
            System.out.println(top);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            bottom = false;
            System.out.println(bottom);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
            System.out.println(right);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
            System.out.println(left);
        }
    }
}
