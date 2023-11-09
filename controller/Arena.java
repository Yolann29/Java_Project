package controller;

import models.fighters.Fighter;
import models.weapons.attacks.Attack;

import java.util.Scanner;

public class Arena {

    private Fighter fighter1;
    private Fighter fighter2;
    Scanner scanner = new Scanner(System.in);

    public Arena(Fighter fighter1, Fighter fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    public void fight(){

        do {
            boolean item = false;


            System.out.println(String.format("%s HP: %d", fighter1.getName(), fighter1.getHp()));
            System.out.println(String.format("%s HP: %d", fighter2.getName(), fighter2.getHp()));

            System.out.println("Your turn, what do you want to do?");
            System.out.println("1. Attack");
            System.out.println("2. Use item");

            int choice = scanner.nextInt();

            //SI CHOIX D'ATTAQUER
            if (choice == 1) {
//                attack();
            }

            //SI CHOIX D'ITEMS
            if(choice == 2){
                System.out.println("Choose your item");

                System.out.println("0. Cancel");
                for (int i = 0; i < fighter1.getItems().size(); i++) {

                    System.out.println(String.format("%d. %s", i + 1, fighter1.item(i).getName()));
                }

                do{

                    int itemChoice = scanner.nextInt();

                    if(itemChoice == 0){
                        break;
                    }
                    if(fighter1.item(itemChoice - 1) != null){
                        fighter1.useItem(itemChoice - 1);
                        item = true;
                    } else {
                        System.out.println("You can't do that");
                    }

                } while (!item);

            }

            if(fighter2.getHp() > 0){
                fighter2.attack(fighter1, fighter2.getWeapon().getAttacks()[0]);
                System.out.println(String.format("%s use %s on %s!", fighter2.getName(), fighter2.getWeapon().getAttacks()[0].getName(), fighter1.getName()));
            }

        } while(fighter1.getHp() > 0 && fighter2.getHp() > 0);

        if(fighter1.getHp() > 0){
            System.out.println(String.format("%s won!", fighter1.getName()));
        } else {
            System.out.println(String.format("%s won!", fighter2.getName()));
        }

    }

    public void attack(Fighter attacker, Fighter target, Attack attack){
        if (attack != null) {
            if (attacker.attack(target, attack)) {
                System.out.println(String.format("%s use %s on %s!", attacker.getName(), attack.getName(), target.getName()));
            }
        }
    }

    public Fighter getFighter1() {
        return fighter1;
    }

    public Fighter getFighter2() {
        return fighter2;
    }
}

