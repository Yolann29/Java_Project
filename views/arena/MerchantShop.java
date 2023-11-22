package views.arena;

import controller.Game;
import models.weapons.*;
import views.GamePanel;
import views.WorldPanel;
import views.customwidgets.PButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MerchantShop extends JPanel {

    private Image texture;

    public MerchantShop(GamePanel gamePanel, Game game) {
        this.setBackground(new Color(141, 141, 141,255));

        loadRessources();

        JPanel weaponChoices = new JPanel();
        weaponChoices.setLayout(new GridLayout(2, 3, 10, 10));
        weaponChoices.setPreferredSize(new Dimension(500, 100));
        weaponChoices.setBackground(new Color(0,0,0,0));

        PButton iceswordChoice = new PButton("Ice Sword");
        PButton fireswordChoice = new PButton("Fire Sword");
        PButton electricGauntletChoice = new PButton("Electric Gauntlet");
        PButton groundSpearChoice = new PButton("Ground Spear");
        PButton lassoChoice = new PButton("Lasso");

        iceswordChoice.setForeground( new Color(0, 162, 255));
        fireswordChoice.setForeground( new Color(255, 81, 31));
        electricGauntletChoice.setForeground( new Color(220, 207, 0));
        lassoChoice.setForeground( new Color(0, 194, 0));
        groundSpearChoice.setForeground( new Color(141, 63, 34));


        weaponChoices.add(iceswordChoice);
        weaponChoices.add(fireswordChoice);
        weaponChoices.add(electricGauntletChoice);
        weaponChoices.add(groundSpearChoice);
        weaponChoices.add(lassoChoice);

        iceswordChoice.addActionListener(e -> {
            game.getPlayer().fighter.pickWeapon(new IceSword());
            gamePanel.closeMerchantShop();
        });
        fireswordChoice.addActionListener(e -> {
            game.getPlayer().fighter.pickWeapon(new FireSword());
            gamePanel.closeMerchantShop();
        });
        electricGauntletChoice.addActionListener(e -> {
            game.getPlayer().fighter.pickWeapon(new ElectricGauntlet());
            gamePanel.closeMerchantShop();
        });
        groundSpearChoice.addActionListener(e -> {
            game.getPlayer().fighter.pickWeapon(new GroundSpear());
            gamePanel.closeMerchantShop();
        });
        lassoChoice.addActionListener(e -> {
            game.getPlayer().fighter.pickWeapon(new Lasso());
            gamePanel.closeMerchantShop();
        });

        weaponChoices.revalidate();

        this.add(weaponChoices);
    }

    public void loadRessources(){

        try{
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-menu.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(texture, 0, 0, null);
    }
}
