package de.twins.gladiator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Equipment.Rarity;

public class EquipmentTest {
	private Equipment testee;

	@Before
	public void setup() {
		testee = new Equipment(BodyPart.BODY, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,Rarity.EPIC);
	}

	@Test
	public void shouldSetStats() {

		assertThat(testee.getAttack(), is(BigDecimal.ONE));
		assertThat(testee.getDefense(), is(BigDecimal.ONE));
		assertThat(testee.getHealthPoints(), is(BigDecimal.ONE));
		assertThat(testee.getBodyPart(), is(BodyPart.BODY));
		assertThat(testee.getRarity(), is(Rarity.EPIC));
	}

	@Test
	public void shouldPrintRelevantData(){
		assertThat(testee.toString(), is("Attack:1\nDefense:1\nHp:1\nBodypart:BODY\nRarity:EPIC\n"));
	}
}
