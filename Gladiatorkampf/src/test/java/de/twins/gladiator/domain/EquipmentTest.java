package de.twins.gladiator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import de.twins.gladiator.domain.Equipment.BodyPart;

public class EquipmentTest {
	private Equipment testee;

	@Test
	public void shouldSetStats() {
		testee = new Equipment(BodyPart.BODY,BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE);

		assertThat(testee.getAttack(), is(BigDecimal.ONE));
		assertThat(testee.getDefense(), is(BigDecimal.ONE));
		assertThat(testee.getHealthPoints(), is(BigDecimal.ONE));
		assertThat(testee.getBodyPart(), is(BodyPart.BODY));
	}
}
