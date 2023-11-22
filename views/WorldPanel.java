package views;

import controller.Game;
import controller.GameState;
import controller.entities.Entity;
import controller.entities.NotPlayableCharacter;
import controller.entities.Player;
import controller.handler.KeyHandler;
import controller.manager.TileManager;
import models.Pattern;
import models.Role;
import models.fighters.Merchant;
import models.fighters.Warrior;
import models.types.Type;
import models.weapons.*;
import views.customwidgets.PButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class WorldPanel extends JPanel {

    private double launchFightAnimationDuration = 0;
    private Game game;
    final GameState gs;
    final GamePanel gamePanel;
    final KeyHandler keyHandler;
    final TileManager tileManager;
    final private Player player;
    public boolean fighterClose = false;
    public NotPlayableCharacter npcEncounter;

    public static ArrayList<NotPlayableCharacter> fightersNpc = new ArrayList<>();
    public static ArrayList<NotPlayableCharacter> npcs = new ArrayList<>();
    int npcExperience = 1;

    public WorldPanel(GameState gs, GamePanel gamePanel, KeyHandler keyHandler, Player player, Game game) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.player = player;
        this.gs = gs;
        this.game = game;

        //FIGHTERS
        createNPC(15, 11, 4, Pattern.CIRCLE, Role.VAGRANT, Type.WATER, new ElectricGauntlet(), false);
        createNPC(7, 14, 6, Pattern.SCARED, Role.ARCHER, Type.GROUND, new GroundSpear(), false);
        createNPC(10, 5, 3, Pattern.LEFT_RIGHT, Role.MAGICIAN, Type.WATER, new IceSword(), false);
        createNPC(18, 1, 4, Pattern.SLOW_PURSUER, Role.MAGICIAN, Type.WATER, new IceSword(), false);
        createNPC(22, 14, 5, Pattern.SCARED, Role.WARRIOR, Type.FIRE, new GroundSpear(), false);
        createNPC(27, 11, 10, Pattern.FAST_PURSUER, Role.WARRIOR, Type.FIRE, new ElectricGauntlet(), false);
        createNPC(36, 7, 4, Pattern.LEFT_RIGHT, Role.ARCHER, Type.ELECTRICITY, new IceSword(), false);

        //PETS
        createNPC(9, 9, 6, Pattern.SCARED, Role.CAT_BLACK, Type.WATER, new GroundSpear(), false);
        createNPC(13, 15, 6, Pattern.SCARED, Role.CAT_ORANGE, Type.WATER, new GroundSpear(), false);
        createNPC(20, 7, 6, Pattern.SCARED, Role.CAT_ORANGE, Type.WATER, new GroundSpear(), false);
        createNPC(15, 9, 6, Pattern.SCARED, Role.CAT_GRAY, Type.WATER, new GroundSpear(), false);

        //MERCHANT
        createNPC(18, 9, 7, Pattern.IDLE, Role.VAGRANT, null, new IceSword(), true);

        this.tileManager = new TileManager(game);
        this.addKeyListener(player.getKeyHandler());
        this.setFocusable(true);
    }

    private void createNPC(int x, int y, int speed, Pattern behavior, Role className, Type type, Weapon weapon, boolean isMerchant) {
        NotPlayableCharacter npc = new NotPlayableCharacter(game, x * GamePanel.tileSize, y * GamePanel.tileSize, speed, behavior, className);

        if(isMerchant){
            npc.fighter = new Merchant("Marchand");
        } else {
            npcExperience += 16;
            npc.fighter = new Warrior(className.getName(), type, Math.max(100,new Random().nextInt((int) Math.pow(npcExperience, 2))));
        }
        if (weapon != null) {
            npc.fighter.pickWeapon(weapon);
        }
        npcs.add(npc);

        if(npc.fighter instanceof Merchant
                || className == Role.CAT_BLACK
                || className == Role.CAT_GRAY
                || className == Role.CAT_ORANGE) return;

        fightersNpc.add(npc);
    }

    public void update() {
        player.update();
        if (fightersNpc.isEmpty()) return;
        if (TileManager.mapTileNum == TileManager.mapTileNum2) {

            for (NotPlayableCharacter npc : npcs) {
                npc.update();
            }

            boolean npcClose = false;
            for (NotPlayableCharacter npc : npcs) {
                if (distanceBetween(npc)) {
                    npcClose = true;
                }
            }

            if (!npcClose) {
                fighterClose = false;
                npcEncounter = null;
                gamePanel.closeMerchantShop();
            }
        }
    }

    public boolean distanceBetween(NotPlayableCharacter npc){
        if (player.getWorldX() < (npc.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (npc.getWorldX() - GamePanel.tileSize) && player.getWorldY() < (npc.getWorldY() + GamePanel.tileSize) && player.getWorldY() > (npc.getWorldY() - GamePanel.tileSize) && !npc.isDead && !player.isDead) {

            if(npc.classe == Role.CAT_ORANGE || npc.classe == Role.CAT_GRAY || npc.classe == Role.CAT_BLACK){
                return true;
            }

            if (npc.fighter instanceof Merchant) {
                gamePanel.openMerchantShop();
                return true;
            }

            System.out.println("Fight begin between " + player.fighter.getName() + " and " + npc.fighter.getName());
            fighterClose = true;
            npcEncounter = npc;
            if(launchFightAnimationDuration == 0){
                launchFightAnimationDuration = 1;
                game.setFightTransition(true);
            }

            return true;
        }

        return false;
    }

    public NotPlayableCharacter drawOrder(Graphics2D g2, Entity entity) {
        if (player.getWorldX() < (entity.getWorldX() + GamePanel.tileSize) && player.getWorldX() > (entity.getWorldX() - GamePanel.tileSize) && player.getWorldY() > entity.getWorldY()) {
            NotPlayableCharacter npc;
            if (entity instanceof NotPlayableCharacter) {
                npc = (NotPlayableCharacter) entity;
                npc.draw(g2);
                return npc;
            }
        }
        return null;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (player.changeMap) {
            tileManager.changeMap();
            player.changeMap = false;

        } else if (TileManager.mapTileNum == TileManager.mapTileNum2) {
            tileManager.draw(g2);
            ArrayList<NotPlayableCharacter> npcToDraw = new ArrayList<>(npcs);

            for(int i = 0; i < npcs.size(); i++){
                npcToDraw.remove(drawOrder(g2, npcs.get(i)));
            }


            if (!player.isDead && !fightersNpc.isEmpty()) {
                player.draw(g2);
                for (NotPlayableCharacter npc : npcToDraw) {
                    npc.draw(g2);
                }

                tileManager.drawTopPlayer(g2);
            } else {
                for (NotPlayableCharacter npc : npcToDraw) {
                    npc.draw(g2);
                }

                tileManager.drawTopPlayer(g2);
                if(fightersNpc.isEmpty()){
                    g2.setColor(new Color(255, 255, 255, 153));
                    g2.fillRect(0, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);
                    g2.setColor(Color.GREEN.darker());
                    g2.setFont(GamePanel.gloucester.deriveFont(48f));
                    g2.drawString("You won !", GamePanel.FRAME_WIDTH/2 - 145, GamePanel.FRAME_HEIGHT/2 - 32);
                }
                player.draw(g2);
            }
        } else {
            tileManager.draw(g2);
            player.draw(g2);
            tileManager.drawTopPlayer(g2);
        }


        if(launchFightAnimationDuration != 0){
            g2.setColor(Color.BLACK);
            launchFightAnimationDuration = launchFightAnimationDuration + 40;

            if(launchFightAnimationDuration > 800){
                g2.fillRect((int) (launchFightAnimationDuration - 800) - GamePanel.FRAME_WIDTH, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);
                if((launchFightAnimationDuration - 800) > GamePanel.FRAME_WIDTH){
                    launchFightAnimationDuration = 0;
                    game.getGp().switchToArena();
                }
            }

        }
        g2.dispose();
    }
}
