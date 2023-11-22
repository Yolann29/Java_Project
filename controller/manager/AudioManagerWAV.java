package controller.manager;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.Objects;

public class AudioManagerWAV {

    private Clip clip;
    private AudioInputStream audioInputStream;

    public AudioManagerWAV(String folderName, String fileName){
        this.setSoundFile(folderName, fileName);
    }

    public void setSoundFile(String folderName, String fileName) {
        String path = "/ressources/sounds/" + folderName + "/" + fileName;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(this.getClass().getResourceAsStream(path))));
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

    public void playSound(float volume){
        setVolume(volume);
        playSound();
    }

    public void playSound(){
        clip.setFramePosition(0);
        clip.start();

    }


    public Clip getClip() {
        return clip;
    }
}
