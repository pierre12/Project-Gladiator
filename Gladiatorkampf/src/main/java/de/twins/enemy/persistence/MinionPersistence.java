package de.twins.enemy.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.twins.enemy.domain.Minion;

@Repository
public interface MinionPersistence extends CrudRepository<Minion, Integer> {

}
