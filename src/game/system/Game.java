package game.system;

import game.enums.SoundEffect;
import game.handlers.GameController;
import game.handlers.ObjectHandler;
import game.system.input.KeyInput;
import game.system.input.MouseInput;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	public static final int SCREEN_WIDTH = 1200, SCREEN_HEIGHT = 800;
	public static final String TITLE = "Astroids | NielzosFilms";

	private Thread thread;
	private boolean running = true;
	public static int current_fps = 0;

	public KeyInput keyInput = new KeyInput(this);
	public MouseInput mouseInput = new MouseInput(this);

	public ObjectHandler handler = new ObjectHandler(this);
	public GameController controller = new GameController(this);

	public Game() {
		SoundEffect.init();
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		new Window(SCREEN_WIDTH, SCREEN_HEIGHT, TITLE, this);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
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
		this.requestFocus();
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
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				current_fps = frames;
				frames = 0;
			}
		}
		stop();
	}

	public void tick() {
		handler.tick();
		controller.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(new Color(24, 20, 37));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		handler.render(g);

		g.dispose();
		g2d.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "True");
		new Game();
	}
}
