package de.twins.gladiator.process;

import de.twins.gladiator.domain.Ortable;

public class CollissionProcessImpl implements CollissionProcess {


    @Override
    public boolean doCollide(Ortable one, Ortable two) {
        return one.doCollide(two);
    }


}
