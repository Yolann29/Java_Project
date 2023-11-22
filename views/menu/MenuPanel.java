package views.menu;

import controller.Game;
import controller.entities.Player;
import controller.manager.AudioManager;
import models.Role;
import models.fighters.Warrior;
import models.weapons.FireSword;
import views.GamePanel;
import views.WorldPanel;
import views.customwidgets.HoverPanel;
import views.customwidgets.MenuText;
import views.customwidgets.PButton;
import views.menu.submenus.ArcherPanel;
import views.menu.submenus.MagePanel;
import views.menu.submenus.WarriorPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MenuPanel extends HoverPanel {

    private Game game;
    private WarriorPanel warriorPanel;
    private ArcherPanel archerPanel;
    private MagePanel magePanel;
    private JTextField nameInput;
    private JPanel playPanel;
    private PButton play;
    private Image texture, background, archerBackground, warriorBackground, magicianBackground, title;
    public static Role classePlayer;
    public static boolean magicianPicked;
    public static boolean warriorPicked;
    public static boolean archerPicked;
    private boolean changeMenu;

    public MenuPanel(GamePanel gp, Game game) {
        loadRessources();
        this.game = game;
        this.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new BorderLayout());

        this.warriorPanel = new WarriorPanel();
        this.archerPanel = new ArcherPanel();
        this.magePanel = new MagePanel();

        AudioManager music = new AudioManager("menu","mainMenu.wav");
        music.playSound();
        music.setVolume(-5);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 2, 10, 10));
        actionPanel.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, 150));
        actionPanel.setBorder(new EmptyBorder(15,0,0,0));
        actionPanel.setOpaque(false);

        this.playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        playPanel.setOpaque(false);
        playPanel.setBorder(new EmptyBorder(30,0,0,0));

        this.play = new PButton("Play");
        play.setPreferredSize(new Dimension(120,50));

        play.addActionListener(e1 -> {
            System.out.println("Menu -> Play button clicked");
            music.stopSoundFadeOut();
            gp.removeAll();
            Player player = new Player(gp.getGame(), gp.getGame().getKeyH(), classePlayer);
            String name = nameInput.getText();
            if (nameInput.getText().length() > 10) name = nameInput.getText().substring(0,10);
            if (nameInput.getText().isEmpty()) name = "YOU";
            player.fighter = new Warrior(name, 50);
            player.fighter.pickWeapon(new FireSword());
            gp.getGame().setPlayer(player);
            gp.setWorldPanel(new WorldPanel(gp.getGame().getGs(), gp, gp.getGame().getKeyH(), player, gp.getGame()));
            gp.getGame().getGs().setMenu(false);
            gp.switchToOverworld();
        });
        JPanel textContainer = new JPanel();
        textContainer.setLayout(new GridLayout(2, 1, 10, 10));
        textContainer.setOpaque(false);

        MenuText textPane = new MenuText("  Choose your avatar !\n  Enter your name :");
        textPane.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, 75));

        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.setPreferredSize(new Dimension(100, 50));
        namePanel.setBorder(new EmptyBorder(0,0,30,0));

        nameInput = new JTextField();
        nameInput.setBackground(new Color(10,100,100));
        nameInput.setFont(new Font("Courier", Font.BOLD, 20));
        nameInput.setForeground(Color.WHITE);
        nameInput.setPreferredSize(new Dimension(300, 40));
        nameInput.setBorder(new LineBorder(Color.BLACK, 2));
        namePanel.add(nameInput);

        textContainer.add(textPane);
        textContainer.add(namePanel);

        actionPanel.add(textContainer);
        actionPanel.add(playPanel);



        JPanel menuInit = new JPanel();
        menuInit.setLayout(new BorderLayout());
        menuInit.setOpaque(false);

        JPanel title = new JPanel();
        title.setLayout(new GridLayout(3,1,10,10));
        title.setPreferredSize(new Dimension(GamePanel.FRAME_WIDTH, 200));
        title.setOpaque(false);

        JPanel playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout());
        playPanel.setBorder(new EmptyBorder(80,0,0,0));
        playPanel.setOpaque(false);

        PButton chooseCharacter = new PButton("Choose Character");
        chooseCharacter.setPreferredSize(new Dimension(200,50));

        chooseCharacter.addActionListener(e1 -> {
            System.out.println("Menu -> Choose character button");
            this.removeAll();
            this.add(archerPanel, BorderLayout.WEST);
            this.add(warriorPanel, BorderLayout.CENTER);
            this.add(magePanel, BorderLayout.EAST);
            this.add(actionPanel, BorderLayout.SOUTH);
            changeMenu = true;
        });

        playPanel.add(chooseCharacter);

        JPanel quitPanel = new JPanel();
        quitPanel.setLayout(new FlowLayout());
        quitPanel.setBorder(new EmptyBorder(0,0,100,0));
        quitPanel.setOpaque(false);

        PButton quitButton = new PButton("Quit");
        quitButton.setPreferredSize(new Dimension(200,50));

        quitButton.addActionListener(e1 -> {
            System.out.println("Menu -> Quit button");
            gp.getGame().getWindow().dispose();
            System.exit(0);
        });

        quitPanel.add(quitButton);

        menuInit.add(title, BorderLayout.NORTH);
        menuInit.add(playPanel, BorderLayout.CENTER);
        menuInit.add(quitPanel, BorderLayout.SOUTH);

        this.add(menuInit);
    }

    public void loadRessources(){
        try{
            title = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/menu/logo.png")));
            magicianBackground = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/magician_background.png")));
            warriorBackground = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/warrior_background.png")));
            archerBackground = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/Archer_Background.png")));
            background = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/title_background.png")));
            texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("/textures/fight/background-menu.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (magicianPicked || warriorPicked || archerPicked) {
            if (GamePanel.isNotPanelAdded(playPanel, play)) {
                playPanel.add(play);
            }
        } else {
            playPanel.remove(play);
        }
        playPanel.revalidate();
        playPanel.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g.drawImage(background, 0, 0, null);
        g2.setColor(new Color(0,255,0,70));
        g2.fillRect(0,0,GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);
        int width = (int) (299 * 1.5);
        int height = (int) (107 * 1.5);
        int x = GamePanel.FRAME_WIDTH/2 - width/2;
        int y = 150 - height/2;
        g.drawImage(title, x, y, width, height, null);
        if (changeMenu) {
            g.drawImage(magicianBackground, 500, 0, null);
            g.drawImage(warriorBackground, 250, 0, null);
            g.drawImage(archerBackground, 0, 0, null);
            g.drawImage(texture, 0, GamePanel.FRAME_HEIGHT - 150, null);
        }
    }
}