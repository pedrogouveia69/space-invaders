package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

import java.awt.*;

public abstract class Ship {

    protected SpriteBatch batch;
    protected Animator animator;
    public int x;
    public int y;

    public int attackValue;
    public Rectangle boundingBox;

    public Ship(SpriteBatch batch){
        this.batch = batch;
    }

    public Ship(int x, int y, SpriteBatch batch){
        this.x = x;
        this.y = y;
        this.batch = batch;
    }

    public void setBoundingBox() {
        create();
        boundingBox = new Rectangle(x, y, animator.getWidth(), animator.getHeight());
    }

    public void create(){
        animator.create();
    }

    public void render(){
        animator.render(x,y);
    }
}
