package de.twins.gladiator.domain;

public class Arrow extends Persistable implements Ortable {


    private double power;

    private double x;

    private double y;

    private double width;

    private double height;

    private double speed;

    public Arrow(){

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getPower() {

        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
