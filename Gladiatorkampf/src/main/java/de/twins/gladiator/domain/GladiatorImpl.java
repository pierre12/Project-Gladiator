package de.twins.gladiator.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GladiatorImpl implements Gladiator {

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

	public GladiatorImpl(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense,
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

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#defend(java.math.BigDecimal)
	 */
	@Override
	public void defend(BigDecimal attack) {
		BigDecimal dmg = attack.subtract(totalDefense);
		if (dmg.compareTo(BigDecimal.ZERO) > 0) {
			setCurrentHealthPoints(getCurrentHealthPoints().subtract(dmg));
		}
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getBaseAttack()
	 */
	@Override
	public BigDecimal getBaseAttack() {
		return baseAttack;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getBaseDefense()
	 */
	@Override
	public BigDecimal getBaseDefense() {
		return baseDefense;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getBaseHealthPoints()
	 */
	@Override
	public BigDecimal getBaseHealthPoints() {
		return baseHealthPoints;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getCurrentHealthPoints()
	 */
	@Override
	public BigDecimal getCurrentHealthPoints() {
		return currentHealthPoints;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getEquipments()
	 */
	@Override
	public Map<BodyPart, Equipment> getEquipments() {
		return equipments;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getTotalAttack()
	 */
	@Override
	public BigDecimal getTotalAttack() {
		return totalAttack;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getTotalDefense()
	 */
	@Override
	public BigDecimal getTotalDefense() {
		return totalDefense;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#getTotalHealthPoints()
	 */
	@Override
	public BigDecimal getTotalHealthPoints() {
		return totalHealthPoints;
	}

	@Override
	public boolean isAlive() {
		return currentHealthPoints.compareTo(BigDecimal.ZERO) > 0;
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#removeEquipment(de.twins.gladiator.domain.Equipment)
	 */
	@Override
	public void removeEquipment(Equipment equipment) {
		equipments.remove(equipment.getBodyPart());
		updateStats();
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#setBaseAttack(java.math.BigDecimal)
	 */
	@Override
	public void setBaseAttack(BigDecimal baseAttack) {
		checkIfNullOrNegativeValue(baseAttack);
		this.baseAttack = baseAttack;
		updateStats();
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#setBaseDefense(java.math.BigDecimal)
	 */
	@Override
	public void setBaseDefense(BigDecimal baseDefense) {
		checkIfNullOrNegativeValue(baseDefense);
		this.baseDefense = baseDefense;
		updateStats();
	}

	/* (non-Javadoc)
	 * @see de.twins.gladiator.domain.Gladiator#setBaseHealthPoints(java.math.BigDecimal)
	 */
	@Override
	public void setBaseHealthPoints(BigDecimal healthPoints) {
		checkIfNullOrNegativeValue(healthPoints);
		this.baseHealthPoints = healthPoints;
		updateStats();
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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
	
}
