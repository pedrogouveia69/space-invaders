package pt.uma.arq.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.entities.*;

import java.util.Timer;
import java.util.TimerTask;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background background;
    private BitmapFont font;

    private PlayerShip playerShip;
    private Fleet fleet;
    private int health;
    private int score;

    private TimerTask fireLaser;

    private void detectCollisions(){

        for (int i = 0; i < playerShip.lasers.size(); i++) {
            for(int j = 0; j < fleet.ships.size(); j++) {
                if(fleet.ships.get(j).boundingBox.contains(playerShip.lasers.get(i).x, playerShip.lasers.get(i).y)){
                    score += fleet.ships.get(j).attackValue;
                    fleet.ships.remove(j);
                    playerShip.lasers.remove(i);
                    break;
                }
            }
        }

        for(int i = 0; i < fleet.lasers.size(); i++) {
            if(playerShip.boundingBox.contains(fleet.lasers.get(i).x, fleet.lasers.get(i).y)){
                health -= fleet.lasers.get(i).attackValue;
                fleet.lasers.remove(i);
                break;
            }
        }
    }

    private void removeOutOfBoundsLasers(){

        for (int i = 0; i < fleet.lasers.size(); i++) {
            if(fleet.lasers.get(i).y < 0){
                fleet.lasers.remove(i);
                break;
            }
        }

        for (int i = 0; i < playerShip.lasers.size(); i++) {
            if(playerShip.lasers.get(i).y > 800){
                playerShip.lasers.remove(i);
                break;
            }
        }
    }


    @Override
    public void create() {

        Gdx.graphics.setWindowedMode(600, 800);

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);

        background = new Background(batch);

        health = 100;
        score = 0;

        playerShip = new PlayerShip(batch);
        playerShip.create();

        fleet= new Fleet(batch);
        fleet.create();

        //TODO https://stackoverflow.com/questions/29467761/opengl-context-libgdx
        fireLaser = new TimerTask() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable(){
                    public void run(){
                        fleet.fireLaser();
                    }
                });
            }
        };

        new Timer().schedule(fireLaser, 0, 500);

    }

    @Override
    public void render() {
        batch.begin();

        /* ???
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        */

        removeOutOfBoundsLasers();
        detectCollisions();

        background.render();

        font.draw(batch, "HEALTH: " + health, 420, 780);
        font.draw(batch, "SCORE: " + score, 10, 780);

        playerShip.render();
        fleet.render();

        for (Laser laser: playerShip.lasers) {
            laser.render();
        }

        for (EnemyLaser laser: fleet.lasers) {
            laser.render();
        }

        playerShip.handleInput();

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}