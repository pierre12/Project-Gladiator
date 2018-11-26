package de.twins.ui;

import de.twins.arena.domain.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ObstacleUI extends GameObject {


    private final Obstacle obstacle;

    public ObstacleUI( Obstacle obstacle) {
        super(obstacle.getX(), obstacle.getY());
        this.obstacle = obstacle;
    }

    @Override
    public void render(Graphics g) {
        try {
            File file = new File(ImagePaths.STAR_BALL);
            BufferedImage image = ImageIO.read(file);
            g.drawImage(image, obstacle.getX(), obstacle.getY(),obstacle.getWidth(),obstacle.getHeight(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
