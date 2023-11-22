package controller.manager;

import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {

    private File soundFile;
    private Clip clip;
    private AudioInputStream audioInputStream;

    public AudioManager(String folderName, String fileName){
        this.setSoundFile(folderName, fileName);
    }

    public void setSoundFile(String folderName, String fileName) {
        this.soundFile = new File("ressources/sounds/" + folderName + "/" + fileName);
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setVolume(float volume){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }

    public void playSound(){
        clip.setFramePosition(0);
        clip.start();

    }

    public void pauseSound(){
        clip.stop();
    }

    public void stopSound(){
        clip.stop();
        clip.setFramePosition(0);
    }

    public void stopSoundFadeOut(){
        new Thread(() -> {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            for (int i = 0; i < 10; i++) {
                gainControl.setValue(gainControl.getValue() - 5);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            clip.stop();
        }).start();
    }

    public Clip getClip() {
        return clip;
    }
}
