package de.twins.arena.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import de.twins.arena.domain.ArenaResult.Result;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Persistable;

@Entity
public class FightRecord extends Persistable {

	@ManyToOne
	private AbstractFighter fighter;

	@OneToMany(cascade = CascadeType.ALL)
	private List<AbstractFighter> enemies;

	@OneToMany(cascade = CascadeType.ALL)
	private List<AbstractFighter> allies;

	private BigDecimal dmgTaken;

	private BigDecimal dmgInflicted;

	private Result result;

	public FightRecord(AbstractFighter fighter) {
		this.fighter = fighter;
		dmgTaken = BigDecimal.ZERO;
		dmgInflicted = BigDecimal.ZERO;
	}

	public AbstractFighter getFighter() {
		return fighter;
	}

	public void setFigher(AbstractFighter fightable) {
		this.fighter = fightable;
	}

	public BigDecimal getDmgTaken() {
		return dmgTaken;
	}

	public void setDmgTaken(BigDecimal dmgTaken) {
		this.dmgTaken = dmgTaken;
	}

	public BigDecimal getDmgInflicted() {
		return dmgInflicted;
	}

	public void setDmgInflicted(BigDecimal dmgDone) {
		this.dmgInflicted = dmgDone;
	}

	public List<AbstractFighter> getEnemies() {
		return enemies;
	}

	public List<AbstractFighter> getAllies() {
		return allies;
	}

	public Result getResult() {
		return result;
	}

	public void setEnemies(List<AbstractFighter> enemies) {
		this.enemies = enemies;
	}

	public void setAllies(List<AbstractFighter> allies) {
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
