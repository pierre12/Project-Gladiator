package de.twins.gladiator.domain;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AbstractFighter extends Persistable implements Fightable {



	protected String name;
	protected BigDecimal baseHealthPoints;
	protected BigDecimal baseAttack;
	protected BigDecimal baseDefense;
	protected BigDecimal currentHealthPoints;
	// stats combined from all sources
	protected BigDecimal totalHealthPoints;
	protected BigDecimal totalAttack;
	protected BigDecimal totalDefense;
	/**
	 * 
	 * Konstruktor
	 */
	public AbstractFighter(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense) {

		this.name = name;
		this.baseAttack = attack;
		this.baseDefense = defense;
		this.baseHealthPoints = healthPoints;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBaseHealthPoints() {
		return baseHealthPoints;
	}

	public void setBaseHealthPoints(BigDecimal baseHealthPoints) {
		this.baseHealthPoints = baseHealthPoints;
	}

	public BigDecimal getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(BigDecimal baseAttack) {
		this.baseAttack = baseAttack;
	}

	public BigDecimal getBaseDefense() {
		return baseDefense;
	}

	public void setBaseDefense(BigDecimal baseDefense) {
		this.baseDefense = baseDefense;
	}

	public BigDecimal getCurrentHealthPoints() {
		return currentHealthPoints;
	}

	public void setCurrentHealthPoints(BigDecimal currentHealthPoints) {
		this.currentHealthPoints = currentHealthPoints;
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

}
