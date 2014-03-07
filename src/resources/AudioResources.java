package resources;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioResources {
	public static AudioInputStream SPLASH;
	public static AudioInputStream BACKGROUND1;
	public static AudioInputStream BACKGROUND2;
	public static AudioInputStream BREAKWALL;
	public static AudioInputStream BUTTON;
	public static AudioInputStream FALL;
	public static AudioInputStream BLOK;
	
	
	public static void loadResources() throws UnsupportedAudioFileException, IOException{
		SPLASH=AudioSystem.getAudioInputStream(new File("res/audio/splash.wav"));
		BACKGROUND1=AudioSystem.getAudioInputStream(new File("res/audio/loop.wav"));
		FALL=AudioSystem.getAudioInputStream(new File("res/audio/pak.wav"));
		BLOK=AudioSystem.getAudioInputStream(new File("res/audio/blok.wav"));
	}
}
