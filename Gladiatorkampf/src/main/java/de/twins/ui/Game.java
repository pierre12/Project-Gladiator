package de.twins.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 16 * 9;
	private Thread thread;
	private boolean running = false;

	public Game() {
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
		Graphics g = getGraphics();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.dispose();
		setVisible(true);
		
	}

	private void tick() {
	}
}
