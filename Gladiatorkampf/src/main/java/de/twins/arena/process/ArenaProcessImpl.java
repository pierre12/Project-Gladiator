package de.twins.arena.process;

import de.twins.arena.domain.Arena;
import de.twins.arena.domain.Obstacle;
import de.twins.enemy.domain.Minion;
import de.twins.enemy.domain.Strategy;
import de.twins.equipment.domain.IsWeapon;
import de.twins.equipment.domain.Projectile;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.equipment.domain.Arrow;
import de.twins.gladiator.domain.Ortable;
import de.twins.gladiator.domain.Position;
import de.twins.physic.Collission;
import de.twins.physic.CollissionProcess;
import de.twins.physic.CollissionProcessImpl;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArenaProcessImpl implements ArenaProcess {
    private CollissionProcess collissionProcess = new CollissionProcessImpl();


    public Arena tick(Arena arena) {
        List<AbstractFighter> abstractFighters = arena.getAbstractFighters();
        Set<Minion> minions = extractMinions(abstractFighters);
        this.executeStrategy(minions);
        this.moveArrows(arena);
        this.moveFighters(arena);
        return arena;
    }

    private void moveArrows(Arena arena) {
        List<Obstacle> obstacles = arena.getObstacles();

        Iterator<Projectile> iterator = arena.getArrows().iterator();
        while (iterator.hasNext()) {
            Projectile arrow = iterator.next();
            arrow.setX(arrow.getX() + arrow.getXSpeed());
            arrow.setY(arrow.getY() + arrow.getYSpeed());
            List<Collission> collissionsWithArrows = this.collissionProcess.determineCollissions(arrow, obstacles);
            if (!collissionsWithArrows.isEmpty()) {
                iterator.remove();
            }

            List<AbstractFighter> abstractFighters = arena.getAbstractFighters();
            List<Collission> arrowsHit = collissionProcess.determineCollissions(arrow, abstractFighters, Arrays.asList(arrow.getSource().getOwner()));
            if (!arrowsHit.isEmpty()) {
                Collission collission = arrowsHit.get(0);
                AbstractFighter abstractFighter = (AbstractFighter) collission.getOrtable2();
                doDamage(arrow, abstractFighter);
                iterator.remove();
            }
        }
    }

    private Set<Minion> extractMinions(List<AbstractFighter> abstractFighters) {
        Predicate<AbstractFighter> isMinion = fighter -> fighter instanceof Minion;
        return abstractFighters.stream()
                .filter(isMinion)
                .filter(Objects::nonNull)
                .map(minion -> (Minion) minion)
                .collect(Collectors.toSet());
    }

    private void moveFighters(Arena arena) {
        List<AbstractFighter> abstractFighters = arena.getAbstractFighters();
        for (AbstractFighter abstractFighter : abstractFighters) {
            int xSpeed = abstractFighter.getXSpeed();
            int ySpeed = abstractFighter.getYSpeed();

            int oldX = abstractFighter.getX();
            int oldY = abstractFighter.getY();
            int width = abstractFighter.getWidth();
            int height = abstractFighter.getHeight();
            int newX = oldX + xSpeed;
            int newY = oldY + ySpeed;

            int arenaHeigth = arena.getHeigth();
            int arenaWidth = arena.getWidth();

            if (newX + width <= arenaWidth && newX >= 10) {
                abstractFighter.setX(newX);
            }
            if (newX < 10) {
                abstractFighter.setX(10);
            }
            if (newX + width >= arenaWidth) {
                abstractFighter.setX(arenaWidth - width);
            }

            if (newY < arenaHeigth && newX >= 10) {
                abstractFighter.setY(newY);
            }

            if (newY < 10) {
                abstractFighter.setY(10);
            }

            if (newY + height > arenaHeigth) {
                abstractFighter.setY(arenaHeigth - height);
            }

            if (!collissionProcess.determineCollissions(abstractFighter, abstractFighters).isEmpty()) {
                Optional<Position> position = collissionProcess.determineOptimalPosition(abstractFighter, abstractFighters);
                if (position.isPresent()) {
                    abstractFighter.setY(position.get().getY());
                    abstractFighter.setX(position.get().getX());
                }
            }

            if (!collissionProcess.determineCollissions(abstractFighter, arena.getObstacles()).isEmpty()) {
                Optional<Position> position = collissionProcess.determineOptimalPosition(abstractFighter, arena.getObstacles());
                if (position.isPresent()) {
                    System.out.println(position);
                    abstractFighter.setY(position.get().getY());
                    abstractFighter.setX(position.get().getX());
                }
            }
        }
    }

    private void doDamage(IsWeapon weapon, AbstractFighter abstractFighter) {
        abstractFighter.setHeight(abstractFighter.getHeight() - 1);
    }

    private void executeStrategy(Set<Minion> minions) {
        for (Minion minion : minions) {
            minion.getStrategy().ifPresent(Strategy::execute);
        }
    }

}
