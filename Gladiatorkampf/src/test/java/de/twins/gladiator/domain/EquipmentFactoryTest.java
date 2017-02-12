package de.twins.gladiator.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Equipment.Rarity;
import de.twins.gladiator.process.EquipmentFactory;
import de.twins.gladiator.process.SimpleEquipFactoryImpl;

public class EquipmentFactoryTest {

	EquipmentFactory factory;

	@Before
	public void init() {
		factory = new SimpleEquipFactoryImpl();
	}

	@Test
	public void createRandomEquipment() {
		Equipment createRandomEquipment = factory.createRandomEquipment();
		Equipment createRandomEquipment2 = factory.createRandomEquipment();
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertNotNull(createRandomEquipment2);
		Assert.assertNotSame(createRandomEquipment, createRandomEquipment2);

		boolean notSame = false;
		notSame |= createRandomEquipment.getAttack() != createRandomEquipment2.getAttack();
		notSame |= createRandomEquipment.getDefense() != createRandomEquipment2.getDefense();
		notSame |= createRandomEquipment.getHealthPoints() != createRandomEquipment2.getHealthPoints();
		notSame |= createRandomEquipment.getRarity() != createRandomEquipment2.getRarity();
		notSame |= createRandomEquipment.getBodyPart() != createRandomEquipment2.getBodyPart();

		Assert.assertTrue(notSame);
	}

	@Test
	public void createRandomLegendaryEquipmentForHead() {
		Equipment createRandomEquipment = factory.createRandomLegendaryEquipmentFor(BodyPart.HEAD);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.HEAD, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.LEGENDARY, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomLegendaryEquipmentForArms() {
		Equipment createRandomEquipment = factory.createRandomLegendaryEquipmentFor(BodyPart.ARMS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.ARMS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.LEGENDARY, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomLegendaryEquipmentForBody() {
		Equipment createRandomEquipment = factory.createRandomLegendaryEquipmentFor(BodyPart.BODY);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.BODY, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.LEGENDARY, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomLegendaryEquipmentForLegs() {
		Equipment createRandomEquipment = factory.createRandomLegendaryEquipmentFor(BodyPart.LEGS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.LEGS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.LEGENDARY, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomEpicEquipmentForHead() {
		Equipment createRandomEquipment = factory.createRandomEpicEquipmentFor(BodyPart.HEAD);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.HEAD, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.EPIC, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomEpicEquipmentForArms() {
		Equipment createRandomEquipment = factory.createRandomEpicEquipmentFor(BodyPart.ARMS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.ARMS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.EPIC, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomEpicEquipmentForBody() {
		Equipment createRandomEquipment = factory.createRandomEpicEquipmentFor(BodyPart.BODY);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.BODY, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.EPIC, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomEpicEquipmentForLegs() {
		Equipment createRandomEquipment = factory.createRandomEpicEquipmentFor(BodyPart.LEGS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.LEGS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.EPIC, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomMagicEquipmentForHead() {
		Equipment createRandomEquipment = factory.createRandomMagicEquipmentFor(BodyPart.HEAD);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.HEAD, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.MAGIC, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomMagicEquipmentForArms() {
		Equipment createRandomEquipment = factory.createRandomMagicEquipmentFor(BodyPart.ARMS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.ARMS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.MAGIC, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomMagicEquipmentForBody() {
		Equipment createRandomEquipment = factory.createRandomMagicEquipmentFor(BodyPart.BODY);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.BODY, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.MAGIC, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomMagicEquipmentForLegs() {
		Equipment createRandomEquipment = factory.createRandomMagicEquipmentFor(BodyPart.LEGS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.LEGS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.MAGIC, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomUncommonEquipmentForHead() {
		Equipment createRandomEquipment = factory.createRandomUncommonEquipmentFor(BodyPart.HEAD);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.HEAD, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.UNCOMMON, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomUncommonEquipmentForArms() {
		Equipment createRandomEquipment = factory.createRandomUncommonEquipmentFor(BodyPart.ARMS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.ARMS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.UNCOMMON, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomUncommonEquipmentForBody() {
		Equipment createRandomEquipment = factory.createRandomUncommonEquipmentFor(BodyPart.BODY);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.BODY, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.UNCOMMON, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomUncommonEquipmentForLegs() {
		Equipment createRandomEquipment = factory.createRandomUncommonEquipmentFor(BodyPart.LEGS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.LEGS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.UNCOMMON, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomCommonEquipmentForHead() {
		Equipment createRandomEquipment = factory.createRandomCommonEquipmentFor(BodyPart.HEAD);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.HEAD, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.COMMON, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomCommonEquipmentForArms() {
		Equipment createRandomEquipment = factory.createRandomCommonEquipmentFor(BodyPart.ARMS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.ARMS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.COMMON, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomCommonEquipmentForBody() {
		Equipment createRandomEquipment = factory.createRandomCommonEquipmentFor(BodyPart.BODY);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.BODY, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.COMMON, createRandomEquipment.getRarity());
	}

	@Test
	public void createRandomCommonEquipmentForLegs() {
		Equipment createRandomEquipment = factory.createRandomCommonEquipmentFor(BodyPart.LEGS);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.LEGS, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.COMMON, createRandomEquipment.getRarity());
	}

}
