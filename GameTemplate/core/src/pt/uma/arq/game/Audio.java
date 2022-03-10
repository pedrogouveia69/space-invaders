package pt.uma.arq.game;

import com.badlogic.gdx.Gdx;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Audio {

    private static void play(String pathname){
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

    public static void playBackgroundMusic(){

        TimerTask loopPlay = new TimerTask() {
            @Override
            public void run() {
                //https://stackoverflow.com/questions/29467761/opengl-context-libgdx
                Gdx.app.postRunnable(new Runnable(){
                    public void run(){
                        play("core/audio/LASRHVY1.wav");
                    }
                });
            }
        };
        new Timer().schedule(loopPlay, 0, 1000);
    }

}
