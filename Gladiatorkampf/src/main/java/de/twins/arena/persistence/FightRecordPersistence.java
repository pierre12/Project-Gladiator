package de.twins.arena.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.twins.arena.domain.FightRecord;

@Repository
public interface FightRecordPersistence extends CrudRepository<FightRecord, Integer> {

}
