package views.arena;

import controller.Arena;
import controller.Game;
import controller.GameState;
import controller.entities.Entity;
import controller.entities.NotPlayableCharacter;
import controller.entities.Player;
import controller.handler.KeyHandler;
import controller.manager.FighterClasseManager;
import models.Action;
import models.Role;
import models.fighters.Fighter;
import views.GamePanel;
import views.WorldPanel;
import views.customwidgets.PTextPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ArenaPanel extends JPanel {

    GameState gs;
    Image background;
    Image profile_background;
    Arena arena;
    ActionsPanel da;
    Game game;
    KeyHandler keyHandler;

    boolean endGame = false;
    long initAnimation = 0;
    long currentTime = 0;

    boolean fighter1DamageTaken = false;
    boolean fighter2DamageTaken = false;

    private final Player player;
    private final NotPlayableCharacter encounter;

    public ArenaPanel(Game game, GameState gs, Arena arena, ActionsPanel da, Player player, NotPlayableCharacter encounter) {
        this.da = da;
        this.arena = arena;
        this.keyHandler = game.getKeyH();
        this.player = player;
        this.encounter = encounter;
        this.gs = gs;
        this.game = game;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        loadRessources();

    }

    public void loadRessources() {

        try {
            background = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-fight.png")));
            profile_background = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-profile.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawInfoBar(Graphics2D g2, Fighter fighter, int x){
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //NAME
        g2.setColor(Color.white);
        g2.setFont(new Font("Courier", Font.BOLD, 20));
        g2.drawString(fighter.getName(), x - 20, 210);
        //LEVEL
        g2.setColor(Color.YELLOW);
        g2.drawString("Lv." + fighter.getLevel(), x + 90, 210);
        //TYPE
        g2.setColor(fighter.getWeapon().getType().getColor());
        g2.fillRoundRect(x - 20, 235, fighter.getWeapon().getType().getName().length() * 10 + 3, 15, 5, 5);
        g2.setFont(new Font("Courier", Font.BOLD, 12));
        g2.setColor(Color.WHITE);
        g2.drawString(fighter.getWeapon().getType().getName(), x - 10, 247);
        //HP
        if(fighter.getHp() < 50) g2.setColor(Color.YELLOW);
        if(fighter.getHp() < 20) g2.setColor(Color.RED);
        g2.drawString("Hp." + fighter.getHp() + "/" + fighter.getMaxHp(), x + 70, 245);
        //HP BAR
        g2.setColor(Color.RED);
        g2.fillRoundRect(x - 20, 220, 150, 8, 20, 30);
        g2.setColor(Color.GREEN);
        g2.fillRoundRect(x - 20, 220, (int) (150 * ((float) fighter.getHp() / fighter.getMaxHp())), 8, 20, 30);
    }

    public void moveToTarget(Graphics2D g2, Fighter fighter, Role classe, int startX, boolean reversed){

        long time = System.currentTimeMillis() - fighter.getWalkingTime();
        float timeInSec = time / 1300f;
        int x = startX + (int)(370 * Math.min(1, timeInSec)) * (reversed?-1:1);


        if(timeInSec >= 1 && timeInSec <= 1.3) {
            if(reversed){
                this.fighter1DamageTaken = true;
            } else {
                this.fighter2DamageTaken = true;
            }
            Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.ATTACK)).paint(g2, x, 270, 96, 96, reversed);
            drawInfoBar(g2, fighter, x);
        } else if (timeInSec < 1){
            Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.WALK)).paint(g2, x, 270, 96, 96, reversed);
            drawInfoBar(g2, fighter, x);
        } else {
            arena.applyAttack(g2);
            if(reversed){
                this.fighter1DamageTaken = false;
            } else {
                this.fighter2DamageTaken = false;
            }
            int reversedX = startX + (int) (370 * Math.max(0, 1 - (timeInSec - 1.3))  * (reversed?-1:1));
            Objects.requireNonNull(FighterClasseManager.returnRightAnimation(classe, Action.WALK)).paint(g2, reversedX, 270, 96, 96, !reversed);
            drawInfoBar(g2, fighter, reversedX);
            if(reversedX == startX){
                arena.switchTurn();
                fighter.setWalkingTime(0);
            }
        }

    }

    public void quitArena(Entity looser) {
        if (initAnimation == 0) {
            initAnimation = System.currentTimeMillis();
        }
        currentTime = System.currentTimeMillis();
        if (currentTime >= initAnimation + 2500) {
            endGame = false;
            game.getGp().switchToOverworld();
            initAnimation = 0;

            arena.isYourTurn = true;
            arena.getFighter1().getWeapon().setDamage(arena.getFighter1().getWeapon().getInitialDamage());
            arena.getFighter1().regenItems();
            arena.getFighter1().giveXp(arena.getFighter2().getLevel() * 200);
            game.getPlayer().fighter.restoreHpMax();
            WorldPanel.fightersNpc.remove(looser);

            game.getEncounter().fighter = null;
            looser.setDirection(Action.DEAD);
            looser.isDead = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //BACKGROUND
        g.drawImage(background, 0, 0, null);

        g.setColor(Color.ORANGE);

        //ANIMATION
        //JOUEUR 1
        if (arena.getFighter1().isDead()) {
            Objects.requireNonNull(FighterClasseManager.returnRightAnimation(player.classe, Action.DEAD)).paint(g2, 100, 270, 96, 96, false);
            drawInfoBar(g2, arena.getFighter1(), 100);
        } else if (arena.getFighter1().getWalkingTime() != 0) {
            moveToTarget(g2, arena.getFighter1(), player.classe, 100, false);
        } else {
            if(!endGame && !this.fighter1DamageTaken) Objects.requireNonNull(FighterClasseManager.returnRightAnimation(player.classe, Action.IDLE)).paint(g2, 100, 270, 96, 96, false);
            drawInfoBar(g2, arena.getFighter1(), 100);
        }

        //JOUEUR 2
        if (arena.getFighter2().isDead()) {
            Objects.requireNonNull(FighterClasseManager.returnRightAnimation(encounter.classe, Action.DEAD)).paint(g2, 550, 270, 96, 96, true);
            drawInfoBar(g2, arena.getFighter2(), 550);
        } else if (arena.getFighter2().getWalkingTime() != 0) {
            moveToTarget(g2, arena.getFighter2(), encounter.classe, 550, true);
        } else {
            if(!endGame && !this.fighter2DamageTaken) Objects.requireNonNull(FighterClasseManager.returnRightAnimation(encounter.classe, Action.IDLE)).paint(g2, 550, 270, 96, 96, true);
            drawInfoBar(g2, arena.getFighter2(), 550);
        }

        //ANIMATION DEGATS
        if(this.fighter1DamageTaken){
            Objects.requireNonNull(FighterClasseManager.returnRightAnimation(player.classe, Action.HIT)).paint(g2, 100, 270, 96, 96, false);
        } else if (this.fighter2DamageTaken){
            Objects.requireNonNull(FighterClasseManager.returnRightAnimation(encounter.classe, Action.HIT)).paint(g2, 550, 270, 96, 96, true);
        }

        //DESACTIVER LES BOUTONS
        da.setInteraction(arena.getFighter1().getWalkingTime() == 0
                && arena.getFighter2().getWalkingTime() == 0
                && !arena.getFighter1().isDead()
                && !arena.getFighter2().isDead()
                && arena.isYourTurn());

        //DETERMINER LE WINNER
        if(!arena.getFighter1().isDead() && arena.getFighter2().isDead()){
            da.setDialogText(arena.getFighter1().getName() + " won " +  arena.getFighter2().getLevel() * 200 + "xp");
            if(arena.getFighter1().getWalkingTime() == 0){
                endGame = true;
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(player.classe, Action.JUMP)).paint(g2, 100, 270, 96, 96, false);
                quitArena(encounter);
            }
        }
        if(!arena.getFighter2().isDead() && arena.getFighter1().isDead()){
            da.setDialogText(arena.getFighter2().getName() + " won " + arena.getFighter2().getLevel() * 200 + "xp");
            if(arena.getFighter2().getWalkingTime() == 0){
                endGame = true;
                Objects.requireNonNull(FighterClasseManager.returnRightAnimation(encounter.classe, Action.JUMP)).paint(g2, 550, 270, 96, 96, true);
                quitArena(player);
            }
        }
    }
}

