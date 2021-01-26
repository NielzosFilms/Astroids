package game.handlers;

import game.enums.ID;
import game.objects.Astroid;
import game.objects.Player;
import game.system.Game;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class GameController {
	private Random r = new Random();
	private Game game;
	public GameController(Game game) {
		this.game = game;
		game.handler.addObject(new Player(Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2));
		for(int i=0; i<10; i++) {
			game.handler.addObject(new Astroid(r.nextInt(Game.SCREEN_WIDTH), r.nextInt(Game.SCREEN_HEIGHT)));
		}
	}

	public void tick() {
		checkBulletCollision();
		checkAstroidCollision();
	}

	private void checkBulletCollision() {
		LinkedList<GameObject> bullets = game.handler.getObjectsByID(ID.player_bullet);
		LinkedList<GameObject> astroids = game.handler.getObjectsByID(ID.astroid);

		for(GameObject bullet : bullets) {
			for(GameObject astroid : astroids) {
				if(bullet.getBounds().intersects(astroid.getBounds())) {
					game.handler.removeObject(astroid);
					game.handler.removeObject(bullet);
				}
			}
		}
	}
	private void checkAstroidCollision() {
		LinkedList<GameObject> astroids = game.handler.getObjectsByID(ID.astroid);
		LinkedList<GameObject> bounced_astroids = new LinkedList<>();

		for(GameObject astroid_1 : astroids) {
			if(bounced_astroids.contains(astroid_1)) continue;
			for(GameObject astroid_2 : astroids) {
				if(astroid_1 == astroid_2) continue;
				if(bounced_astroids.contains(astroid_2)) continue;
				if(astroid_1.getBounds().intersects(astroid_2.getBounds())) {
					System.out.println("bounce astroids");
					float astroid_1_velX = astroid_1.getVelX();
					float astroid_1_velY = astroid_1.getVelY();
					astroid_1.setVelX(astroid_2.getVelX());
					astroid_1.setVelY(astroid_2.getVelY());

					astroid_2.setVelX(astroid_1_velX);
					astroid_2.setVelY(astroid_1_velY);

					bounced_astroids.add(astroid_1);
					bounced_astroids.add(astroid_2);
				}
			}
		}
	}
}
