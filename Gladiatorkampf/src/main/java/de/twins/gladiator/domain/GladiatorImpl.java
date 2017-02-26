package de.twins.gladiator.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.twins.gladiator.domain.Equipment.BodyPart;

//@Entity
public class GladiatorImpl extends AbstractFighter implements Gladiator {


	private Map<BodyPart, Equipment> equipments;

	public GladiatorImpl(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense,
			Map<BodyPart, Equipment> equipments) {
		super(name, healthPoints, attack, defense);
		checkIfNullOrNegativeValue(attack);
		checkIfNullOrNegativeValue(defense);
		checkIfNullOrNegativeValue(healthPoints);
		totalHealthPoints = BigDecimal.ZERO;
		totalAttack = BigDecimal.ZERO;
		totalDefense = BigDecimal.ZERO;
		this.currentHealthPoints = BigDecimal.ZERO;
		if (equipments != null) {
			this.equipments = equipments;
		} else {
			this.equipments = new HashMap<>();
		}
		updateStats();
		currentHealthPoints = totalHealthPoints;
	}


	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#addOrReplaceEquipment(de.twins.gladiator.domain.Equipment)
	 */
	@Override
	public void addOrReplaceEquipment(Equipment equipment) {
		equipments.put(equipment.getBodyPart(), equipment);
		updateStats();
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
	 * @see de.twins.gladiator.domain.Gladiator#defend(java.math.BigDecimal)
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

	private double getDMGResistant() {
		double doubleValue = this.getTotalDefense().doubleValue();
		return Math.log(doubleValue) / 10;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#getBaseAttack()
	 */
	@Override
	public BigDecimal getBaseAttack() {
		return baseAttack;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#getBaseDefense()
	 */
	@Override
	public BigDecimal getBaseDefense() {
		return baseDefense;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#getBaseHealthPoints()
	 */
	@Override
	public BigDecimal getBaseHealthPoints() {
		return baseHealthPoints;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#getCurrentHealthPoints()
	 */
	@Override
	public BigDecimal getCurrentHealthPoints() {
		return currentHealthPoints;
	}


	/**
	 * @see de.twins.gladiator.domain.Gladiator#getEquipments()
	 */
	@Override
	public Map<BodyPart, Equipment> getEquipments() {
		return equipments;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#getTotalAttack()
	 */
	@Override
	public BigDecimal getTotalAttack() {
		return totalAttack;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#getTotalDefense()
	 */
	@Override
	public BigDecimal getTotalDefense() {
		return totalDefense;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#getTotalHealthPoints()
	 */
	@Override
	public BigDecimal getTotalHealthPoints() {
		return totalHealthPoints;
	}

	/**
	 * @see de.twins.gladiator.domain.AbstractFighter#isAlive()
	 */
	@Override
	public boolean isAlive() {
		return currentHealthPoints.compareTo(BigDecimal.ZERO) > 0;
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#removeEquipment(de.twins.gladiator.domain.Equipment)
	 */
	@Override
	public void removeEquipment(Equipment equipment) {
		equipments.remove(equipment.getBodyPart());
		updateStats();
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#setBaseAttack(java.math.BigDecimal)
	 */
	@Override
	public void setBaseAttack(BigDecimal baseAttack) {
		checkIfNullOrNegativeValue(baseAttack);
		this.baseAttack = baseAttack;
		updateStats();
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#setBaseDefense(java.math.BigDecimal)
	 */
	@Override
	public void setBaseDefense(BigDecimal baseDefense) {
		checkIfNullOrNegativeValue(baseDefense);
		this.baseDefense = baseDefense;
		updateStats();
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#setBaseHealthPoints(java.math.BigDecimal)
	 */
	@Override
	public void setBaseHealthPoints(BigDecimal healthPoints) {
		checkIfNullOrNegativeValue(healthPoints);
		this.baseHealthPoints = healthPoints;
		updateStats();
	}

	/**
	 * @see de.twins.gladiator.domain.Gladiator#setCurrentHealthPoints(java.math.BigDecimal)
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
	 * @see de.twins.gladiator.domain.Gladiator#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}


	protected void updateStats() {
		totalHealthPoints = baseHealthPoints != null ? baseHealthPoints : totalHealthPoints;
		totalAttack = baseAttack != null ? baseAttack : totalAttack;
		totalDefense = baseDefense != null ? baseDefense : totalDefense;
		equipments.forEach((k, v) -> {
			totalHealthPoints = totalHealthPoints.add(v.getHealthPoints());
			totalAttack = totalAttack.add(v.getAttack());
			totalDefense = totalDefense.add(v.getDefense());
		});
		// if current Health is bigger after calculated total HealthPoints.
		if (currentHealthPoints.compareTo(totalHealthPoints) >= 0) {
			currentHealthPoints = totalHealthPoints;
		}
	}
	
	@Override
	public void addOrReplaceEquipments(Collection<Equipment> equipments) {
		for (Equipment equipment : equipments) {
			this.addOrReplaceEquipment(equipment);
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append("CurrentHP:");
		sb.append(this.getCurrentHealthPoints());
		sb.append(",");
		sb.append("Attack:");
		sb.append(this.getTotalAttack());
		sb.append(",");
		sb.append("Defense:");
		sb.append(this.getTotalDefense());
		sb.append(" ]");
		return sb.toString();
	}
}
