package de.twins.equipment.domain;

import de.twins.gladiator.domain.AbstractFighter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Lance implements IsWeapon {
    @Override
    public double getStrength() {
        return 0;
    }

    @Override
    public void setOwner(AbstractFighter abstractFighter) {

    }

    @Override
    public AbstractFighter getOwner() {
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getXSpeed() {
        throw new NotImplementedException();
    }

    @Override
    public int getYSpeed() {
        throw new NotImplementedException();
    }
}
