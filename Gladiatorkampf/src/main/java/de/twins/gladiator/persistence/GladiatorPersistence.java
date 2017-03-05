package de.twins.gladiator.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.twins.gladiator.domain.Gladiator;

@Repository
public interface GladiatorPersistence extends CrudRepository<Gladiator, Integer> {

}
