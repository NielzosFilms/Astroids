package game.handlers;

import game.objects.Player;
import game.system.Game;

import java.awt.*;

public class GameController {
	private Game game;
	public GameController(Game game) {
		this.game = game;
		game.handler.addObject(new Player(64, 64));
	}

	public void tick() {

	}
}
