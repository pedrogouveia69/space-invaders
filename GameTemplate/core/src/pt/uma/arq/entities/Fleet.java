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
    public ArrayList<EnemyLaser> lasers;

    public Fleet(SpriteBatch batch){
        this.batch = batch;
        this.ships = new ArrayList<Ship>();
        this.lasers = new ArrayList<EnemyLaser>();
    }

    public void create(){
        for (int i = 0; i < 9; i++) {
            // i*n is for spacing the ships
            Ship small = new SmallShip(90 + (i * 50), 550, batch);
            Ship medium = new MediumShip(40 + (i * 60), 600, batch);
            Ship large = new LargeShip(40 + (i * 60), 650, batch);

            ships.add(small);
            ships.add(medium);
            ships.add(large);

            small.create();
            medium.create();
            large.create();
        }
    }

    public void render(){
        // foreach
        for (Ship ship: ships) {
            ship.render();
        }
    }

    public void fireLaser(){
        if(ships.size() > 0){
            int rand = (int) (Math.random() * ships.size());
            Ship randShip = ships.get(rand);
            EnemyLaser laser = new EnemyLaser(batch, randShip.x, randShip.y, randShip.attackValue);
            lasers.add(laser);
            laser.create();
        }
    }
}
