package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

import java.awt.*;
import java.util.Date;


public class Explosion {

    private Animator animator;
    public int x;
    public int y;
    public Date createdAt;

    public Explosion(SpriteBatch batch, int x, int y){
       this.x = x;
        this.y = y;
        animator = new Animator(batch, "explosion.png", 5, 1);
        createdAt = new Date();

    }

    public void create(){
        animator.create();
    }

    public void render(){
        animator.render(x,y);
    }
}