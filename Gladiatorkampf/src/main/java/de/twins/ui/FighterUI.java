package de.twins.ui;


import java.awt.Color;
import java.awt.Graphics;

import de.twins.gladiator.domain.AbstractFighter;

/**
 * UI reprensentation of a fighter.
 * @author Pierre
 *
 */
public class FighterUI extends AbstractFighterUI {

	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;

	public FighterUI(Player id,AbstractFighter fighter, int x, int y) {
		super(id, fighter, x, y);
	}

	@Override
	public void tick() {
		System.out.println("velx: "+(getX()+ getVelx()));
		setX(getX()+ getVelx());
		System.out.println("vely: "+(getY()+ getVely()));
		setY(getY()+ getVely());
	}

	@Override
	public void render(Graphics g) {
		if(getId() == Player.PLAYER){
			g.setColor(Color.WHITE);
		}
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
