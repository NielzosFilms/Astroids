package game.system.input;

import game.system.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class MouseInput extends MouseAdapter implements MouseMotionListener {
	private HashMap<Integer, Boolean> mouseDown = new HashMap<>();
	private int mX = 0, mY = 0;
	private Game game;

	public MouseInput(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.mX = e.getX();
		this.mY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mX = e.getX();
		this.mY = e.getY();
	}

	public void mousePressed(MouseEvent e) {
		mouseDown.put(e.getButton(), true);
	}

	public void mouseReleased(MouseEvent e) {
		mouseDown.put(e.getButton(), false);
	}

	public boolean isMouseDown(Integer button) {
		if(!mouseDown.containsKey(button)) return false;
		return mouseDown.get(button);
	}

	public int getMX() {
		return mX;
	}

	public int getMY() {
		return mY;
	}
}
