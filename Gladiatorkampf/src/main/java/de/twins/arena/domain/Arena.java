package de.twins.arena.domain;

import de.twins.equipment.domain.IsWeapon;
import de.twins.equipment.domain.Projectile;
import de.twins.equipment.domain.RangeWeapon;
import de.twins.gladiator.domain.AbstractFighter;
import de.twins.equipment.domain.Arrow;

import java.util.ArrayList;
import java.util.List;

/**
 * Welt-Objekt für {@link Obstacle} und {@link AbstractFighter}
 */
public class Arena {

    private List<AbstractFighter> abstractFighters = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();
    private int width;
    private int heigth;
    private List<Projectile> projectiles = new ArrayList<>();

    public Arena(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    public List<AbstractFighter> getAbstractFighters() {
        return abstractFighters;
    }

    public void setAbstractFighters(List<AbstractFighter> abstractFighters) {
        this.abstractFighters = abstractFighters;
    }

    public void addFighter(AbstractFighter abstractFighter) {
        this.abstractFighters.add(abstractFighter);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void addObstacle(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    public List<Projectile> getArrows() {
        return projectiles;
    }

    public void shootProjectile(AbstractFighter abstractFighter) {
        IsWeapon rangeWeapon = abstractFighter.getWeapon();
        if(rangeWeapon instanceof RangeWeapon){
            Projectile projectile = ((RangeWeapon) rangeWeapon).createProjectile();
            this.projectiles.add(projectile);
        }
        //Keine RangeWeapon es wird nicht gefeuert.
    }

    public synchronized void removeArrow(Arrow arrow) {
        if(this.projectiles != null){
            this.projectiles.remove(arrow);
        }
    }

    public void addArrow(Arrow arrow) {
        if(arrow != null){
            this.projectiles.add(arrow);
        }
    }

}
