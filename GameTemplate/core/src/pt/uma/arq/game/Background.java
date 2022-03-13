package pt.uma.arq.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {

    private Sprite sprite;
    SpriteBatch spriteBatch;

    public Background(SpriteBatch batch) {
        this.spriteBatch = batch;
        sprite = new Sprite(new Texture("background.png"));
        sprite.setPosition(0, 0);
    }

    public void render() {
        sprite.draw(spriteBatch);
    }

}
