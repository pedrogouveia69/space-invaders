package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.ShipSprite;

public class LargeShip extends Ship{

    public LargeShip(int x, int y, SpriteBatch batch){
        super(x, y, batch);
        attackValue = 20;
        sprite = new ShipSprite("GameTemplate/core/assets/enemy-big.png",2, 1);
        setBoundingBox();
        setAnimator();
    }

}
