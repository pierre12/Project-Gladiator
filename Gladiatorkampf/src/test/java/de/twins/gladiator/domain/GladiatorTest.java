package de.twins.gladiator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class GladiatorTest {

	Gladiator testee;
	Gladiator enemy;

	@Before
	public void setup() {
		testee = new Gladiator("testee", new BigDecimal(100), new BigDecimal(10), new BigDecimal(10));
		enemy = new Gladiator("enemy", new BigDecimal(100), new BigDecimal(10), new BigDecimal(10));
	}

	@Test
	public void shouldSetHpToZeroWhenHpIsNegative() {
		testee.setHp(new BigDecimal(-1));
		assertThat(testee.getHp(), is(BigDecimal.ZERO));
	}

	@Test
	public void shouldNullifyAttackWhenDefenseIsEqualsOrHigher() {
		testee.defend(enemy.getAttack());
		assertThat(testee.getHp(), is(new BigDecimal(100)));
	}
	@Test
	public void shouldLoseHpFromAttack() {
		enemy.setAttack(new BigDecimal(20));
		testee.defend(enemy.getAttack());
		assertThat(testee.getHp(), is(new BigDecimal(90)));
	}
	@Test
	public void shouldSetName() {
		String name = "gladiator";
		testee.setName(name);
		assertThat(testee.getName(), is(name));
	}
	@Test
	public void shouldSetDefense() {
		BigDecimal defense = new BigDecimal(3.33);
		testee.setDefense(defense);
		assertThat(testee.getDefense(), is(defense));
	}
}
