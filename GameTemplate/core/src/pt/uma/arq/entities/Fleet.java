package pt.uma.arq.entities;

import pt.uma.arq.game.Animator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.TimerTask;

import java.util.ArrayList;

public class Fleet {

    private SpriteBatch batch;
    private ArrayList<Ship> ships;
    private ArrayList<Laser> enemyLasers;

    public Fleet(SpriteBatch batch){

        this.batch = batch;
        this.ships = new ArrayList<Ship>();
        this.enemyLasers = new ArrayList<Laser>();
    }

    public void create(){
        for (int i = 0; i < 9; i++) {
            // i*n is for spacing the ships
            Ship small = new SmallShip(90 + (i * 50), 550, 5, batch);
            small.create();
            ships.add(small);
            Ship medium = new MediumShip(40 + (i * 60), 600, 10, batch);
            medium.create();
            ships.add(medium);
            Ship large = new LargeShip(40 + (i * 60), 650, 20, batch);
            large.create();
            ships.add(large);
        }
    }

    public void render(){
        // foreach
        for (Ship ship: ships) {
            ship.render();
        }
        for (Laser laser: enemyLasers) {
            laser.renderEnemy();
        }
    }

    public void fireLaser(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            }
        };
    }

}
