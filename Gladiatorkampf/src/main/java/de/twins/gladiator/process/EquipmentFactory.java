package de.twins.gladiator.process;

import java.util.Map;

import de.twins.gladiator.domain.Equipment;
import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Equipment.Rarity;

public interface EquipmentFactory {


	Equipment createRandomEquipment();

	Equipment createRandomEquipmentFor(BodyPart bodypart);

	Equipment createRandomEquipmentWithRarityGrade(BodyPart bodypart,Rarity rarity);

	Map<BodyPart, Equipment> randomFullSet();

}
