package de.twins.arena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.twins.gladiator.domain.Gladiator;

/**
 * Arena limited for two gladiators which fight 1 vs 1 against each other.
 * 
 * @author Pierre
 *
 */
public class OneOnOneArena implements Arena {

	private Set<Gladiator> gladiators;

	public OneOnOneArena() {
		gladiators = new HashSet();
	}

	public OneOnOneArena(Set<Gladiator> gladiators) {
		this.gladiators = gladiators;

	}

	@Override
	public void announceWinner() {
		for (Gladiator gladiator : gladiators) {
			if (gladiator.isAlive()) {
				System.out.println("Gladiator" + gladiator.getName() + "won the fight.");
			}
		}

	}

	@Override
	public void startFight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startRound() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endRound() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endFight() {

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
	public void removeGladiator(Gladiator gladiator) {
		gladiators.remove(gladiator);
	}

	@Override
	public void removeGladiators(Set<Gladiator> gladiators) {
		for (Gladiator gladiator : gladiators) {
			this.gladiators.remove(gladiator);
		}

	}

}
