package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public final class MissileKillAsteroidCommand extends Command  {
	private GameWorld gw;
	public MissileKillAsteroidCommand(GameWorld gw) {
		super("asteroid kill");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.asteroidDestroyed();
		System.out.println("Rock Destroyed!");
	}
	
}
