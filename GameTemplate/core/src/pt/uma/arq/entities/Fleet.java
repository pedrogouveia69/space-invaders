package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import pt.uma.arq.game.Animator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import java.util.ArrayList;

public class Fleet {

    private SpriteBatch batch;
    public ArrayList<Ship> ships;
    public ArrayList<Laser> lasers;

    public Fleet(SpriteBatch batch){

        this.batch = batch;
        this.ships = new ArrayList<Ship>();
        this.lasers = new ArrayList<Laser>();
    }

    public void create(){
        for (int i = 0; i < 9; i++) {
            // i*n is for spacing the ships
            Ship small = new SmallShip(90 + (i * 50), 550, batch);
            small.create();
            ships.add(small);
            Ship medium = new MediumShip(40 + (i * 60), 600, batch);
            medium.create();
            ships.add(medium);
            Ship large = new LargeShip(40 + (i * 60), 650, batch);
            large.create();
            ships.add(large);
        }
    }

    public void render(){
        // foreach
        for (Ship ship: ships) {
            ship.render();
        }
        for (Laser laser: lasers) {
            laser.renderEnemy();
        }
    }

    public void fireLaser(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            int rand = (int) (Math.random() * ships.size());
            Ship randShip = ships.get(rand);
            Laser laser = new Laser(randShip.x, randShip.y, batch);
            laser.create();
            lasers.add(laser);
        }

        //TODO
        /*
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Laser laser = new Laser(300, 400, batch);
                laser.create();
                fleet.lasers.add(laser);
            }
        };

        new Timer().schedule(timerTask, 1000);
        */
    }

}
