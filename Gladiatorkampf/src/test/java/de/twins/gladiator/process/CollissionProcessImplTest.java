package de.twins.gladiator.process;

import de.twins.gladiator.domain.Ortable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollissionProcessImplTest {


    private CollissionProcess collissionProcess;

    @Before
    public void setup() {
        collissionProcess = new CollissionProcessImpl();
    }


    /**
     * <-------width--------->
     * ^  (x,y)------------------+
     * |  |                      |
     * |  |                      |
     * height |  |                      |
     * |  |                      |
     * |  |                      |
     * |  |                      |
     * #  +----------------------+
     */
    @Test
    public void testOverlappingOrtableDoCollide() {
        Ortable ortable = createOrtable(10, 10, 10, 10);
        Assert.assertTrue(this.collissionProcess.doCollide(ortable, ortable));

        Ortable ortable2 = createOrtable(19, 19, 1, 1);
        Assert.assertTrue(this.collissionProcess.doCollide(ortable, ortable2));


        Ortable ortable3 = createOrtable(0, 0, 9.9, 10);
        Assert.assertFalse(this.collissionProcess.doCollide(ortable, ortable3));

        Ortable ortable4 = createOrtable(0, 0, 11, 9.9);
        Assert.assertFalse(this.collissionProcess.doCollide(ortable, ortable4));

        Ortable ortable5 = createOrtable(0, 0, 11, 11);
        Assert.assertTrue(this.collissionProcess.doCollide(ortable, ortable5));

        Ortable ortable6 = createOrtable(15, 15, 1, 1);
        Assert.assertTrue(this.collissionProcess.doCollide(ortable, ortable6));

    }


    private Ortable createOrtable(double x, double y, double width, double height) {
        return new Ortable() {
            @Override
            public double getX() {
                return x;
            }

            @Override
            public double getY() {
                return y;
            }

            @Override
            public double getWidth() {
                return width;
            }

            @Override
            public double getHeight() {
                return height;
            }
        };
    }

}