package de.twins.arena;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.twins.arena.domain.ArenaResult;
import de.twins.arena.domain.FightRecord;
import de.twins.arena.process.OneOnOneArena;
import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.AbstractFighter;

public class OneOnOneArenaTest {

	AbstractFighter fighter1;
	AbstractFighter fighter2;

	OneOnOneArena arena;

	@Before
	public void setUp() {
		arena = new OneOnOneArena();
		fighter1 = new Minion("Fighter1", new BigDecimal(11), new BigDecimal(100), new BigDecimal(100));
		fighter2 = new Minion("Fighter2", new BigDecimal(11), new BigDecimal(10), new BigDecimal(10));
	}

	@Test(timeout = 5000)
	public void drawFightsEnd() {
		// timeout um einen endlosen kampf zu entgehen
		int rounds = 100;
		arena.setRounds(rounds);
		arena.addFighter(fighter1);
		arena.addFighter(fighter2);
		arena.startFight();
		ArenaResult record = arena.getResult();
		List<FightRecord> fightRecords = record.getRecords();
		Assert.assertEquals(2 * 100, fightRecords.size());
	}
}
