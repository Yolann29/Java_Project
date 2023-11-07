
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame {

    static final public int FRAMERATE_TARGET = 60;
    static final public int FRAME_WIDTH = 650;
    static final public int FRAME_HEIGHT = 650;
    static final public int FRAME_RES = FRAME_WIDTH * FRAME_HEIGHT;
    private int imageX = 200; // Position X de l'image
    private int imageY = 200; // Position Y de l'image
    private int imageWidth = 100; // Largeur de l'image (temporaire)
    private int imageHeight = 190; // Hauteur de l'image (temporaire)
    private int stepSize = 2;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main(){
        JFrame frame = new JFrame("T-JAV-501");
        logging(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon originalIcon = new ImageIcon("poc/interface/sprite_personnage.png");
                Image img = originalIcon.getImage();
                g.drawImage(img, imageX, imageY, imageWidth, imageHeight, null);
            }
        };

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_LEFT) {
                    imageX -= stepSize;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    imageX += stepSize;
                } else if (keyCode == KeyEvent.VK_UP) {
                    imageY -= stepSize;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    imageY += stepSize;
                }

                imagePanel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });



        frame.add(imagePanel);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

    }


    public static void logging(JFrame frame){
        System.out.printf("FRAME_HEIGHT EXPECTED: %dpx | GOT : %d\n", FRAME_HEIGHT, frame.getHeight());
        System.out.printf("FRAME_WIDTH EXPECTED: %dpx | GOT : %d\n", FRAME_WIDTH, frame.getWidth());
        System.out.printf("FRAME_RES: %dpx\n", FRAME_RES);


    }



}
