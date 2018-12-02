package de.twins.ui;

import de.twins.gladiator.domain.AbstractFighter;

public abstract class AbstractFighterUI extends GameObject {

    private PlayerType playerType;
    private AbstractFighter fighter;

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }


    public AbstractFighterUI(PlayerType id, AbstractFighter fighter, int x, int y) {
        super(x, y);
        this.playerType = id;
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
