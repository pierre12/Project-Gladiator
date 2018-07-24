package de.twins.arena.domain;

import de.twins.arena.domain.ArenaResult.Result;
import de.twins.gladiator.domain.AbstractFighter;

import java.math.BigDecimal;
import java.util.List;

public class FightRecord {

    private AbstractFighter fighter;

    private List<AbstractFighter> enemies;

    private List<AbstractFighter> allies;

    private BigDecimal dmgTaken;

    private BigDecimal dmgInflicted;

    private Result result;

    public FightRecord(AbstractFighter fighter) {
        this.fighter = fighter;
        dmgTaken = BigDecimal.ZERO;
        dmgInflicted = BigDecimal.ZERO;
    }

    public void addDmgInflicted(BigDecimal damageDoneToFighter) {
        dmgInflicted = this.dmgInflicted.add(damageDoneToFighter);

    }

    public void addDmgTaken(BigDecimal damageDoneToFighter) {
        dmgTaken = this.dmgTaken.add(damageDoneToFighter);
    }

    public List<AbstractFighter> getAllies() {
        return allies;
    }

    public BigDecimal getDmgInflicted() {
        return dmgInflicted;
    }

    public BigDecimal getDmgTaken() {
        return dmgTaken;
    }

    public List<AbstractFighter> getEnemies() {
        return enemies;
    }

    public AbstractFighter getFighter() {
        return fighter;
    }

    public Result getResult() {
        return result;
    }

    public void setAllies(List<AbstractFighter> allies) {
        this.allies = allies;
    }

    public void setDmgInflicted(BigDecimal dmgDone) {
        this.dmgInflicted = dmgDone;
    }

    public void setDmgTaken(BigDecimal dmgTaken) {
        this.dmgTaken = dmgTaken;
    }

    public void setEnemies(List<AbstractFighter> enemies) {
        this.enemies = enemies;
    }

    public void setFigher(AbstractFighter fightable) {
        this.fighter = fightable;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
