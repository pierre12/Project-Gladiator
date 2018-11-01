package de.twins.gladiator.domain;

public class Position {

    private int x;

    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
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


    public boolean isInOrtable(Ortable ortable) {
        int minY = ortable.getY();
        int maxY = ortable.maxY();
        int minX = ortable.getX();
        int maxX = ortable.maxX();

        return this.x > minX && this.x < maxX && this.y > minY && this.y < maxY;
    }

    @Override
    public String toString() {
        return String.format("Position x:%s , y:%s",this.x,this.y);
    }
}
