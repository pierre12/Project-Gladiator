package de.twins.ui;


import de.twins.equipment.domain.Arrow;
import de.twins.equipment.domain.Projectile;
import de.twins.util.DegreeHelper;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ArrowUI extends GameObject {


    private int arrowLength;

    public ArrowUI(Projectile arrow) {
        super(arrow.getX(),arrow.getY());
        setVelx(arrow.getXSpeed());
        setVely(arrow.getYSpeed());
        arrowLength = 10;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D g1 = (Graphics2D) g;

        double degree = DegreeHelper.calculateDegree(getVelx(), getVely());
        AffineTransform transform = g1.getTransform();
        g1.rotate(  Math.toRadians(degree), x, y);
        g.drawLine(x, y, x + arrowLength, y);
        //feathers
        g.drawLine(x, y, x - 2, y - 2);
        g.drawLine(x, y, x - 2, y + 2);
        //arrowhead
        g.fillPolygon(new int[]{x + arrowLength, x - 3 + arrowLength, x - 3 + arrowLength}, new int[]{y, y + 3, y - 3}, 3);
        g1.setTransform(transform);
    }


}
