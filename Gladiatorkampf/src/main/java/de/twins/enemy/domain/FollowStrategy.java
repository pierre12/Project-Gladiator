package de.twins.enemy.domain;

import de.twins.gladiator.domain.AbstractFighter;

import java.util.Random;

public class FollowStrategy implements Strategy {

    private final Minion minion;
    public FollowStrategy(Minion minion){
        this.minion = minion;
    }
    private Random random;
    @Override
    public void execute() {
        if(minion == null){
        }else{

            int newXSpeed;
            int newYSpeed;
            AbstractFighter target = minion.getTarget();
            int minionMaxSpeed = 10;
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
                if(Math.abs(xDif) > minionMaxSpeed){
                    newXSpeed = xDif> 0? minionMaxSpeed :-minionMaxSpeed;
                }else{
                    newXSpeed = xDif;
                }
                if(Math.abs(yDif)> minionMaxSpeed){
                    newYSpeed = yDif> 0? minionMaxSpeed :-minionMaxSpeed;
                }else{
                    newYSpeed = yDif;
                }
            } else {
                //random x and y speed
                random = new Random();
                newXSpeed = random.nextInt(minionMaxSpeed + 1 + minionMaxSpeed) - minionMaxSpeed;
                newYSpeed = random.nextInt(minionMaxSpeed + 1 + minionMaxSpeed) - minionMaxSpeed;

            }
            minion.setXSpeed(newXSpeed);
            minion.setYSpeed(newYSpeed);
        }


    }
}
