package views;

import controller.Arena;
import controller.entities.Player;
import controller.handler.KeyHandler;
import models.fighters.Fighter;
import models.fighters.Warrior;
import models.items.DamageBooster;
import models.items.HealPotion;
import models.items.Item;
import models.types.Type;
import models.weapons.FireSword;
import models.weapons.IceSword;
import models.weapons.Weapon;
import views.dialog.DialogActions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {

    final public static int MAX_FPS = 60;
    final public static int FRAME_WIDTH = 750;
    final public static int FRAME_HEIGHT = 600;
    final private KeyHandler keyHandler = new KeyHandler();
    final private WorldPanel worldPanel;
    final private Player nathanPlayer;
//    final private ArenaPanel arenaPanel;
//    final private DialogActions dialogActions;
    final private Arena arena;

    Thread gameLoop = null;
//    KeyHandler keyHandler = new KeyHandler();
    public GamePanel() {

        Fighter nathan = new Warrior("Nathan", Type.FIRE);
        Fighter victor = new Warrior("Victor", Type.ELECTRICITY);
        Weapon firesword = new FireSword();
        Weapon icesword = new IceSword();
        Item healPotion = new HealPotion();
        Item damageBooster = new DamageBooster();
        nathan.pickWeapon(firesword);
        victor.pickWeapon(icesword);
        nathan.pickItems(Arrays.asList(healPotion, damageBooster));

        arena = new Arena(nathan, victor);
        nathanPlayer = new Player(this, keyHandler);
        this.worldPanel = new WorldPanel(nathanPlayer);

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());

        this.add(worldPanel, BorderLayout.CENTER);
//        arenaPanel = new ArenaPanel();
//        dialogActions = new DialogActions(arena);
//        this.add(arenaPanel, BorderLayout.CENTER);
//        this.add(dialogActions, BorderLayout.SOUTH);
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
            worldPanel.update();
//        if (keyHandler.changePanel) {
//            this.removeAll();
//            this.add(worldPanel, BorderLayout.CENTER);
//        } else {
//            this.removeAll();
//            this.add(arenaPanel, BorderLayout.CENTER);
//            this.add(dialogActions, BorderLayout.SOUTH);
//        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

}
