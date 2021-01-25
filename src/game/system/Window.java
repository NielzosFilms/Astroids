package game.system;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

	public Window(int width, int height, String title, Game game) {
		JFrame f = new JFrame(title);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(width, height));
		f.setMaximumSize(new Dimension(width, height));
		f.setMinimumSize(new Dimension(width, height));
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.add(game);
		f.pack();
		f.setVisible(true);
		game.start();
	}

}
