package de.twins.gladiator.domain;


public class Arrow implements Ortable {


    private final AbstractFighter owner;

    private int x;

    private int y;

    private int width;

    private int height;

    private int ySpeed;

    private int xSpeed;

    public Arrow(AbstractFighter owner) {
        this.owner = owner;
    }

    public AbstractFighter getOwner() {
        return owner;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
}
