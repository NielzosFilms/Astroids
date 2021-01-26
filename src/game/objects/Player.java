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
	private final float mouse_move_acceleration = 0.08f, mouse_move_deceleration = 0.03f, move_decel = 0.01f;
	private final float max_vel = 30f;
	private final int shoot_cooldown = 3;

	private int shoot_timer = shoot_cooldown;

	public Player(int x, int y) {
		super(x, y, ID.player);
	}

	@Override
	public void tick() {
		doMovement();
		shootingCheck();
	}

	private void doMovement() {
		Point mouse = new Point(game.mouseInput.getMX(), game.mouseInput.getMY());
		int angle = Helpers.getAngle(new Point(x, y), mouse);
		if(!game.mouseInput.isMouseDown(MouseEvent.BUTTON3)) {
			float targ_velX = (float) (max_vel*Math.cos(Math.toRadians(angle)));
			float targ_velY = (float) (max_vel*Math.sin(Math.toRadians(angle)));
			velX += (targ_velX - velX) * mouse_move_acceleration;
			velY += (targ_velY - velY) * mouse_move_acceleration;

			velX -= (velX) * mouse_move_deceleration;
			velY -= (velY) * mouse_move_deceleration;
		}

		if(velX > -0.1f && velX < 0.1f) {
			velX = 0;
		}
		if(velY > -0.1f && velY < 0.1f) {
			velY = 0;
		}
		velX -= (velX) * move_decel;
		velY -= (velY) * move_decel;

		velX = Helpers.clampFloat(velX, -max_vel, max_vel);
		velY = Helpers.clampFloat(velY, -max_vel, max_vel);

		x += velX;
		y += velY;
	}

	private void shootingCheck() {
		if(shoot_timer <= 0) {
			if(game.mouseInput.isMouseDown(MouseEvent.BUTTON1)) {
				shoot();
				shoot_timer = shoot_cooldown;
			}
		} else shoot_timer--;
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

	private void shoot() {
		Point mouse = new Point(game.mouseInput.getMX(), game.mouseInput.getMY());
		game.handler.addObject(new Bullet(x, y, Helpers.getAngle(new Point(x, y), mouse)));
	}
}
