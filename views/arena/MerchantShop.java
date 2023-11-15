package views.arena;

import models.weapons.ElectricGauntlet;
import models.weapons.FireSword;
import models.weapons.GroundSpear;
import models.weapons.IceSword;
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

    public MerchantShop(GamePanel gamePanel, WorldPanel worldPanel) {
        this.setBackground(new Color(141, 141, 141,255));

        loadRessources();

        JPanel weaponChoices = new JPanel();
        weaponChoices.setLayout(new GridLayout(2, 2, 10, 10));
        weaponChoices.setPreferredSize(new Dimension(500, 100));
        weaponChoices.setBackground(new Color(0,0,0,0));

        PButton iceswordChoice = new PButton("Ice Sword");
        PButton fireswordChoice = new PButton("Fire Sword");
        PButton electricGauntletChoice = new PButton("Electric Gauntlet");
        PButton groundSpearChoice = new PButton("Ground Spear");

        weaponChoices.add(iceswordChoice);
        weaponChoices.add(fireswordChoice);
        weaponChoices.add(electricGauntletChoice);
        weaponChoices.add(groundSpearChoice);

        iceswordChoice.addActionListener(e -> {
            gamePanel.player.fighter.pickWeapon(new IceSword());
            worldPanel.merchantClose = false;
        });
        fireswordChoice.addActionListener(e -> {
            gamePanel.player.fighter.pickWeapon(new FireSword());
            worldPanel.merchantClose = false;
        });
        electricGauntletChoice.addActionListener(e -> {
            gamePanel.player.fighter.pickWeapon(new ElectricGauntlet());
            worldPanel.merchantClose = false;
        });
        groundSpearChoice.addActionListener(e -> {
            gamePanel.player.fighter.pickWeapon(new GroundSpear());
            worldPanel.merchantClose = false;
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
