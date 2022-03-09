package pt.uma.arq.game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {

    public static void play(String pathname){
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(pathname)));
            clip.start();
        }
        catch (Exception e)
        {
            System.out.println("Audio.play() - " + e.getMessage());
        }
    }

    public static void playLaser(){
        play("core/audio/LASRLIT1.wav");
    }

    public static void playEnemyLaser(){
        play("core/audio/Probe-Gun.wav");
    }


}
