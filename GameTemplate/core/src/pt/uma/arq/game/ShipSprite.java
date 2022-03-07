package pt.uma.arq.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShipSprite extends Sprite {

    public String imgPath;
    public int columns;
    public int rows;

    public ShipSprite(String imgPath, int columns, int rows){
        this.imgPath = imgPath;
        this.columns = columns;
        this.rows = rows;
        new Sprite(new Texture(imgPath));
        setSize();
    }

    private void setSize(){
        BufferedImage readImage;
        try {
            readImage = ImageIO.read(new File("core/assets/" + imgPath));
            setSize(readImage.getWidth(), readImage.getHeight());
        } catch (IOException e) {
            System.out.println("!!!!! setSize(): " + e.getMessage() + " !!!!!");
            System.exit(0);
        }
    }
}
