package views;

import controller.Arena;
import controller.entities.Player;
import controller.handler.KeyHandler;
import controller.manager.Collision;
import models.fighters.Fighter;
import models.fighters.Warrior;
import models.items.DamageBooster;
import models.items.HealPotion;
import models.items.Item;
import models.types.Type;
import models.weapons.FireSword;
import models.weapons.IceSword;
import models.weapons.Weapon;
import views.customwidgets.PButton;
import views.dialog.DialogActions;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {

    final public static int tileSize = 64;
    final public static int columns = 23; //736
    final public static int rows = 18; //576
    final public static int worldColumns = 38;
    final public static int worldRows = 17;
    final public static int worldWidth = worldColumns * tileSize;
    final public static int worldHeight = worldRows * tileSize;

    final public static int MAX_FPS = 60;
    final public static int FRAME_WIDTH = 750;
    final public static int FRAME_HEIGHT = 600;
    public int imageCount;

    final private KeyHandler keyHandler = new KeyHandler();
    final public Collision collision = new Collision(this);
    final private WorldPanel worldPanel;
    final private JPanel fightLauncher;

    private ArenaPanel arenaPanel;
    private DialogActions dialogActions;
    private Arena arena;
    final public Player player;
    public Fighter playerFighter;

    Thread gameLoop = null;
    public GamePanel() {

        Fighter playerFighter = new Warrior("Nathan", Type.FIRE);
        Fighter encounter = new Warrior("First Encounter", Type.WATER);
        Weapon firesword = new FireSword();
        Weapon icesword = new IceSword();
        Item healPotion = new HealPotion();
        Item damageBooster = new DamageBooster();
        playerFighter.pickWeapon(firesword);
        encounter.pickWeapon(icesword);
        playerFighter.pickItems(Arrays.asList(healPotion, damageBooster));

        this.arena = new Arena(playerFighter, encounter);
        this.dialogActions = new DialogActions(arena);
        this.arenaPanel = new ArenaPanel(arena, dialogActions);
        this.player = new Player(this, keyHandler, "Vagrant");
        this.worldPanel = new WorldPanel(this,keyHandler, player);

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        this.add(arenaPanel);
        this.add(dialogActions);
        this.add(worldPanel, BorderLayout.CENTER);

        fightLauncher = new JPanel();
        fightLauncher.setLayout(new BorderLayout());
        PButton fight = new PButton("Fight");
        fightLauncher.add(fight);
        fight.addActionListener(e -> {
            keyHandler.overWorld = false;
        });
        fightLauncher.revalidate();
    }

    public void startGameLoop(){

            if(gameLoop == null){
                gameLoop = new Thread(this);
                gameLoop.start();
            }
    }

    @Override
    public void run() {

        imageCount = 0;

        while(gameLoop != null){
            update();
            repaint();

            try {
                Thread.sleep(1000/MAX_FPS);
                imageCount++;
                if (imageCount == 200) {
                    imageCount = 0;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){
        if (keyHandler.overWorld) {
            this.remove(dialogActions);
            this.remove(arenaPanel);
            if (isPanelAdded(this, worldPanel)) {
                this.add(worldPanel, BorderLayout.CENTER);
            }
            if (worldPanel.fighterClose) {
                if (isPanelAdded(this, fightLauncher)) {
                    this.add(fightLauncher, BorderLayout.SOUTH);
                }
            } else {
                this.remove(fightLauncher);
            }
        } else {
            this.remove(fightLauncher);
            this.remove(worldPanel);
            if (isPanelAdded(this, arenaPanel) && isPanelAdded(this, dialogActions)) {
//                arena = new Arena(playerFighter, worldPanel.fighterEncountered);
//                dialogActions = new DialogActions(arena);
//                arenaPanel = new ArenaPanel(arena, dialogActions);

                this.add(arenaPanel, BorderLayout.CENTER);
                this.add(dialogActions, BorderLayout.SOUTH);
            }
        }
        this.revalidate();
        this.repaint();
        worldPanel.update();
        arena.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    private static boolean isPanelAdded(Container container, Component panel) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component component = container.getComponent(i);
            if (component == panel) {
                return false;
            }
        }
        return true;
    }
}
