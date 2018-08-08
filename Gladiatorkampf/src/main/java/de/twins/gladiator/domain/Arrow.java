package de.twins.gladiator.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Arrow implements Ortable {


    private double power;

    private int x;

    private int y;

    private int width;

    private int height;

    private double speed;

    public Arrow() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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

    @Override
    public int getXSpeed() {
        throw new NotImplementedException();
    }

    @Override
    public int getYSpeed() {
        throw new NotImplementedException();
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
