package de.twins.gladiator.domain;

import de.twins.equipment.domain.IsWeapon;

import java.math.BigDecimal;

public abstract class AbstractFighter implements Fightable, Ortable {

    protected String name;
    protected BigDecimal baseHealthPoints;
    protected BigDecimal baseAttack;
    protected BigDecimal baseDefense;
    protected BigDecimal currentHealthPoints;
    // stats combined from all sources
    protected BigDecimal totalHealthPoints;
    protected BigDecimal totalAttack;
    protected BigDecimal totalDefense;

    private IsWeapon weapon;

    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int xSpeed;
    protected int ySpeed;

    //zwischen 0 und 360 gibt die Richtung in Grad an in die der Character guckt
    protected double watchDirectionInDegree =0;


    /**
     * Default-Konstruktor
     */
    public AbstractFighter() {
    }

    /**
     * Konstruktor
     */
    public AbstractFighter(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense) {
        checkIfNullOrNegativeValue(attack);
        checkIfNullOrNegativeValue(defense);
        checkIfNullOrNegativeValue(healthPoints);
        this.name = name;
        this.baseAttack = attack;
        this.baseDefense = defense;
        this.baseHealthPoints = healthPoints;
        this.currentHealthPoints = BigDecimal.ZERO;
        totalHealthPoints = BigDecimal.ZERO;
        totalAttack = BigDecimal.ZERO;
        totalDefense = BigDecimal.ZERO;

    }

    protected void checkIfNullOrNegativeValue(BigDecimal value) {
        if (value == null) {
            throw new GladiatorException("value must not be null");
        }
        if (value.signum() == -1) {
            throw new GladiatorException("value must not be negative");
        }

    }

    @Override
    public BigDecimal defend(BigDecimal attack) {
        double dmgRes = getDMGResistant();
        BigDecimal nettoDmg = attack.multiply(new BigDecimal(1 - dmgRes));
        if (nettoDmg.compareTo(BigDecimal.ZERO) > 0) {
            setCurrentHealthPoints(getCurrentHealthPoints().subtract(nettoDmg));
            return nettoDmg;
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getBaseAttack() {
        return baseAttack;
    }

    @Override
    public BigDecimal getBaseDefense() {
        return baseDefense;
    }

    @Override
    public BigDecimal getBaseHealthPoints() {
        return baseHealthPoints;
    }

    @Override
    public BigDecimal getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    protected double getDMGResistant() {
        double doubleValue = this.getTotalDefense().doubleValue();
        return doubleValue != 0 ? Math.log(doubleValue) / 10 : 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getTotalAttack() {
        return this.totalAttack;
    }

    @Override
    public BigDecimal getTotalDefense() {
        return this.totalDefense;
    }

    @Override
    public BigDecimal getTotalHealthPoints() {
        return this.totalHealthPoints;
    }

    @Override
    public boolean isAlive() {
        return currentHealthPoints.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public void setBaseAttack(BigDecimal baseAttack) {
        checkIfNullOrNegativeValue(baseAttack);
        this.baseAttack = baseAttack;
        updateStats();
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    /**
     * @see de.twins.gladiator.domain.Equipable#setBaseDefense(java.math.BigDecimal)
     */
    @Override
    public void setBaseDefense(BigDecimal baseDefense) {
        checkIfNullOrNegativeValue(baseDefense);
        this.baseDefense = baseDefense;
        updateStats();
    }

    /**
     * @see de.twins.gladiator.domain.Equipable#setBaseHealthPoints(java.math.BigDecimal)
     */
    @Override
    public void setBaseHealthPoints(BigDecimal healthPoints) {
        checkIfNullOrNegativeValue(healthPoints);
        this.baseHealthPoints = healthPoints;
        updateStats();
    }

    /**
     * @see de.twins.gladiator.domain.Equipable#setCurrentHealthPoints(java.math.BigDecimal)
     */
    @Override
    public void setCurrentHealthPoints(BigDecimal hp) {
        if (hp.compareTo(BigDecimal.ZERO) <= 0) {
            this.currentHealthPoints = BigDecimal.ZERO;
        } else {
            this.currentHealthPoints = hp;
        }
    }

    /**
     * @see de.twins.gladiator.domain.Equipable#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("Name:");
        sb.append(this.getName());
        sb.append(", ");
        sb.append("CurrentHP:");
        sb.append(this.getCurrentHealthPoints());
        sb.append(", ");
        sb.append("Attack:");
        sb.append(this.getTotalAttack());
        sb.append(", ");
        sb.append("Defense:");
        sb.append(this.getTotalDefense());
        sb.append("]");
        return sb.toString();
    }

    protected void updateStats() {
        totalHealthPoints = baseHealthPoints != null ? baseHealthPoints : totalHealthPoints;
        totalAttack = baseAttack != null ? baseAttack : totalAttack;
        totalDefense = baseDefense != null ? baseDefense : totalDefense;

        if (currentHealthPoints.compareTo(totalHealthPoints) >= 0) {
            currentHealthPoints = totalHealthPoints;
        }
    }

    public IsWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(IsWeapon weapon) {
        weapon.setOwner(this);
        this.weapon = weapon;
    }

    @Override
    public int getXSpeed() {
        return xSpeed;
    }

    @Override
    public int getYSpeed() {
        return ySpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public double getWatchDirectionInDegree() {
        return watchDirectionInDegree;
    }

    public void setWatchDirectionInDegree(double watchDirectionInDegree) {
        this.watchDirectionInDegree = watchDirectionInDegree;
    }
}
