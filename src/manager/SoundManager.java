package manager;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;



public class SoundManager {
	
	private static ArrayList<Clip> STORED_CLIPS=new ArrayList<Clip>();
	private static ArrayList<AudioInputStream> STORED_FILES=new ArrayList<AudioInputStream>();
	
	
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}
	
	private static boolean MUTE=false;
	
	public static void playSound(AudioInputStream file,boolean loop){
		Clip clip=null;
		boolean exist=false;
		for(int i=0;i<STORED_FILES.size();i++){
			if(STORED_FILES.get(i).equals(file)){
				clip=STORED_CLIPS.get(i);
				BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
				muteControl.setValue(MUTE);
				exist=true;
				break;
			}
		}
		if(!exist){
			try {
				clip=AudioSystem.getClip();
				clip.open(file);
				STORED_CLIPS.add(clip);
				STORED_FILES.add(file);
				BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
				muteControl.setValue(MUTE);
			} catch (LineUnavailableException | IOException e) {
				e.printStackTrace();
			}
		}else{
			clip.setFramePosition(0);
		}
		
		if(loop){
			clip.loop(-1);
		}
		clip.start();
	}
	
	public static void setVolume(boolean mute){
		MUTE=mute;
	}
	
	public static void changeVolumeState(){
		MUTE=!MUTE;
		for(Clip clip:STORED_CLIPS){
			BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
			muteControl.setValue(MUTE);
		}
	}
}
