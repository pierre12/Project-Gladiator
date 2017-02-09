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
	public void setup(){
	testee = new Gladiator("testee",new BigDecimal(100),new BigDecimal(10),new BigDecimal(10));
	enemy = new Gladiator("enemy",new BigDecimal(100),new BigDecimal(10),new BigDecimal(10));
	}
	@Test
	public void shouldSetHpToZeroWhenHpIsNegative(){
		testee.setHp(new BigDecimal(-1));
		assertThat(testee.getHp(), is(BigDecimal.ZERO));
	}
}
