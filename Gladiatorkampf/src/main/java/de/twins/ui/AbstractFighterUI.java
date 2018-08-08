package de.twins.ui;

import de.twins.gladiator.domain.AbstractFighter;

public abstract class AbstractFighterUI extends GameObject {

    private Player id;
    private AbstractFighter fighter;

    public Player getId() {
        return id;
    }

    public void setId(Player id) {
        this.id = id;
    }


    public AbstractFighterUI(Player id, AbstractFighter fighter, int x, int y) {
        super(x, y);
        this.id = id;
        this.fighter = fighter;
        this.width = fighter.getWidth();
        this.height = fighter.getHeight();
    }

    public AbstractFighter getFighter() {
        return fighter;
    }

    public void setFighter(AbstractFighter fighter) {
        this.fighter = fighter;
    }
}
