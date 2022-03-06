package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyLaser extends Laser{

    public int attackValue;

    public EnemyLaser(SpriteBatch batch, int x, int y, int attackValue) {
        super(batch, x, y);
        this.attackValue = attackValue;
    }

    public void render(){
        animator.render(x,y);
        y-=10;
    }
}
