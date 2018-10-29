package de.twins.equipment.domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Lance implements IsWeapon {
    @Override
    public double getStrength() {
        return 0;
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
