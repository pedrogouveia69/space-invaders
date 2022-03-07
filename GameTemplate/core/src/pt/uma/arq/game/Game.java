package pt.uma.arq.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background background;
    private BitmapFont mainFont;
    private BitmapFont dangerFont;

    private PlayerShip playerShip;
    private Fleet fleet;
    private int health;
    private int score;
    private float accuracy;

    private ArrayList<Explosion> explosions;

    private void detectCollisions(){

        for (int i = 0; i < playerShip.lasers.size(); i++) {
            for(int j = 0; j < fleet.ships.size(); j++) {
                if(fleet.ships.get(j).boundingBox.contains(playerShip.lasers.get(i).x, playerShip.lasers.get(i).y)) {
                    score += fleet.ships.get(j).attackValue;
                    createExplosion(fleet.ships.get(j).x, fleet.ships.get(j).y);
                    fleet.ships.remove(j);
                    playerShip.lasers.remove(i);
                    playerShip.lasersHit++;
                    break;
                }
            }
        }

        for(int i = 0; i < fleet.lasers.size(); i++) {
            if(playerShip.boundingBox.contains(fleet.lasers.get(i).x, fleet.lasers.get(i).y)){
                health -= fleet.lasers.get(i).attackValue;
                createExplosion(playerShip.x, playerShip.y);
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

    private void createExplosion(int x, int y){
        Explosion explosion = new Explosion(batch, x, y);
        explosion.create();
        explosions.add(explosion);
    }

    private void removeOldExplosions(){
        for (Explosion explosion: explosions) {
            float seconds = (new Date().getTime() -  explosion.createdAt.getTime());
            if(seconds > 500){
                explosions.remove(explosion);
                break;
            }
        }
    }

    private void drawHealthAndScore(){
        if (health < 35){
            dangerFont.draw(batch, "HEALTH: " + health, 420, 780);
        }
        else {
            mainFont.draw(batch, "HEALTH: " + health, 420, 780);
        }
        mainFont.draw(batch, "SCORE: " + score, 10, 780);
    }

    private void resetGame(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            setDefaults();
        }
    }

    private void setDefaults(){
        health = 100;
        score = 0;
        accuracy = 0;

        playerShip = new PlayerShip(batch);
        playerShip.create();

        fleet = new Fleet(batch);
        fleet.create();
        fleet.scheduleFireLaser();

        explosions = new ArrayList<>();
    }

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(600, 800);
        batch = new SpriteBatch();
        mainFont = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);
        dangerFont = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);
        dangerFont.setColor(255, 0, 0, 1);
        background = new Background(batch);

        setDefaults();
    }

    @Override
    public void render() {
        /* ???
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        */
        removeOutOfBoundsLasers();

        batch.begin();
        background.render();

        if(fleet.ships.size() == 0){
            //TODO ??? bruh
            //accuracy = playerShip.lasersHit / playerShip.lasersFired;
            mainFont.draw(batch, "YOU WON! ", 225, 600);
            resetGame();
        }
        else if (health <= 0)
        {
            dangerFont.draw(batch, "YOU LOST! ", 225, 600);
            resetGame();
        }
        else
        {
            detectCollisions();
            removeOldExplosions();
            drawHealthAndScore();

            fleet.render();
            for (Explosion explosion: explosions) {
                explosion.render();
            }
        }

        playerShip.render();
        playerShip.handleInput();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}