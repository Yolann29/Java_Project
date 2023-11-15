package views;

import controller.Arena;
import controller.entities.NotPlayer;
import controller.entities.Player;
import controller.handler.KeyHandler;
import controller.manager.Collision;
import models.fighters.Fighter;
import models.fighters.Warrior;
import models.types.Type;
import models.weapons.FireSword;
import models.weapons.IceSword;
import models.weapons.Weapon;
import views.arena.ActionsPanel;
import views.arena.ArenaPanel;
import views.arena.FightLauncher;
import views.arena.MerchantShop;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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

    final public KeyHandler keyHandler = new KeyHandler();
    final public Collision collision = new Collision(this);
    final public WorldPanel worldPanel;
    final private FightLauncher fightLauncher;
    final private MerchantShop merchantShop;

    private ArenaPanel arenaPanel;
    private ActionsPanel actionsPanel;
    private Arena arena;
    final public Player player;
    public NotPlayer encounter;
    public NotPlayer encounter2;
    public Fighter playerFighter;
    public Fighter encounterFighter;

    Thread gameLoop = null;
    public GamePanel() {

        encounterFighter = new Warrior("First Encounter", Type.WATER, new Random().nextInt(10) + 1);
        Weapon firesword = new FireSword();
        Weapon icesword = new IceSword();
        encounterFighter.pickWeapon(icesword);

        this.player = new Player(this, keyHandler, "Warrior");
        player.fighter = new Warrior("YOU", Type.FIRE, 50);
        player.fighter.pickWeapon(firesword);
        this.encounter = new NotPlayer(this, 0,0, 0,"left-right","Vagrant");
        this.encounter.fighter = encounterFighter;
        this.arena = new Arena(player.fighter, encounterFighter);
        this.actionsPanel = new ActionsPanel(arena);
        this.arenaPanel = new ArenaPanel(arena, actionsPanel, this, player, encounter);
        this.worldPanel = new WorldPanel(this,keyHandler, player);
        this.fightLauncher = new FightLauncher(keyHandler);
        this.merchantShop = new MerchantShop(this, worldPanel);

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.add(arenaPanel);
        this.add(actionsPanel);
        this.add(worldPanel, BorderLayout.CENTER);
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

            if (!keyHandler.pause) {
                update();
                repaint();

                try {

                    Thread.sleep(1000 / MAX_FPS);
                    imageCount++;
                    if (imageCount == 200) {
                        imageCount = 0;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {

                try {

                    Thread.sleep(1000 / MAX_FPS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public void update(){
        if (keyHandler.overWorld) {
            this.remove(actionsPanel);
            this.remove(arenaPanel);
            if (isPanelAdded(this, worldPanel)) {
                this.add(worldPanel, BorderLayout.CENTER);
            }

            if (worldPanel.fighterClose) {
                if (isPanelAdded(this, fightLauncher)) {
                    this.add(fightLauncher, BorderLayout.SOUTH);
                    if (worldPanel.npcEncounter != null) {
                        encounter = worldPanel.npcEncounter;
                    }
                }
            } else {
                this.remove(fightLauncher);
            }

            if (worldPanel.merchantClose) {
                if (isPanelAdded(this, merchantShop)) {
                    this.add(merchantShop, BorderLayout.SOUTH);
                }
            } else {
                this.remove(merchantShop);
            }
            this.revalidate();
            this.repaint();
            worldPanel.update();
        } else {
            if (worldPanel.npcEncounter != null) {
                encounter = worldPanel.npcEncounter;
            }
            this.remove(merchantShop);
            this.remove(fightLauncher);
            this.remove(worldPanel);
            if (isPanelAdded(this, arenaPanel) && isPanelAdded(this, actionsPanel)) {
                arena = new Arena(player.fighter, encounter.fighter);
                actionsPanel = new ActionsPanel(arena);
                arenaPanel = new ArenaPanel(arena, actionsPanel, this, player, encounter);

                this.add(arenaPanel, BorderLayout.CENTER);
                this.add(actionsPanel, BorderLayout.SOUTH);
            }
            this.revalidate();
            this.repaint();
            arena.update();
        }
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
