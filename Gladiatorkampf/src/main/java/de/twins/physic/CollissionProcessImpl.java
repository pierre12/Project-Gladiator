package de.twins.physic;

import de.twins.gladiator.domain.Ortable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollissionProcessImpl implements CollissionProcess {


    @Override
    public boolean doCollide(Ortable one, Ortable two) {
        return one.doCollide(two);
    }


    @Override
    public List<Collission> getCollidingObjects(List<Ortable> ortableList) {
        if(!CollectionUtils.isEmpty(ortableList)){
            return ortableList.stream().map(ortable -> determineCollissions(ortable, ortableList)).flatMap(collection ->
                    collection.stream()).collect(Collectors.toList());
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
                .filter(opt -> opt.isPresent())
                .map(Optional::get)
                .collect(Collectors.toList());
    }


}
