package de.twins.arena;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.twins.arena.domain.ArenaResult;
import de.twins.arena.domain.FightRecord;
import de.twins.arena.process.OneOnOneArena;
import de.twins.enemy.Minion;
import de.twins.gladiator.domain.Fightable;

public class OneOnOneArenaTest {

	Fightable fighter1;
	Fightable fighter2;

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
		Map<Fightable, List<FightRecord>> fightRecords = record.getRecords();
		// Je ein List<FightRecord> für jeden Kämpfer
		Assert.assertEquals(2, fightRecords.size());
		Collection<List<FightRecord>> values = fightRecords.values();
		Iterator<List<FightRecord>> iterator = values.iterator();
		List<FightRecord> next = iterator.next();
		// 10 Runden bedeutet 10 Records müssen angelegt sein
		Assert.assertEquals(rounds, next.size());
		List<FightRecord> next2 = iterator.next();
		Assert.assertEquals(rounds, next2.size());
	}
}
