package de.twins.arena.process;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import de.twins.arena.domain.ArenaResult;
import de.twins.arena.domain.ArenaResult.Result;
import de.twins.arena.exception.ArenaException;
import de.twins.arena.persistence.ArenaResultPersistence;
import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.AbstractFighter;

public class OneOnOneArenaTest {
	AbstractFighter fighter1;
	AbstractFighter fighter2;
	AbstractFighter fighter3;
	@InjectMocks
	@Spy
	OneOnOneArena testee;
	@Mock
	ArenaResultPersistence persistence;
	@Mock
	ArenaResult arenaResultMock;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fighter1 = new Minion("Fighter1", new BigDecimal(11), new BigDecimal(100), new BigDecimal(100));
		fighter2 = new Minion("Fighter2", new BigDecimal(11), new BigDecimal(10), new BigDecimal(10));
		fighter3 = new Minion("Fighter3", new BigDecimal(11), new BigDecimal(10), new BigDecimal(10));
		testee.addFighter(fighter1);
		testee.addFighter(fighter2);
	}

	@Test
	public void shouldEndFight() {
		testee.endFight();
		verify(testee).announceWinner();
		verify(persistence).save((ArenaResult) anyObject());
	}

	@Test
	public void shouldThrowExceptionIfMoreThanTwoFightersAreAdded() {
		expectedException.expect(ArenaException.class);
		expectedException.expectMessage("Arena does only allow two fighters but it was tried to increase the number of fighters to 3");
		testee.addFighter(fighter3);
	}
	@Test
	public void shouldDoNothingIfAddedGladiatorIsAlreadyInArena() {
		testee.addFighter(fighter2);
	}
	@Test
	public void shouldDoNothingIfAddedGladiatorsAreAlreadyInArena() {
		testee.addFighters(Arrays.asList(fighter1,fighter2));
	}
	@Test
	public void shouldThrowExceptionIfAddedGladiatorsExceedTheLimitOfTwo() {
		expectedException.expect(ArenaException.class);
		expectedException.expectMessage("Arena does only allow two fighters but it was tried to increase the number of fighters to 3");
		testee.addFighters(Arrays.asList(fighter1,fighter2,fighter3));
	}
	@Test
	public void shouldThrowExceptionIfFighterIsNull() {
		expectedException.expect(ArenaException.class);
		expectedException.expectMessage("Fighter must not be null");
		testee.addFighter(null);
	}
	 @Test
	 public void shouldAnnounceWinner() {
	 when(arenaResultMock.getNumberOfRoundsWithResult(fighter1,Result.WIN)).thenReturn(1);
	 testee.announceWinner();
	
	 }
}
