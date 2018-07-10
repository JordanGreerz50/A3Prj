package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public final class AsteroidCrashAsteroid extends Command  {
	private GameWorld gw;
	public AsteroidCrashAsteroid(GameWorld gw) {
		
		super("asteroid blew up another asteroid");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		gw.asteroidCrashAsteroid();
		System.out.println("two asteroid have killed each other!");
	}
	
}
