package de.twins.gladiator.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Persistable {

	@javax.persistence.Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
