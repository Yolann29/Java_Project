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
    final private NotPlayer npc1;
    final private NotPlayer npc2;
    final private NotPlayer npc3;
    final private NotPlayer npc4;
    final private NotPlayer npc5;
    final private NotPlayer merchant;

    public boolean fighterClose = false;
    public boolean merchantClose = false;
    Fighter fighterEncountered;
    Fighter fighterEncounteredNPC1;
    Fighter fighterEncounteredNPC2;
    Fighter fighterEncounteredNPC3;
    Fighter fighterEncounteredNPC4;
    Fighter fighterEncounteredNPC5;

    public WorldPanel(GamePanel gamePanel, KeyHandler keyHandler, Player player) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.player = player;
        this.npc1 = new NotPlayer(gamePanel, 36*GamePanel.tileSize, 7*GamePanel.tileSize, "left-right", "Magician");
        this.fighterEncounteredNPC1 = new Warrior("Weak Magician", Type.ELECTRICITY);
        fighterEncounteredNPC1.pickWeapon(new IceSword());
        this.npc2 = new NotPlayer(gamePanel, 9*GamePanel.tileSize, 11*GamePanel.tileSize, "circle", "Vagrant");
        this.fighterEncounteredNPC2 = new Warrior("Weak Vagrant", Type.WATER);
        fighterEncounteredNPC2.pickWeapon(new IceSword());
        this.npc3 = new NotPlayer(gamePanel, 12*GamePanel.tileSize, 14*GamePanel.tileSize, "up-down", "Vagrant");
        this.fighterEncounteredNPC3 = new Warrior("Medium Vagrant", Type.GROUND);
        fighterEncounteredNPC3.pickWeapon(new IceSword());
        this.npc4 = new NotPlayer(gamePanel, 18*GamePanel.tileSize, GamePanel.tileSize, null, "Magician");
        this.fighterEncounteredNPC4 = new Warrior("Great Magician", Type.WATER);
        fighterEncounteredNPC4.pickWeapon(new IceSword());
        this.npc5 = new NotPlayer(gamePanel, 27*GamePanel.tileSize, 11*GamePanel.tileSize, "attack", "Warrior");
        this.fighterEncounteredNPC5 = new Warrior("Great Warrior", Type.FIRE);
        fighterEncounteredNPC5.pickWeapon(new FireSword());
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
        player.update();

        if (player.getWorldX() < (npc1.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc1.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc1.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc1.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = fighterEncounteredNPC1;
            }

        } else if (player.getWorldX() < (npc2.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc2.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc2.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc2.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = fighterEncounteredNPC2;
            }

        } else if (player.getWorldX() < (npc3.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc3.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc3.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc3.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = fighterEncounteredNPC3;
            }

        } else if (player.getWorldX() < (npc4.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc4.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc4.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc4.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = fighterEncounteredNPC4;
            }

        } else if (player.getWorldX() < (npc5.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc5.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc5.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc5.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = fighterEncounteredNPC5;
            }

        } else if (player.getWorldX() < (merchant.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (merchant.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (merchant.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (merchant.getWorldY() - GamePanel.tileSize)) {
            merchantClose = true;
        } else {
            fighterClose = false;
            merchantClose = false;
            fighterEncountered = null;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        if (player.getWorldX() < (npc1.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc1.getWorldX() - GamePanel.tileSize) && player.getWorldY() < npc1.getWorldY()) {
            npc2.draw(g2);
            npc3.draw(g2);
            npc4.draw(g2);
            player.draw(g2);
            npc1.draw(g2);
            npc5.draw(g2);
            merchant.draw(g2);

        } else if (player.getWorldX() < (npc2.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc2.getWorldX() - GamePanel.tileSize) && player.getWorldY() < npc2.getWorldY()) {
            npc1.draw(g2);
            npc3.draw(g2);
            npc4.draw(g2);
            player.draw(g2);
            npc2.draw(g2);
            npc5.draw(g2);
            merchant.draw(g2);

        } else if (player.getWorldX() < (npc3.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc3.getWorldX() - GamePanel.tileSize) && player.getWorldY() < npc3.getWorldY()) {
            npc1.draw(g2);
            npc2.draw(g2);
            npc4.draw(g2);
            player.draw(g2);
            npc3.draw(g2);
            npc5.draw(g2);
            merchant.draw(g2);

        } else if (player.getWorldX() < (npc4.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc4.getWorldX() - GamePanel.tileSize) && player.getWorldY() < npc4.getWorldY()) {
            npc1.draw(g2);
            npc2.draw(g2);
            npc3.draw(g2);
            player.draw(g2);
            npc4.draw(g2);
            npc5.draw(g2);
            merchant.draw(g2);

        } else {
            npc1.draw(g2);
            npc2.draw(g2);
            npc3.draw(g2);
            npc4.draw(g2);
            player.draw(g2);
            npc5.draw(g2);
            merchant.draw(g2);
        }

        tileManager.drawTopPlayer(g2);
        g2.dispose();
    }
}
