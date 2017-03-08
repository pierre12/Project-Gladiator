package de.twins.arena.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	private FightRecord recordOfFighter1;
	private FightRecord recordOfFighter2;
	private ArenaResult result;
	private List<AbstractFighter> fighters;

	private int rounds;

	public OneOnOneArena() {
		fighters = new ArrayList<>();
		result = new ArenaResult();
		rounds = 20;
	}

	public OneOnOneArena(List<AbstractFighter> fighter, int rounds) {
		super();
		this.fighters = fighter;
		this.rounds = rounds;

	}

	@Override
	public void addFighter(AbstractFighter fighter) {
		if (fighter == null) {
			throw new ArenaException("Fighter must not be null");
		}
		boolean isAlreadyInArena = fighters.contains(fighter);
		if (fighters.size() == 2 && !isAlreadyInArena) {
			throw new ArenaException(
					"Arena does only allow two fighters but it was tried to increase the number of fighters to " + 3);
		}
		if (!isAlreadyInArena) {
			fighters.add(fighter);
		}else{
			System.out.println(fighter.getName() + "is already in the arena" );
		}

	}

	@Override
	public void addFighters(List<AbstractFighter> fighters) {
		if (fighters == null) {
			throw new ArenaException("Fighters set must not be null");
		}
		for (AbstractFighter fighter : fighters) {
			addFighter(fighter);
		}
	}

	@Override
	public void announceWinner() {
		String winnerMessage = "Gladiator %s won the fight.With %d round win(s)";
		String tiedMessage = "End Of The Match: Draw";
		Integer wonRoundsFirstGladiator = result.getNumberOfRoundsWithResult(fighters.get(0), Result.WIN);
		Integer wonRoundsSecondGladiator = result.getNumberOfRoundsWithResult(fighters.get(1), Result.WIN);
		if (wonRoundsFirstGladiator > wonRoundsSecondGladiator) {
			System.out.println(String.format(winnerMessage, fighters.get(0).getName(), wonRoundsFirstGladiator));
		} else if (wonRoundsFirstGladiator == wonRoundsSecondGladiator) {
			System.out.println(String.format(tiedMessage, wonRoundsFirstGladiator));
		} else {
			System.out.println(String.format(winnerMessage, fighters.get(1), wonRoundsSecondGladiator));
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
		recordOfFighter1 = new FightRecord(fighters.get(0));
		recordOfFighter2 = new FightRecord(fighters.get(1));
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

		if (fighters.get(0).isAlive() && !fighters.get(1).isAlive()) {
			setResults(Result.WIN, Result.LOSE);
			fighterWinsRound(fighters.get(0));
		} else if (!fighters.get(0).isAlive() && fighters.get(1).isAlive()) {
			setResults(Result.LOSE, Result.WIN);
			fighterWinsRound(fighters.get(1));
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
	public void removeFighter(AbstractFighter fighter) {
		fighters.remove(fighter);
	}

	@Override
	public void removeFighters(Set<AbstractFighter> fighters) {
		for (AbstractFighter fighter : fighters) {
			this.fighters.remove(fighter);
		}

	}

	private void reportDmg() {
		BigDecimal dmgGotFighter1 = calculateDmgInflicted(fighters.get(0));
		BigDecimal dmgGotFighter2 = calculateDmgInflicted(fighters.get(1));
		BigDecimal dmgMadeFighter1 = calculateDmgIncome(fighters.get(0));
		BigDecimal dmgMadeFighter2 = calculateDmgIncome(fighters.get(1));
		System.out.println("Fighter1 made " + dmgMadeFighter1.setScale(2, RoundingMode.FLOOR) + " dmg and got "
				+ dmgGotFighter1.setScale(2, RoundingMode.FLOOR) + " dmg.");
		System.out.println("Fighter2 made " + dmgMadeFighter2.setScale(2, RoundingMode.FLOOR) + " dmg and got "
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
			BigDecimal dmgDoneToFighter1 = fighters.get(0).defend(fighters.get(1).getTotalAttack());
			recordOfFighter1.addDmgTaken(dmgDoneToFighter1);
			recordOfFighter2.addDmgInflicted(dmgDoneToFighter1);
			BigDecimal damageDoneToFighter2 = fighters.get(1).defend(fighters.get(0).getTotalAttack());
			recordOfFighter1.addDmgInflicted(damageDoneToFighter2);
			recordOfFighter2.addDmgTaken(damageDoneToFighter2);
			bothAlive = fighters.get(0).isAlive() && fighters.get(1).isAlive();
			endlessFight = dmgDoneToFighter1.equals(BigDecimal.ZERO) && damageDoneToFighter2.equals(BigDecimal.ZERO);
		}
		endRound();
	}

}
