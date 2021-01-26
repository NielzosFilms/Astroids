package game.objects;

import game.enums.ID;
import game.handlers.GameObject;
import game.system.Game;
import game.system.Helpers;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Player extends GameObject {
	private final float move_speed = 5f;
	private final float mouse_move_acceleration = 0.01f, mouse_move_deceleration = 0.08f;
	private final float max_vel = 30f;

	public Player(int x, int y) {
		super(x, y, ID.player);
	}

	@Override
	public void tick() {
		Point mouse = new Point(game.mouseInput.getMX(), game.mouseInput.getMY());
		if(!game.mouseInput.isMouseDown(MouseEvent.BUTTON3)) {
			velX += (mouse.x - x) * mouse_move_acceleration;
			velY += (mouse.y - y) * mouse_move_acceleration;

			velX -= (velX) * mouse_move_deceleration;
			velY -= (velY) * mouse_move_deceleration;
		}

		velX = Helpers.clampFloat(velX, -max_vel, max_vel);
		velY = Helpers.clampFloat(velY, -max_vel, max_vel);

		x += velX;
		y += velY;

		/*if(game.keyInput.isKeyDown(KeyEvent.VK_W)) {
			velY = -move_speed;
		}
		if(game.keyInput.isKeyDown(KeyEvent.VK_S)) {
			velY = move_speed;
		}
		if(game.keyInput.isKeyDown(KeyEvent.VK_A)) {
			velX = -move_speed;
		}
		if(game.keyInput.isKeyDown(KeyEvent.VK_D)) {
			velX = move_speed;
		}
		if(!game.keyInput.isKeyDown(KeyEvent.VK_W) && !game.keyInput.isKeyDown(KeyEvent.VK_S)) {
			velY = 0;
		}
		if(!game.keyInput.isKeyDown(KeyEvent.VK_A) && !game.keyInput.isKeyDown(KeyEvent.VK_D)) {
			velX = 0;
		}*/
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x-Game.TILESIZE/2, y-Game.TILESIZE/2, Game.TILESIZE, Game.TILESIZE);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x-Game.TILESIZE/2, y-Game.TILESIZE/2, Game.TILESIZE, Game.TILESIZE);
	}
}
