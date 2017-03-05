package de.twins.gladiator.domain;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Equipment.Rarity;
import de.twins.gladiator.process.SimpleEquipFactoryImpl;

public class SimpleEquipFactoryImplTest {
	@InjectMocks
	SimpleEquipFactoryImpl testee;

	@Mock
	Environment env;

	@Before
	public void setup() {
		testee = spy(new SimpleEquipFactoryImpl());
		MockitoAnnotations.initMocks(this);
		when(env.getProperty(anyString(),anyString())).thenAnswer(key -> String.valueOf((int)(Math.random()*100)));
	}

	@Test
	public void createRandomEquipment() {
		Equipment createRandomEquipment = testee.createRandomEquipment();
		Equipment createRandomEquipment2 = testee.createRandomEquipment();
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
	public void shouldCreateRandomEquipmentWithRarity() {
		Equipment createRandomEquipment = testee.createRandomEquipmentWithRarityGrade(BodyPart.HEAD, Rarity.LEGENDARY);
		Assert.assertNotNull(createRandomEquipment);
		Assert.assertEquals(BodyPart.HEAD, createRandomEquipment.getBodyPart());
		Assert.assertEquals(Rarity.LEGENDARY, createRandomEquipment.getRarity());
	}

	@Test
	public void randomFullSet() {
		int numberOfBodyParts = BodyPart.values().length;
		Map<BodyPart, Equipment> randomFullSet = testee.randomFullSet();

		verify(testee, times(numberOfBodyParts)).createRandomEquipmentFor(any(BodyPart.class));
		assertThat(randomFullSet.size(), is(numberOfBodyParts));
		assertThat(randomFullSet.keySet(), hasItems(BodyPart.values()));
	}

}
