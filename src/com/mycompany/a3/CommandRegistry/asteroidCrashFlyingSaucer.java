package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public final class asteroidCrashFlyingSaucer extends Command  {
	private GameWorld gw;
	public asteroidCrashFlyingSaucer(GameWorld gw) {
		
		super("asteroid blew up saucer");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		gw.saucerCrashAsteroid();
		System.out.println("Asteroid killed saucer!");
	}
	
}
