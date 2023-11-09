package controller.manager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Animation {

    private final BufferedImage texture;
    private final int frameCount;
    private final long frameDuration;
    private long time;

    private BufferedImage shadow;

    public Animation(String texture, int frameCount, long frameDuration) {
        try {
            this.texture = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream(texture)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.frameCount = frameCount;
        this.frameDuration = frameDuration;
        this.time = System.currentTimeMillis();
        shadow = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
    }

    public void paint(Graphics2D g2, int x, int y, int width, int height, boolean reversed){

        int frameIndex = 0;
        long elapsed = System.currentTimeMillis() - time;
        if(elapsed >= frameDuration * frameCount){
            elapsed = 0;
            time = System.currentTimeMillis();
        }
        frameIndex = (int) Math.min(elapsed / frameDuration, frameCount - 1);
        int u = frameIndex * texture.getWidth() / frameCount;

        Graphics2D g2d = shadow.createGraphics();

        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, 32, 32);
        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(new Color(0,0,0,50));
        g2d.fillOval(0, 0, 32, 32 / 2);
        g2d.dispose();

        g2.drawImage(shadow, x, (int) (y + height / 1.4), width, height, null);

        if(reversed){
            g2.drawImage(texture, x + width, y, x, y + height, u, 0, u + texture.getWidth() / frameCount, texture.getHeight(), null);
        } else {
            g2.drawImage(texture, x, y, x + width, y + height, u, 0, u + texture.getWidth() / frameCount, texture.getHeight(), null);
        }

    }

}
