package controller;

import controller.entities.NotPlayableCharacter;
import controller.entities.Player;
import controller.handler.KeyHandler;
import controller.manager.Collision;
import models.Pattern;
import models.Role;
import models.fighters.Fighter;
import models.fighters.Warrior;
import models.types.Type;
import models.weapons.FireSword;
import models.weapons.IceSword;
import models.weapons.Weapon;
import views.GamePanel;

import javax.swing.*;
import java.util.Random;

public class Game {

    private JFrame window;

    private boolean fightTransition = false;

    private GameState gs;
    private GamePanel gp;
    private KeyHandler keyH;
    private Collision collision = new Collision();
    private Arena arena;
    private Player player;
    public NotPlayableCharacter encounter;

    public Game(){

        this.gs = new GameState();
        this.keyH = new KeyHandler(gs);

        this.window = new JFrame("T-JAV-501");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setIgnoreRepaint(true);

        this.gp = new GamePanel(gs, keyH, this);

        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void update(){
        if (gs.getMenu()) {
            gp.getMenuPanel().update();

        } else if (gs.getOverWorld() && !fightTransition) {
            gp.getWorldPanel().update();

        } else if (arena != null) {
            arena.update();
        }
    }

    public Player getPlayer(){
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Collision getCollision() {
        return collision;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }

    public NotPlayableCharacter getEncounter() {
        return encounter;
    }

    public void setEncounter(NotPlayableCharacter encounter) {
        this.encounter = encounter;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public GameState getGs() {
        return gs;
    }

    public GamePanel getGp() {
        return gp;
    }

    public boolean isFightTransition() {
        return fightTransition;
    }

    public void setFightTransition(boolean fightTransition) {
        this.fightTransition = fightTransition;
    }

    public JFrame getWindow() {
        return window;
    }
}

