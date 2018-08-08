package de.twins.arena.domain;

import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Ortable;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private List<AbstractFighter> abstractFighters = new ArrayList<>();

    private int width;
    private int heigth;

    public Arena(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    public List<AbstractFighter> getAbstractFighters() {
        return abstractFighters;
    }

    public void setAbstractFighters(List<AbstractFighter> abstractFighters) {
        this.abstractFighters = abstractFighters;
    }

    public void addFighter(AbstractFighter abstractFighter) {
        this.abstractFighters.add(abstractFighter);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }
}
