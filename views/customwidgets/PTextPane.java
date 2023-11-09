package views.customwidgets;

import javax.swing.*;
import java.awt.*;

public class PTextPane extends JTextPane {

    private String fullText;

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
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        setText(partialText);
                    }
                });
                Thread.sleep(25); // Ajustez cela en fonction de la vitesse que vous souhaitez
            }
            return null;
        }

        @Override
        protected void done() {
            System.out.println("Done");
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
