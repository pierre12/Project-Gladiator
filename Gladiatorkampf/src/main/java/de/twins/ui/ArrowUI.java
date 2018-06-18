package de.twins.ui;



import java.awt.*;

public class ArrowUI extends GameObject{


    public ArrowUI(int x, int y) {
        super(x, y);
        setVelx(10);
    }


    @Override
    public void tick() {
        setX(getX() + getVelx());
        setY(getY() + getVely());
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawLine(x ,y,x + 10,y);
    }
}
