package de.twins.ui;

import de.twins.arena.domain.Arena;
import de.twins.arena.process.ArenaProcess;
import de.twins.arena.process.ArenaProcessImpl;
import de.twins.enemy.domain.Minion;
import de.twins.equipment.domain.IsWeapon;
import de.twins.equipment.domain.RangeWeapon;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.gladiator.domain.Gladiator;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Updates and renders all game objects
 *
 * @author Pierre
 */
public class GameObjectHandler {

    private List<GameObject> gameObjects = new ArrayList<>();
    private Arena arena;
    private ArenaProcess arenaProcess = new ArenaProcessImpl();


    public void tick() {
        arena = arenaProcess.tick(arena);
    }



    public void render(Graphics g) {
        renderArena();
        for (GameObject gameObject : getGameObjects()) {
            gameObject.render(g);
        }
    }

    private void renderArena() {
        if (arena != null) {
            gameObjects.clear();
            List<AbstractFighter> abstractFighters = arena.getAbstractFighters();
            for (AbstractFighter abstractFighter : abstractFighters) {
                if (abstractFighter instanceof Gladiator) {
                    Gladiator gladiator = (Gladiator) abstractFighter;
                    FighterUI fighterUI = new FighterUI(PlayerType.PLAYER, gladiator);
                    gameObjects.add(fighterUI);
                } else if (abstractFighter instanceof Minion) {
                    MinionUI minionUI = new MinionUI(PlayerType.ENEMY, ((Minion) abstractFighter));
                    gameObjects.add(minionUI);
                }
            }

            arena.getObstacles().forEach(obstacle -> gameObjects.add(new ObstacleUI(obstacle)) );
            //TODO UI für Projektile generisch gestalten
            arena.getArrows().forEach(arrow -> gameObjects.add(new ArrowUI(arrow)));
        }
    }

    public void addObjects(GameObject... objects) {
        addObjects(Arrays.stream(objects).collect(Collectors.toList()));
    }

    public void addObjects(Collection<GameObject> objects) {
        if (objects != null && !objects.isEmpty()) {
            gameObjects.addAll(objects.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }
    }

    public void removeObjects(GameObject... objects) {
        for (GameObject gameObject : objects) {
            getGameObjects().remove(gameObject);
        }
    }

    public List<GameObject> getGameObjects() {
        //ohne Kopie treten permanent ConcurrentModificationErrors auf, sollten weitere GameObjects
        //hinzugefügt werden
        return new ArrayList<>(gameObjects);
    }

    public void doAttackMove(AbstractFighter attacker){
        IsWeapon weapon = attacker.getWeapon();
        if(weapon instanceof RangeWeapon){
            this.arena.shootProjectile(attacker);

        }else{
            //Melee Weapon
        }

    }
    public void setArena(Arena arena) {
        this.arena = arena;
    }
}
