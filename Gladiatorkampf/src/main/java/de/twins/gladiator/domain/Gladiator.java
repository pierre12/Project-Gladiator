package de.twins.gladiator.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import de.twins.gladiator.domain.Equipment.BodyPart;
/**
 * Gladiator interface which is used to create a diversity of gladiators,
 * Gladiators are designed to fight against each other in arenas.
 * @author Pierre
 *
 */
public interface Gladiator extends Fightable {

	/**
	 * Adds equipment to the gladiator.If equipment position already is
	 * allocated it will be replaced with the given equipment
	 * 
	 * @param equipment
	 *            equipment which should be equipped.
	 */
	void addOrReplaceEquipment(Equipment equipment);
	
	void addOrReplaceEquipments(Collection<Equipment> equipment);


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
	 * Return the equipments which is currently allocated to the gladiator.
	 * 
	 * @return the equipments which is currently allocated to the gladiator.
	 */
	Map<BodyPart, Equipment> getEquipments();


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
	 * Sets the name of the gladiator
	 * @param name name which should be set.
	 */
	void setName(String name);

}