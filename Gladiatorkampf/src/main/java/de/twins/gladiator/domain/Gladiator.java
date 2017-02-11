package de.twins.gladiator.domain;

import java.math.BigDecimal;
import java.util.Map;
/**
 * Gladiator interface which is used to create a diversity of gladiators,
 * Gladiators are designed to fight against each other in arenas.
 * @author Pierre
 *
 */
public interface Gladiator {

	/**
	 * Adds equipment to the gladiator.If equipment position already is
	 * allocated it will be replaced with the given equipment
	 * 
	 * @param equipment
	 *            equipment which should be equipped.
	 */
	void addOrReplaceEquipment(Equipment equipment);

	/**
	 * Reduces enemy attack power by own defense value. The reduced attack power
	 * substracts directly the hp.
	 * 
	 * @param attack
	 *            The attack power which reduces the hp.
	 */
	void defend(BigDecimal attack);

	/**
	 * Returns the base attack which is originated from the gladiator.
	 * 
	 * @return the base attack which is originated from the gladiator.
	 */
	BigDecimal getBaseAttack();

	/**
	 * Returns the base defense which is originated from the gladiator.
	 * 
	 * @return the base defense which is originated from the gladiator.
	 */
	BigDecimal getBaseDefense();

	/**
	 * Returns the base health points which is originated from the gladiator.
	 * 
	 * @return the base health points which is originated from the gladiator.
	 */
	BigDecimal getBaseHealthPoints();

	/**
	 * Returns the current health points of the gladiator.
	 * 
	 * @return the current health points of the gladiator.
	 */
	BigDecimal getCurrentHealthPoints();

	/**
	 * Return the equipments which is currently allocated to the gladiator.
	 * 
	 * @return the equipments which is currently allocated to the gladiator.
	 */
	Map<BodyPart, Equipment> getEquipments();

	/**
	 * Returns the name of the gladiator.
	 * 
	 * @return the name of the gladiator.
	 */
	String getName();

	/**
	 * Return the total attack of the gladiator.Which is the sum of the
	 * equipment and base attack.
	 * 
	 * @return the total attack of the gladiator.
	 */
	BigDecimal getTotalAttack();

	/**
	 * Return the total attack of the gladiator.Which is the sum of the
	 * equipment and base defense.
	 * 
	 * @return the total attack of the gladiator.
	 */
	BigDecimal getTotalDefense();

	/**
	 * Return the total attack of the gladiator.Which is the sum of the
	 * equipment and base health points.
	 * 
	 * @return the total attack of the gladiator.
	 */
	BigDecimal getTotalHealthPoints();

	/**
	 * removes equipment
	 * 
	 * @param equipment
	 *            equipment which should be removed
	 */
	void removeEquipment(Equipment equipment);

	/**
	 * Sets the base attack of the gladiator.
	 * @param baseAttack base attack which should be set.
	 */
	void setBaseAttack(BigDecimal baseAttack);
	/**
	 * Sets the base attack of the gladiator.
	 * @param baseAttack base attack which should be set.
	 */
	void setBaseDefense(BigDecimal baseDefense);
	/**
	 * Sets the base health points of the gladiator.
	 * @param healthPoints base health points which should be set.
	 */
	void setBaseHealthPoints(BigDecimal healthPoints);
	/**
	 * Sets the base attack of the gladiator
	 * @param healthPoints current health points which should be set.
	 */
	void setCurrentHealthPoints(BigDecimal healthPoints);
	/**
	 * Sets the name of the gladiator
	 * @param name name which should be set.
	 */
	void setName(String name);
	/**
	 * Returns true if gladiator is alive.
	 * @return true if gladiator is alive.
	 */
	boolean isAlive();

}