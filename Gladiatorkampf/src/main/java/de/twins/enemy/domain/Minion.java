package de.twins.enemy.domain;

import de.twins.gladiator.domain.AbstractFighter;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import java.math.BigDecimal;
import java.util.Optional;

public class Minion extends AbstractFighter {

    private AbstractFighter target;

    private Strategy strategy = new FollowStrategy(this);

    public Minion(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense) {
        super(name, healthPoints, attack, defense);
        updateStats();
        currentHealthPoints = totalHealthPoints;
    }

    public Minion(String name, Double healthPoints, Double attack, Double defense) {
        this(name,new BigDecimal(healthPoints),new BigDecimal(attack),new BigDecimal(defense));
    }

    public AbstractFighter getTarget() {
        return target;
    }

    public void setTarget(AbstractFighter target) {
        this.target = target;
    }

    public Optional<Strategy> getStrategy() {
        return Optional.ofNullable(strategy);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
