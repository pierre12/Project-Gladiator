package de.twins.gladiator.process;

import java.util.Map;

import de.twins.gladiator.domain.Equipment;
import de.twins.gladiator.domain.Equipment.BodyPart;

public interface EquipmentFactory {

	Equipment createRandomLegendaryEquipmentFor(BodyPart bodypart);

	Equipment createRandomEquipment();

	Equipment createRandomUncommonEquipmentFor(BodyPart bodypart);

	Equipment createRandomMagicEquipmentFor(BodyPart bodypart);

	Equipment createRandomEpicEquipmentFor(BodyPart bodypart);

	Equipment createRandomCommonEquipmentFor(BodyPart bodypart);

	Equipment createRandomEquipmentFor(BodyPart bodypart);

	Map<BodyPart, Equipment> randomFullSet();

}
