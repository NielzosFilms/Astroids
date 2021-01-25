package game.enums;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public enum SoundEffect {
	;

	private static final float volume = 0.5f;
	private Clip clip;

	SoundEffect(String filepath) {
		try {
			URL url = ClassLoader.getSystemResource("assets/audio/" + filepath);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (clip.isRunning()) clip.stop();
		clip.setFramePosition(0);
		setVol(volume, clip);
		clip.start();
	}

	private static void setVol(double vol, Clip clip) {
		FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		float dB = (float) (Math.log(vol) / Math.log(10) * 20);
		gain.setValue(dB);
	}

	/**
	 * Calls the constructor for all the elements.
	 * A.K.A loading all the sound effects.
	 */
	public static void init() {
		values();
		System.out.println("Sound effects loaded!");
	}
}
