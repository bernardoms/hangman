package psweb.hangman;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	private String pathToAudio = "";
	
	public String getPathToAudio() {
		return pathToAudio;
	}
	
	public void setPathToAudio(String pathToAudio) {
		this.pathToAudio = pathToAudio;
	}
	
	public void playSound(String pathToAudio) {
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(new File(pathToAudio)));
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}
}
