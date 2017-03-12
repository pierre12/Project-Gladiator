package de.twins.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Updates and renders all game objects
 * @author Pierre
 *
 */
public class GameObjectHandler {

	private List<GameObject> gameObjects = new ArrayList<>();
	
	
	public void tick(){
		for (GameObject gameObject : getGameObjects()) {
			gameObject.tick();
		}
		
	}
	public void render(Graphics g){
		for (GameObject gameObject : getGameObjects()) {
			gameObject.render(g);
		}
	}
	
	public void addObjects(GameObject... objects){
		for (GameObject gameObject : objects) {
			getGameObjects().add(gameObject);
		}
	}
	public void removeObjects(GameObject... objects){
		for (GameObject gameObject : objects) {
			getGameObjects().remove(gameObject);
		}
	}
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
}
