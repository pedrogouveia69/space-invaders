package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

import java.awt.*;

public class SmallShip extends Ship{


    public SmallShip(int x, int y, SpriteBatch batch){
        super(x, y);
        animator = new Animator(batch, "enemy-small.png", 2, 1);
    }
}
