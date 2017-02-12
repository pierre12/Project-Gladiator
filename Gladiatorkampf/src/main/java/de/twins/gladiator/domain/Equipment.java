package de.twins.gladiator.domain;

import java.math.BigDecimal;

/**
 * Equipment used for the gladiatorto enhance his attributes.
 * 
 * @author Pierre
 *
 */
public class Equipment {

	public enum Rarity {
		COMMON, LEGENDARY, EPIC, MAGIC, UNCOMMON;
	}

	public enum BodyPart {
		HEAD, ARMS, BODY, LEGS, HANDS
	}

	private Rarity rarity;
	private BodyPart bodyPart;
	private BigDecimal healthPoints;
	private BigDecimal attack;

	private BigDecimal defense;

	public Equipment(BodyPart bodyPart, BigDecimal hp, BigDecimal attack, BigDecimal defense, Rarity rarity) {
		this(bodyPart, hp, attack, defense);
		this.rarity = rarity;
	}
	public Equipment(BodyPart bodyPart, BigDecimal hp, BigDecimal attack, BigDecimal defense) {
		super();
		this.bodyPart = bodyPart;
		this.healthPoints = hp;
		this.attack = attack;
		this.defense = defense;
	}

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
		return healthPoints;
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
		this.healthPoints = hp;
	}

	public Rarity getRarity() {
		return rarity;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Attack:");
		sb.append(attack);
		sb.append("\n");
		sb.append("Defense:");
		sb.append(defense);
		sb.append("\n");
		sb.append("Hp:");
		sb.append(healthPoints);
		sb.append("\n");
		sb.append("Bodypart:");
		sb.append(bodyPart);
		sb.append("\n");
		sb.append("Rarity:");
		sb.append(rarity);
		sb.append("\n");
		return sb.toString();
	}
}
