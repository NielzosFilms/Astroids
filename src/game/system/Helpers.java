package game.system;

import java.awt.*;

public class Helpers {
	public static float clampFloat(float var, float min, float max) {
		if (var <= min) {
			var = min;
		} else if (var >= max) {
			var = max;
		}
		return var;
	}

	public static int getAngle(Point origin, Point target) {
		float atan = (float) Math.atan2(target.y - origin.y, target.x - origin.x);
		float angle = (float) Math.toDegrees(atan);

		// to set 0 top the top
		//angle += 90;

		if(angle < 0){
			angle += 360;
		}

		return Math.round(angle);
	}
}
