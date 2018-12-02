package de.twins.equipment.domain;

/**
 * Ein Projektil wird von einer Waffe aus abgefeuert
 * und übernimmt Werte dieser Waffe
 */
public abstract class Projectile implements IsWeapon {

    protected IsWeapon source;

    protected int x;

    protected int y;

    protected int width;

    protected int height;

    protected int ySpeed;

    protected int xSpeed;

    public Projectile(IsWeapon weapon){
        this.source = weapon;
    }


    public void setY(int y){
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    @Override
    public int getX() {
        return x;
    }



    @Override
    public int getY() {
        return y;
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


    public IsWeapon getSource() {
        return source;
    }
}
