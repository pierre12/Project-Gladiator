package de.twins.ui;

import de.twins.arena.domain.Arena;
import de.twins.arena.domain.Obstacle;
import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.Gladiator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 16 * 9;
    private Thread thread;
    private boolean running = false;
    private GameObjectHandler handler;
    private HUD hud;

    public Game() {
        Arena arena = new Arena(WIDTH - 20, HEIGHT - 50);

        Gladiator conan = new Gladiator("CONAN", new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000), null);
        conan.setHeight(10);
        conan.setWidth(10);
        conan.setXSpeed(1);
        conan.setX(100);
        conan.setY(100);
        arena.addFighter(conan);
        Minion snake = new Minion("Snake", new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000));
        snake.setHeight(30);
        snake.setWidth(30);
        snake.setYSpeed(2);
        snake.setTarget(conan);
        arena.addFighter(snake);
        arena.addObstacle(new Obstacle(50,50,30,30));

        handler = new GameObjectHandler();
        handler.setArena(arena);
        addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "Working title: Gladiators", this);
    }

    public static void main(String[] args) {
        new Game();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                if (hud != null) {
                    hud.tick();
                }
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            // Prints FPS every second
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
        stop();
    }

    private void render() {
        // important creates buffer which paints the picture in whole before
        // showing it in UI.
        // Otherwise the image would flicker
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        //load background
        File file = new File(ImagePaths.GRASS_UNDERGROUND);
        try {
            BufferedImage read = ImageIO.read(file);
            Graphics g = bs.getDrawGraphics();
            g.drawImage(read,0,0, WIDTH, HEIGHT, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
            if (hud != null) {
                hud.render(g);
            }
            handler.render(g);
            g.dispose();
            bs.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void tick() {
        handler.tick();
    }
}
