package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

public class MediumShip extends Ship{

    public MediumShip(int x, int y, int attackValue, SpriteBatch batch){
        super(x,y,attackValue);
        animator = new Animator(batch, "enemy-medium.png", 2, 1);
    }
}
