package de.twins.gladiator.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

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
	 * Default-Konstruktor
	 */
	public AbstractFighter() {
	}

	/**
	 * 
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

	/**
	 * @see de.twins.gladiator.domain.Equipable#defend(java.math.BigDecimal)
	 */
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

	/**
	 * @see de.twins.gladiator.domain.Equipable#setBaseAttack(java.math.BigDecimal)
	 */
	@Override
	public void setBaseAttack(BigDecimal baseAttack) {
		checkIfNullOrNegativeValue(baseAttack);
		this.baseAttack = baseAttack;
		updateStats();
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
}
