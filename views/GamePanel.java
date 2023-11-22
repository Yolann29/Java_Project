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
import views.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

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

    public static Font gloucester;
    public WorldPanel worldPanel;
    public MenuPanel menuPanel;
    private MerchantShop merchantShop;
    private ArenaPanel arenaPanel;
    private ActionsPanel actionsPanel;
    private Game game;
    private GameState gs;

    public GamePanel(GameState gs, KeyHandler keyH, Game game) {

        try{
            InputStream is = getClass().getResourceAsStream("/font/gloucester.ttf");
            gloucester = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e){
            e.printStackTrace();
        }

        this.menuPanel = new MenuPanel(this, game);
        this.merchantShop = new MerchantShop(this, game);
        this.game = game;
        this.gs = gs;

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.add(menuPanel, BorderLayout.CENTER);
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
        if (!isNotPanelAdded(this, actionsPanel)) {
            this.remove(actionsPanel);
        }
        if (!isNotPanelAdded(this, arenaPanel)) {
            this.remove(arenaPanel);
        }
        this.add(worldPanel, BorderLayout.CENTER);
        this.revalidate();
    }

    public void openMerchantShop(){
        if(isNotPanelAdded(this, merchantShop)){
            System.out.println("World -> Open merchant shop");
            this.add(merchantShop, BorderLayout.SOUTH);
            this.revalidate();
        }

    }

    public void closeMerchantShop(){
        if(!isNotPanelAdded(this,merchantShop)) {
            System.out.println("World -> Close merchant shop");
            this.remove(merchantShop);
            this.revalidate();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red);
        g.setFont(gloucester);
    }

    public static boolean isNotPanelAdded(Container container, Component panel) {
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

    public void setWorldPanel(WorldPanel worldPanel) {
        this.worldPanel = worldPanel;
    }

    public MenuPanel getMenuPanel() { return menuPanel; }

    public Game getGame() { return game; }
}
