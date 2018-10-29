package de.twins.arena.process;

import de.twins.arena.domain.Arena;
import de.twins.enemy.domain.Minion;
import de.twins.enemy.domain.Strategy;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.physic.CollissionProcess;
import de.twins.physic.CollissionProcessImpl;

import java.util.List;
import java.util.Random;

public class ArenaProcess {

    private CollissionProcess collissionProcess = new CollissionProcessImpl();



    public Arena tick(Arena arena) {
        int arenaHeigth = arena.getHeigth();
        int arenaWidth = arena.getWidth();

        List<AbstractFighter> abstractFighters = arena.getAbstractFighters();
        for (AbstractFighter abstractFighter : abstractFighters) {
            int xSpeed = abstractFighter.getXSpeed();
            int ySpeed = abstractFighter.getYSpeed();

            if (abstractFighter instanceof Minion) {
                Strategy strategy = ((Minion) abstractFighter).getStrategy();
                if (strategy != null) {
                    strategy.execute();
                }
            }


            int oldX = abstractFighter.getX();
            int oldY = abstractFighter.getY();
            int width = abstractFighter.getWidth();
            int height = abstractFighter.getHeight();
            int newX = oldX + xSpeed;
            int newY = oldY + ySpeed;
            if (newX + width <= arenaWidth && newX >= 10) {
                abstractFighter.setX(newX);
            }
            if (newX < 10) {
                abstractFighter.setX(10);
            }
            if (newX + width >= arenaWidth) {
                abstractFighter.setX(arenaWidth - width);
            }

            if (newY < arenaHeigth && newX >= 10) {
                abstractFighter.setY(newY);
            }

            if (newY < 10) {
                abstractFighter.setY(10);
            }

            if (newY + height > arenaHeigth) {
                abstractFighter.setY(arenaHeigth - height);
            }

            if (!collissionProcess.determineCollissions(abstractFighter, abstractFighters).isEmpty()) {
                abstractFighter.setY(oldY);
                abstractFighter.setX(oldX);
            }
        }

        return arena;
    }


}
