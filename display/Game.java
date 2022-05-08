package display;

import handlers.*;
import states.*;

import java.awt.image.BufferStrategy;
import java.awt.Graphics;

public class Game extends Window implements Runnable {
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private boolean run = false;

	private MouseManager mouseManager;
	public State menuState;
	

	public Game() {
		mouseManager = new MouseManager();
	}

	private void init() {
		display();
		frame.addMouseListener(mouseManager);
		frame.addMouseMotionListener(mouseManager);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);

		menuState = new MenuState();
		State.setState(menuState);
	}

	private void update() {
		if (State.getState() != null) {
			State.getState().update(mouseManager);
		}
	}

	private void draw() {
		bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, 1000, 700);

		if (State.getState() != null) {
			State.getState().draw(g);
		}

		bs.show();
		g.dispose();
	}

	public void run() {
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while (run) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1) {
				update();
				draw();
				delta--;
			}
		}

		stop();
	}

	public synchronized void start() {
		if (run) {
			return;
		}
		run = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!run) {
			return;
		}
		run = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}
}
