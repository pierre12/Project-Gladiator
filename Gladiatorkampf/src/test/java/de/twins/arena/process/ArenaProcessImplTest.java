package de.twins.arena.process;

import de.twins.arena.domain.Arena;
import de.twins.arena.domain.Obstacle;
import de.twins.enemy.domain.Minion;
import de.twins.equipment.domain.Arrow;
import de.twins.equipment.domain.Bow;
import de.twins.gladiator.domain.Gladiator;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ArenaProcessImplTest {


    ArenaProcess arenaProcess = new ArenaProcessImpl();



    @Test
    public void arrowDisappearsWhenHittingFighter(){

        // 0 0 arrow -> 10 0 fighter
        Arena arena = new Arena(100,100);
        Gladiator gladiator = new Gladiator();
        Bow bow = new Bow(1, 10);
        gladiator.setWeapon(bow);
        Arrow arrow = bow.createProjectile();
        arrow.setX(0);
        arrow.setY(0);
        arrow.setHeight(14);
        arrow.setWidth(10);
        arrow.setXSpeed(10);

        Minion test = new Minion("Test", 10d, 10d, 10d);
        test.setX(10);
        test.setY(0);
        test.setWidth(10);
        test.setHeight(10);
        test.setStrategy(null);
        arena.addFighter(test);
        arena.addArrow(arrow);

        Assert.assertThat(arena.getArrows().size(), Matchers.is(1));
        arenaProcess.tick(arena);
        Assert.assertThat(arena.getArrows().size(), Matchers.is(0));


    }

    @Test
    public void arrowDisappearsWhenHittingObstacle(){

        // 0 0 arrow -> 10 0 fighter
        Arena arena = new Arena(100,100);
        Gladiator gladiator = new Gladiator();
        Bow bow = new Bow(1, 10);
        gladiator.setWeapon(bow);
        Arrow arrow = bow.createProjectile();
        arrow.setX(0);
        arrow.setY(0);
        arrow.setHeight(14);
        arrow.setWidth(10);
        arrow.setXSpeed(10);

        arena.addObstacle(new Obstacle(10,0,10,10));
        arena.addArrow(arrow);

        Assert.assertThat(arena.getArrows().size(), Matchers.is(1));
        arenaProcess.tick(arena);
        Assert.assertThat(arena.getArrows().size(), Matchers.is(0));


    }
}