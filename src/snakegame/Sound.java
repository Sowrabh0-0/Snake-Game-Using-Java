package snakegame;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;//To use Audio file we use clip
	URL soundURL[] = new URL[30]; //to store the sound file we use URL
	
	
	public Sound() {
		
		soundURL[0]=getClass().getResource("/sound/fruiteat.wav");
		soundURL[1]=getClass().getResource("/sound/gameover.wav");
		
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream fru =AudioSystem.getAudioInputStream(soundURL[i]);
			clip =AudioSystem.getClip();
			clip.open(fru);
		}catch(Exception e) {
			
		}
	}
	public void play() {
		clip.start();
	}
	public void start() {
		clip.stop();
	}

}
