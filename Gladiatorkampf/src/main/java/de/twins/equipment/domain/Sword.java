package de.twins.equipment.domain;

import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Ortable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Sword implements IsWeapon {

    private int width;

    private int height;

    private int x;

    private int y;

    private double strength;
    private AbstractFighter owner;

    public Sword(){

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int getXSpeed() {
        throw new NotImplementedException();
    }

    @Override
    public int getYSpeed() {
        throw new NotImplementedException();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getStrength() {
        return strength;
    }

    @Override
    public void setOwner(AbstractFighter abstractFighter) {
        this.owner = abstractFighter;    }

    @Override
    public AbstractFighter getOwner() {
        return null;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
