package views.customwidgets;

import javax.swing.*;
import java.awt.*;

public class MenuText extends JTextPane {

    private String fullText;
    private views.customwidgets.MenuText.TypeTextTask typeTextTask;

    public MenuText(String text) {
        this.setEditable(false);
        this.fullText = text;
        this.setFont(new Font("Courier", Font.BOLD, 20));
        this.setOpaque(false);
        this.setForeground(Color.WHITE);
        this.setHighlighter(null);

        startTypingEffect();
    }

    private void startTypingEffect() {
        if(this.typeTextTask != null){
            this.typeTextTask.cancel(true);
        }
        this.typeTextTask = new views.customwidgets.MenuText.TypeTextTask();
        this.typeTextTask.execute();
    }

    private class TypeTextTask extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i <= fullText.length(); i++) {
                final String partialText = fullText.substring(0, i);
                setText(partialText);
                Thread.sleep(40);
            }
            return null;
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