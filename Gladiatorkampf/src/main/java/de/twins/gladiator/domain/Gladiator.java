package de.twins.gladiator.domain;

import java.math.BigDecimal;

public class Gladiator {

	private String name;
	private BigDecimal hp;
	private BigDecimal attack;
	private BigDecimal defense;

	public Gladiator(String name, BigDecimal hp, BigDecimal attack, BigDecimal defense) {
		super();
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getHp() {
		return hp;
	}
	public void setHp(BigDecimal hp) {
		if(hp.compareTo(BigDecimal.ZERO) <= 0){
		this.hp = BigDecimal.ZERO;
		}
		this.hp = hp;
	}
	public BigDecimal getAttack() {
		return attack;
	}
	public void setAttack(BigDecimal attack) {
		this.attack = attack;
	}
	public BigDecimal getDefense() {
		return defense;
	}
	public void setDefense(BigDecimal defense) {
		this.defense = defense;
	}
	/**
	 * Reduces enemy attack power by own defense value.
	 * The reduced attack power substracts directly the hp.
	 * @param attack The attack power which reduces the hp.
	 */
	public void defend(BigDecimal attack){
		BigDecimal dmg = attack.subtract(defense);
		if(dmg.compareTo(BigDecimal.ZERO) > 0){
			setHp(getHp().subtract(dmg));
		}
	}
}
