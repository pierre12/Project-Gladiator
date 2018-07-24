package de.twins.gladiator.domain;

public interface Ortable {

    default boolean doCollide(Ortable ortable) {
        double x = this.getX();
        double y = this.getY();
        double width = this.getWidth();
        double height = this.getHeight();


        boolean outsideBottom = y + height < ortable.getY();
        boolean outsideTop = y > ortable.getY() + ortable.getHeight();
        boolean outsideLeft = x > ortable.getX() + ortable.getWidth();
        boolean outsideRight = x + width < ortable.getX();
        return !(outsideBottom || outsideTop || outsideLeft || outsideRight);

    }

    ;

    double getX();

    double getY();

    double getWidth();

    double getHeight();

}
