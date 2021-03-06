package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

public class LargeShip extends Ship{

    public LargeShip(int x, int y, SpriteBatch batch){
        super(x, y, batch);
        attackValue = 20;
        animator = new Animator(batch, "sprites/enemy-big.png", 2, 1);
        setBoundingBox();
    }

}
