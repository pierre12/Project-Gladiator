package de.twins.arena.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.twins.arena.domain.ArenaResult;

@Repository
public interface ArenaResultPersistence extends CrudRepository<ArenaResult, Integer> {

}
