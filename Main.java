import models.fighters.Fighter;
import models.fighters.Warrior;
import models.types.Type;
import models.weapons.FireSword;
import models.weapons.IceSword;
import models.weapons.Weapon;
import views.GamePanel;

import javax.swing.*;

public class Main {

        public static void main(String[] args) {

//            JFrame window = new JFrame("T-JAV-501");
//            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            window.setResizable(false);
//
//            GamePanel gamePanel = new GamePanel();
//            window.add(gamePanel);
//            window.pack();
//            gamePanel.startGameLoop();
//
//            window.setLocationRelativeTo(null);
//            window.setVisible(true);

                Fighter nathan = new Warrior("Nathan", Type.AIR);
                Fighter victor = new Warrior("Victor", Type.FIRE);

                Weapon firesword = new FireSword();
                Weapon icesword = new IceSword();

                nathan.pickWeapon(firesword);
                nathan.pickWeapon(icesword);

                System.out.println(nathan.getWeapon().getName());



        }


}