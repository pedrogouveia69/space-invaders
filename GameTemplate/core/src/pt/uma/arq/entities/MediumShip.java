package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

import java.awt.*;

public class MediumShip extends Ship{

    public MediumShip(int x, int y, SpriteBatch batch){
        super(x, y);
        boundingBox = new Rectangle(x, y, 48, 24);
        animator = new Animator(batch, "enemy-medium.png", 2, 1);
    }
}
