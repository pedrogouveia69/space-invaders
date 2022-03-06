package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

public class Laser {

    protected Animator animator;
    public int x;
    public int y;

    public Laser(SpriteBatch batch, int x, int y){
        this.x = x;
        this.y = y;
        animator = new Animator(batch, "laser-bolts.png", 2, 2);
    }

    public void create(){
        animator.create();
    }


    public void render(){
        animator.render(x,y);
        y+=10;
    }
}
