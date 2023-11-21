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

    private GameState gs;
    private GamePanel gp;
    private KeyHandler keyH;
    private Collision collision = new Collision();
    private Arena arena;
    private Player player;
    public NotPlayableCharacter encounter;
    public Fighter encounterFighter;

    public Game(){

        this.gs = new GameState();
        this.keyH = new KeyHandler(gs);

        this.window = new JFrame("T-JAV-501");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setIgnoreRepaint(true);

        encounterFighter = new Warrior("First Encounter", Type.WATER, new Random().nextInt(10) + 1);
        Weapon firesword = new FireSword();
        Weapon icesword = new IceSword();
        encounterFighter.pickWeapon(icesword);

        this.player = new Player(this, keyH, Role.WARRIOR);
        player.fighter = new Warrior("YOU", Type.FIRE, 50);
        player.fighter.pickWeapon(firesword);

        this.encounter = new NotPlayableCharacter(this, 0,0, 0, Pattern.LEFT_RIGHT, Role.VAGRANT);
        this.encounter.fighter = encounterFighter;
        this.arena = new Arena(player.fighter, encounterFighter);
        this.gp = new GamePanel(gs, keyH, arena, player, encounter, this);

        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void update(){
        if (gs.getMenu()) {
            gp.getMenuPanel().update();
        } else {
            if (gs.getOverWorld()) {
                gp.getWorldPanel().update();
            } else {
                arena.update();
            }
        }
    }

    public Player getPlayer(){
        return this.player;
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

    public GamePanel getGp() {
        return gp;
    }
}

