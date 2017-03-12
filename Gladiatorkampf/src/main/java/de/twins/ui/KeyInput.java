package de.twins.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private GameObjectHandler handler;

	public KeyInput(GameObjectHandler handler) {
		this.handler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = (char) e.getKeyCode();
		for (GameObject object : handler.getGameObjects()) {
			if(object.getId() == Player.PLAYER){
				setMovement(key, object, 1);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key =  e.getKeyCode();
		for (GameObject object : handler.getGameObjects()) {
			if(object.getId() == Player.PLAYER){
			setMovement(key, object,0);
			}
		}
	}

	private void setMovement(int key, GameObject object,int velocity) {
		if (key == KeyEvent.VK_W) {
			object.setVely(-velocity);
		}
		if (key == KeyEvent.VK_A) {
			object.setVelx(-velocity);
		}
		if (key ==  KeyEvent.VK_S) {
			object.setVely(velocity);
		}
		if (key ==  KeyEvent.VK_D) {
			object.setVelx(velocity);
		}
	}
}
