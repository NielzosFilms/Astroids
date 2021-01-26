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
	public static final int TILESIZE = 32;

	private Thread thread;
	private boolean running = true;
	public static int current_fps = 0;
	public static int current_ticks = 0;

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

		long fps_lastTime = System.nanoTime();
		double fps_cap = 200.0;
		double fps_ns = 1000000000 / fps_cap;
		double fps_delta = 0;
		int ticks = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}

			long fps_now = System.nanoTime();
			fps_delta += (fps_now - fps_lastTime) / fps_ns;
			fps_lastTime = fps_now;
			if(fps_delta >= 1) {
				if (running)
					render();
				frames++;
				fps_delta = 0;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				current_fps = frames;
				current_ticks = ticks;
				frames = 0;
				ticks = 0;
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

		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("fps:" + current_fps, 10, 10);
		g.drawString("ticks:" + current_ticks, 10, 20);

		bs.show();
		g.dispose();
		g2d.dispose();
	}

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "True");
		new Game();
	}
}
