package de.twins.gladiator.domain;

import java.util.Collection;
import java.util.Map;

import de.twins.gladiator.domain.Equipment.BodyPart;
/**
 * Equipable interface which is used to equip Equipments for fighter
 * @author Pierre
 *
 */
public interface Equipable  {

	/**
	 * Adds equipment to the fighter.If equipment position already is
	 * allocated it will be replaced with the given equipment
	 * 
	 * @param equipment
	 *            equipment which should be equipped.
	 */
	void addOrReplaceEquipment(Equipment equipment);
	
	void addOrReplaceEquipments(Collection<Equipment> equipment);


	/**
	 * Return the equipments which is currently allocated to the fighter.
	 * 
	 * @return the equipments which is currently allocated to the fighter.
	 */
	Map<BodyPart, Equipment> getEquipments();


	/**
	 * removes equipment
	 * 
	 * @param equipment
	 *            equipment which should be removed
	 */
	void removeEquipment(Equipment equipment);


}