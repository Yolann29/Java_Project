package views.customwidgets;

import controller.Arena;

import javax.swing.*;
import java.awt.*;

public class PTextPane extends JTextPane {

    private String fullText;
    private Arena arena;

    public PTextPane(String text, Arena arena) {
        this(text);
        this.arena = arena;
    }
    public PTextPane(String text) {
        super();
        this.setEditable(false);
        this.fullText = text;
        this.setFont(new Font("Courier", Font.BOLD, 20));
        this.setBackground(new Color(0, 0, 0, 0));
        this.setForeground(Color.WHITE);
        this.setHighlighter(null);

        startTypingEffect();
    }



    private void startTypingEffect() {
        new TypeTextTask().execute();
    }

    private class TypeTextTask extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i <= fullText.length(); i++) {
                final String partialText = fullText.substring(0, i);
                setText(partialText);
                Thread.sleep(25);
            }
            return null;
        }

        @Override
        protected void done() {
            if(arena.getFighter1() != null && arena.getFighter1().isUsingItem()){
                arena.getFighter1().setUsingItem(false);
                arena.switchTurn();
            }
        }
    }

    public void setTextWithTypingEffect(String text) {
        this.fullText = text;
        startTypingEffect();
    }

    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
    }
}
