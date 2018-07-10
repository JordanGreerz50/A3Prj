package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public final class FireMissileCommand extends Command  {
	private GameWorld gw;
	public FireMissileCommand(GameWorld gw) {
		super("fire Missile");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.fireMissile();
		System.out.println("Missile Fired!");
	}
	
}