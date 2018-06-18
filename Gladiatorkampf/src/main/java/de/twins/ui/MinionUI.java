package de.twins.ui;

import java.awt.Color;
import java.awt.Graphics;

import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.AbstractFighter;

public class MinionUI extends AbstractFighterUI {

	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;

	public MinionUI(Player id,Minion minion, int x, int y) {
		super(id,minion, x, y);
		setVely(5);
		setVelx(5);
	}

	@Override
	public void tick() {
		setX(getX()+getVelx());
		setY(getY()+getVely());
		if(getY() <= 0 ||getY() >= Game.HEIGHT-45){
			setVely(getVely() *-1);
		}

		if(getX() <= 0 ||getX() >= Game.WIDTH-10){
			setVelx(getVelx() *-1);
		}
	}

	@Override
	public void render(Graphics g) {
			g.setColor(Color.RED);
		//draw body
		g.fillRect(getX(), getY(), WIDTH, HEIGHT);
		//arms
		g.fillRect(getX()+WIDTH, getY(), WIDTH/5, HEIGHT/5);
		g.fillRect(getX()-2, getY(), WIDTH/5, HEIGHT/5);
		//legs
		g.fillRect(getX(), getY()+HEIGHT, WIDTH/5, HEIGHT/2);
		g.fillRect(getX()+WIDTH-2, getY()+HEIGHT, WIDTH/5, HEIGHT/2);
		//head
		g.fillRect(getX()+(WIDTH/4), getY()-5, WIDTH/2, HEIGHT/2);
	}		

}
