package de.twins.physic;

import de.twins.gladiator.domain.DummyOrtable;
import de.twins.gladiator.domain.Ortable;
import de.twins.gladiator.domain.Position;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CollissionProcessImpl implements CollissionProcess {


    @Override
    public boolean doCollide(Ortable one, Ortable two) {
        return one.doCollide(two);
    }


    @Override
    public List<Collission> getCollidingObjects(List<Ortable> ortableList) {
        if (!CollectionUtils.isEmpty(ortableList)) {
            return ortableList.stream().map(ortable -> determineCollissions(ortable, ortableList)).flatMap(Collection::stream).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Collission> determineCollission(Ortable ortable, Ortable ortable2) {
        if (ortable.doCollide(ortable2)) {
            return Optional.of(new Collission(ortable, ortable2));
        } else {
            return Optional.empty();
        }
    }


    @Override
    public List<Collission> determineCollissions(Ortable one, List<? extends Ortable> ortables) {
        return this.determineCollissions(one,ortables,null);
    }

    @Override
    public List<Collission> determineCollissions(Ortable ortable, List<? extends Ortable> ortables, List<? extends Ortable> ignores) {
        List<? extends Ortable>  saveIgnores= ignores != null?ignores: Collections.emptyList();
        return ortables.stream()
                .filter(it -> !saveIgnores.contains(it))
                .map(it -> this.determineCollission(ortable, it))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Position> determineOptimalPosition(Ortable ortable, List<? extends Ortable> ortables) {
        List<Collission> collissions = this.determineCollissions(ortable, ortables);
        if (!collissions.isEmpty()) {
            int xSpeed = ortable.getXSpeed();
            int ySpeed = ortable.getYSpeed();
            if (xSpeed == 0 && ySpeed == 0) {
                //Ortable is static wird nicht bewegt
                return Optional.empty();
            }
            List<Position> possiblePositions = new ArrayList<>();
            for (Collission collission : collissions) {
                Ortable ortable2 = collission.getOrtable2();
                int optimalY = getOptimalY(ortable, ortable2);
                int optimalX = getOptimalX(ortable, ortable2);

                //both new Values really necessary?
                //try it out
                Ortable ortableAlteredX = new DummyOrtable(optimalX, ortable.getY(), ortable.getWidth(), ortable.getHeight());
                Ortable ortableAlteredY = new DummyOrtable(ortable.getX(), ortable.getX(), ortable.getWidth(), ortable.getHeight());
                if (!ortableAlteredX.doCollide(ortable2)) {
                    possiblePositions.add(new Position(optimalX, ortable.getY()));
                } else if (!ortableAlteredY.doCollide(ortable2)) {
                    possiblePositions.add(new Position(ortable.getX(), optimalY));
                } else {
                    possiblePositions.add(new Position(optimalX, optimalY));
                }
            }
            //Prüfen ob die mögliche Position nicht in einem kolliderenden Ortable kollidiert TODO fehlerquelle prüfen mit mehreren Ortables
            Predicate<Position> isNotInOrtables = position -> ortables.stream().noneMatch(position::isInOrtable);
            return possiblePositions.stream().filter(isNotInOrtables).findFirst();
        } else {
            return Optional.empty();
        }
    }

    public int getOptimalX(Ortable ortable, Ortable ortable2) {
        if (!(ortable.isOutsideLeft(ortable2) || ortable.isOutsideRight(ortable2))) {
            if (ortable.getXSpeed() != 0) {
                int newPositionX;
                if (ortable.getXSpeed() > 0) {
                    newPositionX =  ortable2.getX() - ortable.getWidth();
                } else {
                    newPositionX = ortable2.maxX();
                }
                if(Math.abs(ortable.getX() - newPositionX) > Math.abs(ortable.getXSpeed())){
                    return ortable.getX() - ortable.getXSpeed();
                }else{
                    return  newPositionX;
                }
            }
        }
        return ortable.getX();
    }

    private int getOptimalY(Ortable ortable, Ortable ortable2) {
        if (!(ortable.isOutsideBottom(ortable2) || ortable.isOutsideTop(ortable2))) {
            if (ortable.getYSpeed() != 0) {
                int newPositionY;
                if (ortable.getYSpeed() > 0) {
                    newPositionY = ortable2.getY() - ortable.getHeight();
                } else {
                    newPositionY = ortable2.maxY();
                }

                if(Math.abs(ortable.getY() - newPositionY) > Math.abs(ortable.getYSpeed())){
                    return ortable.getY() - ortable.getYSpeed();
                }else{
                    return  newPositionY;
                }
            }
        }
        return ortable.getY();
    }


}
