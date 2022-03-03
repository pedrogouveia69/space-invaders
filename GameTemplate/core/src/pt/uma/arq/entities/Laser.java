package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

public class Laser {

    protected Animator animator;
    protected int x;
    protected int y;

    public Laser(int x, int y, SpriteBatch batch){
        this.x = x;
        this.y = y;
        animator = new Animator(batch, "laser-bolts.png", 2, 2);
    }

    public void create(){
        animator.create();
    }

    // Laser goes up
    public void render(){
        animator.render(x,y);
        y+=10;
    }

    // Laser goes down
    public void renderEnemy(){
        animator.render(x,y);
        y-=10;
    }

}
