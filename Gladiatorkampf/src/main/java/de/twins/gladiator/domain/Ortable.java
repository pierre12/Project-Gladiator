package de.twins.gladiator.domain;

public interface Ortable {

    default boolean doCollide(Ortable ortable) {
        if (ortable.equals(this)) {
            return false;
        }


        boolean outsideBottom = isOutsideBottom(ortable);
        boolean outsideTop = isOutsideTop(ortable);
        boolean outsideLeft = isOutsideLeft(ortable);
        boolean outsideRight = isOutsideRight(ortable);
        return !(outsideBottom || outsideTop || outsideLeft || outsideRight);

    }

    default boolean isOutsideRight(Ortable ortable) {
        return this.getX() + this.getWidth() <= ortable.getX();
    }

    default boolean isOutsideLeft(Ortable ortable) {
        return this.getX() >= ortable.getX() + ortable.getWidth();
    }

    default boolean isOutsideTop(Ortable ortable) {
        return this.getY() >= ortable.getY() + ortable.getHeight();
    }

    default boolean isOutsideBottom(Ortable ortable) {
        return this.getY() + this.getHeight() <= ortable.getY();
    }

    default int maxX() {
        return getX() + getWidth();
    }

    default int maxY() {
        return getY() + getHeight();
    }


    int getX();

    int getY();

    int getWidth();

    int getHeight();

    int getXSpeed();

    int getYSpeed();

}
