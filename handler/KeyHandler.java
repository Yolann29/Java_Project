package handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            System.out.println("touche up pressed");
        }

        if(code == KeyEvent.VK_DOWN){
            System.out.println("touche down pressed");
        }

        if(code == KeyEvent.VK_RIGHT){
            System.out.println("touche right pressed");
        }

        if(code == KeyEvent.VK_LEFT){
            System.out.println("touche left pressed");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            System.out.println("touche up released");
        }

        if(code == KeyEvent.VK_DOWN){
            System.out.println("touche down released");
        }

        if(code == KeyEvent.VK_RIGHT){
            System.out.println("touche right released");
        }

        if(code == KeyEvent.VK_LEFT){
            System.out.println("touche left released");
        }

    }
}
