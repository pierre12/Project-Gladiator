package de.twins.gladiator.process;

import de.twins.gladiator.domain.Equipment;
import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Equipment.Rarity;

import java.util.Map;

public interface EquipmentFactory {


    Equipment createRandomEquipment();

    Equipment createRandomEquipmentFor(BodyPart bodypart);

    Equipment createRandomEquipmentWithRarityGrade(BodyPart bodypart, Rarity rarity);

    Map<BodyPart, Equipment> randomFullSet();

}
