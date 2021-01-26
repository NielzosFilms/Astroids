package game.handlers;

import game.enums.ID;
import game.system.Game;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ObjectHandler {
	private LinkedList<GameObject> objects = new LinkedList<>();
	private Game game;

	public ObjectHandler(Game game) {
		this.game = game;
	}

	public void tick() {
		for(int i=0; i<objects.size(); i++) {
			objects.get(i).tick();
		}
		screenWrapObjects();
	}

	public void render(Graphics g) {
		for(int i=0; i<objects.size(); i++) {
			objects.get(i).render(g);
		}
	}

	private void screenWrapObjects() {
		LinkedList<GameObject> screenwrappable_objects = getObjectsByID(ID.player, ID.astroid);
		for(GameObject object : screenwrappable_objects) {
			if(object.getX() < -Game.TILESIZE) {
				object.setX(Game.SCREEN_WIDTH);
			}
			if(object.getX() > Game.SCREEN_WIDTH+Game.TILESIZE) {
				object.setX(0);
			}
			if(object.getY() < -Game.TILESIZE) {
				object.setY(Game.SCREEN_HEIGHT);
			}
			if(object.getY() > Game.SCREEN_HEIGHT+Game.TILESIZE) {
				object.setY(0);
			}
		}
	}

	public LinkedList<GameObject> getObjects() {
		return objects;
	}

	public LinkedList<GameObject> getObjectsByID(ID... ids_args) {
		LinkedList<GameObject> ret = new LinkedList<>();
		List<ID> ids = Arrays.asList(ids_args);

		for(int i=0; i<objects.size(); i++) {
			GameObject tmp = objects.get(i);
			if(ids.contains(tmp.getId())) {
				ret.add(tmp);
			}
		}

		return ret;
	}

	public void addObject(GameObject object) {
		object.setGame(game);
		this.objects.add(object);
	}

	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
	public void removeObject(int index) {
		this.objects.remove(index);
	}
}
