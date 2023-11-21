package controller.manager;

import controller.entities.Entity;
import controller.entities.NotPlayableCharacter;
import models.Action;
import models.Pattern;
import views.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class AI {
    private final GamePanel gamePanel;
    private int positionX, positionY;
    private boolean trigger;
    private final ArrayList<Action> directions;
    private ArrayList<Integer> ways;

    public AI(GamePanel gamePanel, NotPlayableCharacter npc) {
        this.gamePanel = gamePanel;
        this.positionX = npc.getWorldX();
        this.positionY = npc.getWorldY();
        this.directions = new ArrayList<>(Arrays.asList(Action.LEFT, Action.RIGHT, Action.UP, Action.DOWN));
        this.ways = new ArrayList<>();
    }

    public void move(NotPlayableCharacter npc, Action direction) {
        switch (direction) {
            case UP:
                positionY = -npc.getSpeed()*10;
            case DOWN:
                positionY = +npc.getSpeed()*10;
            case LEFT:
                positionX = -npc.getSpeed()*10;
            case RIGHT:
                positionX = +npc.getSpeed()*10;
        }
    }

    public Action seeFewStepsInAdvance(NotPlayableCharacter npc, int steps, ArrayList<Action> ways, ArrayList<Integer> positionsX, ArrayList<Integer> positionsY) {
        if (steps == 0) {
            int distanceMax = 0;
            Action betterAction = null;
            for (int i = 0; i < positionsX.size(); i++) {
                if (distanceMax < distanceWithPlayer(positionsX.get(i), positionsY.get(i))) {
                    distanceMax = distanceWithPlayer(positionsX.get(i), positionsY.get(i));
                    betterAction = ways.get(i);
                }
            }
            System.out.println(betterAction);
            return betterAction;
        }
        if (ways.size() < 4) {
            for (Action direction : directions) {
                this.positionX = npc.getWorldX();
                this.positionY = npc.getWorldY();
                npc.collisionOn = false;
                npc.doorHere = false;
                gamePanel.collision.checkTile(npc, positionX, positionY, direction);
                if (!npc.collisionOn && !npc.doorHere) move(npc, direction);
                positionsX.add(positionX);
                positionsY.add(positionY);
                ways.add(direction);
            }
            return seeFewStepsInAdvance(npc, steps - 1, ways, positionsX, positionsY);
        }

        ArrayList<Integer> newPositionsX = new ArrayList<>();
        ArrayList<Integer> newPositionsY = new ArrayList<>();
        ArrayList<Action> newWays = new ArrayList<>();
        System.out.println(positionsX.size());
        for (Action direction : directions) {
            for (int i = 0; i < positionsX.size(); i++) {
                positionX = positionsX.get(i);
                positionY = positionsY.get(i);
                npc.collisionOn = false;
                npc.doorHere = false;
                gamePanel.collision.checkTile(npc, positionX, positionY, direction);
                if (!npc.collisionOn && !npc.doorHere) move(npc, direction);
                newPositionsX.add(positionX);
                newPositionsY.add(positionY);
                newWays.add(ways.get(i));
            }
        }
        return seeFewStepsInAdvance(npc, steps - 1, newWays, newPositionsX, newPositionsY);
    }

    public int distanceWithPlayer(int positionX, int positionY) {
        return Math.abs(positionX - gamePanel.player.getWorldX()) + Math.abs(positionY - gamePanel.player.getWorldY());
    }

}

//case SCARED_SMART:
//        AI scared = new AI(gamePanel, this);
//        ArrayList<Action> ways = new ArrayList<>();
//        ArrayList<Integer> positionsX = new ArrayList<>();
//        ArrayList<Integer> positionsY = new ArrayList<>();
//
//        if (gamePanel.player.getWorldX() < (getWorldX() + 3 * GamePanel.tileSize) && gamePanel.player.getWorldX() > (getWorldX() - 3 * GamePanel.tileSize) && gamePanel.player.getWorldY() < (getWorldY() + 3 * GamePanel.tileSize) && gamePanel.player.getWorldY() > (getWorldY() - 3 * GamePanel.tileSize)) {
//        this.setDirection(scared.seeFewStepsInAdvance(this, 5, ways, positionsX, positionsY));
//        collisionOn = false;
//        doorHere = false;
//        gamePanel.collision.checkTile(this);
//        if (!collisionOn && !doorHere) move(getDirection());
//        }
//        break;