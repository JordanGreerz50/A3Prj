package com.mycompany.a3;

import java.io.IOException;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound  {
	private Media m;
	
	private static Sound gameSounds;
	private static Sound asterCollision;
	private static Sound saucerCollision;
	private static Sound shipCollision;
	private static Sound missileCollision;
	

	public Sound(String fileName)  {
		try {
			InputStream iStream = Display.getInstance().getResourceAsStream(getClass(), ("/"+fileName));
			m = MediaManager.createMedia(iStream, "audio/wav");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void play() {
		m.setTime(0);
		m.play();
	}
	
	public void pause() {
		m.setTime(0);
		m.pause();
	}
	
	public void run() {
		m.setTime(0);
		m.play();
	}
	
	public void playAsterCollisionClip(){ 
        asterCollision.play(); 
    } 

    public void playSaucerCollisionClip(){ 
        saucerCollision.play(); 
    } 

    public void playShipCollisionClip(){ 
        shipCollision.play(); 
    } 
   
}
