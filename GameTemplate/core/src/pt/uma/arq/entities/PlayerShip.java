package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.ShipSprite;
import java.util.ArrayList;

public class PlayerShip extends Ship{

    public ArrayList<Laser> lasers;

    public PlayerShip(SpriteBatch batch){
        super(batch);
        sprite = new ShipSprite("core/assets/ship.png", 5, 2);
        x = 300;
        y = 20;
        setBoundingBox();
        setAnimator();
        lasers = new ArrayList<Laser>();
    }

    public void create(){
        animator.create();
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
            if (x < 0) {
                x = 600;
            } else {
                x -= 10;
            }
        }
        setBoundingBox();
    }

    private void moveRight(){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (x > 600) {
                x = 0;
            } else {
                x += 10;
            }
        }
        setBoundingBox();
    }

    private void fireLaser() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Laser laser = new Laser(batch, x, y);
            lasers.add(laser);
            laser.create();
        }
    }
}
