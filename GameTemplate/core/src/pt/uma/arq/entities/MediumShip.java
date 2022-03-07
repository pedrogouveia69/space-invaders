package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.ShipSprite;

public class MediumShip extends Ship{

    public MediumShip(int x, int y, SpriteBatch batch){
        super(x, y, batch);
        attackValue = 10;
        sprite = new ShipSprite("GameTemplate/core/assets/enemy-medium.png",2, 1);
        setBoundingBox();
        setAnimator();
    }
}
