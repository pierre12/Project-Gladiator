package de.twins.ui;

import java.awt.Graphics;

import de.twins.gladiator.domain.AbstractFighter;

public abstract class GameObject {
	private int x;
	private int y;
	private Player id;
	private AbstractFighter fighter;
	public Player getId() {
		return id;
	}

	public void setId(Player id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public GameObject(Player id,AbstractFighter fighter,int x, int y) {
		super();
		this.id = id;
		this.fighter = fighter;
		this.x = x;
		this.y = y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelx() {
		return velx;
	}

	public void setVelx(int velx) {
		this.velx = velx;
	}

	public int getVely() {
		return vely;
	}

	public void setVely(int vely) {
		this.vely = vely;
	}

	private int velx;
	private int vely;
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public AbstractFighter getFighter() {
		return fighter;
	}

	public void setFighter(AbstractFighter fighter) {
		this.fighter = fighter;
	}
	
}
