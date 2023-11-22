package controller.manager;
;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.InputStream;

public class AudioManagerMP3 {

    private String filePath;
    private AdvancedPlayer player;
    MyJavaSoundAudioDevice audioDevice;
    InputStream inputStream;


    public AudioManagerMP3(String fileFolder, String fileName){
        this.filePath = "/ressources/sounds/" + fileFolder + "/" + fileName;
        reloadSound();
    }

    public void playSound() {
        playSound(1f);
    }

    public void playSound(float volume) {
        new Thread(() -> {

            try {
                this.setVolume(volume);
                player.play();
            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }


        }).start();

    }

    public void setVolume(float volume){
        audioDevice.setVolume(volume);
    }

    public void stopSoundFadeOut(double time){
        new Thread(() -> {
            float step = audioDevice.getVolume() / 10;
            for (int i = 0; i < 10; i++) {
                audioDevice.setVolume(audioDevice.getVolume() - step);
                try {
                    Thread.sleep((long) (time * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stopSound();
        }).start();
    }

    public void stopSound(){
        player.stop();
        reloadSound();
    }

    public void reloadSound(){
        try {
            audioDevice = new MyJavaSoundAudioDevice();
            inputStream = this.getClass().getResourceAsStream(filePath);
            player = new AdvancedPlayer(inputStream, audioDevice);
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {

                }
            });
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}


