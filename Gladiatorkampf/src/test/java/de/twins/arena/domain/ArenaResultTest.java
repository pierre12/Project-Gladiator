package de.twins.arena.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.twins.arena.domain.ArenaResult.Result;
import de.twins.gladiator.domain.AbstractFighter;

public class ArenaResultTest {
	private static final int FIGHTER_ID = 100;
	private ArenaResult testee;
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private FightRecord fightRecordMock;
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private FightRecord fightRecordMock2;
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private FightRecord fightRecordForDifferentFighterMock;
	@Mock
	private AbstractFighter fighterMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		testee = new ArenaResult();

		when(fighterMock.getId()).thenReturn(FIGHTER_ID);
		when(fightRecordMock.getFighter().getId()).thenReturn(FIGHTER_ID);
		when(fightRecordMock2.getFighter().getId()).thenReturn(FIGHTER_ID);
		when(fightRecordForDifferentFighterMock.getFighter().getId()).thenReturn(200);

		testee.addRecord(fightRecordMock);
		testee.addRecord(fightRecordMock2);
		testee.addRecord(fightRecordForDifferentFighterMock);

	}

	@Test
	public void shouldGetFightRecordForFighter() {

		List<FightRecord> fightRecordsForFighter = testee.getFightRecordsByFightable(fighterMock);
		assertThat(fightRecordsForFighter.size(), is(2));
		assertThat(fightRecordsForFighter.contains(fightRecordMock), is(true));
	}

	@Test
	public void shouldGetNumberOfRoundsForFighterWithSpecificResult() {
		when(fightRecordMock.getResult()).thenReturn(Result.WIN);
		when(fightRecordMock2.getResult()).thenReturn(Result.LOSE);
		int numberOfResult = testee.getNumberOfRoundsWithResult(fighterMock, Result.WIN);
		assertThat(numberOfResult, is(1));
	}

	@Test
	public void shouldResetResults() {
		testee.addRecord(fightRecordMock);
		testee.resetResults();
		assertThat(testee.getRecords().size(), is(0));
	}

}
