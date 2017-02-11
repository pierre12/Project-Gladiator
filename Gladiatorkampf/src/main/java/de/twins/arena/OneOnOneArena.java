package de.twins.arena;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.twins.gladiator.domain.Gladiator;

/**
 * Arena limited for two gladiators which fight 1 vs 1 against each other.
 * 
 * @author Pierre
 *
 */
public class OneOnOneArena implements Arena {
	private Gladiator firstGladiator;
	private Gladiator secondGladiator;
	private Map<ArenaResult, Integer> resultForFirstGladiator;
	private Map<ArenaResult, Integer> resultForSecondGladiator;

	private Set<Gladiator> gladiators;

	private int rounds;
	public OneOnOneArena() {
		gladiators = new HashSet<>();
		resultForFirstGladiator = new HashMap<ArenaResult, Integer>();
		resultForSecondGladiator = new HashMap<ArenaResult, Integer>();
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
		String winnerMessage = "Gladiator {0} won the fight.With {1} round win(s)";
		String tiedMessage = "Both gladiators won {0}  round.";
		if (resultForFirstGladiator.get(ArenaResult.WIN) > resultForSecondGladiator.get(ArenaResult.WIN)) {
			System.out.println(String.format(winnerMessage, firstGladiator.getName(),
					resultForFirstGladiator.get(ArenaResult.WIN)));
		} else if (resultForFirstGladiator.get(ArenaResult.WIN) == resultForSecondGladiator.get(ArenaResult.WIN)) {
			System.out.println(String.format(tiedMessage, resultForFirstGladiator.get(ArenaResult.WIN)));
		} else {
			System.out.println(String.format(winnerMessage, secondGladiator.getName(),
					resultForSecondGladiator.get(ArenaResult.WIN)));
		}

	}

	@Override
	public void endFight() {
		announceWinner();
		resultForFirstGladiator.clear();
		resultForSecondGladiator.clear();
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

	private void restoreHealthOfGladiators() {
		gladiators.forEach(gladiator -> gladiator.setCurrentHealthPoints(gladiator.getTotalHealthPoints()));
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
		firstGladiator = gladiators.iterator().next();
		secondGladiator = gladiators.iterator().next();
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
