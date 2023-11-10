package controller.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean top, bottom, right, left;
    public boolean changing = false, changePanel = false;

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            top = true;
        }

        if(code == KeyEvent.VK_DOWN){
            bottom = true;
        }

        if(code == KeyEvent.VK_RIGHT){
            right = true;
        }

        if(code == KeyEvent.VK_LEFT){
            left = true;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            changePanel = !changePanel;
            changing = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            top = false;
        }

        if(code == KeyEvent.VK_DOWN){
            bottom = false;
        }

        if(code == KeyEvent.VK_RIGHT){
            right = false;
        }

        if(code == KeyEvent.VK_LEFT){
            left = false;
        }

        if(code == KeyEvent.VK_ESCAPE){
            changing = false;
        }

    }
}
