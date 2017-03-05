package de.twins.gladiator.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import de.twins.gladiator.domain.Equipment.BodyPart;

@Entity
public class Gladiator extends AbstractFighter implements Equipable {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@MapKey(name = "bodyPart")
	private Map<BodyPart, Equipment> equipments = new  HashMap<>();

	/**
	 * Default-Konstruktor
	 */
	public Gladiator() {

	}
	public Gladiator(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense,
			Map<BodyPart, Equipment> equipments) {
		super(name, healthPoints, attack, defense);
		if (equipments != null) {
			this.equipments = equipments;
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


	@Override
	public void addOrReplaceEquipments(Collection<Equipment> equipments) {
		for (Equipment equipment : equipments) {
			this.addOrReplaceEquipment(equipment);
		}

	}


	/**
	 * @see de.twins.gladiator.domain.Equipable#getEquipments()
	 */
	@Override
	public Map<BodyPart, Equipment> getEquipments() {
		return equipments;
	}

	

	/**
	 * @see de.twins.gladiator.domain.Equipable#removeEquipment(de.twins.gladiator.domain.Equipment)
	 */
	@Override
	public void removeEquipment(Equipment equipment) {
		equipments.remove(equipment.getBodyPart());
		updateStats();
	}
	
	@Override
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
