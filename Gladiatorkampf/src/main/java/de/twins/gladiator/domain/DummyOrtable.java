package de.twins.gladiator.domain;

public class DummyOrtable implements Ortable {


    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public DummyOrtable(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
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

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getXSpeed() {
        return 0;
    }

    @Override
    public int getYSpeed() {
        return 0;
    }
}
