package de.twins.gladiator.process;

import java.math.BigDecimal;

import de.twins.enemy.domain.Minion;

public class MinionFactoryImpl implements EnemyFactory {

	@Override
	public Minion createMinion() {
		BigDecimal healthPoints = new BigDecimal(10);
		BigDecimal attack = new BigDecimal(10);
		BigDecimal defense = new BigDecimal(10);
		Minion minion = new Minion("Minion", healthPoints, healthPoints, healthPoints);
		return minion;
	}


}
