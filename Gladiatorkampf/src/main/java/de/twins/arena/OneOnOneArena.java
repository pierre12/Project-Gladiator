package de.twins.arena;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

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
	private Fightable fighter2;
	private Map<ArenaResult, Integer> resultForFirstGladiator;
	private Map<ArenaResult, Integer> resultForSecondGladiator;

	private Set<Fightable> fighters;

	private int rounds;

	public OneOnOneArena() {
		fighters = new HashSet<>();
		resetResults();
		rounds = 500;
	}

	private void resetResults() {
		resultForFirstGladiator = new HashMap<ArenaResult, Integer>();
		resultForSecondGladiator = new HashMap<ArenaResult, Integer>();
		for (ArenaResult resultPossibility : ArenaResult.values()) {
			resultForFirstGladiator.put(resultPossibility, 0);
			resultForSecondGladiator.put(resultPossibility, 0);
		}

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
		Integer wonRoundsFirstGladiator = resultForFirstGladiator.get(ArenaResult.WIN);
		Integer wonRoundsSecondGladiator = resultForSecondGladiator.get(ArenaResult.WIN);
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
		announceWinner();
		resetResults();
	}

	@Override
	public void endRound() {
		if (fighter1.isAlive() && !fighter2.isAlive()) {
			setResults(ArenaResult.WIN, ArenaResult.LOSE);
		}

		else if (!fighter1.isAlive() && fighter2.isAlive()) {
			setResults(ArenaResult.LOSE, ArenaResult.WIN);
		} else {
			setResults(ArenaResult.TIED, ArenaResult.TIED);
		}
		restoreHealthOfGladiators();

	}

	public Map<ArenaResult, Integer> getResultForFirstGladiator() {
		return new HashMap<>(resultForFirstGladiator);
	}

	public Map<ArenaResult, Integer> getResultForSecondGladiator() {
		return new HashMap<>(resultForSecondGladiator);
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
	private void setResults(ArenaResult resultOfFirst, ArenaResult resultOfSecond) {
		resultForFirstGladiator.put(resultOfFirst, resultForFirstGladiator.get(resultOfFirst) + 1);
		resultForSecondGladiator.put(resultOfSecond, resultForSecondGladiator.get(resultOfSecond) + 1);
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
			boolean gotDmg = fighter1.defend(fighter2.getTotalAttack());
			boolean gotDmg2 = fighter2.defend(fighter1.getTotalAttack());
			bothAlive = fighter1.isAlive() && fighter2.isAlive();
			endlessFight = !gotDmg && !gotDmg2;
		}
		endRound();
	}

}
