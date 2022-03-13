package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;
import pt.uma.arq.game.Audio;

import java.util.ArrayList;

public class PlayerShip extends Ship{

    public ArrayList<Laser> lasers;
    public int lasersFired;
    public int lasersHit;

    public PlayerShip(SpriteBatch batch){
        super(batch);;
        x = 300;
        y = 20;
        animator = new Animator(batch, "sprites/ship.png",5,2);
        setBoundingBox();
        lasers = new ArrayList<>();
        create();
    }

    public void render(){
        animator.render(x, y);
        for (Laser laser: lasers) {
            laser.render();
        }
    }

    public void handleInput(){
        moveLeft();
        moveRight();
        fireLaser();
    }

    private void moveLeft() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (x > 0) {
                x -= 10;
            }
        }
        setBoundingBox();
    }

    private void moveRight(){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (x < 600 - animator.getWidth()) {
                x += 10;
            }
        }
        setBoundingBox();
    }

    private void fireLaser() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Laser laser = new Laser(batch, x, y);
            lasers.add(laser);
            lasersFired++;
            Audio.playLaser();
        }
    }
}
