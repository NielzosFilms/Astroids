package game.objects;

import game.enums.ID;
import game.handlers.GameObject;
import game.system.Game;

import java.awt.*;

public class Bullet extends GameObject {
	private static final int size = Game.TILESIZE/2;
	private static final float bullet_speed = 40f;
	private int lifetime = 60*5;
	private int travel_angle;
	public Bullet(int x, int y, int travel_angle) {
		super(x, y, ID.player_bullet);
		this.travel_angle = travel_angle;
		velX = (float) (bullet_speed*Math.cos(Math.toRadians(travel_angle)));
		velY = (float) (bullet_speed*Math.sin(Math.toRadians(travel_angle)));
	}

	@Override
	public void tick() {
		/*if(x < 0 || y < 0 || x > Game.SCREEN_WIDTH || y > Game.SCREEN_HEIGHT) {
			game.handler.removeObject(this);
		}*/
		if(lifetime <= 0) {
			game.handler.removeObject(this);
		} else lifetime--;
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x-size/2, y-size/2, size, size);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x-size/2, y-size/2, size, size);
	}
}
