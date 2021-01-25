package game.system.input;

import game.system.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyInput  extends KeyAdapter {
	private HashMap<Integer, Boolean> keysDown = new HashMap<>();
	private Game game;

	public KeyInput(Game game) {
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		keysDown.put(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		keysDown.put(e.getKeyCode(), false);
	}

	public boolean isKeyDown(Integer keyCode) {
		if(!keysDown.containsKey(keyCode)) return false;
		return keysDown.get(keyCode);
	}
}
