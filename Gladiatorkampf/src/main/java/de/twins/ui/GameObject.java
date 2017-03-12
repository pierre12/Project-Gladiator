package de.twins.ui;

public abstract class GameObject {
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public GameObject(int x, int y, int velx, int vely) {
		super();
		this.x = x;
		this.y = y;
		this.velx = velx;
		this.vely = vely;
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
}
