package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

import java.awt.*;

public abstract class Ship {

    protected Animator animator;
    protected int x;
    protected int y;
    protected int attackValue;
    public boolean collided;
    public Rectangle boundingBox;

    public Ship(){
        x = 0;
        y = 0;
        attackValue = 0;
        collided = false;
        boundingBox = new Rectangle();
    }

    public Ship(int x, int y){
        this.x = x;
        this.y = y;
        this.collided = false;
        this.boundingBox = new Rectangle(x, y, 20, 20); //TODO set for each ship
    }

    public void create(){
        animator.create();
    }

    public void render(){
        animator.render(x,y);
    }
}
