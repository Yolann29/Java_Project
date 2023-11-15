package views;

import controller.entities.Entity;
import controller.entities.NotPlayableCharacter;
import controller.entities.Player;
import controller.handler.KeyHandler;
import controller.manager.TileManager;
import models.fighters.Merchant;
import models.fighters.Warrior;
import models.types.Type;
import models.weapons.ElectricGauntlet;
import models.weapons.FireSword;
import models.weapons.GroundSpear;
import models.weapons.IceSword;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WorldPanel extends JPanel {

    final GamePanel gamePanel;
    final KeyHandler keyHandler;
    final TileManager tileManager;

    final private Player player;
    final public NotPlayableCharacter npc1;
    final private NotPlayableCharacter npc2;
    final public NotPlayableCharacter scaredArcher;
    final private NotPlayableCharacter smartMagician;
    final private NotPlayableCharacter madWarrior;
    final private NotPlayableCharacter pursuer;
    final private NotPlayableCharacter merchant;

    public boolean fighterClose = false;
    public boolean merchantClose = false;
    public NotPlayableCharacter npcEncounter;
    public static ArrayList<NotPlayableCharacter> fightersNpc = new ArrayList<>();

    long initAnimation = 0;
    long currentTime = 0;

    public WorldPanel(GamePanel gamePanel, KeyHandler keyHandler, Player player) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.player = player;

        this.npc1 = new NotPlayableCharacter(gamePanel, 36*GamePanel.tileSize, 7*GamePanel.tileSize, 4,"left-right", "Archer");
        npc1.fighter = new Warrior("Archer", Type.ELECTRICITY, new Random().nextInt(10000) + 6000);
        npc1.fighter.pickWeapon(new IceSword());

        this.npc2 = new NotPlayableCharacter(gamePanel, 9*GamePanel.tileSize, 11*GamePanel.tileSize, 4,"circle", "Vagrant");
        npc2.fighter = new Warrior("Vagrant", Type.WATER, new Random().nextInt(500) + 100);
        npc2.fighter.pickWeapon(new ElectricGauntlet());

        this.scaredArcher = new NotPlayableCharacter(gamePanel, 12*GamePanel.tileSize, 14*GamePanel.tileSize, 6,"scared archer", "Archer");
        scaredArcher.fighter = new Warrior("Archer", Type.GROUND, new Random().nextInt(500) + 1500);
        scaredArcher.fighter.pickWeapon(new IceSword());

        this.smartMagician = new NotPlayableCharacter(gamePanel, 18*GamePanel.tileSize, GamePanel.tileSize, 4, "smart magician", "Magician");
        smartMagician.fighter = new Warrior("Magician", Type.WATER, new Random().nextInt(3000) + 2000);
        smartMagician.fighter.pickWeapon(new IceSword());

        this.madWarrior = new NotPlayableCharacter(gamePanel, 27*GamePanel.tileSize, 11*GamePanel.tileSize, 10, "mad warrior", "Warrior");
        madWarrior.fighter = new Warrior("Warrior", Type.FIRE, new Random().nextInt(2000) + 2000);
        madWarrior.fighter.pickWeapon(new FireSword());

        this.pursuer = new NotPlayableCharacter(gamePanel, 22*GamePanel.tileSize, 14*GamePanel.tileSize, 5,"pursuer", "Warrior");
        pursuer.fighter = new Warrior("Warrior", Type.FIRE, new Random().nextInt(800) + 1500);
        pursuer.fighter.pickWeapon(new GroundSpear());

        this.merchant = new NotPlayableCharacter(gamePanel, 18*GamePanel.tileSize, 9*GamePanel.tileSize, 0, null, "Vagrant");
        this.merchant.fighter = new Merchant("Marchand");
        this.tileManager = new TileManager(gamePanel);

//        fightersNpc.add(npc1);
        fightersNpc.add(npc2);
//        fightersNpc.add(scaredArcher);
//        fightersNpc.add(smartMagician);
//        fightersNpc.add(madWarrior);
//        fightersNpc.add(pursuer);

        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);
    }

    public void update() {
        if (TileManager.mapTileNum == TileManager.mapTileNum2) {
            npc1.update();
            npc2.update();
            scaredArcher.update();
            smartMagician.update();
            madWarrior.update();
            pursuer.update();

            boolean npcClose = false;

            npcClose |= distanceBetween(npc1);
            npcClose |= distanceBetween(npc2);
            npcClose |= distanceBetween(scaredArcher);
            npcClose |= distanceBetween(smartMagician);
            npcClose |= distanceBetween(madWarrior);
            npcClose |= distanceBetween(pursuer);
            npcClose |= distanceBetween(merchant);

            if (!npcClose) {
                fighterClose = false;
                npcEncounter = null;
                merchantClose = false;
            }
        }
        player.update();
    }


    public boolean distanceBetween(NotPlayableCharacter notplayer){
        if (player.getWorldX() < (notplayer.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (notplayer.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (notplayer.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (notplayer.getWorldY() - GamePanel.tileSize) && !notplayer.isDead && !player.isDead) {
            if (notplayer == merchant) {
                merchantClose = true;
                return true;
            }

            System.out.println("Fight begin between " + player.fighter.getName() + " and " + notplayer.fighter.getName());
            fighterClose = true;
            npcEncounter = notplayer;
            keyHandler.overWorld = false;
            return true;

//            if (keyHandler.attack) {
//                if (initAnimation == 0) {
//                    initAnimation = System.currentTimeMillis();
//                }
//                currentTime = System.currentTimeMillis();
//
//                if (currentTime >= initAnimation + 300) {
//                    fighterClose = true;
//                    npcEncounter = notplayer;
//                    keyHandler.overWorld = false;
//                    return true;
//                }
//
//            }

        }

        return false;
    }

    public NotPlayableCharacter drawOrder(Graphics2D g2, Entity entity) {
        if (player.getWorldX() < (entity.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (entity.getWorldX() - GamePanel.tileSize) && player.getWorldY() > entity.getWorldY()) {
            NotPlayableCharacter npc = null;
            if (entity instanceof NotPlayableCharacter) {
                npc = (NotPlayableCharacter) entity;
                npc.draw(g2);
                return npc;
            }
        }
        return null;
    }

    public void drawOverOrBehind() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (player.changeMap) {
            tileManager.changeMap();
            player.changeMap = false;

        } else if (TileManager.mapTileNum == TileManager.mapTileNum2) {
            tileManager.draw(g2);
            ArrayList<NotPlayableCharacter> npcs = new ArrayList<>(Arrays.asList(npc1, npc2, scaredArcher, smartMagician, madWarrior, pursuer));

            npcs.remove(drawOrder(g2, npc1));
            npcs.remove(drawOrder(g2, npc2));
            npcs.remove(drawOrder(g2, scaredArcher));
            npcs.remove(drawOrder(g2, smartMagician));
            npcs.remove(drawOrder(g2, madWarrior));
            npcs.remove(drawOrder(g2, pursuer));

            if (!player.isDead || !fightersNpc.isEmpty()) {
                player.draw(g2);
                for (NotPlayableCharacter npc : npcs) {
                    npc.draw(g2);
                }
                merchant.draw(g2);
                tileManager.drawTopPlayer(g2);
            } else {
                for (NotPlayableCharacter npc : npcs) {
                    npc.draw(g2);
                }
                merchant.draw(g2);
                tileManager.drawTopPlayer(g2);
                player.draw(g2);
            }
        } else {
            tileManager.draw(g2);
            player.draw(g2);
            tileManager.drawTopPlayer(g2);
        }

        if(fightersNpc.isEmpty()){
            g2.setColor(new Color(255, 255, 255, 153));
            g2.fillRect(0, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);
            g2.setColor(Color.GREEN);
            g2.setFont(new Font("Courier", Font.BOLD, 64));
            g2.drawString("You won !", GamePanel.FRAME_WIDTH/2 - 128, GamePanel.FRAME_HEIGHT/2 - 32);

        }
        tileManager.drawTopPlayer(g2);
        g2.dispose();
    }
}
