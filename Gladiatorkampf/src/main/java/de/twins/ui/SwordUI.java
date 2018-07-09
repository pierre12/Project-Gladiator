package de.twins.ui;

import de.twins.gladiator.domain.AbstractFighter;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SwordUI extends GameObject {

    private final AbstractFighterUI abstractFighter;
    private double rotate=-90;
    public SwordUI(int x, int y, AbstractFighterUI abstractFighter) {
        super(x, y);
        this.abstractFighter = abstractFighter;
    }

    @Override
    public void tick() {
        rotate += 12;
        if(rotate >= 90){
            rotate=-90;
        }
    }

    @Override
    public void render(Graphics g) {
        int x = abstractFighter.getX()+abstractFighter.getWidth() + 10;
        int y = abstractFighter.getY()+abstractFighter.getHeight() / 2 ;
        g.setColor(Color.gray);
        Graphics2D g1 = (Graphics2D) g;
        AffineTransform transform = g1.getTransform();
        g1.rotate(Math.toRadians(rotate),x,y);
        g.fillRect(x,y,20,4);
        g.fillRect(x +1,y - 3,2,10);
        g1.setTransform(transform);
    }
}
