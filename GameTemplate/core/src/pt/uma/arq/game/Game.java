package pt.uma.arq.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jdk.internal.icu.util.CodePointTrie;
import pt.uma.arq.entities.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;

    private BackgroundManagement backgroundManagement;
    private BitmapFont font;

    private PlayerShip player;
    private Fleet fleet;
    private int health;
    private int score;

    public void hitDetection(){
        for (int i = 0; i < player.lasers.size(); i++) {
            for(int j = 0; j < fleet.ships.size(); j++) {

                if(fleet.ships.get(j).boundingBox.contains(player.lasers.get(i).x, player.lasers.get(i).y)){
                    setScore(fleet.ships.get(j));
                    fleet.ships.remove(j);
                }
            }
        }
    }

    public void setScore(Ship ship){
        if(ship instanceof SmallShip){
            score += 5;
        }
        else if(ship instanceof MediumShip){
            score += 10;
        }
        else{
            score += 20;
        }
    }

    //TODO
    /*
    public void setHealth(){

    }
    */

    //TODO
    /*
    public void gameOver(){

    }
    */

    @Override
    public void create() {

        Gdx.graphics.setWindowedMode(600, 800);

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);

        backgroundManagement = new BackgroundManagement(batch);


        health = 100;
        score = 0;

        player = new PlayerShip(batch);
        player.create();

        fleet = new Fleet(batch);
        fleet.create();
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        hitDetection();

        player.handleInput();

        batch.begin();

        backgroundManagement.render();
        font.draw(batch, "HEALTH: " + health, 420, 780);
        font.draw(batch, "SCORE: " + score, 10, 780);

        player.render();
        fleet.render();
        fleet.fireLaser();

        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}