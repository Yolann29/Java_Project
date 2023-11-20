package views;

import controller.Arena;
import controller.Game;
import controller.GameState;
import controller.entities.NotPlayableCharacter;
import controller.entities.Player;
import controller.handler.KeyHandler;
import views.arena.ActionsPanel;
import views.arena.ArenaPanel;
import views.arena.MerchantShop;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    final public static int tileSize = 64;
    final public static int columns = 23; //736
    final public static int rows = 18; //576
    final public static int worldColumns = 38;
    final public static int worldRows = 17;
    final public static int worldWidth = worldColumns * tileSize;
    final public static int worldHeight = worldRows * tileSize;

    final public static int FRAME_WIDTH = 750;
    final public static int FRAME_HEIGHT = 600;

    public WorldPanel worldPanel;
    private MerchantShop merchantShop;
    private ArenaPanel arenaPanel;
    private ActionsPanel actionsPanel;
    private Game game;
    private GameState gs;

    public GamePanel(GameState gs, KeyHandler keyH, Arena arena, Player player, NotPlayableCharacter encounter, Game game) {

        this.actionsPanel = new ActionsPanel(arena);
        this.arenaPanel = new ArenaPanel(game, gs, arena, actionsPanel,player, encounter);
        this.worldPanel = new WorldPanel(gs, this,keyH, player, game);
        this.merchantShop = new MerchantShop(this, game);
        this.game = game;
        this.gs = gs;

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.add(arenaPanel);
        this.add(actionsPanel);
        this.add(worldPanel, BorderLayout.CENTER);
    }

    public void switchToArena() {
        if (this.getWorldPanel().npcEncounter != null) {
            game.setEncounter(this.getWorldPanel().npcEncounter);
        }
        gs.setOverWorld(false);
        this.remove(merchantShop);
        this.remove(worldPanel);
        game.setArena(new Arena(game.getPlayer().fighter, game.getEncounter().fighter));
        actionsPanel = new ActionsPanel(game.getArena());
        arenaPanel = new ArenaPanel(game, this.gs, game.getArena(), actionsPanel, game.getPlayer(), game.getEncounter());
        this.add(arenaPanel, BorderLayout.CENTER);
        this.add(actionsPanel, BorderLayout.SOUTH);
        this.revalidate();
    }

    public void switchToOverworld(){
        gs.setOverWorld(true);
        this.remove(actionsPanel);
        this.remove(arenaPanel);
        this.add(worldPanel, BorderLayout.CENTER);
        this.revalidate();
    }

    public void openMerchantShop(){
        if(isNotPanelAdded(this, merchantShop)){
            this.add(merchantShop, BorderLayout.SOUTH);
            this.revalidate();
        }

    }

    public void closeMerchantShop(){
        this.remove(merchantShop);
        this.revalidate();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    private static boolean isNotPanelAdded(Container container, Component panel) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            Component component = container.getComponent(i);
            if (component == panel) {
                return false;
            }
        }
        return true;
    }

    public WorldPanel getWorldPanel() {
        return worldPanel;
    }
}
