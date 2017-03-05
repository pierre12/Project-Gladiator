package de.twins.arena.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Persistable;

@Entity
public class ArenaResult extends Persistable {

	public enum Result {
		WIN, LOSE, DRAW
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "arena_result_id")
	private List<FightRecord> records;

	public ArenaResult() {
		records = new ArrayList<FightRecord>();
	}
	public void resetResults() {
		records.clear();
	}

	public void setRecords(List<FightRecord> records) {
		this.records = records;
	}
	public List<FightRecord> getRecords() {
		return new ArrayList<>(records);
	}

	public List<FightRecord> getFightRecordsByFightable(AbstractFighter fighter) {
		List<FightRecord> recordsOfFighter = new ArrayList<>();
		for (FightRecord fightRecord : records) {
			if (fightRecord.getFighter().getId() == fighter.getId()) {
				recordsOfFighter.add(fightRecord);
			}
		}
		return recordsOfFighter;
	}

	public void addRecord(FightRecord record) {
		records.add(record);
	}

	public int getNumberOfRoundsWithResult(AbstractFighter fighter,Result result) {
		List<FightRecord> fightRecords = getFightRecordsByFightable(fighter);
		int numberOfResult = 0;
		for (FightRecord fightRecord : fightRecords) {
			if (fightRecord.getResult().equals(result)) {
				numberOfResult += 1;
			}
		}
		return numberOfResult;
	}


}
