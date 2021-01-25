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
	}

	public void render(Graphics g) {
		for(int i=0; i<objects.size(); i++) {
			objects.get(i).render(g);
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
