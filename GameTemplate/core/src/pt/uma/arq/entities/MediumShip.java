package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

public class MediumShip extends Ship{

    public MediumShip(int x, int y, SpriteBatch batch){
        super(x, y, batch);
        attackValue = 10;
        animator = new Animator(batch, "sprites/enemy-medium.png", 2, 1);
        setBoundingBox();
    }
}
