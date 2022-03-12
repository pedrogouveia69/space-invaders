package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import java.util.ArrayList;

public class Fleet {

    private SpriteBatch batch;
    public ArrayList<Ship> ships;
    public ArrayList<EnemyLaser> lasers;
    private int moveDownPixelCount;
    private xAxisPosition position;

    public Fleet(SpriteBatch batch){
        this.batch = batch;
        this.ships = new ArrayList<>();
        this.lasers = new ArrayList<>();
        position = xAxisPosition.CENTER;
        create();
        startTimers();
    }

    private void create(){
        for (int i = 0; i < 9; i++) {
            // i*n is for spacing the ships
            Ship small = new SmallShip(90 + (i * 50), 600, batch);
            Ship medium = new MediumShip(40 + (i * 60), 650, batch);
            Ship large = new LargeShip(40 + (i * 60), 700, batch);

            ships.add(small);
            ships.add(medium);
            ships.add(large);

            small.create();
            medium.create();
            large.create();
        }
    }

    public void render(){
        for (Ship ship: ships) {
            ship.render();
        }
        for (EnemyLaser laser: lasers) {
            laser.render();
        }
    }

    private void fireLaser(){
        if(ships.size() > 0){
            int rand = (int) (Math.random() * ships.size());
            Ship randShip = ships.get(rand);
            EnemyLaser laser = new EnemyLaser(batch, randShip.x, randShip.y, randShip.attackValue);
            lasers.add(laser);
            //Audio.playEnemyLaser();
        }
    }

    private void moveDown(){
        if(moveDownPixelCount < 500){
            for (Ship ship: ships) {
                ship.y -= 50;
                ship.setBoundingBox();

            }
            moveDownPixelCount += 50;
        }
    }

    private enum xAxisPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    private void moveLeft(){
        for (Ship ship: ships) {
            ship.x -= 25;
            ship.setBoundingBox();
        }
        position = xAxisPosition.LEFT;
    }

    private void moveRight(){
        for (Ship ship: ships) {
            ship.x += 25;
            ship.setBoundingBox();
        }
        position = xAxisPosition.RIGHT;
    }

    private void moveCenter(){
        if(position == xAxisPosition.LEFT){
            moveRight();
        }
        else if(position == xAxisPosition.RIGHT){
            moveLeft();
        }
        position = xAxisPosition.CENTER;
    }

    private void moveXAxis(){
        if(position == xAxisPosition.CENTER){
            if(new Random().nextBoolean()){
                moveLeft();
            }
            else{
                moveRight();
            }
        }
        else{
            moveCenter();
        }
    }

    private void scheduleFireLaser(){
        TimerTask fireLaser = new TimerTask() {
            @Override
            public void run() {
                //https://stackoverflow.com/questions/29467761/opengl-context-libgdx
                Gdx.app.postRunnable(new Runnable(){
                    public void run(){
                        fireLaser();
                    }
                });
            }
        };
        new Timer().schedule(fireLaser, 500, 500);
    }

    private void scheduleMoveDown(){
        TimerTask moveDown = new TimerTask() {
            @Override
            public void run() {
                //https://stackoverflow.com/questions/29467761/opengl-context-libgdx
                Gdx.app.postRunnable(new Runnable(){
                    public void run(){
                        moveDown();
                    }
                });
            }
        };
        new Timer().schedule(moveDown, 3500, 3500);
    }

    private void scheduleMoveXAxis(){
        TimerTask moveXAxis = new TimerTask() {
            @Override
            public void run() {
                //https://stackoverflow.com/questions/29467761/opengl-context-libgdx
                Gdx.app.postRunnable(new Runnable(){
                    public void run(){
                        moveXAxis();
                    }
                });
            }
        };
        new Timer().schedule(moveXAxis, 2500, 2500);
    }

    private void startTimers(){
        scheduleFireLaser();
        scheduleMoveXAxis();
        scheduleMoveDown();
    }
}
