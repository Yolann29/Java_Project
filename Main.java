import views.GamePanel;

import javax.swing.*;

public class Main {

        public static void main(String[] args) {

            JFrame window = new JFrame("T-JAV-501");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);

            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);
            window.pack();
            gamePanel.startGameLoop();

            window.setLocationRelativeTo(null);
            window.setVisible(true);

//            Fighter fighter


        }


}