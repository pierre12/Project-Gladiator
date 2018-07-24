package de.twins.arena.domain;

import de.twins.gladiator.domain.AbstractFighter;

import java.util.ArrayList;
import java.util.List;


public class ArenaResult {

    public enum Result {
        WIN, LOSE, DRAW
    }

    private List<FightRecord> records;

    public ArenaResult() {
        records = new ArrayList<FightRecord>();
    }

    public void addRecord(FightRecord record) {
        records.add(record);
    }

    public List<FightRecord> getFightRecordsByFightable(AbstractFighter fighter) {
        List<FightRecord> recordsOfFighter = new ArrayList<>();
        for (FightRecord fightRecord : records) {
            if (fightRecord.getFighter().getName() == fighter.getName()) {
                recordsOfFighter.add(fightRecord);
            }
        }
        return recordsOfFighter;
    }

    public int getNumberOfRoundsWithResult(AbstractFighter fighter, Result result) {
        List<FightRecord> fightRecords = getFightRecordsByFightable(fighter);
        int numberOfResult = 0;
        for (FightRecord fightRecord : fightRecords) {
            if (fightRecord.getResult().equals(result)) {
                numberOfResult += 1;
            }
        }
        return numberOfResult;
    }

    public List<FightRecord> getRecords() {
        return new ArrayList<>(records);
    }

    public void resetResults() {
        records.clear();
    }

    public void setRecords(List<FightRecord> records) {
        this.records = records;
    }


}
