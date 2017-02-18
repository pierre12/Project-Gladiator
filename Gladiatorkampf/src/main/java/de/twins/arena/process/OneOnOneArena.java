package de.twins.arena.process;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import de.twins.arena.domain.ArenaResult;
import de.twins.arena.domain.ArenaResult.Result;
import de.twins.arena.domain.FightRecord;
import de.twins.arena.exception.ArenaException;
import de.twins.gladiator.domain.Fightable;

/**
 * Arena limited for two gladiators which fight 1 vs 1 against each other.
 * 
 * @author Pierre
 *
 */
@Component
public class OneOnOneArena implements Arena {

	private Fightable fighter1;
	private FightRecord recordOfFighter1;
	private Fightable fighter2;
	private FightRecord recordOfFighter2;
	private ArenaResult result;
	private Set<Fightable> fighters;

	private int rounds;

	public OneOnOneArena() {
		fighters = new HashSet<>();
		result = new ArenaResult();
		rounds = 20;
	}

	private void resetResults() {
		result.resetResults();
	}

	public OneOnOneArena(Set<Fightable> gladiators, int rounds) {
		super();
		this.fighters = gladiators;
		this.rounds = rounds;

	}

	@Override
	public void addFighter(Fightable gladiator) {
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
	public void addFighters(Set<Fightable> fighter) {
		if (fighter == null) {
			throw new ArenaException("Gladiators set must not be null");
		}
		if ((this.fighters.size() + fighter.size()) >= 2) {
			throw new ArenaException(
					"Arena does only allows two Gladiators but it was tried to increase the number of gladiators to "
							+ this.fighters.size() + fighter.size());
		} else {
			for (Fightable fightable : fighter) {
				this.fighters.add(fightable);
			}
		}

	}

	@Override
	public void announceWinner() {
		String winnerMessage = "Gladiator %s won the fight.With %d round win(s)";
		String tiedMessage = "Both gladiators won %d round.";
		Integer wonRoundsFirstGladiator = result.getWonRound(fighter1);
		Integer wonRoundsSecondGladiator = result.getWonRound(fighter2);
		if (wonRoundsFirstGladiator > wonRoundsSecondGladiator) {
			System.out.println(String.format(winnerMessage, fighter1.getName(), wonRoundsFirstGladiator));
		} else if (wonRoundsFirstGladiator == wonRoundsSecondGladiator) {
			System.out.println(String.format(tiedMessage, wonRoundsFirstGladiator));
		} else {
			System.out.println(String.format(winnerMessage, fighter2.getName(), wonRoundsSecondGladiator));
		}

	}

	@Override
	public void endFight() {
		BigDecimal dmgGotFighter1 = calculateDmgInflicted(fighter1);
		BigDecimal dmgGotFighter2 = calculateDmgInflicted(fighter2);
		BigDecimal dmgMadeFighter1 = calculateDmgIncome(fighter1);
		BigDecimal dmgMadeFighter2 = calculateDmgIncome(fighter2);
		System.out.println(
				"Fighter1 made " + dmgMadeFighter1.toString() + " dmg and got " + dmgGotFighter1.toString() + " dmg.");
		System.out.println(
				"Fighter2 made " + dmgMadeFighter2.toString() + " dmg and got " + dmgGotFighter2.toString() + " dmg.");
		announceWinner();
		resetResults();
	}

	private BigDecimal calculateDmgIncome(Fightable fighter) {
		List<FightRecord> records = this.result.getFightRecordsByFightable(fighter);
		BigDecimal dmgIncome = BigDecimal.ZERO;
		for (FightRecord fightRecord : records) {
			dmgIncome = dmgIncome.add(fightRecord.getDmgTaken());
		}
		return dmgIncome;
	}

	private BigDecimal calculateDmgInflicted(Fightable fighter) {
		List<FightRecord> records = this.result.getFightRecordsByFightable(fighter);
		BigDecimal dmgInflicted = BigDecimal.ZERO;
		for (FightRecord fightRecord : records) {
			dmgInflicted = dmgInflicted.add(fightRecord.getDmgTaken());
		}
		return dmgInflicted;
	}

	@Override
	public void endRound() {
		if (fighter1.isAlive() && !fighter2.isAlive()) {
			setResults(Result.WIN, Result.LOSE);
		} else if (!fighter1.isAlive() && fighter2.isAlive()) {
			setResults(Result.LOSE, Result.WIN);
		} else {
			setResults(Result.DRAW, Result.DRAW);
		}
		this.result.addRecord(recordOfFighter1);
		this.result.addRecord(recordOfFighter2);
		recordOfFighter1 = new FightRecord(this.fighter1);
		recordOfFighter2 = new FightRecord(this.fighter2);
		restoreHealthOfGladiators();

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
		Iterator<Fightable> iterator = fighters.iterator();
		fighter1 = iterator.next();
		fighter2 = iterator.next();
		createOrResetFightRecordsForNewRound();
		for (int i = 1; i <= rounds; i++) {
			System.out.println("Round " + i);
			startRound();
		}

		endFight();
	}

	private void createOrResetFightRecordsForNewRound() {
		recordOfFighter1 = new FightRecord(fighter1);
		recordOfFighter2 = new FightRecord(fighter2);
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
			recordOfFighter2.addDmgTaken(damageDoneToFighter2);
			recordOfFighter1.addDmgInflicted(damageDoneToFighter2);
			bothAlive = fighter1.isAlive() && fighter2.isAlive();
			endlessFight = !dmgDoneToFighter1.equals(BigDecimal.ZERO) && !damageDoneToFighter2.equals(BigDecimal.ZERO);
		}
		endRound();
	}

}
