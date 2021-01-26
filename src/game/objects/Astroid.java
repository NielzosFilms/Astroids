package game.objects;

import game.enums.ID;
import game.handlers.GameObject;
import game.system.Game;

import java.awt.*;
import java.util.Random;

public class Astroid extends GameObject {
	private int travel_speed = 1;
	private int size = Game.TILESIZE*2;
	private double buffer_x = 0, buffer_y = 0;
	public Astroid(int x, int y) {
		super(x, y, ID.astroid);
		velX = new Random().nextFloat()*2-1;
		velY = new Random().nextFloat()*2-1;
	}

	@Override
	public void tick() {
		buffer_x += velX;
		buffer_y += velY;

		int old_x = x;
		int old_y = y;
		x += Math.round(buffer_x);
		y += Math.round(buffer_y);
		if(x != old_x) buffer_x = 0;
		if(y != old_y) buffer_y = 0;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x-size/2, y-size/2, size, size);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x-size/2, y-size/2, size, size);
	}

	public void setVelX(float velX) {
		this.buffer_x = 0;
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.buffer_y = 0;
		this.velY = velY;
	}
}
