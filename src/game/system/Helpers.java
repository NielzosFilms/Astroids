package game.system;

public class Helpers {
	public static float clampFloat(float var, float min, float max) {
		if (var <= min) {
			var = min;
		} else if (var >= max) {
			var = max;
		}
		return var;
	}
}
