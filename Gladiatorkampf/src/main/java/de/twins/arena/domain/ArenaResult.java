package de.twins.arena.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.twins.gladiator.domain.Fightable;
import de.twins.gladiator.domain.Persistable;

public class ArenaResult extends Persistable {

	public enum Result {
		WIN, LOSE, DRAW
	}

	private Map<Fightable, List<FightRecord>> records;

	public ArenaResult() {
		records = new HashMap<>();
	}
	public void resetResults() {
		records.clear();
	}

	public Map<Fightable, List<FightRecord>> getRecords() {
		return new HashMap<Fightable, List<FightRecord>>(records);
	}

	public List<FightRecord> getFightRecordsByFightable(Fightable fightable) {
		if (records.containsKey(fightable)) {
			return records.get(fightable);
		}
		return Collections.emptyList();
	}

	public void addRecord(FightRecord record) {
		List<FightRecord> records;
		Fightable fightable = record.getFightable();
		if (this.records.containsKey(fightable)) {
			records = this.records.get(fightable);
			records.add(record);
			this.records.put(fightable, records);
		}else{
			records = new ArrayList<>();
			records.add(record);
			this.records.put(fightable, records);
		}
	}

	public int getLostRound(Fightable fighter) {
		List<FightRecord> fightRecords = getFightRecordsByFightable(fighter);
		int loses = 0;
		for (FightRecord fightRecord : fightRecords) {
			if (fightRecord.getResult().equals(Result.LOSE)) {
				loses += 1;
			}
		}
		return loses;
	}

	public int getTiedRound(Fightable fighter) {
		List<FightRecord> fightRecords = getFightRecordsByFightable(fighter);
		int draws = 0;
		for (FightRecord fightRecord : fightRecords) {
			if (fightRecord.getResult().equals(Result.DRAW)) {
				draws += 1;
			}
		}
		return draws;

	}

	public int getWonRound(Fightable fighter) {
		List<FightRecord> fightRecords = getFightRecordsByFightable(fighter);
		int wins = 0;
		for (FightRecord fightRecord : fightRecords) {
			if (fightRecord.getResult().equals(Result.WIN)) {
				wins += 1;
			}
		}
		return wins;

	}
}
