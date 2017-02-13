package de.twins.arena;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import de.twins.gladiator.domain.Gladiator;

/**
 * Arena limited for two gladiators which fight 1 vs 1 against each other.
 * 
 * @author Pierre
 *
 */
@Component
public class OneOnOneArena implements Arena {

	private Gladiator firstGladiator;
	private Gladiator secondGladiator;
	private Map<ArenaResult, Integer> resultForFirstGladiator;
	private Map<ArenaResult, Integer> resultForSecondGladiator;

	private Set<Gladiator> gladiators;

	private int rounds;

	public OneOnOneArena() {
		gladiators = new HashSet<>();
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

	public OneOnOneArena(Set<Gladiator> gladiators, int rounds) {
		super();
		this.gladiators = gladiators;
		this.rounds = rounds;

	}

	@Override
	public void addGladiator(Gladiator gladiator) {
		if (gladiator == null) {
			throw new ArenaException("Gladiator set must not be null");
		} else if (gladiators.size() == 2) {
			throw new ArenaException(
					"Arena does only allows two Gladiators but it was tried to increase the number of gladiators to "
							+ 3);
		} else {
			gladiators.add(gladiator);
		}

	}

	@Override
	public void addGladiators(Set<Gladiator> gladiators) {
		if (gladiators == null) {
			throw new ArenaException("Gladiators set must not be null");
		}
		if ((this.gladiators.size() + gladiators.size()) >= 2) {
			throw new ArenaException(
					"Arena does only allows two Gladiators but it was tried to increase the number of gladiators to "
							+ this.gladiators.size() + gladiators.size());
		} else {
			gladiators.forEach((gladiator) -> gladiators.add(gladiator));
		}

	}

	@Override
	public void announceWinner() {
		String winnerMessage = "Gladiator %s won the fight.With %d round win(s)";
		String tiedMessage = "Both gladiators won %d round.";
		Integer wonRoundsFirstGladiator = resultForFirstGladiator.get(ArenaResult.WIN);
		Integer wonRoundsSecondGladiator = resultForSecondGladiator.get(ArenaResult.WIN);
		if (wonRoundsFirstGladiator > wonRoundsSecondGladiator) {
			System.out.println(String.format(winnerMessage, firstGladiator.getName(), wonRoundsFirstGladiator));
		} else if (wonRoundsFirstGladiator == wonRoundsSecondGladiator) {
			System.out.println(String.format(tiedMessage, wonRoundsFirstGladiator));
		} else {
			System.out.println(String.format(winnerMessage, secondGladiator.getName(), wonRoundsSecondGladiator));
		}

	}

	@Override
	public void endFight() {
		announceWinner();
		resetResults();
	}

	@Override
	public void endRound() {
		if (firstGladiator.isAlive() && !secondGladiator.isAlive()) {
			setResults(ArenaResult.WIN, ArenaResult.LOSE);
		}

		else if (!firstGladiator.isAlive() && secondGladiator.isAlive()) {
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
	public void removeGladiator(Gladiator gladiator) {
		gladiators.remove(gladiator);
	}

	@Override
	public void removeGladiators(Set<Gladiator> gladiators) {
		for (Gladiator gladiator : gladiators) {
			this.gladiators.remove(gladiator);
		}

	}

	// heals dead gladiator by 100% and living gladiator by 25%
	private void restoreHealthOfGladiators() {
		gladiators.forEach(gladiator -> {
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
		Iterator<Gladiator> iterator = gladiators.iterator();
		firstGladiator = iterator.next();
		secondGladiator = iterator.next();
		for (int i = 1; i <= rounds; i++) {
			startRound();
		}
		endFight();
	}

	@Override
	public void startRound() {
		boolean bothAlive = true;
		while (bothAlive) {
			firstGladiator.defend(secondGladiator.getTotalAttack());
			secondGladiator.defend(firstGladiator.getTotalAttack());
			bothAlive = firstGladiator.isAlive() && secondGladiator.isAlive();
		}
		endRound();
	}

}
