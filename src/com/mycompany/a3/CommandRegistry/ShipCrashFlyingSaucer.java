package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ShipCrashFlyingSaucer extends Command   {
	private GameWorld gw;
	public ShipCrashFlyingSaucer(GameWorld gw) {
		super("ship crashed into saucer");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.saucerCrashShip();
		System.out.println("goodbye! saucer blew up ship");
	}
	
}