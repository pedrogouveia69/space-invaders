package pt.uma.arq.game;

import com.badlogic.gdx.Gdx;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Audio {

    private static String rootPath = "core/assets/audio/";
    private static String laserFileName = "laser.wav";
    private static String enemyLaserFileName = "enemy-laser.wav";
    private static String explosionFileName = "explosion.wav";
    private static String backgroundMusicFileName = "interstellar-odyssey.wav";

    private static void play(String pathname){
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(rootPath + pathname)));
            clip.start();
        }
        catch (Exception e)
        {
            System.out.println("Audio.play() - " + e.getMessage());
        }
    }

    private static long getBackgroundMusicLength(){
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(rootPath + backgroundMusicFileName)));
            return clip.getMicrosecondLength();
        }
        catch (Exception e)
        {
            System.out.println("Audio.getBackgroundMusicLength() - " + e.getMessage());
            return 0;
        }
    }

    public static void playLaser(){
        play(laserFileName);
    }

    public static void playEnemyLaser(){
        play(enemyLaserFileName);
    }

    public static void playExplosion() {
        play(explosionFileName);
    }

    public static void playBackgroundMusic(){

        TimerTask loopPlay = new TimerTask() {
            @Override
            public void run() {
                //https://stackoverflow.com/questions/29467761/opengl-context-libgdx
                Gdx.app.postRunnable(new Runnable(){
                    public void run(){
                        play(backgroundMusicFileName);
                    }
                });
            }
        };
        try{
            new Timer().schedule(loopPlay,0, getBackgroundMusicLength());
        }
        catch (IllegalArgumentException e){
            System.out.println("Audio.playBackgroundMusic() - " + e.getMessage());
        }

    }

}
