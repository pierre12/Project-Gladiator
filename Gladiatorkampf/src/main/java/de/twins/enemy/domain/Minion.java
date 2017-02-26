package de.twins.enemy.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import de.twins.gladiator.domain.AbstractFighter;

@Entity
public class Minion extends AbstractFighter {

	public Minion(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense) {
		super(name, healthPoints, attack, defense);
		this.totalAttack = attack;
		this.totalHealthPoints = healthPoints;
		this.totalDefense = defense;
		this.currentHealthPoints = new BigDecimal(totalHealthPoints.toString());
	}

	@Override
	public BigDecimal defend(BigDecimal attack) {
		BigDecimal dmg = attack.subtract(this.totalDefense);
		this.currentHealthPoints = this.currentHealthPoints.subtract(dmg);
		if (dmg.compareTo(BigDecimal.ZERO) <= 0) {
			return BigDecimal.ZERO;
		}
		return dmg;
	}

}
