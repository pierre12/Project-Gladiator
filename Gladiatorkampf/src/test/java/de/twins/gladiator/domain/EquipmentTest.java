package de.twins.gladiator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class EquipmentTest {
	private Equipment testee;

	@Before
	public void setup(){
		testee = new Equipment();
	}
	@Test
	public void shouldSetStats(){
		testee.setAttack(BigDecimal.ONE);
		testee.setDefense(BigDecimal.ONE);
		testee.setHp(BigDecimal.ONE);
		testee.setBodyPart(BodyPart.BODY);
		
		assertThat(testee.getAttack(), is(BigDecimal.ONE));
		assertThat(testee.getDefense(), is(BigDecimal.ONE));
		assertThat(testee.getHealthPoints(), is(BigDecimal.ONE));
		assertThat(testee.getBodyPart(), is(BodyPart.BODY));
	}
}
