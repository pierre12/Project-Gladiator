package de.twins.gladiator.domain;


import java.math.BigDecimal;

public interface Fightable {

    /**
     * Reduces enemy attack power by own defense value. The reduced attack power
     * substracts directly the hp.
     *
     * @param attack The attack power which reduces the hp.
     * @return true falls der Schaden durchkam
     */
    BigDecimal defend(BigDecimal attack);

    /**
     * Returns the base attack which is originated from the {@link Fightable}.
     *
     * @return the base attack which is originated from the {@link Fightable}.
     */
    BigDecimal getBaseAttack();

    /**
     * Returns the base defense which is originated from the {@link Fightable}.
     *
     * @return the base defense which is originated from the {@link Fightable}.
     */
    BigDecimal getBaseDefense();

    /**
     * Returns the base health points which is originated from the {@link Fightable}.
     *
     * @return the base health points which is originated from the {@link Fightable}.
     */
    BigDecimal getBaseHealthPoints();

    /**
     * Returns the current health points of the {@link Fightable}.
     *
     * @return the current health points of the {@link Fightable}.
     */
    BigDecimal getCurrentHealthPoints();

    /**
     * Returns the name of the {@link Fightable}.
     *
     * @return the name of the {@link Fightable}.
     */
    String getName();

    /**
     * Return the total attack of the {@link Fightable} .Which is the sum of the
     * equipment and base attack.
     *
     * @return the total attack of the {@link Fightable}.
     */
    BigDecimal getTotalAttack();

    /**
     * Return the total attack of the {@link Fightable} .Which is the sum of the
     * equipment and base defense.
     *
     * @return the total attack of the {@link Fightable}.
     */
    BigDecimal getTotalDefense();

    /**
     * Return the total attack of the {@link Fightable} .Which is the sum of the
     * equipment and base health points.
     *
     * @return the total attack of the {@link Fightable}.
     */
    BigDecimal getTotalHealthPoints();

    /**
     * Returns true if {@link Fightable} is alive.
     *
     * @return true if {@link Fightable} is alive.
     */
    boolean isAlive();

    /**
     * Sets the base attack of the {@link Fightable}.
     *
     * @param baseAttack base attack which should be set.
     */
    void setBaseAttack(BigDecimal baseAttack);

    /**
     * Sets the base attack of the {@link Fightable}.
     *
     * @param baseAttack base attack which should be set.
     */
    void setBaseDefense(BigDecimal baseDefense);

    /**
     * Sets the base health points of the {@link Fightable}.
     *
     * @param healthPoints base health points which should be set.
     */
    void setBaseHealthPoints(BigDecimal healthPoints);

    /**
     * Sets the base attack of the {@link Fightable}
     *
     * @param healthPoints current health points which should be set.
     */
    void setCurrentHealthPoints(BigDecimal healthPoints);

    /**
     * Sets the name of the {@link Fightable}
     *
     * @param name name which should be set.
     */
    void setName(String name);


}
