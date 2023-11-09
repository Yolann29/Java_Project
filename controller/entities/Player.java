package controller.entities;

import controller.handler.KeyHandler;
import views.GamePanel;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler KeyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.KeyHandler = keyHandler;
    }
}
