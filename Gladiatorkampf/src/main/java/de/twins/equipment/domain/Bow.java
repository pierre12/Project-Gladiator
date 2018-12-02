package de.twins.equipment.domain;

import de.twins.gladiator.domain.AbstractFighter;

public class Bow extends RangeWeapon<Arrow>{


    private AbstractFighter owner;

    public Bow(double strength, int projectileSpeed) {
        super(strength, projectileSpeed);
    }

    public Bow(AbstractFighter owner,double strength, int projectileSpeed) {
        super(strength, projectileSpeed);
        this.owner = owner;
    }


    @Override
    public Arrow createProjectile() {
        if(owner == null){
            return null;
        }
        Arrow arrow = new Arrow(this, owner);
        arrow.setX(owner.getX() + 10);
        arrow.setY(owner.getY() + 10);

        //projetilSpeed umrechnen in x und y Anteil
        double watchDirection = owner.getWatchDirectionInDegree();
        double x = Math.cos(Math.toRadians(watchDirection));
        double y = Math.sin(Math.toRadians(watchDirection));

        arrow.setXSpeed((int)(this.projectileSpeed * x));
        arrow.setYSpeed((int)(this.projectileSpeed * y));
        return arrow;
    }

    @Override
    public void setOwner(AbstractFighter abstractFighter) {
        this.owner = abstractFighter;
    }

    @Override
    public AbstractFighter getOwner() {
        return this.owner;
    }
}
