package de.twins.physic;

import de.twins.gladiator.domain.Ortable;
import de.twins.gladiator.domain.Position;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CollissionProcessImpl implements CollissionProcess {


    @Override
    public boolean doCollide(Ortable one, Ortable two) {
        return one.doCollide(two);
    }


    @Override
    public List<Collission> getCollidingObjects(List<Ortable> ortableList) {
        if(!CollectionUtils.isEmpty(ortableList)){
            return ortableList.stream().map(ortable -> determineCollissions(ortable, ortableList)).flatMap(Collection::stream).collect(Collectors.toList());
        }else{
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
        return ortables.stream().map(ortable -> this.determineCollission(one, ortable))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Position> determineOptimalPosition(Ortable ortable, List<? extends Ortable> ortables) {
        List<Collission> collissions = this.determineCollissions(ortable, ortables);
        if(!collissions.isEmpty()){
            int xSpeed = ortable.getXSpeed();
            int ySpeed = ortable.getYSpeed();
            if(xSpeed == 0 && ySpeed == 0){
                //Ortable is static wird nicht bewegt
                return Optional.empty();
            }
            List<Position> possiblePositions = new ArrayList<>();
            for (Collission collission : collissions) {
                Ortable ortable2 = collission.getOrtable2();
                int y = getOptimalY(ortable, ortable2);
                int x = getOptimalX(ortable, ortable2);
                possiblePositions.add(new Position(x,y));
            }
            //Prüfen ob die mögliche nicht in einem kolliderenden Ortable ist
            Predicate<Position> isNotInOrtables = position -> ortables.stream().noneMatch(position::isInOrtable);
            return possiblePositions.stream().filter(isNotInOrtables).findFirst();
        }else{
            return Optional.empty();
        }
    }

    public int getOptimalX(Ortable ortable, Ortable ortable2) {
        if(!(ortable.isOutsideLeft(ortable2) || ortable.isOutsideRight(ortable2))){
            if(ortable.getXSpeed() != 0){
                if(ortable.getXSpeed() > 0){
                    return ortable2.getX() - ortable.getWidth();
                }else{
                    return ortable2.maxX();
                }
            }
        }
        return ortable.getX();
    }

    private int getOptimalY(Ortable ortable, Ortable ortable2) {
     if(!(ortable.isOutsideBottom(ortable2) || ortable.isOutsideTop(ortable2))){
            if(ortable.getYSpeed() != 0){
                if(ortable.getYSpeed() > 0){
                    return ortable2.getY() - ortable.getHeight();
                }else{
                    return ortable2.maxY();
                }
            }
        }
        return ortable.getY();
    }


}
