package views.customwidgets;

import javax.swing.*;
import java.awt.*;

public class PTextPane extends JTextPane {

    public PTextPane(String text){
        super();
        this.setEditable(false);
        this.setText(text);
        this.setFont(new Font("Courier", Font.BOLD, 20));
        this.setBackground(new Color(0,0,0,0));
        this.setForeground(Color.WHITE);
        this.setHighlighter(null);

    }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
    }
}
