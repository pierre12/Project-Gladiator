package de.twins.arena;

import java.util.Set;

import de.twins.gladiator.domain.Gladiator;

/**
 * Arena is the place where <tt>Gladiator<tt> can fight against each other.
 * 
 * @author Pierre
 *
 */
public interface Arena {
	/**
	 * Announce the winner of the fight.
	 */
	void announceWinner();

	/**
	 * Starts the fight. A fight is a collection of multiple rounds.
	 */
	void startFight();

	/**
	 * Starts a round.
	 */
	void startRound();

	/**
	 * Determines the number of rounds
	 * 
	 * @param rounds
	 *            number of rounds which should be fight
	 */
	void setRounds(int rounds);

	/**
	 * Ends a round.
	 */
	void endRound();

	/**
	 * Ends a fight.
	 */
	void endFight();

	/**
	 * Adds a gladiator to the arena.
	 * 
	 * @param gladiator
	 *            {@link Gladiator} which is added to the arena.
	 */
	void addGladiator(Gladiator gladiator);

	/**
	 * Adds a set of gladiators to the arena.
	 * 
	 * @param gladiators
	 *            gladiators which should be added to the arena.
	 */
	void addGladiators(Set<Gladiator> gladiators);

	/**
	 * Removes a gladiator from the arena.
	 * 
	 * @param gladiator
	 *            gladiator which should be removed from the arena.
	 */
	void removeGladiator(Gladiator gladiator);

	/**
	 * Removes a set of gladiators from the arena.
	 * 
	 * @param gladiators
	 *            gladiators which should be removed from the arena.
	 */
	void removeGladiators(Set<Gladiator> gladiators);
}
