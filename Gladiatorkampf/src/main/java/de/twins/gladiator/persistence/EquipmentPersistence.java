package de.twins.gladiator.persistence;

import org.springframework.data.repository.CrudRepository;

import de.twins.gladiator.domain.Equipment;

public interface EquipmentPersistence extends CrudRepository<Equipment, Integer> {

}
