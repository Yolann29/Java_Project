package views;

import controller.entities.NotPlayer;
import controller.entities.Player;
import controller.handler.KeyHandler;
import controller.manager.TileManager;
import models.fighters.Fighter;
import models.fighters.Warrior;
import models.types.Type;
import models.weapons.IceSword;
import models.weapons.Weapon;
import views.customwidgets.PButton;

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

    boolean fighterClose = false;
    Fighter fighterEncountered;

    public WorldPanel(GamePanel gamePanel, KeyHandler keyHandler, Player player) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.player = player;
        this.npc1 = new NotPlayer(gamePanel, 36*GamePanel.tileSize, 7*GamePanel.tileSize, "left-right", "Magician");
        this.npc2 = new NotPlayer(gamePanel, 9*GamePanel.tileSize, 11*GamePanel.tileSize, "circle", "Vagrant");
        this.npc3 = new NotPlayer(gamePanel, 12*GamePanel.tileSize, 14*GamePanel.tileSize, "up-down", "Vagrant");
        this.npc4 = new NotPlayer(gamePanel, 18*GamePanel.tileSize, GamePanel.tileSize, null, "Magician");
        this.npc5 = new NotPlayer(gamePanel, 27*GamePanel.tileSize, 11*GamePanel.tileSize, "attack", "Warrior");
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
                fighterEncountered = new Warrior("Weak Magician", Type.ELECTRICITY);
                fighterEncountered.pickWeapon(new IceSword());
            }

        } else if (player.getWorldX() < (npc2.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc2.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc2.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc2.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = new Warrior("WeakVagrant", Type.WATER);
                fighterEncountered.pickWeapon(new IceSword());
            }

        } else if (player.getWorldX() < (npc3.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc3.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc3.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc3.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = new Warrior("MediumVagrant", Type.GROUND);
                fighterEncountered.pickWeapon(new IceSword());
            }

        } else if (player.getWorldX() < (npc4.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc4.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc4.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc4.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = new Warrior("GreatMagician", Type.WATER);
                fighterEncountered.pickWeapon(new IceSword());
            }

        } else if (player.getWorldX() < (npc5.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc5.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc5.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc5.getWorldY() - GamePanel.tileSize)) {
            fighterClose = true;
            if (fighterEncountered == null) {
                fighterEncountered = new Warrior("GreatWarrior", Type.FIRE);
                fighterEncountered.pickWeapon(new IceSword());
            }

        } else {
            fighterClose = false;
            fighterEncountered = null;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        npc1.draw(g2);
        npc2.draw(g2);
        npc3.draw(g2);
        npc4.draw(g2);
        npc5.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
