package controller.handler;

import controller.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean top, bottom, right, left, jump, attack, pause;
    public boolean overWorld = true;

    GameState gs;

    public KeyHandler(GameState gs){
        this.gs = gs;
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Z){
            top = true;
        }

        if(code == KeyEvent.VK_S){
            bottom = true;
        }

        if(code == KeyEvent.VK_D){
            right = true;
        }

        if(code == KeyEvent.VK_Q){
            left = true;
        }

        if (code == KeyEvent.VK_SPACE) {
            jump = true;
        }

        if (code == KeyEvent.VK_A) {
            attack = true;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            pause = !pause;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Z){
            top = false;
        }

        if(code == KeyEvent.VK_S){
            bottom = false;
        }

        if(code == KeyEvent.VK_D){
            right = false;
        }

        if(code == KeyEvent.VK_Q){
            left = false;
        }


        if (code == KeyEvent.VK_SPACE) {
            jump = false;
        }

        if (code == KeyEvent.VK_A) {
            attack = false;
        }
    }
}
