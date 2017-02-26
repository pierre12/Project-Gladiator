package de.twins.arena.domain;

import java.math.BigDecimal;
import java.util.List;

import de.twins.arena.domain.ArenaResult.Result;
import de.twins.gladiator.domain.Fightable;
import de.twins.gladiator.domain.Persistable;

public class FightRecord extends Persistable {

	private Fightable fighter;

	private List<Fightable> enemies;

	private List<Fightable> allies;

	private BigDecimal dmgTaken;

	private BigDecimal dmgInflicted;

	private Result result;

	public FightRecord(Fightable fighter) {
		this.fighter = fighter;
		dmgTaken = BigDecimal.ZERO;
		dmgInflicted = BigDecimal.ZERO;
	}

	public Fightable getFightable() {
		return fighter;
	}

	public void setFightable(Fightable fightable) {
		this.fighter = fightable;
	}

	public BigDecimal getDmgTaken() {
		return dmgTaken;
	}

	public void setDmgTaken(BigDecimal dmgTaken) {
		this.dmgTaken = dmgTaken;
	}

	public BigDecimal getDmgDone() {
		return dmgInflicted;
	}

	public void setDmgDone(BigDecimal dmgDone) {
		this.dmgInflicted = dmgDone;
	}

	public List<Fightable> getEnemies() {
		return enemies;
	}

	public List<Fightable> getAllies() {
		return allies;
	}

	public Result getResult() {
		return result;
	}

	public void setEnemies(List<Fightable> enemies) {
		this.enemies = enemies;
	}

	public void setAllies(List<Fightable> allies) {
		this.allies = allies;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void addDmgTaken(BigDecimal damageDoneToFighter) {
		dmgTaken = this.dmgTaken.add(damageDoneToFighter);
	}

	public void addDmgInflicted(BigDecimal damageDoneToFighter) {
		dmgInflicted = this.dmgInflicted.add(damageDoneToFighter);

	}

}
