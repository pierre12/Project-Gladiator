package de.twins.gladiator.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Gladiator {

	// base stats from character
	private String name;
	private BigDecimal baseHealthPoints;
	private BigDecimal baseAttack;
	private BigDecimal baseDefense;

	// stats combined from all sources
	private BigDecimal totalHealthPoints;
	private BigDecimal currentHealthPoints;
	private BigDecimal totalAttack;
	private BigDecimal totalDefense;

	private Map<BodyPart, Equipment> equipments;

	public Gladiator(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense,
			Map<BodyPart, Equipment> equipments) {
		checkIfNullOrNegativeValue(attack);
		checkIfNullOrNegativeValue(defense);
		checkIfNullOrNegativeValue(healthPoints);
		this.name = name;
		totalHealthPoints = BigDecimal.ZERO;
		totalAttack = BigDecimal.ZERO;
		totalDefense = BigDecimal.ZERO;
		this.currentHealthPoints = BigDecimal.ZERO;
		this.baseAttack = attack;
		this.baseDefense = defense;
		this.baseHealthPoints = healthPoints;

		if (equipments != null) {
			this.equipments = equipments;
		} else {
			this.equipments = new HashMap<>();
		}
		updateStats();
		currentHealthPoints = totalHealthPoints;
	}


	public void addOrReplaceEquipment(Equipment equipment) {
		equipments.put(equipment.getBodyPart(), equipment);
		updateStats();
	}

	protected void checkIfNullOrNegativeValue(BigDecimal value) {
		if (value == null) {
			throw new RuntimeException("value must not be null");
		}
		if (value.signum() == -1) {
			throw new RuntimeException("value must not be negative");
		}

	}

	/**
	 * Reduces enemy attack power by own defense value. The reduced attack power
	 * substracts directly the hp.
	 * 
	 * @param attack
	 *            The attack power which reduces the hp.
	 */
	public void defend(BigDecimal attack) {
		BigDecimal dmg = attack.subtract(totalDefense);
		if (dmg.compareTo(BigDecimal.ZERO) > 0) {
			setCurrentHealthPoints(getCurrentHealthPoints().subtract(dmg));
		}
	}

	public BigDecimal getBaseAttack() {
		return baseAttack;
	}

	public BigDecimal getBaseDefense() {
		return baseDefense;
	}

	public BigDecimal getBaseHealthPoints() {
		return baseHealthPoints;
	}

	public BigDecimal getCurrentHealthPoints() {
		return currentHealthPoints;
	}

	public Map<BodyPart, Equipment> getEquipments() {
		return equipments;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getTotalAttack() {
		return totalAttack;
	}

	public BigDecimal getTotalDefense() {
		return totalDefense;
	}

	public BigDecimal getTotalHealthPoints() {
		return totalHealthPoints;
	}

	public void removeEquipment(Equipment equipment) {
		equipments.remove(equipment.getBodyPart());
		updateStats();
	}

	public void setBaseAttack(BigDecimal baseAttack) {
		checkIfNullOrNegativeValue(baseAttack);
		this.baseAttack = baseAttack;
		updateStats();
	}

	public void setBaseDefense(BigDecimal baseDefense) {
		checkIfNullOrNegativeValue(baseDefense);
		this.baseDefense = baseDefense;
		updateStats();
	}

	public void setBaseHealthPoints(BigDecimal healthPoints) {
		checkIfNullOrNegativeValue(healthPoints);
		this.baseHealthPoints = healthPoints;
		updateStats();
	}

	public void setCurrentHealthPoints(BigDecimal hp) {
		if (hp.compareTo(BigDecimal.ZERO) <= 0) {
			this.currentHealthPoints = BigDecimal.ZERO;
		} else {
			this.currentHealthPoints = hp;
		}
	}

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
	
}
