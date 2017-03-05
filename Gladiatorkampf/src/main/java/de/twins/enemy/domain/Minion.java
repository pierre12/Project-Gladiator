package de.twins.enemy.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import de.twins.gladiator.domain.AbstractFighter;

@Entity
public class Minion extends AbstractFighter {

	public Minion(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense) {
		super(name, healthPoints, attack, defense);
		updateStats();
		currentHealthPoints = totalHealthPoints;
	}

}
