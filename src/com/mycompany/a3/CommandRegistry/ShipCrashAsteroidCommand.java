package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public final class ShipCrashAsteroidCommand extends Command  {
	private GameWorld gw;
	public ShipCrashAsteroidCommand(GameWorld gw) {
		super("ship crashed into asteroid");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.asteroidCrashShip();
		System.out.println("goodbye! Asteroid blew up ship");
	}
	
}