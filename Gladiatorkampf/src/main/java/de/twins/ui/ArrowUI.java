package de.twins.ui;


import de.twins.gladiator.domain.Arrow;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ArrowUI extends GameObject {


    private int arrowLength;

    public ArrowUI(Arrow arrow) {
        super(arrow.getX(),arrow.getY());
        setVelx(arrow.getXSpeed());
        setVely(arrow.getYSpeed());
        arrowLength = 10;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D g1 = (Graphics2D) g;

        double degree = calculateDegree(getVelx(), getVely());
        AffineTransform transform = g1.getTransform();
        g1.rotate(degree, x, y);
        g.drawLine(x, y, x + arrowLength, y);
        //feathers
        g.drawLine(x, y, x - 2, y - 2);
        g.drawLine(x, y, x - 2, y + 2);
        //arrowhead
        g.fillPolygon(new int[]{x + arrowLength, x - 3 + arrowLength, x - 3 + arrowLength}, new int[]{y, y + 3, y - 3}, 3);
        g1.setTransform(transform);
    }

    private double calculateDegree(int velx, int vely) {
        // -------------------------- 0
        return Math.tan((double) vely / (double) velx);
    }
}
