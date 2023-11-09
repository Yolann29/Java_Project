package views.dialog;

import controller.Arena;
import models.items.Item;
import models.weapons.attacks.Attack;
import views.GamePanel;
import views.customwidgets.PButton;
import views.customwidgets.PTextPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class DialogActions extends JPanel {

    private Color dialogColor = new Color(40, 79, 104);
    private Image texture;
    private Arena arena;

    public DialogActions(Arena arena){
        this.arena = arena;
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, 150));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(new Color(141, 141, 141,255));
        this.setBorder(new EmptyBorder(20,20,20,20));

        loadRessources();

        JPanel buttonsPannel = new JPanel();
        buttonsPannel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonsPannel.setPreferredSize(new Dimension(320, 100));
        buttonsPannel.setBackground(new Color(0,0,0,0));

        PButton attack = new PButton("> Attack");
        PButton items = new PButton("> Items");
        PTextPane textPane = new PTextPane("Your turn !");


        buttonsPannel.add(attack);
        buttonsPannel.add(items);
        System.out.println(String.format("%s HP: %d", arena.getFighter1().getName(), arena.getFighter1().getHp()));
        System.out.println(String.format("%s HP: %d", arena.getFighter2().getName(), arena.getFighter2().getHp()));

        //ATTACK BUTTON

        attack.addActionListener(e -> {

            textPane.setText("Choose your attack !");
            buttonsPannel.remove(attack);
            buttonsPannel.remove(items);

            for (Attack weaponAttack : arena.getFighter1().getWeapon().getAttacks()) {
                if (weaponAttack != null) {
                    PButton attackButton = new PButton("> " + weaponAttack.getName());
                    buttonsPannel.add(attackButton);

                    attackButton.addActionListener(e1 -> {
                        arena.attack(arena.getFighter1(), arena.getFighter2(), weaponAttack);
                        textPane.setText(String.format("%s used %s on %s!", arena.getFighter1().getName(), weaponAttack.getName(), arena.getFighter2().getName()));
                        System.out.println(String.format("%s HP: %d", arena.getFighter1().getName(), arena.getFighter1().getHp()));
                        System.out.println(String.format("%s HP: %d", arena.getFighter2().getName(), arena.getFighter2().getHp()));
                        buttonsPannel.removeAll();
                        buttonsPannel.add(attack);
                        buttonsPannel.add(items);
                        buttonsPannel.revalidate();
                    });
                }
            }
            buttonsPannel.revalidate();

        });

        //ITEMS BUTTON
        items.addActionListener(e -> {
            textPane.setText("Choose your item !");
            buttonsPannel.remove(attack);
            buttonsPannel.remove(items);

            for (Item item : arena.getFighter1().getItems()) {
                PButton itemButton = new PButton("> " + item.getName());
                buttonsPannel.add(itemButton);

                itemButton.addActionListener(e1 -> {
                    arena.getFighter1().useItem(item);
                    textPane.setText(String.format("%s used %s!", arena.getFighter1().getName(), item.getName()));
                    System.out.println(String.format("%s HP: %d", arena.getFighter1().getName(), arena.getFighter1().getHp()));
                    System.out.println(String.format("%s HP: %d", arena.getFighter2().getName(), arena.getFighter2().getHp()));
                    buttonsPannel.removeAll();
                    buttonsPannel.add(attack);
                    buttonsPannel.add(items);
                    buttonsPannel.revalidate();

                });
            }
            buttonsPannel.revalidate();
        });

        this.add(textPane, BorderLayout.CENTER);
        this.add(buttonsPannel, BorderLayout.EAST);


    }

//    public void showActions(JPanel buttonsPannel){
//        buttonsPannel.add(att)
//    }

    public void loadRessources(){

        try{
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-menu.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //antialisaing
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(texture, 0, 0, null);

    }



}
