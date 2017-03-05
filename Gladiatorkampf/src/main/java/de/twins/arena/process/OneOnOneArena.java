package de.twins.arena.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.twins.arena.domain.ArenaResult;
import de.twins.arena.domain.ArenaResult.Result;
import de.twins.arena.domain.FightRecord;
import de.twins.arena.exception.ArenaException;
import de.twins.arena.persistence.ArenaResultPersistence;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Fightable;

/**
 * Arena limited for two gladiators which fight 1 vs 1 against each other.
 * 
 * @author Pierre
 *
 */
@Component
@Transactional
public class OneOnOneArena implements Arena {

	@Autowired
	private ArenaResultPersistence arenaResultPersistence;

	private AbstractFighter fighter1;
	private FightRecord recordOfFighter1;
	private AbstractFighter fighter2;
	private FightRecord recordOfFighter2;
	private ArenaResult result;
	private Set<AbstractFighter> fighters;

	private int rounds;

	public OneOnOneArena() {
		fighters = new HashSet<>();
		result = new ArenaResult();
		rounds = 20;
	}

	public OneOnOneArena(Set<AbstractFighter> gladiators, int rounds) {
		super();
		this.fighters = gladiators;
		this.rounds = rounds;

	}

	@Override
	public void addFighter(AbstractFighter gladiator) {
		if (gladiator == null) {
			throw new ArenaException("Gladiator set must not be null");
		} else if (fighters.size() == 2) {
			throw new ArenaException(
					"Arena does only allows two Gladiators but it was tried to increase the number of gladiators to "
							+ 3);
		} else {
			fighters.add(gladiator);
		}

	}

	@Override
	public void addFighters(Set<AbstractFighter> fighter) {
		if (fighter == null) {
			throw new ArenaException("Gladiators set must not be null");
		}
		if ((this.fighters.size() + fighter.size()) >= 2) {
			throw new ArenaException(
					"Arena does only allows two Gladiators but it was tried to increase the number of gladiators to "
							+ this.fighters.size() + fighter.size());
		} else {
			for (AbstractFighter fightable : fighter) {
				this.fighters.add(fightable);
			}
		}

	}

	@Override
	public void announceWinner() {
		String winnerMessage = "Gladiator %s won the fight.With %d round win(s)";
		String tiedMessage = "End Of The Match: Draw";
		Integer wonRoundsFirstGladiator = result.getNumberOfRoundsWithResult(fighter1, Result.WIN);
		Integer wonRoundsSecondGladiator = result.getNumberOfRoundsWithResult(fighter2, Result.WIN);
		if (wonRoundsFirstGladiator > wonRoundsSecondGladiator) {
			System.out.println(String.format(winnerMessage, fighter1.getName(), wonRoundsFirstGladiator));
		} else if (wonRoundsFirstGladiator == wonRoundsSecondGladiator) {
			System.out.println(String.format(tiedMessage, wonRoundsFirstGladiator));
		} else {
			System.out.println(String.format(winnerMessage, fighter2.getName(), wonRoundsSecondGladiator));
		}

	}

	private BigDecimal calculateDmgIncome(AbstractFighter fighter) {
		List<FightRecord> records = this.result.getFightRecordsByFightable(fighter);
		BigDecimal dmgIncome = BigDecimal.ZERO;
		for (FightRecord fightRecord : records) {
			dmgIncome = dmgIncome.add(fightRecord.getDmgTaken());
		}
		return dmgIncome;
	}

	private BigDecimal calculateDmgInflicted(AbstractFighter fighter) {
		List<FightRecord> records = this.result.getFightRecordsByFightable(fighter);
		BigDecimal dmgInflicted = BigDecimal.ZERO;
		for (FightRecord fightRecord : records) {
			dmgInflicted = dmgInflicted.add(fightRecord.getDmgInflicted());
		}
		return dmgInflicted;
	}

	private void createOrResetFightRecordsForNewRound() {
		recordOfFighter1 = new FightRecord(fighter1);
		recordOfFighter2 = new FightRecord(fighter2);
	}

	private void drawRound() {
		System.out.println("Draw");

	}

	@Override
	public void endFight() {
		reportDmg();
		announceWinner();

		arenaResultPersistence.save(this.result);
	}

	@Override
	public void endRound() {
		if (fighter1.isAlive() && !fighter2.isAlive()) {
			setResults(Result.WIN, Result.LOSE);
			fighterWinsRound(fighter1);
		} else if (!fighter1.isAlive() && fighter2.isAlive()) {
			setResults(Result.LOSE, Result.WIN);
			fighterWinsRound(fighter2);
		} else {
			setResults(Result.DRAW, Result.DRAW);
			drawRound();
		}
		this.result.addRecord(recordOfFighter1);
		this.result.addRecord(recordOfFighter2);
		createOrResetFightRecordsForNewRound();
		restoreHealthOfGladiators();

	}

	private void fighterWinsRound(Fightable fighter) {
		System.out.println("Fighter " + fighter.getName() + " wins the Round.");

	}

	public ArenaResult getResult() {
		return this.result;
	}

	@Override
	public void removeFighter(Fightable gladiator) {
		fighters.remove(gladiator);
	}

	@Override
	public void removeFighters(Set<Fightable> gladiators) {
		for (Fightable gladiator : gladiators) {
			this.fighters.remove(gladiator);
		}

	}

	private void reportDmg() {
		BigDecimal dmgGotFighter1 = calculateDmgInflicted(fighter1);
		BigDecimal dmgGotFighter2 = calculateDmgInflicted(fighter2);
		BigDecimal dmgMadeFighter1 = calculateDmgIncome(fighter1);
		BigDecimal dmgMadeFighter2 = calculateDmgIncome(fighter2);
		System.out.println(
				"Fighter1 made " + dmgMadeFighter1.setScale(2, RoundingMode.FLOOR) + " dmg and got "
						+ dmgGotFighter1.setScale(2, RoundingMode.FLOOR) + " dmg.");
		System.out.println(
				"Fighter2 made " + dmgMadeFighter2.setScale(2, RoundingMode.FLOOR) + " dmg and got "
						+ dmgGotFighter2.setScale(2, RoundingMode.FLOOR) + " dmg.");
	}

	private void resetResults() {
		result.resetResults();
	}

	// heals dead gladiator by 100% and living gladiator by 25%
	private void restoreHealthOfGladiators() {
		fighters.forEach(gladiator -> {
			if (gladiator.isAlive()) {
				gladiator.setCurrentHealthPoints(gladiator.getCurrentHealthPoints()
						.add(gladiator.getTotalHealthPoints().divide(new BigDecimal(4))));
				if (gladiator.getCurrentHealthPoints().compareTo(gladiator.getTotalHealthPoints()) == 1) {
					gladiator.setCurrentHealthPoints(gladiator.getTotalHealthPoints());
				}
			} else {
				gladiator.setCurrentHealthPoints(gladiator.getTotalHealthPoints());
			}
		});
	}

	/**
	 * Sets result for both gladiators
	 * 
	 * @param result
	 *            result of the first gladiator
	 * @param result
	 *            result of the second gladiator
	 */
	private void setResults(Result resultOfFirst, Result resultOfSecond) {
		this.recordOfFighter1.setResult(resultOfFirst);
		this.recordOfFighter2.setResult(resultOfSecond);
	}

	@Override
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	@Override
	public void startFight() {

		Iterator<AbstractFighter> iterator = fighters.iterator();
		fighter1 = iterator.next();
		fighter2 = iterator.next();

		resetResults();
		createOrResetFightRecordsForNewRound();
		for (int i = 1; i <= rounds; i++) {
			System.out.println("Round " + i);
			startRound();
		}

		endFight();
	}

	@Override
	public void startRound() {
		boolean bothAlive = true;
		boolean endlessFight = false;
		while (bothAlive && !endlessFight) {
			BigDecimal dmgDoneToFighter1 = fighter1.defend(fighter2.getTotalAttack());
			recordOfFighter1.addDmgTaken(dmgDoneToFighter1);
			recordOfFighter2.addDmgInflicted(dmgDoneToFighter1);
			BigDecimal damageDoneToFighter2 = fighter2.defend(fighter1.getTotalAttack());
			recordOfFighter1.addDmgInflicted(damageDoneToFighter2);
			recordOfFighter2.addDmgTaken(damageDoneToFighter2);
			bothAlive = fighter1.isAlive() && fighter2.isAlive();
			endlessFight = dmgDoneToFighter1.equals(BigDecimal.ZERO) && damageDoneToFighter2.equals(BigDecimal.ZERO);
		}
		endRound();
	}

}
