package de.twins.arena.domain;

import de.twins.gladiator.domain.Ortable;

public class Obstacle implements Ortable {


    private int height;

    private int width;
    private int x;
    private int y;

    public Obstacle(int x, int y  , int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getXSpeed() {
        return 0;
    }

    @Override
    public int getYSpeed() {
        return 0;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
