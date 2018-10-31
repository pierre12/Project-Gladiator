package de.twins.physic;

import de.twins.gladiator.domain.Ortable;

import java.util.List;
import java.util.Optional;

public interface CollissionProcess {

     boolean doCollide(Ortable one, Ortable two);


    /**
     * Ermittelt sämtliche Collissionen, die zwischen den {@link Ortable}s auftreten
     *
     * @param ortableList Liste der zu prüfenden {@link Ortable}
     * @return eine Liste von {@link Collission}
     */
    List<Collission> getCollidingObjects(List<Ortable> ortableList);

    /**
     * Ermittelt eine @{@link Collission}, sollten die ortables miteinander
     * Kollidieren.
     *
     * @param ortable
     * @param ortable2
     * @return ein Optional einer {@link Collission}
     */
    Optional<Collission> determineCollission(Ortable ortable, Ortable ortable2);

    /**
     * Ermittelt sämtliche Collissionen die zwischen ortable und einer Liste
     * von Ortables auftreten.
     *
     * @param ortable
     * @param ortables
     * @return ein Optional einer {@link Collission}
     */
    List<Collission> determineCollissions(Ortable ortable, List<? extends Ortable> ortables);
}
