package de.twins.arena;

import java.util.Set;

import de.twins.gladiator.domain.Fightable;

/**
 * Arena is the place where <tt>Gladiator<tt> can fight against each other.
 * 
 * @author Pierre
 *
 */
public interface Arena {
	/**
	 * Adds a {@link Fightable} to the arena.
	 * 
	 * @param gladiator
	 *            {@link Fightable} which is added to the arena.
	 */
	void addFighter(Fightable gladiator);

	/**
	 * Adds a set of {@link Fightable} to the arena.
	 * 
	 * @param gladiators
	 *            {@link Fightable} which should be added to the arena.
	 */
	void addFighters(Set<Fightable> gladiators);

	/**
	 * Announce the winner of the fight.
	 */
	void announceWinner();

	/**
	 * Ends a fight.
	 */
	void endFight();

	/**
	 * Ends a round.
	 */
	void endRound();

	/**
	 * Removes a {@link Fightable} from the arena.
	 * 
	 * @param fighter
	 *            {@link Fightable} which should be removed from the arena.
	 */
	void removeFighter(Fightable fighter);

	/**
	 * Removes a set of {@link Fightable} from the arena.
	 * 
	 * @param fighter
	 *            {@link Fightable} which should be removed from the arena.
	 */
	void removeFighters(Set<Fightable> fighter);

	/**
	 * Determines the number of rounds
	 * 
	 * @param rounds
	 *            number of rounds which should be fight
	 */
	void setRounds(int rounds);

	/**
	 * Starts the fight. A fight is a collection of multiple rounds.
	 */
	void startFight();

	/**
	 * Starts a round.
	 */
	void startRound();
}
