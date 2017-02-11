package de.twins.gladiator.domain;

import java.math.BigDecimal;

/**
 * Equipment used for the gladiatorto enhance his attributes.
 * @author Pierre
 *
 */
public class Equipment {
	
	private BodyPart bodyPart;
	private BigDecimal hp;
	private BigDecimal attack;
	private BigDecimal defense;
	
	public BigDecimal getAttack() {
		return attack;
	}
	public BodyPart getBodyPart() {
		return bodyPart;
	}
	public BigDecimal getDefense() {
		return defense;
	}
	public BigDecimal getHealthPoints() {
		return hp;
	}
	public void setAttack(BigDecimal attack) {
		this.attack = attack;
	}
	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;
	}
	public void setDefense(BigDecimal defense) {
		this.defense = defense;
	}
	public void setHp(BigDecimal hp) {
		this.hp = hp;
	}
}
