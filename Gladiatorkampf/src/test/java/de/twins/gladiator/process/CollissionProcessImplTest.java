package de.twins.gladiator.process;

import de.twins.gladiator.domain.Ortable;
import de.twins.physic.Collission;
import de.twins.physic.CollissionProcess;
import de.twins.physic.CollissionProcessImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

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
        Assert.assertFalse(this.collissionProcess.doCollide(ortable, ortable));

        Ortable ortable2 = createOrtable(19, 19, 1, 1);
        Assert.assertTrue(this.collissionProcess.doCollide(ortable, ortable2));


        Ortable ortable3 = createOrtable(0, 0, 9, 10);
        Assert.assertFalse(this.collissionProcess.doCollide(ortable, ortable3));

        Ortable ortable4 = createOrtable(0, 0, 11, 10);
        Assert.assertFalse(this.collissionProcess.doCollide(ortable, ortable4));

        Ortable ortable5 = createOrtable(0, 0, 11, 11);
        Assert.assertTrue(this.collissionProcess.doCollide(ortable, ortable5));

        Ortable ortable6 = createOrtable(15, 15, 1, 1);
        Assert.assertTrue(this.collissionProcess.doCollide(ortable, ortable6));

    }

    @Test
    public void testDetermineCollissions() {

        Ortable mainOrtable = createOrtable(50, 50, 50, 50);
        Ortable topLeftNotColliding = createOrtable(0, 0, 49, 49);
        Ortable centerLeftNotColliding = createOrtable(0, 50, 49, 49);
        Ortable bottomLeftNotColliding = createOrtable(0, 100, 49, 49);
        Ortable topCenterNotColliding = createOrtable(50, 0, 49, 49);
        Ortable bottomCenterNotColliding = createOrtable(50, 101, 49, 49);
        Ortable topRightNotColliding = createOrtable(101, 0, 49, 49);
        Ortable centerRightNotColliding = createOrtable(101, 50, 49, 49);
        Ortable bottomRightNotColliding = createOrtable(101, 100, 49, 49);
        List<Ortable> notCollidingOrtables = Arrays.asList(topCenterNotColliding, centerLeftNotColliding, bottomCenterNotColliding, topLeftNotColliding, bottomLeftNotColliding, topRightNotColliding, centerRightNotColliding, bottomRightNotColliding);
        List<Collission> noCollissions = this.collissionProcess.determineCollissions(mainOrtable, notCollidingOrtables);
        Assert.assertThat(noCollissions.size(), is(0));

        Ortable collidingOrtable = createOrtable(51, 51, 1, 1);
        Ortable collidingSurroundingOrtable = createOrtable(0, 0, 300, 300);
        Ortable collidingOrtableAtTheEdge = createOrtable(99, 99, 1, 1);

        List<Ortable> collidingOrtables = Arrays.asList(collidingOrtable, collidingSurroundingOrtable, collidingOrtableAtTheEdge);
        List<Collission> collissions = this.collissionProcess.determineCollissions(mainOrtable, collidingOrtables);
        Assert.assertThat(collissions.size(),is(3));

        long filledCollissions = collissions.stream()
                .filter(collission -> collission.getOrtable1() != null)
                .filter(collission -> collission.getOrtable2() != null)
                .count();

        Assert.assertThat(filledCollissions,is(3L));
    }


    private Ortable createOrtable(int x, int y, int width, int height) {
        return new Ortable() {
            @Override
            public int getX() {
                return x;
            }

            @Override
            public int getY() {
                return y;
            }

            @Override
            public int getWidth() {
                return width;
            }

            @Override
            public int getHeight() {
                return height;
            }

            @Override
            public int getXSpeed() {
                return 0;
            }

            @Override
            public int getYSpeed() {
                return 0;
            }
        };
    }

}