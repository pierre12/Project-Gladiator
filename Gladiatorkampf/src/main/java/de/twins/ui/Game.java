package de.twins.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.math.BigDecimal;

import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.Gladiator;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 16 * 9;
	private Thread thread;
	private boolean running = false;
	private GameObjectHandler handler;
	private HUD hud;

	public Game() {
		handler = new GameObjectHandler();
		FighterUI fighter = new FighterUI(Player.PLAYER,new Gladiator("CONAN",new BigDecimal(10000),new BigDecimal(10000),new BigDecimal(10000),null), 100, 100);
		MinionUI dummyFighter = new MinionUI(Player.ENEMY,new Minion("CONAN",new BigDecimal(10000),new BigDecimal(10000),new BigDecimal(10000)), 200, 100);
		hud = new HUD(fighter);
		handler.addObjects(dummyFighter,fighter);
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
				hud.tick();
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
			hud.render(g);
			handler.render(g);
			g.dispose();
			bs.show();
	}

	private void tick() {
		handler.tick();
	}
}
