package de.twins.ui;

import java.awt.*;

public interface UIObject {

    void setX(int x);

    int getX();

    void setY(int y);

    int getY();

    int getVelx();

    void setVelx(int velx);

    int getVely();

    void setVely(int vely);

    void tick();

    void render(Graphics g);


}
