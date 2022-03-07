package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.ShipSprite;


public class SmallShip extends Ship{


    public SmallShip(int x, int y, SpriteBatch batch){
        super(x, y, batch);
        attackValue = 5;
        sprite = new ShipSprite("GameTemplate/core/assets/enemy-small.png",2, 1);
        setBoundingBox();
        setAnimator();
    }
}
