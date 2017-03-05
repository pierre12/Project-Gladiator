package de.twins.gladiator.process;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import de.twins.enemy.domain.Minion;

public class MinionFactoryImplTest {

	MinionFactoryImpl testee;

	@Test
	public void testCreateMinion() throws Exception {
		BigDecimal expected = new BigDecimal(10);
		testee = new MinionFactoryImpl();
		Minion createMinion = testee.createMinion();
		assertThat(createMinion.getName(), is("Minion"));
		assertThat(createMinion.getTotalAttack(), is(expected));
		assertThat(createMinion.getTotalDefense(), is(expected));
		assertThat(createMinion.getTotalHealthPoints(), is(expected));
	}

}
