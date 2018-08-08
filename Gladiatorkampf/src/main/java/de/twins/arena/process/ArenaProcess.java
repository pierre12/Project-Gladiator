package de.twins.arena.process;

import de.twins.arena.domain.Arena;
import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.physic.CollissionProcess;
import de.twins.physic.CollissionProcessImpl;

import java.util.List;
import java.util.Random;

public class ArenaProcess {

    private CollissionProcess collissionProcess = new CollissionProcessImpl();
    private Random random;


    public Arena tick(Arena arena) {
        int arenaHeigth = arena.getHeigth();
        int arenaWidth = arena.getWidth();

        List<AbstractFighter> abstractFighters = arena.getAbstractFighters();
        for (AbstractFighter abstractFighter : abstractFighters) {
            int xSpeed = abstractFighter.getXSpeed();
            int ySpeed = abstractFighter.getYSpeed();

            if (abstractFighter instanceof Minion) {
                determineMinionSpeed((Minion) abstractFighter);
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

    private void determineMinionSpeed(Minion minion) {
        int newXSpeed = 0;
        int newYSpeed = 0;
        AbstractFighter target = minion.getTarget();
        if (target != null) {
            int x = target.getX();
            int y = target.getY();

            int x1 = minion.getX();
            int y1 = minion.getY();

            int xDif = x - x1;
            if(xDif < 0){
                xDif = xDif + target.getWidth();
            }
            int yDif = y - y1;
            if(yDif < 0){
                yDif = yDif + target.getHeight();
            }
            if(Math.abs(xDif) > 10){
                newXSpeed = xDif> 0?10:-10;
            }else{
                newXSpeed = xDif;
            }
            if(Math.abs(yDif)> 10){
                newYSpeed = yDif> 0?10:-10;
            }else{
                newYSpeed = yDif;
            }
        } else {
            //random x and y speed
            random = new Random();
            newXSpeed = random.nextInt(10 + 1 + 10) - 10;
            newYSpeed = random.nextInt(10 + 1 + 10) - 10;

        }

        minion.setXSpeed(newXSpeed);
        minion.setYSpeed(newYSpeed);
    }
}
