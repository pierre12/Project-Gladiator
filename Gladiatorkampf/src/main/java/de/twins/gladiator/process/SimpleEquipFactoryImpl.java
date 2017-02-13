package de.twins.gladiator.process;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import de.twins.gladiator.domain.Equipment;
import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Equipment.Rarity;

@Component
public class SimpleEquipFactoryImpl implements EquipmentFactory {

	@Override
	public Equipment createRandomEquipment(){
		BodyPart rdBodyPart = getRandomBodyPart();
		return createRandomEquipmentFor(rdBodyPart);
	}
	
	@Override
	public Equipment createRandomEquipmentFor(BodyPart bodypart) {
		Rarity rdRarity = getRandomRarity();
		switch (rdRarity) {
		case COMMON:
			return createRandomCommonEquipmentFor(bodypart);
		case UNCOMMON:
			return createRandomUncommonEquipmentFor(bodypart);
		case EPIC:
			return createRandomEpicEquipmentFor(bodypart);
		case LEGENDARY:
			return createRandomLegendaryEquipmentFor(bodypart);
		case MAGIC:
			return createRandomMagicEquipmentFor(bodypart);
		default:
			return new Equipment(bodypart, new BigDecimal("1"), new BigDecimal("1"), new BigDecimal("1"));
		}
	}

	private BodyPart getRandomBodyPart() {
		BodyPart[] values = BodyPart.values();
		return values[(int) (Math.random() * values.length)];
	}

	private Rarity getRandomRarity() {
		Rarity[] values = Rarity.values();
		return values[(int) (Math.random() * values.length)];
	}

	@Override
	public Equipment createRandomLegendaryEquipmentFor(BodyPart bodypart){
		BigDecimal rdHp = randomHp(Rarity.LEGENDARY);
		BigDecimal rdAttack = randomDefense(Rarity.LEGENDARY);
		BigDecimal rdDefense = randomAttack(Rarity.LEGENDARY);
		Equipment equip = new Equipment(bodypart, rdHp, rdAttack, rdDefense, Rarity.LEGENDARY);
		return equip;
	}


	@Override
	public Equipment createRandomCommonEquipmentFor(BodyPart bodypart) {
		BigDecimal rdHp = randomHp(Rarity.COMMON);
		BigDecimal rdAttack = randomDefense(Rarity.COMMON);
		BigDecimal rdDefense = randomAttack(Rarity.COMMON);
		Equipment equip = new Equipment(bodypart, rdHp, rdAttack, rdDefense, Rarity.COMMON);
		return equip;
	}

	@Override
	public Equipment createRandomEpicEquipmentFor(BodyPart bodypart) {
		BigDecimal rdHp = randomHp(Rarity.EPIC);
		BigDecimal rdAttack = randomDefense(Rarity.EPIC);
		BigDecimal rdDefense = randomAttack(Rarity.EPIC);
		Equipment equip = new Equipment(bodypart, rdHp, rdAttack, rdDefense, Rarity.EPIC);
		return equip;
	}

	@Override
	public Equipment createRandomMagicEquipmentFor(BodyPart bodypart) {
		BigDecimal rdHp = randomHp(Rarity.MAGIC);
		BigDecimal rdAttack = randomDefense(Rarity.MAGIC);
		BigDecimal rdDefense = randomAttack(Rarity.MAGIC);
		Equipment equip = new Equipment(bodypart, rdHp, rdAttack, rdDefense, Rarity.MAGIC);
		return equip;
	}

	@Override
	public Equipment createRandomUncommonEquipmentFor(BodyPart bodypart) {
		BigDecimal rdHp = randomHp(Rarity.UNCOMMON);
		BigDecimal rdAttack = randomDefense(Rarity.UNCOMMON);
		BigDecimal rdDefense = randomAttack(Rarity.UNCOMMON);
		Equipment equip = new Equipment(bodypart, rdHp, rdAttack, rdDefense, Rarity.UNCOMMON);
		return equip;
	}

	private BigDecimal randomHp(Rarity rarity) {
		int maxHp = 0;
		if (Rarity.LEGENDARY.equals(rarity)) {
			maxHp = 500;
		}
		if (Rarity.EPIC.equals(rarity)) {
			maxHp = 200;
		}
		if (Rarity.MAGIC.equals(rarity)) {
			maxHp = 100;
		}
		if (Rarity.UNCOMMON.equals(rarity)) {
			maxHp = 50;
		}
		if (Rarity.COMMON.equals(rarity)) {
			maxHp = 20;
		}
		double hp = Math.random() * maxHp + 1;
		return new BigDecimal(String.valueOf(hp));
	}

	private BigDecimal randomDefense(Rarity rarity) {
		int maxHp = 0;
		if (Rarity.LEGENDARY.equals(rarity)) {
			maxHp = 500;
		}
		if (Rarity.EPIC.equals(rarity)) {
			maxHp = 200;
		}
		if (Rarity.MAGIC.equals(rarity)) {
			maxHp = 100;
		}
		if (Rarity.UNCOMMON.equals(rarity)) {
			maxHp = 50;
		}
		if (Rarity.COMMON.equals(rarity)) {
			maxHp = 20;
		}
		double hp = Math.random() * maxHp + 1;
		return new BigDecimal(String.valueOf(hp));
	}

	private BigDecimal randomAttack(Rarity rarity) {
		int maxHp = 0;
		if (Rarity.LEGENDARY.equals(rarity)) {
			maxHp = 500;
		}
		if (Rarity.EPIC.equals(rarity)) {
			maxHp = 200;
		}
		if (Rarity.MAGIC.equals(rarity)) {
			maxHp = 100;
		}
		if (Rarity.UNCOMMON.equals(rarity)) {
			maxHp = 50;
		}
		if (Rarity.COMMON.equals(rarity)) {
			maxHp = 20;
		}
		double hp = Math.random() * maxHp + 1;
		return new BigDecimal(String.valueOf(hp));
	}

	public Map<BodyPart, Equipment> randomFullSet() {
		Map<BodyPart, Equipment> equipmentSet = new HashMap<>();
		for (BodyPart bodyPart : BodyPart.values()) {
			Equipment randomPart = createRandomEquipmentFor(bodyPart);
			equipmentSet.put(bodyPart, randomPart);
		}
		return equipmentSet;
	}
}
