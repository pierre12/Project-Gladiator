package de.twins.gladiator.domain;

public interface Ortable {

    default boolean doCollide(Ortable ortable){
        if(ortable.equals(this)){
            return false;
        }
        int x = this.getX();
        int y = this.getY();
        double width = this.getWidth();
        double height = this.getHeight();


        boolean outsideBottom = y + height  < ortable.getY();
        boolean outsideTop = y > ortable.getY() + ortable.getHeight();
        boolean outsideLeft = x > ortable.getX() + ortable.getWidth();
        boolean outsideRight = x + width < ortable.getX();
        return !(outsideBottom || outsideTop || outsideLeft || outsideRight);

    };

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    int getXSpeed();

    int getYSpeed();

}
