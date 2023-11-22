package views.arena;

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

public class ActionsPanel extends JPanel {

    private double launchFightAnimationDuration = 0;
    private Image texture;
    private final PButton attack;
    private final PButton items;
    private final PButton cancel;

    private final JPanel buttonsPannel;
    PTextPane textPane;

    public ActionsPanel(Arena arena){
        loadRessources();
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, 150));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(new Color(141, 141, 141,255));
        this.setBorder(new EmptyBorder(20,20,20,20));

        launchFightAnimationDuration = 1;

        buttonsPannel = new JPanel();
        buttonsPannel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonsPannel.setPreferredSize(new Dimension(320, 100));
        buttonsPannel.setBackground(new Color(0,0,0,0));

        JPanel leftPannel = new JPanel();
        leftPannel.setLayout(new GridLayout(2, 1, 0, 0));
        leftPannel.setPreferredSize(new Dimension(320, 120));
        leftPannel.setBackground(new Color(0,0,0,0));

        JPanel returnPannel = new JPanel();
        returnPannel.setLayout(new FlowLayout(FlowLayout.LEFT));
        returnPannel.setBackground(new Color(0,0,0,0));

        attack = new PButton("> Attack");
        items = new PButton("> Items");
        cancel = new PButton("> Return");
        cancel.setPreferredSize(new Dimension(120, 35));
        cancel.setBackground(Color.RED.darker());
        System.out.println("Arena -> Your turn!");
        textPane = new PTextPane("Your turn !", arena);

        cancel.addActionListener(e1 -> {
            System.out.println("Arena -> Your turn ! ");
            textPane.setTextWithTypingEffect("Your turn !");
            returnPannel.remove(cancel);
            buttonsPannel.removeAll();
            buttonsPannel.add(attack);
            buttonsPannel.add(items);
            buttonsPannel.revalidate();
            leftPannel.revalidate();
        });
        returnPannel.revalidate();

        buttonsPannel.add(attack);
        buttonsPannel.add(items);
        leftPannel.add(textPane);
        leftPannel.add(returnPannel);
        buttonsPannel.setVisible(false);

        arena.setTextPane(textPane);
        //ATTACK BUTTON
        attack.addActionListener(e -> {

            System.out.println("Arena -> Choose your attack !");
            textPane.setTextWithTypingEffect("Choose your attack !");
            buttonsPannel.remove(attack);
            buttonsPannel.remove(items);
            returnPannel.add(cancel);

            for (Attack weaponAttack : arena.getFighter1().getWeapon().getWeaponAttacks()) {
                if (weaponAttack != null) {
                    PButton attackButton = new PButton("> " + weaponAttack.getName() + " (" + weaponAttack.getDamage() + ")");
                    buttonsPannel.add(attackButton);

                    attackButton.addActionListener(e1 -> {
                        arena.startAttack(arena.getFighter1(), arena.getFighter2(), weaponAttack);
                        returnPannel.remove(cancel);
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
            System.out.println("Arena -> Choose your item !");
            textPane.setTextWithTypingEffect("Choose your item !");
            buttonsPannel.remove(attack);
            buttonsPannel.remove(items);
            returnPannel.add(cancel);

            for (Item item : arena.getFighter1().getItems()) {
                PButton itemButton = new PButton("> " + item.getName());
                buttonsPannel.add(itemButton);

                itemButton.addActionListener(e1 -> {
                    arena.getFighter1().useItem(item);
                    this.setInteraction(false);
                    System.out.println(String.format("Arena -> %s used %s!", arena.getFighter1().getName(), item.getName()));
                    textPane.setTextWithTypingEffect(String.format("%s used %s!", arena.getFighter1().getName(), item.getName()));
                    returnPannel.remove(cancel);
                    buttonsPannel.removeAll();
                    buttonsPannel.add(attack);
                    buttonsPannel.add(items);
                    buttonsPannel.revalidate();

                });
            }
            buttonsPannel.revalidate();
        });

        this.add(leftPannel);
        this.add(buttonsPannel);


    }


    public void setDialogText(String text){
        textPane.setText(text);
    }

    public void loadRessources(){

        try{
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-menu.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.drawImage(texture, 0, 0, null);

        if(launchFightAnimationDuration != 0){
            g2.setColor(Color.black);
            g2.fillRect((int) launchFightAnimationDuration, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);
            launchFightAnimationDuration = launchFightAnimationDuration + 40;
            if(launchFightAnimationDuration > GamePanel.FRAME_WIDTH){
                launchFightAnimationDuration = 0;
                buttonsPannel.setVisible(true);
            }
        }

    }

    public void setInteraction(boolean interaction){
        attack.setEnabled(interaction);
        items.setEnabled(interaction);
    }



}
