package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;
import pt.uma.arq.game.ShipSprite;
import java.awt.*;

public abstract class Ship {

    protected SpriteBatch batch;
    protected ShipSprite sprite;
    protected Animator animator;
    public int x;
    public int y;

    public int attackValue;
    public Rectangle boundingBox;

    public Ship(SpriteBatch batch){
        this.batch = batch;
    }

    public Ship(int x, int y, SpriteBatch batch){
        this.x = x;
        this.y = y;
        this.batch = batch;
    }

    //TODO try using Animator's getWidth()/getHeight() for this
    public void setBoundingBox() {
        boundingBox = new Rectangle(x, y, (int)(sprite.getWidth() / sprite.columns), (int)(sprite.getHeight() / sprite.rows));
    }

    protected void setAnimator(){
        animator = new Animator(batch, sprite.imgPath, sprite.columns, sprite.rows);
    }

    public void create(){
        animator.create();
    }

    public void render(){
        animator.render(x,y);
    }
}
