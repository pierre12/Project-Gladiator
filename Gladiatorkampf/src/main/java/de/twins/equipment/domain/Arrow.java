package de.twins.equipment.domain;


import de.twins.equipment.domain.IsWeapon;
import de.twins.equipment.domain.Projectile;
import de.twins.gladiator.domain.AbstractFighter;

public class Arrow extends Projectile{

    private AbstractFighter owner;

    public Arrow(IsWeapon source) {
        super(source);
    }
    public Arrow(IsWeapon source,AbstractFighter owner) {
        this(source);
        this.owner = owner;
    }

    public void setOwner(AbstractFighter owner) {
        this.owner = owner;
    }

    public AbstractFighter getOwner() {
        return owner;
    }

    @Override
    public double getStrength() {
        return this.source.getStrength() + owner.getTotalAttack().doubleValue() / 2d;
    }
}
