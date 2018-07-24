package de.twins.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyInput extends KeyAdapter {
    public final static char UP = 'w';
    public final static char DOWN = 's';
    public final static char LEFT = 'a';
    public final static char RIGHT = 'd';
    public final static char SHOOT = 'e';
    public static final char SWING = 'q';
    public static final char STAB = 'r';

    private GameObjectHandler handler;

    private List<Character> keysPressed = new ArrayList<>();

    public KeyInput(GameObjectHandler handler) {
        this.handler = handler;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if (!keysPressed.contains(key)) {
            keysPressed.add(key);
        }
        for (GameObject object : handler.getGameObjects()) {
            if (object instanceof AbstractFighterUI) {
                if (((AbstractFighterUI) object).getId() == Player.PLAYER) {
                    setMovement(key, object, 5);
                }
            }
        }
    }


    private Character getOpposite(char key) {
        if (key == UP) {
            return DOWN;
        }
        if (key == DOWN) {
            return UP;
        }
        if (key == LEFT) {
            return RIGHT;
        }
        if (key == RIGHT) {
            return LEFT;
        }

        return null;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if (keysPressed.contains(key)) {
            int indexOfChar = keysPressed.indexOf(key);
            keysPressed.remove(indexOfChar);

            List<GameObject> itemsToAdd = new ArrayList<>();
            for (GameObject object : handler.getGameObjects()) {
                if (object instanceof AbstractFighterUI) {
                    AbstractFighterUI abstractFighter = (AbstractFighterUI) object;
                    if (abstractFighter.getId() == Player.PLAYER) {
                        Character opposite = getOpposite(key);
                        if (!keysPressed.contains(opposite)) {
                            if (isMovement(key)) {
                                setMovement(key, object, 0);
                            } else if (isAction(key)) {
                                GameObject gameObject = doAction(key, abstractFighter);
                                if (gameObject != null) {
                                    itemsToAdd.add(gameObject);
                                }
                            }
                        }
                    }
                }
            }

            handler.addObjects(itemsToAdd);
        }

    }

    private GameObject doAction(char key, AbstractFighterUI object) {
        if (key == SHOOT) {
            return new ArrowUI(object.getX(), object.getY());
        }
        else if(key == SWING){
            return new SwordUI(50,50,object);
        }else if(key == STAB){
            return new LanceUI(60,60,object);
        }
        return null;
    }

    private boolean isAction(char key) {
        return key == SHOOT || key == SWING || key == STAB;
    }

    private boolean isMovement(char key) {
        return key == UP || key == DOWN || key == LEFT || key == RIGHT;
    }

    private void setMovement(int key, GameObject object, int velocity) {
        if (key == UP) {
            object.setVely(-velocity);
        }
        if (key == LEFT) {
            object.setVelx(-velocity);
        }
        if (key == DOWN) {
            object.setVely(velocity);
        }
        if (key == RIGHT) {
            object.setVelx(velocity);
        }
    }
}
