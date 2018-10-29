package de.twins.ui;

import de.twins.enemy.domain.Minion;

import java.awt.*;

public class MinionUI extends AbstractFighterUI {


    public MinionUI(Player id, Minion minion, int x, int y) {
        super(id, minion, x, y);
        setVely(5);
        setVelx(5);
    }

    public MinionUI(Player id, Minion minion) {
        super(id, minion, minion.getX(), minion.getY());
        setVely(5);
        setVelx(5);
    }

    @Override
    public void tick() {
        setX(getX() + getVelx());
        setY(getY() + getVely());
        if (getY() <= 0 || getY() >= Game.HEIGHT - 45) {
            setVely(getVely() * -1);
        }

        if (getX() <= 0 || getX() >= Game.WIDTH - 10) {
            setVelx(getVelx() * -1);
        }
    }

    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.RED);
        //draw body
        g.fillRect(getX(), getY(), width, height);
        //arms
        g.fillRect(getX() + width, getY(), width / 5, height / 5);
        g.fillRect(getX() - 2, getY(), width / 5, height / 5);
        //legs
        g.fillRect(getX(), getY() + height, width / 5, height / 2);
        g.fillRect(getX() + width - 2, getY() + height, width / 5, height / 2);
        //head
        g.fillRect(getX() + (width / 4), getY() - 5, width / 2, height / 2);
    }

}
