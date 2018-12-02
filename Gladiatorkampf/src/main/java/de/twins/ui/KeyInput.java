package de.twins.ui;

import de.twins.gladiator.domain.AbstractFighter;
import de.twins.util.DegreeHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class KeyInput extends KeyAdapter implements MouseMotionListener {


    private final ControlOption controlOption;
    private GameObjectHandler handler;

    private List<Character> keysPressed = new ArrayList<>();
    private Point mousePosition;

    public KeyInput(GameObjectHandler handler) {

        this.handler = handler;
        this.controlOption = new ControlOption();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if (!keysPressed.contains(key)) {
            keysPressed.add(key);
        }
        for (GameObject object : handler.getGameObjects()) {
            if (object instanceof AbstractFighterUI) {
                if (((AbstractFighterUI) object).getPlayerType() == PlayerType.PLAYER) {
                    setMovement(key, ((AbstractFighterUI) object), 20);
                }
            }
        }
    }


    private Character getOpposite(char key) {
        if (key == controlOption.getUp()) {
            return controlOption.getDown();
        }
        if (key == controlOption.getDown()) {
            return controlOption.getUp();
        }
        if (key == controlOption.getLeft()) {
            return controlOption.getRight();
        }
        if (key == controlOption.getRight()) {
            return controlOption.getLeft();
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
                    if (abstractFighter.getPlayerType() == PlayerType.PLAYER) {
                        updateWatchDirection(abstractFighter);
                        handlePlayerInput(key, itemsToAdd, abstractFighter);
                    }
                }
            }
            handler.addObjects(itemsToAdd);
        }
    }

    private void updateWatchDirection(AbstractFighterUI abstractFighterUI) {

        AbstractFighter fighter = abstractFighterUI.getFighter();
        int x = fighter.getX();
        int y = fighter.getY();

        double mouseX = mousePosition.getX();
        double mouseY = mousePosition.getY();

        double xRelativeToPlayer = mouseX - x;
        double yRelativeToPlayer = mouseY - y;

        System.out.println(String.format("x %s y %s",xRelativeToPlayer,yRelativeToPlayer));

        double watchDirection = DegreeHelper.calculateDegree(xRelativeToPlayer, yRelativeToPlayer);
        System.out.println("Degree " + watchDirection);
        fighter.setWatchDirectionInDegree(watchDirection);
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
        if (key == controlOption.getAttack()) {
            this.handler.doAttackMove(object.getFighter());
        }
        return null;
    }

    private boolean isAction(char key) {
        return key == controlOption.getAttack();
    }

    private boolean isMovement(char key) {
        return key == controlOption.getUp() || key == controlOption.getDown()|| key == controlOption.getLeft()|| key == controlOption.getRight();
    }

    private void setMovement(int key, AbstractFighterUI object, int velocity) {
        AbstractFighter fighter = object.getFighter();
        if (key == controlOption.getUp()) {
            fighter.setYSpeed(-velocity);
        }
        if (key == controlOption.getLeft()) {
            fighter.setXSpeed(-velocity);
        }
        if (key == controlOption.getDown()) {
            fighter.setYSpeed(velocity);
        }
        if (key == controlOption.getRight()) {
            fighter.setXSpeed(velocity);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mousePosition = e.getPoint();
    }
}
