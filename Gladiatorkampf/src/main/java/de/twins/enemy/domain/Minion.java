package de.twins.enemy.domain;

import de.twins.gladiator.domain.AbstractFighter;

import java.math.BigDecimal;

public class Minion extends AbstractFighter {

    public Minion(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense) {
        super(name, healthPoints, attack, defense);
        updateStats();
        currentHealthPoints = totalHealthPoints;
    }

}
