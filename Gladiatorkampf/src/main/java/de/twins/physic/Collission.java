package de.twins.physic;

import de.twins.gladiator.domain.Ortable;

/**
 *
 */
public class Collission {

    private Ortable ortable1;

    private Ortable ortable2;

    public Collission(Ortable ortable1, Ortable ortable2) {
        this.ortable1 = ortable1;
        this.ortable2 = ortable2;
    }

    public Ortable getOrtable1() {
        return ortable1;
    }

    public void setOrtable1(Ortable ortable1) {
        this.ortable1 = ortable1;
    }

    public Ortable getOrtable2() {
        return ortable2;
    }

    public void setOrtable2(Ortable ortable2) {
        this.ortable2 = ortable2;
    }


}
