package de.twins.ui;

import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.Gladiator;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.math.BigDecimal;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 16 * 9;
    private Thread thread;
    private boolean running = false;
    private GameObjectHandler handler;
    private HUD hud;

    public Game() {

        Gladiator conan = new Gladiator("CONAN", new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000), null);
        conan.setHeight(10);
        conan.setWidth(10);
        conan.setXSpeed(3);
        conan.setX(100);
        conan.setY(100);
        Minion snake = new Minion("Snake", new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000));
        snake.setHeight(30);
        snake.setWidth(30);
        snake.setYSpeed(2);
        snake.setTarget(conan);
        handler = new GameObjectHandler();
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
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (hud != null) {
            hud.render(g);
        }
        handler.render(g);
        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
    }
}
