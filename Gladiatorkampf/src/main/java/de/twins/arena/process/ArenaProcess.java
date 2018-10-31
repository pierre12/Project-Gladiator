package de.twins.arena.process;

import de.twins.arena.domain.Arena;
import de.twins.enemy.domain.Minion;
import de.twins.enemy.domain.Strategy;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.physic.CollissionProcess;
import de.twins.physic.CollissionProcessImpl;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface ArenaProcess {

    /**
     * TODO Rene BEschreibung
     * @param arena
     * @return
     */
    Arena tick(Arena arena);
}
