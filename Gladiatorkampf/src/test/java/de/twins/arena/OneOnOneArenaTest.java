package de.twins.arena;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.twins.arena.domain.ArenaResult;
import de.twins.arena.domain.FightRecord;
import de.twins.arena.persistence.ArenaResultPersistence;
import de.twins.arena.process.OneOnOneArena;
import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.AbstractFighter;

public class OneOnOneArenaTest {

	AbstractFighter fighter1;
	AbstractFighter fighter2;

	@InjectMocks
	OneOnOneArena testee = new OneOnOneArena();
	@Mock
	ArenaResultPersistence persistence;
	
	@Test(timeout = 5000)
	public void drawFightsEnd() {
		// timeout um einen endlosen kampf zu entgehen
		int rounds = 100;
		testee.setRounds(rounds);
		testee.addFighter(fighter1);
		testee.addFighter(fighter2);
		testee.startFight();
		ArenaResult record = testee.getResult();
		List<FightRecord> fightRecords = record.getRecords();
		Assert.assertEquals(2 * 100, fightRecords.size());
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fighter1 = new Minion("Fighter1", new BigDecimal(11), new BigDecimal(100), new BigDecimal(100));
		fighter2 = new Minion("Fighter2", new BigDecimal(11), new BigDecimal(10), new BigDecimal(10));
	}
	
}
