package de.twins.gladiator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class AbstractFighterTest {

	private AbstractFighter testee;
	@Before
	public void setUp() throws Exception {
		testee = new TestFighter();
	}

	@Test
	public void shouldBeAliveWhenCurrentHealthIsGreaterZero() {
		testee.setCurrentHealthPoints(BigDecimal.ONE);
		assertThat(testee.isAlive(), is(true));
	}
	@Test
	public void shouldBeDeadWhenCurrentHealthIsGreaterZero() {
		testee.setCurrentHealthPoints(new BigDecimal(-1));
		assertThat(testee.isAlive(), is(false));
	}
	static class TestFighter extends AbstractFighter {

		@Override
		public BigDecimal defend(BigDecimal attack) {
			return null;
		}

	}
}
