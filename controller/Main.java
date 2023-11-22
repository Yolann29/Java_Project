package controller;

import controller.handler.KeyHandler;
import views.GamePanel;
import javax.swing.*;

public class Main implements Runnable{

    static Thread gameLoop = null;
    public static int imageCount;
    static Game game;

    final public static int MAX_FPS = 60;

    public static void main(String[] args) {

        game = new Game();
        Main.startGameLoop();

    }

    public static void startGameLoop(){

        if (gameLoop == null) {
            gameLoop = new Thread(new Main());
            gameLoop.start();
        }
    }


    @Override
    public void run() {

        imageCount = 0;

        System.out.println("Main -> Start Game Loop");
        while(gameLoop != null){

            if (!game.getKeyH().pause) {
                game.update();
                game.getGp().repaint();
            }

            try {

                Thread.sleep(1000 / MAX_FPS);
                imageCount++;
                if (imageCount == 200) {
                    imageCount = 0;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}