package de.twins.enemy.domain;

import de.twins.gladiator.domain.AbstractFighter;

import java.math.BigDecimal;

public class Minion extends AbstractFighter {

    private AbstractFighter target;
    public Minion(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense) {
        super(name, healthPoints, attack, defense);
        updateStats();
        currentHealthPoints = totalHealthPoints;
    }

    public AbstractFighter getTarget() {
        return target;
    }

    public void setTarget(AbstractFighter target) {
        this.target = target;
    }
}
