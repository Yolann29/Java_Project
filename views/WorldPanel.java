package views;

import controller.entities.NotPlayer;
import controller.entities.Player;
import controller.handler.KeyHandler;
import controller.manager.TileManager;
import models.fighters.Fighter;
import models.fighters.Warrior;
import models.types.Type;
import models.weapons.FireSword;
import models.weapons.IceSword;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {

    final GamePanel gamePanel;
    final KeyHandler keyHandler;
    final TileManager tileManager;

    final private Player player;
    final public NotPlayer npc1;
    final private NotPlayer npc2;
    final public NotPlayer npc3;
    final private NotPlayer npc4;
    final private NotPlayer npc5;
    final private NotPlayer pursuer;
    final private NotPlayer merchant;

    public boolean fighterClose = false;
    public boolean merchantClose = false;
    public Fighter fighterEncountered;
    public NotPlayer npcEncounter;

    public WorldPanel(GamePanel gamePanel, KeyHandler keyHandler, Player player) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.player = player;

        this.npc1 = new NotPlayer(gamePanel, 36*GamePanel.tileSize, 7*GamePanel.tileSize, "left-right", "Archer");
        npc1.fighter = new Warrior("Archer", Type.ELECTRICITY);
        npc1.fighter.pickWeapon(new IceSword());

        this.npc2 = new NotPlayer(gamePanel, 9*GamePanel.tileSize, 11*GamePanel.tileSize, "circle", "Vagrant");
        npc2.fighter = new Warrior("Vagrant", Type.WATER);
        npc2.fighter.pickWeapon(new IceSword());

        this.npc3 = new NotPlayer(gamePanel, 12*GamePanel.tileSize, 14*GamePanel.tileSize, "up-down", "Archer");
        npc3.fighter = new Warrior("Archer", Type.GROUND);
        npc3.fighter.pickWeapon(new IceSword());

        this.npc4 = new NotPlayer(gamePanel, 18*GamePanel.tileSize, GamePanel.tileSize, null, "Magician");
        npc4.fighter = new Warrior("Magician", Type.WATER);
        npc4.fighter.pickWeapon(new IceSword());

        this.npc5 = new NotPlayer(gamePanel, 27*GamePanel.tileSize, 11*GamePanel.tileSize, "attack", "Warrior");
        npc5.fighter = new Warrior("Warrior", Type.FIRE);
        npc5.fighter.pickWeapon(new FireSword());

        this.pursuer = new NotPlayer(gamePanel, 24*GamePanel.tileSize, 11*GamePanel.tileSize, "smart", "Warrior");
//        this.fighterEncounteredPursuer = new Warrior("Warrior", Type.FIRE);

        this.merchant = new NotPlayer(gamePanel, 18*GamePanel.tileSize, 9*GamePanel.tileSize, null, "Vagrant");
        this.tileManager = new TileManager(gamePanel);
        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);
    }

    public void update() {
        npc1.update();
        npc2.update();
        npc3.update();
        npc5.update();
//        pursuer.update();
        player.update();

            boolean npcClose = false;

            npcClose |= distanceBetween(npc1);
            npcClose |= distanceBetween(npc2);
            npcClose |= distanceBetween(npc3);
            npcClose |= distanceBetween(npc4);
            npcClose |= distanceBetween(npc5);
            npcClose |= distanceBetween(merchant);

            if (!npcClose) {
                fighterClose = false;
                fighterEncountered = null;
                npcEncounter = null;
                merchantClose = false;
            }
        }


    public boolean distanceBetween(NotPlayer notplayer){
        if (player.getWorldX() < (notplayer.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (notplayer.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (notplayer.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (notplayer.getWorldY() - GamePanel.tileSize) && !notplayer.isDead && !player.isDead) {
            if (notplayer == merchant) {
                merchantClose = true;
                return true;
            }
            fighterClose = true;
            npcEncounter = notplayer;
            if (fighterEncountered == null) {
                fighterEncountered = notplayer.fighter;

            }
            return true;

        }
        return false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (player.changeMap) {
            tileManager.changeMap();
            tileManager.draw(g2);
            player.draw(g2);
            player.changeMap = false;
        } else {
            tileManager.draw(g2);

            if (player.getWorldX() < (npc1.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc1.getWorldX() - GamePanel.tileSize) && player.getWorldY() < npc1.getWorldY()) {
                npc2.draw(g2);
                npc3.draw(g2);
                npc4.draw(g2);
                player.draw(g2);
                npc1.draw(g2);

            } else if (player.getWorldX() < (npc2.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc2.getWorldX() - GamePanel.tileSize) && player.getWorldY() < npc2.getWorldY()) {
                npc1.draw(g2);
                npc3.draw(g2);
                npc4.draw(g2);
                player.draw(g2);
                npc2.draw(g2);

            } else if (player.getWorldX() < (npc3.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc3.getWorldX() - GamePanel.tileSize) && player.getWorldY() < npc3.getWorldY()) {
                npc1.draw(g2);
                npc2.draw(g2);
                npc4.draw(g2);
                player.draw(g2);
                npc3.draw(g2);

            } else if (player.getWorldX() < (npc4.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc4.getWorldX() - GamePanel.tileSize) && player.getWorldY() < npc4.getWorldY()) {
                npc1.draw(g2);
                npc2.draw(g2);
                npc3.draw(g2);
                player.draw(g2);
                npc4.draw(g2);

            } else {
                npc1.draw(g2);
                npc2.draw(g2);
                npc3.draw(g2);
                npc4.draw(g2);
                player.draw(g2);
            }
        }

        npc5.draw(g2);
        merchant.draw(g2);

        tileManager.drawTopPlayer(g2);
        pursuer.draw(g2);
        g2.dispose();
    }
}
