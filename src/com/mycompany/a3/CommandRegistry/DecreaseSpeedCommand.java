package com.mycompany.a3.CommandRegistry;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public class DecreaseSpeedCommand extends Command  {
	private GameWorld gw;
	public DecreaseSpeedCommand(GameWorld gw) {
		super("speed slow");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.decreaseSpeed();
		System.out.println("speed decreased!");
	}
	
}
