package de.twins.equipment.domain;


/**
 * {@link RangeWeapon} können genutzt werden um {@link Projectile}
 * zu verschiessen. Eine RangeWeapon soll zudem die Möglichkeit
 * haben verschiedene Projectile verschiessen zu können.
 */
public abstract class RangeWeapon<T extends Projectile> implements IsWeapon{


    protected double strength;
    protected int x;
    protected int y;
    protected int projectileSpeed;

    public RangeWeapon(double strength,int projectileSpeed){
        this.projectileSpeed=projectileSpeed;
        this.strength = strength;
    }

    public abstract T createProjectile();

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
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
        return 0;
    }

    @Override
    public int getYSpeed() {
        return 0;
    }


}
