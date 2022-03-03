package pt.uma.arq.entities;

import pt.uma.arq.game.Animator;

import java.awt.*;

public abstract class Ship {

    protected Animator animator;
    protected int x;
    protected int y;
    protected int attackValue;
    protected boolean collided;
    protected Rectangle boundingBox;

    public Ship(){
        x = 0;
        y = 0;
        attackValue = 0;
        collided = false;
        boundingBox = new Rectangle();
    }

    public Ship(int x, int y, int attackValue){
        this.x = x;
        this.y = y;
        this.attackValue = attackValue;
        this.collided = false;
        this.boundingBox = new Rectangle(x, y);
    }

    public void create(){
        animator.create();
    }

    public void render(){
        animator.render(x,y);
    }
}
