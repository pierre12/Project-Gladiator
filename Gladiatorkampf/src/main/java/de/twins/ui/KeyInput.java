package de.twins.ui;

import de.twins.gladiator.domain.AbstractFighter;

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
                    setMovement(key, ((AbstractFighterUI) object), 3);
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
                        handlePlayerInput(key, itemsToAdd, abstractFighter);
                    }
                }
            }
            handler.addObjects(itemsToAdd);
        }
    }

    private void handlePlayerInput(char key, List<GameObject> itemsToAdd, AbstractFighterUI abstractFighter) {
        Character opposite = getOpposite(key);
        if (!keysPressed.contains(opposite)) {
            if (isMovement(key)) {
                setMovement(key, abstractFighter, 0);
            } else if (isAction(key)) {
                GameObject gameObject = doAction(key, abstractFighter);
                if (gameObject != null) {
                    itemsToAdd.add(gameObject);
                }
            }
        }
    }

    private GameObject doAction(char key, AbstractFighterUI object) {
        if (key == SHOOT) {
            return new ArrowUI(object.getX(), object.getY());
        } else if (key == SWING) {
            return new SwordUI(50, 50, object);
        } else if (key == STAB) {
            return new LanceUI(60, 60, object);
        }
        return null;
    }

    private boolean isAction(char key) {
        return key == SHOOT || key == SWING || key == STAB;
    }

    private boolean isMovement(char key) {
        return key == UP || key == DOWN || key == LEFT || key == RIGHT;
    }

    private void setMovement(int key, AbstractFighterUI object, int velocity) {
        AbstractFighter fighter = object.getFighter();
        if (key == UP) {
            fighter.setYSpeed(-velocity);
        }
        if (key == LEFT) {
            fighter.setXSpeed(-velocity);
        }
        if (key == DOWN) {
            fighter.setYSpeed(velocity);
        }
        if (key == RIGHT) {
            fighter.setXSpeed(velocity);
        }
    }
}
