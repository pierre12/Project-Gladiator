package de.twins.arena.process;

import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Fightable;

import java.util.List;
import java.util.Set;

/**
 * Arena is the place where <tt>Gladiator<tt> can fight against each other.
 *
 * @author Pierre
 */
public interface Arena {
    /**
     * Adds a {@link Fightable} to the arena.
     *
     * @param fighter {@link Fightable} which is added to the arena.
     */
    void addFighter(AbstractFighter fighter);

    /**
     * Adds a set of {@link Fightable} to the arena.
     *
     * @param fighters {@link Fightable} which should be added to the arena.
     */
    void addFighters(List<AbstractFighter> fighters);

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
     * @param fighter {@link Fightable} which should be removed from the arena.
     */
    void removeFighter(AbstractFighter fighter);

    /**
     * Removes a set of {@link Fightable} from the arena.
     *
     * @param fighter {@link Fightable} which should be removed from the arena.
     */
    void removeFighters(Set<AbstractFighter> fighter);

    /**
     * Determines the number of rounds
     *
     * @param rounds number of rounds which should be fight
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
