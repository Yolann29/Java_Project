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

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {

    final public static int TileSize = 32;
    final public static int columns = 23; //736
    final public static int rows = 18; //576
    final public static int WorldHeight = rows*TileSize;
    final public static int WorldWidth = columns*TileSize;
    final public static int MAX_FPS = 60;
    final public static int FRAME_WIDTH = 750;
    final public static int FRAME_HEIGHT = 600;
    final private KeyHandler keyHandler = new KeyHandler();
    final private WorldPanel worldPanel;
    final private ArenaPanel arenaPanel;
    final private DialogActions dialogActions;
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
        Player nathanPlayer = new Player(this, keyHandler);
        this.worldPanel = new WorldPanel(nathanPlayer);
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        dialogActions = new DialogActions(arena);
        arenaPanel = new ArenaPanel(arena, dialogActions);
        this.add(worldPanel, BorderLayout.CENTER);
        this.add(arenaPanel, BorderLayout.CENTER);
        this.add(dialogActions, BorderLayout.SOUTH);
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
        if (keyHandler.changing) {
            if (keyHandler.changePanel) {
                this.remove(arenaPanel);
                this.remove(dialogActions);
                this.add(worldPanel, BorderLayout.CENTER);
                this.revalidate();
                this.repaint();
            } else {
                this.remove(worldPanel);
                this.add(arenaPanel, BorderLayout.CENTER);
                this.add(dialogActions, BorderLayout.SOUTH);
                this.revalidate();
                this.repaint();
            }
        }
        worldPanel.update();
        arena.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

}
