package de.twins.ui;

import java.awt.*;

public class LanceUI extends GameObject {

    private int defaultLength=12;
    private final AbstractFighterUI abstractFighter;
    private int length=defaultLength;
    private int expansionSpeed = 3;

    public LanceUI(int x, int y, AbstractFighterUI abstractFighter) {
        super(x, y);
        this.abstractFighter = abstractFighter;
    }

    @Override
    public void tick() {
        length = length + expansionSpeed;
        if(length > 70){
            length = defaultLength;
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        int x = abstractFighter.getX()+abstractFighter.getWidth() + 10;
        int y = abstractFighter.getY()+abstractFighter.getHeight() / 2 ;
        g.setColor(Color.yellow);
        g.fillRect(x,y,length,4);
        g.fillPolygon(new Polygon(new int[]{x+length,x+length +10,x+length}, new int[]{y-3,y+2,y+7},3));
    }
}
